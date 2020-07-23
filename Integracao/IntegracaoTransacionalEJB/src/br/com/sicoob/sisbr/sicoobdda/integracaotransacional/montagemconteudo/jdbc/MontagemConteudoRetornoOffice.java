package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bancoob.srtb.dto.RetornoMensagem;
import br.com.bancoob.srtb.montagemconteudo.jdbc.MontagemConteudoRetornoStoredProcedureDelimitado;

/**
 * Montagem para processar os conteudos do Office. Aonde o INDICE inicia com ZERO.
 * 
 * @author Paulo.Stoppa
 */
public final class MontagemConteudoRetornoOffice extends MontagemConteudoRetornoStoredProcedureDelimitado {

    private static final long serialVersionUID = 3379950794728513280L;

    /**
     * Processa os result sets, colocando-os no elemento de retorno.
     * 
     * @param retorno o objeto de retorno.
     * @throws SQLException caso ocorra algum erro.
     */
    protected void processarResultSets(RetornoMensagem retorno) throws SQLException {
        boolean possuiResultados = isHaResultadosDisponiveis();
        StringBuilder builder = new StringBuilder();

        int contadorResultados = -1;

        while (possuiResultados) {

            contadorResultados++;

            ResultSet rset = getStatement().getResultSet();// NOSONAR resultSet é finalizado pela classe chamadora

            final int numeroColunas = rset.getMetaData().getColumnCount();

            while (rset.next()) {

                if (numeroColunas > 0) {

                    builder.append(contadorResultados);
                    builder.append(getDelimitadorColuna());
                    builder.append(rset.getString(1));
                    builder.append(getDelimitadorColuna());

                    if (numeroColunas > 1) {
                        for (int i = 2; i <= numeroColunas; i++) {
                            builder.append(recuperarString(rset, i));
                            builder.append(getDelimitadorColuna());
                        }
                    }
                }

                builder.append(getDelimitadorLinha());
            }
            possuiResultados = getStatement().getMoreResults();
            rset.close();
        }

        retorno.setConteudoRetorno(builder.toString());
    }

}