package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos;

import javax.xml.bind.annotation.XmlRegistry;

import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.TagErroComplexType;

@XmlRegistry
public class ObjectFactory {

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package:
     * br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos
     * 
     */
    public ObjectFactory() {
    }

    public BCARQComplexType createBCARQComplexType() {
        return new BCARQComplexType();
    }

    public TagErroComplexType createTagErroComplexType() {
        return new TagErroComplexType();
    }

    public ArqRecebimento createArqRecebimento() {
        return new ArqRecebimento();
    }

}
