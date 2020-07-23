/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates
 * Arquivo:         TipoMensagemDelegate.java
 * Data Criação:    Aug 14, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.CategoriaMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.TipoMensagemDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNegocionException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoMensagemServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * TipoMensagemDelegate
 * 
 * @author samuell.ramos
 */
@SuppressWarnings("rawtypes")
public class TipoMensagemDelegate extends OperacionalCrudDelegate {

    private TipoMensagemServico tipoMensagemServico;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected TipoMensagemServico localizarServico() {
        if (this.tipoMensagemServico == null) {
            this.tipoMensagemServico = OperacionalServiceLocator.getInstance().localizarTipoMensagemServico();
        }
        return this.tipoMensagemServico;
    }

    /**
     * @param tipoMensagemVO
     * @return
     * @throws ComumException
     */
    public TipoMensagemDto obterTipoMensagem(TipoMensagemDDA tipoMensagemVO) throws ComumException {
        return localizarServico().obterTipoMensagem(tipoMensagemVO);
    }

    /**
     * @return
     * @throws ComumException
     */
    public TipoMensagemDto carregarListasTipoMensagem() throws ComumException {
        return localizarServico().carregarListasTipoMensagem();
    }

    /**
     * @return
     * @throws ComumException
     */
    public List<CategoriaMensagemDDA> listarCategoriaMensagemDDA() throws ComumException {
        return localizarServico().listarCategoriaMensagemDDA();
    }

    /**
     * @param tipoMensagemDDA
     * @throws OperacionalNegocionException
     * @throws ComumException void
     * 
     */
    public void incluirTipoMensagemDDA(TipoMensagemDDA tipoMensagemDDA) throws OperacionalNegocionException, ComumException {
        localizarServico().incluirTipoMensagemDDA(tipoMensagemDDA, false);
    }

    /**
     * @param tipoMensagemDDA
     * @throws OperacionalNegocionException
     * @throws ComumException void
     * 
     */
    public void alterarTipoMensagemDDA(TipoMensagemDDA tipoMensagemDDA) throws OperacionalNegocionException, ComumException {
        localizarServico().alterarTipoMensagemDDA(tipoMensagemDDA);
    }

    /**
     * @return
     * @throws ComumException List<TipoGradeHoraria>
     * 
     */
    public List<TipoGradeHoraria> listarTipoGradeHoraria() throws ComumException {
        return localizarServico().listarTipoGradeHoraria();
    }

    /**
     * ComumException
     * 
     * @param codTipoMensagemDDA
     * @throws ComumException
     * @throws OperacionalNegocionException void
     * 
     */
    public void removerTipoMensagem(String codTipoMensagemDDA) throws ComumException, OperacionalNegocionException {
        localizarServico().removerTipoMensagem(codTipoMensagemDDA);
    }

    /**
     * @param tipoMensagemDDA
     * @throws ComumException
     * @throws OperacionalNegocionException
     */
    public List<TipoMensagemDDA> listarTipoMensagemDDA() throws ComumException {
        return localizarServico().listarTipoMensagemDDA();
    }

}
