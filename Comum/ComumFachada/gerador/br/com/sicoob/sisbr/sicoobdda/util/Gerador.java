package br.com.sicoob.sisbr.sicoobdda.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

public class Gerador {

    private static final String WORKSPACE = "C:\\SisbrIDE-4.5\\workspace_DESENV\\SicoobDDA\\";

    private static final String PACOTE_FLEX[] = {
    // COMUM
    "br.com.sicoob.sisbr.sicoobdda.comumflex." };

    private static final String PASTA_DESTINO_FLEX[] = {
    // COMUM
    WORKSPACE + "Comum\\ComumFlex\\src\\br\\com\\sicoob\\sisbr\\sicoobdda\\comumflex\\" };

    protected static final String PASTA_DESTINO_FACHADA = WORKSPACE + "Comum\\ComumFachada\\src\\br\\com\\sicoob\\sisbr\\sicoobdda\\comumfachada\\";

    protected static final String PASTA_VO = "vo";
    protected static final String PASTA_DTO = "dto";

    // EJB
    protected static final Class<BancoobDto> classeDto = BancoobDto.class;
    protected static final Class<BancoobVo> classeVo = BancoobVo.class;
    protected static final Class<SicoobDDAEntidade> classeEntidade = SicoobDDAEntidade.class;

    // Fachada
    protected static final Class<BancoobDTO> classeDTO = BancoobDTO.class;
    protected static final Class<BancoobVO> classeVO = BancoobVO.class;

    // Formatação
    protected static final String QUEBRA_LINHA = "\n";
    protected static final String QUEBRA_LINHA_DOUBLE = QUEBRA_LINHA + QUEBRA_LINHA;
    protected static final String TAB = "\t";
    protected static final String TAB_DOUBLE = TAB + TAB;
    protected static final String TAB_TRIPLE = TAB_DOUBLE + TAB;

    protected static final String CLASS = "class";
    protected static final String PACKAGE = "package";
    protected static final String PUBLIC = "public";
    protected static final String PRIVATE = "private";
    protected static final String IMPORT = "import";

    protected static final String SET = "set";
    protected static final String GET = "get";
    protected static final String RETURN = "return";

    protected String tratarNome(String nome, TipoClasseEnum tipo) {
        switch (tipo) {
        case ENTIDADE:
            return nome + PASTA_VO.toUpperCase();
        case VO:
            return nome.replace("Vo", PASTA_VO.toUpperCase());
        case DTO:
            return nome.replace("Dto", PASTA_DTO.toUpperCase());
        }

        return nome;
    }

    protected void salvarArquivo(File pastaDestino, String nomeArquivo, StringBuffer texto, String extensao) throws FileNotFoundException, IOException {
        FileOutputStream fos = null;

        try {
            if (!pastaDestino.exists()) {
                pastaDestino.mkdirs();
            }

            File arquivo = new File(pastaDestino, nomeArquivo + extensao);
            if (arquivo.exists()) {
                arquivo.delete();
            }

            fos = new FileOutputStream(arquivo);

            fos.write(texto.toString().getBytes());
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }
    }

    protected String primeiraMaiuscula(String nome) {
        String novo = nome;

        if (nome != null && nome.length() > 0) {
            novo = nome.substring(0, 1).toUpperCase();
            novo += nome.substring(1, nome.length());
        }

        return novo;
    }

    protected boolean isTipoImport(Field field) {
        return !field.getType().isPrimitive() && !field.getType().getName().startsWith("java.lang") && !Modifier.isFinal(field.getModifiers())
                && !Modifier.isTransient(field.getModifiers()) && !Modifier.isStatic(field.getModifiers());
    }

    protected TipoClasseEnum getTipoClasse(Class<?> clazz) {
        Class<?> superclass = clazz.getSuperclass();

        if (superclass == null) {
            return null;
        }

        if (superclass.isAssignableFrom(classeEntidade)) {
            return TipoClasseEnum.ENTIDADE;
        } else if (superclass.isAssignableFrom(classeDto)) {
            return TipoClasseEnum.DTO;
        } else if (superclass.isAssignableFrom(classeVo)) {
            return TipoClasseEnum.VO;
        } else if (superclass.isAssignableFrom(classeDTO)) {
            return TipoClasseEnum.DTO;
        } else if (superclass.isAssignableFrom(classeVO)) {
            return TipoClasseEnum.VO;
        } else {
            return null;
        }
    }

    public enum TipoClasseEnum {
        VO,
        DTO,
        ENTIDADE;
    }

    public enum ProjetoEnum {
        COMUM(PASTA_DESTINO_FLEX[0], PACOTE_FLEX[0]);

        private final String pastaFlex;
        private final String pacoteFlex;

        private ProjetoEnum(String pastaFlex, String pacoteFlex) {
            this.pastaFlex = pastaFlex;
            this.pacoteFlex = pacoteFlex;
        }

        public String getPastaFlex() {
            return pastaFlex;
        }

        public String getPacoteFlex() {
            return pacoteFlex;
        }

    }

}
