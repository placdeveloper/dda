/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb
 * Arquivo:         MensagemAGEN001ServicoEJB.java
 * Data Criação:    May 5, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemDDAAGEN001ServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.AGEN001.AGEN001ComplexType;

/**
 * MensagemAGEN001ServicoEJB é responsável por
 * 
 * @author Adriano.Pinheiro
 */
@Stateless
@Local({ MensagemDDAAGEN001ServicoLocal.class })
public class MensagemDDAAGEN001ServicoEJB extends IntegracaoCipServicoEJB implements MensagemDDAAGEN001ServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private MensagemDDADao mensagemDDADao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDAAGEN001Servico#incluir()
     * 
     */
    public void incluir(String mensagemEco) throws BancoobException {
        if (!ObjectUtil.isEmpty(mensagemDDADao.obterQuantidadeMensagemPendente(TipoMensagemDDA.AGEN001))) {
            throw new ComumNegocioException("integracaocip.erro.existe.mensagem.pendente");
        }
        criarMensagemEnvioDDA(getAGEN001ComplexType(mensagemEco), null, getUsuarioLogado(), getIdInstituicaoLogado(), CanalEnum.RETAGUARDA.getId());
    }

    /**
     * Método responsável por
     * 
     * @param msgEco
     * @return AGEN001ComplexType
     * 
     */
    private AGEN001ComplexType getAGEN001ComplexType(String msgEco) {
        AGEN001ComplexType mensagem = new AGEN001ComplexType();

        mensagem.setISPBDestinatario(Constantes.ISPB_CIP_SITRAF);
        mensagem.setISPBEmissor(Constantes.ISPB_BANCOOB);
        mensagem.setMsgECO(msgEco);

        return mensagem;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }
}
