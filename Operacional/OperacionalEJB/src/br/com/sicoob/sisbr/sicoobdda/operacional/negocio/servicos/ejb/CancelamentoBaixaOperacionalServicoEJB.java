/**
 * Projeto:         Cobranca Bancaria
 * Camada Projeto:  cob-integracao-cip
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.negocio.servicos.ejb
 * Arquivo:         CadastrarBeneficiarioServicoEJB.java
 * Data Criacao:    May 8, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.LinhaDigitavelCodigoBarrasUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemDDAServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.ADMDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SCIDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.CancelamentoBaixaOperacionalServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BoletoDDADao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;

/**
 * CancelamentoBaixaOperacionalServicoEJB é responsável por
 * 
 * @author George.Santos
 */
@Stateless
@Local({ CancelamentoBaixaOperacionalServicoLocal.class })
public class CancelamentoBaixaOperacionalServicoEJB extends OperacionalServicoEJB implements CancelamentoBaixaOperacionalServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private BoletoDDADao dao;

    @Dao(entityManager = "em", fabrica = ComumDaoFactory.class)
    private ParametroDao parametroDAO;

    private SCIDelegate sciDelegate;

    private ADMDelegate admDelegate;

    @EJB
    private MensagemDDAServicoLocal mensagemDDAServico;

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
     * @return o atributo sciDelegate
     */
    public SCIDelegate getSCIDelegate() {
        if (ObjectUtil.isNull(sciDelegate)) {
            sciDelegate = IntegracaoInternaFabricaDelegates.getInstance().getSCIDelegate();
        }

        return sciDelegate;
    }

    /**
     * @return o atributo ADMDelegate
     */
    public ADMDelegate getADMDelegate() {
        if (ObjectUtil.isNull(admDelegate)) {
            admDelegate = IntegracaoInternaFabricaDelegates.getInstance().getADMDelegate();
        }
        return admDelegate;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.CancelamentoBaixaOperacionalServico#processarCancelamentoBaixaOperacional(java.lang.String,
     *      java.lang.Integer, java.math.BigDecimal)
     */
    public void processarCancelamentoBaixaOperacional(String codigoBarras, Integer numCooperativa, BigDecimal valorPago, Short idCanal) throws ComumException,
            ComumNegocioException {
        debug("### Processando o cancelamento da baixa operacional...");
        debug("Parâmetro - codigoBarras: " + codigoBarras);
        debug("Parâmetro - numCooperativa: " + numCooperativa);
        debug("Parâmetro - valorPago: " + valorPago);
        debug("Parâmetro - idCanal: " + idCanal);

        validacaoCancelamentoBaixaOperacional(codigoBarras, numCooperativa, valorPago);

        int idInstituicao = obterIdInstituicao(numCooperativa);
        debug("Id instituição: " + idInstituicao);

        boolean ddaAtivo = parametroDAO.obterValorBoolean(ParametroDDA.SICOOBDDA_ATIVADO, idInstituicao);
        debug("DDA Ativo: " + ddaAtivo);

        // Verifca se DDA está ativo e se está na boletoDDA
        if (ddaAtivo && getDao().possuiBoletoDDA(codigoBarras)) {
            DateTimeDB dataMovimento = new DateTimeDB(getADMDelegate().obterDataMovimento(idInstituicao).getTime());
            debug("Data de movimento: " + dataMovimento);

            List<MensagemDDABaixaOperacional> listaMensagem = getDao().listarMensagemDDABaixaOperacional(codigoBarras, dataMovimento);

            // Se possuir baixa é porque o boleto está na onda, caso contrário, o boleto não está na onda e não deve lançar exceção
            if (!ObjectUtil.isEmpty(listaMensagem)) {
                MensagemDDABaixaOperacional mensagemSelecionada = null;

                for (MensagemDDABaixaOperacional mensagemDDABaixaOperacional : listaMensagem) {
                    if (mensagemDDABaixaOperacional.getValorBaixa().doubleValue() == valorPago.doubleValue()) {
                        mensagemSelecionada = mensagemDDABaixaOperacional;
                        break;
                    }
                }

                // Se não encontrou mensagem de baixa para o valor informado lança exceção
                if (ObjectUtil.isNull(mensagemSelecionada)) {
                    throw new ComumNegocioException("integracaocip.boleto.baixa.operacional.nao.existe");
                }

                mensagemDDAServico.incluir(criarMensagemDDABaixaOperacional(mensagemSelecionada), TipoMensagemDDA.DDA0115, dataMovimento, getUsuarioLogado(), idInstituicao,
                        idCanal);
            }
        }
    }

    /**
     * Método responsável por obter o id da instituição
     * 
     * @param numCooperativa
     * @return
     * @throws ComumException
     */
    private int obterIdInstituicao(Integer numCooperativa) throws ComumException {
        debug("### Obtendo o id da instituição...");

        InstituicaoDto instituicaoDto = getSCIDelegate().obterInstituicaoPorCooperativaCache(numCooperativa);

        debug("InstituicaoDto: " + instituicaoDto);

        return instituicaoDto.getIdInstituicao();
    }

    /**
     * Método responsável por validar se é permitido cancelar a baixa operacional
     * 
     * @param codigoBarras
     * @param numCooperativa
     * @param valorPago
     * @throws ComumNegocioException
     * @throws ComumException
     */
    private void validacaoCancelamentoBaixaOperacional(String codigoBarras, Integer numCooperativa, BigDecimal valorPago) throws ComumNegocioException, ComumException {
        debug("### Validando o cancelamento da baixa operacional...");

        if (ObjectUtil.isEmpty(codigoBarras)) {
            throw new ComumNegocioException("integracaocip.parametro.nao.informado", "codigoBarras");
        } else if (ObjectUtil.isEmpty(numCooperativa)) {
            throw new ComumNegocioException("integracaocip.parametro.nao.informado", "numCooperativa");
        } else if (ObjectUtil.isEmpty(valorPago)) {
            throw new ComumNegocioException("integracaocip.parametro.nao.informado", "valorPago");
        }
    }

    /**
     * Método responsável por criar as mensagens do dda BOLETO
     * 
     * @param mensagemDDABaixaOper
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private MensagemDDABaixaOperacional criarMensagemDDABaixaOperacional(MensagemDDABaixaOperacional mensagemDDABaixaOper) throws ComumNegocioException, ComumException {
        getLogger().debug("###### Inicio metodo criarMensagemDDABoleto ######");

        MensagemDDABaixaOperacional mensagemDDABaixaOperacional = new MensagemDDABaixaOperacional();
        mensagemDDABaixaOperacional.setCodTipoBaixaOperacional(mensagemDDABaixaOper.getCodTipoBaixaOperacional());
        mensagemDDABaixaOperacional.setBolOperacaoContingencia(false);

        mensagemDDABaixaOperacional.setNumCodigoBarra(mensagemDDABaixaOper.getNumCodigoBarra());
        mensagemDDABaixaOperacional.setNumCodBarrasCampoLivre(
                mensagemDDABaixaOper.getNumCodigoBarra() != null ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(mensagemDDABaixaOper.getNumCodigoBarra()) : null);
        mensagemDDABaixaOperacional.setCodMeioPagamento(mensagemDDABaixaOper.getCodMeioPagamento());
        mensagemDDABaixaOperacional.setCodCanalPagamento(mensagemDDABaixaOper.getCodCanalPagamento());
        mensagemDDABaixaOperacional.setValorBaixa(mensagemDDABaixaOper.getValorBaixa());

        return mensagemDDABaixaOperacional;
    }

    /**
     * @return o atributo dao
     */
    public BoletoDDADao getDao() {
        return dao;
    }

    /**
     * Define o atributo dao
     */
    public void setDao(BoletoDDADao dao) {
        this.dao = dao;
    }
}
