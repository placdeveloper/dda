package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.PersistenceException;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.AgendamentoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.BoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.BoletoTerceiroAutorizadoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PesquisaBoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.LinhaDigitavelCodigoBarrasUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaEfet;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaOper;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDAGrupoCalculo;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDATerceiroAut;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDATextoInfo;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBoletoPagamento;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BoletoDDADao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDaoDB2;

/**
 * 
 * @author Daniel.Moraes
 * 
 */
public class BoletoDDADaoImpl extends OperacionalCrudDaoDB2<BoletoDDA> implements BoletoDDADao {

    private static final String LIQUIDADO = "Liquidado";
    private static final String AGENDADO = "Agendado";

    public static final String ARQUIVO_QUERIES = "sicoobdda_operacional.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional";

    /**
     * @param datasource
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     */
    public BoletoDDADaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, BoletoDDA.class, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BoletoDDADao#boletoEstaNaCIP(java.lang.String)
     */
    public Boolean boletoEstaNaCIP(String numCodigoBarra) throws ComumNegocioException, ComumException {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("numCodigoBarra", numCodigoBarra);

        Integer qtdRegistros = obterDados("BOLETO_ESTA_NA_CIP", parametros);

        return qtdRegistros > 0;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BoletoDDADao#obterBoletoDDA(java.lang.String, java.lang.Long, boolean)
     */
    public BoletoDDA obterBoletoDDA(String codigoBarras, Long numIdentificadorBoletoCIP, boolean somenteEmAberto) throws ComumException {
        Map<String, Object> parametros = new HashMap<>(3);
        parametros.put("numCodBarrasCampoLivre", LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(codigoBarras));
        parametros.put("numIdentificadorBoletoCIP", numIdentificadorBoletoCIP);

        // Se não informou o número da CIP e for pra pesquisar somente "em aberto"
        if (ObjectUtil.isEmpty(numIdentificadorBoletoCIP) && somenteEmAberto) {
            parametros.put("situacaoBoleto", SituacaoBoleto.ABERTO);
        }

        return complementarDadosBoletoDDA((BoletoDDA) obterDados("OBTER_BOLETO_DDA_CODIGO_BARRAS", parametros, 0, 1));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BoletoDDADao#obterBoletoDDA(java.lang.String, java.lang.Short)
     */
    public BoletoDDA obterBoletoDDA(String codigoBarrasLinhaDigitavel, Integer codSituacaoBoleto) throws ComumException {
        Map<String, Object> parametros = new HashMap<>(3);
        parametros.put("numCodigoBarra", codigoBarrasLinhaDigitavel);
        parametros.put("situacaoBoleto", codSituacaoBoleto != 0 ? codSituacaoBoleto : null);

        BoletoDDA boletoDDA = obterDados("OBTER_BOLETO_DDA_CODIGO_BARRAS", parametros, 0, 1);
        if (!ObjectUtil.isNull(boletoDDA) && !ObjectUtil.isEmpty(boletoDDA.getId())) {
            if (!ObjectUtil.isNull(boletoDDA.getNumRefAtualCadBoleto())) {
                boletoDDA.setNumRefAtualCadBoletoStr(boletoDDA.getNumRefAtualCadBoleto().toString());
            }
            boletoDDA.setListaBoletoDDATerceiroAutorizado(listarBoletoTerceiroAutorizadoDDA(boletoDDA.getId()));
            boletoDDA.setListaBoletoDDATextoInfo(listarBoletoDDATextoInfo(boletoDDA.getId()));
            boletoDDA.setListaBoletoDDABaixaEfet(listarBoletoDDABaixaEfet(boletoDDA.getId()));
            boletoDDA.setListaBoletoDDABaixaOper(listarBoletoDDABaixaOper(boletoDDA.getId()));
        }

        return boletoDDA;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BoletoDDADao#complementarDadosBoletoDDA(br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA)
     */
    public BoletoDDA complementarDadosBoletoDDA(BoletoDDA boletoDDA) throws ComumException {
        debug("### Complementando dados do boletoDDA: " + boletoDDA);

        if (!ObjectUtil.isNull(boletoDDA) && !ObjectUtil.isEmpty(boletoDDA.getId())) {
            // Cria uma cópia do objeto principal, para que ao alterar as informações do objeto que vieram do banco não faça update quando o serviço finalizar
            boletoDDA = copiarBoletoDDA(boletoDDA);

            preencherInformacoesAdicionais(boletoDDA);

            boletoDDA.setListaBoletoDDAGrupoCalculo(listarBoletoDDAGrupoCalculoValido(boletoDDA.getId()));
        }

        return boletoDDA;
    }

    /**
     * Método responsável por copiar o objeto principal, para que ao alterar as informações do objeto que vieram do banco não faça update quando o serviço
     * finalizar
     * 
     * @param boletoDDA
     * @return
     * @throws ComumException
     */
    private BoletoDDA copiarBoletoDDA(BoletoDDA boletoDDA) throws ComumException {
        debug("### Copiando BoletoDDA...");

        try {
            return ObjectUtil.copy(boletoDDA);
        } catch (IOException e) {
            throw new ComumException("integracaocip.erro.copiar.boleto", e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            throw new ComumException("integracaocip.erro.copiar.boleto", e.getMessage(), e);
        }
    }

    /**
     * Método responsável por preencher algumas informações adicionais vindas da ViewBoletoDDASituacao
     * 
     * @param boletoDDA
     * @throws ComumException
     */
    private void preencherInformacoesAdicionais(BoletoDDA boletoDDA) throws ComumException {
        debug("### Preenchendo informações adicionais...");

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idBoletoDDA", boletoDDA.getId());

        Object[] array = obterDados("OBTER_VIEW_BOLETO_DDA_SITUACAO_ID", parametros);

        boletoDDA.setQtdPagamentoRestante((Integer) array[0]);
        debug("Qtd. pagamento restante: " + boletoDDA.getQtdPagamentoRestante());

        boletoDDA.setValorPago((BigDecimal) array[1]);
        debug("Valor pago: " + boletoDDA.getValorPago());

        boletoDDA.setCodSituacaoBoletoPagamento((String) array[2]);
        debug("CodSituacaoBoletoPagamento: " + boletoDDA.getCodSituacaoBoletoPagamento());

        boletoDDA.setCodSituacaoBoleto((Integer) array[3]);
        debug("CodSituacaoBoleto: " + boletoDDA.getCodSituacaoBoleto());
    }

    /**
     * Método responsável por listar os cálculos vigentes.
     * 
     * @param idBoletoDDA
     * @return
     * @throws ComumException
     */
    private List<BoletoDDAGrupoCalculo> listarBoletoDDAGrupoCalculoValido(Long idBoletoDDA) throws ComumException {
        debug("### Listando os grupos de cálculo do boletoDDA: " + idBoletoDDA);

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("idBoletoDDA", idBoletoDDA);

        return listar("LISTAR_BOLETO_DDA_GRUPO_CALCULO_VALIDO", parametros);
    }

    /**
     * Método responsável por listar os descontos.
     * 
     * @param idBoletoDDA
     * @return
     * @throws ComumException
     */
    // private List<BoletoDDADesconto> listarBoletoDDADesconto(Long idBoletoDDA) throws ComumException {
    // debug("### Listando os descontos do boletoDDA: " + idBoletoDDA);
    //
    // Map<String, Object> parametros = new HashMap<String, Object>();
    // parametros.put("idBoletoDDA", idBoletoDDA);
    //
    // return listar("LISTAR_BOLETO_DDA_DESCONTO", parametros);
    // }

    /**
     * Método responsável por listar os boletos Terceiro Autorizado
     * 
     * @param idBoletoDDA
     * @return
     * @throws ComumException List<BoletoDDATerceiroAut>
     * 
     */
    private List<BoletoDDATerceiroAut> listarBoletoTerceiroAutorizadoDDA(Long idBoletoDDA) throws ComumException {
        debug("### Listando os descontos do boletoDDA: " + idBoletoDDA);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idBoletoDDA", idBoletoDDA);

        return listar("LISTAR_BOLETO_DDA_TERCEIROAUT", parametros);
    }

    /**
     * Método responsável por os listar Texto Info
     * 
     * @param idBoletoDDA
     * @return
     * @throws ComumException List<BoletoDDATextoInfo>
     * 
     */
    private List<BoletoDDATextoInfo> listarBoletoDDATextoInfo(Long idBoletoDDA) throws ComumException {
        debug("### Listando os descontos do boletoDDA: " + idBoletoDDA);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idBoletoDDA", idBoletoDDA);

        return listar("LISTAR_BOLETO_DDA_TEXTOINFO", parametros);
    }

    /**
     * Método responsável por listar as Baixas Oper
     * 
     * @param idBoletoDDA
     * @return
     * @throws ComumException List<BoletoDDABaixaOper>
     * 
     */
    private List<BoletoDDABaixaOper> listarBoletoDDABaixaOper(Long idBoletoDDA) throws ComumException {
        debug("### Listando os descontos do boletoDDA: " + idBoletoDDA);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idBoletoDDA", idBoletoDDA);

        return listar("LISTAR_BOLETO_DDA_BAIXAOPER", parametros);
    }

    /**
     * Método responsável por listar as Baixas Efet
     * 
     * @param idBoletoDDA
     * @return
     * @throws ComumException List<BoletoDDABaixaEfet>
     * 
     */
    private List<BoletoDDABaixaEfet> listarBoletoDDABaixaEfet(Long idBoletoDDA) throws ComumException {
        debug("### Listando os descontos do boletoDDA: " + idBoletoDDA);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idBoletoDDA", idBoletoDDA);

        return listar("LISTAR_BOLETO_DDA_BAIXAEFET", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BoletoDDADao#obterSituacaoBoleto(java.lang.String)
     */
    public SituacaoBoletoPagamento obterSituacaoBoleto(String codSituacaoBoletoPagamento) throws ComumException {
        debug("### Obtendo a situação do boleto pagamento: " + codSituacaoBoletoPagamento);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("codSituacaoBoletoPagamento", codSituacaoBoletoPagamento);

        return obterDados("OBTER_SITUACAO_BOLETO_PAGAMENTO", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BoletoDDADao#permiteAlterarValor(java.lang.String)
     */
    public Boolean permiteAlterarValor(String codAutorizacaoValorDivergente) throws ComumException {
        debug("### Permite alterar valor (codAutorizacaoValorDivergente): " + codAutorizacaoValorDivergente);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("codAutorizacaoValorDivergente", codAutorizacaoValorDivergente);

        Boolean permiteAlterarValor = obterDados("VERIFICAR_PERMITE_ALTERAR_VALOR", parametros);
        debug("Permite alterar valor (codAutorizacaoValorDivergente): " + permiteAlterarValor);

        return permiteAlterarValor;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BoletoDDADao#listarBoletoDDATransacaoCanais(java.util.Set,
     *      br.com.bancoob.persistencia.types.DateTimeDB, br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Integer, java.lang.Integer,
     *      java.math.BigDecimal)
     */
    public List<BoletoDDADto> listarBoletoDDATransacaoCanais(Set<String> listaCpfCnpj, DateTimeDB dataInicial, DateTimeDB dataFinal, Integer codSituacao, Integer numCooperativa,
            BigDecimal numContaCorrente) throws ComumException {
        debug("### Listando boletos DDA...");
        debug("Parâmetro - listaCpfCnpj: " + listaCpfCnpj);
        debug("Parâmetro - dataInicial: " + dataInicial);
        debug("Parâmetro - dataFinal: " + dataFinal);
        debug("Parâmetro - codSituacao: " + codSituacao);
        debug("Parâmetro - numCooperativa: " + numCooperativa);
        debug("Parâmetro - numContaCorrente: " + numContaCorrente);

        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        List<BoletoDDADto> listaBoletoDDADto = new ArrayList<>();

        try {
            conn = estabelecerConexao();

            final int size = listaCpfCnpj.size();

            String sql = criarSQLListarBoletoDDA(codSituacao, size);
            debug(sql);

            ps = conn.prepareStatement(sql);

            definirParametrosListarBoletoDDA(listaCpfCnpj, dataInicial, dataFinal, ps, size);

            debug("Executando a query...");
            rs = ps.executeQuery();

            processarListaBoletoDDA(codSituacao, numCooperativa, numContaCorrente, rs, listaBoletoDDADto);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharStatement(ps);
            fecharConexao(conn);
        }

        return listaBoletoDDADto;
    }

    /**
     * Método responsável por processar os boletos DDA
     * 
     * @param codSituacao
     * @param numCooperativa
     * @param numContaCorrente
     * @param rs
     * @param listaBoletoDDADto
     * @throws SQLException
     */
    private void processarListaBoletoDDA(Integer codSituacao, Integer numCooperativa, BigDecimal numContaCorrente, ResultSet rs, List<BoletoDDADto> listaBoletoDDADto)
            throws SQLException {
        // Só deve mostrar os dados de pagamento se a situação do boleto for 'agendado' ou 'liquidado'
        boolean mostrarDataValorPagamento = codSituacao == Constantes.SITUACAO_BOLETO_AGENDADO || codSituacao == Constantes.SITUACAO_BOLETO_LIQUIDADO;

        if (rs.next()) {
            List<AgendamentoDto> listaAgendamento = null;

            final boolean isBaixado = codSituacao == Constantes.SITUACAO_BOLETO_BAIXADO;

            // Se não estiver baixado faz a consulta dos agendamentos/efetivações
            if (!isBaixado) {
                listaAgendamento = listarAgendamentos(numCooperativa, numContaCorrente, codSituacao, rs.getLong("MIN_NUMIDENTIFICADORBOLETOCIP"),
                        rs.getLong("MAX_NUMIDENTIFICADORBOLETOCIP"));
                debug("Lista: " + listaAgendamento.size());
            }

            boolean continuar;

            do {
                final Long numIdentificadorBoletoCip = rs.getLong("NUMIDENTIFICADORBOLETOCIP");
                final int situacaoBoleto = rs.getInt("IDTIPOSITUACAOBOLETO");
                final boolean permitePgmtoParcial = rs.getBoolean("BOLPAGAMENTOPARCIAL");

                do {
                    continuar = false;
                    AgendamentoDto agendamentoDtoTemp = null;

                    // Se não estiver baixado
                    if (!isBaixado) {
                        // Se o boleto estiver "em aberto" e houver agendamento
                        if (situacaoBoleto == SituacaoBoleto.ABERTO && !ObjectUtil.isEmpty(listaAgendamento)) {
                            for (int i = 0; i < listaAgendamento.size(); i++) {
                                AgendamentoDto dto = listaAgendamento.get(i);

                                if (dto.getNumIdentificadorBoletoCip().equals(numIdentificadorBoletoCip)) {
                                    agendamentoDtoTemp = dto;
                                    listaAgendamento.remove(i);

                                    /*
                                     * Se a consulta não for para boletos em aberto, verifica se há outro agendamento para o mesmo identificador CIP OBS: não
                                     * precisa incrementar o 'i', pois foi removido o registro da lista anteriormente
                                     */
                                    if (codSituacao != Constantes.SITUACAO_BOLETO_EM_ABERTO && i < listaAgendamento.size()) {
                                        /*
                                         * Verifica se o próximo agendamento é do mesmo identificador para que crie um novo objeto com outras informações de
                                         * valor e data do agendamento
                                         */
                                        if (listaAgendamento.get(i).getNumIdentificadorBoletoCip().equals(numIdentificadorBoletoCip)) {
                                            continuar = true;
                                        }
                                    }

                                    break;
                                }
                            }
                        }

                        if (ObjectUtil.isNull(agendamentoDtoTemp)) {
                            // Quando pesquisado por "agendado" a SP só retorna os agendados, neste caso, se não encontrou é porque não está agendado
                            if (codSituacao == Constantes.SITUACAO_BOLETO_AGENDADO) {
                                break;
                            }
                            // Quando pesquisado por "liquidado" a SP só retorna os efetivados, neste caso, se não encontrou e o boleto está "em aberto" é
                            // porque não há efetivação
                            else if (codSituacao == Constantes.SITUACAO_BOLETO_LIQUIDADO && situacaoBoleto == SituacaoBoleto.ABERTO) {
                                break;
                            }
                        }
                        /*
                         * Quando pesquisado por "em aberto" a SP só retorna os que possuem agendamento e efetivação, neste caso, se encontrou é porque está
                         * agendado ou efetivado, se o boleto não aceitar pagamento parcial não mostrará
                         */
                        else if (codSituacao == Constantes.SITUACAO_BOLETO_EM_ABERTO && !permitePgmtoParcial) {
                            break;
                        }
                    }

                    BoletoDDADto boletoDDADto = criarBoletoDDADto(codSituacao, rs, mostrarDataValorPagamento, numIdentificadorBoletoCip, situacaoBoleto, agendamentoDtoTemp);

                    listaBoletoDDADto.add(boletoDDADto);
                } while (continuar);
            } while (rs.next());
        }
    }

    /**
     * Método responsável por criar o dto
     * 
     * @param codSituacao
     * @param rs
     * @param mostrarDataValorPagamento
     * @param numIdentificadorBoletoCip
     * @param situacaoBoleto
     * @param dtoTemp
     * @return
     * @throws SQLException
     */
    private BoletoDDADto criarBoletoDDADto(Integer codSituacao, ResultSet rs, boolean mostrarDataValorPagamento, Long numIdentificadorBoletoCip, int situacaoBoleto,
            AgendamentoDto dtoTemp) throws SQLException {
        debug("### Criando BoletoDDADto...");

        BoletoDDADto boletoDDADto = new BoletoDDADto();
        boletoDDADto.setIdBoletoDDA(rs.getLong("IDBOLETODDA"));
        boletoDDADto.setDescTipoPagador(rs.getString("DESCTIPOPAGADOR"));

        boletoDDADto.setTipoPessoaBeneficiario(rs.getString("CODTIPOPESSOABENEFICIARIO").charAt(0));
        boletoDDADto.setNumCpfCnpjBeneficiario(rs.getString("NUMCPFCNPJBENEFICIARIO"));
        boletoDDADto.setNomeRazaoSocialBeneficiario(rs.getString("NOMEBENEFICIARIO"));

        boletoDDADto.setTipoPessoaPagador(rs.getString("CODTIPOPESSOAPAGADOR").charAt(0));
        boletoDDADto.setNumCpfCnpjPagador(rs.getString("NUMCPFCNPJPAGADOR"));
        boletoDDADto.setNomeRazaoSocialPagador(rs.getString("NOMEPAGADOR"));

        boletoDDADto.setValorBoleto(rs.getDouble("VALORBOLETO"));
        boletoDDADto.setDataVencimentoBoleto(new DateTimeDB(rs.getDate("DATAVENCIMENTO").getTime()));

        boletoDDADto.setCodTipoSituacaoBoleto(situacaoBoleto);
        boletoDDADto.setNumIdentificadorBoletoCip(numIdentificadorBoletoCip);

        boletoDDADto.setNumCodigoBarras(rs.getString("NUMCODIGOBARRA"));
        boletoDDADto.setNumCpfCnpjPagadorEletronico(rs.getString("NUMCPFCNPJPAGADORELETRONICO"));

        boletoDDADto.setBolAceite(rs.getBoolean("BOLACEITE"));

        // Se a consulta for pela situação "agendado", define a situação "agendado" pois o boleto estará "em aberto"
        if (codSituacao == Constantes.SITUACAO_BOLETO_AGENDADO) {
            boletoDDADto.setDescSituacaoBoleto(AGENDADO);
        }
        // Se a consulta for pela situação "liquidado" e o boleto estiver "em aberto", define a situação "liquidado"
        else if (codSituacao == Constantes.SITUACAO_BOLETO_LIQUIDADO && situacaoBoleto == SituacaoBoleto.ABERTO) {
            boletoDDADto.setDescSituacaoBoleto(LIQUIDADO);
        } else {
            boletoDDADto.setDescSituacaoBoleto(rs.getString("DESCTIPOSITUACAOBOLETO"));
        }

        boletoDDADto.setNumNossoNumero(rs.getString("NUMNOSSONUMERO"));
        boletoDDADto.setNumDocumento(rs.getString("NUMDOCUMENTO"));

        boletoDDADto.setBolMostrarDataValorPagamento(mostrarDataValorPagamento);

        // Se for pra mostrar os dados do agendamento e possuir um agendamento
        if (mostrarDataValorPagamento && !ObjectUtil.isNull(dtoTemp)) {
            boletoDDADto.setValorPagamento(dtoTemp.getValorPagamento());
            boletoDDADto.setDataPagamento(dtoTemp.getDataPagamento());
        }
        // Se for uma liquidação mostrará os valores de pagamento da BaixaEfetiva
        else if (codSituacao == Constantes.SITUACAO_BOLETO_LIQUIDADO) {
            boletoDDADto.setValorPagamento(getNullableDouble("VALORBAIXAEFET", rs));

            Date dataProcessamento = rs.getDate("DATAPROCESSAMENTOBAIXAEFET");

            if (!ObjectUtil.isNull(dataProcessamento)) {
                boletoDDADto.setDataPagamento(new DateTimeDB(dataProcessamento.getTime()));
            }
        }

        return boletoDDADto;
    }

    /**
     * Método responsável por listar os agendamentos/efetivações do legado de acordo com a situação informada e o identificador inicial e final.
     * 
     * Se a situação for:
     * <ul>
     * <li>"em aberto" trará todos os agendamentos com situação "agendado" ou "efetivado";</li>
     * <li>"agendado" trará todos os agendamentos com situação "agendado";</li>
     * <li>"liquidado" trará todos os agendamentos com situação "efetivado".</li>
     * </ul>
     * 
     * @param numCooperativa
     * @param numContaCorrente
     * @param codSituacao
     * @param numIdentificadorCIPInicial
     * @param numIdentificadorCIPFinal
     * @return
     */
    private List<AgendamentoDto> listarAgendamentos(Integer numCooperativa, BigDecimal numContaCorrente, Integer codSituacao, Long numIdentificadorCIPInicial,
            Long numIdentificadorCIPFinal) {
        debug("### Listando situação dos agendamentos...");
        debug("Parâmetro - numIdentificadorCIPInicial: " + numIdentificadorCIPInicial);
        debug("Parâmetro - numIdentificadorCIPFinal: " + numIdentificadorCIPFinal);

        Connection conn = null;
        ResultSet rs = null;
        CallableStatement cs = null;

        List<AgendamentoDto> lista = new ArrayList<>();
        final String sql = "{ call SPU_COB_ConsultarAgendamentoTituloDDA (?, ?, ?, ?, ?, ?, ?) }";
        debug("SQL: " + sql);

        try {
            conn = estabelecerConexao(numCooperativa);

            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, java.sql.Types.INTEGER);
            cs.registerOutParameter(2, java.sql.Types.VARCHAR);
            cs.setNull(3, java.sql.Types.NULL);
            cs.setBigDecimal(4, numContaCorrente);
            cs.setInt(5, codSituacao);
            cs.setLong(6, numIdentificadorCIPInicial);
            cs.setLong(7, numIdentificadorCIPFinal);

            debug("Executando a query...");
            rs = cs.executeQuery();

            while (rs.next()) {
                Long numIdentificadorBoletoCip = getNullableLong("NumIdentificadorBoletoCIP", rs);
                Double valorPagamento = getNullableDouble("ValorLancamento", rs);
                Date dataPagamento = rs.getDate("DataLancamento");

                AgendamentoDto dto = new AgendamentoDto();
                dto.setNumIdentificadorBoletoCip(numIdentificadorBoletoCip);
                dto.setValorPagamento(valorPagamento);

                if (!ObjectUtil.isNull(dataPagamento)) {
                    dto.setDataPagamento(new DateTimeDB(dataPagamento.getTime()));
                }

                lista.add(dto);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharStatement(cs);
            fecharConexao(conn);
        }

        return lista;
    }

    /**
     * Método responsável por definir os parâmetros da consulta
     * 
     * @param listaCpfCnpj
     * @param dataInicial
     * @param dataFinal
     * @param ps
     * @param size
     * @throws SQLException
     */
    private void definirParametrosListarBoletoDDA(Set<String> listaCpfCnpj, DateTimeDB dataInicial, DateTimeDB dataFinal, PreparedStatement ps, final int size)
            throws SQLException {
        debug("### Definindo os parâmetros da consulta...");

        Iterator<String> iterator = listaCpfCnpj.iterator();
        int index = 1;

        for (int i = 0; i < size; i++) {
            ps.setString(index++, iterator.next());
        }

        ps.setDate(index++, new Date(dataInicial.getTime()));
        ps.setDate(index++, new Date(dataFinal.getTime()));
    }

    /**
     * Método responsável por criar o comando SQL para listar os boletos DDA.
     * 
     * @param codSituacao
     * @param size
     * @return
     */
    private String criarSQLListarBoletoDDA(Integer codSituacao, final int size) {
        debug("### Criando o SQL da consulta...");

        StringBuffer sql = new StringBuffer();
        sql.append("SELECT VIW.IDBOLETODDA, ");
        sql.append("       VIW.DESCTIPOPAGADOR, ");
        sql.append("       VIW.CODTIPOPESSOABENEFICIARIO, ");
        sql.append("       VIW.NUMCPFCNPJBENEFICIARIO, ");
        sql.append("       VIW.NOMEBENEFICIARIO, ");
        sql.append("       VIW.CODTIPOPESSOAPAGADOR, ");
        sql.append("       VIW.NUMCPFCNPJPAGADOR, ");
        sql.append("       VIW.NOMEPAGADOR, ");
        sql.append("       VIW.NUMNOSSONUMERO, ");
        sql.append("       VIW.NUMDOCUMENTO, ");
        sql.append("       VIW.VALORBOLETO, ");
        sql.append("       VIW.DATAVENCIMENTO, ");
        sql.append("       VIW.BOLACEITE, ");
        sql.append("       VIW.IDTIPOSITUACAOBOLETO, ");
        sql.append("       VIW.DESCTIPOSITUACAOBOLETO, ");
        sql.append("       VIW.NUMIDENTIFICADORBOLETOCIP, ");
        sql.append("       VIW.NUMCODIGOBARRA, ");
        sql.append("       VIW.NUMCPFCNPJPAGADORELETRONICO, ");
        sql.append("       VIW.BOLPAGAMENTOPARCIAL, ");

        if (!ObjectUtil.isNull(codSituacao) && codSituacao.equals(Constantes.SITUACAO_BOLETO_LIQUIDADO)) {
            sql.append("       BAIXAEFETIVA.VALORBAIXAEFET, ");
            sql.append("       BAIXAEFETIVA.DATAPROCESSAMENTOBAIXAEFET, ");
        }

        sql.append(
                "       MIN(CASE WHEN VIW.IDTIPOSITUACAOBOLETO = 1 THEN VIW.NUMIDENTIFICADORBOLETOCIP ELSE 999999999999999999999999999999 END) OVER () AS MIN_NUMIDENTIFICADORBOLETOCIP, ");
        sql.append("       MAX(CASE WHEN VIW.IDTIPOSITUACAOBOLETO = 1 THEN VIW.NUMIDENTIFICADORBOLETOCIP ELSE 0 END) OVER () AS MAX_NUMIDENTIFICADORBOLETOCIP ");
        sql.append("FROM DDA.VIW_BOLETOPAGELETRONICO VIW ");

        if (!ObjectUtil.isNull(codSituacao) && codSituacao.equals(Constantes.SITUACAO_BOLETO_LIQUIDADO)) {
            sql.append("LEFT JOIN DDA.BOLETODDABAIXAEFET BAIXAEFETIVA ON BAIXAEFETIVA.IDBOLETODDA = VIW.IDBOLETODDA ");
        }

        sql.append("WHERE viw.NUMCPFCNPJPAGADORELETRONICO in (");

        for (int i = 0; i < size; i++) {
            sql.append("?");

            if ((i + 1) < size) {
                sql.append(",");
            }
        }

        sql.append(") AND viw.DATAVENCIMENTO BETWEEN ? AND ? ");

        if (!ObjectUtil.isNull(codSituacao)) {
            switch (codSituacao) {
            case Constantes.SITUACAO_BOLETO_EM_ABERTO:
            case Constantes.SITUACAO_BOLETO_AGENDADO:
                sql.append("AND VIW.IDTIPOSITUACAOBOLETO = ");
                sql.append(Constantes.SITUACAO_BOLETO_EM_ABERTO);
                break;
            case Constantes.SITUACAO_BOLETO_BAIXADO:
                sql.append("AND VIW.IDTIPOSITUACAOBOLETO = ");
                sql.append(Constantes.SITUACAO_BOLETO_BAIXADO);
                break;
            case Constantes.SITUACAO_BOLETO_LIQUIDADO:
                sql.append("AND (VIW.IDTIPOSITUACAOBOLETO = ");
                sql.append(Constantes.SITUACAO_BOLETO_LIQUIDADO);
                sql.append(" OR VIW.IDTIPOSITUACAOBOLETO = ");
                sql.append(Constantes.SITUACAO_BOLETO_EM_ABERTO);
                sql.append(")");
                break;
            }
        }

        return sql.toString();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BoletoDDADao#listarSituacaoBoleto()
     */
    public List<SituacaoBoleto> listarSituacaoBoleto() {
        debug("*******INICIO listarSituacaoBoleto*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<SituacaoBoleto> listaSituacaoBoleto = new ArrayList<SituacaoBoleto>();
        try {
            comando = getComando("LISTAR_SITUACAO_BOLETO_CANAIS");
            comando.configurar();
            debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                SituacaoBoleto situacaoBoleto = new SituacaoBoleto();
                situacaoBoleto.setCodSituacaoBoleto(rs.getString("IDTIPOSITUACAOBOLETO"));
                situacaoBoleto.setDescSituacaoBoleto(rs.getString("DESCTIPOSITUACAOBOLETO"));
                listaSituacaoBoleto.add(situacaoBoleto);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaSituacaoBoleto;

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BoletoDDADao#listarBoletoTerceiroAutorizadoDDA(java.lang.String)
     */
    public List<BoletoTerceiroAutorizadoDDADto> listarBoletoTerceiroAutorizadoDDA(String numIdentificadorBoletoCIP) {
        debug("*******INICIO listarBoletoTerceiroAutorizadoDDA*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<BoletoTerceiroAutorizadoDDADto> listaBoletoTerceiroAutorizadoDDADto = new ArrayList<BoletoTerceiroAutorizadoDDADto>();
        try {
            comando = getComando("LISTAR_BOLETO_TERCEIRO_AUTORIZADO_DDA_TRANSACAO_CANAIS");
            comando.adicionarVariavel("numIdentificadorBoletoCIP", numIdentificadorBoletoCIP);
            comando.configurar();
            debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                BoletoTerceiroAutorizadoDDADto boletoTerceiroAutorizadoDDADto = new BoletoTerceiroAutorizadoDDADto();
                boletoTerceiroAutorizadoDDADto.setBolComCheck(rs.getInt("BOLCOMCHECK"));
                boletoTerceiroAutorizadoDDADto.setNumIdentificadorBoletoCip(rs.getString("NUMIDENTIFICADORBOLETOCIP"));

                boletoTerceiroAutorizadoDDADto.setCodTipoPessoaPagador(rs.getString("CODTIPOPESSOAPAGADOR").charAt(0));
                boletoTerceiroAutorizadoDDADto.setNumCpfCnpjPagador(rs.getString("NUMCPFCNPJPAGADOR"));

                boletoTerceiroAutorizadoDDADto.setCodTipoPessoaTerceiro(rs.getString("CODTIPOPESSOATERCEIRO").charAt(0));
                boletoTerceiroAutorizadoDDADto.setNumCpfCnpjTerceiro(rs.getString("NUMCPFCNPJTERCEIRO"));

                boletoTerceiroAutorizadoDDADto.setOperacao(rs.getString("OPERACAO"));
                boletoTerceiroAutorizadoDDADto.setSituacao(rs.getString("SITUACAO"));

                listaBoletoTerceiroAutorizadoDDADto.add(boletoTerceiroAutorizadoDDADto);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }

        debug("*******FIM listarBoletoDDATransacaoCanais*******");
        return listaBoletoTerceiroAutorizadoDDADto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BoletoDDADao#possuiBoletoDDA(java.lang.String)
     */
    public boolean possuiBoletoDDA(String codigoBarras) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("numCodBarrasCampoLivre", LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(codigoBarras));

        long total = obterDados("POSSUI_BOLETO_DDA", parametros);

        return total > 0;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BoletoDDADao#listarMensagemDDABaixaOperacional(java.lang.String,
     *      br.com.bancoob.persistencia.types.DateTimeDB)
     */
    public List<MensagemDDABaixaOperacional> listarMensagemDDABaixaOperacional(String codigoBarras, DateTimeDB dataMovimento) throws ComumException {
        debug("### Listando mensagens de baixa operacional...");
        debug("Parâmetro - codigoBarras: " + codigoBarras);
        debug("Parâmetro - dataMovimento: " + dataMovimento);

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<MensagemDDABaixaOperacional> lista = null;

        // ****************************************************************
        // A CONSULTA FOI CRIADA DIRETAMENTE AQUI PARA MELHORAR PERFORMANCE
        // ****************************************************************
        final String SQL = " SELECT MENSAGEMBAIXA.VALORBAIXA, "//
                + "        MENSAGEMBAIXA.CODTIPOBAIXAOPERACIONAL, "//
                + "        MENSAGEMBAIXA.NUMCODIGOBARRA, "//
                + "        MENSAGEMBAIXA.CODMEIOPAGAMENTO, "//
                + "        MENSAGEMBAIXA.CODCANALPAGAMENTO "//
                + " FROM DDA.MENSAGEMDDABAIXAOPERACIONAL AS MENSAGEMBAIXA "//
                + " JOIN DDA.MENSAGEMDDA AS MENSAGEM ON MENSAGEM.IDMENSAGEMDDA = MENSAGEMBAIXA.IDMENSAGEMDDA "//
                + " WHERE MENSAGEMBAIXA.NUMCODIGOBARRA = ? "//
                + " AND MENSAGEM.CODTIPOMENSAGEMDDA IN (?, ?) "//
                + " AND MENSAGEM.DATAMOVIMENTO = ? "//
                + " AND ( MENSAGEMBAIXA.NUMREFATUALBAIXAOPER IS NULL OR "//
                + "       EXISTS ( SELECT 1 "//
                + "                FROM DDA.BOLETODDABAIXAOPER BOLETOBAIXA "//
                + "                WHERE BOLETOBAIXA.NUMREFATUALBAIXAOPER = MENSAGEMBAIXA.NUMREFATUALBAIXAOPER "//
                + "                AND BOLETOBAIXA.NUMCODBARRASBAIXAOPER = MENSAGEMBAIXA.NUMCODIGOBARRA "//
                + "                AND BOLETOBAIXA.CODSITUACAOBAIXAOPERACIONAL = ? ) ) ";

        debug(SQL);

        try {
            conn = estabelecerConexao();

            ps = conn.prepareStatement(SQL);

            ps.setString(1, codigoBarras);
            // Pesquisa pela mensagem DDA0108 e ADDA108
            ps.setString(2, TipoMensagemDDA.DDA0108);
            ps.setString(3, TipoMensagemDDA.ADDA108);

            ps.setDate(4, new Date(dataMovimento.getTime()));

            ps.setString(5, SituacaoBaixaOperacional.ATIVA);

            debug("Executando a query...");
            rs = ps.executeQuery();

            if (rs.next()) {
                debug("Preenchendo a lista...");
                lista = new ArrayList<MensagemDDABaixaOperacional>();

                do {
                    MensagemDDABaixaOperacional mensagemBaixa = new MensagemDDABaixaOperacional();

                    mensagemBaixa.setCodTipoBaixaOperacional(rs.getShort("codTipoBaixaOperacional"));
                    mensagemBaixa.setNumCodigoBarra(rs.getString("numCodigoBarra"));
                    mensagemBaixa.setNumCodBarrasCampoLivre(
                            mensagemBaixa.getNumCodigoBarra() != null ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(mensagemBaixa.getNumCodigoBarra()) : null);
                    mensagemBaixa.setCodMeioPagamento(rs.getShort("codMeioPagamento"));
                    mensagemBaixa.setCodCanalPagamento(rs.getShort("codCanalPagamento"));
                    mensagemBaixa.setValorBaixa(rs.getBigDecimal("valorBaixa"));

                    lista.add(mensagemBaixa);
                } while (rs.next());
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharStatement(ps);
            fecharConexao(conn);
        }

        return lista;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BoletoDDADao#listarSituacaoBoletoDDA()
     */
    public List<SituacaoBoleto> listarSituacaoBoletoDDA() throws ComumException {
        return listar("LISTAR_SITUACAO_BOLETO");
    }

    public Boolean boletoEncaminhadoCIP(String numCodigoBarra, Short codSituacaoBoleto) throws ComumException {
        debug("*******INICIO boletoEncaminhadoCIP*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        boolean boletoEncaminhadoCIP = false;
        try {
            comando = getComando("OBTER_106_ENCAMINHADA_CIP");
            comando.adicionarVariavel("numCodigoBarraCampoLivre", LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(numCodigoBarra));
            comando.adicionarVariavel("listaSituacaoBoleto", listaSituacaoBoleto(codSituacaoBoleto));
            comando.configurar();
            debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                boletoEncaminhadoCIP = true;
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return boletoEncaminhadoCIP;
    }

    /**
     * Método responsável por
     * 
     * @param codSituacaoBoleto
     * @return String
     * 
     */
    private List<Integer> listaSituacaoBoleto(Short codSituacaoBoleto) {
        List<Integer> listaSituacaoBoleto = null;
        if (!ObjectUtil.isNull(codSituacaoBoleto)) {
            listaSituacaoBoleto = new ArrayList<Integer>();
            if (codSituacaoBoleto.intValue() == Constantes.SITUACAO_BOLETO_EM_ABERTO) {
                listaSituacaoBoleto.add(SituacaoBoleto.ABERTO);
            } else if (codSituacaoBoleto.intValue() == Constantes.SITUACAO_BOLETO_BAIXADO) {
                listaSituacaoBoleto.add(SituacaoBoleto.BAIXA_EFETIVA_POR_DECURSO_DE_PRAZO);
                listaSituacaoBoleto.add(SituacaoBoleto.BAIXA_EFETIVA_POR_ENVIO_PARA_PROTESTO);
                listaSituacaoBoleto.add(SituacaoBoleto.BAIXA_EFETIVA_POR_SOLICITACAO_DO_CEDENTE);
            } else if (codSituacaoBoleto.intValue() == Constantes.SITUACAO_BOLETO_LIQUIDADO) {
                listaSituacaoBoleto.add(SituacaoBoleto.BAIXA_EFETIVA_LIQUIDACAO_INTRABANCARIA);
                listaSituacaoBoleto.add(SituacaoBoleto.BAIXA_EFETIVA_POR_LIQUIDACAO_INTERBANCARIA);
                listaSituacaoBoleto.add(SituacaoBoleto.BAIXA_EFETIVA_POR_SOLICITACAO_DA_INSTITUICAO_DESTINATARIA);
            }
        }
        return listaSituacaoBoleto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BoletoDDADao#obterDataHoraEncaminhadoParaCIP(java.lang.String, java.lang.Long)
     */
    public DateTimeDB obterDataHoraEncaminhadoParaCIP(String numCodigoBarra, Short codSituacaoBoleto) throws ComumException {
        debug("*******INICIO boletoEncaminhadoCIP*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        DateTimeDB dataHoraCadastro = null;
        try {
            comando = getComando("OBTER_106_ENCAMINHADA_CIP_APOS_MINUTOS_PARAMETRIZADO");
            comando.adicionarVariavel("numCodigoBarra", numCodigoBarra);
            comando.adicionarVariavel("listaSituacaoBoleto", listaSituacaoBoleto(codSituacaoBoleto));
            comando.adicionarVariavel("idParametro", ParametroDDA.TEMPO_MINUTOS_CONSULTA_BOLETO106);
            comando.configurar();
            debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                dataHoraCadastro = new DateTimeDB(rs.getDate("1").getTime());
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return dataHoraCadastro;
    }

    public List<PesquisaBoletoDDADto> listarBoletoDDA(PesquisaBoletoDDADto dto) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("criterios.filtro.numCodigoBarraCampoLivre", dto.getNumCodigoBarraCampoLivre());
        parametros.put("criterios.filtro.numCpfCnpjBeneficiario", dto.getNumCpfCnpjBeneficiario());
        parametros.put("criterios.filtro.numCpfCnpjPagador", dto.getNumCpfCnpjPagador());
        parametros.put("criterios.filtro.numBanco", dto.getNumBanco());
        parametros.put("criterios.filtro.numSeuNumero", dto.getNumSeuNumero());
        parametros.put("criterios.filtro.codSituacaoBoleto", dto.getCodSituacaoBoleto());
        parametros.put("criterios.filtro.dataVencimentoInicial", dto.getDataVencimentoInicial());
        parametros.put("criterios.filtro.dataVencimentoFinal", dto.getDataVencimentoFinal());

        parametros.put("criterios.filtro.dataRegistroDDAInicial", dto.getDataRegistroDDAInicial());
        parametros.put("criterios.filtro.dataRegistroDDAFinal", dto.getDataRegistroDDAFinal());

        return listar("PESQUISAR_BOLETO_PAGINADO", parametros);
    }

}