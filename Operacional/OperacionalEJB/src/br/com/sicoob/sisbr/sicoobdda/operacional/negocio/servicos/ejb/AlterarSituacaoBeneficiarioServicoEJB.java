/**
 * Projeto:         Cobranca Bancaria
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.negocio.servicos.ejb
 * Arquivo:         CadastrarBeneficiarioServicoEJB.java
 * Data Criacao:    May 8, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.util.MensagemUtil;
import br.com.bancoob.validacao.ValidacaoCnpj;
import br.com.bancoob.validacao.ValidacaoCpf;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.AlteraSituacaoBeneficiarioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.RetornoAlterarSituacaoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.IFBeneficiarioAlerta;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ReplicarBeneficiarioLegadoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BeneficiarioCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.AlterarSituacaoBeneficiarioServicoLocal;

/**
 * AlterarSituacaoBeneficiarioServicoEJB
 * 
 * @author Rafael.Silva
 */
@Stateless
@Local({ AlterarSituacaoBeneficiarioServicoLocal.class })
public class AlterarSituacaoBeneficiarioServicoEJB extends OperacionalServicoEJB implements AlterarSituacaoBeneficiarioServicoLocal {

    private static final String ERRO_ALTERAR_SITUACAO_BANCOOB_ORIGEM = "integracaocip.erro.alterar.situacao.bancoob.origem";
    private static final String ERRO_ALTERAR_SITUACOES_IGUAIS = "integracaocip.erro.alterar.situacoes.iguais";
    private static final String ERRO_ALTERAR_SITUACAO_APTO_PARA_APTO = "integracaocip.erro.alterar.situacao.apto.para.apto";

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private BeneficiarioCipDao dao;

    private ReplicarBeneficiarioLegadoDelegate replicarBeneficiarioLegadoDelegate;

