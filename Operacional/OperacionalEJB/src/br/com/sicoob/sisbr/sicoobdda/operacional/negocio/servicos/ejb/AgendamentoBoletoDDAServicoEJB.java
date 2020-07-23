package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.AgendamentoBoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.AgendamentoBoletoDDAServicoLocal;

/**
 * AgendamentoBoletoDDAServicoEJB é responsável por fornecer serviço de agendamento no DB2
 * 
 * @author Rodrigo.Neri
 */
@Stateless
@Local(AgendamentoBoletoDDAServicoLocal.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AgendamentoBoletoDDAServicoEJB extends OperacionalServicoEJB implements AgendamentoBoletoDDAServicoLocal {

    @Dao(entityManager = "em", fabrica = ComumDaoFactory.class)
    private ParametroDao parametroDAO;

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.OperacionalServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.AgendamentoBoletoDDAServico#obterParametrosAgendamento(int)
     */
    public AgendamentoBoletoDDADto obterParametrosAgendamento(int idInstituicao) throws ComumException, ComumNegocioException {
        debug("### Obtendo os parâmetros de agendamento...");
        debug("Parâmetro - idInstituicao: " + idInstituicao);

        if (ObjectUtil.isEmpty(idInstituicao)) {
            throw new ComumNegocioException("integracaocip.parametro.nao.informado", "id da instituição");
        }

        Boolean ddaAtivado = parametroDAO.obterValorBoolean(ParametroDDA.SICOOBDDA_ATIVADO, idInstituicao);
        debug("DDA ativado: " + ddaAtivado);

        int valorBoletoNaCIP = parametroDAO.obterValorInteger(ParametroDDA.VALOR_MIN_BOLETO_ENVIO_A_CIP, idInstituicao);
        debug("Valor boleto na CIP: " + valorBoletoNaCIP);

        AgendamentoBoletoDDADto dto = new AgendamentoBoletoDDADto();
        dto.setDdaAtivado(ddaAtivado);
        dto.setValorMinimoDDA(valorBoletoNaCIP);

        return dto;
    }

}
