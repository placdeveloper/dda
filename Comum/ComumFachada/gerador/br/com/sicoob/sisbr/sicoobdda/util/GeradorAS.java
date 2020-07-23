package br.com.sicoob.sisbr.sicoobdda.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * Classe utilizada para a criação das classes action script (.as) do flex. É necessário definir no método run() os VO's e/ou DTO's.
 * 
 * @author Rodrigo.Neri
 */
public class GeradorAS extends Gerador {

    private static final String IMPORT_REGISTER_CLASS = IMPORT + " " + "flash.net.registerClassAlias;";
    private static final String CLASS_ARRAY_COLLECTION = "mx.collections.ArrayCollection";
    private static final String CLASS_IDATETIME = "br.com.bancoob.tipos.IDateTime";

    /*
     * Extensão arquivo action script
     */
    static final String EXTENSAO = ".as";

    /*
     * TIPOS Flex
     */
    private static final String STRING = "String";
    private static final String NUMBER = "Number";
    private static final String DATA = "IDateTime";
    private static final String LISTA = "ArrayCollection";

    /*
     * TIPOS Java
     */
    private static final String[] TIPOS_TEXTO = { "String", "Character" };
    private static final String[] TIPOS_NUMERICOS = { "byte", "int", "Integer", "Short", "Long", "Float", "Decimal", "Double", "BigDecimal", "BigInteger" };
    private static final String[] TIPOS_DATA = { "DateTimeDB", "DateTime", "Date" };
    private static final String[] TIPOS_LISTA = { "List", "ArrayList" };

    private static final String FUNCTION = "function";
    private static final String VAR = "var";

    private StringBuffer imports;

    protected static final String PACOTE_VO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo";
    protected static final String PACOTE_DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto";

    protected static final String PACOTE_VO_FLEX = "br.com.sicoob.sisbr.sicoobdda.comumflex.vo";
    protected static final String PACOTE_DTO_FLEX = "br.com.sicoob.sisbr.sicoobdda.comumflex.dto";

    /**
     * Método responsável por gerar os .as
     * 
     * @throws IOException
     * @throws FileNotFoundException
     */
    public void gerar(Class<?> clazz, TipoClasseEnum tipo, ProjetoEnum projeto, List<Field> fieldsAntigos) throws FileNotFoundException, IOException {
        File pastaDestino;
        String pacoteDestino;
        String pacoteFlex;

        if (tipo == TipoClasseEnum.ENTIDADE || tipo == TipoClasseEnum.VO) {
            pacoteDestino = PACOTE_VO;
            pacoteFlex = projeto.getPacoteFlex() + PASTA_VO;
            pastaDestino = new File(projeto.getPastaFlex(), PASTA_VO);
        } else {
            pacoteDestino = PACOTE_DTO;
            pacoteFlex = projeto.getPacoteFlex() + PASTA_DTO;
            pastaDestino = new File(projeto.getPastaFlex(), PASTA_DTO);
        }

        /*
         * Criação da classe
         */
        StringBuffer texto = criarClasse(clazz, pacoteFlex, pacoteDestino, fieldsAntigos, tipo);

        salvarArquivo(pastaDestino, tratarNome(clazz.getSimpleName(), tipo), texto, EXTENSAO);
    }

    /**
     * Método responsável por gerar os .as
     * 
     * @throws IOException
     * @throws FileNotFoundException
     */
    public void gerar(Class<?> clazz, TipoClasseEnum tipo, ProjetoEnum projeto) throws FileNotFoundException, IOException {
        gerar(clazz, tipo, projeto, null);
    }

    private StringBuffer criarClasse(Class<?> clazz, String pacoteFlex, String pacoteDestino, List<Field> fieldsAntigos, TipoClasseEnum tipo) {
        StringBuffer texto = new StringBuffer("");

        Field[] fields = clazz.getDeclaredFields();

        tratarImports(fields, fieldsAntigos, tipo);

        criarCabecalho(texto, pacoteFlex, pacoteDestino, tratarNome(clazz.getSimpleName(), tipo));

        criarAtributos(texto, fields, tipo, fieldsAntigos);

        finalizarClasse(texto);

        return texto;
    }

