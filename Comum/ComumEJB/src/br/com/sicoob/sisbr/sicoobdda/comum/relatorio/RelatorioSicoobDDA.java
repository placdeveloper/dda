/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.relatorio
 * Arquivo:         RelatorioSicoobDDA.java
 * Data Criação:    22/06/2012
 */
package br.com.sicoob.sisbr.sicoobdda.comum.relatorio;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.jasperreports.engine.JRDataSource;
import br.com.bancoob.infraestrutura.FabricaServicos;
import br.com.bancoob.infraestrutura.relatorios.ServicoRelatorios;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.negocio.excecao.RelatorioException;
import br.com.bancoob.negocio.relatorios.ColecaoDataSource;
import br.com.bancoob.negocio.relatorios.jasper.RelatorioJasperReports;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.RelatorioSicoobDDAEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;

/**
 * RelatorioSicoobDDA é responsável por
 * 
 * @author Kaio.Rocha
 */
public class RelatorioSicoobDDA extends RelatorioJasperReports {

    private static final long serialVersionUID = 1L;
    public static final String MAP_SUBREPORT_DIR = "SUBREPORT_DIR";
    public static final String MAP_APLICATIVO_SISBR = "aplicativoSisBR";
    public static final String MAP_LOGIN_USUARIO = "loginUsuario";
    public static final String MAP_CODIGO_RELATORIO = "codigoRelatorio";
    public static final String MAP_NOME_RELATORIO = "nomeRelatorio";
    public static final String MAP_NUM_COOPERATIVA = "numCooperativa";
    public static final String MAP_DESC_FILTRO_OPCIONAL = "descFiltroOpcional";
    private static final String APLICATIVO_SISBR = "SISBR 2.0 - Sicoob DDA";
    private final List<?> lista;
    private RelatorioSicoobDDAEnum relatorioSicoobDDAEnum;

    /**
     * Construtor RelatorioSicoobDDA para utilização do Novo Template Sicoob
     * 
     * @param relatorioSicoobDDAEnum
     * @param lista
     * @param mapaParametros
     */
    @SuppressWarnings({ "unchecked" })
    public RelatorioSicoobDDA(RelatorioSicoobDDAEnum relatorioSicoobDDAEnum, List<?> lista, Map<String, Object> mapaParametros) {
        super(relatorioSicoobDDAEnum.getArquivoJasper());
        this.lista = lista;
        this.relatorioSicoobDDAEnum = relatorioSicoobDDAEnum;

        if (mapaParametros != null) {
            for (Entry<String, Object> entry : mapaParametros.entrySet()) {
                getParametros().put(entry.getKey(), entry.getValue());
            }
        }

        getParametros().put(MAP_SUBREPORT_DIR, recuperarCaminhoRelatorios(relatorioSicoobDDAEnum.getArquivoJasper()));
        adicionarParametrosTemplate(relatorioSicoobDDAEnum);
        verificarFiltroOpcional();
    }

    /**
     * Método responsável por  void
     * 
     */
    @SuppressWarnings("unchecked")
    private void verificarFiltroOpcional() {
        String filtroOpcional = "";
        Object dataMovimentoObjeto = getParametros().get("dataMovimento");
        Object dataInicialObjeto = getParametros().get("dataInicial");
        Object dataFinalObjeto = getParametros().get("dataFinal");
        if (dataMovimentoObjeto != null) {
            filtroOpcional = "Movimento do dia ";
            if (dataMovimentoObjeto instanceof Date) {
                Date dataMovimento = (Date) dataMovimentoObjeto;
                filtroOpcional = filtroOpcional.concat(DateUtil.formatarData(dataMovimento, "dd/MM/yyyy"));
            } else {
                filtroOpcional = String.valueOf(dataMovimentoObjeto);
            }
        } else if (dataInicialObjeto != null && dataFinalObjeto != null) {
            if (dataInicialObjeto instanceof Date && dataFinalObjeto instanceof Date) {
                filtroOpcional = DateUtil.formatarData((Date) dataInicialObjeto, "dd/MM/yyyy") + " a " + DateUtil.formatarData((Date) dataFinalObjeto, "dd/MM/yyyy");
            } else {
                filtroOpcional = dataInicialObjeto + " a " + dataFinalObjeto;
            }
        }
        getParametros().put(MAP_DESC_FILTRO_OPCIONAL, filtroOpcional);
    }

    /**
     * Método responsável por 
     * @param relatorioSicoobDDAEnum void
     * 
     */
    @SuppressWarnings("unchecked")
    private void adicionarParametrosTemplate(RelatorioSicoobDDAEnum relatorioSicoobDDAEnum) {
        String usuario = null;
        String numCooperativa = null;
        if (!ObjectUtil.isNull(InformacoesUsuario.getInstance())) {
            usuario = InformacoesUsuario.getInstance().getLogin();
            numCooperativa = InformacoesUsuario.getInstance().getCooperativa();
        }
        getParametros().put(MAP_APLICATIVO_SISBR, APLICATIVO_SISBR);
        getParametros().put(MAP_LOGIN_USUARIO, usuario != null ? usuario.toLowerCase() : "");
        getParametros().put(MAP_CODIGO_RELATORIO, relatorioSicoobDDAEnum.getCodigoRelatorio());
        getParametros().put(MAP_NOME_RELATORIO, relatorioSicoobDDAEnum.getNomeRelatorio());
        getParametros().put(MAP_NUM_COOPERATIVA, numCooperativa);
    }

    /**
     * Método responsável por obter o caminho para os subrelatórios a serem gerados.
     * 
     * @return String
     */
    private String recuperarCaminhoRelatorios(String nomeRelatorio) {
        ServicoRelatorios servico = FabricaServicos.getInstance().criarServicoRelatorios();
        return servico.recuperarRelatorio(nomeRelatorio).getUrl().getPath().replace(nomeRelatorio, "");
    }

    /**
     * 
     * Método responsável por
     * 
     * @return RelatorioSicoobDDAEnum
     * 
     */
    public RelatorioSicoobDDAEnum getRelatorioSicoobDDAEnum() {
        return relatorioSicoobDDAEnum;
    }

    /**
     * @return o atributo lista
     */
    public List<?> getLista() {
        return lista;
    }

    /**
     * 
     * Método responsável por retornar o Codigo do Relatorio (RelatorioSicoobDDAEnum)
     * 
     * @return String
     * 
     */
    public String getCodigoRelatorio() {
        return getRelatorioSicoobDDAEnum() != null ? getRelatorioSicoobDDAEnum().getCodigoRelatorio() : "";
    }

    /**
     * 
     * Método responsável por retornar o mesmo nome da ConstantesRelatorios.as
     * 
     * @return String
     * 
     */
    public String getContextoFlex() {
        return getRelatorioSicoobDDAEnum() != null ? getRelatorioSicoobDDAEnum().getContextoFlex() : "";
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.relatorios.jasper.RelatorioJasperReports#getDataSource()
     */
    @Override
    protected JRDataSource getDataSource() throws RelatorioException {
        return new ColecaoDataSource(this.lista, 1, true);
    }

    /**
     * @return
     * @throws RelatorioException JRDataSource
     * 
     */
    protected JRDataSource getDataSourceEmissao2Via() throws RelatorioException {
        return new ColecaoDataSource(this.lista, 1, true);
    }

}
