/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb
 * Arquivo:         MensagemSerie0200ServicoEJB.java
 * Data Criação:    Jan 13, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CadastroSerie0200Dto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemSerie0200ServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0200.DDA0200ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0214.DDA0214ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0215.DDA0215ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0215.GrupoDDA0215DettComplexType;
import br.com.sicoob.tipos.DateTime;

/**
 * MensagemSerie0200ServicoEJB é responsável por
 * 
 * @author felipe.rosa
 */
@Stateless
@Local({ MensagemSerie0200ServicoLocal.class })
public class MensagemSerie0200ServicoEJB extends IntegracaoCipServicoEJB implements MensagemSerie0200ServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

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
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemSerie0200Servico#incluir(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CadastroSerie0200Dto,
     *      java.lang.String, java.lang.Integer)
     */
    public void incluir(CadastroSerie0200Dto cadastroDto, String codTipoMensagem, Integer numPrioridadeEnvio) throws BancoobException {
        ComplexType complexType = null;

        if (codTipoMensagem.equalsIgnoreCase(TipoMensagemDDA.DDA0200)) {
            complexType = getDDA0200(cadastroDto);
        } else if (codTipoMensagem.equalsIgnoreCase(TipoMensagemDDA.DDA0214)) {
            complexType = getDDA0214(cadastroDto);
        } else if (codTipoMensagem.equalsIgnoreCase(TipoMensagemDDA.DDA0215)) {
            complexType = getDDA0215(cadastroDto);
        } else {
            throw new ComumNegocioException("integracaocip.erro.solicitacao.invalida");
        }

        criarMensagemEnvioDDA(complexType, numPrioridadeEnvio, getUsuarioLogado(), getIdInstituicaoLogado(), CanalEnum.RETAGUARDA.getId());
    }

    /**
     * Método responsável por reuperar o objeto xml DDA0200ComplexType da mensagem de requisição de arquivo.
     * 
     * @param cadastroDto
     * @param numSeqMsg
     * @return DDA0200ComplexType
     * @throws ComumException
     */
    private DDA0200ComplexType getDDA0200(CadastroSerie0200Dto cadastroDto) throws ComumException {
        DDA0200ComplexType dda0200 = new DDA0200ComplexType();
        dda0200.setCodMsg(TipoMensagemDDA.DDA0200);
        dda0200.setISPBPartPrincipal(Constantes.ISPB_BANCOOB);
        dda0200.setISPBPartAdmtd(Constantes.ISPB_BANCOOB);
        dda0200.setDtIniApurc(DataCipUtil.dateTimeParaXMLGregorianCalendar(cadastroDto.getDataInicioApuracao()));
        dda0200.setDtFimApurc(DataCipUtil.dateTimeParaXMLGregorianCalendar(cadastroDto.getDataFimApuracao()));
        dda0200.setNumIdentcLanc(cadastroDto.getNumIdentLancamento());
        dda0200.setTpTransc(cadastroDto.getCodTipoTransacao());
        dda0200.setTpCons(cadastroDto.getCodTipoConsulta());
        dda0200.setTpRet(cadastroDto.getCodTipoRetorno());
        dda0200.setDtMovto(DataCipUtil.dateTimeParaXMLGregorianCalendar(new DateTime()));
        return dda0200;
    }

    /**
     * Método responsável por reuperar o objeto xml DDA0214ComplexType da mensagem de requisição de arquivo.
     * 
     * @param cadastroDto
     * @param numSeqMsg
     * @return DDA0200ComplexType
     * @throws ComumException
     */
    private DDA0214ComplexType getDDA0214(CadastroSerie0200Dto cadastroDto) throws ComumException {
        DDA0214ComplexType dda0214 = new DDA0214ComplexType();
        dda0214.setCodMsg(TipoMensagemDDA.DDA0214);
        dda0214.setISPBPart(Constantes.ISPB_BANCOOB);
        dda0214.setDtRef(DataCipUtil.dateTimeParaXMLGregorianCalendar(cadastroDto.getDataHoraRef()));
        dda0214.setDtHrIni(ObjectUtil.isNull(cadastroDto.getDataInicioApuracao()) ? null : DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(cadastroDto.getDataInicioApuracao()));
        dda0214.setDtHrFim(ObjectUtil.isNull(cadastroDto.getDataInicioApuracao()) ? null : DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(cadastroDto.getDataFimApuracao()));
        dda0214.setTpMsgArq(cadastroDto.getTipoMensagemArquivo());
        dda0214.setCodMsgArq(cadastroDto.getCodTipoMensagem());
        dda0214.setTpRet(cadastroDto.getCodTipoRetorno());
        dda0214.setDtMovto(DataCipUtil.dateTimeParaXMLGregorianCalendar(new DateTime()));
        return dda0214;
    }

    /**
     * Método responsável por reuperar o objeto xml DDA0215ComplexType da mensagem de requisição de arquivo.
     * 
     * @param cadastroDto
     * @param numSeqMsg
     * @return DDA0215ComplexType
     * @throws ComumException
     */
    private DDA0215ComplexType getDDA0215(CadastroSerie0200Dto cadastroDto) throws ComumException {
        DDA0215ComplexType dda0215 = new DDA0215ComplexType();
        dda0215.setCodMsg(TipoMensagemDDA.DDA0215);
        dda0215.setISPBPartPrincipal(Constantes.ISPB_BANCOOB);
        dda0215.setTpMsgArq(cadastroDto.getTipoMensagemArquivo());
        dda0215.setCodMsgArq(cadastroDto.getCodTipoMensagem());
        dda0215.getGrupoDDA0215Dett().add(getGrupoDDA0215Det(cadastroDto));
        dda0215.setDtMovto(DataCipUtil.dateTimeParaXMLGregorianCalendar(new DateTime()));
        return dda0215;
    }

    /**
     * Método responsável por
     * 
     * @param cadastroDto
     * @return GrupoDDA0215DettComplexType
     */
    private GrupoDDA0215DettComplexType getGrupoDDA0215Det(CadastroSerie0200Dto cadastroDto) {
        GrupoDDA0215DettComplexType grupo = new GrupoDDA0215DettComplexType();
        grupo.setIdentdArqOr(cadastroDto.getIdentArquivoc());
        grupo.setNUOpOr(cadastroDto.getNumOpMensagem());
        return grupo;
    }

}
