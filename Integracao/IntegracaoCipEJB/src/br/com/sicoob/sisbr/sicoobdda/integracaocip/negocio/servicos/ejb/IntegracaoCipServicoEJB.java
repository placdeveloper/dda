package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.persistence.EntityManager;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.CapesDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ArquivoUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAConsultaBoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoBoletoConsultado;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.IntegracaoFilaSSPBEnvioDDAServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.EscritorXMLArquivoUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.LeitorXmlUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.ZipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SSPBDelegate;

/**
 * Implementacao base de todos os servicos do sistema IntegracaoCip
 * 
 * @author Sicoob
 */
public abstract class IntegracaoCipServicoEJB extends ComumServicoEJB implements IntegracaoCipServico {

    private static final String ERRO_DESCOMPACTAR_XML_RECEBIDO = "integracaocip.erro.descompactar.xml.recebido";
    private static final String ERRO_RECUPERAR_BYTES_ARQUIVO = "integracaocip.erro.recuperar.bytes.arquivo";
    private static final String ERRO_COMPACTAR_XML_ENVIO = "integracaocip.erro.compactar.xml.envio";
    private static final String ERRO_SALVAR_ARQUIVO_DIRETORIO = "integracaocip.erro.salvar.arquivo.diretorio";
    private static final String ERRO_ENVIAR_MENSAGEM_SSPB = "integracaocip.erro.enviar.mensagem.sspb";
    private static final String ERRO_ENVIAR_MENSAGEM_FILA_RECEBIMENTO_SSPB = "integracaocip.erro.enviar.mensagem.fila.recebimento.sspb";

    protected static final String ERRO_BENEFICARIO_NAO_CADASTRADO_CIP = "integracaocip.erro.beneficario.nao.cadastrado.cip";
    protected static final String ERRO_CNPJ_CPF_REPRESENTANTE_INVALIDO = "integracaocip.erro.cnpj.cpf.representante.invalido";
    protected static final String ERRO_TIPO_PESSOA_REPRESENTANTE_INVALIDO = "integracaocip.erro.tipo.pessoa.representante.invalido";
    protected static final String ERRO_TP_PRODUTO_CONVENIO_INVALIDA = "integracaocip.erro.tp.produto.convenio.invalida";
    protected static final String ERRO_AGENCIA_DESTINO_CONVENIO_INVALIDA = "integracaocip.erro.agencia.destino.convenio.invalida";
    protected static final String ERRO_TP_AGENCIA_DESTINO_CONVENIO_INVALIDA = "integracaocip.erro.tp.agencia.destino.convenio.invalida";
    protected static final String ERRO_DATA_INICIO_RELACIONAMENTO_CONVENIO_INVALIDA = "integracaocip.erro.data.inicio.relacionamento.convenio.invalida";
    protected static final String ERRO_NOME_RAZAO_SOCIAL_BENEFICIARIO_INVALIDO = "integracaocip.erro.nome.razao.social.beneficiario.invalido";
    protected static final String ERRO_NOME_FANTASIA_BENEFICIARIO_OBRIGATORIO_PJ = "integracaocip.erro.nome.fantasia.beneficiario.obrigatorio.pj";
    protected static final String ERRO_CNPJ_CPF_BENEFICIARIO_INVALIDO = "integracaocip.erro.cnpj.cpf.beneficiario.invalido";
    protected static final String ERRO_TIPO_PESSOA_INVALIDO = "integracaocip.erro.tipo.pessoa.invalido";
    protected static final String ERRO_INSTITUICAO_SCI_INVALIDA = "integracaocip.erro.instituicao.sci.invalida";
    protected static final String ERRO_CARGA_EM_EXECUCAO = "integracaocip.erro.carga.em.execucao";

    /**
     * @return EntityManager
     * 
     */
    protected abstract EntityManager getEm();

    @EJB
    private IntegracaoFilaSSPBEnvioDDAServicoLocal integracaoFilaSSPBEnvioDDAServico;

    private SSPBDelegate sspbDelegate;
    private CapesDelegate capesDelegate;

    private MensagemDDADelegate mensagemDDADelegate = IntegracaoCipFabricaDelegates.getInstance().getMensagemDDADelegate();

