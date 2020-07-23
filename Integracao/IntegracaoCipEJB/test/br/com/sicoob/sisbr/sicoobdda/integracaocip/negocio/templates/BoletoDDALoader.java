/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates
 * Arquivo:         BoletoDDALoader.java
 * Data Criação:    Oct 31, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDAGrupoCalculo;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaSacadorAvalistaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * BoletoDDALoader é responsável por
 * 
 * @author Felipe.Rosa
 */
public final class BoletoDDALoader {

    /**
     * 
     */
    private BoletoDDALoader() {

    }

    /**
     * Método responsável por
     * 
     * @return BoletoDDA
     * 
     */
    public static BoletoDDA geraBoletoDDA() {
        BoletoDDA boletoDDA = new BoletoDDA();
        boletoDDA.setId(Constantes.LONG_UM);
        boletoDDA.setCodPartDestinatario(Constantes.NOME_TESTE);
        boletoDDA.setNumIdentificadorBoletoCip(Constantes.LONG_UM);
        boletoDDA.setNumRefAtualCadBoleto(Constantes.LONG_UM);
        boletoDDA.setNumSeqAtualCadBoleto(Constantes.LONG_ZERO);
        boletoDDA.setDataHoraSituacaoBoleto(new DateTimeDB());
        boletoDDA.setCodIspbPartDestinatario(Constantes.NOME_TESTE);
        boletoDDA.setNumCpfCnpjCodTipoPessoaBeneficiario(Constantes.CNPJ_AUX, TipoPessoaEnum.PJ.getCodDominioCip());
        boletoDDA.setNomeBeneficiario(Constantes.NOME_TESTE);
        boletoDDA.setNomeFantasiaBeneficiario(Constantes.NOME_TESTE);
        boletoDDA.setDescLogradouroBeneficiario(Constantes.NOME_TESTE);
        boletoDDA.setDescCidadeBeneficiario(Constantes.NOME_TESTE);
        boletoDDA.setSiglaUfBeneficiario(Constantes.NOME_TESTE);
        boletoDDA.setNumCepBeneficiario(Constantes.NOME_TESTE);
        boletoDDA.setNumCpfCnpjCodTipoPessoaBeneficiarioFinal(Constantes.CNPJ_AUX, TipoPessoaEnum.PJ.getCodDominioCip());
        boletoDDA.setNomeBeneficiarioFinal(Constantes.NOME_TESTE);
        boletoDDA.setNomeFantasiaBeneficiarioFinal(Constantes.NOME_TESTE);
        boletoDDA.setNumCpfCnpjCodTipoPessoaPagador(Constantes.CNPJ_AUX, TipoPessoaEnum.PJ.getCodDominioCip());
        boletoDDA.setNomePagador(Constantes.NOME_TESTE);
        boletoDDA.setNomeFantasiaPagador(Constantes.NOME_TESTE);
        boletoDDA.setDescLogradouroPagador(Constantes.NOME_TESTE);
        boletoDDA.setDescCidadePagador(Constantes.NOME_TESTE);
        boletoDDA.setSiglaUfPagador(Constantes.NOME_TESTE);
        boletoDDA.setNumCepPagador(Constantes.NOME_TESTE);
        boletoDDA.setNumCpfCnpjCodTipoPessoaAvalista(Constantes.CNPJ_AUX, TipoPessoaSacadorAvalistaEnum.CNPJ.getCodDominio());
        boletoDDA.setNomeAvalista(Constantes.NOME_TESTE);
        boletoDDA.setIdCarteira(Constantes.INTEGER_UM);
        boletoDDA.setIdEspecieDocumento(Constantes.INTEGER_UM);
        boletoDDA.setNumDocumento(Constantes.NOME_TESTE);
        boletoDDA.setCodTipoPagamento(Constantes.INTEGER_UM);
        boletoDDA.setNumParcela(Constantes.INTEGER_UM);
        boletoDDA.setQtdTotalParcela(Constantes.INTEGER_UM);
        boletoDDA.setBolTituloNegociado(Boolean.TRUE);
        boletoDDA.setQtdPagamentoParcial(Constantes.INTEGER_UM);
        boletoDDA.setDataVencimento(Constantes.DATE_TIME_DB_AUX);
        boletoDDA.setValorBoleto(BigDecimal.ONE);
        boletoDDA.setNumDiasProtesto(Constantes.INTEGER_UM);
        boletoDDA.setDataLimitePagamento(Constantes.DATE_TIME_DB_AUX);
        boletoDDA.setBolBloqueioPagamento(Boolean.TRUE);
        boletoDDA.setValorAbatimento(BigDecimal.ONE);
        boletoDDA.setCodTipoPagamento(Constantes.INTEGER_UM);
        boletoDDA.setCodMoeda(Constantes.NOME_TESTE);
        boletoDDA.setNumNossoNumero(Constantes.NOME_TESTE);
        boletoDDA.setNumCodigoBarra(Constantes.NOME_TESTE);
        boletoDDA.setNumCodBarrasCampoLivre(Constantes.NOME_TESTE);
        boletoDDA.setNumLinhaDigitavel(Constantes.NOME_TESTE);
        boletoDDA.setDataEmissao(Constantes.DATE_TIME_DB_AUX);
        boletoDDA.setBolPagamentoParcial(Boolean.TRUE);
        // boletoDDA.setBoletoDDAJuros(new BoletoDDAJuros(boletoDDA, Constantes.DATE_TIME_DB_AUX, Constantes.SHORT_UM, BigDecimal.ONE));
        // boletoDDA.setBoletoDDAMulta(new BoletoDDAMulta(boletoDDA, Constantes.DATE_TIME_DB_AUX, Constantes.SHORT_UM, BigDecimal.ONE));
        // boletoDDA.setListaBoletoDDADesconto(geraListaBoletoDDADesconto(boletoDDA));
        boletoDDA.setCodAutorizacaoValorDivergente(Constantes.NOME_TESTE);
        boletoDDA.setCodTipoModeloCalculo(Constantes.NOME_TESTE);
        boletoDDA.setCodIndicadorValorMinimo(Constantes.NOME_TESTE);
        boletoDDA.setValorMinimo(BigDecimal.ONE);
        boletoDDA.setCodIndicadorValorMaximo(Constantes.NOME_TESTE);
        boletoDDA.setValorMaximo(BigDecimal.ONE);
        boletoDDA.setListaBoletoDDAGrupoCalculo(geraListaBoletoDDAGrupoCalculo(boletoDDA));
        boletoDDA.setValorSaldoPagamento(BigDecimal.ONE);
        boletoDDA.setDataHoraSituacaoBoleto(Constantes.DATE_TIME_DB_AUX);
        boletoDDA.setCodSituacaoBoletoPagamento(Constantes.NOME_TESTE);
        boletoDDA.setBolAceite(Boolean.FALSE);
        boletoDDA.setQtdPagamentoParcialReg(Constantes.INTEGER_UM);
        return boletoDDA;
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @return List<BoletoDDAGrupoCalculo>
     * 
     */
    private static List<BoletoDDAGrupoCalculo> geraListaBoletoDDAGrupoCalculo(BoletoDDA boletoDDA) {
        List<BoletoDDAGrupoCalculo> lista = new ArrayList<BoletoDDAGrupoCalculo>();
        lista.add(new BoletoDDAGrupoCalculo(boletoDDA, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE, Constantes.DATE_TIME_DB_AUX));
        return lista;
    }

    /**
     * Método responsável por
     * 
     * @return List<BoletoDDADesconto>
     * 
     */
    // private static List<BoletoDDADesconto> geraListaBoletoDDADesconto(BoletoDDA boletoDDA) {
    // List<BoletoDDADesconto> lista = new ArrayList<BoletoDDADesconto>();
    // lista.add(new BoletoDDADesconto(boletoDDA, Constantes.DATE_TIME_DB_AUX, Constantes.NOME_TESTE, BigDecimal.ONE));
    // return lista;
    // }

}
