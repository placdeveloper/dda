package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * ConsultaParametroDto é responsável por
 * 
 * @author daniel.moraes
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ConsultaParametroDTO")
public class ConsultaParametroDto extends BancoobDto {

    private static final long serialVersionUID = 6490757499076617058L;

    private Long idParametro;
    private String nomeParametro;
    private String descricaoParametro;
    private Long idInstituicao;
    private TipoParametroDDA tipoParametro;
    private Boolean bolPermiteAlteracaoPeloUsuario;
    private Boolean bolVisivelFuncionalidadeAlteracao;
    private Boolean bolAtivo;
    private Boolean bolParametroGlobal;
    private String valorBaseParametro;
    private String valorBaseParametroTexto;
    private DateTimeDB dataCriacao;
    private DateTimeDB dataUltimaAlteracao;
    private String numeroCooperativa;
    private String descricaoSiglaCooperativa;
    private Boolean somenteVisiveis;
    private Integer idParametroLegado;

    public ConsultaParametroDto() {
    }

    /**
     * @param idParametro
     * @param nomeParametro
     * @param descricaoParametro
     * @param idInstituicao
     * @param tipoParametro
     * @param bolPermiteAlteracaoPeloUsuario
     * @param bolAtivo
     * @param bolParametroGlobal
     * @param valorBaseParametro
     * @param valorBaseParametroTexto
     * @param dataCriacao
     * @param dataUltimaAlteracao
     */
    public ConsultaParametroDto(Long idParametro, String nomeParametro, String descricaoParametro, Long idInstituicao, TipoParametroDDA tipoParametro,
            Boolean bolPermiteAlteracaoPeloUsuario, Boolean bolVisivelFuncionalidadeAlteracao, Boolean bolAtivo, Boolean bolParametroGlobal, String valorBaseParametro,
            String valorBaseParametroTexto, Date dataCriacao, Date dataUltimaAlteracao, String numeroCooperativa, String descricaoSiglaCooperativa) {
        super();
        this.idParametro = idParametro;
        this.nomeParametro = nomeParametro.trim();
        this.descricaoParametro = descricaoParametro.trim();
        this.idInstituicao = idInstituicao;
        this.tipoParametro = tipoParametro;
        this.bolPermiteAlteracaoPeloUsuario = bolPermiteAlteracaoPeloUsuario;
        this.bolVisivelFuncionalidadeAlteracao = bolVisivelFuncionalidadeAlteracao;
        this.bolAtivo = bolAtivo;
        this.bolParametroGlobal = bolParametroGlobal;
        this.valorBaseParametro = valorBaseParametro;
        this.valorBaseParametroTexto = valorBaseParametroTexto;
        if (dataCriacao != null) {
            this.dataCriacao = new DateTimeDB(dataCriacao.getTime());
        }
        if (dataUltimaAlteracao != null) {
            this.dataUltimaAlteracao = new DateTimeDB(dataUltimaAlteracao.getTime());
        }

        this.numeroCooperativa = numeroCooperativa;

        // Quando a consulta é pelo idInstituicao do Bancoob a consulta não trará o número da cooperativa, pois não existe na view da instituição, neste caso,
        // definiremos o valor 0001.
        if (ObjectUtil.isEmpty(numeroCooperativa) && idInstituicao == Constantes.ID_BANCOOB) {
            this.numeroCooperativa = Constantes.NUMERO_INST_BANCOOB;
        }

        this.descricaoSiglaCooperativa = descricaoSiglaCooperativa;
    }

    /**
     * @return o atributo idParametro
     */
    public Long getIdParametro() {
        return idParametro;
    }

    /**
     * Define o atributo idParametro
     */
    public void setIdParametro(Long idParametro) {
        this.idParametro = idParametro;
    }

    /**
     * @return o atributo nomeParametro
     */
    public String getNomeParametro() {
        return nomeParametro;
    }

    /**
     * Define o atributo nomeParametro
     */
    public void setNomeParametro(String nomeParametro) {
        this.nomeParametro = nomeParametro;
    }

    /**
     * @return o atributo descricaoParametro
     */
    public String getDescricaoParametro() {
        return descricaoParametro;
    }

    /**
     * Define o atributo descricaoParametro
     */
    public void setDescricaoParametro(String descricaoParametro) {
        this.descricaoParametro = descricaoParametro;
    }

    /**
     * @return o atributo idInstituicao
     */
    public Long getIdInstituicao() {
        return idInstituicao;
    }

