package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import static br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes.SEPARADOR_DE_DADOS;

import java.lang.reflect.Field;
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
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.XMLCipDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoInstanciaProcessamentoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDao;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.entidades.ErroMensagemRetornoCip;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoErroCip;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipMensagemDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ProcessarRetornoDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.MensagemDesconhecidaException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ProcessarRetornoDDAServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.LeitorXmlUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ArqRecebimento;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.GEN0004.GEN0004ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.MQRecebimento;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.respostaenvio.MQRespostaEnvio;

/**
 * CadastrarBeneficiarioServicoEJB
 * 
 * @author Rafael.Silva
 */
@Stateless
@Local({ ProcessarRetornoDDAServicoLocal.class })
public class ProcessarRetornoDDAServicoEJB extends IntegracaoCipServicoEJB implements ProcessarRetornoDDAServicoLocal {

    private static final String ERRO_PROCESSAR_MENSAGEM_RECEBIDA = "integracaocip.erro.processar.mensagem.recebida";

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private MensagemDDADao dao;

    @Dao(entityManager = "em", fabrica = ComumDaoFactory.class)
    private ComumDao comumDao;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private ArquivoCipDao arquivoCipDao;

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
     * @return the arquivoCipDao
     */
    public ArquivoCipDao getArquivoCipDao() {
        return arquivoCipDao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarRetornoDDAServico#processarMensagemRecebida(java.lang.String)
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void processarMensagemRecebida(String xmlRecebido) throws BancoobException {
        processarMensagemRecebida(xmlRecebido, Boolean.FALSE);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarRetornoDDAServico#processarMensagemRecebidaArquivo(java.lang.String,
     *      java.lang.Long)
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void processarMensagemRecebidaArquivo(String xmlRecebido, Long idLogDetalhe) throws BancoobException {
        IntegracaoCipFabricaDelegates.getInstance().getProcessarRetornoDDADelegate().processarMensagemRecebida(xmlRecebido);
        arquivoCipDao.atualizarStatusDetalheArquivoRecebido(idLogDetalhe);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarRetornoDDAServico#processarMensagemRecebida(java.lang.String, java.lang.Long)
     */

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void processarMensagemRecebida(List<String> listaDetalhesArquivos) throws BancoobException {
        ProcessarRetornoDDADelegate processarRetornoDelegate = IntegracaoCipFabricaDelegates.getInstance().getProcessarRetornoDDADelegate();
        for (String reg : listaDetalhesArquivos) {
            String xmlRegistro = reg.split(SEPARADOR_DE_DADOS)[1];
            processarRetornoDelegate.processarMensagemRecebidaArquivo(xmlRegistro, Long.valueOf(reg.split(SEPARADOR_DE_DADOS)[0]));
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarRetornoDDAServico#processarMensagemRecebidaContingencia(br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA)
     */
    public void processarMensagemRecebidaContingencia(MensagemDDA mensagemDDA) throws BancoobException {
        processarMensagemRecebida(mensagemDDA.getXmlMensagem(), Boolean.TRUE);
        dao.excluir(mensagemDDA.getId());
    }

    /**
     * Método responsável por
     * 
     * @param xmlRecebido
     * @param eContingencia
     * @throws BancoobException void
     * 
     */
    private void processarMensagemRecebida(String xmlRecebido, boolean eContingencia) throws BancoobException {
        try {
            logarXmlRecebido(xmlRecebido);
            if (xmlRecebido.toLowerCase().contains(Constantes.TAGRESPOSTAENVIO)) {
                processarMensagemProtocolo(xmlRecebido);
            } else if (xmlRecebido.toLowerCase().contains(Constantes.TAGRECEBIMENTO)) {
                processarMensagemRetorno(xmlRecebido);
            } else if (xmlRecebido.contains(Constantes.TAGRECEBIMENTOARQ)) {
                processarConteudoArquivoRetorno(xmlRecebido);
            } else {
                throw new ComumException("integracaocip.erro.xml.recebido.invalido");
            }
        } catch (Exception e) { // NOSONAR
            getLogger().erro(e, ERRO_PROCESSAR_MENSAGEM_RECEBIDA);
            getSessionContext().setRollbackOnly();

            if (eContingencia) {
                throw new ComumNegocioException("integracaocip.erro.processar.mensagem.contingencia", e);
            }

            IntegracaoCipFabricaDelegates.getInstance().getMensagemDDAInclusaoDelegate().incluirMensagemDDA(xmlRecebido);
        }
    }

    /**
     * Método responsável por
     * 
     * @param xmlRecebido
     * @throws BancoobException void
     * 
     */
    private void processarMensagemProtocolo(String xmlRecebido) throws BancoobException {
        MQRespostaEnvio respostaEnvio = (MQRespostaEnvio) LeitorXmlUtil.desempacotarArquivo(MQRespostaEnvio.class, xmlRecebido);
        MensagemDDA mensagem = dao.obter(respostaEnvio.getHeader().getNumSeqOr());
        if (ObjectUtil.isEmpty(mensagem.getNumOperacao())) {
            if (!ObjectUtil.isEmpty(respostaEnvio.getRespostaMsg().getErro())) {
                mensagem.setDataHoraProtocolo(new DateTimeDB());
                mensagem.setDescErroProtocolo(getDescErroProtocoloTruncado(respostaEnvio.getRespostaMsg().getErro()));
            } else {
                mensagem.setDataHoraProtocolo(new DateTimeDB());
                mensagem.setNumOperacao(respostaEnvio.getRespostaMsg().getNuOp());
                mensagem.setDescErroProtocolo(null);
            }
        }
    }

    /**
     * @param descErroProtocolo
     * @return String
     * 
     */
    private String getDescErroProtocoloTruncado(String descErroProtocolo) {
        return descErroProtocolo.length() > 480 ? descErroProtocolo.substring(0, 480) : descErroProtocolo;
    }

    /**
     * Método responsável por processar os xmls de registro de mensagens recebidas
     * 
     * @param xmlRecebido
     * @throws BancoobException
     * @throws IllegalAccessException void
     * 
     */
    private void processarMensagemRetorno(String xmlRecebido) throws BancoobException, IllegalAccessException {
        MQRecebimento recebimento = (MQRecebimento) LeitorXmlUtil.desempacotarArquivo(MQRecebimento.class, xmlRecebido);
        String numOpMsg = recebimento.getHeader().getNuOp();

        ConteudoMsgRecebida conteudoMsg = getConteudoMsgRecebida(recebimento.getMsgSPB());
        if (!ObjectUtil.isNull(conteudoMsg)) {
            if (conteudoMsg.getCodMsg().equals(TipoMensagemDDA.GEN0004)) {
                gravarRetornoMensagemErroGEN004DDA(recebimento.getHeader().getNuOpOr(), xmlRecebido, conteudoMsg);
            } else {
                gravarRetornoMensagemDDA(numOpMsg, xmlRecebido, conteudoMsg);
            }
        } else {
            // É uma mensagem de rejeição ou uma mensagem não mapeada.
            gravarRetornoMensagemErroDDA(numOpMsg, xmlRecebido);
        }
    }

    /**
     * Método responsável por processar os xmls de registro dos arquivos recebidos
     * 
     * @param xmlRecebido void
     * @throws BancoobException
     * @throws IllegalAccessException
     * 
     */
    private void processarConteudoArquivoRetorno(String xmlRecebido) throws BancoobException, IllegalAccessException {
        ArqRecebimento recebimento = (ArqRecebimento) LeitorXmlUtil.desempacotarArquivo(ArqRecebimento.class, xmlRecebido);
        String numOpMsg = Constantes.TAGRECEBIMENTOARQ;
        // arqrecebimento

        ConteudoMsgRecebida conteudoMsg = getConteudoMsgRecebida(recebimento);
        if (!ObjectUtil.isNull(conteudoMsg)) {
            gravarRetornoMensagemDDA(numOpMsg, xmlRecebido, conteudoMsg);
        } else {
            // É uma mensagem de rejeição ou uma mensagem não mapeada.
            gravarRetornoMensagemErroDDA(numOpMsg, xmlRecebido);
        }
    }

    /**
     * Método responsável por
     * 
     * @param recebimento
     * @return
     * @throws IllegalAccessException ConteudoMsgRecebida
     * 
     */
    private ConteudoMsgRecebida getConteudoMsgRecebida(Object recebimento) throws IllegalAccessException {
        for (Field fieldObjetoRecebido : recebimento.getClass().getDeclaredFields()) {
            fieldObjetoRecebido.setAccessible(Boolean.TRUE);
            if (!ObjectUtil.isNull(fieldObjetoRecebido.get(recebimento))) {

                if (fieldObjetoRecebido.getName() == Constantes.CAMPO_NOME_ARQUIVO) {
                    continue;
                }

                return (ConteudoMsgRecebida) fieldObjetoRecebido.get(recebimento);
            }
        }
        return null;
    }

    /**
     * Método responsável por gravar a mensagemDDA e fazer alguma operaçao de acordo com o seu retorno. Caso a mensagem não possua uma implementação apenas
     * grava seu conteudo em MensagemDDA.
     * 
     * @param xmlRecebido
     * @param numOpMsg
     * @param codTipoMsg
     * @param conteudoMsg
     * @throws BancoobException
     * 
     */
    private void gravarRetornoMensagemDDA(String numOpMsg, String xmlRecebido, ConteudoMsgRecebida conteudoMsg) throws BancoobException {
        if (!isArquivoTipoDis(conteudoMsg.getCodMsg())) {
            gravarMensagemRetornoDDA(numOpMsg, xmlRecebido, conteudoMsg.getCodMsg(),
                    ObjectUtil.isEmpty(conteudoMsg.getIdMensagemOrigem()) ? null : dao.obter(conteudoMsg.getIdMensagemOrigem()), conteudoMsg.getNumCtrlDDA(), null);
        }

        if (!isMsgR2OriginadaPeloSicoob(conteudoMsg.getCodMsg(), numOpMsg)) {
            try {
                getDelegate(conteudoMsg.getCodMsg()).processarRetornoMensagemDDA(conteudoMsg);
            } catch (MensagemDesconhecidaException e) {
                getLogger().alerta(e.getMessage());
            }
        }
    }

    /**
     * @param prCodMensagem
     * @return boolean
     * 
     */
    private boolean isArquivoTipoDis(String prCodMensagem) {
        TipoInstanciaProcessamentoEnum instancia = TipoInstanciaProcessamentoEnum.getTipoInstancia(prCodMensagem);

        if (ObjectUtil.isNull(instancia)) {
            return Boolean.FALSE;
        }

        String[] tipoArquivo = instancia.getTiposArquivo();
        for (String tipo : tipoArquivo) {
            if ("DIS".equals(tipo)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * Método responsável por verificar se a mensagem R2 recebida é de uma alteração feita pelo própio Sicoob. Nestes casos não é necessário processar a R2,
     * apenas a R1.
     * 
     * @return Boolean
     * 
     */
    private Boolean isMsgR2OriginadaPeloSicoob(String codMsg, String numOpMsg) {
        if (codMsg.contains("R2")) {
            String ispbOrigemMsg = numOpMsg.substring(0, 8);
            if (ispbOrigemMsg.equals(Constantes.ISPB_BANCOOB)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * Método responsável por processar os retornos do tipo ERRO recebidos da CIP. Consiste em gravar os erros e o xmlRecebido.
     * 
     * @param numOpMsg
     * @param xmlRecebido
     * @param codTipoMensagem
     * @param conteudo
     * @throws BancoobException void
     * 
     */
    private void gravarRetornoMensagemErroDDA(String numOpMsg, String xmlRecebido) throws BancoobException {
        XMLCipDto xmlCipDto = LeitorXmlUtil.obterDadosXMLErroCip(xmlRecebido);
        List<ErroMensagemRetornoCip> listaErroMensagemRetornoCip = new ArrayList<ErroMensagemRetornoCip>();
        for (String codTipoErro : xmlCipDto.getListaCodErro()) {
            listaErroMensagemRetornoCip.add(getErroMensagemDDA(codTipoErro));
        }
        gravarMensagemRetornoDDA(numOpMsg, xmlRecebido, xmlCipDto.getCodTipoMensagem(),
                ObjectUtil.isEmpty(xmlCipDto.getIdMensagem()) ? null : dao.obter(xmlCipDto.getIdMensagem()), null, listaErroMensagemRetornoCip);
    }

    /**
     * Método responsável por processar o retorno do tipo GEN004. Faz a relação da mensagemOrigem e grava o ErroMensagemRetornoCIP.
     * 
     * @param numOpMsg
     * @param xmlRecebido
     * @param codTipoMensagem
     * @param conteudo
     * @throws BancoobException void
     * 
     */
    private void gravarRetornoMensagemErroGEN004DDA(String numOpOrMsg, String xmlRecebido, ConteudoMsgRecebida conteudo) throws BancoobException {
        gravarMensagemRetornoDDA(numOpOrMsg, xmlRecebido, conteudo.getCodMsg(), dao.obterMensagemPorNumOperacao(numOpOrMsg), conteudo.getNumCtrlDDA(),
                getListaErroMensagemRetornoCIP(conteudo));
    }

    /**
     * Método responsável por criar e persistir uma mensagem.
     * 
     * @param numOpMensagem
     * @param xmlRecebido
     * @param codTipoMensagem
     * @param mensagemDDAOrigem
     * @param numControleDDA
     * @param listaMensagemRetornoCIP
     * @return
     * @throws BancoobException MensagemDDA
     * 
     */
    private MensagemDDA gravarMensagemRetornoDDA(String numOpMensagem, String xmlRecebido, String codTipoMensagem, MensagemDDA mensagemDDAOrigem, String numControleDDA,
            List<ErroMensagemRetornoCip> listaMensagemRetornoCIP) throws BancoobException {
        MensagemDDA mensagem = new MensagemDDA();
        mensagem.setBolOrigemSicoob(Boolean.FALSE);
        mensagem.setDataHoraCadastro(new DateTimeDB());
        mensagem.setDataHoraMensagem(comumDao.obterDataHoraDataBase());
        mensagem.setDataMovimento(new DateTimeDB());
        mensagem.setDataHoraProtocolo(null);
        mensagem.setDescErroProtocolo(null);

        if (xmlRecebido.contains(Constantes.CAMPO_NOME_ARQUIVO)) {
            mensagem.setNumOperacao(extrairNomeArquivo(xmlRecebido));
        } else {
            mensagem.setNumOperacao(numOpMensagem);
        }

        mensagem.setQtdTotalRegistros(1);
        mensagem.setXmlMensagem(xmlRecebido);
        mensagem.setTipoMensagemDDA(new TipoMensagemDDA(codTipoMensagem));
        mensagem.setNumPrioridadeEnvio(Constantes.INTEGER_CEM);
        mensagem.setNumControleDDA(numControleDDA);

        mensagem.setIdUsuarioSolicitante(getUsuarioLogado());
        mensagem.setIdInstituicaoSolicitante(getIdInstituicaoLogado());
        mensagem.setIdCanal(CanalEnum.RETAGUARDA.getId());

        if (!ObjectUtil.isNull(mensagemDDAOrigem)) {
            mensagem.setMensagemOrigem(mensagemDDAOrigem);
        }

        if (!ObjectUtil.isEmpty(listaMensagemRetornoCIP)) {
            for (ErroMensagemRetornoCip erroMensagemRetornoCip : listaMensagemRetornoCIP) {
                erroMensagemRetornoCip.setMensagemDDA(mensagem);
            }
            mensagem.setListaErroMensagemRetornoCip(listaMensagemRetornoCIP);
        }

        return dao.incluir(mensagem);
    }

    /**
     * Método responsável por
     * 
     * @param xmlRecebido
     * @return String
     * 
     */
    private String extrairNomeArquivo(String xmlRecebido) {
        return xmlRecebido.substring(xmlRecebido.indexOf("<" + Constantes.CAMPO_NOME_ARQUIVO + ">") + Constantes.CAMPO_NOME_ARQUIVO.length() + 2,
                xmlRecebido.indexOf("</" + Constantes.CAMPO_NOME_ARQUIVO + ">"));
    }

    /**
     * Método responsável por
     * 
     * @param xmlRecebido void
     * 
     */
    private void logarXmlRecebido(String xmlRecebido) {
        getLogger().debug("###### XmlRecebido ######");
        getLogger().debug(xmlRecebido);
        getLogger().debug("###### XmlRecebido ######");
    }

    /**
     * Método responsável por criar o delegate de acordo com o tipo de mensagem
     * 
     * @param codTipoMensagem
     * @return
     * @throws MensagemDesconhecidaException
     * @throws ComumException IntegracaoCipMensagemDelegate<IntegracaoCipServico>
     * 
     */
    private IntegracaoCipMensagemDelegate<IntegracaoCipServico> getDelegate(String codTipoMensagem) throws MensagemDesconhecidaException {
        return IntegracaoCipFabricaDelegates.getInstance().getMensagemDelegate(codTipoMensagem);
    }

    /**
     * Método responsável por
     * 
     * @param conteudo
     * @return List<ErroMensagemRetornoCip>
     * 
     */
    private List<ErroMensagemRetornoCip> getListaErroMensagemRetornoCIP(ConteudoMsgRecebida conteudo) {
        GEN0004ComplexType gen = (GEN0004ComplexType) conteudo;
        List<ErroMensagemRetornoCip> listaErroMensagemRetornoCip = new ArrayList<ErroMensagemRetornoCip>();
        listaErroMensagemRetornoCip.add(getErroMensagemDDA(gen.getErroGEN()));
        return listaErroMensagemRetornoCip;
    }

    /**
     * Método responsável por
     * 
     * @param codTipoErro
     * @return ErroMensagemRetornoCip
     * 
     */
    private ErroMensagemRetornoCip getErroMensagemDDA(String codTipoErro) {
        ErroMensagemRetornoCip erroRetorno = new ErroMensagemRetornoCip();
        erroRetorno.setTipoErroCip(new TipoErroCip(codTipoErro));
        erroRetorno.setDataHoraAtualizacao(new DateTimeDB());
        return erroRetorno;
    }
}
