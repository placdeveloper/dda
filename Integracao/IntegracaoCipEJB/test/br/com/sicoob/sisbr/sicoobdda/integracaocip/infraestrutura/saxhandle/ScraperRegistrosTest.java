/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.infraestrutura.saxhandle
 * Arquivo:         ScraperRegistrosTest.java
 * Data Criação:    Dec 6, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.infraestrutura.saxhandle;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import br.com.sicoob.sisbr.sicoobdda.entidades.LogDetRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoProcessamentoArquivo;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.enums.TipoArquivoRetornoEnum;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO;

/**
 * ScraperRegistrosTest é responsável por
 * 
 * @author Adriano.Pinheiro
 */
@RunWith(JUnit4.class)
public class ScraperRegistrosTest {

    private SAXParserFactory spf;
    private SAXParser saxParser;
    private XMLReader xmlReader;

    private String XMLDIS = "";

    private String XMLRET = "";

    @Before
    public void setup() throws ParserConfigurationException, SAXException {

        this.spf = SAXParserFactory.newInstance();
        this.spf.setNamespaceAware(true);
        this.saxParser = spf.newSAXParser();
        this.xmlReader = saxParser.getXMLReader();

        // xml teste DISADDA122
        this.XMLDIS = this.montaXMLDISADDA122();

        // xml teste RETADDA101
        this.XMLRET = this.montaXMLRETADDA101();

    }

    @Test
    public void testaCriacaoDoScraperParaArquivoDIS() {

        // dado os parâmetros

        ArrayList<LogDetRecebimentoArquivoDDA> lista = new ArrayList<LogDetRecebimentoArquivoDDA>();

        // quando
        final ArquivoProcessamentoVO arqRET = new ArquivoProcessamentoVO(1L, TipoArquivoRetornoEnum.RET, "nome_teste", 1L, "ADDA101RET", SituacaoProcessamentoArquivo.ARQUIVO_DISPONIVEL);

        // então
        Assert.assertNotNull("Objeto Criado com sucesso", new ScraperRegistros(arqRET, lista, 1));

    }

    @Test
    public void testaCriacaoDoScraperParaArquivoRET() {

        // dado os parâmetros
        ArrayList<LogDetRecebimentoArquivoDDA> lista = new ArrayList<LogDetRecebimentoArquivoDDA>();

        // quando
        final ArquivoProcessamentoVO arqDIS = new ArquivoProcessamentoVO(1L, TipoArquivoRetornoEnum.DIS, "nome_teste", 1L, "ADDA101RR2", SituacaoProcessamentoArquivo.ARQUIVO_DISPONIVEL);

        // então
        Assert.assertNotNull("Objeto Criado com sucesso", new ScraperRegistros(arqDIS, lista, 1));

    }

    @Test
    public void testaScraperDeArquivDIS() throws IOException, SAXException {

        // dado os parâmetros
        ArquivoProcessamentoVO arqProc = new ArquivoProcessamentoVO(1L, TipoArquivoRetornoEnum.DIS, "TESTE", 1L, "ADDA122RR2", SituacaoProcessamentoArquivo.ARQUIVO_DISPONIVEL);
        ArrayList<LogDetRecebimentoArquivoDDA> lista = new ArrayList<LogDetRecebimentoArquivoDDA>();

        // quando
        this.xmlReader.setContentHandler(new ScraperRegistros(arqProc, lista, 1));
        this.xmlReader.parse(new InputSource(new StringReader(this.XMLDIS)));

        // então
        Assert.assertEquals(2, lista.size());

    }

    @Test
    public void testaScraperDeArquivoRET() throws IOException, SAXException {

        // dado os parâmtros
        ArquivoProcessamentoVO arqProc = new ArquivoProcessamentoVO(1L, TipoArquivoRetornoEnum.RET, "TESTE", 1L, "ADDA101RET", SituacaoProcessamentoArquivo.ARQUIVO_DISPONIVEL);
        ArrayList<LogDetRecebimentoArquivoDDA> lista = new ArrayList<LogDetRecebimentoArquivoDDA>();

        // quando
        this.xmlReader.setContentHandler(new ScraperRegistros(arqProc, lista, 1));
        this.xmlReader.parse(new InputSource(new StringReader(this.XMLRET)));

        // então
        Assert.assertEquals(4, lista.size());
    }

