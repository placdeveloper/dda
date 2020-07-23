package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.tipos.DateTime;

/**
 * StandardGettersSettersUtil é responsável por
 * 
 * @author Tayrone.Oliveira
 */
public class StandardGettersSettersUtil {

    private static HashMap<Class<?>, Object> MAP_VALUES = new HashMap<Class<?>, Object>();

    static {
        MAP_VALUES.put(Collection.class, new HashSet<Object>());
        MAP_VALUES.put(Set.class, new HashSet<Object>());
        MAP_VALUES.put(List.class, new LinkedList<Object>());
        MAP_VALUES.put(Map.class, new HashMap<Object, Object>());
        MAP_VALUES.put(BigDecimal.class, BigDecimal.TEN);
        MAP_VALUES.put(BigInteger.class, BigInteger.TEN);
        MAP_VALUES.put(Double.class, Double.MIN_VALUE);
        MAP_VALUES.put(double.class, Double.MIN_VALUE);
        MAP_VALUES.put(Float.class, Float.MIN_VALUE);
        MAP_VALUES.put(float.class, Float.MIN_VALUE);
        MAP_VALUES.put(Long.class, Long.MIN_VALUE);
        MAP_VALUES.put(long.class, Long.MIN_VALUE);
        MAP_VALUES.put(Integer.class, Integer.MIN_VALUE);
        MAP_VALUES.put(int.class, Integer.MIN_VALUE);
        MAP_VALUES.put(Short.class, Short.MIN_VALUE);
        MAP_VALUES.put(short.class, Short.MIN_VALUE);
        MAP_VALUES.put(Boolean.class, Boolean.TRUE);
        MAP_VALUES.put(boolean.class, Boolean.TRUE);
        MAP_VALUES.put(String.class, "SGE");
        MAP_VALUES.put(String[].class, new String[0]);
        MAP_VALUES.put(Byte.class, Byte.MIN_VALUE);
        MAP_VALUES.put(byte.class, Byte.MIN_VALUE);
        MAP_VALUES.put(Byte[].class, new Byte[0]);
        MAP_VALUES.put(byte[].class, new byte[0]);
        MAP_VALUES.put(Date.class, new Date());
        MAP_VALUES.put(DateTime.class, new DateTime());
        MAP_VALUES.put(DateTimeDB.class, new DateTimeDB());
        MAP_VALUES.put(ResultSet.class, null);
        MAP_VALUES.put(Character.class, Character.MIN_VALUE);
    }

    private ObjetoUtil objetoUtil = ObjetoUtil.getInstance();

    /**
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    private <T> T getDefaultValue(final Class<T> clazz) throws BancoobException {
        Object object = null;

        try {
            if (MAP_VALUES.containsKey(clazz)) {
                object = MAP_VALUES.get(clazz);
            } else {
                object = ((Enum.class.isAssignableFrom(clazz)) ? clazz.getEnumConstants()[BigInteger.ZERO.intValue()] : clazz.newInstance());
            }
        } catch (Exception e) {
            throw new BancoobException(e);
        }

        return (T) object;
    }

    /**
     * @param clazz
     */
    public void testAll(Class<?> clazz, boolean isNull) throws BancoobException {

        String methodName = null;
        String className = clazz.getCanonicalName();
        if (!Modifier.isStatic(clazz.getModifiers())) {
            try {
                Object object = ((Enum.class.isAssignableFrom(clazz)) ? clazz.getEnumConstants()[BigInteger.ZERO.intValue()] : ((!isNull) ? objetoUtil.criarObjeto(clazz)
                        : objetoUtil.newInstance(clazz)));
                Method[] methods = object.getClass().getDeclaredMethods();
                Class<?>[] listParameterTypes = null;
                List<Object> listParameter = null;

                for (Method method : methods) {
                    methodName = method.getName();
                    try {
                        if (!Modifier.isVolatile(method.getModifiers())) {
                            method.setAccessible(Boolean.TRUE);
                            listParameterTypes = method.getParameterTypes();

                            if (listParameterTypes != null && listParameterTypes.length > BigInteger.ZERO.intValue()) {
                                listParameter = new LinkedList<Object>();

                                for (Class<?> clazzParameter : listParameterTypes) {
                                    if (Enum.class.isAssignableFrom(clazz) && String.class.isAssignableFrom(clazzParameter)) {
                                        listParameter.add(clazz.getEnumConstants()[BigInteger.ZERO.intValue()].toString());
                                    } else {
                                        listParameter.add((isNull && !clazzParameter.isPrimitive() && !Enum.class.isAssignableFrom(clazzParameter)) ? null
                                                : getDefaultValue(clazzParameter));
                                    }
                                }

                                method.invoke(object, listParameter.toArray());
                            } else {
                                method.invoke(object);
                            }
                        }

                    } catch (Exception e) {
                        System.out.println("Erro na Classe " + className + " no método: " + methodName + ". Erro: " + e.getMessage());
                        continue;
                    }
                }
            } catch (Exception e) {
            }
        }
    }
}