    /**
     * Método responsável por recuperar o diretorio.
     * 
     * @param idParametroDiretorio
     * @return String
     * 
     */
    protected String getDiretorio(Long idParametroDiretorio) {
        return getEm().find(ParametroDDA.class, idParametroDiretorio).getValorParametro();
    }

    /**
     * Método responsável por recuperar o xml de envio e postar na fila SSPB de envio a CIP.
     * 
     * @param objetoEnvio
     * @param numSeqMsg
     * @throws ComumException void
     * 
     */
    public void postarMensagemFilaSSPBDDA(String xmlEnvio) throws ComumException {
        try {
            getIntegracaoFilaSSPBEnvioDDAServico().enviar(xmlEnvio);
        } catch (ComumException e) {
            throw new ComumException(ERRO_ENVIAR_MENSAGEM_SSPB, e);
        }
    }

    /**
     * Método responsável por recuperar o xml de envio e postar na fila de recebimento SSPB.
     * 
     * @param xmlEnvio
     * @throws ComumException void
     * 
     */
    public void postarMensagemFilaRecebimentoSSPBDDA(String xmlEnvio) throws ComumException {
        try {
            getIntegracaoFilaSSPBEnvioDDAServico().enviarFilaRecebimento(xmlEnvio);
        } catch (ComumException e) {
            throw new ComumException(ERRO_ENVIAR_MENSAGEM_FILA_RECEBIMENTO_SSPB, e);
        }
    }

    /**
     * Método responsável por criar e persistir uma mensagem.
     * 
     * @param complexType
     * @param numPrioridadeEnvio
     * @throws ComumException
     */
    protected void criarMensagemEnvioDDA(ComplexType complexType, Integer numPrioridadeEnvio, String usuarioSolicitante, Integer idInstitiucaoSolicitante, Short idCanal)
            throws ComumException {
        this.mensagemDDADelegate.incluir(complexType, new DateTimeDB(), numPrioridadeEnvio, usuarioSolicitante, idInstitiucaoSolicitante, idCanal);
    }

    /**
     * Método responsável por preparar o arquivos de postagem no diretorio de envio para a CIP. A preparação consistem em converter em xml, zipar, criptografar
     * e postar no diretorio.
     * 
     * @param addaDOC
     * @param nomeArquivoPastaAntesEnvio
     * @param string
     * @throws ComumException void
     * 
     */
    public void postarArquivoDiretorio(Long idParametroDiretorio, LogEnvioArquivoDDA logEnvioArquivoDDA, String nomeArquivoPastaAntesEnvio, String enconding)
            throws ComumException {
        getLogger().debug("########## Iniciando preparação do arquivo...");

        // Obtendo o Arquivo da pasta de PRE_ENVIAR
        File fileOrigem = new File(nomeArquivoPastaAntesEnvio);
        if (fileOrigem.exists()) {
            // Descompactando o Arquivo e gerando um Byte para ser criptogrado
            byte[] xmlEnvio = obterObjectArquivo(fileOrigem);

            // Compactar o arquivo
            getLogger().debug("########## Iniciando compactação do arquivo...");
            byte[] bytesXmlEnvio = compactarArquivo(xmlEnvio, enconding);
            getLogger().debug("########## Fim da compactação do arquivo...");

            // Assinar/Criptografar (SSPB)
            getLogger().debug("########## Iniciando a criptografia no SSPB...");
            bytesXmlEnvio = getSspbDelegate().criptografar(bytesXmlEnvio);
            getLogger().debug("########## XML Criptografado com sucesso.");

            // Postar no diretorio
            salvarArquivo(idParametroDiretorio, logEnvioArquivoDDA, bytesXmlEnvio);
        }
    }


