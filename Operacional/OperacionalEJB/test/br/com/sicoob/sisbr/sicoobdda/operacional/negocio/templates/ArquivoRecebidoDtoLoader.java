/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.templates
 * Arquivo:         ArquivoRecebidoDtoLoader.java
 * Data Criação:    Jul 11, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.templates;

import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.ArquivoRecebidoDto;

/**
 * ArquivoRecebidoDtoLoader é responsável por
 * 
 * @author Felipe.Rosa
 */
public final class ArquivoRecebidoDtoLoader {

    /**
     * 
     */
    private ArquivoRecebidoDtoLoader() {

    }

    /**
     * Método responsável por
     * 
     * @param codSituacaoProcessamento
     * @return ArquivoRecebidoDto
     * 
     */
    public static ArquivoRecebidoDto gerar(Short codSituacaoProcessamento) {
        ArquivoRecebidoDto arquivo = new ArquivoRecebidoDto(Constantes.LONG_UM, Constantes.STRING_NUMERO_1, codSituacaoProcessamento);
        arquivo.setCodTipoArquivo("DIS");
        arquivo.setIdLogEnvioArqDDA(Constantes.LONG_UM);
        arquivo.setIdLogRecebimentoArqDDA(Constantes.LONG_UM);
        arquivo.setCodTipoMensagem(TipoMensagemDDA.ADDA101RR2);
        return arquivo;
    }

}
