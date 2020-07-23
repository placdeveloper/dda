package br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * AgendamentoBoletoDDAAPIDto � respons�vel por
 * 
 * @author Rodrigo.Neri
 */
public class AgendamentoBoletoDDAAPIDto extends BancoobDto {

    private static final long serialVersionUID = -8106178620289620700L;

    private Boolean ddaAtivado;
    private Integer valorMinimoDDA;

    /**
     * @return o atributo ddaAtivado
     */
    public Boolean getDdaAtivado() {
        return ddaAtivado;
    }

    /**
     * Define o atributo ddaAtivado
     */
    public void setDdaAtivado(Boolean ddaAtivado) {
        this.ddaAtivado = ddaAtivado;
    }

    /**
     * @return o atributo valorMinimoDDA
     */
    public Integer getValorMinimoDDA() {
        return valorMinimoDDA;
    }

    /**
     * Define o atributo valorMinimoDDA
     */
    public void setValorMinimoDDA(Integer valorMinimoDDA) {
        this.valorMinimoDDA = valorMinimoDDA;
    }

}
