/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb
 * Arquivo:         MonitoramentoMensagensDDAServicoEJB.java
 * Data Criação:    Aug 13, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CooperativaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.LogErroCargaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ParametroDDAReprocessamentoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TipoErroCipDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.RelatorioSicoobDDAEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.relatorio.ConfigurarRelatorioJasper;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoTratamentoErroCip;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.IntegracaoCipException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BeneficiarioCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SCIDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.AlterarSituacaoBeneficiarioDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.ExcluirRelacionamentoBeneficiarioDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.MonitoramentoMensagensDDAServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.MonitoramentoMensagensDDADao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;

/**
 * MonitoramentoMensagensDDAServicoEJB
 * 
 * @author Samuell.Ramos
 */
@Stateless
@Local({ MonitoramentoMensagensDDAServicoLocal.class })
public class MonitoramentoMensagensDDAServicoEJB extends OperacionalCrudServicoEJB<SicoobDDAEntidade> implements MonitoramentoMensagensDDAServicoLocal {

    private static final String ERRO_ENVIAR_MENSAGEM_CONTINGENCIA = "integracaocip.erro.enviar.mensagem.contingencia";
    private static final String ERRO_ENVIAR_MENSAGEM_CONTINGENCIA_TIPO_MENSAGEM_INVALIDO = "integracaocip.erro.enviar.mensagem.contingencia.tipo.mensagem.invalido";
    private static final String ERRO_RECUPERAR_LOG_ERRO_CARGA = "integracaocip.erro.recuperar.log.erro.carga";
    private static final String ERRO_INTEGRACAO_INSTITUICAO_NAO_ENCONTRADA = "integracaocip.erro.integracao.instituicao.nao.encontrada";

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private MonitoramentoMensagensDDADao monitoramentoMensagensDao;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private BeneficiarioCipDao beneficiarioCipDao;
    private ExcluirRelacionamentoBeneficiarioDelegate excluirRelacionamentoBeneficiarioDelegate = OperacionalFabricaDelegates.getInstance()
            .getExcluirRelacionamentoBeneficiarioDelegate();
    private AlterarSituacaoBeneficiarioDelegate alterarSituacaoBeneficiarioDelegate = OperacionalFabricaDelegates.getInstance().getAlterarSituacaoBeneficiarioDelegate();
    private SCIDelegate sciDelegate = IntegracaoInternaFabricaDelegates.getInstance().getSCIDelegate();

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoramentoMensagensDDAServico#listarTipoMensagens(java.lang.String)
     */
    public List<TipoMensagemDDA> listarTipoMensagens(String origem) throws ComumException {
        return getDAO().listarTipoMensagensDDA(origem);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoramentoMensagensDDAServico#recuperaMensagemDDA(java.lang.Long)
     */
    public MensagemDDA recuperaMensagemDDA(Long idMensagem) throws ComumException {
        return getEm().find(MensagemDDA.class, idMensagem);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoramentoMensagensDDAServico#recuperaMensagemOrigemDDA(java.lang.Long)
     */
    public MensagemDDA recuperaMensagemOrigemDDA(Long idMensagem) throws ComumException {
        return getDAO().recuperaMensagemOrigemDDA(idMensagem);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoramentoMensagensDDAServico#reenviarMensagemCip(java.util.List, java.lang.Short)
     */
    public void reenviarMensagemCip(List<Integer> listaIdMensagem, Short idCanal) throws IntegracaoCipException, ComumNegocioException {
        for (Integer idMensagem : listaIdMensagem) {
            this.reenviarMensagemCip(idMensagem.longValue(), idCanal);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoramentoMensagensDDAServico#reenviarMensagemCip(java.lang.Long, java.lang.Short)
     */
    public void reenviarMensagemCip(Long idMensagem, Short idCanal) throws IntegracaoCipException, ComumNegocioException {
        try {
            MensagemDDA mensagem = this.recuperaMensagemDDA(idMensagem);

            atualizarTipoTratamentoMensagemRetornoCip(idMensagem, TipoTratamentoErroCip.FINALIZAR_REENVIAR_NOVA_MENSAGEM);

            /*
             * if (mensagem.getTipoMensagemDDA().getCodTipoMensagem().equals(TipoMensagemDDA.DDA0501E)) { this.reenviarMensagemCadastroCip(mensagem,
             * getMensagemDDABeneficiario(mensagem.getMensagemOrigem().getId()).getNumCpfCnpjBeneficiario()); } else if
             * (mensagem.getTipoMensagemDDA().getCodTipoMensagem().equals(TipoMensagemDDA.DDA0502E)) { this.reenviarMensagemAlterarCadastroCip(mensagem,
             * getMensagemDDABeneficiario(mensagem.getMensagemOrigem().getId()).getNumCpfCnpjBeneficiario()); } else
             */
            if (mensagem.getTipoMensagemDDA().getCodTipoMensagem().equals(TipoMensagemDDA.DDA0503E)) {
                excluirRelacionamentoBeneficiarioDelegate
                        .excluirRelacionamentoBeneficiario(getMensagemDDABeneficiario(mensagem.getMensagemOrigem().getId()).getNumCpfCnpjBeneficiario(), new DateTimeDB(), idCanal);
            } else if (mensagem.getTipoMensagemDDA().getCodTipoMensagem().equals(TipoMensagemDDA.DDA0505E)) {
                alterarSituacaoBeneficiarioDelegate
                        .processarAlterarSituacaoBeneficiario(getMensagemDDABeneficiario(mensagem.getMensagemOrigem().getId()).getNumCpfCnpjBeneficiario(), idCanal);
            } else {
                throw new IntegracaoCipException(ERRO_ENVIAR_MENSAGEM_CONTINGENCIA_TIPO_MENSAGEM_INVALIDO);
            }

            mensagem.setDescErroProtocolo(null);
        } catch (ComumException e) {
            getLogger().erro(e, ERRO_ENVIAR_MENSAGEM_CONTINGENCIA);
            throw new IntegracaoCipException(e);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoramentoMensagensDDAServico#recuperaTipoErroCip()
     */
    public List<TipoErroCipDto> recuperaTipoErroCip() throws ComumException {
        return getDAO().listarTiposErroCIP();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoramentoMensagensDDAServico#recuperaRegistroErroCarga(java.lang.Long)
     */
    public LogErroCargaDto recuperaRegistroErroCarga(Long idLogErroCarga) throws ComumException {
        LogErroCargaDto logCargaDto = new LogErroCargaDto();
        try {
            logCargaDto.setListaErroCarga(getDAO().obterLogErroCargaDTO(idLogErroCarga));
            logCargaDto.setListaCooperativa(new ArrayList<CooperativaDto>());
            for (LogErroCargaDto logCarga : logCargaDto.getListaErroCarga()) {
                InstituicaoDto instituicao = sciDelegate.obterInstituicaoCache(logCarga.getIdInstituicao());
                if (ObjectUtil.isNull(instituicao)) {
                    throw new IntegracaoCipException(ERRO_INTEGRACAO_INSTITUICAO_NAO_ENCONTRADA);
                }
                logCargaDto.getListaCooperativa().add(new CooperativaDto(instituicao.getNumCooperativa(), instituicao.getNomeInstituicao()));
            }
        } catch (ComumException e) {
            getLogger().erro(e, ERRO_RECUPERAR_LOG_ERRO_CARGA);
            throw e;
        }
        return logCargaDto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoramentoMensagensDDAServico#recuperaParametrosReprocessamento(java.lang.Long)
     */
    public ParametroDDAReprocessamentoDto recuperaParametrosReprocessamento(Long idArquivo) throws ComumException {
        ParametroDDAReprocessamentoDto parametrosProcessamento = new ParametroDDAReprocessamentoDto();

        String emExecucao = em.find(ParametroDDA.class, ParametroDDA.CONTINGENCIA_CARGA_BENEFICIARIOS_POR_MENSAGEM_EM_EXECUCAO).getValorParametro();
        parametrosProcessamento.setEmExecucao(emExecucao.equals(Constantes.STRING_NUMERO_1) ? Boolean.TRUE : Boolean.FALSE);

        parametrosProcessamento.setQtdMaximaErros(Long.parseLong(em.find(ParametroDDA.class, ParametroDDA.QTD_MAXIMA_ERROS_CONTINGENCIA_POR_MENSAGEM).getValorParametro()));

        String parametro14 = em.find(ParametroDDA.class, ParametroDDA.QTD_TOTAL_ERROS_CONTINGENCIA_POR_MENSAGEM_EM_EXECUCAO).getValorParametro();
        String[] splitParametro14 = parametro14.split("/");
        if (splitParametro14.length == 2) {
            Long totalErrosArquivo = Long.parseLong(splitParametro14[1]);
            Long toralErrosAtual = getDAO().obterQtdTotalErrosBeneficiariosPorArquivo(idArquivo);
            parametrosProcessamento.setQtdTotalErrosExecucao(totalErrosArquivo - toralErrosAtual);
        } else {
            parametrosProcessamento.setQtdTotalErrosExecucao(0L);
        }

        parametrosProcessamento.setQtdErrosEnviados(parametro14);

        return parametrosProcessamento;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoramentoMensagensDDAServico#configurarRelatorioMonitoramentoMensagem(java.lang.Long,
     *      br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO)
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ConfigurarRelatorioJasper configurarRelatorioMonitoramentoMensagem(Long idMensagem, UsuarioBancoobDTO usuario) throws BancoobException {

        MensagemDDA mensagemDDA = getEm().find(MensagemDDA.class, idMensagem);

        String xml = mensagemDDA.getXmlMensagem();
        mensagemDDA.setXmlMensagem(ObjectUtil.identXmlString(xml));
        if (!ObjectUtil.isNull(mensagemDDA.getMensagemOrigem())) {
            String xmlPai = mensagemDDA.getMensagemOrigem().getXmlMensagem();
            mensagemDDA.getMensagemOrigem().setXmlMensagem(ObjectUtil.identXmlString(xmlPai));
        }

        List<MensagemDDA> listaMensagensDDA = new ArrayList<>();
        listaMensagensDDA.add(mensagemDDA);

        return new ConfigurarRelatorioJasper(RelatorioSicoobDDAEnum.SDDA501, listaMensagensDDA, usuario);
    }

    /**
     * @param idMensagemDDA
     * @return
     * @throws ComumException MensagemDDABeneficiario
     * 
     */
    private MensagemDDABeneficiario getMensagemDDABeneficiario(Long idMensagemDDA) throws ComumException {
        return getBeneficiarioCipDao().obterMensagemDDABeneficiario(idMensagemDDA);
    }

    /**
     * Método responsável por atualizar o erroTratado da Mensagem de Retorno Cip
     * 
     * @param idMensagemDDA
     * @param codTipoTratamentoErroCip
     * @throws ComumException void
     * 
     */
    private void atualizarTipoTratamentoMensagemRetornoCip(Long idMensagemDDA, int codTipoTratamentoErroCip) throws ComumException {
        getDAO().atualizarTipoTratamentoMensagemRetornoCip(idMensagemDDA, codTipoTratamentoErroCip);
    }

    /**
     * Método responsável por
     * 
     * @param mensagem
     * @throws ComumException void
     * @throws ComumNegocioException
     * 
     */
    /*
     * private void reenviarMensagemCadastroCip(MensagemDDA mensagem, String numCpfCnpj) throws ComumException, ComumNegocioException { List<Integer>
     * listaNumCooperativa = new ArrayList<Integer>(); MQRecebimento mqRecebimento = (MQRecebimento) LeitorXmlUtil.desempacotarArquivo(MQRecebimento.class,
     * mensagem.getXmlMensagem());
     * 
     * // TODO George.santos nao vai mais ser feito o reenvio dessa maneira, temos que verificar uma forma de fazer o reenvio das msgs
     * 
     * for (GrupoDDA0501ConvComplexType grupoConvenio : mqRecebimento.getMsgSPB().getDDA0501E().getGrupoDDA0501Conv()) {
     * listaNumCooperativa.add(Integer.parseInt(grupoConvenio.getAgDest().getValue())); }
     * 
     * 
     * getCadastrarBeneficiarioDelegate().processarReenvioMensagemCadastroBeneficiario(numCpfCnpj, listaNumCooperativa); }
     * 
     * /** Método responsável por
     * 
     * @param mensagem
     * 
     * @throws ComumException void
     * 
     * @throws ComumNegocioException
     * 
     * 
     * private void reenviarMensagemAlterarCadastroCip(MensagemDDA mensagem, String numCpfCnpj) throws ComumException, ComumNegocioException {
     * List<ConvenioAlteracaoDDADto> listaConvenio = new ArrayList<ConvenioAlteracaoDDADto>(); MQRecebimento mqRecebimento = (MQRecebimento)
     * LeitorXmlUtil.desempacotarArquivo(MQRecebimento.class, mensagem.getXmlMensagem());
     * 
     * // TODO George.santos nao vai mais ser feito o reenvio dessa maneira, temos que verificar uma forma de fazer o reenvio das msgs /* for
     * (GrupoDDA0502ConvComplexType grupoConvenio : mqRecebimento.getMsgSPB().getDDA0502E().getGrupoDDA0502Conv()) { listaConvenio.add(new
     * ConvenioAlteracaoDDADto(TipoManutencaoEnum.getBy(grupoConvenio.getTpManutConv().getValue()), Integer.parseInt(grupoConvenio.getAgDest() .getValue()))); }
     * 
     * 
     * getAlterarCadastroBeneficiarioDelegate().processarReenvioAlterarCadastroBeneficiario(getArquivoCipDao().obterBeneficiarioPorCpfCnpj(numCpfCnpj),
     * listaConvenio); }
     */

    /**
     * @return o atributo integracaoCipDao
     */
    private BeneficiarioCipDao getBeneficiarioCipDao() {
        return beneficiarioCipDao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB#getDAO()
     */
    public MonitoramentoMensagensDDADao getDAO() {
        return monitoramentoMensagensDao;
    }

    /**
     * @return o atributo em
     */
    private EntityManager getEm() {
        return em;
    }

}
