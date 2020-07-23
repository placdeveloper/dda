package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaEfetiva;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoBaixaEfetiva;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ArquivoBaixaEfetivaBoletoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemBaixaEfetivaDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA118.ADDA118ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA118.GrupoADDA118TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA118.SISARQComplexType;

/**
 * ArquivoBaixaEfetivaBoletoServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ ArquivoBaixaEfetivaBoletoServicoLocal.class })
public class ArquivoBaixaEfetivaBoletoServicoEJB extends IntegracaoCipServicoEJB implements ArquivoBaixaEfetivaBoletoServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private BoletoCipDao dao;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private MensagemBaixaEfetivaDao mensagemDao;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private MensagemDDADao mensagemDDADao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoArquivo#obterSISARQ(java.lang.Long)
     */
    public Object obterSISARQ(Long idLogEnvioArquivoDDA) throws ComumException {
        return new SISARQComplexType(getADDA118(mensagemDao.listarMensagemDDABaixaEfetiva(idLogEnvioArquivoDDA)));
    }

    /**
     * Método responsável por montar o ADDA118ComplexType
     * 
     * @param listaMensagemDDABaixaEfetiva
     * @return
     * @throws ComumException
     * 
     */
    private ADDA118ComplexType getADDA118(List<MensagemDDABaixaEfetiva> listaMensagemDDABaixaEfetiva) throws ComumException {
        getLogger().debug("###### Qtde de Arquivos Enviados -> " + listaMensagemDDABaixaEfetiva.size());

        ADDA118ComplexType adda118 = new ADDA118ComplexType();

        for (MensagemDDABaixaEfetiva mensagemDDABaixaEfetiva : listaMensagemDDABaixaEfetiva) {
            adda118.getGrupoADDA118Tit().add(getGrupoADDA118(mensagemDDABaixaEfetiva));
        }

        return adda118;
    }

    /**
     * Método responsável por popular o getGrupoADDA118
     * 
     * @param msgBaixaEfetiva
     * @return
     * @throws ComumException GrupoADDA118TitComplexType
     * 
     */
    private GrupoADDA118TitComplexType getGrupoADDA118(MensagemDDABaixaEfetiva msgBaixaEfetiva) throws ComumException {
        GrupoADDA118TitComplexType boletoBaixaEfetiva = new GrupoADDA118TitComplexType();
        boletoBaixaEfetiva.setNumCtrlReqPart(msgBaixaEfetiva.getId().toString());
        boletoBaixaEfetiva.setISPBPartDestinatarioPrincipal(Constantes.ISPB_BANCOOB);
        boletoBaixaEfetiva.setISPBPartDestinatarioAdmtd(Constantes.ISPB_BANCOOB);

        boletoBaixaEfetiva.setNumIdentcTit(BigInteger.valueOf(msgBaixaEfetiva.getNumIdentificadorBoletoCip()));
        boletoBaixaEfetiva.setNumRefAtlCadTit(ObjectUtil.isEmpty(msgBaixaEfetiva.getNumRefAtualCadBoleto()) ? null : msgBaixaEfetiva.getNumRefAtualCadBoleto());
        boletoBaixaEfetiva.setNumRefAtlBaixaEft(ObjectUtil.isEmpty(msgBaixaEfetiva.getNumRefAtualBaixaEfetiva()) ? null : msgBaixaEfetiva.getNumRefAtualBaixaEfetiva());

        if (ObjectUtil.isNull(msgBaixaEfetiva.getNumIdentificadorBaixaOper())) {
            if (msgBaixaEfetiva.getCodTipoBaixaEfetiva().toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_INTEGRAL_INTERBANCARIA)
                    || msgBaixaEfetiva.getCodTipoBaixaEfetiva().toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_INTEGRAL_INTRABANCARIA)) {
                setValoresBaixaEfetiva(boletoBaixaEfetiva, null, null, null, Integer.valueOf(TipoBaixaEfetiva.BAIXA_EFETIVA_SOLICITACAO_INSTITUICAO_DESTINATARIA));
            } else if (msgBaixaEfetiva.getCodTipoBaixaEfetiva().toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_INTEGRAL_SOLICITACAO_CEDENTE)
                    || msgBaixaEfetiva.getCodTipoBaixaEfetiva().toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_INTEGRAL_ENVIO_PROTESTO)
                    || msgBaixaEfetiva.getCodTipoBaixaEfetiva().toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_INTEGRAL_DECURSO_PRAZO)
                    || msgBaixaEfetiva.getCodTipoBaixaEfetiva().toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_SOLICITACAO_INSTITUICAO_DESTINATARIA)) {
                setValoresBaixaEfetiva(boletoBaixaEfetiva, null, null, null, msgBaixaEfetiva.getCodTipoBaixaEfetiva());
            } else {
                setValoresBaixaEfetiva(boletoBaixaEfetiva, msgBaixaEfetiva.getCodCanalPagamento(), msgBaixaEfetiva.getCodMeioPagamento(), msgBaixaEfetiva.getValorBaixa(),
                        msgBaixaEfetiva.getCodTipoBaixaEfetiva());
            }
        } else {
            boletoBaixaEfetiva.setNumIdentcBaixaOperac(ObjectUtil.isEmpty(msgBaixaEfetiva.getNumIdentificadorBaixaOper()) ? null : msgBaixaEfetiva.getNumIdentificadorBaixaOper());

            setValoresBaixaEfetiva(boletoBaixaEfetiva, msgBaixaEfetiva.getCodCanalPagamento(), msgBaixaEfetiva.getCodMeioPagamento(), msgBaixaEfetiva.getValorBaixa(),
                    msgBaixaEfetiva.getCodTipoBaixaEfetiva());

            setISPBPartRecebedorBaixaEfetiva(msgBaixaEfetiva, boletoBaixaEfetiva);
        }
        
        // Necessario essa validação pois se o legado enviar uma baixa efetiva por solicitacao do destinatario e o boleto em questao tiver baixa, alguns dados
        // nao podem ser enviados
        validaBaixaSolicitacaoDestinataria(msgBaixaEfetiva, boletoBaixaEfetiva);

        boletoBaixaEfetiva.setDtHrProcBaixaEft(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(
                getDataHoraCadastro(msgBaixaEfetiva.getMensagemDDA().getDataHoraCadastro(), msgBaixaEfetiva.getMensagemDDA().getDataMovimento()).getTime()));
        boletoBaixaEfetiva.setDtProcBaixaEft(DataCipUtil.dateTimeParaXMLGregorianCalendar(msgBaixaEfetiva.getMensagemDDA().getDataMovimento()));

        boletoBaixaEfetiva.setNumCodBarrasBaixaEft(msgBaixaEfetiva.getNumCodigoBarra());

        return boletoBaixaEfetiva;
    }

    /**
     * Método responsável por 
     * @param msgBaixaEfetiva
     * @param boletoBaixaEfetiva void
     */
    private void validaBaixaSolicitacaoDestinataria(MensagemDDABaixaEfetiva msgBaixaEfetiva, GrupoADDA118TitComplexType boletoBaixaEfetiva) {
        if(msgBaixaEfetiva.getCodTipoBaixaEfetiva().toString() .equals(TipoBaixaEfetiva.BAIXA_EFETIVA_SOLICITACAO_INSTITUICAO_DESTINATARIA)) {
            boletoBaixaEfetiva.setCanPgto(null);
            boletoBaixaEfetiva.setMeioPgto(null);
            boletoBaixaEfetiva.setVlrBaixaEftTit(null);
        }
    }

    /**
     * Método responsável por 
     * @param msgBaixaEfetiva
     * @param boletoBaixaEfetiva void
     */
    private void setISPBPartRecebedorBaixaEfetiva(MensagemDDABaixaEfetiva msgBaixaEfetiva, GrupoADDA118TitComplexType boletoBaixaEfetiva) {
        if (msgBaixaEfetiva.getCodTipoBaixaEfetiva().toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_PARCIAL_INTERBANCARIA)
                || msgBaixaEfetiva.getCodTipoBaixaEfetiva().toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_PARCIAL_INTRABANCARIA)
                || msgBaixaEfetiva.getCodTipoBaixaEfetiva().toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_INTEGRAL_INTERBANCARIA)
                || msgBaixaEfetiva.getCodTipoBaixaEfetiva().toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_INTEGRAL_INTRABANCARIA)) {
            boletoBaixaEfetiva
                    .setISPBPartRecbdrBaixaEft(!ObjectUtil.isEmpty(msgBaixaEfetiva.getCodISPBPartRecbdrBaixaOper()) ? msgBaixaEfetiva.getCodISPBPartRecbdrBaixaOper() : null);
            boletoBaixaEfetiva.setCodPartRecbdrBaixaEft(!ObjectUtil.isEmpty(msgBaixaEfetiva.getCodPartRecbdrBaixaOper()) ? msgBaixaEfetiva.getCodPartRecbdrBaixaOper() : null);
        }
    }

    /**
     * Necessario para obter a hora que foi cadastrado na mensagemDDA junto com a data de movimento
     * 
     * @param dtHoraCadastro
     * @param dataMovimento
     * @return Calendar
     */
    private Calendar getDataHoraCadastro(DateTimeDB dtHoraCadastro, DateTimeDB dataMovimento) {

        final Calendar dataHoraCadastro = Calendar.getInstance();
        dataHoraCadastro.setTime(dtHoraCadastro);
        int hour = dataHoraCadastro.get(Calendar.HOUR_OF_DAY);
        int minute = dataHoraCadastro.get(Calendar.MINUTE);
        int second = dataHoraCadastro.get(Calendar.SECOND);

        dataHoraCadastro.setTime(dataMovimento);
        dataHoraCadastro.set(Calendar.HOUR_OF_DAY, hour);
        dataHoraCadastro.set(Calendar.MINUTE, minute);
        dataHoraCadastro.set(Calendar.SECOND, second);

        return dataHoraCadastro;
    }

    /**
     * Método responsável por setar os valores do canal de pagamento, meio de pagamento, valor da Baixa e Tipo baixa Efetiva
     * 
     * @param boletoBaixaEfetiva
     * @param codCanalPagamento
     * @param codMeioPagamento
     * @param valorBaixa
     * @param codTipoBaixaEfetiva void
     * 
     */
    private void setValoresBaixaEfetiva(GrupoADDA118TitComplexType boletoBaixaEfetiva, Integer codCanalPagamento, Integer codMeioPagamento, BigDecimal valorBaixa, Integer codTipoBaixaEfetiva) {
        boletoBaixaEfetiva.setCanPgto(ObjectUtil.isEmpty(codCanalPagamento) ? null : BigInteger.valueOf(codCanalPagamento));
        boletoBaixaEfetiva.setMeioPgto(ObjectUtil.isEmpty(codMeioPagamento) ? null : BigInteger.valueOf(codMeioPagamento));
        boletoBaixaEfetiva.setVlrBaixaEftTit(ObjectUtil.isEmpty(valorBaixa) ? null : valorBaixa);
        boletoBaixaEfetiva.setTpBaixaEft(codTipoBaixaEfetiva.toString());
    }

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
     * @return the integracaoCipDao
     */
    public BoletoCipDao getDao() {
        return dao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoBaixaEfetivaBoletoServico#processarArquivoRetornoBaixaEfetivaDDA(long, long, long)
     */
    public void processarArquivoRetornoBaixaEfetivaDDA(long idLogRecebArq, long idLogDetRecebimentoInicial, long idLogDetRecebimentoFinal) {
        getDao().processarArquivoRetornoBaixaEfetivaDDA(idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal);
    }
    
    /**
     * @return o atributo mensagemDDADao
     */
    public MensagemDDADao getMensagemDDADao() {
        return mensagemDDADao;
    }
}
