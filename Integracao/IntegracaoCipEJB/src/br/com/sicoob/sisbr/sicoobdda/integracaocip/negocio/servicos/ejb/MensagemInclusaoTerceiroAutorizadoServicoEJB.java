package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.math.BigInteger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDATerceiroAut;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDATerceiroAut;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemInclusaoTerceiroAutorizadoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.EscritorXMLUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RR2TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RR3TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor.ConversorBoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor.ConversorTerceiroAutorizadoDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.DDA0121ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.DDA0121R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.DDA0121R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.DDA0121R3ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.GrupoDDA0121R3TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemInclusaoTerceiroAutorizadoServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ MensagemInclusaoTerceiroAutorizadoServicoLocal.class })
public class MensagemInclusaoTerceiroAutorizadoServicoEJB extends IntegracaoCipServicoEJB implements MensagemInclusaoTerceiroAutorizadoServicoLocal {

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
        getLogger().debug("########### Inicio processarMensagem(Long idMensagem) para DDA0121 - Participante solicita inclusão de terceiro autorizado");
        MensagemDDATerceiroAut mensagem = dao.obterMensagemDDATerceiroAutorizadoAtualizaReferencias(idMensagem);

        DDA0121ComplexType dda0121 = getDDA0121ComplexType(mensagem);
        String xmlEnvio = EscritorXMLUtil.obterXmlEnvio(dda0121, mensagem.getId());