    private String montaXMLDISADDA122() {

        StringBuilder sb = new StringBuilder();

        // possui 2 registros

        sb.append("<?xml version='1.0' encoding='UTF-8'?>"
                + "<ADDADOC xmlns='http://www.bcb.gov.br/ARQ/ADDA122.xsd' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:schemaLocation='http://www.bcb.gov.br/ARQ/ADDA122.xsd ../xsds/ADDA122.xsd '>"
                + "  <BCARQ>" + "    <NomArq>NomArq</NomArq>    <NumCtrlEmis>NumCtrlEmis</NumCtrlEmis>" + "    <NumCtrlDestOr>NumCtrlDestOr</NumCtrlDestOr>"
                + "    <ISPBEmissor>ISPBEmissor</ISPBEmissor>" + "    <ISPBDestinatario>ISPBDestinatario</ISPBDestinatario>" + "    <DtHrDDA>2001-12-31T12:00:00</DtHrDDA>"
                + "    <SitReqDDA>0</SitReqDDA>" + "    <IndrFlagFim>IndrFlagFim</IndrFlagFim>" + "    <DtMovto>2001-01-01</DtMovto>" + "    <Grupo_Seq>"
                + "      <NumSeq>0</NumSeq>" + "      <IndrCont>IndrCont</IndrCont>" + "    </Grupo_Seq>" + "  </BCARQ>" + "  <SISARQ>" + "    <ADDA122RR2>"

                + "        <Grupo_ADDA122RR2_Tit>" + "        <ISPBPartRecbdrPrincipal>ISPBPartRecbdrPrincipal</ISPBPartRecbdrPrincipal>"
                + "        <ISPBPartRecbdrAdmtd>ISPBPartRecbdrAdmtd</ISPBPartRecbdrAdmtd>" + "        <NumCtrlDDA>NumCtrlDDA</NumCtrlDDA>"
                + "        <NumIdentcTit>0</NumIdentcTit>" + "        <NumIdentcTerc>0</NumIdentcTerc>" + "        <NumRefAtlCadTerc>0</NumRefAtlCadTerc>"
                + "        <TpPessoaTerc>TpPessoaTerc</TpPessoaTerc>" + "        <CNPJ_CPFTerc>0</CNPJ_CPFTerc>" + "        <DtHrSit>2001-12-31T12:00:00</DtHrSit>"
                + "      </Grupo_ADDA122RR2_Tit>" + "      <Grupo_ADDA122RR2_Tit>" + "        <ISPBPartRecbdrPrincipal>ISPBPartRecbdrPrincipal</ISPBPartRecbdrPrincipal>"

                + "        <ISPBPartRecbdrAdmtd>ISPBPartRecbdrAdmtd</ISPBPartRecbdrAdmtd>" + "        <NumCtrlDDA>NumCtrlDDA</NumCtrlDDA>"
                + "        <NumIdentcTit>0</NumIdentcTit>" + "        <NumIdentcTerc>0</NumIdentcTerc>" + "        <NumRefAtlCadTerc>0</NumRefAtlCadTerc>"
                + "        <TpPessoaTerc>TpPessoaTerc</TpPessoaTerc>" + "        <CNPJ_CPFTerc>0</CNPJ_CPFTerc>" + "        <DtHrSit>2001-12-31T12:00:00</DtHrSit>"
                + "      </Grupo_ADDA122RR2_Tit>" + "   </ADDA122RR2>" + "  </SISARQ>" + "  <ESTARQ>ESTARQ</ESTARQ>" + "</ADDADOC>");
        return sb.toString();

    }

