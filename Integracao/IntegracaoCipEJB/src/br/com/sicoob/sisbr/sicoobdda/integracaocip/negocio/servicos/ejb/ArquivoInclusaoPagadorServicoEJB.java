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
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoAgenciaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoContaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagadorAgregado;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagadorConta;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ArquivoInclusaoPagadorServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA001.ADDA001ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA001.GrupoADDA001AgrgdDDAComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA001.GrupoADDA001CtCliPagdrComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA001.GrupoADDA001PagdrComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA001.SISARQComplexType;

/**
 * ArquivoInclusaoPagadorServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ ArquivoInclusaoPagadorServicoLocal.class })
public class ArquivoInclusaoPagadorServicoEJB extends IntegracaoCipServicoEJB implements ArquivoInclusaoPagadorServicoLocal {

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
        return new SISARQComplexType(getADDA001(dao.listarMensagemDDAPagadorLogEnvioArquivo(idLogEnvioArquivoDDA)));
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
    private ADDA001ComplexType getADDA001(List<MensagemDDAPagador> listaMensagemDDAPagador) throws ComumException {
        getLogger().debug("###### Qtde de Arquivos Enviados -> " + listaMensagemDDAPagador.size());

        ADDA001ComplexType adda001 = new ADDA001ComplexType();

        for (MensagemDDAPagador mensagemDDAPagador : listaMensagemDDAPagador) {
            adda001.getGrupoADDA001Pagdr().add(getGrupoADDA001(mensagemDDAPagador));
        }

        return adda001;
    }

    /**
     * Método responsável por popular o GrupoADDA001
     * 
     * @param m
     * @return
     * @throws ComumException GrupoADDA001PagdrComplexType
     * 
     */
    private GrupoADDA001PagdrComplexType getGrupoADDA001(MensagemDDAPagador m) throws ComumException {
        GrupoADDA001PagdrComplexType pagador = new GrupoADDA001PagdrComplexType();
        pagador.setNumCtrlReqPart(m.getId().toString());
        pagador.setISPBPartRecbdrPrincipal(Constantes.ISPB_BANCOOB);
        pagador.setISPBPartRecbdrAdmtd(Constantes.ISPB_BANCOOB);

        pagador.setTpPessoaPagdr(m.getCodTipoPessoaPagador());
        pagador.setCNPJCPFPagdr(new BigInteger(m.getNumCpfCnpjPagador()));

        pagador.setNumIdentcPagdr(ObjectUtil.isNull(m.getNumIdentificaPagadorCip()) ? null : BigInteger.valueOf(m.getNumIdentificaPagadorCip()));
        pagador.setNumRefAtlCadCliPagdr(ObjectUtil.isNull(m.getNumRefAtualCadPagador()) ? null : BigInteger.valueOf(m.getNumRefAtualCadPagador()));

        for (MensagemDDAPagadorConta mensagemDDAPagadorConta : m.getListaMensagemDDAPagadorConta()) {
            GrupoADDA001CtCliPagdrComplexType grupoADDA = new GrupoADDA001CtCliPagdrComplexType();
            grupoADDA.setTpAgCliPagdr(TipoAgenciaEnum.FISICA.getCodDominio());
            grupoADDA.setAgCliPagdr(new BigInteger(mensagemDDAPagadorConta.getNumAgencia().toString()));
            grupoADDA.setTpCtCliPagdr(TipoContaEnum.CONTA_CORRENTE.getCodDominio());
            grupoADDA.setCtCliPagdr(new BigInteger(mensagemDDAPagadorConta.getNumContaCorrente().toString()));
            grupoADDA.setDtAdesCliPagdrDDA(DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagemDDAPagadorConta.getDataHoraAdesao()));
            pagador.getGrupoADDA001CtCliPagdr().add(grupoADDA);
        }

        if (!ObjectUtil.isEmpty(m.getListaMensagemDDAPagadorAgregado())) {
            for (MensagemDDAPagadorAgregado mensagemDDAPagadorAgregado : m.getListaMensagemDDAPagadorAgregado()) {
                GrupoADDA001AgrgdDDAComplexType grupoADDA001AgrgdDDAComplexType = new GrupoADDA001AgrgdDDAComplexType();
                grupoADDA001AgrgdDDAComplexType.setTpPessoaAgrgd(mensagemDDAPagadorAgregado.getCodTipoPessoaAgregado());
                grupoADDA001AgrgdDDAComplexType.setCNPJCPFAgrgd(new BigInteger(mensagemDDAPagadorAgregado.getNumCpfCnpjAgregado().toString()));
                pagador.getGrupoADDA001AgrgdDDA().add(grupoADDA001AgrgdDDAComplexType);
            }
        }

        pagador.setIndrAdesCliPagdrDDA(m.getBolPagadorEletronico());

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
