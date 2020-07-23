/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  integracaocip
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb
 * Arquivo:         MensagemBoletoPagamentoAbertoServicoEJB.java
 * Data Criação:    Apr 06, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemBoletoPagamentoAbertoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA127.GrupoADDA127TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor.ConversorBoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0127.DDA0127ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0127.GrupoDDA0127TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemBoletoPagamentoAbertoServicoEJB
 * 
 * @author rafael.silva
 */
@Stateless
@Local({ MensagemBoletoPagamentoAbertoServicoLocal.class })
public class MensagemBoletoPagamentoAbertoServicoEJB extends IntegracaoCipServicoEJB implements MensagemBoletoPagamentoAbertoServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private BoletoCipDao dao;

    private MensagemDDADelegate mensagemDDADelegate;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarMensagem(java.lang.Long) Método não utilizado
     *      pois é uma mensagem de distribuição.
     */
    public String processarMensagem(Long idMensagem) throws ComumException {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)
     */
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida retornoDDA) throws ComumException {
        getLogger().debug("########### Inicio processarRetornoMensagemDDA(Object retornoDDA) - DDA0127 ");

        if (retornoDDA.getCodMsg().equals(TipoMensagemDDA.ADDA127)) {
            processarGrupoBoleto((GrupoADDA127TitComplexType) retornoDDA);
        } else {
            processarListaGrupoBoleto(((DDA0127ComplexType) retornoDDA).getGrupoDDA0127Tit());
        }

    }

    /**
     * 
     * Se o sequencial de atualização recebido na mensagem for menor que o do registro na base, gerar uma mensagem DDA0106 e NÃO processar a mensagem recebida.
     * 
     * Se o sequencial da mensagem recebida for maior PROCESSAR
     * 
     * Se o sequencial da mensagem recebida for igual, NÃO processar.
     * 
     * @param grupoBoleto
     * @throws ComumException void
     * 
     */
    private void processarGrupoBoleto(GrupoADDA127TitComplexType grupoBoleto) throws ComumException {
        BoletoDDA boleto = dao.obterBoletoDDALock(grupoBoleto.getNumIdentcTit().longValue());

        if (ConversorBoletoDDA.isNumSeqRecebidoMenorQueAtual(boleto, grupoBoleto.getNumSeqAtlzCadTit())) {
            incluirMensagemDDA0106(boleto);
        } else if (ConversorBoletoDDA.isNumSeqRecebidoMaiorQueAtual(boleto, grupoBoleto.getNumSeqAtlzCadTit())) {
            getLogger().info("Numero sequencial MAIOR que o numero sequencial da base");
            processarMensagemADDA127(grupoBoleto, boleto);
        }

    }

    /**
     * Método responsável por verificar se o boleto existe na base. Se sim, ignora o xml recebido e posta uma mensagem DDA0106. Se não, chama o processamento do
     * xml que irá incluir o boleto novo.
     * 
     * A postagem da mensagem DDA0106 é necessária devido ao particionamento de mensagens. Nestes casos o xml pode estar incompleto, então a aplicação posta a
     * DDA0106 que vai retornar os dados completos do boleto.
     * 
     * @param grupoBoleto
     * @throws ComumException void
     * 
     */
    private void processarGrupoBoleto(GrupoDDA0127TitComplexType grupoBoleto) throws ComumException {
        BoletoDDA boleto = dao.obterBoletoDDALock(grupoBoleto.getNumIdent());

        if (!ObjectUtil.isNull(boleto) && !ObjectUtil.isEmpty(boleto.getId())) {
            incluirMensagemDDA0106(boleto);
        } else {
            processarMensagemDDA0127(grupoBoleto);
        }
    }

    /**
     * @param listaGrupoBoleto void
     * @throws ComumException
     * 
     */
    private void processarListaGrupoBoleto(List<GrupoDDA0127TitComplexType> listaGrupoBoleto) throws ComumException {
        for (GrupoDDA0127TitComplexType grupoBoleto : listaGrupoBoleto) {
            processarGrupoBoleto(grupoBoleto);
        }
    }

    /**
     * @param grupoBoleto
     * @param boleto
     * @throws ComumException void
     * 
     */
    private void processarMensagemDDA0127(GrupoDDA0127TitComplexType grupoBoleto) throws ComumException {
        incluirBoletoDDA(grupoBoleto);
    }

    /**
     * @param grupoBoleto
     * @param boleto
     * @throws ComumException void
     * 
     */
    private void processarMensagemADDA127(GrupoADDA127TitComplexType grupoBoleto, BoletoDDA boleto) throws ComumException {
        if (ObjectUtil.isNull(boleto) || ObjectUtil.isEmpty(boleto.getId())) {
            incluirBoletoDDA(grupoBoleto);
        } else {
            alterarBoletoDDA(boleto, grupoBoleto);
        }
    }

    /**
     * @param grupoBoleto
     * @throws ComumException void
     * 
     */
    private void incluirBoletoDDA(ConteudoMsgRecebida grupoBoleto) throws ComumException {
        getLogger().info("Inicio da conversão do Objeto do Boleto");
        BoletoDDA boletoDDA = ConversorBoletoDDA.converter(grupoBoleto);
        getEm().persist(boletoDDA);
        getLogger().info("Objeto Convertido com Sucesso");
    }

    /**
     * @param boletoDDA
     * @param grupoBoleto
     * @throws ComumException void
     * 
     */
    private void alterarBoletoDDA(BoletoDDA boletoDDA, ConteudoMsgRecebida grupoBoleto) throws ComumException {
        getLogger().info("Processando alteração do Boleto - " + boletoDDA.getId());
        removerRelacionamentoBoletoDDA(boletoDDA, dao);
        BoletoDDA boletoDDAAlterado = ConversorBoletoDDA.merge(grupoBoleto, boletoDDA);

        if (ObjectUtil.isNull(boletoDDAAlterado.getDataVencimento())) {
            boletoDDAAlterado.setDataVencimento(boletoDDA.getDataVencimento());
        }
        if (ObjectUtil.isNull(boletoDDAAlterado.getNumRefAtualCadBoleto())) {
            boletoDDAAlterado.setNumRefAtualCadBoleto(boletoDDA.getNumRefAtualCadBoleto());
        }

        getEm().merge(boletoDDAAlterado);
        getLogger().info("Objeto Convertido com Sucesso");
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }

    /**
     * @return the BoletoCipDao
     */
    public BoletoCipDao getDao() {
        return dao;
    }

    /**
     * @return o atributo mensagemDDADelegate
     */
    public MensagemDDADelegate getMensagemDDADelegate() {
        if (ObjectUtil.isNull(mensagemDDADelegate)) {
            mensagemDDADelegate = IntegracaoCipFabricaDelegates.getInstance().getMensagemDDADelegate();
        }
        return mensagemDDADelegate;
    }
}
