/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates
 * Arquivo:         BeneficiarioDDALoader.java
 * Data Criação:    Nov 4, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates;

import java.util.ArrayList;
import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioInstituicao;
import br.com.sicoob.sisbr.sicoobdda.entidades.HistoricoIfStatusBeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidades.HistoricoStatusBeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.IFBeneficiarioAlerta;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * BeneficiarioDDALoader é responsável por
 * 
 * @author Felipe.Rosa
 */
public final class BeneficiarioDDALoader {

    /**
     * 
     */
    private BeneficiarioDDALoader() {

    }

    /**
     * Método responsável por
     * 
     * @return BeneficiarioDDA
     * 
     */
    public static BeneficiarioDDA geraBeneficiarioDDA() {
        return geraBeneficiarioDDA(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE);
    }

    /**
     * Método responsável por
     * 
     * @param bolOrigemSicoob
     * @return BeneficiarioDDA
     * 
     */
    public static BeneficiarioDDA geraBeneficiarioDDAOrigemSicoob(Boolean bolOrigemSicoob) {
        return geraBeneficiarioDDA(bolOrigemSicoob, Boolean.TRUE, Boolean.TRUE);
    }

    /**
     * Método responsável por
     * 
     * @param bolListaIFBeneficiarioAlerta
     * @return BeneficiarioDDA
     * 
     */
    public static BeneficiarioDDA geraBeneficiarioDDAIFAlerta(Boolean bolListaIFBeneficiarioAlerta) {
        return geraBeneficiarioDDA(Boolean.TRUE, bolListaIFBeneficiarioAlerta, Boolean.TRUE);
    }

    /**
     * Método responsável por
     * 
     * @param bolListaHistoricoStatus
     * @return BeneficiarioDDA
     * 
     */
    public static BeneficiarioDDA geraBeneficiarioDDAHistoricoStatus(Boolean bolListaHistoricoStatus) {
        return geraBeneficiarioDDA(Boolean.TRUE, Boolean.TRUE, bolListaHistoricoStatus);
    }

    /**
     * Método responsável por
     * 
     * @param bolOrigemSicoob
     * @param bolListaIFBeneficiarioAlerta
     * @param bolListaHistoricoStatus
     * @return BeneficiarioDDA
     * 
     */
    public static BeneficiarioDDA geraBeneficiarioDDA(Boolean bolOrigemSicoob, Boolean bolListaIFBeneficiarioAlerta, Boolean bolListaHistoricoStatus) {
        BeneficiarioDDA beneficiario = new BeneficiarioDDA();
        beneficiario.setId(Constantes.LONG_UM);
        beneficiario.setNumCpfCnpjCodTipoPessoa(Constantes.CNPJ_AUX_BIGINT.toString(), TipoPessoaEnum.PJ.getCodDominioCip());
        beneficiario.setSituacaoBeneficiarioDDA(geraSituacaoStatusBeneficiario());
        beneficiario.setDataInicioRelacionamento(Constantes.DATE_TIME_DB_AUX);
        beneficiario.setDataHoraUltimaAtualizacao(Constantes.DATE_TIME_DB_AUX);
        beneficiario.setBolOrigemSicoob(bolOrigemSicoob);
        beneficiario.setIdProduto(Constantes.ID_PRODUTO_COBRANCA);
        if (bolOrigemSicoob) {
            beneficiario.setNumIdentBeneficiario(Constantes.LONG_UM);
            beneficiario.setNumCtrlDDA(Constantes.NOME_TESTE);
            beneficiario.setNumRefAtualCadBeneficiario(Constantes.LONG_UM);
            beneficiario.setNumSeqAtualCadBeneficiario(Constantes.LONG_UM);
            beneficiario.setListaBeneficiarioInstituicao(geraListaBeneficiarioInstituicao(beneficiario));

        }

        if (bolListaIFBeneficiarioAlerta) {
            beneficiario.setListaIFBeneficiarioAlerta(geraListaIFBeneficiarioAlerta(beneficiario));
        }

        if (bolListaHistoricoStatus) {
            beneficiario.setListaHistoricoStatusBeneficiarioDDA(geraListaHistoricoStatus(beneficiario));
        }

        return beneficiario;
    }

