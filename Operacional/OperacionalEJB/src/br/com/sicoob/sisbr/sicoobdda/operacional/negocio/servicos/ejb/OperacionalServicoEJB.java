/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-interna-privada-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracao.interna.privada.negocio.servicos.ejb
 * Arquivo:         IntegracaoInternaPrivadaEJB.java
 * Data Criação:    Oct 22, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemPendenteBeneficiarioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.HistoricoIfStatusBeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidades.HistoricoStatusBeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.IFBeneficiarioAlerta;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.OperacionalServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacaoBeneficiarioDDADao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;

/**
 * OperacionalServicoEJB
 * 
 * @author rafael.silva
 */
public abstract class OperacionalServicoEJB extends ComumServicoEJB implements OperacionalServico {

    protected static final String ERRO_BENEFICARIO_NAO_CADASTRADO_CIP = "integracaocip.erro.beneficario.nao.cadastrado.cip";
    protected static final String ERRO_CNPJ_CPF_REPRESENTANTE_INVALIDO = "integracaocip.erro.cnpj.cpf.representante.invalido";
    protected static final String ERRO_TIPO_PESSOA_REPRESENTANTE_INVALIDO = "integracaocip.erro.tipo.pessoa.representante.invalido";
    protected static final String ERRO_TP_PRODUTO_CONVENIO_INVALIDA = "integracaocip.erro.tp.produto.convenio.invalida";
    protected static final String ERRO_AGENCIA_DESTINO_CONVENIO_INVALIDA = "integracaocip.erro.agencia.destino.convenio.invalida";
    protected static final String ERRO_TP_AGENCIA_DESTINO_CONVENIO_INVALIDA = "integracaocip.erro.tp.agencia.destino.convenio.invalida";
    protected static final String ERRO_DATA_INICIO_RELACIONAMENTO_CONVENIO_INVALIDA = "integracaocip.erro.data.inicio.relacionamento.convenio.invalida";
    protected static final String ERRO_NOME_RAZAO_SOCIAL_BENEFICIARIO_INVALIDO = "integracaocip.erro.nome.razao.social.beneficiario.invalido";
    protected static final String ERRO_CNPJ_CPF_BENEFICIARIO_INVALIDO = "integracaocip.erro.cnpj.cpf.beneficiario.invalido";
    protected static final String ERRO_TIPO_PESSOA_INVALIDO = "integracaocip.erro.tipo.pessoa.invalido";
    protected static final String ERRO_INSTITUICAO_SCI_INVALIDA = "integracaocip.erro.instituicao.sci.invalida";
    protected static final String ERRO_CARGA_EM_EXECUCAO = "integracaocip.erro.carga.em.execucao";
    protected static final String ERRO_DESCOMPACTAR_XML_RECEBIDO = "integracaocip.erro.descompactar.xml.recebido";
    protected static final String ERRO_RECUPERAR_BYTES_ARQUIVO = "integracaocip.erro.recuperar.bytes.arquivo";
    protected static final String ERRO_COMPACTAR_XML_ENVIO = "integracaocip.erro.compactar.xml.envio";
    protected static final String ERRO_SALVAR_ARQUIVO_DIRETORIO = "integracaocip.erro.salvar.arquivo.diretorio";
    protected static final String ERRO_ENVIAR_MENSAGEM_SSPB = "integracaocip.erro.enviar.mensagem.sspb";
    protected static final String ERRO_GRUPO_CONVENIO_INVALIDO = "integracaocip.erro.grupo.convenio.invalido";
    protected static final String ERRO_DATA_INICIO_RELACIONAMENTO_PARTICIPANTE_INVALIDA = "integracaocip.erro.data.inicio.relacionamento.participante.invalida";
    protected static final String ERRO_SITUACAO_RELACIONAMENTO_PARTICIPANTE_INVALIDA = "integracaocip.erro.situacao.relacionamento.participante.invalida";
    protected static final String ERRO_DATA_HORA_SITUACAO_BENEFICIARIO_INVALIDA = "integracaocip.erro.data.hora.situacao.beneficiario.invalida";
    protected static final String ERRO_SITUACAO_BENEFICIARIO_INVALIDA = "integracaocip.erro.situacao.beneficiario.invalida";
    protected static final String ERRO_BENEFICARIO_JA_CADASTRADO = "integracaocip.erro.beneficario.ja.cadastrado";

    protected static final String ERRO_DATA_SITUACAO_BENEFICIARIO_INVALIDO = "integracaocip.erro.data.situacao.beneficiario.invalido";
    protected static final String ERRO_SITUACAO_BENEFICIARIO_INVALIDO = "integracaocip.erro.situacao.beneficiario.invalido";
    protected static final String ERRO_PROCESSAR_DDA_AVISA_ALTERACAO_SITUACAO = "integracaocip.erro.processar.dda.avisa.alteracao.situacao";

    /**
     * {@inheritDoc}
     * 
     */
    protected abstract EntityManager getEm();

    private OperacaoBeneficiarioDDADao operacionalBeneficiarioDDADao;

    /**
     * Método responsável por
     * 
     * @return Boolean
     * 
     */
    protected Boolean verificaReplicacaoLegadoAutorizada() {
        ParametroDDA parametro = getEm().find(ParametroDDA.class, ParametroDDA.REPLICAR_BENEFICIARIO_LEGADO);
        return (parametro.getValorParametro().equals(Constantes.STRING_NUMERO_1));
    }

    /**
     * Método responsável por copiar a situação atual e as ifs orinadoras da situação atual do beneficiário para tabela de historico.
     * 
     * @param beneficiario void
     * 
     */
    protected void moverSituacaoAtualParaHistoricoStatus(BeneficiarioDDA beneficiario) {
        moverListaIfBeneficiarioAlertaParaHistoricoStatus(beneficiario, beneficiario.getListaIFBeneficiarioAlerta());
    }