    /**
     * Método responsável por gravar o arquivo aberto na pasta X
     * 
     * @param sisARQComplexType
     * @param idParametroDiretorio
     * @param nomeArquivo
     * @param idLogEnvioArquivoDDA
     * @param logEnvioArquivoDDA
     * @param codTipoMensagem
     * @return
     * @throws ComumException String
     * 
     */
    public String gravarArquivoAbertoDiretorio(Object sisARQComplexType, Long idParametroDiretorio, LogEnvioArquivoDDA logEnvioArquivoDDA) throws ComumException {
        getLogger().debug("########## Iniciando preparação do arquivo...");

        // Postar no diretorio
        String destinoArquivo = null;
        try {
            destinoArquivo = getDiretorio(idParametroDiretorio) + File.separator + logEnvioArquivoDDA.getDescNomeArquivoEnviado();
            EscritorXMLArquivoUtil.salvarArquivoAberto(sisARQComplexType, logEnvioArquivoDDA, destinoArquivo);
            getLogger().debug("########## XML salvo no diretorio --> " + destinoArquivo + ".");
        } catch (IOException e) {
            getLogger().erro(e, MensagemUtil.getString(ERRO_SALVAR_ARQUIVO_DIRETORIO, logEnvioArquivoDDA.getDescNomeArquivoEnviado(), destinoArquivo));
            throw new ComumException(MensagemUtil.getString(ERRO_SALVAR_ARQUIVO_DIRETORIO, logEnvioArquivoDDA.getDescNomeArquivoEnviado(), destinoArquivo), e);
        }

        return destinoArquivo;
    }

    /**
     * Método responsável por recuperar arquvo com @param nomeArquivo e realizar a descriptografia, descompactacao e unmarshal do objeto.
     * 
     * @param clazz
     * @param nomeArquivo
     * @return
     * @throws ComumException Object
     * 
     */
    public Object obterObjectArquivoRecebido(Class<?> clazz, File file) throws ComumException {
        Object retorno = null;
        String strXml = null;
        byte[] bytesArquivo = null;
        try {
            bytesArquivo = ArquivoUtil.getBytesFromFileSemLimite(file);
            getLogger().debug("########## Arquivo recuperado no diretorio --> " + file.getAbsolutePath() + ".");
        } catch (ComumException e) {
            getLogger().erro(e, ERRO_RECUPERAR_BYTES_ARQUIVO + file.getName());
            throw new ComumException(ERRO_RECUPERAR_BYTES_ARQUIVO + file.getName(), e);
        }

        bytesArquivo = getSspbDelegate().decriptar(bytesArquivo);
        getLogger().debug("########## XML desCriptografado.");

        try {
            strXml = ZipUtil.extrairArquivo(bytesArquivo);
            getLogger().debug("########## XML desCriptografado e desCompactado.");
        } catch (IOException e) {
            getLogger().erro(e, ERRO_DESCOMPACTAR_XML_RECEBIDO);
            throw new ComumException(ERRO_DESCOMPACTAR_XML_RECEBIDO, e);
        }

        retorno = LeitorXmlUtil.desempacotarArquivo(clazz, strXml);

        return retorno;
    }

    /**
     * Método responsável por recuperar arquvo com @param nomeArquivo.
     * 
     * @param clazz
     * @param nomeArquivo
     * @return
     * @throws ComumException Object
     * 
     */
    public byte[] obterObjectArquivo(File file) throws ComumException {
        byte[] bytesArquivo = null;
        try {
            bytesArquivo = ArquivoUtil.getBytesFromFileSemLimite(file);
            getLogger().debug("########## Arquivo recuperado no diretorio --> " + file.getAbsolutePath() + ".");
        } catch (ComumException e) {
            getLogger().erro(e, ERRO_RECUPERAR_BYTES_ARQUIVO + file.getName());
            throw new ComumException(ERRO_RECUPERAR_BYTES_ARQUIVO + file.getName(), e);
        }

        return bytesArquivo;
    }

    /**
     * Método responsável por verificar se o beneficiário é válido para alteração.
     * 
     * @param beneficiario
     * @throws ComumNegocioException void
     * 
     */
    protected void validarBeneficiarioCadastrado(BeneficiarioDDA beneficiario) throws ComumNegocioException {
        if (ObjectUtil.isNull(beneficiario) || ObjectUtil.isEmpty(beneficiario.getNumIdentBeneficiario())) {
            throw new ComumNegocioException(ERRO_BENEFICARIO_NAO_CADASTRADO_CIP);
        }
    }

    /**
     * Método responsável por
     * 
     * @return Boolean
     * 
     */
    protected Boolean verificaReplicacaoLegadoAutorizada() {
        ParametroDDA parametro = getEm().find(ParametroDDA.class, ParametroDDA.REPLICAR_BENEFICIARIO_LEGADO);
        return parametro.getValorParametro().equals(Constantes.STRING_NUMERO_1);
    }

