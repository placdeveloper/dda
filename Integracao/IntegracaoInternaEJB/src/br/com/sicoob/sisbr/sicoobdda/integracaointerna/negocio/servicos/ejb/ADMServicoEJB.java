package br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.apache.commons.lang.time.DateUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.FabricaServicos;
import br.com.bancoob.infraestrutura.cache.ServicoCache;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.administrativo.api.negocio.delegates.AdmApiFabricaDelegates;
import br.com.sicoob.sisbr.administrativo.api.negocio.delegates.AdmApiFeriadoDelegate;
import br.com.sicoob.sisbr.administrativo.api.negocio.delegates.AdmApiProdutoInstituicaoDelegate;
import br.com.sicoob.sisbr.administrativo.api.negocio.dto.filtro.AdmApiFiltroBancoCaf;
import br.com.sicoob.sisbr.administrativo.api.negocio.entidades.IAdmApiBancoCaf;
import br.com.sicoob.sisbr.administrativo.api.negocio.entidades.IAdmApiProdutoInstituicao;
import br.com.sicoob.sisbr.administrativo.api.negocio.excecao.AdmApiException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.adm.BancoCafDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.adm.ComparatorBancoCafDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.adm.ProdutoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao.BancoNaoEncontradoException;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao.DataMovimentoNaoEncontradaException;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao.ISPBNaoEncontradoException;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao.IntegracaoInternaException;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao.NomeISPBNaoEncontradoException;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.interfaces.ADMServicoLocal;

/**
 * ADMServicoEJB
 * 
 * @author Samuell.Ramos
 */
@Stateless
@Local({ ADMServicoLocal.class })
public class ADMServicoEJB extends IntegracaoInternaServicoEJB implements ADMServicoLocal {

    private static final String ERRO_ADAPTADORINTEGRACAOSERVICO_DATA_MOVIMENTO_NAO_ENCONTRADA = "adaptadorintegracaoservico.data.movimento.nao.encontrada";
    private static final String KEY_CACHE_NUM_BANCO = "COBRANCA_CACHE_INSTITUICAO_NUM_BANCO_";
    private static final String KEY_CACHE_ISPB = "COBRANCA_CACHE_INSTITUICAO_ISPB_";
    private static final int CACHE_TIMEOUT = 12 * DateUtils.MILLIS_IN_HOUR;

    private ServicoCache servicoCache;