    /**
     * Método responsável por
     * 
     * @param beneficiario
     * @return List<BeneficiarioInstituicao>
     * 
     */
    private static List<BeneficiarioInstituicao> geraListaBeneficiarioInstituicao(BeneficiarioDDA beneficiario) {
        List<BeneficiarioInstituicao> listaBeneficiarioInstituicao = new ArrayList<BeneficiarioInstituicao>();
        listaBeneficiarioInstituicao.add(new BeneficiarioInstituicao(beneficiario, Constantes.INTEGER_UM, Constantes.DATE_TIME_DB_AUX));
        return listaBeneficiarioInstituicao;
    }

    /**
     * Método responsável por
     * 
     * @return List<IFBeneficiarioAlerta>
     * 
     */
    private static List<IFBeneficiarioAlerta> geraListaIFBeneficiarioAlerta(BeneficiarioDDA beneficiarioDDA) {
        List<IFBeneficiarioAlerta> listaIFBeneficiarioAlerta = new ArrayList<IFBeneficiarioAlerta>();
        listaIFBeneficiarioAlerta.add(new IFBeneficiarioAlerta(beneficiarioDDA, Constantes.NOME_TESTE, Constantes.DATE_TIME_DB_AUX));
        return listaIFBeneficiarioAlerta;
    }

    /**
     * Método responsável por
     * 
     * @param beneficiarioDDA
     * 
     * @return List<HistoricoStatusBeneficiarioDDA>
     * 
     */
    private static List<HistoricoStatusBeneficiarioDDA> geraListaHistoricoStatus(BeneficiarioDDA beneficiarioDDA) {
        List<HistoricoStatusBeneficiarioDDA> listaHistoricoStatus = new ArrayList<HistoricoStatusBeneficiarioDDA>();
        listaHistoricoStatus.add(geraHistoricoStatusBeneficiario(beneficiarioDDA));
        return listaHistoricoStatus;
    }

    /**
     * Método responsável por
     * 
     * @param beneficiarioDDA
     * @return HistoricoStatusBeneficiarioDDA
     * 
     */
    private static HistoricoStatusBeneficiarioDDA geraHistoricoStatusBeneficiario(BeneficiarioDDA beneficiarioDDA) {
        HistoricoStatusBeneficiarioDDA historico = new HistoricoStatusBeneficiarioDDA(beneficiarioDDA, geraSituacaoStatusBeneficiario(), Constantes.DATE_TIME_DB_AUX);
        historico.setListaHistoricoIfStatusBeneficiario(geraListaHistoricoIFStatus(historico));
        return historico;
    }

    /**
     * Método responsável por
     * 
     * @return SituacaoBeneficiarioDDA
     * 
     */
    private static SituacaoBeneficiarioDDA geraSituacaoStatusBeneficiario() {
        return new SituacaoBeneficiarioDDA(SituacaoBeneficiarioEnum.APTO.getCodDominio());
    }

    /**
     * Método responsável por
     * 
     * @param historicoStatusBeneficiarioDDA
     * @return List<HistoricoIfStatusBeneficiario>
     * 
     */
    private static List<HistoricoIfStatusBeneficiario> geraListaHistoricoIFStatus(HistoricoStatusBeneficiarioDDA historicoStatusBeneficiarioDDA) {
        List<HistoricoIfStatusBeneficiario> listaHistoricoIfStatus = new ArrayList<HistoricoIfStatusBeneficiario>();
        listaHistoricoIfStatus.add(new HistoricoIfStatusBeneficiario(historicoStatusBeneficiarioDDA, Constantes.ISPB_BANCOOB, Constantes.DATE_TIME_DB_AUX));
        return listaHistoricoIfStatus;
    }
}
