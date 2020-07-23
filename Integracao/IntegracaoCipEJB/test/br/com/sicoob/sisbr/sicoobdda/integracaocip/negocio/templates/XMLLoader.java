/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates
 * Arquivo:         XMLLoader.java
 * Data Criação:    Jan 23, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates;

import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * XMLLoader é responsável por 
 * 
 * @author Felipe.Rosa
 */
public abstract class XMLLoader {

    /**
     * 
     */
    private XMLLoader() {
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public static String getMQRecebimentoGEN0004() {
        StringBuilder str = new StringBuilder();
        str.append("<GEN0004>");
        str.append("<CodMsg>GEN0004</CodMsg>");
        str.append("<ErroGEN>EGEN0300</ErroGEN>");
        str.append("<ISPBEmissor>00038166</ISPBEmissor>");
        str.append("<ISPBDestinatario>02038232</ISPBDestinatario>");
        str.append("<NumMQ>414D5120514D2E30323033383233322E5FE88C5720956902</NumMQ>");
        str.append("<NUOpOr>02038232201607267498474</NUOpOr>");
        str.append("<DtHrPart>2016-07-26T00:43:58</DtHrPart>");
        str.append("</GEN0004>");
        return getMQRecebimento(str.toString());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public static String getMQRecebimentoDDA0503R1() {
        StringBuilder str = new StringBuilder();
        str.append("<DDA0503R1>");
        str.append("<CodMsg>DDA0503R1</CodMsg>");
        str.append("<NumCtrlPart>999</NumCtrlPart>");
        str.append("<NumCtrlDDA>999</NumCtrlDDA>");
        str.append("<ISPBPartDestinatarioPrincipal>02038232</ISPBPartDestinatarioPrincipal>");
        str.append("<ISPBPartDestinatarioAdmtd>02038232</ISPBPartDestinatarioAdmtd>");
        str.append("<NumIdentcBenfcrio>02038232</NumIdentcBenfcrio>");
        str.append("<NumRefAtlCadBenfcrio>02038232</NumRefAtlCadBenfcrio>");
        str.append("<NumRefAtlCadBenfcrio>02038232</NumRefAtlCadBenfcrio>");
        str.append("<NumSeqAtlzCadBenfcrio>02038232</NumSeqAtlzCadBenfcrio>");
        str.append("<DtHrDDA>2016-10-17T00:43:58</DtHrDDA>");
        str.append("<DtMovto>2016-10-17T00:00:00</DtMovto>");
        str.append("</DDA0503R1>");
        return getMQRecebimento(str.toString());

    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public static String getMQRecebimentoNaoMapeado() {
        return getMQRecebimento("");

    }

    /**
     * Método responsável por
     * 
     * @param xmlTipoMensagem
     * @return String
     * 
     */
    private static String getMQRecebimento(String xmlTipoMensagem) {
        StringBuilder str = new StringBuilder();
        str.append("<MQRecebimento>");
        str.append("<Header>");
        str.append("<Cooperativa>0001</Cooperativa>");
        str.append("<DataHora>2016-07-26T11:29:05</DataHora>");
        str.append("<NUOP>02038232201607267498535</NUOP>");
        str.append("</Header>");
        str.append("<MsgSPB>");
        str.append(xmlTipoMensagem);
        str.append("</MsgSPB>");
        str.append("</MQRecebimento>");
        return str.toString();
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public static String getMQRespostaEnvio() {
        return getMQRespostaEnvio("");
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public static String getMQRespostaEnvioErro() {
        return getMQRespostaEnvio(Constantes.TESTE_FALHA);
    }

    /**
     * Método responsável por
     * 
     * @param erro
     * @return String
     * 
     */
    private static String getMQRespostaEnvio(String erro) {
        StringBuilder str = new StringBuilder();
        str.append("<MQRespostaEnvio>");
        str.append("<Header>");
        str.append("<Cooperativa>0001</Cooperativa>");
        str.append("<DataHora>2016-07-26T11:29:05</DataHora>");
        str.append("<NumSeqOp>02038232201607267498535</NumSeqOp>");
        str.append("</Header>");
        str.append("<RespostaMsg>");
        str.append("<NUOP>02038232201607267498535</NUOP>");
        str.append("<Erro>");
        str.append(erro);
        str.append("</Erro>");
        str.append("</RespostaMsg>");
        str.append("</MQRespostaEnvio>");
        return str.toString();
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public static String getArqRecebimento() {
        StringBuilder str = new StringBuilder();
        str.append("<ArqRecebimento>");
        str.append("<Grupo_ADDA001RET_PagdrActo>");
        str.append("<NumCtrlReqPart>123456</NumCtrlReqPart>");
        str.append("<ISPBPartRecbdrPrincipal>123456</ISPBPartRecbdrPrincipal>");
        str.append("<ISPBPartRecbdrAdmtd>123456</ISPBPartRecbdrAdmtd>");
        str.append("<NumIdentcPagdr>123</NumIdentcPagdr>");
        str.append("<NumRefAtlCadPagdr>123</NumRefAtlCadPagdr>");
        str.append("<NumSeqAtlzCadPagdr>123</NumSeqAtlzCadPagdr>");
        str.append("<NumCtrlDDA>123456</NumCtrlDDA>");
        str.append("<QtdAdesPagdrDDA>123</QtdAdesPagdrDDA>");
        str.append("</Grupo_ADDA001RET_PagdrActo>");
        str.append("</ArqRecebimento>");
        return str.toString();
    }

}
