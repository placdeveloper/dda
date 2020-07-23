package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ComumCrudServico;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ComumServico;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * Interface que define um contrato padrao para as operacoes de CRUD de todo o sistema Integracao Transacional
 * 
 * @author george.santos
 */
public interface IntegracaoTransacionalCrudServico<T extends SicoobDDAEntidade> extends ComumServico, ComumCrudServico<T> {

}