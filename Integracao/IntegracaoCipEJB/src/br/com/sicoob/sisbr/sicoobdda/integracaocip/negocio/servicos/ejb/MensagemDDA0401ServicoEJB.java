/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb
 * Arquivo:         MensagemDDA0401ServicoEJB.java
 * Data Criação:    Jan 17, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemDDA0401ServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0401.DDA0401ComplexType;

/**
 * MensagemDDA0401ServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ MensagemDDA0401ServicoLocal.class })
public class MensagemDDA0401ServicoEJB extends IntegracaoCipServicoEJB implements MensagemDDA0401ServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = ComumDaoFactory.class)
    private ParametroDao parametroDAO;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDA0401Servico#incluir(java.lang.String,
     *      br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Integer)
     */
    public void incluir(String codTipoHorario, DateTimeDB dataReferencia, Integer numPrioridadeEnvio) throws BancoobException {
        debug("########### Inicio processarMensagem() para DDA0401");

        criarMensagemEnvioDDA(getDDA0401NPC(codTipoHorario, dataReferencia), numPrioridadeEnvio, getUsuarioLogado(), getIdInstituicaoLogado(), CanalEnum.RETAGUARDA.getId());

    }

    /**
     * Método responsável por criar o complexType
     * 
     * @param codTipoHorario
     * @param dataReferencia
     * @return
     * @throws ComumException
     */
    private DDA0401ComplexType getDDA0401NPC(String codTipoHorario, DateTimeDB dataReferencia) throws ComumException {
        DDA0401ComplexType dda0401 = new DDA0401ComplexType();
        dda0401.setCodMsg(TipoMensagemDDA.DDA0401);
        dda0401.setISPBPart(Constantes.ISPB_BANCOOB);
        dda0401.setTpHrio(codTipoHorario);
        dda0401.setDtRef(DataCipUtil.dateTimeParaXMLGregorianCalendar(dataReferencia));

        return dda0401;
    }
}
