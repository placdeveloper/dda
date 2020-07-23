package br.com.sicoob.sisbr.sicoobdda.comum.negocio.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.tipos.DateTime;

/**
 * MergeUtil é responsável por fazer o merge de vários objetos do mesmo tipo em um único, incluíndo as lista.<br>
 * <br>
 * <strong>IMPORTANTE:</strong> Para que seja feito o merge dos objetos dentro das listas é necessário que o objeto estenda a interface {@link Identificador}.<br>
 * <br>
 * <strong>EXEMPLO:</strong> <blockquote>
 * 
 * <pre>
 * public class Principal {
 * 
 *      private Long id;
 *      private String nome;
 *      private List<Item> lista;
 *      ...
 *      
 * }
 * 
 * public class Item implements Interface {
 * 
 *      private Long numIdentif;
 *      ...
 *      
 *      public String getIdentificador() {
 *          return numIdentif.toString();
 *      }
 *      
 * }
 * 
 * public static void main(String args[]) {
 *      List<Principal> lista = obterLista();
 *      Principal principal = MergeUtil.merge(lista);
 * }
 * 
 * <pre>
 * </blockquote>
 * 
 * @author Rodrigo.Neri
 */
public class MergeUtil {

    /**
     * Método responsável por fazer o merge dos objetos
     * 
     * @param lista
     * @return
     * @throws ComumException
     */
    public static <T extends Object> T merge(List<T> lista) throws ComumException {
        if (ObjectUtil.isEmpty(lista)) {
            return null;
        }

        T destino = lista.remove(0);

        if (ObjectUtil.isNull(destino)) {
            return null;
        }

        for (T origem : lista) {
            merge(destino, origem);
        }

        return destino;
    }

    @SuppressWarnings("unchecked")
    private static <T> void merge(T destino, T origem) throws ComumException {
        Field[] fieldsOrigem = origem.getClass().getDeclaredFields();
        Class<? extends Object> classeDestino = destino.getClass();

        for (Field fieldOrigem : fieldsOrigem) {
            if (Modifier.isFinal(fieldOrigem.getModifiers()) || Modifier.isStatic(fieldOrigem.getModifiers()) || Modifier.isTransient(fieldOrigem.getModifiers())) {
                continue;
            }

            try {
                Field fieldDestino = classeDestino.getDeclaredField(fieldOrigem.getName());

                fieldOrigem.setAccessible(true);
                Object objetoOrigem = fieldOrigem.get(origem);

                if (fieldDestino != null && objetoOrigem != null) {
                    fieldDestino.setAccessible(true);

                    Class<?> typeDestino = fieldDestino.getType();

                    if (typeDestino.isAssignableFrom(String.class) || typeDestino.isAssignableFrom(Character.class) || Number.class.isAssignableFrom(typeDestino)
                            || Boolean.class.isAssignableFrom(typeDestino) || typeDestino.isAssignableFrom(Date.class) || typeDestino.isAssignableFrom(DateTime.class)
                            || typeDestino.isAssignableFrom(DateTimeDB.class) || typeDestino.isAssignableFrom(XMLGregorianCalendar.class)) {
                        fieldDestino.set(destino, objetoOrigem);
                    } else if (List.class.isAssignableFrom(typeDestino)) {
                        List<?> listaOrigem = (List<?>) objetoOrigem;
                        List<Object> listaDestino = (List<Object>) fieldDestino.get(destino);

                        if (listaDestino == null) {
                            listaDestino = new ArrayList<Object>();
                        }

                        for (Object obj : listaOrigem) {
                            Type genericType = fieldDestino.getGenericType();

                            if (genericType instanceof ParameterizedType) {
                                boolean encontrou = false;

                                if (obj instanceof Identificador) {
                                    String identificador = ((Identificador) obj).getIdentificador();

                                    for (Object object : listaDestino) {
                                        if (((Identificador) object).getIdentificador().equals(identificador)) {
                                            merge(object, obj);
                                            encontrou = true;
                                            continue;
                                        }
                                    }
                                }

                                if (!encontrou) {
                                    listaDestino.add(obj);
                                }
                            }
                        }

                        // Define a lista no campo
                        fieldDestino.set(destino, listaDestino);
                    } else {
                        // Faz a instância de um novo objeto
                        Object objetoDestino = typeDestino.newInstance();
                        // Copia os atributos
                        merge(objetoOrigem, objetoDestino);
                        // Define no campo
                        fieldDestino.set(destino, objetoDestino);
                    }
                }
            } catch (IllegalArgumentException e) {
                throw new ComumException(e);
            } catch (IllegalAccessException e) {
                throw new ComumException(e);
            } catch (InstantiationException e) {
                throw new ComumException(e);
            } catch (SecurityException e) {
                throw new ComumException(e);
            } catch (NoSuchFieldException e) {
                throw new ComumException(e);
            }
        }
    }

    /**
     * Identificador é responsável por obter o identificar do objeto
     * 
     * @author Rodrigo.Neri
     */
    public interface Identificador {
        String getIdentificador();
    }

}
