package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Rodrigo.Neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.cobrancabancaria.entidades.negocio.entidades.BancoDepositario")
public class BancoDepositarioVO extends BancoobVO {

    private Short numBancoDepositario;

    /**
     * @return o atributo numBancoDepositario
     */
    public Short getNumBancoDepositario() {
        return numBancoDepositario;
    }

    /**
     * Define o atributo numBancoDepositario
     */
    public void setNumBancoDepositario(Short numBancoDepositario) {
        this.numBancoDepositario = numBancoDepositario;
    }

}