    /**
     * @return CAPESDelegate
     */
    protected CapesDelegate getCapesDelegate() {
        if (ObjectUtil.isNull(capesDelegate)) {
            this.capesDelegate = ComumFabricaDelegates.getInstance().getCapesDelegate();
        }
        return this.capesDelegate;
    }

    /**
     * @return the integracaoFilaSSPBEnvioDDAServico
     */
    public IntegracaoFilaSSPBEnvioDDAServicoLocal getIntegracaoFilaSSPBEnvioDDAServico() {
        return integracaoFilaSSPBEnvioDDAServico;
    }

    /**
     * @param integracaoFilaSSPBEnvioDDAServico the integracaoFilaSSPBEnvioDDAServico to set
     */
    public void setIntegracaoFilaSSPBEnvioDDAServico(IntegracaoFilaSSPBEnvioDDAServicoLocal integracaoFilaSSPBEnvioDDAServico) {
        this.integracaoFilaSSPBEnvioDDAServico = integracaoFilaSSPBEnvioDDAServico;
    }

    /**
     * @return o atributo sspbDelegate
     */
    public SSPBDelegate getSspbDelegate() {
        if (ObjectUtil.isNull(sspbDelegate)) {
            sspbDelegate = IntegracaoInternaFabricaDelegates.getInstance().getSSPBDelegate();
        }
        return sspbDelegate;
    }

    /**
     * Define o atributo sspbDelegate
     */
    public void setSspbDelegate(SSPBDelegate sspbDelegate) {
        this.sspbDelegate = sspbDelegate;
    }

    /**
     * Método responsável por remover todos os relacionamentos com o boleto já existente.
     * 
     * @param boletoDDA
     * @param dao
     * @throws ComumException void
     * 
     */
    protected void removerRelacionamentoBoletoDDA(BoletoDDA boletoDDA, BoletoCipDao dao) throws ComumException {
        dao.removerBaixaEfetiva(boletoDDA.getId());
        dao.removerBaixaOperacional(boletoDDA.getId());
        dao.removerGrupoCalculo(boletoDDA.getId());
        dao.removerTerceiroAutorizado(boletoDDA.getId());
    }

    /**
     * Prepara o arquivo recebido da CIP para ser processado, pois os arquivos que chegam da CIP são criptografados e compactados, sendo assim os mesmos
     * precisam ser descriptografados e descompactados posteriormente
     * 
     * @param arquivo o arquivo que deverá ser preparado
     */
    protected byte[] prepararArquivoParaProcessamento(File arquivo) {
        FileInputStream streamDoArquivo = null;
        byte[] dadosCripto = null;
        byte[] dadosDecripto = null;
        byte[] dadosDescompactado = null;

        try {

            streamDoArquivo = new FileInputStream(arquivo);
            dadosCripto = new byte[(int) arquivo.length()];

            streamDoArquivo.read(dadosCripto);

            getLogger().debug("**** DESCRIPTOGRAFAR [ " + arquivo.getName() + " ] Iniciando...");

            /***********************************************************************************************************************************************************
             * em desenvolvimento este método não funciona adequadamente por não ser executado nos servidores de produção que possuem acesso aos dados corretos
             * para descriptografia, por este motivo aqui criei uma rotina apenas para retornar o array de bytes do arquivo informado para retornar ao chamador.
             * 
             * mas ressalto que isto é para ser utilizado apenas quando executado nos testes locais. quando este código for para a produção este trecho de
             * código deverá ser comentado.
             * 
             * Att.
             * 
             * Adriano.
             *********************************************************************************************************************************************************/

            boolean desenvolvimento = false;

            if (desenvolvimento) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(arquivo);

                    dadosDescompactado = new byte[fis.available()];

                    fis.read(dadosDescompactado);

                    return dadosDescompactado;
                } finally {
                    ArquivoUtil.fecharFileInputStream(fis);
                }

            }

            // descriptografa o arquivo
            dadosDecripto = getSspbDelegate().decriptar(dadosCripto);

            getLogger().debug("**** DESCRIPTOGRAFAR [ " + arquivo.getName() + " ] Concluído!");

