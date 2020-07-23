/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates
 * Arquivo:         CadastroSerie0200DtoLoader.java
 * Data Criação:    Jan 17, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CadastroSerie0200Dto;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * CadastroSerie0200DtoLoader é responsável por 
 * 
 * @author Felipe.Rosa
 */
public final class CadastroSerie0200DtoLoader {

    /**
     * 
     */
    private CadastroSerie0200DtoLoader() {
        super();
    }

    /**
     * Método responsável por
     * 
     * @return CadastroSerie0200Dto
     * 
     */
    public static CadastroSerie0200Dto geraCadastroDDA0200() {
        CadastroSerie0200Dto dto = new CadastroSerie0200Dto();
        dto.setDataInicioApuracao(new DateTimeDB());
        dto.setDataFimApuracao(new DateTimeDB());
        dto.setNumIdentLancamento(Constantes.BIG_INTEGER_1_AUX);
        dto.setCodTipoTransacao(Constantes.STRING_NUMERO_1);
        dto.setCodTipoConsulta(Constantes.STRING_NUMERO_1);
        dto.setCodTipoRetorno(Constantes.STRING_NUMERO_1);
        return dto;
    }

    /**
     * Método responsável por
     * 
     * @return CadastroSerie0200Dto
     * 
     */
    public static CadastroSerie0200Dto geraCadastroDDA0214() {
        CadastroSerie0200Dto dto = new CadastroSerie0200Dto();
        dto.setDataHoraRef(new DateTimeDB());
        dto.setDataInicioApuracao(new DateTimeDB());
        dto.setDataFimApuracao(new DateTimeDB());
        dto.setCodTipoMensagem(Constantes.STRING_NUMERO_1);
        dto.setTipoMensagemArquivo(Constantes.STRING_NUMERO_1);
        dto.setCodTipoRetorno(Constantes.STRING_NUMERO_1);
        return dto;
    }

    /**
     * Método responsável por
     * 
     * @return CadastroSerie0200Dto
     * 
     */
    public static CadastroSerie0200Dto geraCadastroDDA0215() {
        CadastroSerie0200Dto dto = new CadastroSerie0200Dto();
        dto.setCodTipoMensagem(Constantes.STRING_NUMERO_1);
        dto.setTipoMensagemArquivo(Constantes.STRING_NUMERO_1);
        dto.setIdentArquivoc(Constantes.STRING_NUMERO_1);
        dto.setNumOpMensagem(Constantes.STRING_NUMERO_1);
        return dto;
    }
}
