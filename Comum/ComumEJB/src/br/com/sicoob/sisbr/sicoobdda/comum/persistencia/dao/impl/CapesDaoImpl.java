/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl
 * Arquivo:         InstituicaoDaoImpl.java
 * Data Criação:    30/09/2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.capes.PessoaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.CapesDao;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumCrudDaoDB2;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * {@link CapesDaoImpl} é responsável por incluir e recuperar o histórico de determinada entidade.
 * 
 * @author george.santos
 */
public class CapesDaoImpl extends ComumCrudDaoDB2<SicoobDDAEntidade> implements CapesDao {

    private static final String ARQUIVO_QUERIES = "sicoobdda_comum.capes.queries.xml";
    private static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-comum-capes";

    /**
     * Construtor.
     */
    public CapesDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.CapesDao#obterPessoa(java.lang.String, java.lang.Integer)
     */
    public PessoaDto obterPessoa(String numCpfCnpj, Integer idInstituicao) throws ComumException {
        return obterPessoa(null, numCpfCnpj, idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.CapesDao#obterPessoa(java.lang.Long, java.lang.Integer)
     */
    @Override
    public PessoaDto obterPessoa(Long idPessoa, Integer idInstituicao) throws ComumException {
        return obterPessoa(idPessoa, null, idInstituicao);
    }

    /**
     * Método responsável por
     * 
     * @param idPessoa
     * @param numCpfCnpj
     * @param idInstituicao
     * @return
     * @throws ComumException PessoaDto
     */
    private PessoaDto obterPessoa(Long idPessoa, String numCpfCnpj, Integer idInstituicao) throws ComumException {
        getLogger().debug("*******OBTER_PESSOA_VIW_CAPES*******");
        getLogger().debug("idPessoa = " + idPessoa);
        getLogger().debug("numCpfCnpj = " + numCpfCnpj);
        getLogger().debug("idInstituicao = " + idInstituicao);
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        PessoaDto pessoaDto = null;
        try {
            conn = estabelecerConexao();
            comando = getComando("OBTER_PESSOA_VIW_CAPES");
            comando.adicionarVariavel("idPessoa", idPessoa);
            comando.adicionarVariavel("numCpfCnpj", numCpfCnpj);
            comando.adicionarVariavel("idInstituicao", idInstituicao);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            rs = comando.executarConsulta(conn);
            if (rs.next()) {
                pessoaDto = setPessoaDto(rs);
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return pessoaDto;
    }

    /**
     * Método responsável por.
     *
     * @param rs the rs
     * @return the pessoa dto
     * @throws SQLException PessoaDto
     */
    private PessoaDto setPessoaDto(ResultSet rs) throws SQLException {
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setCodigoCompartilhamentoCadastro(rs.getShort("CODCOMPARTILHAMENTOCADASTRO"));
        pessoaDto.setCodTipoPessoa(rs.getShort("CODTIPOPESSOA"));
        pessoaDto.setIdPessoa(rs.getInt("IDPESSOA"));
        pessoaDto.setNomePessoa(rs.getString("NOMEPESSOA"));
        pessoaDto.setCpfCnpj(rs.getString("NUMCPFCNPJ"));
        pessoaDto.setNomeCompleto(rs.getString("NOMECOMPLETO"));
        pessoaDto.setNomeApelido(rs.getString("NOMEAPELIDO"));
        pessoaDto.setIdInstituicao(rs.getInt("IDINSTITUICAO"));
        pessoaDto.setIdUnidadeInst(rs.getInt("IDUNIDADEINST"));
        pessoaDto.setIdPessoaLegado(rs.getInt("IDPESSOALEGADO"));
        return pessoaDto;
    }

}
