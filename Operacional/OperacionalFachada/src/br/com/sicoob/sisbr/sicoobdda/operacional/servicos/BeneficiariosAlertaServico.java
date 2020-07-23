/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.servicos
 * Arquivo:         BeneficiariosAlertaServico.java
 * Data Criação:    Feb 17, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.servicos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.PesquisaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.BancoCafDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.BeneficiarioAlertaDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.BeneficiariosAlertaFiltroDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor.ConversorVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.FormatadorUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.BeneficiarioAlertaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.fachada.OperacionalFachada;
import br.com.sicoob.sisbr.sicoobdda.operacional.iface.IBeneficiariosAlerta;
import br.com.sicoob.sisbr.sicoobdda.operacional.util.ComparadorBeneficiarioInstituicao;

/**
 * BeneficiariosAlertaServico
 * 
 * @author Danilo.Barros
 */
@RemoteService
public class BeneficiariosAlertaServico extends OperacionalFachada implements IBeneficiariosAlerta {
    private static final String PESQUISA_BENEFICIARIOS_FILTRO = "pesquisaBeneficiariosFiltro";
    private List<BeneficiarioAlertaDTO> listaBeneficiariosAlertaDTO;
    private BeneficiariosAlertaFiltroDTO beneficiariosAlertaFiltroDTO;
    

