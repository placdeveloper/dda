/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates
 * Arquivo:         BeneficiariosAlertaDelegate.java
 * Data Criação:    Feb 23, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.relatorio.ConfigurarRelatorioJasper;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.BeneficiarioAlertaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.BeneficiariosAlertaFiltroDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.BeneficiariosAlertaServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * BeneficiariosAlertaDelegate é responsável por
 * 
 * @author Danilo.Barros
 */
@SuppressWarnings("rawtypes")
public class BeneficiariosAlertaDelegate extends OperacionalCrudDelegate {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected BeneficiariosAlertaServico localizarServico() {
        return OperacionalServiceLocator.getInstance().localizarBeneficiariosAlertaServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {
        localizarServico().verificarDisponibilidade();
    }

    /**
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.BeneficiariosAlertaServico#obterInstituicaoBeneficiariosAlerta(java.util.List)
     */
    public List<BeneficiarioAlertaDto> obterInstituicaoBeneficiariosAlerta(List<Long> idsBeneficiariosDDA) throws ComumException {
        return localizarServico().obterInstituicaoBeneficiariosAlerta(idsBeneficiariosDDA);
    }

    /**
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.BeneficiariosAlertaServico#obterBeneficiariosAlerta(br.com.sicoob.sisbr.sicoobdda.operacional.dto.BeneficiariosAlertaFiltroDto)
     */
    public List<BeneficiarioAlertaDto> obterBeneficiariosAlerta(BeneficiariosAlertaFiltroDto beneficiariosAlertaFiltroDto) throws ComumException {
        return localizarServico().obterBeneficiariosAlerta(beneficiariosAlertaFiltroDto);
    }

    /**
     * Método responsável por
     * 
     * @param beneficiariosAlertaFiltroDto
     * @param usuarioBancoobDTO
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    public ConfigurarRelatorioJasper configurarRelatorioBenficiariosAlerta(BeneficiariosAlertaFiltroDto beneficiariosAlertaFiltroDto, UsuarioBancoobDTO usuarioBancoobDTO)
            throws BancoobException {
        return localizarServico().configurarRelatorioBenficiariosAlerta(beneficiariosAlertaFiltroDto, usuarioBancoobDTO);
    }

}
