/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.servicos.ejb
 * Arquivo:         ComumServicoEJB.java
 * Data Criação:    27/09/2012
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb;

import javax.transaction.SystemException;

import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.negocio.servicos.ejb.BancoobServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ComumServico;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;

/**
 * ComumServicoEJB é responsável por
 * 
 * @author Fabricio.Brandao
 */
public class ComumServicoEJB extends BancoobServicoEJB implements ComumServico {

    /**
     * @return o atributo usuarioLogado
     */
    protected String getUsuarioLogado() {
        if (!ObjectUtil.isNull(InformacoesUsuario.getInstance())) {
            return InformacoesUsuario.getInstance().getLogin();
        }
        return null;
    }

    /**
     * @return o atributo idInstituicaoLogado
     */
    protected Integer getIdInstituicaoLogado() {
        if (!ObjectUtil.isNull(InformacoesUsuario.getInstance()) && !ObjectUtil.isNull(InformacoesUsuario.getInstance().getIdInstituicao())) {
            return Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao());
        }
        return null;
    }

    /**
     * Método responsável por executar um rollback em transações gerenciadas manualmente (TransactionManagementType.BEAN) void
     * 
     */
    protected void rollback() {
        try {
            getSessionContext().getUserTransaction().rollback();
        } catch (IllegalStateException e) {
            getLogger().erro(e, e.getMessage());
        } catch (SecurityException e) {
            getLogger().erro(e, e.getMessage());
        } catch (SystemException e) {
            getLogger().erro(e, e.getMessage());
        }
    }
}
