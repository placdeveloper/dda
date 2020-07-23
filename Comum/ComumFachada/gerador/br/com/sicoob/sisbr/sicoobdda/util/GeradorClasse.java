package br.com.sicoob.sisbr.sicoobdda.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

public class GeradorClasse extends Gerador {

    private static final String CLASSE_RESPONSAVEL_POR_REPRESENTAR_A_ENTIDADE = "Classe responsável por representar a entidade.";
    private static final String AUTHOR = "@author ";
    private static final String O_ATRIBUTO = "o atributo ";
    private static final String COMENTARIO_DEFINE = "Define ";
    private static final String COMENTARIO_RETURN = "@return ";

    private static final String COMENTARIO_INICIO = "/**";
    private static final String COMENTARIO_MEIO = " * ";
    private static final String COMENTARIO_FIM = " */";

    protected static final String TAB = "    ";
    protected static final String TAB_DOUBLE = TAB + TAB;
    protected static final String TAB_TRIPLE = TAB_DOUBLE + TAB;

    private static final String THIS = "this";
    private static final String VOID = "void";

    private static final Class<BancoobVO> EXTENDS_CLASS_VO = BancoobVO.class;
    private static final Class<BancoobDTO> EXTENDS_CLASS_DTO = BancoobDTO.class;

    private final static File pastaDestinoVO = new File(PASTA_DESTINO_FACHADA, PASTA_VO);
    private final static File pastaDestinoDTO = new File(PASTA_DESTINO_FACHADA, PASTA_DTO);

    private static final String EXTENSAO = ".java";

    private static final String EXTENDS = "extends";

    private static final String ENTIDADE = "entidade";
    private static final String DTO = "dto";

    private StringBuffer imports = new StringBuffer();
    private StringBuffer metodos;
    private List<Field> fieldsAntigos;

    protected static final String PACOTE_VO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo";
    protected static final String PACOTE_DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto";

    /**
     * Método responsável por gerar a classe .java
     * 
     * @param clazz
     * @param tipo void
     * @throws IOException
     * @throws FileNotFoundException
     */
    public void gerar(Class<?> clazz, TipoClasseEnum tipo, ProjetoEnum projeto) throws FileNotFoundException, IOException {
        File pastaDestino;
        String pacoteDestino;

        if (tipo == TipoClasseEnum.VO || tipo == TipoClasseEnum.ENTIDADE) {
            pacoteDestino = PACOTE_VO;
            pastaDestino = pastaDestinoVO;
        } else {
            pacoteDestino = PACOTE_DTO;
            pastaDestino = pastaDestinoDTO;
        }

        StringBuffer texto = criarClasse(clazz, pacoteDestino, tipo);

        salvarArquivo(pastaDestino, tratarNome(clazz.getSimpleName(), tipo), texto, EXTENSAO);

        new GeradorAS().gerar(clazz, tipo, projeto, fieldsAntigos);
    }

    private StringBuffer criarClasse(Class<?> clazz, String pacoteDestino, TipoClasseEnum tipo) {
        StringBuffer texto = new StringBuffer("");
        metodos = new StringBuffer();

        String nomeDestino = tratarNome(clazz.getSimpleName(), tipo);

        Field[] fields = clazz.getDeclaredFields();

        validarFields(fields, pacoteDestino, clazz.getSimpleName(), nomeDestino);

        tratarImports(fields, tipo);

        criarCabecalho(texto, pacoteDestino, clazz.getPackage().getName(), nomeDestino, clazz.getSimpleName(), tipo);

        texto.append(QUEBRA_LINHA_DOUBLE);

        criarAtributos(texto, fields);

        finalizarClasse(texto);

        return texto;
    }