    /**
     * Define o atributo idInstituicao
     */
    public void setIdInstituicao(Long idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    /**
     * @return o atributo tipoParametro
     */
    public TipoParametroDDA getTipoParametro() {
        return tipoParametro;
    }

    /**
     * Define o atributo tipoParametro
     */
    public void setTipoParametro(TipoParametroDDA tipoParametro) {
        this.tipoParametro = tipoParametro;
    }

    /**
     * @return o atributo bolPermiteAlteracaoPeloUsuario
     */
    public Boolean getBolPermiteAlteracaoPeloUsuario() {
        return bolPermiteAlteracaoPeloUsuario;
    }

    /**
     * Define o atributo bolPermiteAlteracaoPeloUsuario
     */
    public void setBolPermiteAlteracaoPeloUsuario(Boolean bolPermiteAlteracaoPeloUsuario) {
        this.bolPermiteAlteracaoPeloUsuario = bolPermiteAlteracaoPeloUsuario;
    }

    /**
     * @return o atributo bolVisivelFuncionalidadeAlteracao
     */
    public Boolean getBolVisivelFuncionalidadeAlteracao() {
        return bolVisivelFuncionalidadeAlteracao;
    }

    /**
     * Define o atributo bolVisivelFuncionalidadeAlteracao
     */
    public void setBolVisivelFuncionalidadeAlteracao(Boolean bolVisivelFuncionalidadeAlteracao) {
        this.bolVisivelFuncionalidadeAlteracao = bolVisivelFuncionalidadeAlteracao;
    }

    /**
     * @return o atributo bolAtivo
     */
    public Boolean getBolAtivo() {
        return bolAtivo;
    }

    /**
     * Define o atributo bolAtivo
     */
    public void setBolAtivo(Boolean bolAtivo) {
        this.bolAtivo = bolAtivo;
    }

    /**
     * @return o atributo bolParametroGlobal
     */
    public Boolean getBolParametroGlobal() {
        return bolParametroGlobal;
    }

    /**
     * Define o atributo bolParametroGlobal
     */
    public void setBolParametroGlobal(Boolean bolParametroGlobal) {
        this.bolParametroGlobal = bolParametroGlobal;
    }

    /**
     * @return o atributo valorBaseParametro
     */
    public String getValorBaseParametro() {
        return valorBaseParametro;
    }

    /**
     * Define o atributo valorBaseParametro
     */
    public void setValorBaseParametro(String valorBaseParametro) {
        this.valorBaseParametro = valorBaseParametro;
    }

    /**
     * @return o atributo valorBaseParametroTexto
     */
    public String getValorBaseParametroTexto() {
        return valorBaseParametroTexto;
    }

    /**
     * Define o atributo valorBaseParametroTexto
     */
    public void setValorBaseParametroTexto(String valorBaseParametroTexto) {
        this.valorBaseParametroTexto = valorBaseParametroTexto;
    }

    /**
     * @return o atributo dataCriacao
     */
    public DateTimeDB getDataCriacao() {
        return dataCriacao;
    }

    /**
     * Define o atributo dataCriacao
     */
    public void setDataCriacao(DateTimeDB dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    /**
     * @return o atributo dataUltimaAlteracao
     */
    public DateTimeDB getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    /**
     * Define o atributo dataUltimaAlteracao
     */
    public void setDataUltimaAlteracao(DateTimeDB dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    /**
     * @return o atributo numeroCooperativa
     */
    public String getNumeroCooperativa() {
        return numeroCooperativa;
    }

    /**
     * Define o atributo numeroCooperativa
     */
    public void setNumeroCooperativa(String numeroCooperativa) {
        this.numeroCooperativa = numeroCooperativa;
    }

    /**
     * @return o atributo descricaoSiglaCooperativa
     */
    public String getDescricaoSiglaCooperativa() {
        return descricaoSiglaCooperativa;
    }

    /**
     * Define o atributo descricaoSiglaCooperativa
     */
    public void setDescricaoSiglaCooperativa(String descricaoSiglaCooperativa) {
        this.descricaoSiglaCooperativa = descricaoSiglaCooperativa;
    }

    /**
     * @return o atributo somenteVisiveis
     */
    public Boolean getSomenteVisiveis() {
        return somenteVisiveis;
    }

    /**
     * Define o atributo somenteVisiveis
     */
    public void setSomenteVisiveis(Boolean somenteVisiveis) {
        this.somenteVisiveis = somenteVisiveis;
    }

    /**
     * @return o atributo idParametroLegado
     */
    public Integer getIdParametroLegado() {
        return idParametroLegado;
    }

    /**
     * Define o atributo idParametroLegado
     */
    public void setIdParametroLegado(Integer idParametroLegado) {
        this.idParametroLegado = idParametroLegado;
    }

}
