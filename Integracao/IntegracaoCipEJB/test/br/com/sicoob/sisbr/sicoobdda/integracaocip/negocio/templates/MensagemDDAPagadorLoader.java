/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates
 * Arquivo:         MensagePagadorDDALoader.java
 * Data Criação:    Oct 25, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoOperacaoSicoobDDAEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagadorAgregado;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagadorConta;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * MensagemDDAPagadorLoader é responsável por
 * 
 * @author Felipe.Rosa
 */
public final class MensagemDDAPagadorLoader extends MensagemDDALoader {

    /**
     * 
     */
    private MensagemDDAPagadorLoader() {
        super();
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDAPagador
     * 
     */
    public static MensagemDDAPagador gerar() {
        MensagemDDAPagador mensagemDDAPagador = new MensagemDDAPagador();
        mensagemDDAPagador.setId(Constantes.LONG_UM);
        mensagemDDAPagador.setCodTipoPessoaPagador(Constantes.TIPO_PESSOA_FISICA_TESTE);
        mensagemDDAPagador.setNumCpfCnpjPagador(Constantes.CPF_AUX);
        mensagemDDAPagador.setBolPagadorEletronico(Boolean.TRUE);

        mensagemDDAPagador.setNumIdentificaPagadorCip(Constantes.LONG_UM);
        mensagemDDAPagador.setNumRefAtualCadPagador(Constantes.LONG_UM);

        mensagemDDAPagador.getListaMensagemDDAPagadorConta().add(geraMensagemPagadorConta());
        mensagemDDAPagador.getListaMensagemDDAPagadorConta().add(geraMensagemPagadorConta(Boolean.TRUE));
        mensagemDDAPagador.getListaMensagemDDAPagadorAgregado().add(geraMensagemPagadorAgregado());

        mensagemDDAPagador.setIdEventoDDA(Constantes.LONG_UM);

        mensagemDDAPagador.setMensagemDDA(geraMensagemDDA());
        return mensagemDDAPagador;
    }

    /**
     * Método responsável por
     * 
     * @return List<MensagemDDAPagador>
     * 
     */
    public static List<MensagemDDAPagador> gerarLista() {
        List<MensagemDDAPagador> lista = new ArrayList<MensagemDDAPagador>();
        lista.add(gerar());
        return lista;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDAPagadorConta
     * 
     */
    public static MensagemDDAPagadorConta geraMensagemPagadorConta() {
        return geraMensagemPagadorConta(Boolean.FALSE);
    }

    /**
     * Método responsável por
     * 
     * @param bolDiferente
     * @return MensagemDDAPagadorConta
     * 
     */
    private static MensagemDDAPagadorConta geraMensagemPagadorConta(Boolean bolDiferente) {
        MensagemDDAPagadorConta mensagemDDAConta = new MensagemDDAPagadorConta();
        mensagemDDAConta.setCodTipoOperacao(TipoOperacaoSicoobDDAEnum.INCLUSAO.getCodigoOperacao());
        mensagemDDAConta.setNumAgencia(bolDiferente ? Constantes.INTEGER_ZERO : Constantes.INTEGER_UM);
        mensagemDDAConta.setNumContaCorrente(BigDecimal.ONE);
        return mensagemDDAConta;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDAPagadorAgregado
     * 
     */
    public static MensagemDDAPagadorAgregado geraMensagemPagadorAgregado() {
        MensagemDDAPagadorAgregado mensagemDDAAgregado = new MensagemDDAPagadorAgregado();
        mensagemDDAAgregado.setCodTipoOperacao(TipoOperacaoSicoobDDAEnum.INCLUSAO.getCodigoOperacao());
        mensagemDDAAgregado.setCodTipoPessoaAgregado(TipoPessoaEnum.PF.getCodDominioCip());
        mensagemDDAAgregado.setNumCpfCnpjAgregado(Constantes.CPF_AUX);
        return mensagemDDAAgregado;
    }
}