        getLogger().debug("########### Fim processarMensagem(Long idMensagem) para DDA0121 - Participante solicita inclusão de terceiro autorizado");
        return xmlEnvio;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)
     */
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida retornoDDA) throws ComumException {
        if (retornoDDA.getCodMsg().equals(TipoMensagemDDA.DDA0121R1)) {
            processarRetornoMensagem((DDA0121R1ComplexType) retornoDDA);
        } else if (retornoDDA.getCodMsg().equals(TipoMensagemDDA.DDA0121R2)) {
            processarRetornoMensagem((DDA0121R2ComplexType) retornoDDA);
        } else if (retornoDDA.getCodMsg().equals(TipoMensagemDDA.DDA0121R3)) {
            processarRetornoMensagem(((DDA0121R3ComplexType) retornoDDA).getGrupoDDA0121R3Tit());
        } else if (retornoDDA.getCodMsg().equals(TipoMensagemDDA.ADDA121RR2)) {
            processarRetornoGrupo((GrupoADDA121RR2TitComplexType) retornoDDA);
        } else {
            processarRetornoGrupo((GrupoADDA121RR3TitComplexType) retornoDDA);
        }
    }

    /**
     * Método responsável por
     * 
     * @param retornoDDA
     * @throws ComumException void
     * 
     */
    private void processarRetornoMensagem(DDA0121R1ComplexType dda0121R1) throws ComumException {
        MensagemDDATerceiroAut mensagemDDA = dao.obterMensagemDDATerceiroAutorizado(dda0121R1.getIdMensagemOrigem());
        BoletoDDATerceiroAut boletoDDATerceiroAut = dao.obterBoletoDDATerceiroAutorizado(dda0121R1.getNumIdentcTerc().longValue(), mensagemDDA.getNumIdentificadorBoletoCip());
        if (ObjectUtil.isNull(boletoDDATerceiroAut)) {
            boletoDDATerceiroAut = ConversorTerceiroAutorizadoDDA.converter(dda0121R1, mensagemDDA);
            boletoDDATerceiroAut.setBoletoDDA(dao.obterBoletoDDA(mensagemDDA.getNumIdentificadorBoletoCip()));
            getEm().persist(boletoDDATerceiroAut);
        } else {
            boletoDDATerceiroAut = ConversorTerceiroAutorizadoDDA.merge(boletoDDATerceiroAut, dda0121R1.getNumIdent(), mensagemDDA.getNumRefAtualTerceiro());
            getEm().merge(boletoDDATerceiroAut);
        }
    }

    /**
     * Método responsável por
     * 
     * @param msg
     * @param boletoDDATerceiroAut
     * @throws ComumException void
     * 
     */
    private void processarRetornoMensagem(DDA0121R2ComplexType msg) throws ComumException {
        processarTerceiroAutorizadoR2(msg, msg.getNumIdentcTerc().longValue(), msg.getNumIdentcTit().longValue(), msg.getNumRefAtlCadTerc().longValue());
    }

    /**
     * Método responsável por
     * 
     * @param grupo
     * @param boletoDDATerceiroAut
     * @throws ComumException void
     * 
     */
    private void processarRetornoGrupo(GrupoADDA121RR2TitComplexType grupo) throws ComumException {
        processarTerceiroAutorizadoR2(grupo, grupo.getNumIdentcTerc().longValue(), grupo.getNumIdentcTit().longValue(), grupo.getNumRefAtlCadTerc().longValue());
    }

    /**
     * Método responsável por
     * 
     * @param conteudo
     * @throws ComumException void
     * 
     */
    private void processarTerceiroAutorizadoR2(ConteudoMsgRecebida conteudo, Long numIdentTerc, Long numIdentTit, Long numRefAtlCadTerc) throws ComumException {
        BoletoDDATerceiroAut boletoDDATerceiroAut = dao.obterBoletoDDATerceiroAutorizado(numIdentTerc, numIdentTit);
        if (ObjectUtil.isNull(boletoDDATerceiroAut)) {
            boletoDDATerceiroAut = ConversorTerceiroAutorizadoDDA.converter(conteudo);
            boletoDDATerceiroAut.setBoletoDDA(dao.obterBoletoDDA(numIdentTit));
            getEm().persist(boletoDDATerceiroAut);
        } else {
            boletoDDATerceiroAut = ConversorTerceiroAutorizadoDDA.merge(boletoDDATerceiroAut, numIdentTerc, numRefAtlCadTerc);
            getEm().merge(boletoDDATerceiroAut);
        }
    }

    /**
     * Método responsável por
     * 
     * Se o sequencial de atualização recebido na mensagem for menor que o do registro na base, gerar uma mensagem DDA0106 e NÃO processar a mensagem recebida.
     * 
     * Se o sequencial da mensagem recebida for igual, NÃO processar.
     * 
     * @param grupoDDA0121R3
     * @throws ComumException void
     * 
     */
    private void processarRetornoMensagem(GrupoDDA0121R3TitComplexType grupoDDA0121R3) throws ComumException {
        BoletoDDA boletoDDA = dao.obterBoletoDDALock(grupoDDA0121R3.getNumIdent());
        if (ConversorBoletoDDA.isNumSeqRecebidoMenorQueAtual(boletoDDA, grupoDDA0121R3.getNumSeqAtlzCadTit().longValue())) {
            incluirMensagemDDA0106(boletoDDA);
        } else if (ConversorBoletoDDA.isNumSeqRecebidoMaiorQueAtual(boletoDDA, grupoDDA0121R3.getNumSeqAtlzCadTit().longValue())) {
            processarBoletoR3(grupoDDA0121R3, boletoDDA);
        }
    }

    /**
     * Método responsável por
     * 
     * Se o sequencial de atualização recebido na mensagem for menor que o do registro na base, gerar uma mensagem DDA0106 e NÃO processar a mensagem recebida.
     * 
     * Se o sequencial da mensagem recebida for igual, NÃO processar.
     * 
     * @param grupoADDA0121R3
     * @throws ComumException void
     * 
     */
    private void processarRetornoGrupo(GrupoADDA121RR3TitComplexType grupoADDA0121R3) throws ComumException {
        BoletoDDA boletoDDA = dao.obterBoletoDDALock(grupoADDA0121R3.getNumIdent());
        if (ConversorBoletoDDA.isNumSeqRecebidoMenorQueAtual(boletoDDA, grupoADDA0121R3.getNumSeqAtlzCadTit().longValue())) {
            incluirMensagemDDA0106(boletoDDA);
        } else if (ConversorBoletoDDA.isNumSeqRecebidoMaiorQueAtual(boletoDDA, grupoADDA0121R3.getNumSeqAtlzCadTit().longValue())) {
            processarBoletoR3(grupoADDA0121R3, boletoDDA);
        }
    }

    /**
     * @param conteudo
     * @param boletoDDA
     * @throws ComumException void
     * 
     */
    private void processarBoletoR3(ConteudoMsgRecebida conteudo, BoletoDDA boletoDDA) throws ComumException {
        if (ObjectUtil.isNull(boletoDDA)) {
            boletoDDA = ConversorTerceiroAutorizadoDDA.converterBoletoDDAR3(conteudo);
            getEm().persist(boletoDDA);
        } else {
            removerRelacionamentoBoletoDDA(boletoDDA, dao);
            boletoDDA = ConversorTerceiroAutorizadoDDA.mergeBoletoDDAR3(conteudo, boletoDDA);
            getEm().merge(boletoDDA);
        }
    }

    /**
     * Método responsável por criar o objeto de envio para a CIP.
     * 
     * @param mensagem
     * @return
     * @throws ComumException DDA0121ComplexType
     * 
     */
    public DDA0121ComplexType getDDA0121ComplexType(MensagemDDATerceiroAut mensagem) throws ComumException {
        DDA0121ComplexType dda = new DDA0121ComplexType();
        dda.setCodMsg(TipoMensagemDDA.DDA0121);
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
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }

}
