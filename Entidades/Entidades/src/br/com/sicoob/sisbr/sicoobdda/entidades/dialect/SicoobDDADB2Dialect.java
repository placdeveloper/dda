package br.com.sicoob.sisbr.sicoobdda.entidades.dialect;

import org.hibernate.dialect.DB2Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.DateType;

// TODO: Auto-generated Javadoc
/**
 * SicoobDDADB2Dialect � respons�vel por.
 *
 * @author Rodrigo.Neri
 */
public class SicoobDDADB2Dialect extends DB2Dialect {

    /**
     * Instantiates a new sicoob DDADB 2 dialect.
     */
    public SicoobDDADB2Dialect() {
        super();

        // Fun��o que adiciona dias a uma data espec�fica
        registerFunction("date_add_days", new SQLFunctionTemplate(DateType.INSTANCE, "(?1 + ?2 DAYS)"));
    }

}
