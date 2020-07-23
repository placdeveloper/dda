package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.math.BigInteger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDATerceiroAut;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDATerceiroAut;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemExcluirTerceiroAutorizadoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.EscritorXMLUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA122.GrupoADDA122RR2TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor.ConversorTerceiroAutorizadoDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0122.DDA0122ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0122.DDA0122R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0122.DDA0122R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemExcluirTerceiroAutorizadoServicoEJB
 * 
 * @author Francisco.Marcio
 */
@Stateless
@Local({ MensagemExcluirTerceiroAutorizadoServicoLocal.class })
public class MensagemExcluirTerceiroAutorizadoServicoEJB extends IntegracaoCipServicoEJB implements MensagemExcluirTerceiroAutorizadoServicoLocal {

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
        getLogger().debug("########### Inicio processarMensagem(Long idMensagem) para DDA0122");
        MensagemDDATerceiroAut mensagem = dao.obterMensagemDDATerceiroAutorizadoAtualizaReferencias(idMensagem);

        DDA0122ComplexType dda0122 = getDDA0122ComplexType(mensagem);
        String xmlEnvio = EscritorXMLUtil.obterXmlEnvio(dda0122, mensagem.getId());

        getLogger().debug("########### Fim processarMensagem(Long idMensagem) para DDA0122");
        return xmlEnvio;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)
     */
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida retornoDDA) throws ComumException {
        BoletoDDATerceiroAut boletoTerceiroAut;
        if (retornoDDA.getCodMsg().equals(TipoMensagemDDA.DDA0122R1)) {
            boletoTerceiroAut = processarRetornoMensagem((DDA0122R1ComplexType) retornoDDA);
        } else if (retornoDDA.getCodMsg().equals(TipoMensagemDDA.DDA0122R2)) {
            boletoTerceiroAut = processarRetornoMensagem((DDA0122R2ComplexType) retornoDDA);
        } else {
            boletoTerceiroAut = processarRetornoGrupo((GrupoADDA122RR2TitComplexType) retornoDDA);
        }
        getEm().merge(boletoTerceiroAut);
    }

    /**
     * Método responsável por criar o objeto de envio para a CIP.
     * 
     * @param mensagem
     * @return
     * @throws ComumException DDA0122ComplexType
     * 
     */
    public DDA0122ComplexType getDDA0122ComplexType(MensagemDDATerceiroAut mensagem) throws ComumException {
        DDA0122ComplexType dda = new DDA0122ComplexType();
        dda.setCodMsg(TipoMensagemDDA.DDA0122);
        dda.setNumCtrlPart(mensagem.getId().toString());
        dda.setISPBPartRecbdrPrincipal(Constantes.ISPB_BANCOOB);
        dda.setISPBPartRecbdrAdmtd(Constantes.ISPB_BANCOOB);

        dda.setNumIdentcTit(BigInteger.valueOf(mensagem.getNumIdentificadorBoletoCip()));

        dda.setNumIdentcTerc(ObjectUtil.isNull(mensagem.getNumIdentificadorTerceiro()) ? null : BigInteger.valueOf(mensagem.getNumIdentificadorTerceiro()));
        dda.setNumRefAtlCadTerc(ObjectUtil.isNull(mensagem.getNumRefAtualTerceiro()) ? null : BigInteger.valueOf(mensagem.getNumRefAtualTerceiro()));

        dda.setTpPessoaPagdrAutzdr(mensagem.getCodTipoPessoaAutorizador());
        dda.setCNPJCPFPagdrAutzdr(new BigInteger(mensagem.getNumCpfCnpjAutorizador()));

        dda.setTpPessoaTerc(mensagem.getCodTipoPessoaTerceiro());
        dda.setCNPJCPFTerc(new BigInteger(mensagem.getNumCpfCnpjTerceiro()));

        dda.setDtMovto(DataCipUtil.dateTimeZeroHoraParaXMLGregorianCalendar(mensagem.getMensagemDDA().getDataMovimento()));

        return dda;
    }

    /**
     * @param dda0122R1
     * @return
     * @throws ComumException BoletoDDATerceiroAut
     * 
     */
    private BoletoDDATerceiroAut processarRetornoMensagem(DDA0122R1ComplexType dda0122R1) throws ComumException {
        BoletoDDATerceiroAut boletoTerceiroAut = dao.obterBoletoDDATerceiroAutorizado(dda0122R1.getNumIdentcTerc().longValue(), dda0122R1.getNumIdentcTit().longValue());
        return ConversorTerceiroAutorizadoDDA.converterMensagem122Exclusao(boletoTerceiroAut, dda0122R1);
    }

    /**
     * @param dda0122R2
     * @return
     * @throws ComumException BoletoDDATerceiroAut
     * 
     */
    private BoletoDDATerceiroAut processarRetornoMensagem(DDA0122R2ComplexType dda0122R2) throws ComumException {
        BoletoDDATerceiroAut boletoTerceiroAut = dao.obterBoletoDDATerceiroAutorizado(dda0122R2.getNumIdentcTerc().longValue(), dda0122R2.getNumIdentcTit().longValue());
        return ConversorTerceiroAutorizadoDDA.converterMensagem122Exclusao(boletoTerceiroAut, dda0122R2);
    }

    /**
     * @param adda122RR2
     * @return
     * @throws ComumException BoletoDDATerceiroAut
     * 
     */
    private BoletoDDATerceiroAut processarRetornoGrupo(GrupoADDA122RR2TitComplexType adda122RR2) throws ComumException {
        BoletoDDATerceiroAut boletoTerceiroAut = dao.obterBoletoDDATerceiroAutorizado(adda122RR2.getNumIdentcTerc().longValue(), adda122RR2.getNumIdentcTit().longValue());
        return ConversorTerceiroAutorizadoDDA.converterMensagem122Exclusao(boletoTerceiroAut, adda122RR2);
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
