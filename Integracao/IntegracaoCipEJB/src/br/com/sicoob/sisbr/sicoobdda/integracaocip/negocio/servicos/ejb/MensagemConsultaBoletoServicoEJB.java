package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoRetornoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAConsultaBoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemConsultaBoletoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.EscritorXMLUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoIncluirBoletoDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA106.GrupoADDA106TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor.ConversorBoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0106.DDA0106ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0106.DDA0106R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0106.GrupoDDA0106R1TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemConsultaBoletoServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ MensagemConsultaBoletoServicoLocal.class })
public class MensagemConsultaBoletoServicoEJB extends IntegracaoCipServicoEJB implements MensagemConsultaBoletoServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private BoletoCipDao boletoDao;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private ArquivoIncluirBoletoDao dao;

    private MensagemDDADelegate mensagemDDADelegate;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemConsultaBoletoServico#processarRetornoMensagemDDA(long, long, long)
     */
    public void processarRetornoMensagemDDA(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetFinal) {
        dao.inluirADDA106(idLogArquivoRecebido, idLogDetArqInicial, idLogDetFinal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarMensagem(java.lang.Long)
     */
    public String processarMensagem(Long idMensagem) throws ComumException {
        getLogger().debug("########### Inicio processarMensagem(Long idMensagem) para DDA0106 - Participante consulta base de boletos de Pagamento");
        MensagemDDAConsultaBoleto mensagem = boletoDao.obterMensagemDDAConsultaBoleto(idMensagem);

        DDA0106ComplexType dda0106 = getDDA0106ComplexType(mensagem);
        String xmlEnvio = EscritorXMLUtil.obterXmlEnvio(dda0106, mensagem.getId());

        getLogger().debug("******* XmlEnvio gerado >>> " + xmlEnvio);

        getLogger().debug("########### Fim processarMensagem(Long idMensagem) para DDA0106 - Participante consulta base de boletos de Pagamento");
        return xmlEnvio;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)
     */
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida retornoDDA) throws ComumException {
        getLogger().debug("########### Inicio processarRetornoMensagemDDA(Object retornoDDA) - " + retornoDDA.getCodMsg());
        if (retornoDDA.getCodMsg().equals(TipoMensagemDDA.ADDA106)) {
            processarGrupoBoleto((GrupoADDA106TitComplexType) retornoDDA);
        } else {
            processarListaGrupoBoleto(((DDA0106R1ComplexType) retornoDDA).getGrupoDDA0106R1Tit());
        }
        getLogger().debug("########### Fim processarRetornoMensagemDDA(Object retornoDDA) - " + retornoDDA.getCodMsg());
    }

    /**
     * Método responsável por
     * 
     * @param listaGrupoBoleto void
     * @throws ComumException
     * 
     */
    private void processarListaGrupoBoleto(List<GrupoDDA0106R1TitComplexType> listaGrupoBoleto) throws ComumException {
        for (GrupoDDA0106R1TitComplexType grupoBoleto : listaGrupoBoleto) {
            processarGrupoBoleto(grupoBoleto);
        }
    }

    /**
     * Método responsável por
     * 
     * Se o sequencial de atualização recebido na mensagem for menor que o do registro na base, gerar uma mensagem DDA0106 e NÃO processar a mensagem recebida.
     * 
     * Se o sequencial da mensagem recebida for igual, DEVE processar.
     * 
     * @param grupoBoleto
     * @throws ComumException void
     * 
     */
    private void processarGrupoBoleto(GrupoADDA106TitComplexType grupoBoleto) throws ComumException {
        BoletoDDA boleto = boletoDao.obterBoletoDDALock(grupoBoleto.getNumIdentcTit().longValue());
        if (ConversorBoletoDDA.isNumSeqRecebidoMenorQueAtual(boleto, grupoBoleto.getNumSeqAtlzCadTit())) {
            incluirMensagemDDA0106(boleto);
        } else {
            getLogger().info("Numero sequencial MAIOR ou IGUAL ao numero sequencial da base");
            processarMensagemADDA106(grupoBoleto, boleto);
        }
    }

    /**
     * Método responsável por
     * 
     * Se o sequencial de atualização recebido na mensagem for menor que o do registro na base, gerar uma mensagem DDA0106 e NÃO processar a mensagem recebida.
     * 
     * Se o sequencial da mensagem recebida for igual, DEVE processar.
     * 
     * @param grupoBoleto
     * @throws ComumException void
     * 
     */
    private void processarGrupoBoleto(GrupoDDA0106R1TitComplexType grupoBoleto) throws ComumException {
        BoletoDDA boleto = boletoDao.obterBoletoDDALock(grupoBoleto.getNumIdentcTit().longValue());
        if (!isBarraReutilizada(grupoBoleto, boleto)) {
            if (ConversorBoletoDDA.isNumSeqRecebidoMenorQueAtual(boleto, grupoBoleto.getNumSeqAtlzCadTit())) {
                incluirMensagemDDA0106(boleto);
            } else {
                getLogger().info("Numero sequencial MAIOR ou IGUAL ao numero sequencial da base");
                processarMensagemDDA0106(grupoBoleto, boleto);
            }
        } else {
            // Se a barra foi reutilizada, verifica se o xml recebido é mais atual (dataHoraSituacao) que o registro gravado no banco.
            if (DateUtil.maiorQue(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoBoleto.getDtHrSit()), boleto.getDataHoraSituacaoBoleto())) {
                processarMensagemDDA0106(grupoBoleto, boleto);
            }
        }
    }

    /**
     * Método responsável por verificar se o código de barras foi reutilizado para um novo boleto. Ou seja, o primeiro boleto foi baixado e após o período
     * definido pela CIP para reutilização do código de barras, um novo boleto foi inserido com a mesma barra.
     * 
     * @param grupoBoleto
     * @param boleto
     * @return Boolean
     * 
     */
    private Boolean isBarraReutilizada(GrupoDDA0106R1TitComplexType grupoBoleto, BoletoDDA boleto) {
        // Codigo de barras existe na base com numIdentificador diferente.
        return !ObjectUtil.isNull(boleto) && !grupoBoleto.getNumIdent().equals(boleto.getNumIdentificadorBoletoCip());
    }

    /**
     * @param grupoBoleto
     * @param boleto
     * @throws ComumException void
     * 
     */
    private void processarMensagemDDA0106(GrupoDDA0106R1TitComplexType grupoBoleto, BoletoDDA boleto) throws ComumException {
        if (ObjectUtil.isNull(boleto) || ObjectUtil.isEmpty(boleto.getId())) {
            incluirBoletoDDA(grupoBoleto);
        } else {
            alterarBoletoDDA(boleto, grupoBoleto);
        }
    }

    /**
     * @param grupoBoleto
     * @param boleto
     * @throws ComumException void
     * 
     */
    private void processarMensagemADDA106(GrupoADDA106TitComplexType grupoBoleto, BoletoDDA boleto) throws ComumException {
        if (ObjectUtil.isNull(boleto) || ObjectUtil.isEmpty(boleto.getId())) {
            incluirBoletoDDA(grupoBoleto);
        } else {
            alterarBoletoDDA(boleto, grupoBoleto);
        }
    }

    /**
     * Método responsável por
     * 
     * @param grupoBoleto
     * @throws ComumException void
     * 
     */
    private void incluirBoletoDDA(ConteudoMsgRecebida grupoBoleto) throws ComumException {
        getLogger().info("Inicio da conversão do Objeto do Boleto");
        BoletoDDA boletoDDA = ConversorBoletoDDA.converter(grupoBoleto);
        getEm().persist(boletoDDA);
        getLogger().info("Objeto Convertido com Sucesso");
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param grupoBoleto
     * @throws ComumException void
     * 
     */
    private void alterarBoletoDDA(BoletoDDA boletoDDA, ConteudoMsgRecebida grupoBoleto) throws ComumException {
        getLogger().info("Processando alteração do Boleto - " + boletoDDA.getId());
        removerRelacionamentoBoletoDDA(boletoDDA, boletoDao);
        BoletoDDA boletoDDAAlterado = ConversorBoletoDDA.merge(grupoBoleto, boletoDDA);
        getEm().merge(boletoDDAAlterado);
        getEm().flush();
        getLogger().info("Objeto Convertido com Sucesso");

    }

    /**
     * Método responsável por criar o objeto de envio para a CIP.
     * 
     * @param mensagem
     * @return
     * @throws ComumException DDA0106ComplexType
     * 
     */
    private DDA0106ComplexType getDDA0106ComplexType(MensagemDDAConsultaBoleto mensagem) throws ComumException {
        DDA0106ComplexType dda = new DDA0106ComplexType();
        dda.setCodMsg(TipoMensagemDDA.DDA0106);
        dda.setNumCtrlPart(mensagem.getId().toString());
        dda.setISPBPartPrincipal(Constantes.ISPB_BANCOOB);
        dda.setISPBPartAdmtd(Constantes.ISPB_BANCOOB);

        if (!ObjectUtil.isEmpty(mensagem.getNumCodigoBarra())) {
            dda.setNumCodBarras(mensagem.getNumCodigoBarra());
            dda.setSitTit("0" + mensagem.getCodSituacaoBoleto().toString());
        } else {
            dda.setNumIdentcTitInial(BigInteger.valueOf(mensagem.getNumIdentBoletoInicial()));
            dda.setNumIdentcTitFinl(BigInteger.valueOf(mensagem.getNumIdentBoletoFinal()));
        }
        dda.setTpTitConsd(mensagem.getCodTipoBoletoConsultado());

        dda.setTpRet(TipoRetornoEnum.M.getCodDominio());

        dda.setDtMovto(DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getMensagemDDA().getDataMovimento()));

        return dda;
    }

    /**
     * @return o atributo mensagemDDADelegate
     */
    public MensagemDDADelegate getMensagemDDADelegate() {
        if (ObjectUtil.isNull(mensagemDDADelegate)) {
            mensagemDDADelegate = IntegracaoCipFabricaDelegates.getInstance().getMensagemDDADelegate();
        }
        return mensagemDDADelegate;
    }

}
