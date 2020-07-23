package br.com.sicoob.sisbr.sicoobdda.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ControleRateioDto;
import br.com.sicoob.tipos.DateTime;

public class GeradorUtil {

    private static final String LETRAS = "abcdefghijklmnopqrstuvwxyz 0123456789";

    public static void main(String[] args) throws Exception {
        List<ControleRateioDto> lista = GeradorUtil.criarLista(ControleRateioDto.class, 10);

        System.out.println(lista.size());
    }

    public static <T extends Object> List<T> criarLista(Class<T> classe, int qtd) throws Exception {
        if (classe == null) {
            return null;
        }

        List<T> list = new ArrayList<T>(qtd);

        for (int i = 0; i < qtd; i++) {
            T t = criarObjeto(classe);

            list.add(t);
        }

        return list;
    }

    public static <T> T criarObjeto(Class<T> classe) throws Exception {
        T t = (T) classe.newInstance();
        Field[] fields = t.getClass().getDeclaredFields();

        for (Field f : fields) {
            if (Modifier.isFinal(f.getModifiers())) {
                continue;
            }

            Object value = null;

            if (!(f.getType().isAssignableFrom(Object.class)) && f.getType().isAssignableFrom(List.class)) {
                Type genericType = f.getGenericType();

                if (genericType instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) genericType;

                    if (parameterizedType.getActualTypeArguments().length > 0) {
                        Class<?> clazz = (Class<?>) parameterizedType.getActualTypeArguments()[0];

                        value = criarLista(clazz, 10);
                    }
                }
            }

            if (value == null) {
                value = obterValor(f.getType());
            }

            f.setAccessible(true);
            f.set(t, value);
        }
        return t;
    }

    private static Object obterValor(Class<?> type) throws Exception {
        if (type.isAssignableFrom(Long.class) || long.class == type) {
            return (long) (Math.random() * 100);
        } else if (type.isAssignableFrom(Integer.class) || int.class == type) {
            return (int) (Math.random() * 100);
        } else if (type.isAssignableFrom(Double.class) || double.class == type) {
            return (double) (Math.random() * 100);
        } else if (type.isAssignableFrom(Float.class) || float.class == type) {
            return (float) (Math.random() * 100);
        } else if (type.isAssignableFrom(Boolean.class) || boolean.class == type) {
            return Math.random() > .5 ? true : false;
        } else if (type.isAssignableFrom(String.class)) {
            return obterTexto();
        } else if (type.isAssignableFrom(BigDecimal.class)) {
            return new BigDecimal(Math.random() * 100);
        } else if (type.isAssignableFrom(Date.class)) {
            return new Date();
        } else if (type.isAssignableFrom(DateTime.class)) {
            return new DateTime();
        } else if (type.isAssignableFrom(DateTimeDB.class)) {
            return new DateTimeDB();
            // } else if (type.isAssignableFrom(List.class)) {
            // for (TypeVariable<?> x : type.getTypeParameters()) {
            // System.out.println(x);
            // System.out.println(x.getGenericDeclaration());
            // System.out.println(x.getName());
            // }
            //
            // System.out.println(type.getComponentType());
            //
            // return criarLista(type.getTypeParameters()[0].getClass(), 10);
        }

        return null; // FIXME criarObjeto(type);
    }

    private static String obterTexto() {
        StringBuffer sb = new StringBuffer();

        int tamanho = (int) (Math.random() * 15) + 5;
        int length = LETRAS.length();

        for (int i = 0; i < tamanho; i++) {
            sb.append(LETRAS.charAt((int) ((Math.random() * 100) % length)));
        }

        return sb.toString();
    }

}