    /**
     * Método responsável por obter o servico cache por causa do Teste Unitário.
     * 
     * @return ServicoCache
     * 
     */
    private ServicoCache getServicoCache() {
        if (ObjectUtil.isNull(servicoCache)) {
            servicoCache = FabricaServicos.getInstance().criarServicoCache();
        }
        return servicoCache;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.cobrancabancaria.integracao.interna.privada.negocio.servicos.ADMServico#obterNomeBancoCache(short)
     */
    public String obterNomeBancoCache(short numBanco) throws ComumException, ComumNegocioException {
        debug("### Obtendo o nome do banco " + numBanco + " no chache.");

        debug("Gerando a chave pra o cache...");
        String chave = gerarChaveCacheNumBanco(numBanco);
        debug("Chave de cache gerada: " + chave);

        debug("Recuperando nome do banco no cache...");
        String nomeBanco = (String) getServicoCache().recuperar(chave);
        debug("Nome do banco obtido no cache: " + nomeBanco);

        if (ObjectUtil.isNull(nomeBanco)) {
            nomeBanco = obterNomeBanco(numBanco);

            if (!ObjectUtil.isNull(nomeBanco)) {
                debug("Armazenando o nomeBanco: " + nomeBanco + " no cache com a chave: " + chave);
                getServicoCache().armazenar(chave, nomeBanco, CACHE_TIMEOUT);
            }
        }

        return nomeBanco;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ADMServico#obterDataMovimento(java.lang.Integer)
     */
    public Date obterDataMovimento(Integer idInstituicao) throws IntegracaoInternaException {
        return obterDataMovimento(idInstituicao, Boolean.FALSE);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ADMServico#obterDataProximoMovimento(java.lang.Integer)
     */
    public Date obterDataProximoMovimento(Integer idInstituicao) throws IntegracaoInternaException {
        return obterDataMovimento(idInstituicao, Boolean.TRUE);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ADMServico#obterDataMovimentoBancoob()
     */
    public Date obterDataMovimentoBancoob() throws IntegracaoInternaException {
        return obterDataMovimento(Constantes.ID_BANCOOB, Boolean.FALSE);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ADMServico#obterDataProximoMovimentoBancoob()
     */
    public Date obterDataProximoMovimentoBancoob() throws IntegracaoInternaException {
        return obterDataMovimento(Constantes.ID_BANCOOB, Boolean.TRUE);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ADMServico#obterProdutoCobrancaBancoob()
     */
    public ProdutoDto obterProdutoCobrancaBancoob() throws IntegracaoInternaException {
        debug("### Obtendo o produto cobrança do bancoob...");

        final short idProduto = Integer.valueOf(Constantes.ID_PRODUTO_COBRANCA).shortValue();

        AdmApiProdutoInstituicaoDelegate delegate = AdmApiFabricaDelegates.getInstance().createAdmApiProdutoInstituicaoDelegate();
        List<IAdmApiProdutoInstituicao> listaProdutoInstituicao;

        try {
            listaProdutoInstituicao = delegate.pesquisarProdutoInstituicao(Constantes.ID_BANCOOB, idProduto);
        } catch (BancoobException e) {
            throw new IntegracaoInternaException("adaptadorintegracaoservico.erro.adm.servico", e);
        }

        debug("Lista de produto da instituição: " + listaProdutoInstituicao);

        if (ObjectUtil.isEmpty(listaProdutoInstituicao)) {
            throw new IntegracaoInternaException(ERRO_ADAPTADORINTEGRACAOSERVICO_DATA_MOVIMENTO_NAO_ENCONTRADA);
        }

        debug("Recuperando o primeiro registro da lista: " + listaProdutoInstituicao.size());
        IAdmApiProdutoInstituicao produtoInstituicao = listaProdutoInstituicao.get(0);
        debug("o primeiro registro da lista: " + listaProdutoInstituicao.size());

        return new ProdutoDto(idProduto, Constantes.ID_BANCOOB, produtoInstituicao.getIdUnidadeInstituicao(), produtoInstituicao.getDataUltimoMovimento(),
                produtoInstituicao.getDataAtualMovimento(), produtoInstituicao.getDataProximoMovimento());
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ADMServico#verificarDiaUtil(br.com.bancoob.persistencia.types.DateTimeDB)
     */
    public boolean verificarDiaUtil(DateTimeDB data) throws IntegracaoInternaException {
        try {
            AdmApiFeriadoDelegate admApiFeriadoDelegate = AdmApiFabricaDelegates.getInstance().createAdmApiFeriadoDelegate();
            return admApiFeriadoDelegate.verificarDiaUtil(Constantes.ID_BANCOOB, data);
        } catch (AdmApiException e) {
            throw new IntegracaoInternaException(e);
        }
    }

    /**
     * Método responsável por obter o nome do banco.
     * 
     * @param numBanco
     * @return
     * @throws ComumException
     */
    private String obterNomeBanco(short numBanco) throws ComumException, ComumNegocioException {
        debug("### Obtendo o nome do banco...");
        debug("Parâmetro - numBanco: " + numBanco);

        try {
            String nomeBanco = AdmApiFabricaDelegates.getInstance().createAdmApiBancoCafDelegate().recuperarNomeBanco(numBanco);
            debug("Nome do banco recuperado pelo seriço: " + nomeBanco);

            if (ObjectUtil.isNull(nomeBanco)) {
                throw new BancoNaoEncontradoException(numBanco);
            }

            return nomeBanco;
        } catch (BancoNaoEncontradoException e) {
            getLogger().erro(e, e.getMessage());
            throw e;
        } catch (AdmApiException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException("Erro ao obter nome do banco.", e);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ADMServico#obterNomeInstituicaoFinanceiraCache(java.lang.String)
     */
    public String obterNomeInstituicaoFinanceiraCache(String ispb) throws ComumException {
        debug("### Obtendo o nome da IF pelo ISPB " + ispb + " no chache.");

        debug("Gerando a chave pra o cache...");
        String chave = gerarChaveCacheISPB(ispb);
        debug("Chave de cache gerada: " + chave);

        debug("Recuperando nome do banco no cache...");
        String nome = (String) getServicoCache().recuperar(chave);
        debug("Nome do banco obtido no cache: " + nome);

        if (ObjectUtil.isNull(nome)) {
            nome = obterNomeInstituicaoFinanceira(ispb);
            if (!ObjectUtil.isNull(nome)) {
                debug("Armazenando o nome da IF: " + nome + " no cache com a chave: " + chave);
                getServicoCache().armazenar(chave, nome, CACHE_TIMEOUT);
            }
        }
        return nome;
    }

    /**
     * Método responsável por obter o nome da instituição financeira pelo ISPB.
     * 
     * @param ispb
     * @return
     * @throws ComumException
     */
    private String obterNomeInstituicaoFinanceira(String ispb) throws ComumException {
        debug("### Obtendo o nome da instituição...");
        debug("Parâmetro - ispb: " + ispb);

        try {
            AdmApiFiltroBancoCaf filtro = new AdmApiFiltroBancoCaf();
            filtro.setCodigoIspb(ispb);

            List<IAdmApiBancoCaf> lista = AdmApiFabricaDelegates.getInstance().createAdmApiBancoCafDelegate().obterBancoPorFiltroBancoCaf(filtro);
            debug("Lista retornada do serivço: " + lista);

            if (ObjectUtil.isEmpty(lista)) {
                throw new ISPBNaoEncontradoException(ispb);
            }

            IAdmApiBancoCaf admApiBancoCaf = lista.get(0);
            String nomeIF = admApiBancoCaf.getDescBanco();

            debug("Nome do banco recuperado pelo seriço: " + nomeIF);

            if (ObjectUtil.isNull(nomeIF)) {
                throw new NomeISPBNaoEncontradoException(ispb);
            }

            return nomeIF;
        } catch (ISPBNaoEncontradoException e) {
            getLogger().erro(e, e.getMessage());
            throw e;
        } catch (AdmApiException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException(e);
        }
    }

    /**
     * Gerar chave do cache por numero de banco.
     * 
     * @param numBanco
     * @return Short
     */
    private String gerarChaveCacheNumBanco(Short numBanco) {
        return KEY_CACHE_NUM_BANCO + numBanco;
    }

    /**
     * Gerar chave do cache por numero de ISPB.
     * 
     * @param numBanco
     * @return Short
     */
    private String gerarChaveCacheISPB(String ispb) {
        return KEY_CACHE_ISPB + ispb;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ADMServico#recuperarQtdDiasUteis(java.util.Date, java.util.Date, int)
     */
    public int recuperarQtdDiasUteis(Date dataInicial, Date dataFinal, int idInstituicao) throws ComumException {
        debug("### Obtendo o quantidade de dias úteis...");
        debug("Parâmetro - dataInicial: " + dataInicial);
        debug("Parâmetro - dataFinal: " + dataFinal);
        debug("Parâmetro - idInstituicao: " + idInstituicao);

        try {
            int qtdDiasUteis = AdmApiFabricaDelegates.getInstance().createAdmApiFeriadoDelegate().recuperarQtdDiasUteis(dataInicial, dataFinal, idInstituicao);
            debug("Quantidade de dias úteis: " + qtdDiasUteis);

            return qtdDiasUteis;
        } catch (AdmApiException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException(e);
        }
    }

    /**
     * Método responsável por obter a data de movimento.
     * 
     * @param idInstituicao
     * @param obterDataProximoMovimento
     * @return
     * @throws ComumException
     */
    private Date obterDataMovimento(Integer idInstituicao, boolean obterDataProximoMovimento) throws IntegracaoInternaException {
        debug("### Obtendo a data de movimento...");
        debug("Parâmetro - idInstituicao: " + idInstituicao);
        debug("Parâmetro - obterDataUltimoMovimento: " + obterDataProximoMovimento);

        try {
            debug("Criando delegate...");
            AdmApiProdutoInstituicaoDelegate delegate = AdmApiFabricaDelegates.getInstance().createAdmApiProdutoInstituicaoDelegate();
            debug("ProdutoInstituicaoDelegate criado: " + delegate);

            debug("Pesquisando os produtos da instituição...");

            final Integer idProduto = Integer.valueOf(Constantes.ID_PRODUTO_COBRANCA);

            List<IAdmApiProdutoInstituicao> listaProdutoInstituicao = delegate.pesquisarProdutoInstituicao(idInstituicao, idProduto.shortValue());
            debug("Lista de produto da instituição: " + listaProdutoInstituicao);

            if (ObjectUtil.isEmpty(listaProdutoInstituicao)) {
                throw new DataMovimentoNaoEncontradaException(idInstituicao, idProduto);
            }

            debug("Recuperando o primeiro registro da lista: " + listaProdutoInstituicao.size());
            IAdmApiProdutoInstituicao produtoInstituicao = listaProdutoInstituicao.get(0);
            debug("o primeiro registro da lista: " + listaProdutoInstituicao.size());

            Date dataMovimento;

            if (obterDataProximoMovimento) {
                debug("Obtendo a data do próximo movimento...");
                dataMovimento = produtoInstituicao.getDataProximoMovimento();
            } else {
                debug("Obtendo a data atual de movimento");
                dataMovimento = produtoInstituicao.getDataAtualMovimento();
            }

            debug("Data de movimento: " + dataMovimento);

            if (ObjectUtil.isNull(dataMovimento)) {
                throw new DataMovimentoNaoEncontradaException(idInstituicao, idProduto);
            }

            return dataMovimento;
        } catch (DataMovimentoNaoEncontradaException e) {
            getLogger().erro(e, e.getMessage());
            throw e;
        } catch (BancoobException e) {
            getLogger().erro(e, e.getMessage());
            throw new IntegracaoInternaException(e);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ADMServico#listarBancosCafComAgenciasAtivas()
     */
    public List<IAdmApiBancoCaf> listarBancosCafComAgenciasAtivas() throws ComumException {
        debug("### Obtendo a lista de BancosCAF...");
        List<IAdmApiBancoCaf> listaBancosCaf = null;
        try {
            listaBancosCaf = AdmApiFabricaDelegates.getInstance().createAdmApiBancoCafDelegate().listarBancosCafComAgenciasAtivas();
        } catch (AdmApiException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException(e);
        }
        return listaBancosCaf;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ADMServico#listarBancosCafDtoComAgenciasAtivas(java.lang.Boolean)
     */
    public List<BancoCafDto> listarBancosCafDtoComAgenciasAtivas(Boolean ordenarLista) throws ComumException {
        debug("### Obtendo a lista de BancosCafDto...");
        List<IAdmApiBancoCaf> listaBancosCaf = null;
        List<BancoCafDto> listaBancoCafDtos = new ArrayList<BancoCafDto>();
        try {
            listaBancosCaf = AdmApiFabricaDelegates.getInstance().createAdmApiBancoCafDelegate().listarBancosCafComAgenciasAtivas();
            for (IAdmApiBancoCaf iAdmApiBancoCaf : listaBancosCaf) {
                listaBancoCafDtos.add(new BancoCafDto(iAdmApiBancoCaf.getNumBanco(), !ObjectUtil.isEmpty(iAdmApiBancoCaf.getDescBanco()) ? iAdmApiBancoCaf.getDescBanco().trim()
                        : iAdmApiBancoCaf.getDescBanco(), iAdmApiBancoCaf.getNumCodISPB(), iAdmApiBancoCaf.getBolAtividade()));
            }
            if (ordenarLista) {
                debug("### Ordenando a lista de BancosCafDto...");
                Collections.sort(listaBancoCafDtos, new ComparatorBancoCafDto());
            }
        } catch (AdmApiException e) {
            getLogger().erro(e, e.getMessage());
            throw new IntegracaoInternaException(e);
        }
        return Collections.unmodifiableList(listaBancoCafDtos);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ADMServico#obterProximoDiaUtil(java.lang.Integer, java.util.Date)
     */
    public Date obterProximoDiaUtil(Integer idInstituicao, Date dataInicio) throws ComumException {
        AdmApiFeriadoDelegate delegate = AdmApiFabricaDelegates.getInstance().createAdmApiFeriadoDelegate();

        try {
            return delegate.recuperarProximoDiaUtil(idInstituicao, dataInicio);
        } catch (AdmApiException e) {
            getLogger().erro(e, e.getMessage());
            throw new IntegracaoInternaException(e);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ADMServico#obterPrimeiroDiaUtil(java.lang.Integer, java.util.Date)
     */
    public Date obterPrimeiroDiaUtil(Integer idInstituicao, Date dataInicio) throws ComumException {
        AdmApiFeriadoDelegate delegate = AdmApiFabricaDelegates.getInstance().createAdmApiFeriadoDelegate();

        try {
            return delegate.recuperarPrimeiroDiaUtil(idInstituicao, dataInicio);
        } catch (AdmApiException e) {
            getLogger().erro(e, e.getMessage());
            throw new IntegracaoInternaException(e);
        }
    }

}
