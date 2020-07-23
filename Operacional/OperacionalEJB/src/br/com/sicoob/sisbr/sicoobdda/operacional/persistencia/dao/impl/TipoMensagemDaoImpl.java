package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import java.util.List;
import java.util.Map;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.CategoriaMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDaoDB2;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoMensagemDao;

/**
 * @author samuell.ramos
 */
public class TipoMensagemDaoImpl extends OperacionalCrudDaoDB2<TipoMensagemDDA> implements TipoMensagemDao {

    private static final String ARQUIVO_QUERIES = "sicoobdda_operacional.tipomensagem.queries.xml";
    private static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional-tipomensagem";
    private static final String COMANDOS = "PESQUISAR_TIPO_MENSAGEM";
    private static final String COMANDOS_QTD = "OBTER_QTD_TIPO_MENSAGEM";

    private static final String COD_TIPO_ENVIO_POR_ARQUIVO = "A";

    /**
     * 
     */
    public TipoMensagemDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, null, COMANDOS, COMANDOS_QTD);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoMensagemDao#listarTipoMensagem()
     */
    public List<TipoMensagemDDA> listarTipoMensagem() throws ComumException {
        return listar("LISTAR_TIPO_MENSAGEM");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoMensagemDao#listarTipoMensagemPorCategoria()
     */
    public List<TipoMensagemDDA> listarTipoMensagemPorCategoria() throws ComumException {
        return listar("LISTAR_TIPO_MENSAGEM_POR_CATEGORIA", getMapaParametro(COD_TIPO_ENVIO_POR_ARQUIVO, "codTipoCategoria"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoMensagemDao#listarCategoriaMensagemDDA()
     */
    public List<CategoriaMensagemDDA> listarCategoriaMensagemDDA() throws ComumException {
        return listar("LISTAR_CATEGORIA_MENSAGEM_DDA");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoMensagemDao#isExisteEmTipoMensagem(java.lang.String)
     */
    public Boolean isExisteEmTipoMensagem(String codTipoMensagem) throws ComumException {
        return existeRegistro("EXISTE_TIPO_MENSAGEM", getParametrosCodTipoMensagem(codTipoMensagem));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoMensagemDao#isVinculadoArqCorrespondente(java.lang.String)
     */
    public Boolean isVinculadoArqCorrespondente(String codTipoMensagem) throws ComumException {
        return existeRegistro("EXISTE_VINCULO_ARQUIVO_CORRESPONDENTE", getParametrosCodTipoMensagem(codTipoMensagem));
    }

    /**
     * Método responsável por
     * 
     * @param codTipoMensagem
     * @return Map<String,Object>
     * 
     */
    private Map<String, Object> getParametrosCodTipoMensagem(String codTipoMensagem) {
        return getMapaParametro(codTipoMensagem, "codTipoMensagem");
    }
}
