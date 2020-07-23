/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.17-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.util
 * Arquivo:         GsonUtil.java
 * Data Criação:    26 de out de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.util;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * GsonUtil é responsável por 
 * 
 * @author Felipe.Rosa
 */
public final class GsonUtil {

    private static Gson gson;

    /**
     * 
     */
    private GsonUtil() {

    }

    /**
     * Método responsável por
     * 
     * @return Gson
     * 
     */
    public static Gson getGson() {
        if (ObjectUtil.isNull(gson)) {
            // @formatter:off
            gson = new GsonBuilder()
                    .registerTypeAdapter(DateTimeDB.class, (JsonDeserializer<DateTimeDB>) (json, type, jsonSerializationContext) -> (json == null ? null : new DateTimeDB(json.getAsLong())))
                    .registerTypeAdapter(DateTimeDB.class, (JsonSerializer<Date>) (date, type, jsonSerializationContext) -> new JsonPrimitive(date.getTime()))
                    .create();
            // @formatter:on
        }

        return gson;
    }

}
