/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.enums
 * Arquivo:         TipoErroEnum.java
 * Data Criação:    Jul 3, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums;

/**
 * TipoErroEnum é responsável por
 * 
 * @author Jesliel.Rocha
 */
public enum TipoErroEnum {

    EDDA0157("EDDA0157", "Participante administrado não é administrado pelo participante principal informado"),
    EDDA0158("EDDA0158", "Nome fantasia do beneficiário obrigatório para pessoa jurídica."),
    EDDA0159("EDDA0159", "Data e hora da situação do beneficiário não pode ser maior que a data e hora de recepção da requisição"),
    EDDA0160("EDDA0160", "Data de início do relacionamento do cliente beneficiário com o participante não pode ser maior do que a data de referência do sistema"),
    EDDA0161("EDDA0161", "Data fim de relacionamento não deve ser informada para cliente beneficiário com vínculo ativo no participante"),
    EDDA0162("EDDA0162", "Data de fim do relacionamento do cliente beneficiário com o participante não pode ser maior do que a data de referência do sistema"),
    EDDA0163("EDDA0163", "Data de fim do relacionamento com o participante não pode ser menor que a data de início do relacionamento do cliente beneficiário com o participante"),
    EDDA0164("EDDA0164", "Data de fim de relacionamento com o participante é obrigatório para clientes beneficiários sem vínculo ativo"),
    EDDA0165("EDDA0165", "O conteúdo do ISPB do participante incorporado é inválido"),
    EDDA0166("EDDA0166", "ISPB do Participante Incorporado não pode ser igual ao ISPB do Participante Incorporador"),
    EDDA0167("EDDA0167", "O participante incorporado informado está incorporado a outra instituição"),
    EDDA0168("EDDA0168", "O participante incorporado informado não possui registro de incorporação"),
    EDDA0169("EDDA0169", "O conteúdo da situação do convenio do cliente beneficiário no participante é inválido"),
    EDDA0170("EDDA0170", "A situação do convenio do cliente beneficiário no participante informado não consta no cadastro de domínio"),
    EDDA0171("EDDA0171", "Data de fim de relacionamento com o convenio é obrigatório para clientes beneficiários que optaram por não atuar no convenio fornecido pelo participante"),
    EDDA0172(
            "EDDA0172",
            "Data de início do relacionamento do cliente beneficiário com o convenio fornecido pelo participante não pode ser maior do que a data de referência do sistema"),
    EDDA0173("EDDA0173", "Data fim de relacionamento do convenio não deve ser informada para cliente beneficiário com vínculo ativo no convenio fornecido pelo participante"),
    EDDA0174("EDDA0174", "Data de fim do relacionamento cliente beneficiário no convenio fornecido pelo participante não pode ser maior do que a data de referência do sistema"),
    EDDA0175(
            "EDDA0175",
            "Data de fim do relacionamento do cliente beneficiário no convenio fornecido pelo participante não pode ser menor que a data de início do relacionamento do cliente beneficiário com o convenio"),
    EDDA0176("EDDA0176", "O conteúdo do tipo da agência de destino é inválido"),
    EDDA0177("EDDA0177", "O tipo da agência de destino não consta no cadastro de domínio"),
    EDDA0178("EDDA0178", "A número de agencia de destino informado deve ser numérico positivo e diferente de zero"),
    EDDA0179("EDDA0179", "O conteúdo do tipo da conta de destino é inválido"),
    EDDA0180("EDDA0180", "O tipo da conta de destino não consta no cadastro de domínio"),
    EDDA0181("EDDA0181", "O número de conta de destino informado deve ser numérico positivo e diferente de zero"),
    EDDA0182("EDDA0182", "O conteúdo do tipo de produto ou convenio é inválido"),
    EDDA0183("EDDA0183", "O tipo de produto ou convenio informado não consta no cadastro de domínio"),
    EDDA0184("EDDA0184", "O Tipo de carteira de convenio da cobrança é obrigatório para o tipo de produto ou convenio informado"),
    EDDA0185("EDDA0185", "O conteúdo do tipo de carteira do convenio da cobrança é inválido"),
    EDDA0186("EDDA0186", "O tipo de carteira do convenio da cobrança informado não consta no cadastro de domínio"),
    EDDA0187("EDDA0187", "O conteúdo tipo de pessoa do representante do cliente beneficiário é inválido"),
    EDDA0188("EDDA0188", "O tipo de pessoa do representante do cliente beneficiário informado não consta no cadastro de domínio"),
    EDDA0189("EDDA0189", "CNPJ ou CPF do representante do cliente beneficiário é inválido para o tipo de pessoa informado"),
    EDDA0190("EDDA0190", "Representante do cliente beneficiário já possui cadastro para o participante administrado informado"),
    EDDA0191("EDDA0191", "Representante do cliente beneficiário já possui cadastro ativo para o participante administrado informado"),
    EDDA0192("EDDA0192", "Representante do cliente beneficiário não poderá ser informado mais do que uma vez"),
    EDDA0193("EDDA0193", "Representante do cliente beneficiário não pode ser o próprio cliente beneficiário."),
    EDDA0194("EDDA0194", "O conteúdo do ISPB do participante Destinatário administrado é inválido"),
    EDDA0195("EDDA0195", "Participante Destinatário administrado não cadastrado no sistema"),
    EDDA0196("EDDA0196", "Participante Destinatário administrado não ativo no sistema"),
    EDDA0197("EDDA0197", "O participante Destinatário administrado não tem adesão ao serviço"),
    EDDA0198("EDDA0198", "O conteúdo do participante Destinatário principal é inválido"),
    EDDA0199("EDDA0199", "ISPB do Emissor não pode ser diferente do ISPB do participante Destinatário principal"),
    EDDA0200("EDDA0200", "O conteúdo do tipo de pessoa do cliente beneficiário é inválido"),
    EDDA0201("EDDA0201", "O tipo da pessoa do cliente beneficiário informado não consta no cadastro de domínio"),
    EDDA0202("EDDA0202", "O conteúdo do nome fantasia do cliente beneficiário é inválido"),
    EDDA0203("EDDA0203", "CNPJ ou CPF do cliente beneficiário é inválido"),
    EDDA0204("EDDA0204", "O conteúdo da situação do cliente beneficiário é inválido"),
    EDDA0205("EDDA0205", "A situação do beneficiário informada não consta no cadastro de domínio"),
    EDDA0206("EDDA0206", "Situação do cliente beneficiário não permitida para uma requisição de manutenção no cadastro do cliente beneficiário"),
    EDDA0207("EDDA0207", "O conteúdo da situação de relacionamento do cliente beneficiário é inválido"),
    EDDA0208("EDDA0208", "A situação de relacionamento do cliente beneficiário com o participante não consta no cadastro de domínio"),
    EDDA0209(
            "EDDA0209",
            "Data de fim do relacionamento do convenio contratado pelo cliente beneficiário não pode ser maior do que a data de fim de relacionamento com o participante"),
    EDDA0210(
            "EDDA0210",
            "Data de início do relacionamento do convenio contratado pelo cliente beneficiário não pode ser menor do que a data de início do relacionamento com o participante"),
    EDDA0211("EDDA0211", "Situação do convenio não permitida para situação de relacionamento do cliente beneficiário no participante informada"),
    EDDA0212("EDDA0212", "Situação do convenio não permitida para o tipo de manutenção do convenio informado"),
    EDDA0213("EDDA0213", "O conteúdo do tipo de manutenção do convênio é inválido"),
    EDDA0214("EDDA0214", "O tipo de manutenção do convênio informado não consta no cadastro de domínio"),
    EDDA0215("EDDA0215", "Dados de agencia e/ou conta já possui cadastro para o participante administrado e tipo de produto ou convenio informados"),
    EDDA0216("EDDA0216", "Dados de agencia e/ou conta e tipo de produto ou convenio não poderão ser informados mais do que uma vez"),
    EDDA0217("EDDA0217", "Dados de agencia e/ou conta e tipo de produto ou convenio não possui cadastro para o participante administrado informado"),
    EDDA0218("EDDA0218", "O conteúdo do tipo de manutenção do representante do cliente beneficiário é inválido"),
    EDDA0219("EDDA0219", "O tipo de manutenção do representante do cliente beneficiário informado não consta no cadastro de domínio"),
    EDDA0220("EDDA0220", "Não é possível excluir um representante do cliente beneficiário já excluído"),
    EDDA0221("EDDA0221", "Ao menos um convenio ativo deve estar vinculado ao cliente beneficiário ativo"),
    EDDA0222("EDDA0222", "Situação do convenio não permitida para a inclusão do cliente beneficiário"),
    EDDA0223("EDDA0223", "Dados de agencia e/ou conta já foram excluídos para o cliente beneficiário tipo de produto e participante administrado informados"),
    EDDA0224("EDDA0224", "Situação de relacionamento do cliente beneficiário não permitida para a inclusão do cliente beneficiário"),
    EDDA0225("EDDA0225", "Não é possível alterar o tipo de pessoa do cliente beneficiário"),
    EDDA0226("EDDA0226", "Não é possível alterar o número de documento do cliente beneficiário"),
    EDDA0227("EDDA0227", "Cliente beneficiário já possui cadastro para o participante administrado informado"),
    EDDA0228("EDDA0228", "Cliente beneficiário já possui cadastro ativo para o participante administrado informado"),
    EDDA0229("EDDA0229", "Não é possível excluir um cliente beneficiário já excluido."),
    EDDA0230("EDDA0230", "Dados de agencia e/ou conta já possui cadastro ativo para o participante administrado e tipo de produto ou convenio informados"),
    EDDA0231("EDDA0231", "Cliente beneficiário já cadastrado, nova inclusão não é permitida para a situação de alerta anteriormente informada para o cliente beneficiário"),
    EDDA0232("EDDA0232", "Não é possível alterar um cliente beneficiário excluido."),
    EDDA0233("EDDA0233", "Não é possível alterar a situação do beneficiário na requisição de alteração do beneficiário"),
    EDDA0234("EDDA0234", "Número de Identificação do beneficiário não existe"),
    EDDA0235("EDDA0235", "Número de identificação do cliente beneficiário não pertence ao participante administrado informado"),
    EDDA0236("EDDA0236", "Situação de relacionamento do cliente beneficiário não permitida para a exclusão do cliente beneficiário"),
    EDDA0237("EDDA0237", "Tipo de pessoa do beneficiário divergente do cadastro para o número de identificação do beneficiário informado"),
    EDDA0238("EDDA0238", "CNPJ ou CPF do beneficiário divergente do cadastro para o número de identificação do beneficiário informado"),
    EDDA0239("EDDA0239", "Data de início da última alteração de situação do cliente beneficiário não pode ser maior do que a data de referência do sistema"),
    EDDA0240("EDDA0240", "Data de fim da última alteração de situação do cliente beneficiário não pode ser maior do que a data de referência do sistema"),
    EDDA0241(
            "EDDA0241",
            "Data de fim da última alteração de situação do cliente beneficiário não pode ser menor que a data de início da última alteração de situação do cliente beneficiário "),
    EDDA0242(
            "EDDA0242",
            "Ao informar a data de inicio da última alteração de situação do cliente beneficiário, a data de fim da última alteração do cliente beneficiário torna-se obrigatória"),
    EDDA0243(
            "EDDA0243",
            "Ao informar a data de fim da última alteração de situação do cliente beneficiário, a data de início da última alteração do cliente beneficiário torna-se obrigatória"),
    EDDA0244("EDDA0244", "O conteúdo do tipo de retorno para a resposta de solicitação da consulta é inválido"),
    EDDA0245("EDDA0245", "O tipo de retorno para a resposta de solicitação da consulta do beneficiário informado não consta no cadastro de domínio"),
    EDDA0246("EDDA0246", "Participante administrado possui adesão inativa ao canal de arquivo para a funcionalidade: ADDA504"),
    EDDA0247("EDDA0247", "Participante administrado não possui adesão ao canal de arquivo para a funcionalidade ADDA504"),
    EDDA0248("EDDA0248", "O conteúdo do CNPJ base do cliente beneficiário é inválido"),
    EDDA0249("EDDA0249", "O conteúdo do CNPJ base do cliente beneficiário diverge do CNPJ ou CPF do cliente beneficiário informado"),
    EDDA0250("EDDA0250", "Dados mínimos necessários para a realização da consulta do beneficiário não foram informados"),
    EDDA0251("EDDA0251", "Período ultrapassa o limite de dias permitidos para o tipo de retorno por mensagem"),
    EDDA0252("EDDA0252", "Período ultrapassa o limite de dias permitidos para o tipo de retorno por arquivo"),
    EDDA0253("EDDA0253", "Há requisições pendentes, aguardar o processamento da próxima grade referente ao arquivo ADDA504, para realizar uma nova solicitação"),
    EDDA0254("EDDA0254", "Situação do beneficiário já registrada para o participante administrado informado"),
    EDDA0255("EDDA0255", "O critério de seleção é obrigatório para identificar o tipo de relatório solicitado"),
    EDDA0256("EDDA0256", "O conteúdo do tipo de solicitação de relatório no cadastro de cliente beneficiário é inválido"),
    EDDA0257("EDDA0257", "O tipo de solicitação do relatório do cadastro de clientes beneficiários informado não consta no cadastro de domínio"),
    EDDA0258("EDDA0258", "O grupo de beneficiários é obrigatório para á solicitação de relatórios do cadastro de beneficiários."),
    EDDA0259("EDDA0259", "Situação do cliente beneficiário não permitida para uma requisição de inventário do cadastro de cliente beneficiário"),
    EDDA0260("EDDA0260", "Situação do cliente beneficiário não permitida para uma requisição de carga inicial do cadastro de cliente beneficiário"),
    EDDA0261("EDDA0261", "O conteúdo do nome do arquivo é inválido"),
    EDDA0262("EDDA0262", "O nome do arquivo informado não consta no cadastro de domínio"),
    EDDA0263("EDDA0263", "O conteúdo do tipo de transmissão é inválido"),
    EDDA0264("EDDA0264", "O tipo de transmissão informado não consta no cadastro de domínio"),
    EDDA0265("EDDA0265", "O tipo de transmissão informado não é utilizado no DDA"),
    EDDA0266("EDDA0266", "ISPB do Destinatário é inválido"),
    EDDA0267("EDDA0267", "Nome do arquivo não permitido para solicitação de relatório no cadastro de beneficiários"),
    EDDA0268("EDDA0268", "Há requisições pendentes, aguardar o processamento da próxima grade de inventário de beneficiários, para realizar uma nova solicitação"),
    EDDA0269("EDDA0269", "Há requisições pendentes, aguardar o processamento da próxima grade de carga inicial de beneficiários, para realizar uma nova solicitação"),
    EDDA0270("EDDA0270", "Dados de agência e tipo de produto ou convênio não poderão ser informados mais do que uma vez"),
    EDDA0271("EDDA0271", "Dados de agência, conta, código do cliente ou convênio e tipo de produto não poderão ser informados mais do que uma vez"),
    EDDA0272("EDDA0272", "Dados de agência, código do cliente ou convênio e tipo de produto não poderão ser informados mais do que uma vez"),
    EDDA0273("EDDA0273", "Dados de agência, instituição incorporada e tipo de produto não poderão ser informados mais do que uma vez"),
    EDDA0274("EDDA0274", "Dados de agência, conta, instituição incorporada e tipo de produto não poderão ser informados mais do que uma vez"),
    EDDA0275("EDDA0275", "Dados de agência, instituição incorporada, código de cliente ou convênio e tipo de produto não poderão ser informados mais do que uma vez"),
    EDDA0276("EDDA0276", "Dados de agência, conta, instituição incorporada, código de cliente ou convênio e tipo de produto não poderão ser informados mais do que uma vez"),
    EDDA0277("EDDA0277", "O número de conta é obrigatório para o tipo de conta informado"),
    EDDA0278("EDDA0278", "O tipo de conta é obrigatório quando o número de conta é informado"),
    EDDA0279("EDDA0279", "Situação do cliente beneficiário não permitida para uma requisição de consulta"),
    EDDA0280("EDDA0280", "Participante administrado possui adesão inativa ao canal de arquivo para a funcionalidade ADDA200"),
    EDDA0281("EDDA0281", "Participante administrado não possui adesão ao canal de arquivo para a funcionalidade ADDA200"),
    EDDA0284("EDDA0284", "O conteúdo do nome ou razão social do cliente beneficiário é inválido"),
    EDDA0285("EDDA0285", "O conteúdo do código do cliente ou convênio do cliente beneficiário é inválido"),
    EDDA0286("EDDA0286", "Dados de agência já possui cadastro ativo para o participante administrado e tipo de produto informados"),
    EDDA0287("EDDA0287", "Dados de agência, conta e código do cliente ou convênio já possui cadastro ativo para o participante administrado e tipo de produto informados"),
    EDDA0288("EDDA0288", "Dados de agência e código do cliente ou convênio já possui cadastro ativo para o participante administrado e tipo de produto informados"),
    EDDA0289("EDDA0289", "Dados de agência e instituição incorporada já possui cadastro ativo para o participante administrado e tipo de produto informados"),
    EDDA0290("EDDA0290", "Dados de agência, conta e instituição incorporada já possui cadastro ativo para o participante administrado e tipo de produto informados"),
    EDDA0291("EDDA0291", "Dados de agência, instituição incorporada e código de cliente já possui cadastro ativo para o participante administrado e tipo de produto informados"),
    EDDA0292("EDDA0292", "Dados de agência, conta, instituição incorporada e código de cliente já está ativo para o participante administrado e tipo de produto informados"),
    EDDA0293("EDDA0293", "Dados de agência já excluídos para o beneficiário, tipo de produto e participante administrado"),
    EDDA0294("EDDA0294", "Dados de agência, conta e código do cliente ou convênio já excluídos para o beneficiário, tipo de produto e participante administrado"),
    EDDA0295("EDDA0295", "Dados de agência e código do cliente ou convênio já excluídos para o beneficiário, tipo de produto e participante administrado"),
    EDDA0296("EDDA0296", "Dados de agência e instituição incorporada já excluídos para o beneficiário, tipo de produto e participante administrado"),
    EDDA0297("EDDA0297", "Dados de agência, conta e instituição incorporada já excluídos para o beneficiário, tipo de produto e participante administrado"),
    EDDA0298("EDDA0298", "Dados de agência, instituição incorporada e código de cliente já excluídos para o beneficiário, tipo de produto e participante administrado"),
    EDDA0299("EDDA0299", "Dados de agência, conta, instituição incorporada e código de cliente já excluídos para o beneficiário, tipo de produto e participante administrado"),
    EDDA0300("EDDA0300", "Dados de convênio não possui cadastro para o participante administrado informado");

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
     * Método responsável por
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
