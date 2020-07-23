package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDATerceiroAut;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ArquivoExclusaoTerceiroAutorizadoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA122.ADDA122ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA122.GrupoADDA122TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA122.SISARQComplexType;

/**
 * ArquivoExclusaoTerceiroAutorizadoServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ ArquivoExclusaoTerceiroAutorizadoServicoLocal.class })
public class ArquivoExclusaoTerceiroAutorizadoServicoEJB extends IntegracaoCipServicoEJB implements ArquivoExclusaoTerceiroAutorizadoServicoLocal {

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
        return new SISARQComplexType(getADDA122(dao.listarMensagemDDATerceiroAutorizadoLogEnvioArquivo(idLogEnvioArquivoDDA)));
    }

    /**
     * Método responsável por montar o ADDA001
     * 
     * @param listaMensagemDDAPagador
     * @return
     * @throws ComumException
     * @throws ComumNegocioException ADDA001ComplexType
     * 
     */
    private ADDA122ComplexType getADDA122(List<MensagemDDATerceiroAut> listaMensagemDDATerceiroAut) throws ComumException {
        getLogger().debug("###### Qtde de Arquivos Enviados -> " + listaMensagemDDATerceiroAut.size());

        ADDA122ComplexType adda122 = new ADDA122ComplexType();

        for (MensagemDDATerceiroAut mensagemDDATerceiroAut : listaMensagemDDATerceiroAut) {
            adda122.getGrupoADDA122Tit().add(getGrupoADDA122(mensagemDDATerceiroAut));
        }

        return adda122;
    }

    /**
     * Método responsável por popular o getGrupoADDA122
     * 
     * @param mensagemTerceiroAutorizado
     * @return
     * @throws ComumException GrupoADDA001PagdrComplexType
     * 
     */
    private GrupoADDA122TitComplexType getGrupoADDA122(MensagemDDATerceiroAut mensagemTerceiroAutorizado) throws ComumException {
        GrupoADDA122TitComplexType terceiroAutorizado = new GrupoADDA122TitComplexType();
        terceiroAutorizado.setNumCtrlReqPart(mensagemTerceiroAutorizado.getId().toString());
        terceiroAutorizado.setISPBPartRecbdrPrincipal(Constantes.ISPB_BANCOOB);
        terceiroAutorizado.setISPBPartRecbdrAdmtd(Constantes.ISPB_BANCOOB);

        terceiroAutorizado.setNumIdentcTit(BigInteger.valueOf(mensagemTerceiroAutorizado.getNumIdentificadorBoletoCip()));

        terceiroAutorizado.setNumIdentcTerc(ObjectUtil.isNull(mensagemTerceiroAutorizado.getNumIdentificadorTerceiro()) ? null : BigInteger.valueOf(mensagemTerceiroAutorizado
                .getNumIdentificadorTerceiro()));
        terceiroAutorizado.setNumRefAtlCadTerc(ObjectUtil.isNull(mensagemTerceiroAutorizado.getNumRefAtualTerceiro()) ? null : BigInteger.valueOf(mensagemTerceiroAutorizado
                .getNumRefAtualTerceiro()));

        terceiroAutorizado.setTpPessoaPagdrAutzdr(mensagemTerceiroAutorizado.getCodTipoPessoaAutorizador());
        terceiroAutorizado.setCNPJCPFPagdrAutzdr(new BigInteger(mensagemTerceiroAutorizado.getNumCpfCnpjAutorizador()));

        terceiroAutorizado.setTpPessoaTerc(mensagemTerceiroAutorizado.getCodTipoPessoaTerceiro());
        terceiroAutorizado.setCNPJCPFTerc(new BigInteger(mensagemTerceiroAutorizado.getNumCpfCnpjTerceiro()));

        return terceiroAutorizado;
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
