/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb
 * Arquivo:         ArquivoRecebidoServicoEJB.java
 * Data Criação:    Jan 27, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.ArquivoEnviadoDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.ArquivoEnviadoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ArquivoEnviadoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;

/**
 * ArquivoRecebidoServicoEJB
 * 
 * @author Samuell.Ramos
 */
@Stateless
@Local(ArquivoEnviadoServicoLocal.class)
public class ArquivoEnviadoServicoEJB extends OperacionalCrudServicoEJB<LogEnvioArquivoDDA> implements ArquivoEnviadoServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private ArquivoEnviadoDao dao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB#getDAO()
     */
    @Override
    protected ArquivoEnviadoDao getDAO() {
        return dao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ArquivoEnviadoServico#carregarListaTipoMensagem()
     */
    public List<TipoMensagemDDA> carregarListaTipoMensagem() throws ComumException {
        return dao.listarTipoMensagemAgenAdda();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ArquivoEnviadoServico#obterArquivoEnviado(java.lang.Long)
     */
    public ArquivoEnviadoDto obterArquivoEnviado(Long idLogEnvioArquivodda) throws ComumException {
        ArquivoEnviadoDto arquivoEnviadoDto = new ArquivoEnviadoDto();
        arquivoEnviadoDto.setLogEnvioArquivoDDA(dao.obterEnvioArquivoDDA(idLogEnvioArquivodda));
        arquivoEnviadoDto.setListaTipoMensagem(carregarListaTipoMensagem());
        return arquivoEnviadoDto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ArquivoEnviadoServico#alterarArquivo(LogEnvioArquivoDDA)
     */
    public void alterarArquivo(LogEnvioArquivoDDA logEnvioArquivoDDA) throws BancoobException {
        alterar(logEnvioArquivoDDA);
    }
}