            getLogger().debug("**** DESCOMPACTANDO [ " + arquivo.getName() + " ] Iniciando...");

            // descompacta o arquivo
            dadosDescompactado = ZipUtil.extrairArquivo(dadosDecripto).getBytes();

            getLogger().debug("**** DESCOMPACTANDO [ " + arquivo.getName() + " ] Concluído!");

            // retornar os bytes do aquivo para efeito de debug, pois o processo de decriptografar e descompactar não funciona na máquina de des
            return dadosDescompactado;
        } catch (Exception e) { // NOSONAR
            getLogger().debug("**** FALHA na efetiva preparação do arquivo para processamento! MOTIVO[" + e.getMessage() + "]");
            return null;
        } finally {
            ArquivoUtil.fecharFileInputStream(streamDoArquivo);
        }
    }

    /**
     * Método responsável por compactar o arquivo
     * 
     * @param xmlEnvio
     * @return
     * @throws ComumException byte[]
     * 
     */
    private byte[] compactarArquivo(byte[] xmlEnvio, String enconding) throws ComumException {
        byte[] bytesXmlEnvio = null;
        try {
            bytesXmlEnvio = ZipUtil.compactarArquivo(xmlEnvio, enconding);
            getLogger().debug("########## XML Compactado.");
        } catch (IOException e1) {
            getLogger().erro(e1, ERRO_COMPACTAR_XML_ENVIO);
            throw new ComumException(ERRO_COMPACTAR_XML_ENVIO, e1);
        }
        return bytesXmlEnvio;
    }

    /**
     * Método responsável por salvar o arquivo no diretorio
     * 
     * @param idParametroDiretorio
     * @param logEnvioArquivoDDA
     * @param bytesXmlEnvio
     * @throws ComumException void
     * 
     */
    private void salvarArquivo(Long idParametroDiretorio, LogEnvioArquivoDDA logEnvioArquivoDDA, byte[] bytesXmlEnvio) throws ComumException {
        String destinoArquivo = null;
        try {
            destinoArquivo = getDiretorio(idParametroDiretorio) + File.separator + logEnvioArquivoDDA.getDescNomeArquivoEnviado();
            EscritorXMLArquivoUtil.salvarArquivo(bytesXmlEnvio, destinoArquivo);
            getLogger().debug("########## XML salvo no diretorio --> " + destinoArquivo + ".");
        } catch (IOException e) {
            getLogger().erro(e, MensagemUtil.getString(ERRO_SALVAR_ARQUIVO_DIRETORIO, logEnvioArquivoDDA.getDescNomeArquivoEnviado(), destinoArquivo));
            throw new ComumException(MensagemUtil.getString(ERRO_SALVAR_ARQUIVO_DIRETORIO, logEnvioArquivoDDA.getDescNomeArquivoEnviado(), destinoArquivo), e);
        }
    }

    /**
     * Método responsável por incluir uma DDA0106 caso o número sequencial recebido seja menor que o da base.
     * 
     * @param boletoDDA
     * @throws ComumException void
     * 
     */
    protected void incluirMensagemDDA0106(BoletoDDA boletoDDA) throws ComumException {
        getLogger().info("Numero sequencial MENOR que o numero sequencial da base - Gerando mensagem 106");
        String codTipoBoletoConsultado = boletoDDA.getCodPartDestinatario().equals(Constantes.BANCOOB) ? TipoBoletoConsultado.PROPRIO : TipoBoletoConsultado.TERCEIRO;
        MensagemDDAConsultaBoleto mensagemDDAConsultaBoleto = new MensagemDDAConsultaBoleto(boletoDDA.getNumIdentificadorBoletoCip(), boletoDDA.getNumIdentificadorBoletoCip(),
                codTipoBoletoConsultado);
        mensagemDDADelegate.incluir(mensagemDDAConsultaBoleto, TipoMensagemDDA.DDA0106, getUsuarioLogado(), getIdInstituicaoLogado(), CanalEnum.RETAGUARDA.getId());
    }

    // FIXME Adriano retirar este método quando resolver a questão da execução da injeção pela anotação @DAO
    @Override
    @PostConstruct
    protected void postConstruct() {
        super.postConstruct();
    }

}
