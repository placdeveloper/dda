/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao
 * Arquivo:         BeneficiariosAlertaDao.java
 * Data Criação:    Feb 23, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.BeneficiarioAlertaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.BeneficiariosAlertaFiltroDto;

/**
 * BeneficiariosAlertaDao
 * 
 * @author Danilo.Barros
 */
public interface BeneficiariosAlertaDao extends OperacionalCrudDaoIF<SicoobDDAEntidade> {

    /**
     * Método responsável por obter as Instituições dos Beneficiários DDA
     * 
     * @param idsBeneficiariosDDA
     * @return List<BeneficiarioAlertaDto>
     * @throws ComumException
     */
    public List<BeneficiarioAlertaDto> obterInstituicaoBeneficiariosAlerta(List<Long> idsBeneficiariosDDA) throws ComumException;

    /**
     * Método responsável por obter os Beneficiários DDA
     * 
     * @param beneficiariosAlertaFiltroDto
     * @return List<BeneficiarioAlertaDto>
     * @throws ComumException
     */
    public List<BeneficiarioAlertaDto> obterBeneficiariosAlerta(BeneficiariosAlertaFiltroDto beneficiariosAlertaFiltroDto) throws ComumException;
}
