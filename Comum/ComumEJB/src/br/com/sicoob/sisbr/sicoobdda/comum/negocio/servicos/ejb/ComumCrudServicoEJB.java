/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.servicos.ejb
 * Arquivo:         ComumCrudServicoEJB.java
 * Data Cria��o:    26/09/2012
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb;

import javax.annotation.PostConstruct;
import javax.transaction.SystemException;

import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.servicos.ejb.BancoobCrudServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.PesquisaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumCrudDaoIF;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * ComumCrudServicoEJB � respons�vel por
 * 
 */
public abstract class ComumCrudServicoEJB<T extends SicoobDDAEntidade> extends BancoobCrudServicoEJB<T> {

    protected abstract ComumCrudDaoIF<T> getDAO();

    /**
     * M�todo respons�vel por realizar a pesquisa, e, se informados os ids do par�metro e da institui��o, verificar� o limite de consulta dispon�vel.
     * 
     * @param classe
     * @param criterios
     * @param pe
     * @param idParametro
     * @param idInstituicao
     * @return ConsultaDto<E>
     * @throws ComumException
     * @throws ComumNegocioException
     */
    public <E extends SicoobDDAEntidade> ConsultaDto<E> pesquisar(Class<E> classe, ConsultaDto<E> criterios, PesquisaEnum pe, long idParametro, int idInstituicao)
            throws ComumNegocioException, ComumException {
        return getDAO().pesquisar(classe, criterios, pe, idParametro, idInstituicao);
    }

    /**
     * M�todo respons�vel por retornar o usu�rio logado Este metodo esta replicado na classe ComumServicoEJB. Qualquer ajuste deve ser aplicado nas duas
     * classes.
     * 
     * @return UsuarioBancoob
     * 
     */
    public UsuarioBancoob getUsuario() {
        UsuarioBancoob usuario = new UsuarioBancoob();
        usuario.setCooperativa(InformacoesUsuario.getInstance().getCooperativa());
        usuario.setIdInstituicao(InformacoesUsuario.getInstance().getIdInstituicao());
        usuario.setIdUnidadeInstituicao(InformacoesUsuario.getInstance().getIdUnidadeInstituicao());
        usuario.setLogin(InformacoesUsuario.getInstance().getLogin());
        usuario.setPac(InformacoesUsuario.getInstance().getPac());
        return usuario;
    }

    /**
     * 
     * 
     * @return o atributo usuarioLogado
     */
    protected String getUsuarioLogado() {
        if (!ObjectUtil.isNull(InformacoesUsuario.getInstance())) {
            return InformacoesUsuario.getInstance().getLogin();
        }
        return null;
    }

    /**
     * M�todo respons�vel por
     * 
     * @return Integer
     * 
     */
    protected Integer getIdInstituicaoLogado() {
        if (!ObjectUtil.isNull(InformacoesUsuario.getInstance()) && !ObjectUtil.isNull(InformacoesUsuario.getInstance().getIdInstituicao())) {
            return Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao());
        }
        return null;
    }

    /*
     * M�todo respons�vel por executar um rollback em transa��es gerenciadas manualmente (TransactionManagementType.BEAN) void
     */
    protected void rollback() {
        try {
            getSessionContext().getUserTransaction().rollback();
        } catch (IllegalStateException | SecurityException | SystemException e) {
            getLogger().erro(e, e.getMessage());
        }
    }
    
    
    //FIXME Adriano retirar este m�todo quando resolver a quest�o da execu��o da inje��o pela anota��o @DAO
    @PostConstruct
	@Override
	protected void postConstruct() {
		super.postConstruct();
	}
    
}