    /**
     * 
     */
    public BeneficiariosAlertaServico() {
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IBeneficiariosAlerta#listarBancosOriginadores()
     */
    @Override
    @SuppressWarnings("unchecked")
    public RetornoDTO listarBancosOriginadores() throws ComumException {
        RetornoDTO retornoDTO = new RetornoDTO();
        List<BancoCafDTO> listaBancosCafDto = (List<BancoCafDTO>) ConversorVO.getInstance().converter(
                IntegracaoInternaFabricaDelegates.getInstance().getADMDelegate().listarBancosCafDtoComAgenciasAtivas(Boolean.TRUE));
        retornoDTO.getDados().put("listaBancosOriginadores", listaBancosCafDto);
        return retornoDTO;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.iface.IBeneficiariosAlerta#pesquisarBeneficiariosAlertaPaginada(br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO)
     */
    @Override
    @SuppressWarnings("unchecked")
    public DadosSelGeralDTO pesquisarBeneficiariosAlertaPaginada(SelGeralReqDTO geralReqDTO) throws ComumException, ComumNegocioException {
        ConsultaDto<BeneficiariosAlertaFiltroDTO> consultaFiltro = montarConsultaDto(geralReqDTO, BeneficiariosAlertaFiltroDTO.class);
        RequisicaoReqDTO reqDTO = (RequisicaoReqDTO) consultaFiltro.getFiltro();
        listaBeneficiariosAlertaDTO = new ArrayList<BeneficiarioAlertaDTO>();

        if (!ObjectUtil.isNull(reqDTO)) {
            consultaFiltro.setFiltro(reqDTO.getDados().get(PESQUISA_BENEFICIARIOS_FILTRO));
        }

        beneficiariosAlertaFiltroDTO = (BeneficiariosAlertaFiltroDTO) consultaFiltro.getFiltro();

        removerFormatacaoCPF_CNPJ();

        ConsultaDto<BeneficiarioAlertaDTO> consultaDto = getBeneficiariosAlertaDelegate().pesquisar(BeneficiariosAlertaFiltroDTO.class, consultaFiltro,
                PesquisaEnum.PESQUISAR_BENEFICIARIOS_ALERTA);

        listaBeneficiariosAlertaDTO = (List<BeneficiarioAlertaDTO>) ConversorVO.getInstance().converter(consultaDto.getResultado());

        recuperarNomeBancoOriginador();
        obterInstituicaoBeneficiariosAlerta();

        consultaDto.setResultado(listaBeneficiariosAlertaDTO);

        if (ObjectUtil.isNull(consultaDto.getResultado()) || ObjectUtil.isEmpty(consultaDto.getResultado())) {
            throw new ComumNegocioException("Nenhum item encontrado");
        }

        return montarResultado(consultaDto);
    }

    /**
     * Método responsável por obter o nome do Banco Originador
     * 
     * @param listaBeneficiariosAlertaDto
     * @return
     * @throws ComumException
     */
    private void recuperarNomeBancoOriginador() throws ComumException {
        @SuppressWarnings("unchecked")
        List<BancoCafDTO> listaBancosCafDTO = (List<BancoCafDTO>) ConversorVO.getInstance().converter(
                IntegracaoInternaFabricaDelegates.getInstance().getADMDelegate().listarBancosCafDtoComAgenciasAtivas(Boolean.TRUE));
        for (BeneficiarioAlertaDTO beneficiarioAlertaDTO : listaBeneficiariosAlertaDTO) {
            for (BancoCafDTO bancoCafDTO : listaBancosCafDTO) {
                if (beneficiarioAlertaDTO.getNumCodISPB().equalsIgnoreCase(bancoCafDTO.getNumCodISPB())) {
                    beneficiarioAlertaDTO.setNomeBancoConveniado(bancoCafDTO.getDescBanco());
                    break;
                }
            }
        }
    }

    /**
     * Método responsável por obter as Instituições dos Beneficiários DDA
     * 
     * @param listaBeneficiariosAlertaDTO
     * @return
     * @throws BancoobException
     */
    private void obterInstituicaoBeneficiariosAlerta() throws ComumException {
        List<Long> idsBeneficiariosDDA = new ArrayList<Long>();
        List<BeneficiarioAlertaDto> listaDadosInstituicaoBeneficiario = new ArrayList<BeneficiarioAlertaDto>();
        List<BeneficiarioAlertaDTO> listaBeneficiarioSicoob = new ArrayList<BeneficiarioAlertaDTO>();

        for (BeneficiarioAlertaDTO beneficiarioAlertaDTO : listaBeneficiariosAlertaDTO) {
            if (beneficiarioAlertaDTO.isBolSicoob()) {
                idsBeneficiariosDDA.add(beneficiarioAlertaDTO.getIdBeneficiarioDDA());
                listaBeneficiarioSicoob.add(beneficiarioAlertaDTO);
            }
        }

        if (!ObjectUtil.isEmpty(idsBeneficiariosDDA)) {
            listaDadosInstituicaoBeneficiario = getBeneficiariosAlertaDelegate().obterInstituicaoBeneficiariosAlerta(idsBeneficiariosDDA);
        }

        for (BeneficiarioAlertaDTO beneficiarioAlertaDTO : listaBeneficiarioSicoob) {
            for (BeneficiarioAlertaDto beneficiarioAlertaDto : listaDadosInstituicaoBeneficiario) {
                if (beneficiarioAlertaDTO.getIdInstituicao().equals(beneficiarioAlertaDto.getIdInstituicao())) {
                    beneficiarioAlertaDTO.setNomeCooperativa(beneficiarioAlertaDto.getNomeCooperativa());

                    if (Constantes.SIGLA_INST_BANCOOB.equalsIgnoreCase(beneficiarioAlertaDto.getNomeCooperativa())) {
                        beneficiarioAlertaDTO.setNumeroCooperativa(Constantes.NUMERO_INST_BANCOOB);
                    } else {
                        beneficiarioAlertaDTO.setNumeroCooperativa(beneficiarioAlertaDto.getNumeroCooperativa());
                    }

                    beneficiarioAlertaDTO.setPostoAvancCooperativa(beneficiarioAlertaDto.getPostoAvancCooperativa());
                }
            }
        }

        Collections.sort(listaBeneficiariosAlertaDTO, new ComparadorBeneficiarioInstituicao());
    }

    /**
     * Método responsável por remover caracteres especiais
     * 
     * @param
     * @return
     * @throws ComumException
     */
    private void removerFormatacaoCPF_CNPJ() throws ComumException {
        if (!ObjectUtil.isNull(beneficiariosAlertaFiltroDTO) && !ObjectUtil.isEmpty(beneficiariosAlertaFiltroDTO.getcPF_CNPJ())) {
            beneficiariosAlertaFiltroDTO.setcPF_CNPJ(FormatadorUtil.removerFormatacao(FormatadorUtil.PATTERN_SOMENTE_NUMEROS, beneficiariosAlertaFiltroDTO.getcPF_CNPJ()));
        }
    }

    /**
     * Destinado a teste de Ambiente de TI
     * 
     * @param geralReqDTO
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    public DadosSelGeralDTO pesquisarTesteBPaginada(SelGeralReqDTO geralReqDTO) throws ComumException, ComumNegocioException {

        ConsultaDto<BeneficiarioAlertaDTO> consultaDto = new ConsultaDto<BeneficiarioAlertaDTO>();

        return montarResultado(consultaDto);
    }

}
