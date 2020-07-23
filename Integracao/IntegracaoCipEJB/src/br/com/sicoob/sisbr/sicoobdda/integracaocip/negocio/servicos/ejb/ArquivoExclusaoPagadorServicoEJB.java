package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ArquivoExclusaoPagadorServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA006.ADDA006ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA006.GrupoADDA006PagdrComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA006.SISARQComplexType;

/**
 * ArquivoExclusaoPagadorServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ ArquivoExclusaoPagadorServicoLocal.class })
public class ArquivoExclusaoPagadorServicoEJB extends IntegracaoCipServicoEJB implements ArquivoExclusaoPagadorServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private PagadorCipDao dao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoArquivo#obterSISARQ(java.lang.Long)
     */
    public Object obterSISARQ(Long idLogEnvioArquivoDDA) throws ComumException {
        return new SISARQComplexType(getADDA006(dao.listarMensagemDDAPagadorLogEnvioArquivo(idLogEnvioArquivoDDA)));
    }

    /**
     * Método responsável por montar o ADDA006
     * 
     * @param listaMensagemDDAPagador
     * @return
     * @throws ComumException
     * 
     */
    private ADDA006ComplexType getADDA006(List<MensagemDDAPagador> listaMensagemDDAPagador) throws ComumException {
        getLogger().debug("###### Qtde de Arquivos Enviados -> " + listaMensagemDDAPagador.size());

        ADDA006ComplexType adda006 = new ADDA006ComplexType();

        for (MensagemDDAPagador mensagemDDAPagador : listaMensagemDDAPagador) {
            adda006.getGrupoADDA006Pagdr().add(getGrupoADDA006(mensagemDDAPagador));
        }

        return adda006;
    }

    /**
     * Método responsável por popular o GrupoADDA001
     * 
     * @param mensagemDDAPagador
     * @return
     * @throws ComumException GrupoADDA001PagdrComplexType
     * 
     */
    private GrupoADDA006PagdrComplexType getGrupoADDA006(MensagemDDAPagador mensagemDDAPagador) throws ComumException {
        GrupoADDA006PagdrComplexType pagador = new GrupoADDA006PagdrComplexType();
        pagador.setNumCtrlReqPart(mensagemDDAPagador.getId().toString());
        pagador.setISPBPartRecbdrPrincipal(Constantes.ISPB_BANCOOB);
        pagador.setISPBPartRecbdrAdmtd(Constantes.ISPB_BANCOOB);

        pagador.setTpPessoaPagdr(mensagemDDAPagador.getCodTipoPessoaPagador());
        pagador.setCNPJCPFPagdr(new BigInteger(mensagemDDAPagador.getNumCpfCnpjPagador()));

        pagador.setNumIdentcPagdr(ObjectUtil.isNull(mensagemDDAPagador.getNumIdentificaPagadorCip()) ? null : BigInteger.valueOf(mensagemDDAPagador.getNumIdentificaPagadorCip()));
        pagador.setNumRefAtlCadCliPagdr(ObjectUtil.isNull(mensagemDDAPagador.getNumRefAtualCadPagador()) ? null : BigInteger.valueOf(mensagemDDAPagador.getNumRefAtualCadPagador()));

        return pagador;
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
