package br.com.sicoob.sisbr.sicoobdda.entidadeslegado;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
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
 * ObjetoUtil é responsável por
 * 
 * @author Tayrone.Oliveira
 */
public final class ObjetoUtil {

    private static final Map<Class<?>, Object> MAP_VALUES = new HashMap<Class<?>, Object>();

    private Map<Class<?>, Object> hash = new HashMap<Class<?>, Object>();

    static {
        MAP_VALUES.put(Collection.class, new HashSet<Object>());
        MAP_VALUES.put(Set.class, new HashSet<Object>());
        MAP_VALUES.put(List.class, new LinkedList<Object>());
        MAP_VALUES.put(Map.class, new HashMap<Object, Object>());
        MAP_VALUES.put(BigDecimal.class, BigDecimal.TEN);
        MAP_VALUES.put(BigInteger.class, BigInteger.TEN);
        MAP_VALUES.put(Double.class, Double.MAX_VALUE);
        MAP_VALUES.put(double.class, Double.MAX_VALUE);
        MAP_VALUES.put(Float.class, Float.MAX_VALUE);
        MAP_VALUES.put(float.class, Float.MAX_VALUE);
        MAP_VALUES.put(Long.class, Long.MAX_VALUE);
        MAP_VALUES.put(long.class, Long.MAX_VALUE);
        MAP_VALUES.put(Integer.class, Integer.MAX_VALUE);
        MAP_VALUES.put(int.class, Integer.MAX_VALUE);
        MAP_VALUES.put(Short.class, Short.MAX_VALUE);
        MAP_VALUES.put(short.class, Short.MAX_VALUE);
        MAP_VALUES.put(Boolean.class, Boolean.TRUE);
        MAP_VALUES.put(boolean.class, Boolean.TRUE);
        MAP_VALUES.put(String.class, "SGE");
        MAP_VALUES.put(String[].class, new String[0]);
        MAP_VALUES.put(Byte.class, Byte.MAX_VALUE);
        MAP_VALUES.put(byte.class, Byte.MAX_VALUE);
        MAP_VALUES.put(Byte[].class, new Byte[0]);
        MAP_VALUES.put(byte[].class, new byte[0]);
        MAP_VALUES.put(Date.class, new Date());
        MAP_VALUES.put(DateTime.class, new DateTime());
        MAP_VALUES.put(DateTimeDB.class, new DateTimeDB());
        MAP_VALUES.put(Character.class, Character.MIN_VALUE);
    }

    private ObjetoUtil() {
    }

    /**
     * @return
     */
    public static ObjetoUtil getInstance() {
        return new ObjetoUtil();
    }

    /**
     * @param clazz
     * @return
     * @throws BancoobException
     */
    public <T> T criarObjeto(Class<T> clazz) throws BancoobException {
        T t = null;

        try {
            t = newInstance(clazz);

            if (!hash.containsKey(clazz)) {
                hash.put(clazz, t);
            }

            Field fields[] = clazz.getDeclaredFields();

            for (Field field : fields) {
                if (!Modifier.isFinal(field.getModifiers())) {
                    field.setAccessible(true);
                    criarObjeto(t, field);
                }
            }
        } catch (IllegalAccessException ie) {
            error(ie);
        }

        return t;
    }

    /**
     * @param clazz
     * @return
     * @throws BancoobException
     */
    public <T> T newInstance(Class<T> clazz) throws BancoobException {
        try {
            return clazz.newInstance();
        } catch (InstantiationException ie) {
            error(ie);
        } catch (IllegalAccessException ie) {
            error(ie);
        }

        return null;
    }

    /**
     * @param objeto
     * @return
     * @throws BancoobException
     */
    private <T> T criaSubObjeto(Class<T> clazz) throws BancoobException {
        return criarObjeto(clazz);
    }

    /**
     * @param t
     * @param field
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws BancoobException
     */
    private <T> void criarObjeto(T t, Field field) throws IllegalArgumentException, IllegalAccessException, BancoobException {
        if (MAP_VALUES.containsKey(field.getType())) {
            Object object = MAP_VALUES.get(field.getType());

            if (Collection.class.isAssignableFrom(field.getType()) || Map.class.isAssignableFrom(field.getType())) {
                object = newInstance(MAP_VALUES.get(field.getType()).getClass());
            } else if (String[].class.isAssignableFrom(field.getType()) || Byte[].class.isAssignableFrom(field.getType()) || byte[].class.isAssignableFrom(field.getType())) {
                object = Array.newInstance(object.getClass().getComponentType(), 0);
            }

            field.set(t, object);
        } else if (!field.getType().isPrimitive()) {
            if (!hash.containsKey(field.getType())) {
                Object newObject = ((Enum.class.isAssignableFrom(field.getType())) ? field.getType().getEnumConstants()[BigInteger.ZERO.intValue()]
                        : criaSubObjeto(field.getType()));
                hash.put(field.getType(), newObject);
                field.set(t, newObject);
            } else {
                field.set(t, hash.get(field.getType()));
            }
        }
    }

    /**
     * @param cause
     * @throws BancoobException
     */
    private void error(Throwable cause) throws BancoobException {
        throw new BancoobException(cause);
    }
}