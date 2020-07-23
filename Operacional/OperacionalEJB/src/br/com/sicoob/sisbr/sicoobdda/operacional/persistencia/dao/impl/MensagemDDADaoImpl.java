package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import java.util.HashMap;
import java.util.Map;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.MensagemDDADao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDaoDB2;

/**
 * MensagemDDADaoImpl é responsável por
 * 
 * @author Rodrigo.Neri
 */
public class MensagemDDADaoImpl extends OperacionalCrudDaoDB2<SicoobDDAEntidade> implements MensagemDDADao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_operacional.mensagem.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional-mensagem";

    public MensagemDDADaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.MensagemDDADao#obterCanalDDA(short)
     */
    public Short obterCanalDDA(short canalCTR) throws ComumException {
        debug("### Obtendo o canal DDA de acordo com o canalCTR...");
        debug("Parâmetro - canalCTR: " + canalCTR);

        Map<String, Object> parametros = new HashMap<String, Object>(1);
        parametros.put("canalCTR", canalCTR);

        return super.obterDados("OBTER_CANAL_DDA", parametros);
    }

}
