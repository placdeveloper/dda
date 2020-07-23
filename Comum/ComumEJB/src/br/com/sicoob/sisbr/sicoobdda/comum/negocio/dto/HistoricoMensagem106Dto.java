package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * HistoricoMensagem106Dto é responsável por
 * 
 * @author george.santos
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.HistoricoMensagem106DTO")
public class HistoricoMensagem106Dto extends BancoobDto {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private DateTimeDB dataHoraEnvio;
    private DateTimeDB dataHoraRetornoCIP;
    private String numCodigoBarra;
    private String descSituacaoBoleto;
    private String descSituacaoBoletoCIP;

    /**
     * 
     */
    public HistoricoMensagem106Dto() {
    }

    /**
     * @param dataHoraEnvio
     * @param dataHoraRetornoCIP
     * @param numCodigoBarra
     * @param codTipomensagemDDA
     * @param descSituacaoBoleto
     * @throws ComumNegocioException
     */
    public HistoricoMensagem106Dto(Date dataHoraEnvio, Date dataHoraRetornoCIP, String numCodigoBarra, String codTipomensagemDDA, String descSituacaoBoleto, String xmlMensagemDDA)
            throws ComumNegocioException {

        if (dataHoraRetornoCIP != null) {
            this.dataHoraRetornoCIP = new DateTimeDB(dataHoraRetornoCIP.getTime());
            this.descSituacaoBoletoCIP = Constantes.SUCESSO_CIP;
        } else if (codTipomensagemDDA != null && codTipomensagemDDA.contains("E")) {
            this.descSituacaoBoletoCIP = Constantes.RETORNO_CIP_COM_ERRO;
        } else {
            this.descSituacaoBoletoCIP = Constantes.SEM_RETORNO_CIP;
        }

        if (dataHoraEnvio != null) {
            this.dataHoraEnvio = new DateTimeDB(dataHoraEnvio.getTime());
        } else {
            this.descSituacaoBoletoCIP = Constantes.MENSAGEM_NAO_ENVIADA_PARA_CIP;
        }
        
        String[] situacaoReqDDA = (xmlMensagemDDA==null) ? null : xmlMensagemDDA.split("<SitReqDDA>");    
        
        if (situacaoReqDDA != null && situacaoReqDDA.length > 1 && situacaoReqDDA[1].toString().substring(0, 1).equals(Constantes.REQUISIÇÃO_SEM_DADOS_ATENDAM_FILTRO)) {
            this.descSituacaoBoletoCIP = Constantes.RETORNO_CIP_NAO_RETORNOU_RESULTADO;
        }

        this.numCodigoBarra = numCodigoBarra;
        this.descSituacaoBoleto = descSituacaoBoleto;
    }

    /**
     * @return o atributo dataHoraEnvio
     */
    public DateTimeDB getDataHoraEnvio() {
        return dataHoraEnvio;
    }

    /**
     * Define o atributo dataHoraEnvio
     */
    public void setDataHoraEnvio(DateTimeDB dataHoraEnvio) {
        this.dataHoraEnvio = dataHoraEnvio;
    }

    /**
     * @return o atributo dataHoraRetornoCIP
     */
    public DateTimeDB getDataHoraRetornoCIP() {
        return dataHoraRetornoCIP;
    }

    /**
     * Define o atributo dataHoraRetornoCIP
     */
    public void setDataHoraRetornoCIP(DateTimeDB dataHoraRetornoCIP) {
        this.dataHoraRetornoCIP = dataHoraRetornoCIP;
    }

    /**
     * @return o atributo numCodigoBarra
     */
    public String getNumCodigoBarra() {
        return numCodigoBarra;
    }

    /**
     * Define o atributo numCodigoBarra
     */
    public void setNumCodigoBarra(String numCodigoBarra) {
        this.numCodigoBarra = numCodigoBarra;
    }

    /**
     * @return o atributo descSituacaoBoleto
     */
    public String getDescSituacaoBoleto() {
        return descSituacaoBoleto;
    }

    /**
     * Define o atributo descSituacaoBoleto
     */
    public void setDescSituacaoBoleto(String descSituacaoBoleto) {
        this.descSituacaoBoleto = descSituacaoBoleto;
    }

    /**
     * @return o atributo descSituacaoBoletoCIP
     */
    public String getDescSituacaoBoletoCIP() {
        return descSituacaoBoletoCIP;
    }

    /**
     * Define o atributo descSituacaoBoletoCIP
     */
    public void setDescSituacaoBoletoCIP(String descSituacaoBoletoCIP) {
        this.descSituacaoBoletoCIP = descSituacaoBoletoCIP;
    }
}