    /**
     * Método responsável por verificar todos os atributos da classe para verificar os imports que devem ser utilizados.
     * 
     * @param fields
     * @param fieldsAntigos
     * @param tipo void
     */
    private void tratarImports(Field[] fields, List<Field> fieldsAntigos, TipoClasseEnum tipo) {
        imports = new StringBuffer();

        Map<Class<?>, String> lista = new HashMap<Class<?>, String>();

        for (Field field : fields) {
            tratarField(tipo, lista, field);
        }

        if (fieldsAntigos != null) {
            for (Field field : fieldsAntigos) {
                tratarField(tipo, lista, field);
            }
        }

        for (String valor : lista.values()) {
            imports.append(TAB);
            imports.append(IMPORT + " " + valor + ";");
            imports.append(QUEBRA_LINHA);
        }
    }

    private void tratarField(TipoClasseEnum tipo, Map<Class<?>, String> lista, Field field) {
        if (isTipoImport(field)) {
            TipoClasseEnum tipoClasse = getTipoClasse(field.getType());

            if (((tipoClasse == null || !((tipo == TipoClasseEnum.VO || tipo == TipoClasseEnum.ENTIDADE) && (tipoClasse == TipoClasseEnum.VO || tipoClasse == TipoClasseEnum.ENTIDADE))
                    && ((tipo == TipoClasseEnum.DTO && tipoClasse != TipoClasseEnum.DTO))) && !isNumero(field.getType().getSimpleName()))) {
                lista.put(field.getType(), getTipoAS(field.getType()));
            }
        }
    }

    private String getTipoAS(Class<?> classe) {
        if (isData(classe.getSimpleName())) {
            return CLASS_IDATETIME;
        } else if (isLista(classe.getSimpleName())) {
            return CLASS_ARRAY_COLLECTION;
        } else {
            return tratarClassePacote(classe);
        }
    }

    private String tratarClassePacote(Class<?> clazz) {
        if (SicoobDDAEntidade.class.isAssignableFrom(clazz)) {
            return PACOTE_VO_FLEX + "." + clazz.getSimpleName() + PASTA_VO.toUpperCase();
        } else if (BancoobVO.class.isAssignableFrom(clazz)) {
            return PACOTE_VO_FLEX + "." + clazz.getSimpleName();
        } else if (BancoobDTO.class.isAssignableFrom(clazz)) {
            return PACOTE_DTO_FLEX + "." + clazz.getSimpleName();
        } else {
            return clazz.getName();
        }
    }

    private String tratarNomeClasse(Class<?> clazz) {
        if (SicoobDDAEntidade.class.isAssignableFrom(clazz)) {
            return clazz.getSimpleName() + PASTA_VO.toUpperCase();
        } else {
            return clazz.getSimpleName();
        }
    }