    private MensagemDDADelegate mensagemDDADelegate;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.IntegracaoCipServicoEJB.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }

    /**
     * @return the dao
     */
    public BeneficiarioCipDao getDao() {
        return dao;
    }

    /**
     * @return o atributo delegate
     */
    public MensagemDDADelegate getMensagemDDADelegate() {
        if (mensagemDDADelegate == null) {
            mensagemDDADelegate = IntegracaoCipFabricaDelegates.getInstance().getMensagemDDADelegate();
        }

        return mensagemDDADelegate;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.AlterarSituacaoBeneficiarioServico#processarAlterarSituacaoBeneficiario(java.lang.String)
     */
    public void processarAlterarSituacaoBeneficiario(String numCpfCnpj, Short idCanal) throws ComumException, ComumNegocioException {
        BeneficiarioDDA beneficiario = getDao().obterBeneficiarioDiferenteDeSemCadastro(numCpfCnpj);

        AlteraSituacaoBeneficiarioDto alteraSitBeneficiarioDto = new AlteraSituacaoBeneficiarioDto(numCpfCnpj);
        alteraSitBeneficiarioDto.setSituacaoBeneficiario(SituacaoBeneficiarioEnum.getBy(beneficiario.getSituacaoBeneficiarioDDA().getCodSituacaoBeneficiario()));
        alteraSitBeneficiarioDto.setDataSituacaoBeneficiario(beneficiario.getDataHoraUltimaAtualizacao());
        alteraSitBeneficiarioDto.setCnpjCpfBeneficiario(numCpfCnpj);

        alterarSituacaoMensagemDDABeneficiario(alteraSitBeneficiarioDto, beneficiario, idCanal);

        atualizarSituacaoBeneficiario(beneficiario, alteraSitBeneficiarioDto.getSituacaoBeneficiario().getCodDominio(), Boolean.TRUE);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.AlterarSituacaoBeneficiarioServico#processarAlterarSituacaoBeneficiario(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.AlteraSituacaoBeneficiarioDto)
     */
    public RetornoAlterarSituacaoBeneficiarioEnum processarAlterarSituacaoBeneficiario(AlteraSituacaoBeneficiarioDto alteraSitBeneficiarioDto, Short idCanal)
            throws ComumException, ComumNegocioException {
        BeneficiarioDDA beneficiario = getDao().obterBeneficiarioDiferenteDeSemCadastro(alteraSitBeneficiarioDto.getCnpjCpfBeneficiario());

        alterarSituacaoMensagemDDABeneficiario(alteraSitBeneficiarioDto, beneficiario, idCanal);

        return atualizarSituacaoBeneficiario(beneficiario, alteraSitBeneficiarioDto.getSituacaoBeneficiario().getCodDominio(), Boolean.TRUE);
    }

    /**
     * Método responsável por criar a mensagemDDA e suas respectiva mensagemBeneficiario
     * 
     * @param alteraSitBeneficiarioDto
     * @param beneficiario
     * @throws ComumNegocioException void
     * @throws ComumException
     * 
     */
    private void alterarSituacaoMensagemDDABeneficiario(AlteraSituacaoBeneficiarioDto alteraSitBeneficiarioDto, BeneficiarioDDA beneficiario, Short idCanal)
            throws ComumNegocioException, ComumException {
        validarMensagemDDAEnvioPendente(alteraSitBeneficiarioDto.getCnpjCpfBeneficiario());
        validarDados(alteraSitBeneficiarioDto);
        validarBeneficiarioCadastrado(beneficiario);

        criarMensagemAlterarSituacaoBeneficiario(alteraSitBeneficiarioDto, beneficiario.getNumIdentBeneficiario(), idCanal);
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @param numIdentBeneficiario
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private void criarMensagemAlterarSituacaoBeneficiario(AlteraSituacaoBeneficiarioDto dto, Long numIdentBeneficiario, Short idCanal) throws ComumNegocioException, ComumException {
        MensagemDDABeneficiario mensagemDDABeneficiario = new MensagemDDABeneficiario();
        mensagemDDABeneficiario.setCodTipoPessoaBeneficiario(dto.getTipoPessoaBeneficiario().getCodDominioCip());
        mensagemDDABeneficiario.setNumCpfCnpjBeneficiario(dto.getCnpjCpfBeneficiario());
        mensagemDDABeneficiario.setCodSituacaoBeneficiario(dto.getSituacaoBeneficiario().getCodDominio());
        mensagemDDABeneficiario.setDataHoraSituacao(new DateTimeDB());

        getMensagemDDADelegate().incluir(mensagemDDABeneficiario, TipoMensagemDDA.DDA0505, new DateTimeDB(), getUsuarioLogado(), getIdInstituicaoLogado(), idCanal);
    }

    /**
     * Método responsável por verificar a consistencia dos dados antes do envio.
     * 
     * @param dto
     * @throws NegocioException void
     * 
     */
    private void validarDados(AlteraSituacaoBeneficiarioDto dto) throws ComumNegocioException {
        if (ObjectUtil.isNull(dto.getTipoPessoaBeneficiario())) {
            throw new ComumNegocioException(ERRO_TIPO_PESSOA_INVALIDO);
        } else if (ObjectUtil.isEmpty(dto.getCnpjCpfBeneficiario())) {
            throw new ComumNegocioException(ERRO_CNPJ_CPF_BENEFICIARIO_INVALIDO);
        } else if (dto.getTipoPessoaBeneficiario().equals(TipoPessoaEnum.PF)) {
            if (!new ValidacaoCpf(dto.getCnpjCpfBeneficiario(), null).validar()) {
                throw new ComumNegocioException(ERRO_CNPJ_CPF_BENEFICIARIO_INVALIDO);
            }
        } else if (dto.getTipoPessoaBeneficiario().equals(TipoPessoaEnum.PJ)) {
            if (!new ValidacaoCnpj(dto.getCnpjCpfBeneficiario(), null).validar()) {
                throw new ComumNegocioException(ERRO_CNPJ_CPF_BENEFICIARIO_INVALIDO);
            }
        }
        if (ObjectUtil.isNull(dto.getSituacaoBeneficiario())) {
            throw new ComumNegocioException(ERRO_SITUACAO_BENEFICIARIO_INVALIDO);
        } else if (ObjectUtil.isNull(dto.getDataSituacaoBeneficiario())) {
            throw new ComumNegocioException(ERRO_DATA_SITUACAO_BENEFICIARIO_INVALIDO);
        }
    }

    /**
     * Método responsável por verificar se o beneficiário é válido para alteração.
     * 
     * @param beneficiario
     * @throws ComumNegocioException void
     * 
     */
    private void validarBeneficiarioCadastrado(BeneficiarioDDA beneficiario) throws ComumNegocioException {
        if (ObjectUtil.isNull(beneficiario) || ObjectUtil.isEmpty(beneficiario.getNumIdentBeneficiario())) {
            throw new ComumNegocioException(ERRO_BENEFICARIO_NAO_CADASTRADO_CIP);
        }
    }

    /**
     * Método responsável por realizar a alteração da situacao do beneficiario na base DDABeneficiarios antes de enviar a mesagem a CIP.
     * 
     * @param beneficiario
     * @param codNovaSituacao void
     * @return
     * @throws ComumException
     * 
     */
    private RetornoAlterarSituacaoBeneficiarioEnum atualizarSituacaoBeneficiario(BeneficiarioDDA beneficiario, String codNovaSituacao, Boolean reenvio) throws ComumException {
        String codSituacaoAtual = beneficiario.getSituacaoBeneficiarioDDA().getCodSituacaoBeneficiario();
        Boolean alterarSituacao = Boolean.TRUE;
        RetornoAlterarSituacaoBeneficiarioEnum retorno = RetornoAlterarSituacaoBeneficiarioEnum.SUCESSO;

        if (codSituacaoAtual.equals(SituacaoBeneficiarioDDA.APTO)) {
            if (codNovaSituacao.equals(SituacaoBeneficiarioDDA.INAPTO) || codNovaSituacao.equals(SituacaoBeneficiarioDDA.EM_ANALISE)) {
                gravarNovaSituacaoParaIfBancoob(beneficiario);
            } else if (!reenvio) {
                throw new ComumException(ERRO_ALTERAR_SITUACAO_APTO_PARA_APTO);
            }
        } else if (codSituacaoAtual.equals(SituacaoBeneficiarioDDA.EM_ANALISE)) {
            if (codNovaSituacao.equals(SituacaoBeneficiarioDDA.INAPTO)) {
                gravarNovaSituacaoParaIfBancoob(beneficiario);
            } else if (codNovaSituacao.equals(SituacaoBeneficiarioDDA.APTO)) {
                alterarSituacao = alterarSituacaoValidaOrigemAlertaBancoob(beneficiario);
                if (!alterarSituacao) {
                    retorno = RetornoAlterarSituacaoBeneficiarioEnum.ALERTA;
                }
            } else if (!reenvio) {
                if (bancoobOriginadorSituacaoAtualAlerta(beneficiario.getListaIFBeneficiarioAlerta())) {
                    throw new ComumException(MensagemUtil.getString(ERRO_ALTERAR_SITUACOES_IGUAIS, SituacaoBeneficiarioEnum.EM_ANALISE.getDescSituacao()));
                }
                setIFBeneficiarioAlerta(Constantes.ISPB_BANCOOB, beneficiario);
                alterarSituacao = Boolean.FALSE;
                retorno = RetornoAlterarSituacaoBeneficiarioEnum.ALERTA;
            }
        } else if (codSituacaoAtual.equals(SituacaoBeneficiarioDDA.INAPTO)) {
            if (codNovaSituacao.equals(SituacaoBeneficiarioDDA.EM_ANALISE) || codNovaSituacao.equals(SituacaoBeneficiarioDDA.APTO)) {
                alterarSituacao = alterarSituacaoValidaOrigemAlertaBancoob(beneficiario);
                if (!alterarSituacao) {
                    retorno = RetornoAlterarSituacaoBeneficiarioEnum.ALERTA;
                }
            } else if (!reenvio) {
                if (bancoobOriginadorSituacaoAtualAlerta(beneficiario.getListaIFBeneficiarioAlerta())) {
                    throw new ComumException(MensagemUtil.getString(ERRO_ALTERAR_SITUACOES_IGUAIS, SituacaoBeneficiarioEnum.INAPTO.getDescSituacao()));
                }
                setIFBeneficiarioAlerta(Constantes.ISPB_BANCOOB, beneficiario);
                alterarSituacao = Boolean.FALSE;
                retorno = RetornoAlterarSituacaoBeneficiarioEnum.ALERTA;
            }
        }

        if (alterarSituacao) {
            beneficiario.setSituacaoBeneficiarioDDA(em.find(SituacaoBeneficiarioDDA.class, codNovaSituacao));
            beneficiario.setDataHoraUltimaAtualizacao(new DateTimeDB());

            if (this.verificaReplicacaoLegadoAutorizada()) {
                getReplicarBeneficiarioLegadoDelegate().replicarSituacaoBeneficiarioFraudeLegado(beneficiario);
            }
        }

        return retorno;
    }

    /**
     * Método responsável por copiar a situação atual para o HistoricoSituacao e Setar a If orignadora do novo alerta como Bancoob.
     * 
     * @param beneficiario void
     * 
     */
    private void gravarNovaSituacaoParaIfBancoob(BeneficiarioDDA beneficiario) {
        moverSituacaoAtualParaHistoricoStatus(beneficiario);
        // Gravar novo status.
        beneficiario.setListaIFBeneficiarioAlerta(new ArrayList<IFBeneficiarioAlerta>());
        setIFBeneficiarioAlerta(Constantes.ISPB_BANCOOB, beneficiario);
    }

    /**
     * Método responsável por verificar se existe orignador da situacao Bancoob e realizar a alteracao para uma nova situacao.
     * 
     * @param beneficiario
     * @return
     * @throws ComumException Boolean
     * 
     */
    private Boolean alterarSituacaoValidaOrigemAlertaBancoob(BeneficiarioDDA beneficiario) throws ComumException {
        Boolean alterarSituacao = Boolean.TRUE;
        List<IFBeneficiarioAlerta> listaIFBeneficiarioAlertaBancoob = getListaIFBeneficiarioAlertaBancoob(beneficiario.getListaIFBeneficiarioAlerta());
        if (ObjectUtil.isEmpty(listaIFBeneficiarioAlertaBancoob)) {
            throw new ComumException(ERRO_ALTERAR_SITUACAO_BANCOOB_ORIGEM);
        } else if (listaIFBeneficiarioAlertaBancoob.size() < beneficiario.getListaIFBeneficiarioAlerta().size()) {
            beneficiario.getListaIFBeneficiarioAlerta().removeAll(listaIFBeneficiarioAlertaBancoob);
            moverListaIfBeneficiarioAlertaParaHistoricoStatus(beneficiario, listaIFBeneficiarioAlertaBancoob);
            alterarSituacao = Boolean.FALSE;
        } else {
            gravarNovaSituacaoParaIfBancoob(beneficiario);
        }
        return alterarSituacao;
    }

    /**
     * Método responsável por verificar se o Bancoob esta na lista de originadores do Alerta.
     * 
     * @param listaIfAlertaSituacao
     * @return Boolean
     * 
     */
    private Boolean bancoobOriginadorSituacaoAtualAlerta(List<IFBeneficiarioAlerta> listaIfAlertaSituacao) {
        if (ObjectUtil.isEmpty(getListaIFBeneficiarioAlertaBancoob(listaIfAlertaSituacao))) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * Método responsável por getListaIFBeneficiarioAlertaBancoob
     * 
     * @param listaIfAlertaSituacao
     * @return List<IFBeneficiarioAlerta>
     * 
     */
    private List<IFBeneficiarioAlerta> getListaIFBeneficiarioAlertaBancoob(List<IFBeneficiarioAlerta> listaIfAlertaSituacao) {
        List<IFBeneficiarioAlerta> listaIFBeneficiarioAlertaBancoob = new ArrayList<IFBeneficiarioAlerta>();
        for (IFBeneficiarioAlerta ifBeneficiarioAlerta : listaIfAlertaSituacao) {
            if (ifBeneficiarioAlerta.getCodIspbDestinatarioOriginalAlerta().equals(Constantes.ISPB_BANCOOB)) {
                listaIFBeneficiarioAlertaBancoob.add(ifBeneficiarioAlerta);
            }
        }
        return listaIFBeneficiarioAlertaBancoob;
    }

    /**
     * 
     * @return ReplicarBeneficiarioLegadoDao
     * 
     */
    private ReplicarBeneficiarioLegadoDelegate getReplicarBeneficiarioLegadoDelegate() {
        if (replicarBeneficiarioLegadoDelegate == null) {
            replicarBeneficiarioLegadoDelegate = IntegracaoCipFabricaDelegates.getInstance().getReplicarBeneficiarioLegadoDelegate();
        }
        return replicarBeneficiarioLegadoDelegate;
    }
}