    private void validarFields(Field[] fieldsNovo, String pacoteDestino, String nomeOrigem, String nomeDestino) {
        Class<?> clazz;

        try {
            clazz = Class.forName(pacoteDestino + "." + nomeDestino);

            if (clazz != null) {
                fieldsAntigos = new ArrayList<Field>();

                Field[] fieldsAtual = clazz.getDeclaredFields();

                for (Field field : fieldsAtual) {
                    if (!Modifier.isFinal(field.getModifiers()) && !Modifier.isTransient(field.getModifiers()) && !Modifier.isStatic(field.getModifiers())) {
                        boolean isIgual = false;

                        for (Field f : fieldsNovo) {
                            if (!Modifier.isFinal(f.getModifiers()) && !Modifier.isTransient(f.getModifiers()) && !Modifier.isStatic(f.getModifiers())) {
                                // Verifica se o nome é igual
                                if (f.getName().equals(field.getName())) {
                                    isIgual = true;
                                    break;
                                }
                            }
                        }

                        if (!isIgual) {
                            fieldsAntigos.add(field);
                        }
                    }
                }

                if (!fieldsAntigos.isEmpty()) {
                    JFOpcoes dialog = new JFOpcoes(fieldsAntigos, nomeDestino, nomeOrigem);
                    dialog.abrir();
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("A classe " + pacoteDestino + "." + nomeDestino + " ainda não foi criada.");
        }
    }

    private void tratarImports(Field[] fields, TipoClasseEnum tipo) {
        imports = new StringBuffer();

        imports.append(IMPORT + " " + Conversor.class.getName() + ";");
        imports.append(QUEBRA_LINHA);
        imports.append(IMPORT + " ");
        if (tipo == TipoClasseEnum.ENTIDADE || tipo == TipoClasseEnum.VO) {
            imports.append(EXTENDS_CLASS_VO.getName() + ";");
        } else {
            imports.append(EXTENDS_CLASS_DTO.getName() + ";");
        }
        imports.append(QUEBRA_LINHA);

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
            imports.append(IMPORT + " " + valor + ";");
            imports.append(QUEBRA_LINHA);
        }
    }

    private void tratarField(TipoClasseEnum tipo, Map<Class<?>, String> lista, Field field) {
        if (isTipoImport(field)) {
            TipoClasseEnum tipoClasse = getTipoClasse(field.getType());

            if ((tipoClasse == null
                    || !((tipo == TipoClasseEnum.VO || tipo == TipoClasseEnum.ENTIDADE) && (tipoClasse == TipoClasseEnum.VO || tipoClasse == TipoClasseEnum.ENTIDADE)) || ((tipo == TipoClasseEnum.DTO && tipoClasse != TipoClasseEnum.DTO)))) {
                lista.put(field.getType(), tratarClassePacote(field.getType()));

                if (field.getType().isAssignableFrom(List.class)) {
                    ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();

                    if (parameterizedType.getActualTypeArguments().length > 0) {
                        Class<?> clazz = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                        if (SicoobDDAEntidade.class.isAssignableFrom(clazz)) {
                            lista.put(clazz, tratarClassePacote(clazz));
                        }
                    }
                }
            }
        }
    }

    private String tratarClassePacote(Class<?> clazz) {
        if (SicoobDDAEntidade.class.isAssignableFrom(clazz)) {
            return PACOTE_VO + "." + clazz.getSimpleName() + PASTA_VO.toUpperCase();
        } else if (DateTimeDB.class.isAssignableFrom(clazz)) {
            return DateTime.class.getName();
        } else {
            return clazz.getName();
        }
    }

    private String tratarNomeClasse(Field field) {
        if (SicoobDDAEntidade.class.isAssignableFrom(field.getType())) {
            return field.getType().getSimpleName() + PASTA_VO.toUpperCase();
        } else if (DateTimeDB.class.isAssignableFrom(field.getType())) {
            return DateTime.class.getSimpleName();
        } else if (!field.getType().isAssignableFrom(Object.class) && field.getType().isAssignableFrom(List.class)) {
            ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();

            if (parameterizedType.getActualTypeArguments().length > 0) {
                Class<?> clazz = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                if (SicoobDDAEntidade.class.isAssignableFrom(clazz)) {
                    return List.class.getSimpleName() + "<" + clazz.getSimpleName() + PASTA_VO.toUpperCase() + ">";
                } else if (BancoobDto.class.isAssignableFrom(clazz)) {
                    return List.class.getSimpleName() + "<" + clazz.getSimpleName().replace("Dto", "") + PASTA_DTO.toUpperCase() + ">";
                }
            }

            return List.class.getSimpleName();
        } else {
            return field.getType().getSimpleName();
        }
    }

    private void criarCabecalho(StringBuffer texto, String pacoteDestino, String pacoteOrigem, String nomeClasseDestino, String nomeClasseOrigem, TipoClasseEnum tipo) {
        texto.append(PACKAGE + " " + pacoteDestino + ";");
        texto.append(QUEBRA_LINHA_DOUBLE);
        texto.append(imports);
        texto.append(QUEBRA_LINHA);

        // Comentários
        texto.append(COMENTARIO_INICIO);
        texto.append(QUEBRA_LINHA);
        texto.append(COMENTARIO_MEIO);
        texto.append(CLASSE_RESPONSAVEL_POR_REPRESENTAR_A_ENTIDADE);
        texto.append(QUEBRA_LINHA);
        texto.append(COMENTARIO_MEIO);
        texto.append(QUEBRA_LINHA);
        texto.append(COMENTARIO_MEIO);
        texto.append(AUTHOR);
        texto.append(System.getProperty("user.name"));
        texto.append(QUEBRA_LINHA);
        texto.append(COMENTARIO_FIM);
        texto.append(QUEBRA_LINHA);

        criarConversor(texto, pacoteOrigem, nomeClasseOrigem, tipo);
        texto.append(QUEBRA_LINHA);

        texto.append(PUBLIC + " " + CLASS + " " + nomeClasseDestino + " " + EXTENDS + " ");
        if (tipo == TipoClasseEnum.VO || tipo == TipoClasseEnum.ENTIDADE) {
            texto.append(EXTENDS_CLASS_VO.getSimpleName());
        } else {
            texto.append(EXTENDS_CLASS_DTO.getSimpleName());
        }
        texto.append(" {");
    }

    private void criarConversor(StringBuffer texto, String pacoteOrigem, String nomeClasseOrigem, TipoClasseEnum tipo) {
        texto.append("@" + Conversor.class.getSimpleName() + "(");
        switch (tipo) {
        case ENTIDADE:
        case VO:
            texto.append(ENTIDADE);
            break;
        case DTO:
            texto.append(DTO);
        }
        texto.append(" = \"" + pacoteOrigem + "." + nomeClasseOrigem + "\")");
    }

    private void criarAtributos(StringBuffer texto, Field[] fields) {
        for (Field field : fields) {
            if (Modifier.isFinal(field.getModifiers()) || Modifier.isTransient(field.getModifiers()) || Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            tratarAtributos(texto, field);
        }

        if (fieldsAntigos != null) {
            for (Field field : fieldsAntigos) {
                tratarAtributos(texto, field);
            }
        }

        texto.append(QUEBRA_LINHA);

        texto.append(metodos);
    }

    private void tratarAtributos(StringBuffer texto, Field field) {
        String tipoClasse = tratarNomeClasse(field);

        texto.append(TAB);
        texto.append(PRIVATE + " " + tipoClasse + " " + field.getName() + ";");
        texto.append(QUEBRA_LINHA);

        criarMetodos(field, tipoClasse);
    }

    private void criarMetodos(Field field, String tipoClasse) {
        criarGet(field, metodos, tipoClasse);
        metodos.append(QUEBRA_LINHA_DOUBLE);
        criarSet(field, metodos, tipoClasse);
        metodos.append(QUEBRA_LINHA_DOUBLE);
    }

    private void criarGet(Field field, StringBuffer metodos, String tipoClasse) {
        metodos.append(TAB + COMENTARIO_INICIO + QUEBRA_LINHA);
        metodos.append(TAB + COMENTARIO_MEIO + COMENTARIO_RETURN + O_ATRIBUTO + field.getName() + QUEBRA_LINHA);
        metodos.append(TAB + COMENTARIO_FIM + QUEBRA_LINHA);

        metodos.append(TAB);
        metodos.append(PUBLIC + " " + tipoClasse + " " + GET + primeiraMaiuscula(field.getName()) + "() {");
        metodos.append(QUEBRA_LINHA);
        metodos.append(TAB_DOUBLE);
        metodos.append(RETURN + " " + field.getName() + ";");
        metodos.append(QUEBRA_LINHA);
        metodos.append(TAB);
        metodos.append("}");
    }

    private void criarSet(Field field, StringBuffer metodos, String tipoClasse) {
        metodos.append(TAB + COMENTARIO_INICIO + QUEBRA_LINHA);
        metodos.append(TAB + COMENTARIO_MEIO + COMENTARIO_DEFINE + O_ATRIBUTO + field.getName() + QUEBRA_LINHA);
        metodos.append(TAB + COMENTARIO_FIM + QUEBRA_LINHA);

        metodos.append(TAB);
        metodos.append(PUBLIC + " " + VOID + " " + SET + primeiraMaiuscula(field.getName()) + "(");
        metodos.append(tipoClasse + " " + field.getName() + ") {");
        metodos.append(QUEBRA_LINHA);
        metodos.append(TAB_DOUBLE);
        metodos.append(THIS + "." + field.getName() + " = " + field.getName() + ";");
        metodos.append(QUEBRA_LINHA);
        metodos.append(TAB);
        metodos.append("}");
    }

    private void finalizarClasse(StringBuffer texto) {
        texto.append("}");
        texto.append(QUEBRA_LINHA);
    }

}
