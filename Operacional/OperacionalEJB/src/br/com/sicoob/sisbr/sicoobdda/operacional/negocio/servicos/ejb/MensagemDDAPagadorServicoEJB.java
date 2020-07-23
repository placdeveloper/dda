/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb
 * Arquivo:         MensagemDDAPagadorServicoEJB.java
 * Data Criação:    Dec 28, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorAgregadoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoOperacaoSicoobDDAEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagadorAgregado;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagadorConta;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.ADMDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SCIDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao.IntegracaoInternaException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.MensagemDDAPagadorServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.MensagemDDAPagadorDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;

/**
 * MensagemDDAPagadorServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ MensagemDDAPagadorServicoLocal.class })
@TransactionManagement(TransactionManagementType.BEAN)
public class MensagemDDAPagadorServicoEJB extends OperacionalCrudServicoEJB<MensagemDDAPagador> implements MensagemDDAPagadorServicoLocal {

    @SuppressWarnings("unused")
    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private MensagemDDAPagadorDao dao;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private MensagemDDADao mensagemDDADao;

    private ADMDelegate admDelegate;
    private SCIDelegate sciDelegate;

    private MensagemDDADelegate mensagemDDADelegate;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB#getDAO()
     */
    @Override
    protected MensagemDDAPagadorDao getDAO() {
        return dao;
    }

    /**
     * @return o atributo admDelegate
     */
    public ADMDelegate getAdmDelegate() {
        if (ObjectUtil.isNull(admDelegate)) {
            admDelegate = IntegracaoInternaFabricaDelegates.getInstance().getADMDelegate();
        }
        return admDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return SCIDelegate
     * 
     */
    private SCIDelegate getSCIDelegate() {
        if (ObjectUtil.isNull(sciDelegate)) {
            sciDelegate = IntegracaoInternaFabricaDelegates.getInstance().getSCIDelegate();
        }
        return sciDelegate;
    }

    /**
     * @return o atributo delegate
     */
    public MensagemDDADelegate getMensagemDDADelegate() {
        if (mensagemDDADelegate == null) {
            mensagemDDADelegate = IntegracaoCipFabricaDelegates.getInstance().getMensagemDDADelegate();
        }

        return mensagemDDADelegate;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MensagemDDAPagadorServico#incluirMensagemPagador(java.lang.String, java.lang.Integer,
     *      java.util.List, java.lang.Boolean, java.lang.String)
     */
    public MensagemDDAPagador incluirMensagemPagador(String numCpfCnpj, Integer idInstituicao, List<String> listaNumCCO, Boolean bolPagadorEletronico, String codTipoMensagemDDA,
            Short idCanal) throws ComumException {
        debug("### Incluíndo mensagem pagador...");
        debug("Parâmetro - numCpfCnpj: " + numCpfCnpj);
        debug("Parâmetro - idInstituicao: " + idInstituicao);
        debug("Parâmetro - listaNumCCO: " + listaNumCCO);
        debug("Parâmetro - bolPagadorEletronico: " + bolPagadorEletronico);
        debug("Parâmetro - codTipoMensagemDDA: " + codTipoMensagemDDA);

        try {
            getSessionContext().getUserTransaction().begin();

            MensagemDDAPagador mensagemPagador = new MensagemDDAPagador();
            mensagemPagador.setNumCpfCnpjPagador(numCpfCnpj);
            mensagemPagador.setCodTipoPessoaPagador(getCodTipoPessoa(numCpfCnpj));
            mensagemPagador.setBolPagadorEletronico(bolPagadorEletronico);
            
            Date dataReferencia = getMensagemDDADao().obterDataReferencia(TipoMensagemDDA.DDA0001);


            if (codTipoMensagemDDA.equals(TipoMensagemDDA.DDA0001)) {
                mensagemPagador.getListaMensagemDDAPagadorConta().addAll(geraListaMensagemPagadorConta(listaNumCCO, idInstituicao, mensagemPagador));
            }


            if (!ObjectUtil.isNull(dataReferencia)) {
                debug("Parâmetro - dataReferencia: " + dataReferencia);
                debug("Parâmetro - data Atual (new Date): " + DateUtil.obterDataSemHora(new Date()));
                if (DateUtil.igualA(dataReferencia, DateUtil.obterDataSemHora(new Date()))) {
                    getMensagemDDADelegate().incluir(mensagemPagador, codTipoMensagemDDA, new DateTimeDB(dataReferencia.getTime()), Constantes.INTEGER_ZERO, getUsuarioLogado(),
                            idInstituicao, idCanal);
                } else {
                    getMensagemDDADelegate().incluir(mensagemPagador, codTipoMensagemDDA, new DateTimeDB(dataReferencia.getTime()), Constantes.INTEGER_DEZ, getUsuarioLogado(),
                            idInstituicao, idCanal);
                }
            }else {
                getMensagemDDADelegate().incluir(mensagemPagador, codTipoMensagemDDA, getDataMovimento(),
                        Constantes.INTEGER_DEZ, getUsuarioLogado(), idInstituicao, idCanal);
            }
            

            getSessionContext().getUserTransaction().commit();

            return mensagemPagador;
        } catch (Exception e) { // NOSONAR
            getLogger().erro(e, e.getMessage());
            rollback();
            throw new ComumException("integracaocip.pagador.erro.gravar.solicitacao", e);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MensagemDDAPagadorServico#incluirMensagemPagadorAgregado(java.util.List,
     *      java.lang.String, java.lang.Integer, br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoOperacaoSicoobDDAEnum)
     */
    public MensagemDDAPagador incluirMensagemPagadorAgregado(List<PagadorAgregadoDto> listaPagadorAgregado, String numCpfCnpjPagador, Integer idInstituicao,
            TipoOperacaoSicoobDDAEnum tipoOperacao, Short idCanal) throws ComumException {
        debug("### Incluíndo mensagem pagador agregado...");
        debug("Parâmetro - listaPagadorAgregado: " + listaPagadorAgregado);
        debug("Parâmetro - numCpfCnpjPagador: " + numCpfCnpjPagador);
        debug("Parâmetro - idInstituicao: " + idInstituicao);
        debug("Parâmetro - tipoOperacao: " + tipoOperacao);

        try {
            getSessionContext().getUserTransaction().begin();

            MensagemDDAPagador mensagemPagador = new MensagemDDAPagador();
            mensagemPagador.setNumCpfCnpjPagador(numCpfCnpjPagador);
            mensagemPagador.setCodTipoPessoaPagador(getCodTipoPessoa(numCpfCnpjPagador));
            mensagemPagador.setBolPagadorEletronico(Boolean.TRUE);

            mensagemPagador.getListaMensagemDDAPagadorAgregado().addAll(geraListaMensagemPagadorAgregado(listaPagadorAgregado, mensagemPagador, tipoOperacao));

            Date dataReferencia = getMensagemDDADao().obterDataReferencia(TipoMensagemDDA.DDA0005);


            if (!ObjectUtil.isNull(dataReferencia)) {
                debug("Parâmetro - dataReferencia: " + dataReferencia);
                debug("Parâmetro - data Atual (new Date): " + DateUtil.obterDataSemHora(new Date()));
                if (DateUtil.igualA(dataReferencia, DateUtil.obterDataSemHora(new Date()))) {
                    getMensagemDDADelegate().incluir(mensagemPagador, TipoMensagemDDA.DDA0005, new DateTimeDB(dataReferencia.getTime()), Constantes.INTEGER_ZERO,
                            getUsuarioLogado(), idInstituicao, idCanal);
                } else {
                    getMensagemDDADelegate().incluir(mensagemPagador, TipoMensagemDDA.DDA0005, new DateTimeDB(dataReferencia.getTime()), Constantes.INTEGER_DEZ, getUsuarioLogado(),
                            idInstituicao, idCanal);
                }
            } else {
                getMensagemDDADelegate().incluir(mensagemPagador, TipoMensagemDDA.DDA0005, getDataMovimento(), Constantes.INTEGER_DEZ, getUsuarioLogado(), idInstituicao, idCanal);
            }

            getSessionContext().getUserTransaction().commit();

            return mensagemPagador;
        } catch (Exception e) { // NOSONAR
            getLogger().erro(e, e.getMessage());
            throw new ComumException("integracaocip.pagador.erro.gravar.solicitacao", e);
        }
    }

    /**
     * Método responsável por obter a data de movimento
     * 
     * @return
     * @throws IntegracaoInternaException
     */
    private DateTimeDB getDataMovimento() throws IntegracaoInternaException {
        return new DateTimeDB(getAdmDelegate().obterDataMovimentoBancoob().getTime());
    }

    /**
     * Método responsável por
     * 
     * @param listaNumCCO
     * @param idInstituicao
     * @return Object
     * @throws ComumException
     * 
     */
    private List<MensagemDDAPagadorConta> geraListaMensagemPagadorConta(List<String> listaNumCCO, Integer idInstituicao, MensagemDDAPagador mensagemPagador) throws ComumException {
        Integer numCooperativa = getSCIDelegate().obterInstituicaoCache(idInstituicao).getNumCooperativa();
        List<MensagemDDAPagadorConta> listaMensagemPagadorConta = new ArrayList<MensagemDDAPagadorConta>();
        for (String numCCO : listaNumCCO) {
            listaMensagemPagadorConta.add(new MensagemDDAPagadorConta(mensagemPagador, numCooperativa, new BigDecimal(numCCO), TipoOperacaoSicoobDDAEnum.INCLUSAO
                    .getCodigoOperacao(), new DateTimeDB()));
        }
        return listaMensagemPagadorConta;
    }

    /**
     * Método responsável por
     * 
     * @param listaPagadorAgregado
     * @param mensagemPagador
     * @param tipoOperacao
     * @return List<MensagemDDAPagadorAgregado>
     * 
     */
    private List<MensagemDDAPagadorAgregado> geraListaMensagemPagadorAgregado(List<PagadorAgregadoDto> listaPagadorAgregado, MensagemDDAPagador mensagemPagador,
            TipoOperacaoSicoobDDAEnum tipoOperacao) {
        List<MensagemDDAPagadorAgregado> listaMensagemPagadorAgregado = new ArrayList<MensagemDDAPagadorAgregado>();
        for (PagadorAgregadoDto pagadorAgregado : listaPagadorAgregado) {
            listaMensagemPagadorAgregado.add(new MensagemDDAPagadorAgregado(mensagemPagador, pagadorAgregado.getNumCpfCnpj(), getCodTipoPessoa(pagadorAgregado.getNumCpfCnpj()),
                    tipoOperacao.getCodigoOperacao()));
        }
        return listaMensagemPagadorAgregado;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String getCodTipoPessoa(String numCpfCnpj) {
        if (!ObjectUtil.isNull(numCpfCnpj) && numCpfCnpj.length() <= Constantes.TAMANHO_CPF) {
            return TipoPessoaEnum.PF.getCodDominioCip();
        } else {
            return TipoPessoaEnum.PJ.getCodDominioCip();
        }
    }

    /**
     * @return o atributo mensagemDDADao
     */
    public MensagemDDADao getMensagemDDADao() {
        return mensagemDDADao;
    }
}
