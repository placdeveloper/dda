/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl
 * Arquivo:         BeneficiariosAlertaDaoImpl.java
 * Data Criação:    Feb 23, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.BeneficiarioAlertaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.BeneficiariosAlertaFiltroDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BeneficiariosAlertaDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDaoDB2;

/**
 * BeneficiariosAlertaDaoImpl
 * 
 * @author Danilo.Barros
 */
public class BeneficiariosAlertaDaoImpl extends OperacionalCrudDaoDB2<SicoobDDAEntidade> implements BeneficiariosAlertaDao {
    private static final String ARQUIVO_QUERIES = "sicoobdda_operacional.queries.xml";
    private static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional";
    private static final String COMANDOS = "PESQUISAR_BENEFICIARIOS_ALERTA";
    private static final String COMANDOS_QTD = "OBTER_QTD_BENEFICIARIOS_ALERTA";
    private static final String OBTER_BENEFICIARIOS = "OBTER_BENEFICIARIOS_ALERTA";
    private Comando comando = null;
    List<BeneficiarioAlertaDto> listaBeneficiarioAlertaDto = null;

    /**
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     * @param nomeComandoPesquisaNumeroRegistros
     */
    public BeneficiariosAlertaDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, COMANDOS, COMANDOS_QTD);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BeneficiariosAlertaDao#obterInstituicaoBeneficiariosAlerta(java.util.List)
     */
    @SuppressWarnings("unchecked")
    public List<BeneficiarioAlertaDto> obterInstituicaoBeneficiariosAlerta(List<Long> idsBeneficiariosDDA) throws ComumException {
        getLogger().debug("### Obtendo as Instituiçoes dos Beneficiários em Alerta");
        try {
            comando = getComando("OBTER_INSTITUICAO_BENEFICIARIOS_ALERTA");
            comando.adicionarVariavel("idsBeneficiariosDDA", idsBeneficiariosDDA);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            Query query = comando.criarQuery(getEntityManager());
            listaBeneficiarioAlertaDto = query.getResultList();
        } catch (PersistenceException e) {
            throw new ComumException(e);
        } finally {
            fecharComando(comando);
        }
        return listaBeneficiarioAlertaDto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BeneficiariosAlertaDao#obterBeneficiariosAlerta(br.com.sicoob.sisbr.sicoobdda.operacional.dto.BeneficiariosAlertaFiltroDto)
     */
    @SuppressWarnings("unchecked")
    public List<BeneficiarioAlertaDto> obterBeneficiariosAlerta(BeneficiariosAlertaFiltroDto beneficiariosAlertaFiltroDto) throws ComumException {
        getLogger().debug("### Obtendo os Beneficiários em Alerta");
        try {
            comando = getComando(OBTER_BENEFICIARIOS);
            montarCommando(beneficiariosAlertaFiltroDto);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            Query query = comando.criarQuery(getEntityManager());
            listaBeneficiarioAlertaDto = query.getResultList();
        } catch (PersistenceException e) {
            throw new ComumException(e);
        } catch (IllegalStateException e) {
            throw new ComumException(e);
        } finally {
            fecharComando(comando);
        }
        return listaBeneficiarioAlertaDto;
    }

    private void montarCommando(BeneficiariosAlertaFiltroDto beneficiariosAlertaFiltroDto) {
        if (!ObjectUtil.isNull(beneficiariosAlertaFiltroDto.getBancoCaf())) {
            comando.adicionarVariavel(BeneficiariosAlertaFiltroDto.PARAM_COD_ISPB, beneficiariosAlertaFiltroDto.getBancoCaf().getNumCodISPB());
        }
        if (!ObjectUtil.isNull(beneficiariosAlertaFiltroDto.getCodTipoPessoa())) {
            comando.adicionarVariavel(BeneficiariosAlertaFiltroDto.PARAM_TIPO_PESSOA, beneficiariosAlertaFiltroDto.getCodTipoPessoa());
        }
        if (!ObjectUtil.isNull(beneficiariosAlertaFiltroDto.getCodStatusAlerta())) {
            comando.adicionarVariavel(BeneficiariosAlertaFiltroDto.PARAM_STATUS_ALERTA, beneficiariosAlertaFiltroDto.getCodStatusAlerta());
        }
        if (!ObjectUtil.isNull(beneficiariosAlertaFiltroDto.getcPF_CNPJ())) {
            comando.adicionarVariavel(BeneficiariosAlertaFiltroDto.PARAM_NUM_CPFCNPJ, beneficiariosAlertaFiltroDto.getcPF_CNPJ());
        }
        if (beneficiariosAlertaFiltroDto.getIsBeneficiariosSicoob()) {
            comando.adicionarVariavel(BeneficiariosAlertaFiltroDto.PARAM_BENEFICIARIOS_SICOOB, Boolean.TRUE);
        }
    }

}
