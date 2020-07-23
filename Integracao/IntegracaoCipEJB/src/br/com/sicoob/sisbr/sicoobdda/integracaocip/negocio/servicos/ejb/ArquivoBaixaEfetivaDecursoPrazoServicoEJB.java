package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaEfet;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaEfetiva;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ArquivoBaixaEfetivaDecursoPrazoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemBaixaEfetivaDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA120.ADDA120ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA120.GrupoADDA120TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA120.SISARQComplexType;

/**
 * ArquivoBaixaEfetivaDecursoPrazoServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ ArquivoBaixaEfetivaDecursoPrazoServicoLocal.class })
public class ArquivoBaixaEfetivaDecursoPrazoServicoEJB extends IntegracaoCipServicoEJB implements ArquivoBaixaEfetivaDecursoPrazoServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private BoletoCipDao dao;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private MensagemBaixaEfetivaDao mensagemDao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoArquivo#obterSISARQ(java.lang.Long)
     */
    public Object obterSISARQ(Long idLogEnvioArquivoDDA) throws ComumException {
        return new SISARQComplexType(getADDA120(mensagemDao.listarMensagemDDABaixaEfetiva(idLogEnvioArquivoDDA)));
    }

    /**
     * Método responsável por montar o ADDA118ComplexType
     * 
     * @param listaMensagemDDABaixaEfetiva
     * @return
     * @throws ComumException
     * @throws ComumNegocioException ADDA101ComplexType
     * 
     */
    private ADDA120ComplexType getADDA120(List<MensagemDDABaixaEfetiva> listaMensagemDDABaixaEfetiva) throws ComumException {
        getLogger().debug("###### Qtde de Arquivos Enviados -> " + listaMensagemDDABaixaEfetiva.size());

        ADDA120ComplexType adda118 = new ADDA120ComplexType();

        for (MensagemDDABaixaEfetiva mensagemDDABaixaEfetiva : listaMensagemDDABaixaEfetiva) {
            adda118.getGrupoADDA120Tit().add(getGrupoADDA120(mensagemDDABaixaEfetiva));
        }

        return adda118;
    }

    /**
     * Método responsável por popular o getGrupoADDA120
     * 
     * @param mensagemDDABaixaEfetiva
     * @return
     * @throws ComumException GrupoADDA120TitComplexType
     * 
     */
    private GrupoADDA120TitComplexType getGrupoADDA120(MensagemDDABaixaEfetiva mensagemDDABaixaEfetiva) throws ComumException {
        GrupoADDA120TitComplexType boletoBaixaEfetiva = new GrupoADDA120TitComplexType();
        boletoBaixaEfetiva.setISPBPartAdmtd(Constantes.ISPB_BANCOOB);
        boletoBaixaEfetiva.setISPBPartPrincipal(Constantes.ISPB_BANCOOB);

        BoletoDDABaixaEfet boletoBaixaEfetivaBanco = dao.obterBoletoDDABaixaEfetiva(mensagemDDABaixaEfetiva.getNumCodigoBarra());
        boletoBaixaEfetiva.setNumIdentcTit(BigInteger.valueOf(boletoBaixaEfetivaBanco.getBoletoDDA().getNumIdentificadorBoletoCip()));

        boletoBaixaEfetiva.setNumRefAtlCadTit(BigInteger.valueOf(boletoBaixaEfetivaBanco.getBoletoDDA().getNumRefAtualCadBoleto()));
        boletoBaixaEfetiva.setDtHrSitTit(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(boletoBaixaEfetivaBanco.getBoletoDDA().getDataHoraSituacaoBoleto()));
        boletoBaixaEfetiva.setNumIdentcBaixaEft(BigInteger.valueOf(boletoBaixaEfetivaBanco.getNumIdentificadorBaixaEfet()));
        boletoBaixaEfetiva.setDtHrProcBaixaEft(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(boletoBaixaEfetivaBanco.getDataHoraProcessamentoBaixaEfet()));
        // Nao e Obrigatorio
        // boletoBaixaEfetiva.setQtdPgtoParclRegtd(boletoBaixaEfetivaBanco.get);
        boletoBaixaEfetiva.setVlrSldTotAtlPgtoTit(boletoBaixaEfetivaBanco.getValorBaixaEfet());

        boletoBaixaEfetiva.setNumUltIdentcBaixaOperac(BigInteger.valueOf(boletoBaixaEfetivaBanco.getNumIdentificadorBaixaOperacional()));
        // Campos nao e obrigatorio
        // boletoBaixaEfetiva.setNumUltRefAtlBaixaOperac(value);
        // boletoBaixaEfetiva.setNumUltSeqAtlzBaixaOperac(value);

        boletoBaixaEfetiva.setNumUltIdentcBaixaEft(BigInteger.valueOf(boletoBaixaEfetivaBanco.getNumIdentificadorBaixaEfet()));
        boletoBaixaEfetiva.setNumUltRefAtlBaixaEft(BigInteger.valueOf(boletoBaixaEfetivaBanco.getNumRefAtualBaixaEfet()));
        boletoBaixaEfetiva.setNumUltSeqAtlzBaixaEft(BigInteger.valueOf(boletoBaixaEfetivaBanco.getNumSeqAtualBaixaEfet()));

        // Obrigatorio
        boletoBaixaEfetiva.setSitTitPgto(boletoBaixaEfetivaBanco.getBoletoDDA().getCodSituacaoBoletoPagamento());

        return boletoBaixaEfetiva;
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
}
