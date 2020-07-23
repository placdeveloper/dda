/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos
 * Arquivo:         ExpurgarMensagensDDAHistoricoServicoTest.java
 * Data Cria��o:    Aug 7, 2017
 */
/*
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import org.mockito.Mockito;

 *//**
 * HistoricoMensagemDDAServico � respons�vel por
 * 
 * @author felipe.rosa
 */
/*
// @RunWith(MockitoJUnitRunner.class)
public class HistoricoMensagemDDAServicoTest extends Mockito {

 // @InjectMocks
 // private HistoricoMensagemDDAServicoEJB ejb;
 //
 // @Mock
 // private HistoricoMensagemDDADao dao;
 //
 // @Mock
 // private ParametroDao parametroDao;

 *//**
 * M�todo respons�vel por
 * 
 * @throws ComumException void
 * 
 */
/*
// @Test
// public void excluirDefault() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, excluir(TipoHistoricoMensagemEnum.MENSAGEMDDARETORNOCIP, null, null));
// }

// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void excluirMENSAGEMDDA() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, excluir(TipoHistoricoMensagemEnum.MENSAGEMDDA, Constantes.LONG_UM, Constantes.LONG_DOIS));
// verify(dao, times(1)).excluirMensagemDDA(Constantes.LONG_UM, Constantes.LONG_DOIS);
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void excluirMENSAGEMDDABOLETO() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, excluir(TipoHistoricoMensagemEnum.MENSAGEMDDABOLETO, null, null));
// verify(dao, times(1)).excluirMensagemDDABoleto();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void excluirLOGDETALHEMENSAGEMDDA() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, excluir(TipoHistoricoMensagemEnum.LOGDETALHEMENSAGEMDDA, null, null));
// verify(dao, times(1)).excluirLogDetalheMensagemDDA();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void excluirMENSAGEMDDAACEITE() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, excluir(TipoHistoricoMensagemEnum.MENSAGEMDDAACEITE, null, null));
// verify(dao, times(1)).excluirMensagemAceite();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void excluirMENSAGEMDDABAIXAEFETIVA() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, excluir(TipoHistoricoMensagemEnum.MENSAGEMDDABAIXAEFETIVA, null, null));
// verify(dao, times(1)).excluirMensagemBaixaEfetiva();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void excluirMENSAGEMDDABAIXAOPERACIONAL() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, excluir(TipoHistoricoMensagemEnum.MENSAGEMDDABAIXAOPERACIONAL, null, null));
// verify(dao, times(1)).excluirMensagemBaixaOperacional();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void excluirMENSAGEMDDABENEFICIARIO() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, excluir(TipoHistoricoMensagemEnum.MENSAGEMDDABENEFICIARIO, null, null));
// verify(dao, times(1)).excluirMensagemBeneficiario();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void excluirMENSAGEMDDABENEFICIARIOCONVENIO() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, excluir(TipoHistoricoMensagemEnum.MENSAGEMDDABENEFICIARIOCONVENIO, null, null));
// verify(dao, times(1)).excluirMensagemBeneficiarioConvenio();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void excluirMENSAGEMDDABENEFICIARIOREPRESENTANTE() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, excluir(TipoHistoricoMensagemEnum.MENSAGEMDDABENEFICIARIOREPRESENTANTE, null, null));
// verify(dao, times(1)).excluirMensagemBeneficiarioRepresentante();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void excluirMENSAGEMDDABOLETODESCONTO() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, excluir(TipoHistoricoMensagemEnum.MENSAGEMDDABOLETODESCONTO, null, null));
// verify(dao, times(1)).excluirMensagemBoletoDesconto();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void excluirMENSAGEMDDABOLETOGRUPOCALCULO() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, excluir(TipoHistoricoMensagemEnum.MENSAGEMDDABOLETOGRUPOCALCULO, null, null));
// verify(dao, times(1)).excluirMensagemBoletoGrupoCalculo();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void excluirMENSAGEMDDABOLETOJUROS() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, excluir(TipoHistoricoMensagemEnum.MENSAGEMDDABOLETOJUROS, null, null));
// verify(dao, times(1)).excluirMensagemBoletoJuros();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void excluirMENSAGEMDDABOLETOMULTA() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, excluir(TipoHistoricoMensagemEnum.MENSAGEMDDABOLETOMULTA, null, null));
// verify(dao, times(1)).excluirMensagemBoletoMulta();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void excluirMENSAGEMDDABOLETONOTAFISCAL() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, excluir(TipoHistoricoMensagemEnum.MENSAGEMDDABOLETONOTAFISCAL, null, null));
// verify(dao, times(1)).excluirMensagemBoletoNotaFiscal();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void excluirMENSAGEMDDABOLETOTEXTOINFO() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, excluir(TipoHistoricoMensagemEnum.MENSAGEMDDABOLETOTEXTOINFO, null, null));
// verify(dao, times(1)).excluirMensagemBoletoTextoInfo();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void excluirMENSAGEMDDACONSULTABOLETO() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, excluir(TipoHistoricoMensagemEnum.MENSAGEMDDACONSULTABOLETO, null, null));
// verify(dao, times(1)).excluirMensagemConsultaBoleto();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void excluirMENSAGEMDDAPAGADORCONTA() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, excluir(TipoHistoricoMensagemEnum.MENSAGEMDDAPAGADORCONTA, null, null));
// verify(dao, times(1)).excluirMensagemPagadorConta();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void excluirMENSAGEMDDAPAGADORAGREGADO() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, excluir(TipoHistoricoMensagemEnum.MENSAGEMDDAPAGADORAGREGADO, null, null));
// verify(dao, times(1)).excluirMensagemPagadorAgregado();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void excluirMENSAGEMDDAPAGADOR() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, excluir(TipoHistoricoMensagemEnum.MENSAGEMDDAPAGADOR, null, null));
// verify(dao, times(1)).excluirMensagemPagador();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void excluirMENSAGEMDDATERCEIROAUTORIZADO() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, excluir(TipoHistoricoMensagemEnum.MENSAGEMDDATERCEIROAUTORIZADO, null, null));
// verify(dao, times(1)).excluirMensagemTerceiroAutorizado();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void excluirERROMENSAGEMRETORNOCIP() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, excluir(TipoHistoricoMensagemEnum.ERROMENSAGEMRETORNOCIP, null, null));
// verify(dao, times(1)).excluirMensagemDDAErroRetornoCip();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void incluirHistoricoDefault() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirHistorico(TipoHistoricoMensagemEnum.ERROMENSAGEMRETORNOCIP, null, null));
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void incluirHistoricoMENSAGEMDDA() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirHistorico(TipoHistoricoMensagemEnum.MENSAGEMDDA, Constantes.LONG_UM, Constantes.LONG_DOIS));
// verify(parametroDao, times(1)).obterValor(ParametroDDA.QTD_DIAS_LIMITE_EXPURGO_MENSAGENS, Constantes.ID_SICOOB);
// verify(dao, times(1)).incluirHistoricoMensagemDDA(Constantes.LONG_UM, Constantes.LONG_DOIS, Constantes.LONG_UM);
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void incluirHistoricoMENSAGEMDDABOLETO() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirHistorico(TipoHistoricoMensagemEnum.MENSAGEMDDABOLETO, null, null));
// verify(dao, times(1)).incluirHistoricoMensagemDDABoleto();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void incluirHistoricoMENSAGEMDDARETORNOCIP() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirHistorico(TipoHistoricoMensagemEnum.MENSAGEMDDARETORNOCIP, null, null));
// verify(dao, times(1)).incluirHistoricoMensagemDDARetornoCip();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void incluirHistoricoLOGDETALHEMENSAGEMDDA() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirHistorico(TipoHistoricoMensagemEnum.LOGDETALHEMENSAGEMDDA, null, null));
// verify(dao, times(1)).incluirHistoricoLogDetalheMensagemDDA();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void incluirHistoricoMENSAGEMDDAACEITE() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirHistorico(TipoHistoricoMensagemEnum.MENSAGEMDDAACEITE, null, null));
// verify(dao, times(1)).incluirHistoricoMensagemAceite();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void incluirHistoricoMENSAGEMDDABAIXAEFETIVA() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirHistorico(TipoHistoricoMensagemEnum.MENSAGEMDDABAIXAEFETIVA, null, null));
// verify(dao, times(1)).incluirHistoricoMensagemBaixaEfetiva();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void incluirHistoricoMENSAGEMDDABAIXAOPERACIONAL() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirHistorico(TipoHistoricoMensagemEnum.MENSAGEMDDABAIXAOPERACIONAL, null, null));
// verify(dao, times(1)).incluirHistoricoMensagemBaixaOperacional();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void incluirHistoricoMENSAGEMDDABENEFICIARIO() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirHistorico(TipoHistoricoMensagemEnum.MENSAGEMDDABENEFICIARIO, null, null));
// verify(dao, times(1)).incluirHistoricoMensagemBeneficiario();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void incluirHistoricoMENSAGEMDDABENEFICIARIOCONVENIO() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirHistorico(TipoHistoricoMensagemEnum.MENSAGEMDDABENEFICIARIOCONVENIO, null, null));
// verify(dao, times(1)).incluirHistoricoMensagemBeneficiarioConvenio();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void incluirHistoricoMENSAGEMDDABENEFICIARIOREPRESENTANTE() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirHistorico(TipoHistoricoMensagemEnum.MENSAGEMDDABENEFICIARIOREPRESENTANTE, null, null));
// verify(dao, times(1)).incluirHistoricoMensagemBeneficiarioRepresentante();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void incluirHistoricoMENSAGEMDDABOLETODESCONTO() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirHistorico(TipoHistoricoMensagemEnum.MENSAGEMDDABOLETODESCONTO, null, null));
// verify(dao, times(1)).incluirHistoricoMensagemBoletoDesconto();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void incluirHistoricoMENSAGEMDDABOLETOGRUPOCALCULO() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirHistorico(TipoHistoricoMensagemEnum.MENSAGEMDDABOLETOGRUPOCALCULO, null, null));
// verify(dao, times(1)).incluirHistoricoMensagemBoletoGrupoCalculo();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void incluirHistoricoMENSAGEMDDABOLETOJUROS() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirHistorico(TipoHistoricoMensagemEnum.MENSAGEMDDABOLETOJUROS, null, null));
// verify(dao, times(1)).incluirHistoricoMensagemBoletoJuros();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void incluirHistoricoMENSAGEMDDABOLETOMULTA() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirHistorico(TipoHistoricoMensagemEnum.MENSAGEMDDABOLETOMULTA, null, null));
// verify(dao, times(1)).incluirHistoricoMensagemBoletoMulta();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void incluirHistoricoMENSAGEMDDABOLETONOTAFISCAL() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirHistorico(TipoHistoricoMensagemEnum.MENSAGEMDDABOLETONOTAFISCAL, null, null));
// verify(dao, times(1)).incluirHistoricoMensagemBoletoNotaFiscal();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void incluirHistoricoMENSAGEMDDABOLETOTEXTOINFO() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirHistorico(TipoHistoricoMensagemEnum.MENSAGEMDDABOLETOTEXTOINFO, null, null));
// verify(dao, times(1)).incluirHistoricoMensagemBoletoTextoInfo();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void incluirHistoricoMENSAGEMDDACONSULTABOLETO() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirHistorico(TipoHistoricoMensagemEnum.MENSAGEMDDACONSULTABOLETO, null, null));
// verify(dao, times(1)).incluirHistoricoMensagemConsultaBoleto();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void incluirHistoricoMENSAGEMDDAPAGADORCONTA() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirHistorico(TipoHistoricoMensagemEnum.MENSAGEMDDAPAGADORCONTA, null, null));
// verify(dao, times(1)).incluirHistoricoMensagemPagadorConta();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void incluirHistoricoMENSAGEMDDAPAGADORAGREGADO() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirHistorico(TipoHistoricoMensagemEnum.MENSAGEMDDAPAGADORAGREGADO, null, null));
// verify(dao, times(1)).incluirHistoricoMensagemPagadorAgregado();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void incluirHistoricoMENSAGEMDDAPAGADOR() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirHistorico(TipoHistoricoMensagemEnum.MENSAGEMDDAPAGADOR, null, null));
// verify(dao, times(1)).incluirHistoricoMensagemPagador();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @throws ComumException void
// *
// */
// @Test
// public void incluirHistoricoMENSAGEMDDATERCEIROAUTORIZADO() throws ComumException {
// Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirHistorico(TipoHistoricoMensagemEnum.MENSAGEMDDATERCEIROAUTORIZADO, null, null));
// verify(dao, times(1)).incluirHistoricoMensagemTerceiroAutorizado();
// }
//
// /**
// * M�todo respons�vel por
// *
// * @param tipoExpurgo
// * @param idMensagemInicial
// * @param idMensagemFinal
// * @return
// * @throws ComumException String
// *
// */
// private String excluir(TipoHistoricoMensagemEnum tipoExpurgo, Long idMensagemInicial, Long idMensagemFinal) throws ComumException {
// ejb.excluirMensagem(tipoExpurgo, idMensagemInicial, idMensagemFinal);
// return Constantes.TESTE_SUCESSO;
// }
//
// /**
// * M�todo respons�vel por
// *
// * @param tipoHistorico
// * @param idMensagemInicial
// * @param idMensagemFinal
// * @return
// * @throws ComumException String
// *
// */
// private String incluirHistorico(TipoHistoricoMensagemEnum tipoHistorico, Long idMensagemInicial, Long idMensagemFinal) throws ComumException {
// when(parametroDao.obterValor(ParametroDDA.QTD_DIAS_LIMITE_EXPURGO_MENSAGENS, Constantes.ID_SICOOB)).thenReturn(Constantes.STRING_NUMERO_1);
// ejb.incluirHistorico(tipoHistorico, idMensagemInicial, idMensagemFinal);
// return Constantes.TESTE_SUCESSO;
// }
// }
