/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.cobrancabancaria.comum.negocio.servicos.ejb
 * Arquivo:         EmailServicoEJB.java
 * Data Criação:    Jun 15, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb;

import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.EMAIL_DESTINATARIO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.EMAIL_REMETENTE;

import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.util.email.Email;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interfaces.EmailServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * EmailServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local(EmailServicoLocal.class)
public class EmailServicoEJB extends ComumServicoEJB implements EmailServicoLocal {

    @PersistenceContext(unitName = "emCobrancaBancaria_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = ComumDaoFactory.class)
    private ParametroDao dao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.EmailServico#enviar(java.lang.Long, java.lang.Long, java.lang.String)
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void enviar(Long idParametroEmailHabilitado, Long idParametroAssunto, String mensagem) throws ComumException {
        Map<Long, ParametroDDA> mapaParametro = dao.obterMapaParametro(idParametroEmailHabilitado, EMAIL_REMETENTE, EMAIL_DESTINATARIO, idParametroAssunto);

        if (mapaParametro.get(idParametroEmailHabilitado).getValorParametro().equals(Constantes.STRING_NUMERO_1)) {
            String remetente = mapaParametro.get(EMAIL_REMETENTE).getValorParametro();
            String destinatario = mapaParametro.get(EMAIL_DESTINATARIO).getValorParametro();
            String assunto = mapaParametro.get(idParametroAssunto).getValorParametro();
            enviar(remetente, destinatario, assunto, mensagem);
        } else {
            debug("E-mail não habilitado para esse serviço.");
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.EmailServico#enviar(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public void enviar(String remetente, String destinatario, String assunto, String mensagem) throws ComumException {
        try {
            Email email = new Email(remetente, destinatario, assunto, mensagem);
            email.enviar();
        } catch (BancoobRuntimeException e) {
            getLogger().erro(e, e.getMessage());
        }
    }

}
