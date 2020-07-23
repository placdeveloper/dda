/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb
 * Arquivo:         RequisitarArquivoServicoEJB.java
 * Data Criação:    May 27, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CadastroGen0014Dto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSolicitacaoArquivoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSolicitacaoArquivoBoletoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSolicitacaoArquivoPagadorEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSolicitacaoGEN0014Enum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoTransmissaoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemGEN0014ServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.EscritorXMLUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.GEN0014.GEN0014ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.GEN0014.GrupoGen14Beneficiario;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.GEN0014.GrupoGen14Boleto;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.GEN0014.GrupoGen14Pagador;
import br.com.sicoob.tipos.DateTime;

/**
 * RequisitarArquivoServicoEJB
 * 
 * @author Rafael.Silva
 */
@Stateless
@Local({ MensagemGEN0014ServicoLocal.class })
public class MensagemGEN0014ServicoEJB extends IntegracaoCipServicoEJB implements MensagemGEN0014ServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.IntegracaoCipServicoEJB.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemGEN0014Servico#incluir(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CadastroGen0014Dto,
     *      br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSolicitacaoGEN0014Enum)
     */
    public void incluir(CadastroGen0014Dto dto, TipoSolicitacaoGEN0014Enum tipoSolicitacao) throws BancoobException {
        switch (tipoSolicitacao) {
        case BENEFICIARIO:
            processarMensagem(TipoSolicitacaoArquivoBeneficiarioEnum.getBy(dto.getCodTipoSolBeneficiario()), SituacaoBeneficiarioEnum.getBy(dto.getCodSituacaoBeneficiario()));
            break;
        case PAGADOR:
            processarMensagem(TipoSolicitacaoArquivoPagadorEnum.getBy(dto.getCodTipoSolPagador()));
            break;
        case TITULO:
            processarMensagem(TipoSolicitacaoArquivoBoletoEnum.getBy(dto.getCodTipoSolBoleto()), dto.getCodTipoBoletoConsultado());
            break;
        default:
            throw new ComumNegocioException("integracaocip.erro.solicitacao.invalida");
        }
    }

    /**
     * Método responsável por processar a mensagem
     * 
     * @param tipoSolicitacaoArquivoBeneficiario
     * @param situacaoBeneficiarioEnum
     * @throws BancoobException
     */
    public void processarMensagem(TipoSolicitacaoArquivoBeneficiarioEnum tipoSolicitacaoArquivoBeneficiario, SituacaoBeneficiarioEnum situacaoBeneficiarioEnum)
            throws BancoobException {
        getLogger().debug("########### Inicio processarMensagem() para GEN0014 - Participante requisita Arquivo - Beneficiario");

        criarMensagemEnvioDDA(getGEN0014Beneficiario(tipoSolicitacaoArquivoBeneficiario, situacaoBeneficiarioEnum), Constantes.INTEGER_ZERO, getUsuarioLogado(),
                getIdInstituicaoLogado(), CanalEnum.RETAGUARDA.getId());
    }

    /**
     * Método responsável por processar a mensagem
     * 
     * @param tipoSolicitacaoPagador
     * @param numPrioridadeEnvio
     * @throws BancoobException
     */
    private void processarMensagem(TipoSolicitacaoArquivoPagadorEnum tipoSolicitacaoPagador) throws BancoobException {
        getLogger().debug("########### INICIO processarMensagem() para GEN0014 - Participante requisita Arquivo - Pagador");

        criarMensagemEnvioDDA(getGEN0014Pagador(tipoSolicitacaoPagador), Constantes.INTEGER_ZERO, getUsuarioLogado(), getIdInstituicaoLogado(), CanalEnum.RETAGUARDA.getId());
    }

    /**
     * Método responsável por processar a mensagem
     * 
     * @param tipoSolicitacaoBoleto
     * @param codTipoBoletoConsultado
     * @param numPrioridadeEnvio
     * @throws BancoobException
     */
    private void processarMensagem(TipoSolicitacaoArquivoBoletoEnum tipoSolicitacaoBoleto, String codTipoBoletoConsultado) throws BancoobException {
        getLogger().debug("########### Inicio processarMensagem() para GEN0014 - Participante requisita Arquivo - Boleto");

        criarMensagemEnvioDDA(getGEN0014Boleto(tipoSolicitacaoBoleto, codTipoBoletoConsultado), Constantes.INTEGER_ZERO, getUsuarioLogado(), getIdInstituicaoLogado(),
                CanalEnum.RETAGUARDA.getId());
    }

    /**
     * Método responsável por reuperar o objeto xml GEN0014ComplexType da mensagem de requisição de arquivo.
     * 
     * @param numSeqMsg
     * @param situacaoBeneficiarioEnum
     * @return GEN0014ComplexType
     * @throws ComumException
     * 
     */
    private GEN0014ComplexType getGEN0014(String tipoArquivo) throws ComumException {
        GEN0014ComplexType gen14 = new GEN0014ComplexType();
        gen14.setCodMsg(TipoMensagemDDA.GEN0014);
        gen14.setISPBEmissor(Constantes.ISPB_BANCOOB);
        gen14.setISPBDestinatario(Constantes.ISPB_CIP_SITRAF);
        gen14.setNomArq(tipoArquivo);
        gen14.setTpTransm(TipoTransmissaoEnum.ARQUIVO_EXTERNO.getCodIndicador());
        gen14.setDtMovto(DataCipUtil.dateTimeParaXMLGregorianCalendar(new DateTime()));

        return gen14;
    }

    /**
     * Método responsável por reuperar o objeto xml GEN0014ComplexType da mensagem de requisição de arquivo do Beneficiario
     * 
     * @param tipoSolicitacao
     * @param situacaoBeneficiarioEnum
     * @return GEN0014ComplexType
     * @throws ComumException
     */
    private GEN0014ComplexType getGEN0014Beneficiario(TipoSolicitacaoArquivoBeneficiarioEnum tipoSolicitacao, SituacaoBeneficiarioEnum situacaoBeneficiarioEnum)
            throws ComumException {
        GEN0014ComplexType gen14 = getGEN0014(TipoMensagemDDA.ADDA504);

        GrupoGen14Beneficiario grupo = new GrupoGen14Beneficiario();
        grupo.setIspbPartDestinatarioAdmtd(Constantes.ISPB_BANCOOB);
        grupo.setTpSolctcBenfcrio(tipoSolicitacao.getCodDominio());
        if (!ObjectUtil.isNull(situacaoBeneficiarioEnum)) {
            grupo.setSitBenfcrio(situacaoBeneficiarioEnum.getCodDominio());
        }

        gerarCriterioSelecao(gen14, grupo);

        return gen14;
    }

    /**
     * Método responsável por reuperar o objeto xml GEN0014ComplexType da mensagem de requisição de arquivo do Pagador
     * 
     * @param tipoSolicitacao
     * @return GEN0014ComplexType
     * @throws ComumException
     */
    private GEN0014ComplexType getGEN0014Pagador(TipoSolicitacaoArquivoPagadorEnum tipoSolicitacao) throws ComumException {
        GEN0014ComplexType gen14 = getGEN0014(TipoMensagemDDA.ADDA002);

        GrupoGen14Pagador grupo = new GrupoGen14Pagador();
        grupo.setIspbPartAdmtd(Constantes.ISPB_BANCOOB);
        grupo.setTpSolctcPagdr(tipoSolicitacao.getCodDominio());

        gerarCriterioSelecao(gen14, grupo);

        return gen14;
    }

    /**
     * Método responsável por reuperar o objeto xml GEN0014ComplexType da mensagem de requisição de arquivo do Boleto
     * 
     * @param tipoSolicitacao
     * @param codTipoBoletoConsultado
     * @return GEN0014ComplexType
     * @throws ComumException
     */
    private GEN0014ComplexType getGEN0014Boleto(TipoSolicitacaoArquivoBoletoEnum tipoSolicitacao, String codTipoBoletoConsultado) throws ComumException {
        GEN0014ComplexType gen14 = getGEN0014(TipoMensagemDDA.ADDA106);

        GrupoGen14Boleto grupo = new GrupoGen14Boleto();
        grupo.setIspbPartAdmtd(Constantes.ISPB_BANCOOB);
        grupo.setTpSolctcTit(tipoSolicitacao.getCodDominio());
        grupo.setTpTitConsd(codTipoBoletoConsultado);

        gerarCriterioSelecao(gen14, grupo);

        return gen14;
    }

    /**
     * Método responsável por criar o xml de acordo com o grupo
     * 
     * @param gen14
     * @param grupo
     * @throws ComumException void
     * 
     */
    private void gerarCriterioSelecao(GEN0014ComplexType gen14, Object grupo) throws ComumException {
        String critSelec = EscritorXMLUtil.gerarXml(grupo);
        gen14.setCritSelec(critSelec);
    }
}
