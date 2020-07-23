/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.relatorio
 * Arquivo:         ConfigurarRelatorioJasper.java
 * Data Criação:    27/09/2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.relatorio;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.relatorio.api.datasource.ColecaoDataSource;
import br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO;
import br.com.sicoob.relatorio.api.jasper.SicoobJasperReports;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.RelatorioSicoobDDAEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

/**
 * @author Danilo.Barros
 *
 */
public class ConfigurarRelatorioJasper extends SicoobJasperReports {

    private static final long serialVersionUID = -1601612512144847177L;

    private static final String APLICATIVO_SISBR = "SISBR 2.0 - Sicoob DDA";
    private static final String MAP_APLICATIVO_SISBR = "aplicativoSisBR";
    private static final String MAP_SUBREPORT_DIR = "SUBREPORT_DIR";
    private static final String MAP_LOGIN_USUARIO = "loginUsuario";
    private static final String MAP_CODIGO_RELATORIO = "codigoRelatorio";
    private static final String MAP_NOME_RELATORIO = "nomeRelatorio";
    private static final String MAP_NUM_COOPERATIVA = "numCooperativa";
    private static final String MAP_DESC_FILTRO_OPCIONAL = "descFiltroOpcional";

    private final transient JRDataSource dataSource;

    /**
     * @param relatorioSicoobDDAEnum
     * @param dados
     * @param usuarioDTO
     */
    public ConfigurarRelatorioJasper(RelatorioSicoobDDAEnum relatorioSicoobDDAEnum, List<?> dados, UsuarioBancoobDTO usuarioDTO) {
        this(relatorioSicoobDDAEnum, new ColecaoDataSource(dados, Constantes.INTEGER_UM, Boolean.TRUE), usuarioDTO, null);
    }

    /**
     * @param relatorioSicoobDDAEnum
     * @param dados
     * @param usuarioDTO
     * @param parametros
     */
    public ConfigurarRelatorioJasper(RelatorioSicoobDDAEnum relatorioSicoobDDAEnum, List<?> dados, UsuarioBancoobDTO usuarioDTO, Map<String, Object> parametros) {
        this(relatorioSicoobDDAEnum, new ColecaoDataSource(dados, Constantes.INTEGER_UM, Boolean.TRUE), usuarioDTO, parametros);
    }

    /**
     * @param relatorioSicoobDDAEnum
     * @param dataSource
     * @param usuarioDTO
     * @param parametros
     */
    public ConfigurarRelatorioJasper(RelatorioSicoobDDAEnum relatorioSicoobDDAEnum, JRDataSource dataSource, UsuarioBancoobDTO usuarioDTO, Map<String, Object> parametros) {
        super(relatorioSicoobDDAEnum.getArquivoJasper());
        this.dataSource = dataSource;

        if (!ObjectUtil.isNull(parametros)) {
            getParametros().putAll(parametros);
        }
        if (!ObjectUtil.isNull(usuarioDTO)) {
            getParametros().put(MAP_LOGIN_USUARIO, usuarioDTO.getLogin());
            getParametros().put(MAP_NUM_COOPERATIVA, usuarioDTO.getCooperativa());
        }
        getParametros().put(MAP_SUBREPORT_DIR, Constantes.BRANCO);
        getParametros().put(MAP_APLICATIVO_SISBR, APLICATIVO_SISBR);
        getParametros().put(MAP_CODIGO_RELATORIO, relatorioSicoobDDAEnum.getCodigoRelatorio());
        getParametros().put(MAP_NOME_RELATORIO, relatorioSicoobDDAEnum.getNomeRelatorio());
        verificarFiltroOpcional();
    }

    /**
     * @param relatorioSicoobDDAEnum
     * @param usuarioDTO
     * @param parametros
     */
    public ConfigurarRelatorioJasper(RelatorioSicoobDDAEnum relatorioSicoobDDAEnum, UsuarioBancoobDTO usuarioDTO, Map<String, Object> parametros) {
        this(relatorioSicoobDDAEnum, new JREmptyDataSource(), usuarioDTO, parametros);
    }

    /**
     * Método responsável por adicionar ao relatório alguns parâmetros opcionais
     * 
     * @param @return void @throws
     */
    private void verificarFiltroOpcional() {
        String filtroOpcional = Constantes.BRANCO;
        Object dataMovimentoObjeto = getParametros().get("dataMovimento");
        Object dataInicialObjeto = getParametros().get("dataInicial");
        Object dataFinalObjeto = getParametros().get("dataFinal");
        if (!ObjectUtil.isNull(dataMovimentoObjeto)) {
            filtroOpcional = preparaDataMovimento(dataMovimentoObjeto);
        } else if (!ObjectUtil.isNull(dataInicialObjeto) && !ObjectUtil.isNull(dataFinalObjeto)) {
            filtroOpcional = preparaPeriodoData(dataInicialObjeto, dataFinalObjeto);
        }
        getParametros().put(MAP_DESC_FILTRO_OPCIONAL, filtroOpcional);
    }

    /**
     * Método responsável por
     * 
     * @param dataInicialObjeto
     * @param dataFinalObjeto
     * @return String
     * 
     */
    private String preparaPeriodoData(Object dataInicialObjeto, Object dataFinalObjeto) {
        String filtroOpcional;
        if (dataInicialObjeto instanceof Date && dataFinalObjeto instanceof Date) {
            filtroOpcional = DateUtil.formatarData((Date) dataInicialObjeto, Constantes.FORMATO_DE_DATA_DD_MM_YYYY) + " a " + DateUtil.formatarData((Date) dataFinalObjeto, Constantes.FORMATO_DE_DATA_DD_MM_YYYY);
        } else {
            filtroOpcional = dataInicialObjeto + " a " + dataFinalObjeto;
        }
        return filtroOpcional;
    }

    /**
     * Método responsável por
     * 
     * @param dataMovimentoObjeto
     * @return String
     * 
     */
    private String preparaDataMovimento(Object dataMovimentoObjeto) {
        String filtroOpcional;
        filtroOpcional = "Movimento do dia ";
        if (dataMovimentoObjeto instanceof Date) {
            Date dataMovimento = (Date) dataMovimentoObjeto;
            filtroOpcional = filtroOpcional.concat(DateUtil.formatarData(dataMovimento, Constantes.FORMATO_DE_DATA_DD_MM_YYYY));
        } else {
            filtroOpcional = String.valueOf(dataMovimentoObjeto);
        }
        return filtroOpcional;
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.relatorio.api.jasper.SicoobJasperReports#getDataSource()
     */
    @Override
    protected JRDataSource getDataSource() throws BancoobException {
        return dataSource;
    }

    /**
     * Método responsável por adicionar os parametros do relatório.
     * 
     * @param nome @param objeto @return void @throws
     */
    public void setParametro(String nome, Object objeto) {
        getParametros().put(nome, objeto);
    }

}
