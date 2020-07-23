package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoManutencaoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ReplicarPagadorEletronicoLegadoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ArquivoManutencaoPagadorServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ReplicarPagadorEletronicoLegadoDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA003.GrupoADDA003PagdrDDAComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor.ConversorPagadorDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * ArquivoManutencaoPagadorServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ ArquivoManutencaoPagadorServicoLocal.class })
public class ArquivoManutencaoPagadorServicoEJB extends IntegracaoCipServicoEJB implements ArquivoManutencaoPagadorServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private PagadorCipDao dao;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private ReplicarPagadorEletronicoLegadoDao replicarPagadorEletronico;

    private ReplicarPagadorEletronicoLegadoDelegate replicarPagadorEletronicoLegadoDelegate = IntegracaoCipFabricaDelegates.getInstance()
            .getReplicarPagadorEletronicoLegadoDelegate();

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarMensagem(java.lang.Long)
     */
    public String processarMensagem(Long idMensagem) throws ComumException {
        getLogger().debug("########### Inicio processarMensagem(Long idMensagem) para  DDA0005 - Alteração do Pagador");

        getLogger().debug("########### Fim processarMensagem(Long idMensagem) para DDA0005 - Alteração do Pagador");
        return "";
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarRetornoMensagemDDA(java.lang.Long,
     *      java.lang.Object)
     */
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida conteudoMsg) throws ComumException {
        getLogger().info("########### Inicio processarRetornoArquivoDDA(Object retornoDDA) - ADDA003 ");

        getLogger().info("Inicio da atualizaçao do pagador no retorno da mensagem ADDA003");
        GrupoADDA003PagdrDDAComplexType grupoPagadorDDA = (GrupoADDA003PagdrDDAComplexType) conteudoMsg;
        TipoManutencaoEnum tipoManutencao = TipoManutencaoEnum.getBy(grupoPagadorDDA.getIndrManutPagdr());
        PagadorDDA pagador = obterPagadorDDA(grupoPagadorDDA);
        if (ObjectUtil.isNull(pagador) || ObjectUtil.isEmpty(pagador.getId())) {
            incluirPagadorDDA(grupoPagadorDDA);
        } else if (!tipoManutencao.equals(TipoManutencaoEnum.EXCLUSAO)) {
            alterarPagadorDDA(pagador, grupoPagadorDDA);
        } else {
            excluirPagadorDDA(pagador, grupoPagadorDDA);
        }
        getLogger().info("Atualização Feita com Sucesso");

        getLogger().info("########### Fim processarRetornoArquivoDDA(Object retornoDDA) - ADDA003 ");
    }

    /**
     * Método responsável por
     * 
     * @param grupoPagador void
     * @throws ComumException
     * 
     */
    private void incluirPagadorDDA(ConteudoMsgRecebida grupoPagador) throws ComumException {
        getLogger().info("Inicio da conversão do Objeto do Pagador");
        PagadorDDA pagador = ConversorPagadorDDA.converter(grupoPagador);
        getEm().persist(pagador);
        getLogger().info("Objeto Convertido com Sucesso");

        getLogger().debug("###### Replicando o Arquivo Manutencao do pagador (Inclusao) numCpfCnpj = " + pagador.getNumCpfCnpj() + " no legado.");

        replicarPagadorEletronicoLegadoDelegate.replicarPagadorCIPLegado(pagador.getNumCpfCnpj(), pagador.getBolSacadoEletronico(), getNumCooperativa(pagador));

    }

    /**
     * Método responsável por
     * 
     * @param grupoPagador
     * @throws ComumException void
     * 
     */
    private void alterarPagadorDDA(PagadorDDA pagador, ConteudoMsgRecebida grupoPagador) throws ComumException {
        getLogger().info("Processando alteração do Pagador - " + pagador.getId());
        PagadorDDA pagadorAlterado = ConversorPagadorDDA.merge(grupoPagador, pagador);
        getEm().merge(pagadorAlterado);
        getLogger().info("Objeto Convertido com Sucesso");

        getLogger().debug("###### Replicando o Arquivo Manutencao do pagador (Alteracao) numCpfCnpj = " + pagador.getNumCpfCnpj() + " no legado.");

        replicarPagadorEletronicoLegadoDelegate.replicarPagadorCIPLegado(pagador.getNumCpfCnpj(), pagador.getBolSacadoEletronico(), getNumCooperativa(pagador));

    }

    /**
     * Método responsável por
     * 
     * @param grupoPagador
     * @throws ComumException void
     * 
     */
    private void excluirPagadorDDA(PagadorDDA pagador, ConteudoMsgRecebida grupoPagador) throws ComumException {
        getLogger().info("Processando exclusão do Pagador - " + pagador.getId());
        PagadorDDA pagadorAlterado = ConversorPagadorDDA.remove(grupoPagador, pagador);
        getEm().merge(pagadorAlterado);
        getLogger().info("Objeto Convertido com Sucesso");

        getLogger().debug("###### Replicando  o Arquivo Manutencao do pagador (Exclusao)  do pagador numCpfCnpj = " + pagadorAlterado.getNumCpfCnpj() + " no legado.");
        replicarPagadorEletronicoLegadoDelegate.excluirPagadorCipLegador(pagadorAlterado.getNumCpfCnpj(), pagadorAlterado.getQtdAdesaoDDA() > 1);

    }

    /**
     * Método responsável por
     * 
     * @param numCPFCNPJ
     * @param tipoPessoa
     * @return
     * @throws ComumException PagadorDDA
     * 
     */
    private PagadorDDA obterPagadorDDA(GrupoADDA003PagdrDDAComplexType grupoPagador) throws ComumException {
        return dao.obterPagadorDDA(DataCipUtil.obterCPFCNPJ(grupoPagador.getCNPJCPFPagdr(), grupoPagador.getTpPessoaPagdr()), Boolean.FALSE);
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
     * Método responsável por setar o numero da cooperativa, quando vier mais de uma conta pega o 1 registro
     * 
     * @param pagador
     * @return Integer
     * 
     */
    private Integer getNumCooperativa(PagadorDDA pagador) {
        return ObjectUtil.isNull(pagador.getListaPagadorDDAConta()) || pagador.getListaPagadorDDAConta().size() == 0 ? null : pagador.getListaPagadorDDAConta().get(0)
                .getNumAgencia();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoManutencaoPagadorServico#processarRetornoMensagemDDA(long, long, long)
     */
    public void processarRetornoMensagemDDA(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal) throws ComumException {
        getLogger().info("########### Processando Manutenção de Clientes Pagador Eletrônico - ADDA003");
        try {
            dao.executarManutencaoPagadorEletronico(idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal);
            List<Object[]> listaLoteParametros = dao.listarPagadorEletronicoModificados(idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal);
            if (!ObjectUtil.isEmpty(listaLoteParametros)) {
                replicarPagadorEletronico.replicarPagadorEletronico(listaLoteParametros);
            }
        } catch (ComumException comumException) {
            throw new ComumException("Erro ao processarRetornoMensagemDDA ADDA003", comumException);
        }
    }
}