package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaOper;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemDecursoBaixaOperacionalServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA117.GrupoADDA117TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

@Stateless
@Local({ MensagemDecursoBaixaOperacionalServicoLocal.class })
public class MensagemDecursoBaixaOperacionalServicoEJB extends IntegracaoCipServicoEJB implements MensagemDecursoBaixaOperacionalServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private BoletoCipDao dao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarMensagem(java.lang.Long)
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
        getLogger().debug("########### Inicio processarRetornoMensagemDDA(Object retornoDDA) - ADDA0117");

        BoletoDDABaixaOper boletoDDABaixaOper = getDao().obterBoletoDDABaixaOperacional(retornoDDA.getNumIdent());

        if (!ObjectUtil.isNull(boletoDDABaixaOper) && !ObjectUtil.isNull(boletoDDABaixaOper.getId())) {
            getDao().atualizarSituacaoBoletoDDABaixaOper(retornoDDA.getNumIdent(), SituacaoBaixaOperacional.BAIXADA_POR_NAO_CONFIRMACAO_EFETIVA);

            GrupoADDA117TitComplexType grupo = (GrupoADDA117TitComplexType) retornoDDA;
            getDao().atualizarSituacaoBoletoPagamentoDDA(grupo.getSitTitPgto().toString(), grupo.getNumRefAtlCadTit(), grupo.getNumIdentcTit().toString());
        } else {
            throw new ComumException("integracaocip.erro.integracao.cancelamento.baixa.operacional");
        }

        getLogger().debug("########### Fim processarRetornoMensagemDDA(Object retornoDDA) - ADDA0117");
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }

    /**
     * Define o atributo em
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }

    /**
     * @return the integracaoCipDao
     */
    public BoletoCipDao getDao() {
        return dao;
    }

    /**
     * Define o atributo dao
     */
    public void setDao(BoletoCipDao dao) {
        this.dao = dao;
    }
}
