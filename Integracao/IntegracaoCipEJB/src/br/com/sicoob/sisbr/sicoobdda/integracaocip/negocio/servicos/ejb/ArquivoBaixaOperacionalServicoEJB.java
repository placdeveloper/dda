package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ArquivoBaixaOperacionalServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemBaixaOperacionalDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA108.ADDA108ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA108.GrupoADDA108TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA108.SISARQComplexType;

/**
 * ArquivoBaixaOperacionalServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ ArquivoBaixaOperacionalServicoLocal.class })
public class ArquivoBaixaOperacionalServicoEJB extends IntegracaoCipServicoEJB implements ArquivoBaixaOperacionalServicoLocal {

    private static final int MENOS_CINCO_MINUTOS = -5;

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private MensagemBaixaOperacionalDao dao;

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
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoBaixaOperacionalServico#processarArquivoRetornoBaixaOperacionalDDA(long, long, long)
     */
    public void processarArquivoRetornoBaixaOperacionalDDA(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal) {
        dao.gravarBaixaADDA108RET(idLogRecebArq, idLogDetArqInicial, idLogDetArqFinal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoArquivo#obterSISARQ(java.lang.Long)
     */
    public Object obterSISARQ(Long idLogEnvioArquivoDDA) throws ComumException {
        return new SISARQComplexType(getADDA108(dao.listarMensagemDDABaixaOperacional(idLogEnvioArquivoDDA)));
    }

    /**
     * Método responsável por montar o ADDA108
     * 
     * @param listaMensagemDDABaixaOperacional
     * @return
     * @throws ComumException
     * 
     */
    private ADDA108ComplexType getADDA108(List<MensagemDDABaixaOperacional> listaMensagemDDABaixaOperacional) throws ComumException {
        getLogger().debug("###### Qtde de Arquivos Enviados -> " + listaMensagemDDABaixaOperacional.size());

        ADDA108ComplexType adda08 = new ADDA108ComplexType();

        for (MensagemDDABaixaOperacional mensagemDDABaixaOperacional : listaMensagemDDABaixaOperacional) {
            adda08.getGrupoADDA108Tit().add(getGrupoADDA108(mensagemDDABaixaOperacional));
        }

        return adda08;
    }

    /**
     * Método responsável por popular o GrupoADDA101
     * 
     * @param mensagem
     * @return
     * @throws ComumException GrupoADDA001PagdrComplexType
     * 
     */
    private GrupoADDA108TitComplexType getGrupoADDA108(MensagemDDABaixaOperacional mensagem) throws ComumException {
        GrupoADDA108TitComplexType grupoDDA = new GrupoADDA108TitComplexType();
        grupoDDA.setNumCtrlReqPart(mensagem.getId().toString());
        grupoDDA.setISPBPartRecbdrAdmtd(Constantes.ISPB_BANCOOB);
        grupoDDA.setISPBPartRecbdrPrincipal(Constantes.ISPB_BANCOOB);

        grupoDDA.setNumIdentcTit(ObjectUtil.isEmpty(mensagem.getNumIdentificadorBoleto()) ? null : BigInteger.valueOf(mensagem.getNumIdentificadorBoleto()));

        grupoDDA.setNumRefCadTitBaixaOperac(ObjectUtil.isEmpty(mensagem.getNumRefAtualCadBoleto()) ? null : BigInteger.valueOf(mensagem.getNumRefAtualCadBoleto()));
        grupoDDA.setNumRefAtlBaixaOperac(ObjectUtil.isEmpty(mensagem.getNumRefAtualBaixaOper()) ? null : BigInteger.valueOf(mensagem.getNumRefAtualBaixaOper()));

        grupoDDA.setTpBaixaOperac(mensagem.getCodTipoBaixaOperacional().toString());

        grupoDDA.setISPBPartRecbdrBaixaOperac(Constantes.ISPB_BANCOOB);
        grupoDDA.setCodPartRecbdrBaixaOperac(Constantes.BANCOOB);

        if (!ObjectUtil.isEmpty(mensagem.getNumCpfCnpjPortador())) {
            grupoDDA.setTpPessoaPort(mensagem.getCodTipoPessoaPortador());
            grupoDDA.setCNPJCPFPort(new BigInteger(mensagem.getNumCpfCnpjPortador()));
        }

        grupoDDA.setDtHrProcBaixaOperac(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(ajustarDataHoraCadastro(mensagem.getMensagemDDA().getDataHoraCadastro())));
        grupoDDA.setDtProcBaixaOperac(DataCipUtil.dateTimeParaXMLGregorianCalendar(ajustarDataHoraCadastro(mensagem.getMensagemDDA().getDataHoraCadastro())));

        grupoDDA.setVlrBaixaOperacTit(mensagem.getValorBaixa());
        grupoDDA.setNumCodBarrasBaixaOperac(mensagem.getNumCodigoBarra());
        grupoDDA.setCanPgto(BigInteger.valueOf(mensagem.getCodCanalPagamento()));
        grupoDDA.setMeioPgto(BigInteger.valueOf(mensagem.getCodMeioPagamento()));
        grupoDDA.setIndrOpContg(mensagem.getBolOperacaoContingencia());

        return grupoDDA;
    }

    /**
     * @param dataHoraCadastro
     * @return
     * @throws ComumException Date
     * 
     */
    private Date ajustarDataHoraCadastro(DateTimeDB dataHoraCadastro) throws ComumException {
        return DateUtil.incrementarData(dataHoraCadastro, Calendar.MINUTE, MENOS_CINCO_MINUTOS);
    }
}