    /**
     * Método responsável por validar as mensagens que serao encaminhadas para cip se tem alguma pendencia no envio
     * 
     * @param numCpfCnpjBeneficiario
     * @param tipoDocBeneficiarioEnum void
     * @throws ComumException
     * 
     */
    protected void validarMensagemDDAEnvioPendente(String numCpfCnpjBeneficiario) throws ComumNegocioException, ComumException {
        getLogger().debug("###### inicio metodo validarMensagemDDAEnvioPendete ######");
        getLogger().debug("numCpfCnpjBeneficiario = " + numCpfCnpjBeneficiario);
        StringBuilder str = new StringBuilder();
        List<MensagemPendenteBeneficiarioDto> listaMensamgePendente = getOperacionalBeneficiarioDDADao().listarMensagemPendenteBeneficiario(numCpfCnpjBeneficiario);

        for (MensagemPendenteBeneficiarioDto mensagemPendente : listaMensamgePendente) {
            if (ObjectUtil.isEmpty(mensagemPendente.getCodTipoErroCIP()) && ObjectUtil.isEmpty(mensagemPendente.getDescTipoErroCIP())) {
                throw new ComumNegocioException("integracaocip.erro.processar.dda.mensagem.envio.pendente");
            } else {
                str.append(mensagemPendente.getCodTipoErroCIP());
                str.append(" - ");
                str.append(mensagemPendente.getCodTipoErroCIP());
                str.append("\n");
                break;
            }
        }

        if (str.length() > 0) {
            throw new ComumNegocioException(str.toString());
        }

        getLogger().debug("###### fim metodo validarMensagemDDAEnvioPendete ######");
    }

    /**
     * Método responsável por mover a situação atual e as ifs orinadoras da situação atual do beneficiário para tabela de historico.
     * 
     * @param beneficiario void
     * 
     */
    protected void moverListaIfBeneficiarioAlertaParaHistoricoStatus(BeneficiarioDDA beneficiario, List<IFBeneficiarioAlerta> listaIFBeneficiarioAlerta) {
        // Move a situacao atual para HistoricoStatus/HistoricoIF
        HistoricoStatusBeneficiarioDDA historicoStatus = new HistoricoStatusBeneficiarioDDA();
        historicoStatus.setBeneficiarioDDA(beneficiario);
        historicoStatus.setDataHoraCadastro(new DateTimeDB());
        historicoStatus.setSituacaoBeneficiarioDDA(beneficiario.getSituacaoBeneficiarioDDA());

        historicoStatus.setListaHistoricoIfStatusBeneficiario(new ArrayList<HistoricoIfStatusBeneficiario>());
        for (IFBeneficiarioAlerta ifBeneficiarioAlerta : listaIFBeneficiarioAlerta) {
            HistoricoIfStatusBeneficiario historicoIf = new HistoricoIfStatusBeneficiario();
            historicoIf.setCodISPBPartDestinatarioOrigAlerta(ifBeneficiarioAlerta.getCodIspbDestinatarioOriginalAlerta());
            historicoIf.setDataHoraCadastro(new DateTimeDB());
            historicoIf.setHistoricoStatusBeneficiarioDDA(historicoStatus);

            historicoStatus.getListaHistoricoIfStatusBeneficiario().add(historicoIf);

            getEm().remove(ifBeneficiarioAlerta);
        }
        getEm().persist(historicoStatus);
    }

    /**
     * Método responsável por setar a lista de ifs origanarias do alerta.
     * 
     * @param listaISPBOriginarioAlerta
     * @param beneficiario void
     * 
     */
    protected void setListaIFBeneficiarioAlerta(List<String> listaISPBOriginarioAlerta, BeneficiarioDDA beneficiario) {
        for (String ispbAlerta : listaISPBOriginarioAlerta) {
            setIFBeneficiarioAlerta(ispbAlerta, beneficiario);
        }
    }

    /**
     * Método responsável por setar a ifs origanarias do alerta.
     * 
     * @param listaISPBOriginarioAlerta
     * @param beneficiario void
     * 
     */
    protected void setIFBeneficiarioAlerta(String ispbAlerta, BeneficiarioDDA beneficiario) {
        IFBeneficiarioAlerta ifAlerta = new IFBeneficiarioAlerta();
        ifAlerta.setBeneficiarioDDA(beneficiario);
        ifAlerta.setCodIspbDestinatarioOriginalAlerta(ispbAlerta);
        ifAlerta.setDataHoraAtualizacao(new DateTimeDB());

        beneficiario.getListaIFBeneficiarioAlerta().add(ifAlerta);
    }

    /**
     * Método responsável por recuperar o diretorio.
     * 
     * @param idParametroDiretorio
     * @return String
     * 
     */
    protected String getDiretorio(Long idParametroDiretorio) {
        return getEm().find(ParametroDDA.class, idParametroDiretorio).getValorParametro();
    }

    /**
     * @return o atributo operacionalBeneficiarioDDADao
     */
    public OperacaoBeneficiarioDDADao getOperacionalBeneficiarioDDADao() {
        if (ObjectUtil.isNull(operacionalBeneficiarioDDADao)) {
            operacionalBeneficiarioDDADao = OperacionalDaoFactory.getInstance().criarOperacaoBeneficiarioDDADao();
        }
        return operacionalBeneficiarioDDADao;
    }

    /**
     * Define o atributo operacionalBeneficiarioDDADao
     */
    public void setOperacionalBeneficiarioDDADao(OperacaoBeneficiarioDDADao operacionalBeneficiarioDDADao) {
        this.operacionalBeneficiarioDDADao = operacionalBeneficiarioDDADao;
    }

}
