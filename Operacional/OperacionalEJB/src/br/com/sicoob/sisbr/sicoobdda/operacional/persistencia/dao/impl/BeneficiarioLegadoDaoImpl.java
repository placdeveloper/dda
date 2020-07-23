/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl
 * Arquivo:         BeneficiarioLegadoDaoImpl.java
 * Data Criação:    Sep 1, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.AlterarCadastroBeneficiarioDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CadastroBeneficiarioDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConvenioAlteracaoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConvenioCadastroDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoConvenioBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoRelacionamentoParticipanteEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoAgenciaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoCarteiraCobrancaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoProdutoConvenioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.IntegracaoCipException;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BeneficiarioLegadoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDao;

/**
 * BeneficiarioLegadoDaoImpl é responsável por
 * 
 * @author Felipe.Rosa
 */
public class BeneficiarioLegadoDaoImpl extends OperacionalCrudDao<SicoobDDAEntidade> implements BeneficiarioLegadoDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_operacional.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional";
    public static final String COMANDOS = "";

    private static final String ERRO_OBTER_DADOS_CONVENIO_BENEFICIARIO_LEGADO = "integracaocip.erro.obter.dados.convenio.beneficiario.legado";
    private static final String ERRO_OBTER_DADOS_BENEFICIARIO_LEGADO = "integracaocip.erro.obter.dados.beneficiario.legado";

    /**
     * @param datasource
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     */
    public BeneficiarioLegadoDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, COMANDOS);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.percistencia.dao.impl.sicoobdda.integracaocip.persistencia.dao.BeneficiarioLegadoDao#obterDadosBasicosBeneficiario(java.lang.String,
     *      java.lang.Integer)
     */
    public CadastroBeneficiarioDDADto obterCadastroBeneficiarioDDADto(String numCpfCnpj, Integer numCooperativa) throws IntegracaoCipException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        CadastroBeneficiarioDDADto cadastroBeneficiario = null;
        ConvenioCadastroDDADto convenio = null;
        List<ConvenioCadastroDDADto> listaConvenios = new ArrayList<ConvenioCadastroDDADto>();
        try {
            comando = getComando("OBTER_DADOS_CADASTRO_BENFICIARIO");
            comando.adicionarVariavel("numCPFCNPJ", numCpfCnpj);
            comando.configurar();
            getLogger().info(comando.getSqlEmUso());
            conn = estabelecerConexao(numCooperativa);
            rs = comando.executarConsulta(conn);
            if (rs.next()) {
                cadastroBeneficiario = this.montarCadastroBeneficiario(rs);
                convenio = this.montarConvenio(rs);
                listaConvenios.add(convenio);
                cadastroBeneficiario.setListaConvenioCadastroDto(listaConvenios);
            }
        } catch (SQLException e) {
            getLogger().erro(e, ERRO_OBTER_DADOS_BENEFICIARIO_LEGADO);
            throw new IntegracaoCipException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return cadastroBeneficiario;
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException CadastroBeneficiarioDDADto
     * 
     */
    private CadastroBeneficiarioDDADto montarCadastroBeneficiario(ResultSet rs) throws SQLException {
        CadastroBeneficiarioDDADto cadastro = new CadastroBeneficiarioDDADto();
        cadastro.setTipoPessoaBeneficiario(TipoPessoaEnum.getBy(rs.getString("tipoPessoa")));
        cadastro.setCnpjCpfBeneficiario(rs.getString("numeroDocumento"));
        cadastro.setNomeRazaoSocialBeneficiario(rs.getString("nomePessoaRazaoSocial"));

        if (TipoPessoaEnum.isPessoaJuridica(cadastro.getTipoPessoaBeneficiario().getCodDominioCip()) && ObjectUtil.isNull(rs.getString("nomeFantasia"))) {
            cadastro.setNomeFantasiaBeneficiario(cadastro.getNomeRazaoSocialBeneficiario());
        } else {
            cadastro.setNomeFantasiaBeneficiario(rs.getString("nomeFantasia"));
        }
        cadastro.setSituacaoRelacionamentoParticipante(SituacaoRelacionamentoParticipanteEnum.ATIVO);
        cadastro.setDataInicioRelacionamentoParticipante(rs.getDate("dataInicioRelacionamento"));
        cadastro.setDataHoraSituacaoBeneficiario(new Date());
        return cadastro;
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return ConvenioCadastroDDADto
     * @throws SQLException
     * 
     */
    private ConvenioCadastroDDADto montarConvenio(ResultSet rs) throws SQLException {
        getLogger().debug("****** montarConvenio ******");
        ConvenioCadastroDDADto convenio = new ConvenioCadastroDDADto();
        convenio.setNumConta(null);
        convenio.setNumAgencia(rs.getInt("agencia"));
        convenio.setSitucaoConvenio(SituacaoConvenioBeneficiarioEnum.getBy(rs.getShort("situacaoConvenio")));
        convenio.setTipoAgencia(TipoAgenciaEnum.FISICA);
        convenio.setDataInicioConvenio(rs.getDate("dataInclusaoConvenio"));
        convenio.setTipoProdutoConvenio(TipoProdutoConvenioEnum.BOLETO_DE_COBRANCA);
        convenio.setTipoCarteiraConvenio(TipoCarteiraCobrancaEnum.getBy(rs.getString("tipoCarteira")));
        convenio.setCodClienteConvenio(null);
        convenio.setTipoConta(null);
        return convenio;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.percistencia.dao.impl.sicoobdda.integracaocip.persistencia.dao.BeneficiarioLegadoDao#obterAlterarCadastroBeneficiarioDDADto(java.lang.String,
     *      java.lang.Integer)
     */
    public AlterarCadastroBeneficiarioDDADto obterAlterarCadastroBeneficiarioDDADto(String numCpfCnpj, Integer numCooperativa) throws IntegracaoCipException {
        return this.obterAlterarCadastroBeneficiarioDDADto(null, numCpfCnpj, numCooperativa);
    }

    /**
     * Método responsável por
     * 
     * @param numCliente
     * @param numCooperativa
     * @return
     * @throws IntegracaoCipException AlterarCadastroBeneficiarioDDADto
     * 
     */
    public AlterarCadastroBeneficiarioDDADto obterAlterarCadastroBeneficiarioDDADto(Long numCliente, Integer numCooperativa) throws IntegracaoCipException {
        return this.obterAlterarCadastroBeneficiarioDDADto(numCliente, null, numCooperativa);
    }

    /**
     * Método responsável por
     * 
     * @param numCliente
     * @param numCpfCnpj
     * @param numCooperativa
     * @return
     * @throws IntegracaoCipException AlterarCadastroBeneficiarioDDADto
     * 
     */
    private AlterarCadastroBeneficiarioDDADto obterAlterarCadastroBeneficiarioDDADto(Long numCliente, String numCpfCnpj, Integer numCooperativa) throws IntegracaoCipException {
        getLogger().debug("****** obterAlterarCadastroBeneficiarioDDADto ******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        AlterarCadastroBeneficiarioDDADto alterarCadastro = null;
        try {
            comando = getComando("OBTER_DADOS_CADASTRO_BENFICIARIO");
            comando.adicionarVariavel("numCPFCNPJ", numCpfCnpj);
            comando.adicionarVariavel("numCliente", numCliente);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao(numCooperativa);
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                alterarCadastro = this.montarAlterarCadastroBeneficiario(rs);
                ConvenioAlteracaoDDADto convenio = this.montarConvenioAlteracao(rs);
                List<ConvenioAlteracaoDDADto> listaConvenios = new ArrayList<ConvenioAlteracaoDDADto>();
                listaConvenios.add(convenio);
                alterarCadastro.setListaConvenioAlteracaoDto(listaConvenios);
            }
        } catch (SQLException e) {
            getLogger().erro(e, ERRO_OBTER_DADOS_BENEFICIARIO_LEGADO);
            throw new IntegracaoCipException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return alterarCadastro;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.percistencia.dao.impl.sicoobdda.integracaocip.persistencia.dao.BeneficiarioLegadoDao#obterConvenioAlteracaoDDADto(java.lang.String,
     *      java.lang.Integer)
     */
    public ConvenioAlteracaoDDADto obterConvenioAlteracaoDDADto(String numCpfCnpj, Integer numCooperativa) throws IntegracaoCipException {
        getLogger().debug("****** obterConvenioAlteracaoDDADto ******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        ConvenioAlteracaoDDADto convenio = null;
        try {
            comando = getComando("OBTER_DADOS_CONVENIO_BENFICIARIO");
            comando.adicionarVariavel("numCPFCNPJ", numCpfCnpj);
            comando.configurar();
            getLogger().info(comando.getSqlEmUso());
            conn = estabelecerConexao(numCooperativa);
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                convenio = this.montarConvenioAlteracao(rs);
            }
        } catch (SQLException e) {
            getLogger().erro(e, ERRO_OBTER_DADOS_CONVENIO_BENEFICIARIO_LEGADO);
            throw new IntegracaoCipException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return convenio;
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException AlterarCadastroBeneficiarioDDADto
     * 
     */
    private AlterarCadastroBeneficiarioDDADto montarAlterarCadastroBeneficiario(ResultSet rs) throws SQLException {
        getLogger().debug("****** montarAlterarCadastroBeneficiario ******");
        AlterarCadastroBeneficiarioDDADto alterarCadastro = new AlterarCadastroBeneficiarioDDADto();
        alterarCadastro.setTipoPessoaBeneficiario(TipoPessoaEnum.getBy(rs.getString("tipoPessoa")));
        alterarCadastro.setCnpjCpfBeneficiario(rs.getString("numeroDocumento"));
        alterarCadastro.setNomeRazaoSocialBeneficiario(rs.getString("nomePessoaRazaoSocial"));
        alterarCadastro.setDataInicioRelacionamento(rs.getDate("dataInicioRelacionamento"));
        String nomeFantasia = rs.getString("nomeFantasia");
        alterarCadastro.setNomeFantasiaBeneficiario(ObjectUtil.isEmpty(nomeFantasia) ? alterarCadastro.getNomeRazaoSocialBeneficiario() : nomeFantasia);

        return alterarCadastro;
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException ConvenioAlteracaoDDADto
     * 
     */
    private ConvenioAlteracaoDDADto montarConvenioAlteracao(ResultSet rs) throws SQLException {
        ConvenioAlteracaoDDADto convenioAlteracao = new ConvenioAlteracaoDDADto();
        convenioAlteracao.setDataFimConvenio(rs.getDate("dataFimConvenio"));
        convenioAlteracao.setNumConta(null);
        convenioAlteracao.setNumAgencia(rs.getInt("agencia"));
        convenioAlteracao.setSitucaoConvenio(SituacaoConvenioBeneficiarioEnum.getBy(rs.getShort("situacaoConvenio")));
        convenioAlteracao.setTipoAgencia(TipoAgenciaEnum.FISICA);
        convenioAlteracao.setDataInicioConvenio(rs.getDate("dataInclusaoConvenio"));
        convenioAlteracao.setTipoProdutoConvenio(TipoProdutoConvenioEnum.BOLETO_DE_COBRANCA);
        convenioAlteracao.setTipoCarteiraConvenio(TipoCarteiraCobrancaEnum.getBy(rs.getString("tipoCarteira")));
        convenioAlteracao.setCodClienteConvenio(null);
        convenioAlteracao.setTipoConta(null);
        return convenioAlteracao;
    }

}
