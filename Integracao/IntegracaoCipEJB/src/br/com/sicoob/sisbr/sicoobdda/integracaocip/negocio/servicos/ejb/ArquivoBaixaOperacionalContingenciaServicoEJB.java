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
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ArquivoBaixaOperacionalContingenciaServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemBaixaOperacionalDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA114.ADDA114ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA114.GrupoADDA114TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA114.SISARQComplexType;

/**
 * ArquivoBaixaOperacionalContingenciaServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ ArquivoBaixaOperacionalContingenciaServicoLocal.class })
public class ArquivoBaixaOperacionalContingenciaServicoEJB extends IntegracaoCipServicoEJB implements ArquivoBaixaOperacionalContingenciaServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private MensagemBaixaOperacionalDao dao;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private MensagemBaixaOperacionalDao mensagemDao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoArquivo#obterSISARQ(java.lang.Long)
     */
    public Object obterSISARQ(Long idLogEnvioArquivoDDA) throws ComumException {
        return new SISARQComplexType(getADDA114(mensagemDao.listarMensagemDDABaixaOperacionalContingencia(idLogEnvioArquivoDDA)));
    }

    /**
     * Método responsável por montar o ADDA001
     * 
     * @param listaMensagemDDABaixaOperacional
     * @return
     * @throws ComumException
     * 
     */
    private ADDA114ComplexType getADDA114(List<MensagemDDABaixaOperacional> listaMensagemDDABaixaOperacional) throws ComumException {
        getLogger().debug("###### Qtde de Arquivos Enviados -> " + listaMensagemDDABaixaOperacional.size());

        ADDA114ComplexType adda114 = new ADDA114ComplexType();

        for (MensagemDDABaixaOperacional mensagemDDABaixaOperacional : listaMensagemDDABaixaOperacional) {
            adda114.getGrupoADDA114Tit().add(getGrupoADDA114(mensagemDDABaixaOperacional));
        }

        return adda114;
    }

    /**
     * Método responsável por popular o GrupoADDA101
     * 
     * @param mensagemDDABaixaOperacional
     * @return
     * @throws ComumException GrupoADDA001PagdrComplexType
     * 
     */
    private GrupoADDA114TitComplexType getGrupoADDA114(MensagemDDABaixaOperacional mensagemDDABaixaOperacional) throws ComumException {
        GrupoADDA114TitComplexType boleto = new GrupoADDA114TitComplexType();
        boleto.setNumCtrlReqPart(mensagemDDABaixaOperacional.getId().toString());
        boleto.setISPBPartRecbdrAdmtd(Constantes.ISPB_BANCOOB);
        boleto.setISPBPartRecbdrPrincipal(Constantes.ISPB_BANCOOB);

        boleto.setTpBaixaOperac(mensagemDDABaixaOperacional.getCodTipoBaixaOperacional().toString());

        boleto.setISPBPartRecbdrBaixaOperac(Constantes.ISPB_BANCOOB);
        boleto.setCodPartRecbdrBaixaOperac(Constantes.BANCOOB);

        if (!ObjectUtil.isEmpty(mensagemDDABaixaOperacional.getNumCpfCnpjPortador())) {
            boleto.setTpPessoaPort(mensagemDDABaixaOperacional.getCodTipoPessoaPortador());
            boleto.setCNPJCPFPort(new BigInteger(mensagemDDABaixaOperacional.getNumCpfCnpjPortador()));
        }

        boleto.setDtHrProcBaixaOperac(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(mensagemDDABaixaOperacional.getMensagemDDA().getDataHoraCadastro()));
        boleto.setDtProcBaixaOperac(DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagemDDABaixaOperacional.getMensagemDDA().getDataHoraCadastro()));

        boleto.setVlrBaixaOperacTit(mensagemDDABaixaOperacional.getValorBaixa());
        boleto.setNumCodBarrasBaixaOperac(mensagemDDABaixaOperacional.getNumCodigoBarra());

        boleto.setCanPgto(BigInteger.valueOf(mensagemDDABaixaOperacional.getCodCanalPagamento()));
        boleto.setMeioPgto(BigInteger.valueOf(mensagemDDABaixaOperacional.getCodMeioPagamento()));

        boleto.setIndrOpContg(mensagemDDABaixaOperacional.getBolOperacaoContingencia());

        return boleto;
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
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoBaixaOperacionalContingenciaServico#processarRetornoMensagemDDA(long, long,
     *      long)
     */
    @Override
    public void processarRetornoMensagemDDA(long idLogRecebArq, long idLogDetRecebimentoInicial, long idLogDetRecebimentoFinal) {
        dao.gravarBaixaADDA114RET(idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal);
    }
}
