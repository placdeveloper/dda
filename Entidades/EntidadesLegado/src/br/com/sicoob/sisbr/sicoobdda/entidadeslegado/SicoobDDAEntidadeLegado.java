package br.com.sicoob.sisbr.sicoobdda.entidadeslegado;

import java.io.Serializable;

import br.com.bancoob.negocio.entidades.BancoobEntidade;

/**
 * Classe base para todas as entidades do projeto.
 */
public abstract class SicoobDDAEntidadeLegado<ID extends Serializable> extends BancoobEntidade {

    private static final long serialVersionUID = -8505094847594683103L;

    /**
     * Construtor
     */
    protected SicoobDDAEntidadeLegado() {
    }

    /**
     * Método responsável por getId
     * 
     * @return Number
     * 
     */
    public abstract ID getIdSQL();

}