    private void criarAtributos(StringBuffer texto, Field[] fields, TipoClasseEnum tipo, List<Field> fieldsAntigos) {
        StringBuffer metodos = new StringBuffer();

        for (Field field : fields) {
            if (Modifier.isFinal(field.getModifiers()) || Modifier.isTransient(field.getModifiers()) || Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            tratarAtributo(texto, tipo, metodos, field);
        }

        if (fieldsAntigos != null) {
            for (Field field : fieldsAntigos) {
                tratarAtributo(texto, tipo, metodos, field);
            }
        }

        texto.append(QUEBRA_LINHA);

        texto.append(metodos);
    }

    protected String tratarAtributo(StringBuffer texto, TipoClasseEnum tipo, StringBuffer metodos, Field field) {
        String tipoObjeto = getTipo(field.getType(), tipo);

        texto.append(TAB_DOUBLE + PRIVATE + " " + VAR + " _" + field.getName() + ":" + tipoObjeto + ";");
        texto.append(QUEBRA_LINHA);

        criarMetodos(metodos, field.getName(), tipoObjeto);
        return tipoObjeto;
    }

    private String getTipo(Class<?> classe, TipoClasseEnum tipo) {
        if (isTexto(classe.getSimpleName())) {
            return STRING;
        } else if (isNumero(classe.getSimpleName())) {
            return NUMBER;
        } else if (isData(classe.getSimpleName())) {
            return DATA;
        } else if (isLista(classe.getSimpleName())) {
            return LISTA;
        } else {
            return tratarNomeClasse(classe/* , tipo */);
        }
    }

    private boolean isTexto(String tipo) {
        return isTipoInformado(tipo, TIPOS_TEXTO);
    }

    private boolean isNumero(String tipo) {
        return isTipoInformado(tipo, TIPOS_NUMERICOS);
    }

    private boolean isData(String tipo) {
        return isTipoInformado(tipo, TIPOS_DATA);
    }

    private boolean isLista(String tipo) {
        return isTipoInformado(tipo, TIPOS_LISTA);
    }

    private boolean isTipoInformado(String tipo, String[] tipos) {
        for (String t : tipos) {
            if (tipo.equalsIgnoreCase(t)) {
                return true;
            }
        }

        return false;
    }

    private void criarMetodos(StringBuffer metodos, String nome, String tipo) {
        metodoSet(metodos, nome, tipo);
        metodoGet(metodos, nome, tipo);
    }

    private void metodoSet(StringBuffer metodos, String nome, String tipo) {
        metodos.append(TAB_DOUBLE + PUBLIC + " " + FUNCTION + " " + SET + " " + nome + "(" + nome + ":" + tipo + "):void {");
        metodos.append(QUEBRA_LINHA);
        metodos.append(TAB_TRIPLE + "this._" + nome + " = " + nome + ";");
        metodos.append(QUEBRA_LINHA);
        metodos.append(TAB_DOUBLE + "}");
        metodos.append(QUEBRA_LINHA_DOUBLE);
    }

    private void metodoGet(StringBuffer metodos, String nome, String tipo) {
        metodos.append(TAB_DOUBLE + PUBLIC + " " + FUNCTION + " " + GET + " " + nome + "():" + tipo + " {");
        metodos.append(QUEBRA_LINHA);
        metodos.append(TAB_TRIPLE + RETURN + " _" + nome + ";");
        metodos.append(QUEBRA_LINHA);
        metodos.append(TAB_DOUBLE + "}");
        metodos.append(QUEBRA_LINHA_DOUBLE);
    }

    private void criarCabecalho(StringBuffer texto, String pacoteFlex, String pacoteDestino, String nomeClasse) {
        texto.append(PACKAGE + " " + pacoteFlex + " {");
        texto.append(QUEBRA_LINHA_DOUBLE);
        adicionarImports(texto);
        texto.append(QUEBRA_LINHA);
        texto.append(TAB + "registerClassAlias(\"" + pacoteDestino + "." + nomeClasse + "\", " + nomeClasse + ");");
        texto.append(QUEBRA_LINHA_DOUBLE);
        texto.append(TAB + PUBLIC + " " + CLASS + " " + nomeClasse + " {");
        texto.append(QUEBRA_LINHA_DOUBLE);
    }

    private void adicionarImports(StringBuffer texto) {
        texto.append(imports);
        texto.append(QUEBRA_LINHA);
        texto.append(TAB + IMPORT_REGISTER_CLASS);
        texto.append(QUEBRA_LINHA);
    }

    private void finalizarClasse(StringBuffer texto) {
        texto.append(TAB + "}");
        texto.append(QUEBRA_LINHA);
        texto.append("}");
    }

}
