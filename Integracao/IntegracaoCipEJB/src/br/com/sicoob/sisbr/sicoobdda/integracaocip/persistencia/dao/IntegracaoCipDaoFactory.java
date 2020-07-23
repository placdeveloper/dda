/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao
 * Arquivo:         IntegracaoCipDaoFactory.java
 * Data Criação:    Jul 6, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao;

import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl.ArquivoAlteraBoletoDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl.ArquivoCipDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl.ArquivoDecursoPrazoBaixaOperacionalDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl.ArquivoIncluirBoletoDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl.BeneficiarioCipDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl.BoletoCipDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl.IntegracaoCipDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl.IntegracaoCipLegadoDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl.MensagemBaixaEfetivaDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl.MensagemBaixaOperacionalDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl.MensagemDDADaoImpl;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl.PagadorCipDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl.ReplicarBeneficiarioLegadoDaoImpl;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl.ReplicarPagadorEletronicoLegadoDaoImpl;

/**
 * IntegracaoCipDaoFactory
 * 
 * @author Jesliel.Rocha
 */
public class IntegracaoCipDaoFactory extends ComumDaoFactory {

    private static IntegracaoCipDaoFactory factory;

    /**
     * @return IntegracaoCipDaoFactory
     */
    public static IntegracaoCipDaoFactory getInstance() {
        if (factory == null) {
            factory = new IntegracaoCipDaoFactory();
            SicoobLoggerPadrao.getInstance(IntegracaoCipDaoFactory.class).debug("************ Instanciou IntegracaoCipDaoFactory ****************");
        }
        return factory;
    }

    /**
     * @return ArquivoAlteraBoletoDao
     */
    public ArquivoAlteraBoletoDao criarArquivoAlteraBoletoDao() {
        return new ArquivoAlteraBoletoDaoImpl();
    }

    /**
     * @return ArquivoIncluirBoletoDao
     */
    public ArquivoIncluirBoletoDao criarArquivoIncluirBoletoDao() {
        return new ArquivoIncluirBoletoDaoImpl();
    }

    /**
     * @return ArquivoCipDao
     */
    public ArquivoCipDao criarArquivoCipDao() {
        return new ArquivoCipDaoImpl();
    }

    /**
     * @return ReplicarBeneficiarioLegadoDao
     */
    public ReplicarBeneficiarioLegadoDao criarReplicarBeneficiarioLegadoDao() {
        return new ReplicarBeneficiarioLegadoDaoImpl();
    }

    /**
     * @return IntegracaoCipDao
     */
    public IntegracaoCipDao criarIntegracaoCipDao() {
        return new IntegracaoCipDaoImpl();
    }

    /**
     * @return IntegracaoCipDao
     */
    public BoletoCipDao criarBoletoCipDao() {
        return new BoletoCipDaoImpl();
    }

    /**
     * @return IntegracaoCipDao
     */
    public PagadorCipDao criarPagadorCipDao() {
        return new PagadorCipDaoImpl();
    }

    /**
     * @return IntegracaoCipLegadoDao
     */
    public IntegracaoCipLegadoDao criarIntegracaoCipLegadoDao() {
        return new IntegracaoCipLegadoDaoImpl();
    }

    /**
     * @return MensagemDDADao
     */
    public MensagemDDADao criarMensagemDDADao() {
        return new MensagemDDADaoImpl();
    }

    /**
     * @return MensagemBaixaEfetivaDao
     */
    public MensagemBaixaEfetivaDao criarMensagemBaixaEfetivaDao() {
        return new MensagemBaixaEfetivaDaoImpl();
    }

    /**
     * @return MensagemBaixaOperacionalDao
     */
    public MensagemBaixaOperacionalDao criarMensagemBaixaOperacionalDao() {
        return new MensagemBaixaOperacionalDaoImpl();
    }

    /**
     * @return BeneficiarioCipDao
     */
    public BeneficiarioCipDao criarBeneficiarioCipDao() {
        return new BeneficiarioCipDaoImpl();
    }

    /**
     * @return ReplicarPagadorEletronicoLegadoDao
     */
    public ReplicarPagadorEletronicoLegadoDao criarReplicarPagadorEletronicoLegadoDao() {
        return new ReplicarPagadorEletronicoLegadoDaoImpl();
    }

    /**
     * @return ArquivoDecursoPrazoBaixaOperacionalDao
     */
    public ArquivoDecursoPrazoBaixaOperacionalDao criarArquivoDecursoPrazoBaixaOperacionalDao() {
        return new ArquivoDecursoPrazoBaixaOperacionalDaoImpl();
    }

}