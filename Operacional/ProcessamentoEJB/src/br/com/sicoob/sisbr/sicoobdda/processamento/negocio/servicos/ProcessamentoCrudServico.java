package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ComumCrudServico;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * Interface que define um contrato padrao para as operacoes de CRUD de todo o sistema Processamento
 * 
 * @author Sicoob
 */
public interface ProcessamentoCrudServico<T extends SicoobDDAEntidade> extends ProcessamentoServico, ComumCrudServico<T> {

}