package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * LogDetalheEnvioArquivoDDAVO
 * 
 * @author samuell.ramos
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.LogDetalheEnvioArquivoDDA")
public class LogDetalheEnvioArquivoDDAVO extends BancoobVO {
    
	private Long id;
    private LogEnvioArquivoDDAVO logEnvioArquivoDDA;
    private MensagemDDAVO mensagemDDA;
	/**
	 * @return o atributo id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Define o atributo id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return o atributo logEnvioArquivoDDA
	 */
	public LogEnvioArquivoDDAVO getLogEnvioArquivoDDA() {
		return logEnvioArquivoDDA;
	}
	/**
	 * Define o atributo logEnvioArquivoDDA
	 */
	public void setLogEnvioArquivoDDA(LogEnvioArquivoDDAVO logEnvioArquivoDDA) {
		this.logEnvioArquivoDDA = logEnvioArquivoDDA;
	}
	/**
	 * @return o atributo mensagemDDA
	 */
	public MensagemDDAVO getMensagemDDA() {
		return mensagemDDA;
	}
	/**
	 * Define o atributo mensagemDDA
	 */
	public void setMensagemDDA(MensagemDDAVO mensagemDDA) {
		this.mensagemDDA = mensagemDDA;
	}
    
}
