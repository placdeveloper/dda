/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.enums
 * Arquivo:         TipoErroEnum.java
 * Data Cria��o:    Jul 3, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * TipoErroEnum � respons�vel por
 * 
 * @author Jesliel.Rocha
 */
public enum TipoErroEnum {

    EDDA0157("EDDA0157", "Participante administrado n�o � administrado pelo participante principal informado"),
    EDDA0158("EDDA0158", "Nome fantasia do benefici�rio obrigat�rio para pessoa jur�dica."),
    EDDA0159("EDDA0159", "Data e hora da situa��o do benefici�rio n�o pode ser maior que a data e hora de recep��o da requisi��o"),
    EDDA0160("EDDA0160", "Data de in�cio do relacionamento do cliente benefici�rio com o participante n�o pode ser maior do que a data de refer�ncia do sistema"),
    EDDA0161("EDDA0161", "Data fim de relacionamento n�o deve ser informada para cliente benefici�rio com v�nculo ativo no participante"),
    EDDA0162("EDDA0162", "Data de fim do relacionamento do cliente benefici�rio com o participante n�o pode ser maior do que a data de refer�ncia do sistema"),
    EDDA0163("EDDA0163", "Data de fim do relacionamento com o participante n�o pode ser menor que a data de in�cio do relacionamento do cliente benefici�rio com o participante"),
    EDDA0164("EDDA0164", "Data de fim de relacionamento com o participante � obrigat�rio para clientes benefici�rios sem v�nculo ativo"),
    EDDA0165("EDDA0165", "O conte�do do ISPB do participante incorporado � inv�lido"),
    EDDA0166("EDDA0166", "ISPB do Participante Incorporado n�o pode ser igual ao ISPB do Participante Incorporador"),
    EDDA0167("EDDA0167", "O participante incorporado informado est� incorporado a outra institui��o"),
    EDDA0168("EDDA0168", "O participante incorporado informado n�o possui registro de incorpora��o"),
    EDDA0169("EDDA0169", "O conte�do da situa��o do convenio do cliente benefici�rio no participante � inv�lido"),
    EDDA0170("EDDA0170", "A situa��o do convenio do cliente benefici�rio no participante informado n�o consta no cadastro de dom�nio"),
    EDDA0171("EDDA0171", "Data de fim de relacionamento com o convenio � obrigat�rio para clientes benefici�rios que optaram por n�o atuar no convenio fornecido pelo participante"),
    EDDA0172(
            "EDDA0172",
            "Data de in�cio do relacionamento do cliente benefici�rio com o convenio fornecido pelo participante n�o pode ser maior do que a data de refer�ncia do sistema"),
    EDDA0173("EDDA0173", "Data fim de relacionamento do convenio n�o deve ser informada para cliente benefici�rio com v�nculo ativo no convenio fornecido pelo participante"),
    EDDA0174("EDDA0174", "Data de fim do relacionamento cliente benefici�rio no convenio fornecido pelo participante n�o pode ser maior do que a data de refer�ncia do sistema"),
    EDDA0175(
            "EDDA0175",
            "Data de fim do relacionamento do cliente benefici�rio no convenio fornecido pelo participante n�o pode ser menor que a data de in�cio do relacionamento do cliente benefici�rio com o convenio"),
    EDDA0176("EDDA0176", "O conte�do do tipo da ag�ncia de destino � inv�lido"),
    EDDA0177("EDDA0177", "O tipo da ag�ncia de destino n�o consta no cadastro de dom�nio"),
    EDDA0178("EDDA0178", "A n�mero de agencia de destino informado deve ser num�rico positivo e diferente de zero"),
    EDDA0179("EDDA0179", "O conte�do do tipo da conta de destino � inv�lido"),
    EDDA0180("EDDA0180", "O tipo da conta de destino n�o consta no cadastro de dom�nio"),
    EDDA0181("EDDA0181", "O n�mero de conta de destino informado deve ser num�rico positivo e diferente de zero"),
    EDDA0182("EDDA0182", "O conte�do do tipo de produto ou convenio � inv�lido"),
    EDDA0183("EDDA0183", "O tipo de produto ou convenio informado n�o consta no cadastro de dom�nio"),
    EDDA0184("EDDA0184", "O Tipo de carteira de convenio da cobran�a � obrigat�rio para o tipo de produto ou convenio informado"),
    EDDA0185("EDDA0185", "O conte�do do tipo de carteira do convenio da cobran�a � inv�lido"),
    EDDA0186("EDDA0186", "O tipo de carteira do convenio da cobran�a informado n�o consta no cadastro de dom�nio"),
    EDDA0187("EDDA0187", "O conte�do tipo de pessoa do representante do cliente benefici�rio � inv�lido"),
    EDDA0188("EDDA0188", "O tipo de pessoa do representante do cliente benefici�rio informado n�o consta no cadastro de dom�nio"),
    EDDA0189("EDDA0189", "CNPJ ou CPF do representante do cliente benefici�rio � inv�lido para o tipo de pessoa informado"),
    EDDA0190("EDDA0190", "Representante do cliente benefici�rio j� possui cadastro para o participante administrado informado"),
    EDDA0191("EDDA0191", "Representante do cliente benefici�rio j� possui cadastro ativo para o participante administrado informado"),
    EDDA0192("EDDA0192", "Representante do cliente benefici�rio n�o poder� ser informado mais do que uma vez"),
    EDDA0193("EDDA0193", "Representante do cliente benefici�rio n�o pode ser o pr�prio cliente benefici�rio."),
    EDDA0194("EDDA0194", "O conte�do do ISPB do participante Destinat�rio administrado � inv�lido"),
    EDDA0195("EDDA0195", "Participante Destinat�rio administrado n�o cadastrado no sistema"),
    EDDA0196("EDDA0196", "Participante Destinat�rio administrado n�o ativo no sistema"),
    EDDA0197("EDDA0197", "O participante Destinat�rio administrado n�o tem ades�o ao servi�o"),
    EDDA0198("EDDA0198", "O conte�do do participante Destinat�rio principal � inv�lido"),
    EDDA0199("EDDA0199", "ISPB do Emissor n�o pode ser diferente do ISPB do participante Destinat�rio principal"),
    EDDA0200("EDDA0200", "O conte�do do tipo de pessoa do cliente benefici�rio � inv�lido"),
    EDDA0201("EDDA0201", "O tipo da pessoa do cliente benefici�rio informado n�o consta no cadastro de dom�nio"),
    EDDA0202("EDDA0202", "O conte�do do nome fantasia do cliente benefici�rio � inv�lido"),
    EDDA0203("EDDA0203", "CNPJ ou CPF do cliente benefici�rio � inv�lido"),
    EDDA0204("EDDA0204", "O conte�do da situa��o do cliente benefici�rio � inv�lido"),
    EDDA0205("EDDA0205", "A situa��o do benefici�rio informada n�o consta no cadastro de dom�nio"),
    EDDA0206("EDDA0206", "Situa��o do cliente benefici�rio n�o permitida para uma requisi��o de manuten��o no cadastro do cliente benefici�rio"),
    EDDA0207("EDDA0207", "O conte�do da situa��o de relacionamento do cliente benefici�rio � inv�lido"),
    EDDA0208("EDDA0208", "A situa��o de relacionamento do cliente benefici�rio com o participante n�o consta no cadastro de dom�nio"),
    EDDA0209(
            "EDDA0209",
            "Data de fim do relacionamento do convenio contratado pelo cliente benefici�rio n�o pode ser maior do que a data de fim de relacionamento com o participante"),
    EDDA0210(
            "EDDA0210",
            "Data de in�cio do relacionamento do convenio contratado pelo cliente benefici�rio n�o pode ser menor do que a data de in�cio do relacionamento com o participante"),
    EDDA0211("EDDA0211", "Situa��o do convenio n�o permitida para situa��o de relacionamento do cliente benefici�rio no participante informada"),
    EDDA0212("EDDA0212", "Situa��o do convenio n�o permitida para o tipo de manuten��o do convenio informado"),
    EDDA0213("EDDA0213", "O conte�do do tipo de manuten��o do conv�nio � inv�lido"),
    EDDA0214("EDDA0214", "O tipo de manuten��o do conv�nio informado n�o consta no cadastro de dom�nio"),
    EDDA0215("EDDA0215", "Dados de agencia e/ou conta j� possui cadastro para o participante administrado e tipo de produto ou convenio informados"),
    EDDA0216("EDDA0216", "Dados de agencia e/ou conta e tipo de produto ou convenio n�o poder�o ser informados mais do que uma vez"),
    EDDA0217("EDDA0217", "Dados de agencia e/ou conta e tipo de produto ou convenio n�o possui cadastro para o participante administrado informado"),
    EDDA0218("EDDA0218", "O conte�do do tipo de manuten��o do representante do cliente benefici�rio � inv�lido"),
    EDDA0219("EDDA0219", "O tipo de manuten��o do representante do cliente benefici�rio informado n�o consta no cadastro de dom�nio"),
    EDDA0220("EDDA0220", "N�o � poss�vel excluir um representante do cliente benefici�rio j� exclu�do"),
    EDDA0221("EDDA0221", "Ao menos um convenio ativo deve estar vinculado ao cliente benefici�rio ativo"),
    EDDA0222("EDDA0222", "Situa��o do convenio n�o permitida para a inclus�o do cliente benefici�rio"),
    EDDA0223("EDDA0223", "Dados de agencia e/ou conta j� foram exclu�dos para o cliente benefici�rio tipo de produto e participante administrado informados"),
    EDDA0224("EDDA0224", "Situa��o de relacionamento do cliente benefici�rio n�o permitida para a inclus�o do cliente benefici�rio"),
    EDDA0225("EDDA0225", "N�o � poss�vel alterar o tipo de pessoa do cliente benefici�rio"),
    EDDA0226("EDDA0226", "N�o � poss�vel alterar o n�mero de documento do cliente benefici�rio"),
    EDDA0227("EDDA0227", "Cliente benefici�rio j� possui cadastro para o participante administrado informado"),
    EDDA0228("EDDA0228", "Cliente benefici�rio j� possui cadastro ativo para o participante administrado informado"),
    EDDA0229("EDDA0229", "N�o � poss�vel excluir um cliente benefici�rio j� excluido."),
    EDDA0230("EDDA0230", "Dados de agencia e/ou conta j� possui cadastro ativo para o participante administrado e tipo de produto ou convenio informados"),
    EDDA0231("EDDA0231", "Cliente benefici�rio j� cadastrado, nova inclus�o n�o � permitida para a situa��o de alerta anteriormente informada para o cliente benefici�rio"),
    EDDA0232("EDDA0232", "N�o � poss�vel alterar um cliente benefici�rio excluido."),
    EDDA0233("EDDA0233", "N�o � poss�vel alterar a situa��o do benefici�rio na requisi��o de altera��o do benefici�rio"),
    EDDA0234("EDDA0234", "N�mero de Identifica��o do benefici�rio n�o existe"),
    EDDA0235("EDDA0235", "N�mero de identifica��o do cliente benefici�rio n�o pertence ao participante administrado informado"),
    EDDA0236("EDDA0236", "Situa��o de relacionamento do cliente benefici�rio n�o permitida para a exclus�o do cliente benefici�rio"),
    EDDA0237("EDDA0237", "Tipo de pessoa do benefici�rio divergente do cadastro para o n�mero de identifica��o do benefici�rio informado"),
    EDDA0238("EDDA0238", "CNPJ ou CPF do benefici�rio divergente do cadastro para o n�mero de identifica��o do benefici�rio informado"),
    EDDA0239("EDDA0239", "Data de in�cio da �ltima altera��o de situa��o do cliente benefici�rio n�o pode ser maior do que a data de refer�ncia do sistema"),
    EDDA0240("EDDA0240", "Data de fim da �ltima altera��o de situa��o do cliente benefici�rio n�o pode ser maior do que a data de refer�ncia do sistema"),
    EDDA0241(
            "EDDA0241",
            "Data de fim da �ltima altera��o de situa��o do cliente benefici�rio n�o pode ser menor que a data de in�cio da �ltima altera��o de situa��o do cliente benefici�rio "),
    EDDA0242(
            "EDDA0242",
            "Ao informar a data de inicio da �ltima altera��o de situa��o do cliente benefici�rio, a data de fim da �ltima altera��o do cliente benefici�rio torna-se obrigat�ria"),
    EDDA0243(
            "EDDA0243",
            "Ao informar a data de fim da �ltima altera��o de situa��o do cliente benefici�rio, a data de in�cio da �ltima altera��o do cliente benefici�rio torna-se obrigat�ria"),
    EDDA0244("EDDA0244", "O conte�do do tipo de retorno para a resposta de solicita��o da consulta � inv�lido"),
    EDDA0245("EDDA0245", "O tipo de retorno para a resposta de solicita��o da consulta do benefici�rio informado n�o consta no cadastro de dom�nio"),
    EDDA0246("EDDA0246", "Participante administrado possui ades�o inativa ao canal de arquivo para a funcionalidade: ADDA504"),
    EDDA0247("EDDA0247", "Participante administrado n�o possui ades�o ao canal de arquivo para a funcionalidade ADDA504"),
    EDDA0248("EDDA0248", "O conte�do do CNPJ base do cliente benefici�rio � inv�lido"),
    EDDA0249("EDDA0249", "O conte�do do CNPJ base do cliente benefici�rio diverge do CNPJ ou CPF do cliente benefici�rio informado"),
    EDDA0250("EDDA0250", "Dados m�nimos necess�rios para a realiza��o da consulta do benefici�rio n�o foram informados"),
    EDDA0251("EDDA0251", "Per�odo ultrapassa o limite de dias permitidos para o tipo de retorno por mensagem"),
    EDDA0252("EDDA0252", "Per�odo ultrapassa o limite de dias permitidos para o tipo de retorno por arquivo"),
    EDDA0253("EDDA0253", "H� requisi��es pendentes, aguardar o processamento da pr�xima grade referente ao arquivo ADDA504, para realizar uma nova solicita��o"),
    EDDA0254("EDDA0254", "Situa��o do benefici�rio j� registrada para o participante administrado informado"),
    EDDA0255("EDDA0255", "O crit�rio de sele��o � obrigat�rio para identificar o tipo de relat�rio solicitado"),
    EDDA0256("EDDA0256", "O conte�do do tipo de solicita��o de relat�rio no cadastro de cliente benefici�rio � inv�lido"),
    EDDA0257("EDDA0257", "O tipo de solicita��o do relat�rio do cadastro de clientes benefici�rios informado n�o consta no cadastro de dom�nio"),
    EDDA0258("EDDA0258", "O grupo de benefici�rios � obrigat�rio para � solicita��o de relat�rios do cadastro de benefici�rios."),
    EDDA0259("EDDA0259", "Situa��o do cliente benefici�rio n�o permitida para uma requisi��o de invent�rio do cadastro de cliente benefici�rio"),
    EDDA0260("EDDA0260", "Situa��o do cliente benefici�rio n�o permitida para uma requisi��o de carga inicial do cadastro de cliente benefici�rio"),
    EDDA0261("EDDA0261", "O conte�do do nome do arquivo � inv�lido"),
    EDDA0262("EDDA0262", "O nome do arquivo informado n�o consta no cadastro de dom�nio"),
    EDDA0263("EDDA0263", "O conte�do do tipo de transmiss�o � inv�lido"),
    EDDA0264("EDDA0264", "O tipo de transmiss�o informado n�o consta no cadastro de dom�nio"),
    EDDA0265("EDDA0265", "O tipo de transmiss�o informado n�o � utilizado no DDA"),
    EDDA0266("EDDA0266", "ISPB do Destinat�rio � inv�lido"),
    EDDA0267("EDDA0267", "Nome do arquivo n�o permitido para solicita��o de relat�rio no cadastro de benefici�rios"),
    EDDA0268("EDDA0268", "H� requisi��es pendentes, aguardar o processamento da pr�xima grade de invent�rio de benefici�rios, para realizar uma nova solicita��o"),
    EDDA0269("EDDA0269", "H� requisi��es pendentes, aguardar o processamento da pr�xima grade de carga inicial de benefici�rios, para realizar uma nova solicita��o"),
    EDDA0270("EDDA0270", "Dados de ag�ncia e tipo de produto ou conv�nio n�o poder�o ser informados mais do que uma vez"),
    EDDA0271("EDDA0271", "Dados de ag�ncia, conta, c�digo do cliente ou conv�nio e tipo de produto n�o poder�o ser informados mais do que uma vez"),
    EDDA0272("EDDA0272", "Dados de ag�ncia, c�digo do cliente ou conv�nio e tipo de produto n�o poder�o ser informados mais do que uma vez"),
    EDDA0273("EDDA0273", "Dados de ag�ncia, institui��o incorporada e tipo de produto n�o poder�o ser informados mais do que uma vez"),
    EDDA0274("EDDA0274", "Dados de ag�ncia, conta, institui��o incorporada e tipo de produto n�o poder�o ser informados mais do que uma vez"),
    EDDA0275("EDDA0275", "Dados de ag�ncia, institui��o incorporada, c�digo de cliente ou conv�nio e tipo de produto n�o poder�o ser informados mais do que uma vez"),
    EDDA0276("EDDA0276", "Dados de ag�ncia, conta, institui��o incorporada, c�digo de cliente ou conv�nio e tipo de produto n�o poder�o ser informados mais do que uma vez"),
    EDDA0277("EDDA0277", "O n�mero de conta � obrigat�rio para o tipo de conta informado"),
    EDDA0278("EDDA0278", "O tipo de conta � obrigat�rio quando o n�mero de conta � informado"),
    EDDA0279("EDDA0279", "Situa��o do cliente benefici�rio n�o permitida para uma requisi��o de consulta"),
    EDDA0280("EDDA0280", "Participante administrado possui ades�o inativa ao canal de arquivo para a funcionalidade ADDA200"),
    EDDA0281("EDDA0281", "Participante administrado n�o possui ades�o ao canal de arquivo para a funcionalidade ADDA200"),
    EDDA0284("EDDA0284", "O conte�do do nome ou raz�o social do cliente benefici�rio � inv�lido"),
    EDDA0285("EDDA0285", "O conte�do do c�digo do cliente ou conv�nio do cliente benefici�rio � inv�lido"),
    EDDA0286("EDDA0286", "Dados de ag�ncia j� possui cadastro ativo para o participante administrado e tipo de produto informados"),
    EDDA0287("EDDA0287", "Dados de ag�ncia, conta e c�digo do cliente ou conv�nio j� possui cadastro ativo para o participante administrado e tipo de produto informados"),
    EDDA0288("EDDA0288", "Dados de ag�ncia e c�digo do cliente ou conv�nio j� possui cadastro ativo para o participante administrado e tipo de produto informados"),
    EDDA0289("EDDA0289", "Dados de ag�ncia e institui��o incorporada j� possui cadastro ativo para o participante administrado e tipo de produto informados"),
    EDDA0290("EDDA0290", "Dados de ag�ncia, conta e institui��o incorporada j� possui cadastro ativo para o participante administrado e tipo de produto informados"),
    EDDA0291("EDDA0291", "Dados de ag�ncia, institui��o incorporada e c�digo de cliente j� possui cadastro ativo para o participante administrado e tipo de produto informados"),
    EDDA0292("EDDA0292", "Dados de ag�ncia, conta, institui��o incorporada e c�digo de cliente j� est� ativo para o participante administrado e tipo de produto informados"),
    EDDA0293("EDDA0293", "Dados de ag�ncia j� exclu�dos para o benefici�rio, tipo de produto e participante administrado"),
    EDDA0294("EDDA0294", "Dados de ag�ncia, conta e c�digo do cliente ou conv�nio j� exclu�dos para o benefici�rio, tipo de produto e participante administrado"),
    EDDA0295("EDDA0295", "Dados de ag�ncia e c�digo do cliente ou conv�nio j� exclu�dos para o benefici�rio, tipo de produto e participante administrado"),
    EDDA0296("EDDA0296", "Dados de ag�ncia e institui��o incorporada j� exclu�dos para o benefici�rio, tipo de produto e participante administrado"),
    EDDA0297("EDDA0297", "Dados de ag�ncia, conta e institui��o incorporada j� exclu�dos para o benefici�rio, tipo de produto e participante administrado"),
    EDDA0298("EDDA0298", "Dados de ag�ncia, institui��o incorporada e c�digo de cliente j� exclu�dos para o benefici�rio, tipo de produto e participante administrado"),
    EDDA0299("EDDA0299", "Dados de ag�ncia, conta, institui��o incorporada e c�digo de cliente j� exclu�dos para o benefici�rio, tipo de produto e participante administrado"),
    EDDA0300("EDDA0300", "Dados de conv�nio n�o possui cadastro para o participante administrado informado");

    private String codErro;
    private String descErro;

    /**
     * @param codErro
     * @param descErro
     */
    private TipoErroEnum(String codErro, String descErro) {
        this.codErro = codErro;
        this.descErro = descErro;
    }

    /**
     * @return o atributo codErro
     */
    public String getCodErro() {
        return codErro;
    }

    /**
     * @return o atributo descErro
     */
    public String getDescErro() {
        return descErro;
    }

    /**
     * 
     * M�todo respons�vel por
     * 
     * @param codErro
     * @return TipoErroEnum
     * 
     */
    public static TipoErroEnum getBy(String codErro) {
        TipoErroEnum[] lista = values();
        for (TipoErroEnum tipoErro : lista) {
            if (tipoErro.getCodErro().equals(codErro)) {
                return tipoErro;
            }
        }
        return null;
    }

    public String toString() {
        return getCodErro() + " - " + getDescErro();

    }

}
