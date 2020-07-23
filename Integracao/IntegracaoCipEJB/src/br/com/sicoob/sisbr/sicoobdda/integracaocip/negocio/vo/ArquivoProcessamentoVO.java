package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo;

import java.io.Serializable;

import br.com.sicoob.sisbr.sicoobdda.integracaocip.enums.TipoArquivoRetornoEnum;

/**
 * ArquivoParaProc é responsável por
 * 
 * @author Adriano.Pinheiro
 */
public class ArquivoProcessamentoVO implements Serializable, Comparable<ArquivoProcessamentoVO> {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private long idArquivoRecebido;
    private TipoArquivoRetornoEnum tipoArquivo;
    private long idArquivoEnviado;
    private String nmArquivoRecebido;
    private String tipoDaMensagem;
    private short codSituacaoProcessamentoArquivo;

    private Long idDetLogRecebimentoIni;
    private Long idDetLogRecebimentoFin;
    private Long qtdRegistros;

    /**
     * @param idrec
     * @param tpArq
     * @param nmArq
     * @param idEnv
     * @param tipoMensagem
     * @param codSituacaoProcessamentoArquivo
     */
    public ArquivoProcessamentoVO(long idrec, TipoArquivoRetornoEnum tpArq, String nmArq, long idEnv, String tipoMensagem, short codSituacaoProcessamentoArquivo) {
        super();
        this.idArquivoRecebido = idrec;
        this.tipoArquivo = tpArq;
        this.nmArquivoRecebido = nmArq;
        this.idArquivoEnviado = idEnv;
        this.tipoDaMensagem = tipoMensagem;
        this.codSituacaoProcessamentoArquivo = codSituacaoProcessamentoArquivo;
    }

    /**
     * 
     */
    public ArquivoProcessamentoVO() {
        this(0l, null, null, 0l, null, (short) 0);
    }

    /**
     * @param idArquivoRecebido
     * @param tipoDaMensagem
     * @param idDetLogRecebimentoIni
     * @param idDetLogRecebimentoFin
     * @param numIteracao
     * @param totalIteracao
     */
    public ArquivoProcessamentoVO(long idArquivoRecebido, String tipoDaMensagem, Long idDetLogRecebimentoIni, Long idDetLogRecebimentoFin) {
        this(idArquivoRecebido, null, null, 0l, tipoDaMensagem, (short) 0);
        this.idDetLogRecebimentoIni = idDetLogRecebimentoIni;
        this.idDetLogRecebimentoFin = idDetLogRecebimentoFin;
    }

    /**
     * @param idDetLogRecebimentoIni
     * @param idDetLogRecebimentoFin
     * @param qtdRegistros
     */
    public ArquivoProcessamentoVO(Long idDetLogRecebimentoIni, Long idDetLogRecebimentoFin, Long qtdRegistros) {
        this(0l, null, idDetLogRecebimentoIni, idDetLogRecebimentoFin);
        this.qtdRegistros = qtdRegistros;
    }

    /**
     * @return o atributo idArquivoRecebido
     */
    public long getIdArquivoRecebido() {
        return idArquivoRecebido;
    }

    /**
     * Define o atributo idArquivoRecebido
     */
    public void setIdArquivoRecebido(long idArquivoRecebido) {
        this.idArquivoRecebido = idArquivoRecebido;
    }

    /**
     * @return o atributo tipoArquivo
     */
    public TipoArquivoRetornoEnum getTipoArquivo() {
        return tipoArquivo;
    }

    /**
     * Define o atributo tipoArquivo
     */
    public void setTipoArquivo(TipoArquivoRetornoEnum tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    /**
     * @return o atributo idArquivoEnviado
     */
    public long getIdArquivoEnviado() {
        return idArquivoEnviado;
    }

    /**
     * Define o atributo idArquivoEnviado
     */
    public void setIdArquivoEnviado(long idArquivoEnviado) {
        this.idArquivoEnviado = idArquivoEnviado;
    }

    /**
     * @return o atributo nmArquivoRecebido
     */
    public String getNmArquivoRecebido() {
        return nmArquivoRecebido;
    }

    /**
     * Define o atributo nmArquivoRecebido
     */
    public void setNmArquivoRecebido(String nmArquivoRecebido) {
        this.nmArquivoRecebido = nmArquivoRecebido;
    }

    /**
     * @return o atributo tipoDaMensagem
     */
    public String getTipoDaMensagem() {
        return tipoDaMensagem;
    }

    /**
     * Define o atributo tipoDaMensagem
     */
    public void setTipoDaMensagem(String tipoDaMensagem) {
        this.tipoDaMensagem = tipoDaMensagem;
    }

    /**
     * @return o atributo idDetLogRecebimentoIni
     */
    public Long getIdDetLogRecebimentoIni() {
        return idDetLogRecebimentoIni;
    }

    /**
     * Define o atributo idDetLogRecebimentoIni
     */
    public void setIdDetLogRecebimentoIni(Long idDetLogRecebimentoIni) {
        this.idDetLogRecebimentoIni = idDetLogRecebimentoIni;
    }

    /**
     * @return o atributo idDetLogRecebimentoFin
     */
    public Long getIdDetLogRecebimentoFin() {
        return idDetLogRecebimentoFin;
    }

    /**
     * Define o atributo idDetLogRecebimentoFin
     */
    public void setIdDetLogRecebimentoFin(Long idDetLogRecebimentoFin) {
        this.idDetLogRecebimentoFin = idDetLogRecebimentoFin;
    }

    /**
     * @return o atributo qtdRegistros
     */
    public Long getQtdRegistros() {
        return qtdRegistros;
    }

    /**
     * Define o atributo qtdRegistros
     */
    public void setQtdRegistros(Long qtdRegistros) {
        this.qtdRegistros = qtdRegistros;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(ArquivoProcessamentoVO o) {
        if (equals(o)) {
            return 0;
        } else if (this.getIdArquivoEnviado() < o.getIdArquivoEnviado()) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (idArquivoRecebido ^ (idArquivoRecebido >>> 32));
        result = prime * result + ((nmArquivoRecebido == null) ? 0 : nmArquivoRecebido.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()){
            return false;
        }
        ArquivoProcessamentoVO other = (ArquivoProcessamentoVO) obj;
        if (idArquivoRecebido != other.idArquivoRecebido){
            return false;
        }
        if (nmArquivoRecebido == null) {
            if (other.nmArquivoRecebido != null){
                return false;
            }
        } else if (!nmArquivoRecebido.equals(other.nmArquivoRecebido)){
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ARQPROC:[ idArqRec:" + this.idArquivoRecebido + " idArcEnv: " + this.idArquivoEnviado + " nmArqRec: " + this.nmArquivoRecebido + " tipoMSG: " + this.tipoDaMensagem + " tipoArqRec: "
                + this.tipoArquivo.getDescricao() + " ]";
    }

    /**
     * @return the codSituacaoProcessamentoArquivo
     */
    public short getCodSituacaoProcessamentoArquivo() {
        return codSituacaoProcessamentoArquivo;
    }

    /**
     * @param codSituacaoProcessamentoArquivo the codSituacaoProcessamentoArquivo to set
     */
    public void setCodSituacaoProcessamentoArquivo(short codSituacaoProcessamentoArquivo) {
        this.codSituacaoProcessamentoArquivo = codSituacaoProcessamentoArquivo;
    }

}
