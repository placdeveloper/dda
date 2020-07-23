package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemBaixaOperacionalBoletoServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates.BoletoDDALoader;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemBaixaOperacionalDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA108.GrupoADDA108RR2TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0108.DDA0108R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0108.DDA0108R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0115.DDA0115R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemBaixaOperacionalBoletoServicoTest é responsável por
 * 
 * @author Francisco.Marcio
 */
@RunWith(MockitoJUnitRunner.class)
public class MensagemBaixaOperacionalBoletoServicoTest extends Mockito {

    /**
     * 
     */
    private static final String _VALOR_PADRAO = "1";

    @InjectMocks
    private MensagemBaixaOperacionalBoletoServicoEJB ejb;

    @Mock
    private EntityManager em;

    @Mock
    private BoletoCipDao boletoCipDao;

    @Mock
    private MensagemBaixaOperacionalDao mensagemDao;

    /**
     * Método responsável por testar a inclusão quando sucesso
     * 
     * @throws ComumException
     * 
     * @throws BancoobException
     * 
     * @throws OperacionalNoResultException
     * 
     */
    // @Test
    public void processarRetornoMensagemDDAcomSucessoR1() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirBaixaOperacional(getDDA0108R1ComplexType()));
    }

    @Ignore
    @Test
    public void processarRetornoMensagemDDAcomSucessoR2() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirBaixaOperacional(getDDARR2()));
    }

    @Ignore
    @Test
    public void processarRetornoMensagemADDAcomSucessoR2() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirBaixaOperacional(getADDAR2()));
    }

    private String incluirBaixaOperacional(ConteudoMsgRecebida type) {
        try {
            MensagemDDABaixaOperacional msgDDABaixaOper = new MensagemDDABaixaOperacional();
            msgDDABaixaOper.setNumCodigoBarra(_VALOR_PADRAO);
            msgDDABaixaOper.setNumCodBarrasCampoLivre(_VALOR_PADRAO);
            msgDDABaixaOper.setNumIdentificadorBaixaOper(Constantes.LONG_UM);
            when(mensagemDao.obterMensagemDDABaixaOperacionalAtualizaReferencias(Constantes.LONG_UM)).thenReturn(msgDDABaixaOper);
            when(boletoCipDao.obterBoletoDDA(Constantes.LONG_UM)).thenReturn(BoletoDDALoader.geraBoletoDDA());
            when(em.find(MensagemDDABaixaOperacional.class, Constantes.LONG_UM)).thenReturn(msgDDABaixaOper);
            when(boletoCipDao.obterBoletoDDABaixaOperacional(Constantes.LONG_UM, Constantes.LONG_UM)).thenReturn(null);

            ejb.processarRetornoMensagemDDA(type);
        } catch (BancoobException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;

    }

    private static DDA0108R1ComplexType getDDA0108R1ComplexType() throws ComumException {
        DDA0108R1ComplexType type = new DDA0108R1ComplexType();
        type.setNumIdentcBaixaOperac(new BigInteger(_VALOR_PADRAO));
        type.setNumRefAtlBaixaOperac(new BigInteger(_VALOR_PADRAO));
        type.setNumSeqAtlzBaixaOperac(new BigInteger(_VALOR_PADRAO));
        type.setNumIdentcTit(new BigInteger(_VALOR_PADRAO));
        type.setDtHrDDA(DataCipUtil.dateTimeZeroHoraParaXMLGregorianCalendar(new Date()));
        type.setNumCtrlPart(Constantes.STRING_NUMERO_1);

        return type;
    }

    private static DDA0108R2ComplexType getDDARR2() throws ComumException {

        DDA0108R2ComplexType type2 = new DDA0108R2ComplexType();
        type2.setNumIdentcTit(new BigInteger(_VALOR_PADRAO));
        type2.setNumIdentcBaixaOperac(new BigInteger(_VALOR_PADRAO));
        type2.setNumRefAtlBaixaOperac(new BigInteger(_VALOR_PADRAO));
        type2.setNumSeqAtlzBaixaOperac(new BigInteger(_VALOR_PADRAO));
        type2.setNumRefAtlCadTit(new BigInteger(_VALOR_PADRAO));
        type2.setNumRefCadTitBaixaOperac(new BigInteger(_VALOR_PADRAO));
        type2.setDtHrProcBaixaOperac(DataCipUtil.dateTimeZeroHoraParaXMLGregorianCalendar(new Date()));
        type2.setDtProcBaixaOperac(DataCipUtil.dateTimeZeroHoraParaXMLGregorianCalendar(new Date()));
        type2.setQtdPgtoParclRegtd(new BigInteger(_VALOR_PADRAO));
        type2.setVlrSldTotAtlPgtoTit(new BigDecimal(_VALOR_PADRAO));
        type2.setSitTitPgto(_VALOR_PADRAO);
        type2.setNumCodBarrasBaixaOperac(_VALOR_PADRAO);
        type2.setVlrBaixaOperacTit(new BigDecimal(_VALOR_PADRAO));
        type2.setISPBPartRecbdrBaixaOperac(_VALOR_PADRAO);
        type2.setCodPartRecbdrBaixaOperac(_VALOR_PADRAO);
        type2.setTpBaixaOperac(_VALOR_PADRAO);
        type2.setTpPessoaPort(_VALOR_PADRAO);
        type2.setCNPJCPFPort(new BigInteger(_VALOR_PADRAO));
        type2.setCanPgto(new BigInteger(_VALOR_PADRAO));
        type2.setMeioPgto(new BigInteger(_VALOR_PADRAO));
        type2.setIndrOpContg(_VALOR_PADRAO);

        return type2;

    }

    private static GrupoADDA108RR2TitComplexType getADDAR2() throws ComumException {

        GrupoADDA108RR2TitComplexType type2 = new GrupoADDA108RR2TitComplexType();
        type2.setNumIdentcTit(new BigInteger(_VALOR_PADRAO));
        type2.setNumIdentcBaixaOperac(new BigInteger(_VALOR_PADRAO));
        type2.setNumRefAtlBaixaOperac(new BigInteger(_VALOR_PADRAO));
        type2.setNumSeqAtlzBaixaOperac(new BigInteger(_VALOR_PADRAO));
        type2.setNumRefCadTitBaixaOperac(new BigInteger(_VALOR_PADRAO));
        type2.setDtHrProcBaixaOperac(DataCipUtil.dateTimeZeroHoraParaXMLGregorianCalendar(new Date()));
        type2.setDtProcBaixaOperac(DataCipUtil.dateTimeZeroHoraParaXMLGregorianCalendar(new Date()));
        type2.setQtdPgtoParclRegtd(new BigInteger(_VALOR_PADRAO));
        type2.setVlrSldTotAtlPgtoTit(new BigDecimal(_VALOR_PADRAO));
        type2.setSitTitPgto(_VALOR_PADRAO);
        type2.setNumCodBarrasBaixaOperac(_VALOR_PADRAO);
        type2.setVlrBaixaOperacTit(new BigDecimal(_VALOR_PADRAO));
        type2.setISPBPartRecbdrBaixaOperac(_VALOR_PADRAO);
        type2.setCodPartRecbdrBaixaOperac(_VALOR_PADRAO);
        type2.setTpBaixaOperac(_VALOR_PADRAO);
        type2.setTpPessoaPort(_VALOR_PADRAO);
        type2.setCNPJCPFPort(new BigInteger(_VALOR_PADRAO));
        type2.setCanPgto(new BigInteger(_VALOR_PADRAO));
        type2.setMeioPgto(new BigInteger(_VALOR_PADRAO));
        type2.setIndrOpContg(_VALOR_PADRAO);

        return type2;

    }

    private static DDA0115R1ComplexType getDDAR115R2() throws ComumException {
        DDA0115R1ComplexType type2 = new DDA0115R1ComplexType();

        type2.setCodMsg(_VALOR_PADRAO);
        type2.setDtHrDDA(DataCipUtil.dateTimeZeroHoraParaXMLGregorianCalendar(new Date()));
        type2.setDtMovto(DataCipUtil.dateTimeZeroHoraParaXMLGregorianCalendar(new Date()));
        type2.setISPBPartRecbdrAdmtd(_VALOR_PADRAO);
        type2.setISPBPartRecbdrPrincipal(_VALOR_PADRAO);
        type2.setNumCtrlDDA(_VALOR_PADRAO);
        type2.setNumCtrlPart(_VALOR_PADRAO);
        type2.setNumIdentcBaixaOperac(new BigInteger(_VALOR_PADRAO));

        return type2;

    }

}
