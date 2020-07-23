package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAConsultaBoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ArquivoConsultaBoletoPagamentoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA110.ADDA110ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA110.GrupoADDA110TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA110.SISARQComplexType;

/**
 * ArquivoConsultaBoletoPagamentoServicoEJB é responsável por
 * 
 * @author felipe.rosa
 */
@Stateless
@Local({ ArquivoConsultaBoletoPagamentoServicoLocal.class })
public class ArquivoConsultaBoletoPagamentoServicoEJB extends IntegracaoCipServicoEJB implements ArquivoConsultaBoletoPagamentoServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private BoletoCipDao dao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoArquivo#obterSISARQ(java.lang.Long)
     */
    public Object obterSISARQ(Long idLogEnvioArquivoDDA) throws ComumException {
        getLogger().debug("#### Inicio do metodo: obterSISARQ da classe: ArquivoConsultaBoletoPagamento");
        return new SISARQComplexType(getADDA110(dao.listarMensagemDDAConsultaBoleto(idLogEnvioArquivoDDA)));
    }

    /**
     * Método responsável por montar o ADDA118ComplexType
     * 
     * @param listaMensagemDDAConsultaBoleto
     * @return
     * @throws ComumException
     * 
     */
    private ADDA110ComplexType getADDA110(List<MensagemDDAConsultaBoleto> listaMensagemDDAConsultaBoleto) throws ComumException {
        getLogger().debug("#### Inicio do metodo: getADDA110 da classe: ArquivoConsultaBoletoPagamento");
        getLogger().debug("###### Qtde de Arquivos Enviados -> " + listaMensagemDDAConsultaBoleto.size());

        ADDA110ComplexType adda110 = new ADDA110ComplexType();

        for (MensagemDDAConsultaBoleto mensagemDDAConsultaBoleto : listaMensagemDDAConsultaBoleto) {
            adda110.getGrupoADDA110Tit().add(getGrupoADDA110(mensagemDDAConsultaBoleto));
        }
        getLogger().debug("#### Fim do metodo: getADDA110 da classe: ArquivoConsultaBoletoPagamento");
        return adda110;
    }

    /**
     * Método responsável por popular o getGrupoADDA110
     * 
     * @param mensagemDDABoleto
     * @return
     * @throws ComumException GrupoADDA110TitComplexType
     * 
     */
    private GrupoADDA110TitComplexType getGrupoADDA110(MensagemDDAConsultaBoleto mensagemDDAConsultaBoleto) throws ComumException {
        GrupoADDA110TitComplexType grupoAdda110 = new GrupoADDA110TitComplexType();
        grupoAdda110.setNumCtrlReqPart(mensagemDDAConsultaBoleto.getId().toString());
        grupoAdda110.setISPBPartRecbdrAdmtd(Constantes.ISPB_BANCOOB);
        grupoAdda110.setISPBPartRecbdrPrincipal(Constantes.ISPB_BANCOOB);
        grupoAdda110.setNumCodBarras(mensagemDDAConsultaBoleto.getNumCodigoBarra());
        return grupoAdda110;
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

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoConsultaBoletoPagamentoServico#processarArquivoRetornoConsultaBoletoDDA(long,
     *      long, long)
     */
    public void processarArquivoRetornoConsultaBoletoDDA(long idLogRecebArq, long idLogDetRecebimentoInicial, long idLogDetRecebimentoFinal) {
        getLogger().debug("###### Processando consulta a boletos...");
        dao.processarArquivoRetornoConsultaBoletoDDA(idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal);
        getLogger().debug("######  Registros ADDA110RET processados.");

    }
}
