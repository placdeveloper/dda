/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates
 * Arquivo:         MensagemDDATerceiroAutLoader.java
 * Data Criação:    May 11, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates;

import java.util.ArrayList;
import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDATerceiroAut;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * MensagemDDATerceiroAutLoader é responsável por
 * 
 * @author Felipe.Rosa
 */
public class MensagemDDATerceiroAutLoader extends MensagemDDALoader {

    /**
     * Método responsável por
     * 
     * @return MensagemDDATerceiroAut
     * 
     */
    public static MensagemDDATerceiroAut gerar() {
        MensagemDDATerceiroAut mensagemDDATerceiro = new MensagemDDATerceiroAut();
        mensagemDDATerceiro.setId(Constantes.LONG_UM);
        mensagemDDATerceiro.setMensagemDDA(geraMensagemDDA());
        mensagemDDATerceiro.setNumIdentificadorBoletoCip(Constantes.LONG_UM);
        mensagemDDATerceiro.setNumIdentificadorTerceiro(Constantes.LONG_UM);
        mensagemDDATerceiro.setCodTipoPessoaAutorizador(TipoPessoaEnum.PF.getCodDominioCip());
        mensagemDDATerceiro.setNumCpfCnpjAutorizador(Constantes.CPF_AUX);
        mensagemDDATerceiro.setCodTipoPessoaTerceiro(TipoPessoaEnum.PF.getCodDominioCip());
        mensagemDDATerceiro.setNumCpfCnpjTerceiro(Constantes.CPF_AUX);
        mensagemDDATerceiro.setIdEventoTituloDDA(Constantes.LONG_UM);
        return mensagemDDATerceiro;
    }

    /**
     * Método responsável por
     * 
     * @return List<MensagemDDATerceiroAut>
     * 
     */
    public static List<MensagemDDATerceiroAut> gerarLista() {
        List<MensagemDDATerceiroAut> lista = new ArrayList<MensagemDDATerceiroAut>();
        lista.add(gerar());
        return lista;
    }

}
