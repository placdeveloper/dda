package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.NameNotFoundException;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.CooperativaNaoEncontradaNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaBoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.FormatadorUtil;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.AgendamentoBoletoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDao;

/**
 * AgendamentoBoletoDaoImpl é responsável por
 * 
 * @author Rodrigo.Neri
 */
public class AgendamentoBoletoDaoImpl extends OperacionalCrudDao<SicoobDDAEntidade> implements AgendamentoBoletoDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_operacional.agendamento.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional-agendamento";

    public AgendamentoBoletoDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.AgendamentoBoletoDao#obterBoletoPorCodBarras(java.lang.Integer, java.lang.Integer,
     *      java.lang.Integer, java.lang.String, java.util.Date)
     */
    public ConsultaBoletoDDADto obterBoletoPorCodBarras(Integer numCooperativaCodBarras, Integer numCooperativa, Integer numPA, String codigoBarras, Date dataPagamento)
            throws ComumException, ComumNegocioException {
        debug("### Obtendo o boleto por código de barras");
        debug("Parâmetro - numCooperativaCodBarras: " + numCooperativaCodBarras);
        debug("Parâmetro - numCooperativa: " + numCooperativa);
        debug("Parâmetro - numPA: " + numPA);
        debug("Parâmetro - codigoBarras: " + codigoBarras);
        debug("Parâmetro - dataPagamento: " + dataPagamento);

        ConsultaBoletoDDADto dto = null;
        CallableStatement cs = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = estabelecerConexao(numCooperativaCodBarras);

            cs = conn.prepareCall("{call SP_COB_Calcular_Encargos_Titulo (?,?,?,?,?)}");

            cs.setString(1, codigoBarras);
            cs.setDate(2, new java.sql.Date(dataPagamento.getTime()));
            cs.setInt(3, numCooperativa);
            cs.setInt(4, numPA);
            cs.setBoolean(5, Boolean.TRUE); // Validar feriado local

            debug("Executando a SP_COB_Calcular_Encargos_Titulo...");
            rs = cs.executeQuery();

            if (rs.next()) {
                dto = popularDTO(rs);
            }
        } catch (PersistenciaException e) {
            tratarPersistenciaException(numCooperativaCodBarras, e);
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharStatement(cs);
            fecharConexao(conn);
        }

        return dto;
    }

    /**
     * Método responsável por popular o DTO
     * 
     * @param dataPagamento
     * @param rs
     * @param numPA
     * @return
     * @throws SQLException
     */
    private ConsultaBoletoDDADto popularDTO(ResultSet rs) throws SQLException {
        ConsultaBoletoDDADto dto = new ConsultaBoletoDDADto();

        Date dataVencimento = rs.getDate("DataVencimentoTitulo");
        if (!ObjectUtil.isNull(dataVencimento)) {
            dto.setDataVencimentoBoleto(new DateTimeDB(dataVencimento.getTime()));
        }

        dto.setQtdDiasProtesto(rs.getInt("QtdDiasProtesto"));

        dto.setNumInstituicaoEmissora(rs.getShort("NumBancoDep"));

        // Beneficiário
        String cnpjCpfBeneficiario = rs.getString("NumCnpjCpfBeneficiario");
        dto.setNumCnpjCpfBeneficiario(cnpjCpfBeneficiario);
        if (!ObjectUtil.isEmpty(cnpjCpfBeneficiario)) {
            if (cnpjCpfBeneficiario.length() == Constantes.TAMANHO_CNPJ) {
                dto.setTipoPessoaBeneficiario(Constantes.TIPO_PESSOA_JURIDICA);
            } else {
                dto.setTipoPessoaBeneficiario(Constantes.TIPO_PESSOA_FISICA);
            }
        }

        String nome = rs.getString("DescNomePessoa");
        if (ObjectUtil.isEmpty(nome)) {
            nome = rs.getString("DescNomeCompleto");
        }

        dto.setNomeRazaoSocialBeneficiario(nome);

        // Sacador
        dto.setNomeRazaoSocialSacadorAvalista(rs.getString("DescNomeSacador"));
        String cnpjCpfSacadorAvalista = rs.getString("NumCnpjCpfSacador");
        dto.setNumCnpjCpfSacadorAvalista(cnpjCpfSacadorAvalista);
        if (!ObjectUtil.isEmpty(cnpjCpfSacadorAvalista)) {
            if (cnpjCpfSacadorAvalista.length() == Constantes.TAMANHO_CNPJ) {
                dto.setTipoPessoaSacadorAvalista(Constantes.TIPO_PESSOA_JURIDICA);
            } else {
                dto.setTipoPessoaSacadorAvalista(Constantes.TIPO_PESSOA_FISICA);
            }
        }

        // Pagador
        dto.setNomeRazaoSocialPagador(rs.getString("DescNomeSacado"));
        String cnpjCpfPagador = rs.getString("NumCnpjCpfSacado");
        dto.setNumCnpjCpfPagador(cnpjCpfPagador);
        if (!ObjectUtil.isEmpty(cnpjCpfPagador)) {
            if (cnpjCpfPagador.length() == Constantes.TAMANHO_CNPJ) {
                dto.setTipoPessoaPagador(Constantes.TIPO_PESSOA_JURIDICA);
            } else {
                dto.setTipoPessoaPagador(Constantes.TIPO_PESSOA_FISICA);
            }
        }

        dto.setNumNossoNumero(rs.getString("NumTitulo"));
        dto.setNumDocumento(rs.getString("DescSeuNumero"));

        dto.setValorBoleto(rs.getBigDecimal("ValorTitulo"));
        dto.setValorMultaMora(rs.getBigDecimal("ValorEncargos"));
        dto.setValorAbatimentoDesconto(rs.getBigDecimal("ValorAbatimentoDesconto"));
        dto.setValorPagamento(rs.getBigDecimal("ValorFinal"));

        tratarDesconto(dto, rs);

        return dto;
    }

    /**
     * Método responsável por criar as instruções de desconto.
     * 
     * @param dto
     * @param dataPagamentok
     * @param rs
     * @throws SQLException
     */
    private void tratarDesconto(ConsultaBoletoDDADto dto, ResultSet rs) throws SQLException {
        debug("### Tratando o desconto...");

        Date dataDesconto1 = rs.getDate("DataPrimDesconto");
        double valorDesconto1 = rs.getDouble("ValorPrimDesconto");

        dto.setInstrucaoDesconto1(criarInstrucaoDesconto(dataDesconto1, valorDesconto1));

        Date dataDesconto2 = rs.getDate("DataSegDesconto");
        double valorDesconto2 = rs.getDouble("ValorSegDesconto");

        dto.setInstrucaoDesconto2(criarInstrucaoDesconto(dataDesconto2, valorDesconto2));

        Date dataDesconto3 = rs.getDate("DataTercDesconto");
        double valorDesconto3 = rs.getDouble("ValorTercDesconto");

        dto.setInstrucaoDesconto3(criarInstrucaoDesconto(dataDesconto3, valorDesconto3));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.AgendamentoBoletoDao#obterAlertaPagamentoBoletoVencidoCaixaOuCorrespondente(java.lang.Integer,
     *      java.lang.String, java.lang.Short, java.lang.Integer)
     */
    public String obterAlertaPagamentoBoletoVencidoCaixaOuCorrespondente(Integer numCooperativa, String linhaDigitavelCodigoBarras, Short idCanal, Integer numPac)
            throws ComumException, ComumNegocioException {
        debug("### Obtendo mensagem de alerta para pagamento de boleto vencido para o CAIXA ou Correspondente...");

        Connection conn = null;
        CallableStatement cs = null;

        try {
            debug("Estabelecendo consulta: " + numCooperativa);
            conn = estabelecerConexao(numCooperativa);

            cs = conn.prepareCall("{call SPU_COB_VERIFICAR_TITULO_VENCIDO (?,?,?,?,?,?,?,?)}");

            cs.registerOutParameter(1, java.sql.Types.TINYINT);
            cs.registerOutParameter(2, java.sql.Types.VARCHAR);
            cs.registerOutParameter(3, java.sql.Types.VARCHAR);
            // 1- Codigo de Barras - 2 - linhaDigitavel
            cs.setInt(4, linhaDigitavelCodigoBarras.length() == Constantes.TAMANHO_CODIGO_BARRAS ? 1 : 2);
            cs.setString(5, linhaDigitavelCodigoBarras.length() == Constantes.TAMANHO_CODIGO_BARRAS ? linhaDigitavelCodigoBarras : null);
            cs.setString(6, linhaDigitavelCodigoBarras.length() == Constantes.TAMANHO_LINHA_DIGITAVEL ? linhaDigitavelCodigoBarras : null);
            cs.setInt(7, numPac);
            cs.setInt(8, idCanal.intValue());

            debug("Executando a SPU_COB_VERIFICAR_TITULO_VENCIDO...");
            cs.execute();

            String mensagemAlerta = cs.getString(2);
            debug("Mensagem de alerta: " + mensagemAlerta);

            if (!ObjectUtil.isEmpty(mensagemAlerta)) {
                return mensagemAlerta;
            }
        } catch (PersistenciaException e) {
            tratarPersistenciaException(numCooperativa, e);
        } catch (SQLException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException(e);
        } finally {
            fecharConexao(conn);
            fecharStatement(cs);
        }

        return null;
    }

    /**
     * Método responsável por criar a instrução de desconto.
     * 
     * @param dataDesconto
     * @param valorDesconto
     * @return String
     * 
     */
    private String criarInstrucaoDesconto(Date dataDesconto, Double valorDesconto) {
        debug("### Criando a instrução dedesconto");
        debug("Parâmetro - dataDesconto: " + dataDesconto);
        debug("Parâmetro - valorDesconto: " + valorDesconto);

        if (ObjectUtil.isNull(dataDesconto) && ObjectUtil.isEmpty(valorDesconto)) {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        sb.append("Conceder desconto de ");
        sb.append(FormatadorUtil.formatarValorReal(valorDesconto));
        sb.append(" até ");
        sb.append(new SimpleDateFormat(Constantes.FORMATO_DE_DATA_DD_MM_YYYY).format(dataDesconto));
        sb.append(".");

        String instrucao = sb.toString();

        debug("Instrução criada: " + instrucao);

        return instrucao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.AgendamentoBoletoDao#obterDataUtil(int, int, java.util.Date)
     */
    public Date obterDataUtil(int numCooperativa, int numPA, Date data) throws ComumException, ComumNegocioException {
        return obterDataUtil(numCooperativa, numPA, data, true);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.AgendamentoBoletoDao#obterDataUtil(int, int, java.util.Date, boolean)
     */
    public Date obterDataUtil(int numCooperativa, int numPA, Date data, boolean validarFeriadoLocal) throws ComumException, ComumNegocioException {
        debug("### Obtendo data útil...");
        debug("Parâmetro - numCooperativa: " + numCooperativa);
        debug("Parâmetro - numPA: " + numPA);
        debug("Parâmetro - data: " + data);
        debug("Parâmetro - validarFeriadoLocal: " + validarFeriadoLocal);

        Connection conn = null;
        CallableStatement cs = null;

        Date resultado = null;

        try {
            debug("Estabelecendo conexão...");
            conn = estabelecerConexao(numCooperativa);
            debug("Conexão estabelecida.");

            debug("Executando SP_COB_ObterDataUtil...");
            cs = conn.prepareCall("{call dbo.SP_COB_ObterDataUtil(?, ?, ?, ?, ?, ?, ?)}");

            debug("Definindo parâmetros...");
            cs.registerOutParameter(1, java.sql.Types.INTEGER);
            cs.registerOutParameter(2, java.sql.Types.VARCHAR);
            cs.registerOutParameter(3, java.sql.Types.DATE);
            cs.setInt(4, numCooperativa);
            cs.setInt(5, numPA);
            cs.setDate(6, new java.sql.Date(data.getTime()));
            cs.setBoolean(7, validarFeriadoLocal);

            debug("Executando SP...");
            cs.execute();

            debug("Obtendo resultado...");
            java.sql.Date dataSql = cs.getDate(3);

            debug("Resultado: " + resultado);

            if (!ObjectUtil.isNull(dataSql)) {
                debug("Definindo data de retorno...");
                resultado = new Date(dataSql.getTime());
                debug("Resultado: " + resultado);
            } else {
                debug("Resultado nulo");

                String erro = cs.getString(2);
                debug("Erro: " + erro);

                // Verifica se existe algum retorno de erro.
                if (!ObjectUtil.isEmpty(erro)) {
                    throw new ComumNegocioException(erro);
                }
            }
        } catch (PersistenciaException e) {
            tratarPersistenciaException(numCooperativa, e);
        } catch (ComumNegocioException e) {
            throw e;
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharConexao(conn);
            fecharStatement(cs);
        }

        return resultado;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.AgendamentoBoletoDao#isDataUtil(int, java.util.Date)
     */
    public boolean isDataUtil(int numCooperativa, Date data) throws ComumException, ComumNegocioException {
        debug("### Obtendo data útil...");
        debug("Parâmetro - data: " + data);

        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;

        boolean isDataUtil = false;

        try {
            debug("Estabelecendo conexão...");
            conn = estabelecerConexao(numCooperativa);
            debug("Conexão estabelecida.");

            comando = getComando("VERIFICAR_DIA_UTIL_DATA_ESPECIAL");
            comando.adicionarVariavel("data", data);
            comando.configurar();

            debug("Executando a validação de dia útil:\n" + comando.getSqlEmUso());

            rs = comando.executarConsulta(conn);

            if (rs.next()) {
                isDataUtil = rs.getBoolean(1);
                debug("Data útil: " + isDataUtil);
            }
        } catch (PersistenciaException e) {
            tratarPersistenciaException(numCooperativa, e);
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharConexao(conn);
            fecharResultSet(rs);
            fecharComando(comando);
        }

        return isDataUtil;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.AgendamentoBoletoDao#obterQtdDiasUteis(int, int, java.util.Date, java.util.Date)
     */
    public Integer obterQtdDiasUteis(int numCooperativa, int numPA, Date dataInicial, Date dataFinal) throws ComumException, ComumNegocioException {
        debug("### Obtendo quantidade de dias úteis...");
        debug("Parâmetro - numCooperativa: " + numCooperativa);
        debug("Parâmetro - numPA: " + numPA);
        debug("Parâmetro - dataInicial: " + dataInicial);
        debug("Parâmetro - dataFinal: " + dataFinal);

        Connection conn = null;
        CallableStatement cs = null;

        Integer resultado = null;

        try {
            debug("Estabelecendo conexão...");
            conn = estabelecerConexao(numCooperativa);
            debug("Conexão estabelecida.");

            debug("Executando SP_COB_QuantidadeDiasUteis...");
            cs = conn.prepareCall("{call dbo.SP_COB_QuantidadeDiasUteis(?, ?, ?, ?, ?, ?, ?)}");

            debug("Definindo parâmtros...");
            cs.registerOutParameter(1, java.sql.Types.INTEGER);
            cs.registerOutParameter(2, java.sql.Types.VARCHAR);
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.setInt(4, numCooperativa);
            cs.setInt(5, numPA);
            cs.setDate(6, new java.sql.Date(dataInicial.getTime()));
            cs.setDate(7, new java.sql.Date(dataFinal.getTime()));

            debug("Executando SP...");
            cs.execute();

            debug("Obtendo resultado...");
            resultado = cs.getInt(3);

            debug("Resultado: " + resultado);

            if (ObjectUtil.isNull(resultado)) {
                debug("Resultado nulo");

                String erro = cs.getString(2);
                debug("Erro: " + erro);

                // Verifica se existe algum retorno de erro.
                if (!ObjectUtil.isEmpty(erro)) {
                    throw new ComumNegocioException(erro);
                }
            }
        } catch (PersistenciaException e) {
            tratarPersistenciaException(numCooperativa, e);
        } catch (ComumNegocioException e) {
            throw e;
        } catch (SQLException | IllegalStateException e) {
            throw new ComumException(e);
        } finally {
            fecharConexao(conn);
            fecharStatement(cs);
        }

        return resultado;
    }

    /**
     * Método responsável por tratar as exceções de persistência
     * 
     * @param numCooperativa
     * @param e
     * @throws CooperativaNaoEncontradaNegocioException
     * @throws ComumException
     */
    private void tratarPersistenciaException(int numCooperativa, PersistenciaException e) throws CooperativaNaoEncontradaNegocioException, ComumException {
        // Se não encontrar a cooperativa que está tentando estabelecer conexão
        if (!ObjectUtil.isNull(e.getCause())
                && (e.getCause() instanceof NameNotFoundException || (!ObjectUtil.isNull(e.getCause().getCause()) && e.getCause().getCause() instanceof NameNotFoundException))) {
            throw new CooperativaNaoEncontradaNegocioException(numCooperativa, e);
        } else {
            throw new ComumException(e);
        }
    }

}
