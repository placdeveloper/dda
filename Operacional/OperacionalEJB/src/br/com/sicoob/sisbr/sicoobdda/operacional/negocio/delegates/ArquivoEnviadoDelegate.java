/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates
 * Arquivo:         ArquivoEnviadoDelegate.java
 * Data Criação:    Jul 05, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.ArquivoEnviadoDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ArquivoEnviadoServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * ArquivoEnviadoDelegate
 * 
 * @author samuell.ramos
 */
@SuppressWarnings("rawtypes")
public class ArquivoEnviadoDelegate extends OperacionalCrudDelegate{


    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ArquivoEnviadoServico localizarServico() {
    	return OperacionalServiceLocator.getInstance().localizarArquivoEnviadoServico();
    }
    
    /**
     * Método responsável por 
     * @return
     * @throws ComumException List<TipoMensagemDDA>
     * 
     */
    public List<TipoMensagemDDA> carregarListaTipoMensagem() throws ComumException{
        return localizarServico().carregarListaTipoMensagem();
    }
    
    /**
     * Método responsável por 
     * @param idLogEnvioArquivodda
     * @return
     * @throws ComumException ArquivoEnviadoDto
     * 
     */
    public ArquivoEnviadoDto obterArquivoEnviado(Long idLogEnvioArquivodda) throws ComumException{
        return localizarServico().obterArquivoEnviado(idLogEnvioArquivodda);
    }
    
    /**
     * Método responsável por 
     * @param logEnvioArquivoDDA
     * @throws BancoobException void
     * 
     */
    public void alterarArquivo(LogEnvioArquivoDDA logEnvioArquivoDDA) throws BancoobException{
         localizarServico().alterarArquivo(logEnvioArquivoDDA);
    }
    
    
}
