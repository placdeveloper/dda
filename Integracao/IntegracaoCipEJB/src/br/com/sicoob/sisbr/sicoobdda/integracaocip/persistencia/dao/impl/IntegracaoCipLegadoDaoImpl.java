/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl
 * Arquivo:         IntegracaoCipLegadoDaoImpl.java
 * Data Criação:    Aug 16, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.util.StringUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CarteiraDeParaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.EspecieDocumentoDeParaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoCodigoJurosDeParaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoCodigoMultaDeParaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoPagamentoDeParaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.LinhaDigitavelCodigoBarrasUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaEfetiva;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagadorAgregado;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagadorConta;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoDesconto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoModeloCalculo;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipCrudDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipLegadoDao;

/**
 * IntegracaoCipLegadoDaoImpl
 * 
 * @author Rafael.Silva
 */
public class IntegracaoCipLegadoDaoImpl extends IntegracaoCipCrudDao<SicoobDDAEntidade> implements IntegracaoCipLegadoDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_integracaocip.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-integracaocip";
    public static final String COMANDO_PESQUISAR = "";

    private String caracteresRemover;
    private ParametroDao parametroDAO = ComumDaoFactory.getInstance().criarParametroDao();

    /**
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     */
    public IntegracaoCipLegadoDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, COMANDO_PESQUISAR);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipLegadoDao#gerarCargaDadosLegadoInclusao(java.lang.Integer)
     */
    public void gerarCargaDadosLegadoInclusao(Integer numCooperativa) {
        Connection conn = null;
        Comando comando = null;
        CallableStatement st = null;
        try {
            conn = estabelecerConexao(numCooperativa);
            getLogger().debug("########### Executando SPU_DDA_CARGA_TITULODDA_INCLUSAO()");
            st = conn.prepareCall("{call SPU_DDA_CARGA_TITULODDA_INCLUSAO()}");
            st.execute();
        } catch (SQLException e) {
            getLogger().erro(e, "Erro ao executar SPU_DDA_CARGA_TITULODDA_INCLUSAO");
            throw new PersistenciaException(e);
        } finally {
            fecharComando(comando);
            fecharStatement(st);
            fecharConexao(conn);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipLegadoDao#gerarCargaDadosLegadoAlteracao(java.lang.Integer)
     */
    public void gerarCargaDadosLegadoAlteracao(Integer numCooperativa) {
        Connection conn = null;
        Comando comando = null;
        CallableStatement st = null;
        try {
            conn = estabelecerConexao(numCooperativa);
            getLogger().debug("########### Executando SPU_DDA_CARGA_TITULODDA_ALTERACAO()");
            st = conn.prepareCall("{call SPU_DDA_CARGA_TITULODDA_ALTERACAO()}");
            st.execute();
        } catch (SQLException e) {
            getLogger().erro(e, "Erro ao executar SPU_DDA_CARGA_TITULODDA_ALTERACAO");
            throw new PersistenciaException(e);
        } finally {
            fecharComando(comando);
            fecharStatement(st);
            fecharConexao(conn);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipLegadoDao#gerarCargaDadosLegadoBaixa(java.lang.Integer)
     */
    public void gerarCargaDadosLegadoBaixa(Integer numCooperativa) {
        Connection conn = null;
        Comando comando = null;
        CallableStatement st = null;
        try {
            conn = estabelecerConexao(numCooperativa);
            getLogger().debug("########### Executando SPU_DDA_CARGA_TITULODDA_BAIXA()");
            st = conn.prepareCall("{call SPU_DDA_CARGA_TITULODDA_BAIXA()}");
            st.execute();
        } catch (SQLException e) {
            getLogger().erro(e, "Erro ao executar SPU_DDA_CARGA_TITULODDA_BAIXA");
            throw new PersistenciaException(e);
        } finally {
            fecharComando(comando);
            fecharStatement(st);
            fecharConexao(conn);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipLegadoDao#obterNumReferenciaAceiteBoleto(java.lang.Long)
     */
    public Long obterNumReferenciaAceiteBoleto(Long numIdentificadorBoletoCip) throws ComumException {
        return obterDados("OBTER_NUM_REF_ATUAL_ACEITE_BOLETO", getMapaParametro(numIdentificadorBoletoCip, "numIdentificadorBoletoCip"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipLegadoDao#listarMensagensPagadorDDA(java.lang.Integer)
     */
    public List<MensagemDDAPagador> listarMensagensPagadorDDA(Integer numCooperativa, Date dataReferencia, Integer agrupamentoCooperativa)
            throws ComumException {
        Connection conn = null;
        ResultSet rs = null;
        Comando comando = null;
        CallableStatement st = null;
        List<MensagemDDAPagador> listaMensagemDDAPagador = new ArrayList<MensagemDDAPagador>();
        try {
            conn = estabelecerConexao(numCooperativa);
            getLogger().debug("########### Executando SPU_DDA_ENVIO_PAGADORDDA()");
            st = conn.prepareCall("{call SPU_DDA_ENVIO_PAGADORDDA()}");
            rs = st.executeQuery();

            while (rs.next()) {
                listaMensagemDDAPagador.add(criarMensagemDDAPagador(rs, dataReferencia));
            }
        } catch (SQLException e) {
            getLogger().erro(e, "Erro ao executar SPU_DDA_ENVIO_PAGADORDDA");
            throw new PersistenciaException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharStatement(st);
            fecharConexao(conn);
        }

        return listaMensagemDDAPagador;
    }

    /**
     * Método responsável por setar atributos da SP no objeto de retorno
     * 
     * @param rs
     * @param dataReferencia
     * @return CargaMensagemPagadorDto
     * @throws SQLException
     * 
     */
    private MensagemDDAPagador criarMensagemDDAPagador(ResultSet rs, Date dataReferencia) throws SQLException {
        MensagemDDAPagador msgDDAPagador = new MensagemDDAPagador();
        msgDDAPagador.setMensagemDDA(criarMensagemEnvioDDA(rs.getString("CODMENSAGEM"), !ObjectUtil.isNull(dataReferencia) ? dataReferencia : rs.getDate("DATAMOVIMENTO")));
        msgDDAPagador.setIdEventoDDA(getNullableLong("IDEVENTODDA", rs));

        msgDDAPagador.setCodTipoPessoaPagador(rs.getString("CODTIPOPESSOAPAGADOR"));
        msgDDAPagador.setNumCpfCnpjPagador(rs.getString("NUMCPFCNPJPAGADOR"));
        msgDDAPagador.setBolPagadorEletronico(rs.getString("INDADESAODDA").equals("S"));
        msgDDAPagador.setCodTipoTermoPagador(rs.getShort("CODTIPOTERMOPAGADOR"));
        if (!ObjectUtil.isEmpty(rs.getString("CODTIPOMANUTCONTA"))) {
            msgDDAPagador.getListaMensagemDDAPagadorConta().add(criarMensagemDDAPagadorConta(rs, msgDDAPagador));
        }
        if (!ObjectUtil.isEmpty(rs.getString("CODTIPOMANUTAGREGADO"))) {
            msgDDAPagador.getListaMensagemDDAPagadorAgregado().add(criarMensagemDDAPagadorAgregado(rs, msgDDAPagador));
        }
        return msgDDAPagador;
    }

    /**
     * Método responsável por criar MensagemDDAPagadorAgregado
     * 
     * @param rs
     * @param msgDDAPagador
     * @return
     * @throws SQLException MensagemDDAPagadorAgregado
     * 
     */
    private MensagemDDAPagadorAgregado criarMensagemDDAPagadorAgregado(ResultSet rs, MensagemDDAPagador msgDDAPagador) throws SQLException {
        MensagemDDAPagadorAgregado msgAgregado = new MensagemDDAPagadorAgregado();
        msgAgregado.setCodTipoOperacao(rs.getString("CODTIPOMANUTAGREGADO"));
        msgAgregado.setCodTipoPessoaAgregado(rs.getString("CODTIPOPESSOAAGREGADO"));
        msgAgregado.setNumCpfCnpjAgregado(rs.getString("NUMCPFCNPJAGREGADO"));
        msgAgregado.setMensagemDDAPagador(msgDDAPagador);
        return msgAgregado;
    }

    /**
     * Método responsável por criarMensagemDDAPagadorConta
     * 
     * @param rs
     * @param msgDDAPagador
     * @return
     * @throws SQLException MensagemDDAPagadorConta
     * 
     */
    private MensagemDDAPagadorConta criarMensagemDDAPagadorConta(ResultSet rs, MensagemDDAPagador msgDDAPagador) throws SQLException {
        MensagemDDAPagadorConta msgPagadorConta = new MensagemDDAPagadorConta();
        msgPagadorConta.setCodTipoOperacao(rs.getString("CODTIPOMANUTCONTA"));
        msgPagadorConta.setDataHoraAdesao(DateUtil.getDateTimeDB(rs.getDate("DATAMOVIMENTO")));
        msgPagadorConta.setMensagemDDAPagador(msgDDAPagador);
        msgPagadorConta.setNumAgencia(getNullableInt("NUMAGENCIA", rs));
        msgPagadorConta.setNumContaCorrente(rs.getBigDecimal("NUMCONTACORRENTE"));
        return msgPagadorConta;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipLegadoDao#obterPagadorBoleto(java.lang.Long)
     */
    public BoletoDDA obterPagadorBoleto(Long numIdentificadorBoletoCip) throws ComumException {
        return obterDados("OBTER_PAGADOR_BOLETO", getMapaParametro(numIdentificadorBoletoCip, "numIdentificadorBoletoCip"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipLegadoDao#listarMensagensBoleto(java.lang.Integer)
     */
    public List<MensagemDDABoleto> listarMensagensBoleto(Integer numCooperativa, Date dataReferencia, Boolean bolHabilitarModeloCalculo01, Integer agrupamentoCooperativa)
            throws ComumException {
        Connection conn = null;
        ResultSet rs = null;
        Comando comando = null;
        CallableStatement st = null;
        List<MensagemDDABoleto> listaMensagemDDABoleto = new ArrayList<MensagemDDABoleto>();

        preencherParametroCaracteresRemover();

        try {
            conn = estabelecerConexao(numCooperativa);
            getLogger().debug("########### Executando SPU_DDA_ENVIO_BOLETODDA()");
            st = conn.prepareCall("{call SPU_DDA_ENVIO_BOLETODDA(agrupamento)}");
            rs = st.executeQuery();

            while (rs.next()) {
                listaMensagemDDABoleto.add(criarMensagemDDABoleto(rs, dataReferencia, bolHabilitarModeloCalculo01));
            }
        } catch (SQLException e) {
            getLogger().erro(e, "Erro ao executar SPU_DDA_ENVIO_BOLETODDA");
            throw new PersistenciaException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharStatement(st);
            fecharConexao(conn);
        }

        return listaMensagemDDABoleto;
    }

    /**
     * Método responsável por criarMensagemDDABoleto
     * 
     * @param rs
     * @param dataReferencia
     * @return MensagemDDABoleto
     * @throws SQLException
     * @throws ComumException
     */
    private MensagemDDABoleto criarMensagemDDABoleto(ResultSet rs, Date dataReferencia, Boolean bolHabilitarModeloCalculo01) throws SQLException, ComumException {
        MensagemDDABoleto msgBoleto = new MensagemDDABoleto();
        msgBoleto.setMensagemDDA(criarMensagemEnvioDDA(rs.getString("CODMENSAGEM"), !ObjectUtil.isNull(dataReferencia) ? dataReferencia : rs.getDate("DATAMOVIMENTO")));
        msgBoleto.getMensagemDDA().setNumPrioridadeEnvio(getNullableInt("NumPrioridadeEnvio", rs));
        msgBoleto.setIdOperacaoLeg(getNullableLong("IDOPERACAOLEG", rs));
        // beneficiario
        setBeneficiarioBoleto(rs, msgBoleto);
        // pagador
        setPagadorBoleto(rs, msgBoleto);
        // SacadorAvalista
        if (!ObjectUtil.isEmpty(rs.getString("NUMCPFCNPJAVALISTA"))) {
            setSacadorAvalistaBoleto(rs, msgBoleto);
        } else {
            msgBoleto.setCodTipoPessoaDDAAvalista("0");
        }
        // Boleto
        msgBoleto.setNumCodigoBarra(rs.getString("NUMCODIGOBARRA"));
        msgBoleto.setNumCodBarrasCampoLivre(
                !ObjectUtil.isEmpty(msgBoleto.getNumCodigoBarra()) ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(msgBoleto.getNumCodigoBarra()) : null);
        msgBoleto.setNumLinhaDigitavel(rs.getString("NUMLINHADIGITAVEL"));
        msgBoleto.setNumNossoNumero(rs.getString("NUMNOSSONUMERO"));
        msgBoleto.setNumDocumento(rs.getString("NUMDOCUMENTO"));
        msgBoleto.setDataEmissao(DateUtil.getDateTimeDB(rs.getDate("DATAEMISSAO")));
        msgBoleto.setDataVencimento(DateUtil.getDateTimeDB(rs.getDate("DATAVENCIMENTO")));
        msgBoleto.setDataLimitePagamento(DateUtil.getDateTimeDB(rs.getDate("DATALIMITEPAGAMENTO")));
        msgBoleto.setIdCarteira(CarteiraDeParaEnum.getIdCarteiraCip(getNullableInt("IDCARTEIRA_DEPARA", rs)));
        msgBoleto.setIdEspecieDocumento(EspecieDocumentoDeParaEnum.getIdEspecieDocumentoCip(rs.getString("SIGLAESPECIEDOCUMENTO_DEPARA")));
        msgBoleto.setIdOrgaoMoeda(getNullableInt("IDORGAOMOEDA", rs));
        msgBoleto.setCodMoeda(rs.getString("CODMOEDA"));
        msgBoleto.setNumParcela(getNullableInt("NUMPARCELA", rs));
        msgBoleto.setQtdPagamentoParcial(getNullableInt("NUMTOTALPAGAMENTOPARCIAL", rs));
        msgBoleto.setQtdTotalParcela(getNullableInt("QTDTOTALPARCELA", rs));
        msgBoleto.setValorAbatimento(rs.getBigDecimal("VALORABATIMENTO"));
        msgBoleto.setValorBoleto(rs.getBigDecimal("VALORBOLETO"));
        msgBoleto.setBolBloqueioPagamento(rs.getBoolean("BOLBLOQUEIOPAGAMENTO"));
        msgBoleto.setBolPagamentoParcial(rs.getBoolean("BOLPAGAMENTOPARCIAL"));
        msgBoleto.setBolTituloNegociado(rs.getBoolean("BOLTITULONEGOCIADO"));
        msgBoleto.setCodAutorizacaoValorDivergente(rs.getString("CODAUTORIZACAOVALORDIVERGENTE"));

        msgBoleto.setCodTipoModeloCalculo(bolHabilitarModeloCalculo01 ? TipoModeloCalculo.INSTITUICAO_RECEBEDORA_CALCULA_TITULOS_A_VENCER_E_VENCIDOS
                : TipoModeloCalculo.CIP_INSTITUICAO_RECEBEDORA_CALCULA_SALDO_PARCIAL_E_TITULOS_A_VENCER_E_VENCIDOS_NA_CONSULTA);

        msgBoleto.setNumDiasProtesto(getNullableInt("NUMDIASPROTESTO", rs));
        msgBoleto.setCodTipoPagamento(TipoPagamentoDeParaEnum.getIdTipoPagamentoCip(getNullableInt("CODTIPOPAGAMENTO_DEPARA", rs)));

        definirJuros(rs, msgBoleto);
        definirMulta(rs, msgBoleto);
        definirDesconto(rs, msgBoleto);

        msgBoleto.setCodIndicadorValorMaximo(rs.getString("CODINDICADORVALORMAXIMO"));
        msgBoleto.setCodIndicadorValorMinimo(rs.getString("CODINDICADORVALORMINIMO"));
        msgBoleto.setValorMaximo(rs.getBigDecimal("VALORMAXIMO"));
        msgBoleto.setValorMinimo(rs.getBigDecimal("VALORMINIMO"));
        msgBoleto.setListaMensagemDDABoletoGrupoCalculo(null);
        msgBoleto.setListaMensagemDDABoletoNotaFiscal(null);
        msgBoleto.setListaMensagemDDABoletoTextoInfo(null);

        return msgBoleto;
    }

    /**
     * Método responsável por obter o parâmetro
     * 
     * @throws ComumException
     */
    private void preencherParametroCaracteresRemover() throws ComumException {
        debug("Obtendo parâmetro dos caracteres a remover...");
        caracteresRemover = parametroDAO.obterValor(ParametroDDA.CARACTERES_REMOVER_INCLUSAO_BOLETO, Constantes.ID_SICOOB);
        debug("caracteresRemover: " + caracteresRemover);
    }

    /**
     * Método responsável por remover os caracteres especiais do texto
     * 
     * @param txt
     * @return
     * @throws ComumException
     */
    private String removerCaracteresEspeciais(String txt) throws ComumException {
        if (ObjectUtil.isEmpty(txt)) {
            return txt;
        }

        String texto = ObjectUtil.removerCaracteresInvalidos(StringUtil.substituirCaracteresEspeciais(txt));

        // Se há caracteres para tratamento
        if (!ObjectUtil.isEmpty(caracteresRemover)) {
            texto = texto.replaceAll(caracteresRemover, "");
        }

        return texto;
    }

    /**
     * Método responsável por setBeneficiarioBoleto
     * 
     * @param rs
     * @param msgBoleto
     * @throws SQLException void
     * @throws ComumException
     * 
     */
    private void setBeneficiarioBoleto(ResultSet rs, MensagemDDABoleto msgBoleto) throws SQLException, ComumException {
        msgBoleto.setCodTipoPessoaBeneficiario(rs.getString("CODTIPOPESSOABENEFICIARIO"));
        msgBoleto.setNumCpfCnpjBeneficiario(rs.getString("NUMCPFCNPJBENEFICIARIO"));
        msgBoleto.setNomeBeneficiario(removerCaracteresEspeciais(rs.getString("NOMEBENEFICIARIO")));
        msgBoleto.setNomeFantasiaBeneficiario(removerCaracteresEspeciais(rs.getString("NOMEFANTASIABENEFICIARIO")));
        msgBoleto.setDescLogradouroBeneficiario(rs.getString("DESCLOGRADOUROBENEFICIARIO"));
        msgBoleto.setDescCidadeBeneficiario(rs.getString("DESCCIDADEBENEFICIARIO"));
        msgBoleto.setSiglaUfBeneficiario(rs.getString("SIGLAUFBENEFICIARIO"));
        msgBoleto.setNumCepBeneficiario(rs.getString("NUMCEPBENEFICIARIO"));
        if (!ObjectUtil.isEmpty(rs.getString("NUMCPFCNPJBENEFICIARIOFINAL"))) {
            msgBoleto.setCodTipoPessoaBeneficiarioFinal(rs.getString("CODTIPOPESSOABENEFICIARIOFINAL"));
            msgBoleto.setNumCpfCnpjBeneficiarioFinal(rs.getString("NUMCPFCNPJBENEFICIARIOFINAL"));
            msgBoleto.setNomeBeneficiarioFinal(removerCaracteresEspeciais(rs.getString("NOMEBENEFICIARIOFINAL")));
            msgBoleto.setNomeFantasiaBeneficiarioFinal(removerCaracteresEspeciais(rs.getString("NOMEFANTASIABENEFICIARIOFINAL")));
        }
    }

    /**
     * Método responsável por setPagadorBoleto
     * 
     * @param rs
     * @param msgBoleto
     * @throws SQLException void
     * @throws ComumException
     * 
     */
    private void setPagadorBoleto(ResultSet rs, MensagemDDABoleto msgBoleto) throws SQLException, ComumException {
        msgBoleto.setCodTipoPessoaPagador(rs.getString("CODTIPOPESSOAPAGADOR"));
        msgBoleto.setNumCpfCnpjPagador(rs.getString("NUMCPFCNPJPAGADOR"));
        msgBoleto.setNomePagador(removerCaracteresEspeciais(rs.getString("NOMEPAGADOR")));
        msgBoleto.setNomeFantasiaPagador(removerCaracteresEspeciais(rs.getString("NOMEFANTASIAPAGADOR")));
        msgBoleto.setDescLogradouroPagador(rs.getString("DESCLOGRADOUROPAGADOR"));
        msgBoleto.setDescCidadePagador(rs.getString("DESCCIDADEPAGADOR"));
        msgBoleto.setSiglaUfPagador(rs.getString("SIGLAUFPAGADOR"));
        msgBoleto.setNumCepPagador(rs.getString("NUMCEPPAGADOR"));
    }

    /**
     * Método responsável por DateUtil.getDateTimeDB(
     * 
     * @param rs
     * @param msgBoleto
     * @throws SQLException void
     * @throws ComumException
     */
    private void setSacadorAvalistaBoleto(ResultSet rs, MensagemDDABoleto msgBoleto) throws SQLException, ComumException {
        msgBoleto.setCodTipoPessoaDDAAvalista(rs.getString("CODTIPOPESSOADDAAVALISTA"));
        msgBoleto.setNumCpfCnpjAvalista(rs.getString("NUMCPFCNPJAVALISTA"));
        msgBoleto.setNomeAvalista(removerCaracteresEspeciais(rs.getString("NOMEAVALISTA")));
    }

    /**
     * Método responsável por definir os juros
     * 
     * @param rs
     * @param mensagemDDABoleto
     * @throws SQLException List<MensagemDDABoletoJuros>
     */
    private void definirJuros(ResultSet rs, MensagemDDABoleto mensagemDDABoleto) throws SQLException {
        if (!ObjectUtil.isEmpty(getNullableInt("CODTIPOJUROS_DEPARA", rs))) {
            mensagemDDABoleto.setCodTipoJuros(TipoCodigoJurosDeParaEnum.getTipoCodigoJurosDeParaEnum(getNullableInt("CODTIPOJUROS_DEPARA", rs)));
            mensagemDDABoleto.setDataJuros(DateUtil.getDateTimeDB(rs.getDate("DATAJUROS")));
            mensagemDDABoleto.setValorPercentualJuros(rs.getBigDecimal("VALORPERCENTUALJUROS"));
        }
    }

    /**
     * Método responsável por definir a multa
     * 
     * @param rs
     * @param mensagemDDABoleto
     * @throws SQLException List<MensagemDDABoletoMulta>
     */
    private void definirMulta(ResultSet rs, MensagemDDABoleto mensagemDDABoleto) throws SQLException {
        // FIXME rodrigo.neri testar merge
        if (!ObjectUtil.isEmpty(getNullableInt("CODTIPOMULTA_DEPARA", rs))) {
            mensagemDDABoleto.setCodTipoMulta(TipoCodigoMultaDeParaEnum.getTipoCodigoMultaDeParaEnum(getNullableInt("CODTIPOMULTA_DEPARA", rs)));
            mensagemDDABoleto.setDataMulta(DateUtil.getDateTimeDB(rs.getDate("DATAMULTA")));
            mensagemDDABoleto.setValorPercentualMulta(rs.getBigDecimal("VALORPERCENTUALMULTA"));
        }
    }

    /**
     * Método responsável por definir o desconto
     * 
     * @param rs
     * @param mensagemDDABoleto
     * @throws SQLException List<MensagemDDABoletoDesconto>
     */
    private void definirDesconto(ResultSet rs, MensagemDDABoleto mensagemDDABoleto) throws SQLException {
        if (!ObjectUtil.isEmpty(rs.getString("CODTIPODESCONTO1"))) {
            mensagemDDABoleto.setCodTipoDesconto1(rs.getString("CODTIPODESCONTO1"));
            mensagemDDABoleto.setDataDesconto1(DateUtil.getDateTimeDB(rs.getDate("DATADESCONTO1")));
            mensagemDDABoleto.setValorPercentualDesconto1(rs.getBigDecimal("VALORPERCENTUALDESCONTO1"));
        }

        // Caso seja nulo ou Isento (0) nao seta a mensagemDesconto e o TipoDesconto1 nao pode ser isento
        if (!ObjectUtil.isEmpty(rs.getString("CODTIPODESCONTO2")) && !ObjectUtil.isEmpty(rs.getString("CODTIPODESCONTO1"))
                && !rs.getString("CODTIPODESCONTO1").equals(TipoDesconto.ISENTO)) {
            mensagemDDABoleto.setCodTipoDesconto2(rs.getString("CODTIPODESCONTO2"));
            mensagemDDABoleto.setDataDesconto2(DateUtil.getDateTimeDB(rs.getDate("DATADESCONTO2")));
            mensagemDDABoleto.setValorPercentualDesconto2(rs.getBigDecimal("VALORPERCENTUALDESCONTO2"));
        }

        if (!ObjectUtil.isEmpty(rs.getString("CODTIPODESCONTO3")) && !ObjectUtil.isEmpty(rs.getString("CODTIPODESCONTO1"))
                && !rs.getString("CODTIPODESCONTO1").equals(TipoDesconto.ISENTO)) {
            mensagemDDABoleto.setCodTipoDesconto3(rs.getString("CODTIPODESCONTO3"));
            mensagemDDABoleto.setDataDesconto3(DateUtil.getDateTimeDB(rs.getDate("DATADESCONTO3")));
            mensagemDDABoleto.setValorPercentualDesconto3(rs.getBigDecimal("VALORPERCENTUALDESCONTO3"));
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipLegadoDao#listarMensagensBaixaEfetivaDDA(java.lang.Integer)
     */
    public List<MensagemDDABaixaEfetiva> listarMensagensBaixaEfetivaDDA(Integer numCooperativa, Date dataReferencia, Integer agrupamentoCooperativa) {
        Connection conn = null;
        ResultSet rs = null;
        Comando comando = null;
        CallableStatement st = null;
        List<MensagemDDABaixaEfetiva> listaMensagemDDABaixaEfetiva = new ArrayList<MensagemDDABaixaEfetiva>();
        try {
            conn = estabelecerConexao(numCooperativa);
            getLogger().debug("########### Executando SPU_DDA_ENVIO_BAIXAEFETIVA()");
            st = conn.prepareCall("{call SPU_DDA_ENVIO_BAIXAEFETIVA()}");
            rs = st.executeQuery();

            while (rs.next()) {
                listaMensagemDDABaixaEfetiva.add(criarMensagemDDABaixaEfetiva(rs, dataReferencia));
            }
        } catch (SQLException e) {
            getLogger().erro(e, "Erro ao executar SPU_DDA_ENVIO_BAIXAEFETIVA");
            throw new PersistenciaException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharStatement(st);
            fecharConexao(conn);
        }

        return listaMensagemDDABaixaEfetiva;
    }

    /**
     * Método responsável por cirar uma MensagemDDABaixaEfetiva
     * 
     * @param rs
     * @return MensagemDDABaixaEfetiva
     * @throws SQLException
     * 
     */
    private MensagemDDABaixaEfetiva criarMensagemDDABaixaEfetiva(ResultSet rs, Date dataReferencia) throws SQLException {
        MensagemDDABaixaEfetiva msgBxEfetiva = new MensagemDDABaixaEfetiva();
        msgBxEfetiva.setMensagemDDA(criarMensagemEnvioDDA(rs.getString("CODMENSAGEM"), !ObjectUtil.isNull(dataReferencia) ? dataReferencia : rs.getDate("DATAMOVIMENTO")));
        msgBxEfetiva.setIdOperacaoLeg(getNullableLong("IDOPERACAOLEG", rs));
        msgBxEfetiva.setCodCanalPagamento(null);
        msgBxEfetiva.setCodMeioPagamento(null);
        msgBxEfetiva.setCodTipoBaixaEfetiva(getNullableInt("CODTIPOBAIXAEFETIVA", rs));
        msgBxEfetiva.setNumCodigoBarra(rs.getString("NUMCODIGOBARRA"));
        msgBxEfetiva.setNumCodBarrasCampoLivre(rs.getString("NUMCODIGOBARRA") != null ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(rs.getString("NUMCODIGOBARRA")) : null);

        msgBxEfetiva.setValorBaixa(rs.getBigDecimal("VALORCOBRADO"));

        return msgBxEfetiva;
    }

    /**
     * Método responsável por criarMensagemEnvioDDA
     * 
     * @param codTipoMensagem
     * @param dataMovimento
     * @return MensagemDDA
     * 
     */
    private MensagemDDA criarMensagemEnvioDDA(String codTipoMensagem, Date dataMovimento) {
        MensagemDDA msgDDA = new MensagemDDA();
        msgDDA.setTipoMensagemDDA(new TipoMensagemDDA(codTipoMensagem));
        msgDDA.setDataMovimento(DateUtil.getDateTimeDB(dataMovimento));
        msgDDA.setBolOrigemSicoob(Boolean.TRUE);
        msgDDA.setDataHoraCadastro(new DateTimeDB());
        return msgDDA;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipLegadoDao#atualizarDataMovimentoTituloDDA(java.lang.Long,
     *      java.lang.Integer)
     */
    public void atualizarDataMovimentoTituloDDA(Long idOperacaoLeg, Integer numCooperativa) throws ComumException {
        getLogger().debug("####### Executando ATUALIZAR_DATAMOVIMENTO_TITULODDA com parametro idOperacaoLeg = " + idOperacaoLeg);
        executarUpdate("ATUALIZAR_DATAMOVIMENTO_TITULODDA", getMapaParametrosId(idOperacaoLeg), numCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipLegadoDao#atualizarDataEventoTituloDDA(java.lang.Long, java.lang.Integer)
     */
    public void atualizarDataEventoTituloDDA(Long idEventoTituloDDA, Integer numCooperativa) throws ComumException {
        getLogger().debug("####### Executando ATUALIZAR_DATAMOVIMENTO_EVENTOTITULODDA com parametro idEventoTituloDDA = " + idEventoTituloDDA);
        executarUpdate("ATUALIZAR_DATAEVENTO_EVENTOTITULODDA", getMapaParametrosId(idEventoTituloDDA), numCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipLegadoDao#atualizarDataEventoDDA(java.lang.Long, java.lang.Integer)
     */
    public void atualizarDataEventoDDA(Long idEventoDDA, Integer numCooperativa) throws ComumException {
        getLogger().debug("####### Executando ATUALIZAR_DATAEVENTO_EVENTODDA com parametro idEventoDDA = " + idEventoDDA);
        executarUpdate("ATUALIZAR_DATAEVENTO_EVENTODDA", getMapaParametrosId(idEventoDDA), numCooperativa);
    }

    /**
     * Método responsável por
     * 
     * @param id
     * @return Map<String,Object>
     * 
     */
    private Map<String, Object> getMapaParametrosId(Long id) {
        return getMapaParametro(id, "id");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipLegadoDao#obterQtdAgrupamentoCooperativa(java.lang.Integer)
     */
    public Integer obterQtdAgrupamentoCooperativa(Integer numCooperativa) throws ComumException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipLegadoDao#atualizarTituloDDAAgrupador(java.lang.Integer, int)
     */
    public void atualizarTituloDDAAgrupador(Integer numCooperativa, int agrupador) throws ComumException {
        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipLegadoDao#listaAgrupaCooperativaEspecificaBoleto(java.lang.Integer)
     */
    public List<Integer> listaAgrupaCooperativaEspecificaBoleto(Integer numCooperativa) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipLegadoDao#listaAgrupaCooperativaEspecificaBaixa(java.lang.Integer)
     */
    public List<Integer> listaAgrupaCooperativaEspecificaBaixa(Integer numCooperativa) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipLegadoDao#listaAgrupaCooperativaEspecificaPagador(java.lang.Integer)
     */
    public List<Integer> listaAgrupaCooperativaEspecificaPagador(Integer numCooperativa) {
        // TODO Auto-generated method stub
        return null;
    }
}
