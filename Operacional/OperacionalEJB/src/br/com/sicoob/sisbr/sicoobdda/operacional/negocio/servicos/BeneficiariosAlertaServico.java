package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.relatorio.ConfigurarRelatorioJasper;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.BeneficiarioAlertaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.BeneficiariosAlertaFiltroDto;

/**
 * BeneficiariosAlertaServico
 * 
 * @author Danilo.Barros
 */

public interface BeneficiariosAlertaServico extends OperacionalCrudServico<SicoobDDAEntidade> {

    /**
     * Método responsável por obter as Instituições dos Beneficiários DDA
     * 
     * @param idsBeneficiariosDDA
     * @return List<BeneficiarioAlertaDto>
     * @throws ComumException
     */
    List<BeneficiarioAlertaDto> obterInstituicaoBeneficiariosAlerta(List<Long> idsBeneficiariosDDA) throws ComumException;

    /**
     * Método responsável por obter os Beneficiários DDA
     * 
     * @param idsBeneficiariosDDA
     * @return List<BeneficiarioAlertaDto>
     * @throws ComumException
     */
    List<BeneficiarioAlertaDto> obterBeneficiariosAlerta(BeneficiariosAlertaFiltroDto beneficiariosAlertaFiltroDto) throws ComumException;
    
    /**
     * Método responsável por
     * 
     * @param beneficiariosAlertaFiltroDto
     * @param usuarioBancoobDTO
     * @return
     * @throws BancoobException ConfigurarRelatorioJasper
     * 
     */
    ConfigurarRelatorioJasper configurarRelatorioBenficiariosAlerta(BeneficiariosAlertaFiltroDto beneficiariosAlertaFiltroDto, UsuarioBancoobDTO usuarioBancoobDTO)
            throws BancoobException;
}
