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
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ArquivoAlteracaoPagadorServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA005.ADDA005ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA005.GrupoADDA005AgrgdDDAComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA005.GrupoADDA005CtCliPagdrComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA005.GrupoADDA005PagdrComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA005.SISARQComplexType;

@Stateless
@Local({ ArquivoAlteracaoPagadorServicoLocal.class })
public class ArquivoAlteracaoPagadorServicoEJB extends IntegracaoCipServicoEJB implements ArquivoAlteracaoPagadorServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private PagadorCipDao dao;

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
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoArquivo#obterSISARQ(java.lang.Long)
     */
    public Object obterSISARQ(Long idLogEnvioArquivoDDA) throws ComumException {
        return new SISARQComplexType(getADDA005(dao.listarMensagemDDAPagadorLogEnvioArquivo(idLogEnvioArquivoDDA)));
    }

    /**
     * Método responsável por montar o ADDA005
     * 
     * @param listaMensagemDDAPagador
     * @return
     * @throws ComumException
     * @throws ComumNegocioException ADDA001ComplexType
     * 
     */
    private ADDA005ComplexType getADDA005(List<MensagemDDAPagador> listaMensagemDDAPagador) throws ComumException {
        getLogger().debug("###### Qtde de Arquivos Enviados -> " + listaMensagemDDAPagador.size());

        ADDA005ComplexType adda005 = new ADDA005ComplexType();

        for (MensagemDDAPagador mensagemDDAPagador : listaMensagemDDAPagador) {
            adda005.getGrupoADDA005Pagdr().add(getGrupoADDA005(mensagemDDAPagador));
        }

        return adda005;
    }

    /**
     * Método responsável por popular o GrupoADDA001
     * 
     * @param m
     * @return
     * @throws ComumException GrupoADDA001PagdrComplexType
     * 
     */
    private GrupoADDA005PagdrComplexType getGrupoADDA005(MensagemDDAPagador m) throws ComumException {
        GrupoADDA005PagdrComplexType pagador = new GrupoADDA005PagdrComplexType();
        pagador.setNumCtrlReqPart(m.getId().toString());
        pagador.setISPBPartRecbdrPrincipal(Constantes.ISPB_BANCOOB);
        pagador.setISPBPartRecbdrAdmtd(Constantes.ISPB_BANCOOB);

        pagador.setTpPessoaPagdr(m.getCodTipoPessoaPagador());
        pagador.setCNPJCPFPagdr(new BigInteger(m.getNumCpfCnpjPagador()));

        pagador.setNumIdentcPagdr(ObjectUtil.isNull(m.getNumIdentificaPagadorCip()) ? null : BigInteger.valueOf(m.getNumIdentificaPagadorCip()));
        pagador.setNumRefAtlCadCliPagdr(ObjectUtil.isNull(m.getNumRefAtualCadPagador()) ? null : BigInteger.valueOf(m.getNumRefAtualCadPagador()));

        for (MensagemDDAPagadorConta mensagemDDAPagadorConta : m.getListaMensagemDDAPagadorConta()) {
            GrupoADDA005CtCliPagdrComplexType grupoADDA = new GrupoADDA005CtCliPagdrComplexType();
            grupoADDA.setIndrManutCtCliPagdr(mensagemDDAPagadorConta.getCodTipoOperacao());
            grupoADDA.setTpAgCliPagdr(TipoAgenciaEnum.FISICA.getCodDominio());
            grupoADDA.setAgCliPagdr(new BigInteger(mensagemDDAPagadorConta.getNumAgencia().toString()));
            grupoADDA.setTpCtCliPagdr(TipoContaEnum.CONTA_CORRENTE.getCodDominio());
            grupoADDA.setCtCliPagdr(new BigInteger(mensagemDDAPagadorConta.getNumContaCorrente().toString()));
            grupoADDA.setDtAdesCliPagdrDDA(DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagemDDAPagadorConta.getDataHoraAdesao()));
            pagador.getGrupoADDA005CtCliPagdr().add(grupoADDA);
        }

        if (!ObjectUtil.isEmpty(m.getListaMensagemDDAPagadorAgregado())) {
            for (MensagemDDAPagadorAgregado mensagemDDAPagadorAgregado : m.getListaMensagemDDAPagadorAgregado()) {
                GrupoADDA005AgrgdDDAComplexType grupoADDA005AgrgdDDAComplexType = new GrupoADDA005AgrgdDDAComplexType();
                grupoADDA005AgrgdDDAComplexType.setIndrManutAgrgd(mensagemDDAPagadorAgregado.getCodTipoOperacao());
                grupoADDA005AgrgdDDAComplexType.setTpPessoaAgrgd(mensagemDDAPagadorAgregado.getCodTipoPessoaAgregado());
                grupoADDA005AgrgdDDAComplexType.setCNPJCPFAgrgd(new BigInteger(mensagemDDAPagadorAgregado.getNumCpfCnpjAgregado().toString()));
                pagador.getGrupoADDA005AgrgdDDA().add(grupoADDA005AgrgdDDAComplexType);
            }
        }

        return pagador;
    }
}
