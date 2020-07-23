/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb
 * Arquivo:         BeneficiariosAlertaServicoEJB.java
 * Data Criação:    Feb 23, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.adm.BancoCafDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.RelatorioSicoobDDAEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.relatorio.ConfigurarRelatorioJasper;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.FormatadorUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.ADMDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SCIDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.BeneficiarioAlertaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.BeneficiariosAlertaFiltroDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.BeneficiariosAlertaServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BeneficiariosAlertaDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.operacional.util.ComparadorBeneficiarioAlerta;

/**
 * BeneficiariosAlertaServicoEJB
 * 
 * @author Danilo.Barros
 */
@Stateless
@Local(BeneficiariosAlertaServicoLocal.class)
public class BeneficiariosAlertaServicoEJB extends OperacionalCrudServicoEJB<SicoobDDAEntidade> implements BeneficiariosAlertaServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager entityManager;

    @Dao(entityManager = "entityManager", fabrica = OperacionalDaoFactory.class)
    private BeneficiariosAlertaDao beneficiariosAlertaDao;

    @Override
    protected BeneficiariosAlertaDao getDAO() {
        return beneficiariosAlertaDao;
    }

    private SCIDelegate sciDelegate;
    private ADMDelegate admDelegate;

    private static final String PARAM_SIGLA_COOPERATIVA = "siglaCooperativa";

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.BeneficiariosAlertaServico#obterInstituicaoBeneficiariosAlerta(java.util.List)
     */
    @Override
    public List<BeneficiarioAlertaDto> obterInstituicaoBeneficiariosAlerta(List<Long> idsBeneficiariosDDA) throws ComumException {
        getLogger().debug("### Obtendo as Instituiçoes dos Beneficiários em Alerta");
        try {
            return beneficiariosAlertaDao.obterInstituicaoBeneficiariosAlerta(idsBeneficiariosDDA);
        } catch (ComumException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException("beneficiarios.alerta.erro.obter.instituicao", e);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.BeneficiariosAlertaServico#obterBeneficiariosAlerta(br.com.sicoob.sisbr.sicoobdda.operacional.dto.BeneficiariosAlertaFiltroDto)
     */
    @Override
    public List<BeneficiarioAlertaDto> obterBeneficiariosAlerta(BeneficiariosAlertaFiltroDto beneficiariosAlertaFiltroDto) throws ComumException {
        getLogger().debug("### Obtendo os Beneficiários em Alerta");
        try {
            return beneficiariosAlertaDao.obterBeneficiariosAlerta(beneficiariosAlertaFiltroDto);
        } catch (ComumException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException("beneficiarios.alerta.erro.obter.beneficiarios", e);
        }
    }

    /**
     * Método responsável por remover caracteres especiais
     * 
     * @param beneficiariosAlertaFiltroDto
     * 
     * @param @return @throws
     */
    private void removerFormatacaoCPFCNPJ(BeneficiariosAlertaFiltroDto beneficiariosAlertaFiltroDto) {
        if (!ObjectUtil.isNull(beneficiariosAlertaFiltroDto) && !ObjectUtil.isEmpty(beneficiariosAlertaFiltroDto.getcPF_CNPJ())) {
            beneficiariosAlertaFiltroDto.setcPF_CNPJ(FormatadorUtil.removerFormatacao(FormatadorUtil.PATTERN_SOMENTE_NUMEROS, beneficiariosAlertaFiltroDto.getcPF_CNPJ()));
        }
    }

    /**
     * Método responsável por
     * 
     * @param listaBeneficiarioAlertaDto
     * @throws BancoobException void
     * 
     */
    private void formatarCpfCnpj(List<BeneficiarioAlertaDto> listaBeneficiarioAlertaDto) throws BancoobException {
        for (BeneficiarioAlertaDto beneficiarioAlertaDto : listaBeneficiarioAlertaDto) {
            if (!ObjectUtil.isEmpty(beneficiarioAlertaDto.getcPF_CNPJ())) {
                beneficiarioAlertaDto.setcPF_CNPJ(FormatadorUtil.formatar(beneficiarioAlertaDto.getcPF_CNPJ(),
                        TipoPessoaEnum.PF.getCodDominioCip().equalsIgnoreCase(beneficiarioAlertaDto.getDescTipoPessoa()) ? FormatadorUtil.MASCARA_CPF
                                : FormatadorUtil.MASCARA_CNPJ));
            }
        }
    }

    /**
     * @param configuraRelatorio
     * @param beneficiariosAlertaFiltroDto
     * @throws ComumException void
     * 
     */
    private Map<String, Object> getParametrosBeneficiariosAlerta(BeneficiariosAlertaFiltroDto beneficiariosAlertaFiltroDto, Integer idInstituicao) throws ComumException {
        Map<String, Object> parametros = null;

        if (!ObjectUtil.isNull(beneficiariosAlertaFiltroDto)) {
            parametros = new HashMap<>();

            parametros.put(PARAM_SIGLA_COOPERATIVA, getSiglaInstituicao(idInstituicao));
            parametros.put(BeneficiariosAlertaFiltroDto.PARAM_BANCO_ORIGINADOR,
                    !ObjectUtil.isNull(beneficiariosAlertaFiltroDto.getBancoCaf()) ? beneficiariosAlertaFiltroDto.getBancoCaf().getDescBanco() : null);
            parametros.put(BeneficiariosAlertaFiltroDto.PARAM_TIPO_PESSOA, beneficiariosAlertaFiltroDto.getCodTipoPessoa());
            parametros.put(BeneficiariosAlertaFiltroDto.PARAM_STATUS_ALERTA, beneficiariosAlertaFiltroDto.getCodStatusAlerta());
            parametros.put(BeneficiariosAlertaFiltroDto.PARAM_NUM_CPFCNPJ, beneficiariosAlertaFiltroDto.getcPF_CNPJ());
        }

        return parametros;
    }

    /**
     * @return
     * @throws ComumException String
     * 
     */
    private String getSiglaInstituicao(Integer idInstituicao) throws ComumException {
        InstituicaoDto instituicaoDto = getSCIDelegate().obterInstituicaoCache(idInstituicao);
        return ObjectUtil.isNull(instituicaoDto) ? instituicaoDto.getSiglaInstituicao() : "";
    }

    /**
     * Método responsável por obter as Instituições dos Beneficiários DDA
     * 
     * @param listaBeneficiarioAlertaDto
     * 
     * @param listaBeneficiariosAlertaDTO
     * @return
     * @throws BancoobException
     */
    private void obterDadosBeneficiariosAlerta(List<BeneficiarioAlertaDto> listaBeneficiarioAlertaDto) throws ComumException {
        List<Long> idsBeneficiariosDDA = new ArrayList<Long>();
        List<BeneficiarioAlertaDto> listaDadosInstituicaoBeneficiario = new ArrayList<BeneficiarioAlertaDto>();
        List<BeneficiarioAlertaDto> listaBeneficiarioSicoob = new ArrayList<BeneficiarioAlertaDto>();

        for (BeneficiarioAlertaDto beneficiarioAlertaDTO : listaBeneficiarioAlertaDto) {
            if (beneficiarioAlertaDTO.isBolSicoob()) {
                idsBeneficiariosDDA.add(beneficiarioAlertaDTO.getIdBeneficiarioDDA());
                listaBeneficiarioSicoob.add(beneficiarioAlertaDTO);
            }
        }

        if (!ObjectUtil.isEmpty(idsBeneficiariosDDA)) {
            listaDadosInstituicaoBeneficiario = obterInstituicaoBeneficiariosAlerta(idsBeneficiariosDDA);
        }

        for (BeneficiarioAlertaDto beneficiarioAlertaDTO : listaBeneficiarioSicoob) {
            for (BeneficiarioAlertaDto beneficiarioAlertaDto : listaDadosInstituicaoBeneficiario) {
                if (beneficiarioAlertaDTO.getIdInstituicao().equals(beneficiarioAlertaDto.getIdInstituicao())) {
                    beneficiarioAlertaDTO.setNomeCooperativa(beneficiarioAlertaDto.getNomeCooperativa());
                    if (Constantes.SIGLA_INST_BANCOOB.equalsIgnoreCase(beneficiarioAlertaDTO.getNomeCooperativa())) {
                        beneficiarioAlertaDTO.setNumeroCooperativa(Constantes.NUMERO_INST_BANCOOB);
                    } else {
                        beneficiarioAlertaDTO.setNumeroCooperativa(beneficiarioAlertaDto.getNumeroCooperativa());
                    }
                    beneficiarioAlertaDTO.setPostoAvancCooperativa(beneficiarioAlertaDto.getPostoAvancCooperativa());
                }
            }
        }

        Collections.sort(listaBeneficiarioAlertaDto, new ComparadorBeneficiarioAlerta());
    }

    /**
     * Método responsável por
     * 
     * @param listaBeneficiarioAlertaDto
     * @throws ComumException void
     * 
     */
    private void recuperarNomeBancoOriginador(List<BeneficiarioAlertaDto> listaBeneficiarioAlertaDto) throws ComumException {
        List<BancoCafDto> listaBancosCafDto = getADMDelegate().listarBancosCafDtoComAgenciasAtivas(Boolean.TRUE);
        for (BeneficiarioAlertaDto beneficiarioAlertaDTO : listaBeneficiarioAlertaDto) {
            for (BancoCafDto bancoCafDto : listaBancosCafDto) {
                if (beneficiarioAlertaDTO.getNumCodISPB().equalsIgnoreCase(bancoCafDto.getNumCodISPB())) {
                    beneficiarioAlertaDTO.setNomeBancoConveniado(bancoCafDto.getDescBanco());
                    break;
                }
            }
        }
    }

    /**
     * @return o atributo sciDelegate
     */
    private SCIDelegate getSCIDelegate() {
        if (sciDelegate == null) {
            sciDelegate = IntegracaoInternaFabricaDelegates.getInstance().getSCIDelegate();
        }
        return sciDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return ADMDelegate
     * 
     */
    private ADMDelegate getADMDelegate() {
        if (admDelegate == null) {
            admDelegate = IntegracaoInternaFabricaDelegates.getInstance().getADMDelegate();
        }
        return admDelegate;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.BeneficiariosAlertaServico#configurarRelatorioBenficiariosAlerta(br.com.sicoob.sisbr.sicoobdda.operacional.dto.BeneficiariosAlertaFiltroDto,
     *      br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO)
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ConfigurarRelatorioJasper configurarRelatorioBenficiariosAlerta(BeneficiariosAlertaFiltroDto beneficiariosAlertaFiltroDto, UsuarioBancoobDTO usuario)
            throws BancoobException {

        removerFormatacaoCPFCNPJ(beneficiariosAlertaFiltroDto);
        List<BeneficiarioAlertaDto> listaBeneficiarioAlertaDto = obterBeneficiariosAlerta(beneficiariosAlertaFiltroDto);

        if (ObjectUtil.isEmpty(listaBeneficiarioAlertaDto)) {
            throw new NegocioException("Não há dados para exibir o relatório");
        }

        formatarCpfCnpj(listaBeneficiarioAlertaDto);
        recuperarNomeBancoOriginador(listaBeneficiarioAlertaDto);
        obterDadosBeneficiariosAlerta(listaBeneficiarioAlertaDto);
        Integer idInstituicao = Integer.parseInt(usuario.getIdInstituicao());
        return new ConfigurarRelatorioJasper(RelatorioSicoobDDAEnum.SDDA504, listaBeneficiarioAlertaDto, usuario,
                getParametrosBeneficiariosAlerta(beneficiariosAlertaFiltroDto, idInstituicao));

    }

}
