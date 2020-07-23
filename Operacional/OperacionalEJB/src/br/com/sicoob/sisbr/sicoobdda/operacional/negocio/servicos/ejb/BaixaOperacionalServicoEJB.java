package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.LinhaDigitavelCodigoBarrasUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.MeioPagamento;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoBaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.BaixaOperacionalServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.MensagemDDADao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;

/**
 * BaixaOperacionalServicoEJB é responsável por
 * 
 * @author Rodrigo.Neri
 */
@Stateless
@Local({ BaixaOperacionalServicoLocal.class })
public class BaixaOperacionalServicoEJB extends OperacionalServicoEJB implements BaixaOperacionalServicoLocal {

    /**
     * 
     */
    private static final String MSG_INTEGRACAOCIP_PARAMETRO_NAO_INFORMADO = "integracaocip.parametro.nao.informado";

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private MensagemDDADao dao;

    private MensagemDDADelegate mensagemDDADelegate;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.OperacionalServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }

    /**
     * @return o atributo delegate
     */
    public MensagemDDADelegate getMensagemDDADelegate() {
        if (mensagemDDADelegate == null) {
            mensagemDDADelegate = IntegracaoCipFabricaDelegates.getInstance().getMensagemDDADelegate();
        }

        return mensagemDDADelegate;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.BaixaOperacionalServico#incluir(br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaOperacional,
     *      short, short, boolean, br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Integer)
     */
    public void incluir(MensagemDDABaixaOperacional mensagem, short numBanco, short canal, boolean aceitaPagamentoParcial, DateTimeDB dataMovimento, Integer idInstituicao)
            throws ComumException {
        incluir(mensagem, numBanco, canal, aceitaPagamentoParcial, dataMovimento, idInstituicao, (short) 0);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.BaixaOperacionalServico#incluir(br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaOperacional,
     *      short, short, boolean, br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Integer, short)
     */
    public void incluir(MensagemDDABaixaOperacional mensagem, short numBanco, short canal, boolean aceitaPagamentoParcial, DateTimeDB dataMovimento, Integer idInstituicao,
            short codMeioPagamento) throws ComumException {
        debug("### Incluíndo mensagem de baixa operacional...");
        debug("Parâmetro - mensagem: " + mensagem);
        debug("Parâmetro - numBanco: " + numBanco);
        debug("Parâmetro - canal: " + canal);
        debug("Parâmetro - aceitaPagamentoParcial: " + aceitaPagamentoParcial);
        debug("Parâmetro - dataMovimento: " + dataMovimento);
        debug("Parâmetro - idInstituicao: " + idInstituicao);

        if (ObjectUtil.isNull(mensagem)) {
            throw new ComumException(MSG_INTEGRACAOCIP_PARAMETRO_NAO_INFORMADO, "mensagem");
        } else if (ObjectUtil.isEmpty(numBanco)) {
            throw new ComumException(MSG_INTEGRACAOCIP_PARAMETRO_NAO_INFORMADO, "numBanco");
        } else if (ObjectUtil.isNull(dataMovimento)) {
            throw new ComumException(MSG_INTEGRACAOCIP_PARAMETRO_NAO_INFORMADO, "dataMovimento");
        } else if (ObjectUtil.isEmpty(mensagem.getValorBaixa())) {
            throw new ComumException(MSG_INTEGRACAOCIP_PARAMETRO_NAO_INFORMADO, "ValorBaixa");
        } else if (ObjectUtil.isEmpty(mensagem.getNumCodigoBarra())) {
            throw new ComumException(MSG_INTEGRACAOCIP_PARAMETRO_NAO_INFORMADO, "NumCodigoBarra");
        }

        definirMeioPagamento(mensagem, canal, codMeioPagamento);

        mensagem.setCodCanalPagamento(obterCanalDDA(canal));
        mensagem.setCodTipoBaixaOperacional(obterTipoBaixaOperacional(numBanco, aceitaPagamentoParcial));
        mensagem.setNumCodBarrasCampoLivre(LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(mensagem.getNumCodigoBarra()));

        // Se não houver NumRefAtualCadBoleto é porque está em contingência
        mensagem.setBolOperacaoContingencia(ObjectUtil.isNull(mensagem.getNumRefAtualCadBoleto()));

        getMensagemDDADelegate().incluir(mensagem, TipoMensagemDDA.DDA0108, dataMovimento, getUsuarioLogado(), idInstituicao, canal);
    }

    /**
     * Método responsável por definir o meio de pagamento
     * 
     * @param mensagem
     * @param canal
     * @param codMeioPagamento
     */
    private void definirMeioPagamento(MensagemDDABaixaOperacional mensagem, short canal, short codMeioPagamento) {
        /*
         * TODO rodrigo.neri subiremos o código SEM a validação abaixo, porém após 28/05 essa validação deve ser adicionada, visto que será OBRIGATÓRIO informar
         * o meio de pagamento e deve estar entre os valores válidos
         */
        // if (codMeioPagamento < MeioPagamento.ESPECIE || codMeioPagamento > MeioPagamento.CHEQUE) {
        // throw new ComumNegocioException("Meio de pagamento inválido. O valor deve ser de {1} a {2}.", MeioPagamento.ESPECIE, MeioPagamento.CHEQUE);
        // }
        short novoCodMeioPagamento = codMeioPagamento;

        /*
         * TODO rodrigo.neri subiremos o código com a validação abaixo, porém após 28/05 essa validação deve ser removida, visto que haverá a validação acima e
         * utilizará o meio de pagamento que receber do serviço
         * 
         * IMPORTANTE: este tratamento está sendo feito para ficar compatível com a versão do canal que está em produção
         */
        // Se o meio de pagamento não estiver entre os valores válidos definirá o novo valor
        if (codMeioPagamento < MeioPagamento.ESPECIE || codMeioPagamento > MeioPagamento.CHEQUE) {
            // O canal CAIXA e o Correspondente sempre fazem pagamento em espécie
            if (canal == CanalEnum.CAIXA.getId() || canal == CanalEnum.CORRESPONDENTE_SICOOB.getId()) {
                debug("Definindo meio de pagamento: Espécie");
                novoCodMeioPagamento = MeioPagamento.ESPECIE;
            }
            // Canal de agendamento
            else {
                debug("Definindo meio de pagamento: Débito em conta");
                novoCodMeioPagamento = MeioPagamento.DEBITO_EM_CONTA;
            }
        }

        debug("Meio de pagamento: " + novoCodMeioPagamento);
        mensagem.setCodMeioPagamento(novoCodMeioPagamento);
    }

    /**
     * Método responsável por obter o tipo de baixa operacional correspondente ao banco e tipo de pagamento.
     * 
     * @param numBanco
     * @param aceitaPagamentoParcial
     * @return
     */
    private short obterTipoBaixaOperacional(short numBanco, boolean aceitaPagamentoParcial) {
        debug("### Obtendo tipo de baixa operacional...");

        short codTipoBaixaOperacional;

        // Se aceitar pagamento parcial
        if (aceitaPagamentoParcial) {
            // Se for 756
            if (numBanco == Constantes.NUM_BANCOOB) {
                debug("Definindo tipo de baixa operacional parcial intrabancária");
                codTipoBaixaOperacional = TipoBaixaOperacional.BAIXA_OPERACIONAL_PARCIAL_INTRABANCARIA;
            }
            // Outros bancos
            else {
                debug("Definindo tipo de baixa operacional parcial interbancária");
                codTipoBaixaOperacional = TipoBaixaOperacional.BAIXA_OPERACIONAL_PARCIAL_INTERBANCARIA;
            }
        }
        // Se NÃO aceitar pagamento parcial
        else {
            // Se for 756
            if (numBanco == Constantes.NUM_BANCOOB) {
                debug("Definindo tipo de baixa operacional integral intrabancária");
                codTipoBaixaOperacional = TipoBaixaOperacional.BAIXA_OPERACIONAL_INTEGRAL_INTRABANCARIA;
            }
            // Outros bancos
            else {
                debug("Definindo tipo de baixa operacional integral interbancária");
                codTipoBaixaOperacional = TipoBaixaOperacional.BAIXA_OPERACIONAL_INTEGRAL_INTERBANCARIA;
            }
        }

        return codTipoBaixaOperacional;
    }

    /**
     * Método responsável por obter o canal DDA de acordo com o canal CTR
     * 
     * @param canal
     * @return
     * @throws ComumException
     */
    private short obterCanalDDA(short canalCTR) throws ComumException {
        Short canalDDA = dao.obterCanalDDA(canalCTR);

        if (canalDDA == null) {
            throw new ComumException("integracaocip.canal.ctr.nao.correspondente", canalCTR);
        }

        return canalDDA;
    }

}