    private String montaXMLRETADDA101() {

        StringBuilder sb = new StringBuilder();

        sb.append("<?xml version='1.0' encoding='UTF-8'?>"
                + "<ADDADOC xmlns='http://www.bcb.gov.br/ARQ/ADDA101.xsd' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:schemaLocation='http://www.bcb.gov.br/ARQ/ADDA101.xsd ../xsds/ADDA101.xsd '>"
                + "  <BCARQ>" + "    <NomArq>NomArq</NomArq>" + "    <NumCtrlEmis>NumCtrlEmis</NumCtrlEmis>" + "    <NumCtrlDestOr>NumCtrlDestOr</NumCtrlDestOr>"
                + "    <ISPBEmissor>ISPBEmissor</ISPBEmissor>" + "    <ISPBDestinatario>ISPBDestinatario</ISPBDestinatario>" + "    <DtHrDDA>2001-12-31T12:00:00</DtHrDDA>"
                + "    <SitReqDDA>0</SitReqDDA>" + "    <IndrFlagFim>IndrFlagFim</IndrFlagFim>" + "    <DtMovto>2001-01-01</DtMovto>" + "    <Grupo_Seq>"
                + "      <NumSeq>0</NumSeq>" + "      <IndrCont>IndrCont</IndrCont>" + "    </Grupo_Seq>" + "  </BCARQ>" + "  <SISARQ>" + "    <ADDA101RET>"

                + "      <Grupo_ADDA101RET_TitActo>" + "        <NumCtrlReqPart>NumCtrlReqPart</NumCtrlReqPart>"
                + "        <ISPBPartDestinatarioPrincipal>ISPBPartDestinatarioPrincipal</ISPBPartDestinatarioPrincipal>"
                + "        <ISPBPartDestinatarioAdmtd>ISPBPartDestinatarioAdmtd</ISPBPartDestinatarioAdmtd>" + "        <NumIdentcTit>0</NumIdentcTit>"
                + "        <NumRefAtlCadTit>0</NumRefAtlCadTit>" + "        <NumSeqAtlzCadTit>0</NumSeqAtlzCadTit>" + "        <NumCtrlDDA>NumCtrlDDA</NumCtrlDDA>"
                + "        <NumCodBarras>NumCodBarras</NumCodBarras>" + "      </Grupo_ADDA101RET_TitActo>" + "      <Grupo_ADDA101RET_TitActo>"
                + "        <NumCtrlReqPart>NumCtrlReqPart</NumCtrlReqPart>"
                + "        <ISPBPartDestinatarioPrincipal>ISPBPartDestinatarioPrincipal</ISPBPartDestinatarioPrincipal>"
                + "        <ISPBPartDestinatarioAdmtd>ISPBPartDestinatarioAdmtd</ISPBPartDestinatarioAdmtd>" + "        <NumIdentcTit>0</NumIdentcTit>"
                + "       <NumRefAtlCadTit>0</NumRefAtlCadTit>" + "        <NumSeqAtlzCadTit>0</NumSeqAtlzCadTit>" + "        <NumCtrlDDA>NumCtrlDDA</NumCtrlDDA>"
                + "        <NumCodBarras>NumCodBarras</NumCodBarras>" + "      </Grupo_ADDA101RET_TitActo>" + "      " +

                "<Grupo_ADDA101RET_TitRecsd CodErro=''>" + "        <NumCtrlReqPart CodErro=''>NumCtrlReqPart</NumCtrlReqPart>"
                + "        <ISPBPartDestinatarioPrincipal CodErro=''>ISPBPartDestinatarioPrincipal</ISPBPartDestinatarioPrincipal>"
                + "        <ISPBPartDestinatarioAdmtd CodErro=''>ISPBPartDestinatarioAdmtd</ISPBPartDestinatarioAdmtd>"
                + "        <TpPessoaBenfcrioOr CodErro=''>TpPessoaBenfcrioOr</TpPessoaBenfcrioOr>" + "        <CNPJ_CPFBenfcrioOr CodErro=''>0</CNPJ_CPFBenfcrioOr>"
                + "        <Nom_RzSocBenfcrioOr CodErro=''>Nom_RzSocBenfcrioOr</Nom_RzSocBenfcrioOr>" + "        <TpPessoaPagdr CodErro=''>TpPessoaPagdr</TpPessoaPagdr>"
                + "        <CNPJ_CPFPagdr CodErro=''>0</CNPJ_CPFPagdr>" + "        <Nom_RzSocPagdr CodErro=''>Nom_RzSocPagdr</Nom_RzSocPagdr>"
                + "        <TpIdentcSacdrAvalst CodErro=''>0</TpIdentcSacdrAvalst>" + "        <CodCartTit CodErro=''>CodCartTit</CodCartTit>"
                + "        <CodMoedaCNAB CodErro=''>CodMoedaCNAB</CodMoedaCNAB>" + "        <IdentdNossoNum CodErro=''>IdentdNossoNum</IdentdNossoNum>"
                + "        <NumCodBarras CodErro=''>NumCodBarras</NumCodBarras>" + "        <NumLinhaDigtvl CodErro=''>NumLinhaDigtvl</NumLinhaDigtvl>"
                + "        <VlrTit CodErro=''>0.0</VlrTit>" + "        <CodEspTit CodErro=''>CodEspTit</CodEspTit>" + "        <DtEmsTit CodErro=''>2001-01-01</DtEmsTit>"
                + "        <TpPgtoTit CodErro=''>0</TpPgtoTit>" + "        <IndrTitNegcd CodErro=''>IndrTitNegcd</IndrTitNegcd>"
                + "        <IndrBloqPgto CodErro=''>IndrBloqPgto</IndrBloqPgto>" + "        <IndrPgtoParcl CodErro=''>IndrPgtoParcl</IndrPgtoParcl>"
                + "        <VlrAbattTit CodErro=''>0.0</VlrAbattTit>" + "        <TpModlCalc CodErro=''>TpModlCalc</TpModlCalc>"
                + "        <TpAutcRecbtVlrDivgte CodErro=''>TpAutcRecbtVlrDivgte</TpAutcRecbtVlrDivgte>" + "      </Grupo_ADDA101RET_TitRecsd>"
                + "      <Grupo_ADDA101RET_TitRecsd CodErro=''>" + "        <NumCtrlReqPart CodErro=''>NumCtrlReqPart</NumCtrlReqPart>"
                + "        <ISPBPartDestinatarioPrincipal CodErro=''>ISPBPartDestinatarioPrincipal</ISPBPartDestinatarioPrincipal>"
                + "        <ISPBPartDestinatarioAdmtd CodErro=''>ISPBPartDestinatarioAdmtd</ISPBPartDestinatarioAdmtd>"
                + "        <TpPessoaBenfcrioOr CodErro=''>TpPessoaBenfcrioOr</TpPessoaBenfcrioOr>" + "        <CNPJ_CPFBenfcrioOr CodErro=''>0</CNPJ_CPFBenfcrioOr>"
                + "        <Nom_RzSocBenfcrioOr CodErro=''>Nom_RzSocBenfcrioOr</Nom_RzSocBenfcrioOr>" + "        <TpPessoaPagdr CodErro=''>TpPessoaPagdr</TpPessoaPagdr>"
                + "        <CNPJ_CPFPagdr CodErro=''>0</CNPJ_CPFPagdr>" + "        <Nom_RzSocPagdr CodErro=''>Nom_RzSocPagdr</Nom_RzSocPagdr>"
                + "        <TpIdentcSacdrAvalst CodErro=''>0</TpIdentcSacdrAvalst>" + "        <CodCartTit CodErro=''>CodCartTit</CodCartTit>"
                + "        <CodMoedaCNAB CodErro=''>CodMoedaCNAB</CodMoedaCNAB>" + "        <IdentdNossoNum CodErro=''>IdentdNossoNum</IdentdNossoNum>"
                + "        <NumCodBarras CodErro=''>NumCodBarras</NumCodBarras>" + "        <NumLinhaDigtvl CodErro=''>NumLinhaDigtvl</NumLinhaDigtvl>"
                + "        <VlrTit CodErro=''>0.0</VlrTit>" + "        <CodEspTit CodErro=''>CodEspTit</CodEspTit>" + "        <DtEmsTit CodErro=''>2001-01-01</DtEmsTit>"
                + "        <TpPgtoTit CodErro=''>0</TpPgtoTit>" + "        <IndrTitNegcd CodErro=''>IndrTitNegcd</IndrTitNegcd>"
                + "        <IndrBloqPgto CodErro=''>IndrBloqPgto</IndrBloqPgto>" + "        <IndrPgtoParcl CodErro=''>IndrPgtoParcl</IndrPgtoParcl>"
                + "        <VlrAbattTit CodErro=''>0.0</VlrAbattTit>" + "        <TpModlCalc CodErro=''>TpModlCalc</TpModlCalc>"
                + "        <TpAutcRecbtVlrDivgte CodErro=''>TpAutcRecbtVlrDivgte</TpAutcRecbtVlrDivgte>" + "      </Grupo_ADDA101RET_TitRecsd>" + "    </ADDA101RET>"
                + "  </SISARQ>" + "  <ESTARQ>ESTARQ</ESTARQ>" + "</ADDADOC>");

        return sb.toString();
    }

}
