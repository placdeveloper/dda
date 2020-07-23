package br.com.sicoob.sisbr.sicoobdda.comumfachada.infraestrutura.anotacao.conversor;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LazyInitializationException;
import org.hibernate.collection.internal.AbstractPersistentCollection;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * Conversor Cobranca Bancaria
 * 
 * @author kaio.rocha
 * 
 */
public class ConversorVO {

    private static final String SERIAL_VERSION_UID = "serialVersionUID";
    private static ConversorVO conversor;
    private static final SicoobLoggerPadrao LOG = SicoobLoggerPadrao.getInstance(ConversorVO.class);
    /**
     * Armazena os objetos convertidos<br>
     * <ul>
     * key: objeto a ser convertido
     * </ul>
     * <ul>
     * value: objeto convertido
     * </ul>
     */
    private static Map<Object, Object> mapa;

    /**
     * Construtor
     */
    protected ConversorVO() {
    }

    /**
     * Retorna instância do conversor.
     * 
     * @return {@link ConversorVO}
     */
    public static ConversorVO getInstance() {
        if (conversor == null) {
            conversor = new ConversorVO();
        }

        if (mapa == null) {
            mapa = new HashMap<Object, Object>();
        } else {
            mapa.clear();
        }

        return conversor;
    }

    /**
     * Método auxiliar para conversão de listas.
     * 
     * @param listaOrigem
     * @return List<Object>
     */
    public List<?> converter(List<?> listaOrigem) throws ComumException {
        if (listaOrigem == null) {
            return null;
        }

        List<Object> resultado = new ArrayList<Object>();

        for (Object origem : listaOrigem) {
            resultado.add(converter(origem));
        }

        return resultado;
    }

    /**
     * Converte VO em Entidade e Vice versa.
     * 
     * @param origem
     * @return
     */
    public Object converter(Object origem) throws ComumException {
        if (origem == null) {
            return null;
        }

        return converterRef(origem, Boolean.FALSE);
    }
    
    public Object converter(Object origem, boolean removerReferenciaCiclica) throws ComumException {
        if (origem == null) {
            return null;
        }

        return converterRef(origem, removerReferenciaCiclica);
    }


    /**
     * Converte VO em Entidade e Vice versa.
     * 
     * @param origem
     * @return
     */
    private Object converterRef(Object origem, boolean removerReferenciaCiclica) throws ComumException {
        // Verifica se já existe o objeto a ser convertido no mapa
        if (mapa.containsKey(origem)) {
            // retorna o objeto já convertido
            return removerReferenciaCiclica ? null : mapa.get(origem);
        }

        Object retorno = null;
        try {
            try {
                retorno = recuperarInstanciaAlvo(origem);
            } catch (ClassNotFoundException e) {
                throw new ComumException(e);
            } catch (InstantiationException e) {
                throw new ComumException(e);
            }

            // adiciona o objeto convertido
            mapa.put(origem, retorno);

            Field[] camposOrigem = origem.getClass().getDeclaredFields();
            for (Field campoOrigem : camposOrigem) {
                if (Modifier.isFinal(campoOrigem.getModifiers())) {
                    continue;
                }

                try {
                    Boolean controle = Boolean.FALSE;
                    campoOrigem.setAccessible(Boolean.TRUE);
                    Object valor = campoOrigem.get(origem);

                    if (valor == null) {
                        continue;
                    }

                    // Verifica se é uma lista
                    if (!(campoOrigem.getType().isAssignableFrom(Object.class)) && campoOrigem.getType().isAssignableFrom(List.class)) {
                        Type genericType = campoOrigem.getGenericType();

                        if (genericType instanceof ParameterizedType) {
                            ParameterizedType parameterizedType = (ParameterizedType) genericType;

                            if (parameterizedType.getActualTypeArguments().length > 0) {
                                Class<?> clazz = (Class<?>) parameterizedType.getActualTypeArguments()[0];

                                // Se o tipo da lista for diferente de uma entidade do cobrança a conversão não é aplicada.
                                if (!SicoobDDAEntidade.class.isAssignableFrom(clazz) && !BancoobVo.class.isAssignableFrom(clazz) && !BancoobDto.class.isAssignableFrom(clazz)
                                        && !BancoobVO.class.isAssignableFrom(clazz) && !BancoobDTO.class.isAssignableFrom(clazz)) {
                                    copiarOutrosTipos(retorno, campoOrigem, valor, controle);
                                    continue;
                                }
                            }
                        }
                    }

                    if (validarRegrasPreenchimentoVO(origem, campoOrigem, valor)) {
                        controle = converterObjeto(retorno, campoOrigem, valor, controle, removerReferenciaCiclica);
                        controle = converterDateParaDateTime(retorno, campoOrigem, valor, controle);
                        controle = converterValorNumericoEmString(retorno, campoOrigem, valor, controle);
                        controle = converterLista(origem, retorno, campoOrigem, controle,removerReferenciaCiclica);
                        controle = copiarOutrosTipos(retorno, campoOrigem, valor, controle);
                    } else if (validarRegrasPreenchimentoEntidade(origem, campoOrigem, valor)) {
                        controle = converterObjeto(retorno, campoOrigem, valor, controle, removerReferenciaCiclica);
                        controle = converterDateTimeParaDateTimeDB(retorno, campoOrigem, valor, controle);
                        controle = converterStringEmValorNumerico(retorno, campoOrigem, valor, controle);
                        controle = converterLista(origem, retorno, campoOrigem, controle,removerReferenciaCiclica);
                        controle = copiarOutrosTipos(retorno, campoOrigem, valor, controle);
                    } else if (validarRegrasPreenchimentoDTO(origem, campoOrigem, valor)) {
                        controle = converterObjeto(retorno, campoOrigem, valor, controle, removerReferenciaCiclica);
                        controle = converterDateParaDateTime(retorno, campoOrigem, valor, controle);
                        controle = converterValorNumericoEmString(retorno, campoOrigem, valor, controle);
                        controle = converterLista(origem, retorno, campoOrigem, controle, removerReferenciaCiclica);
                        controle = copiarOutrosTipos(retorno, campoOrigem, valor, controle);
                    } else if (validarRegrasPreenchimentoDto(origem, campoOrigem, valor)) {
                        controle = converterObjeto(retorno, campoOrigem, valor, controle, removerReferenciaCiclica);
                        controle = converterDateTimeParaDateTimeDB(retorno, campoOrigem, valor, controle);
                        controle = converterStringEmValorNumerico(retorno, campoOrigem, valor, controle);
                        controle = converterLista(origem, retorno, campoOrigem, controle, removerReferenciaCiclica);
                        controle = copiarOutrosTipos(retorno, campoOrigem, valor, controle);
                    }
                } catch (NoSuchFieldException e) {
                    SicoobLoggerPadrao.getInstance(getClass()).info("Método não encontrado ignorado.");
                } catch (LazyInitializationException e) {
                    SicoobLoggerPadrao.getInstance(getClass()).info("Lista lazy ignorada.");
                } catch (IllegalAccessException e) {
                    SicoobLoggerPadrao.getInstance(getClass()).info("Acesso não permitido.");
                } catch (BancoobException e) {
                    SicoobLoggerPadrao.getInstance(getClass()).info("Erro ao converter Lista");
                }
            }
        } catch (BancoobException e) {
            throw new ComumException(e);
        } catch (IllegalAccessException e) {
            throw new ComumException(e);
        }

        return retorno;
    }

    /**
     * Verifica se o valor passado como parametro está anotado e executa o conversor.
     * 
     * @param retorno
     * @param campoOrigem
     * @param valor
     * @param controle
     * @return Boolean
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws ComumException
     */
    private Boolean converterObjeto(Object retorno, Field campoOrigem, Object valor, Boolean controle, boolean removerReferenciaCiclica) throws SecurityException, NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException, ComumException {
        Boolean resultado = controle;
        if (isAnotado(valor) && !resultado) {
            Field field = retorno.getClass().getDeclaredField(campoOrigem.getName());
            field.setAccessible(Boolean.TRUE);
            field.set(retorno, converterRef(valor, removerReferenciaCiclica));
            resultado = Boolean.TRUE;
        }
        return resultado;
    }

    /**
     * Não executar conversão. Apenas copia os valores do objeto de origem para o destino.
     * 
     * @param retorno
     * @param campoOrigem
     * @param valor
     * @param controle
     * @return Boolean
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private Boolean copiarOutrosTipos(Object retorno, Field campoOrigem, Object valor, Boolean controle) throws NoSuchFieldException, IllegalAccessException {
        Boolean resultado = controle;
        if (!resultado) {
            Field field = retorno.getClass().getDeclaredField(campoOrigem.getName());
            field.setAccessible(Boolean.TRUE);
            field.set(retorno, valor);
            resultado = Boolean.TRUE;
        }
        return resultado;
    }

    /**
     * Converte listas de objetos
     * 
     * @param origem
     * @param retorno
     * @param campoOrigem
     * @param controle
     * @return Boolean
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Boolean converterLista(Object origem, Object retorno, Field campoOrigem, Boolean controle, boolean removerReferenciaCiclica) throws IllegalAccessException, NoSuchFieldException,
            LazyInitializationException, BancoobException {
        Boolean resultado = controle;
        if (campoOrigem.getType().equals(List.class) && !resultado) {
            Field field = retorno.getClass().getDeclaredField(campoOrigem.getName());
            field.setAccessible(Boolean.TRUE);
            List lista = null;

            boolean instanceOfAbstractPersistent = campoOrigem.get(origem) instanceof AbstractPersistentCollection;

            // Verifica se o campo NÃO é do tipo AbstractPersistentCollection, ou, se for desse tipo, verifica se já foi inicializado
            if (!instanceOfAbstractPersistent || (instanceOfAbstractPersistent && ((AbstractPersistentCollection) campoOrigem.get(origem)).wasInitialized())) {
                lista = new ArrayList();

                for (Object obj : ((List) campoOrigem.get(origem))) {
                    lista.add(converterRef(obj, removerReferenciaCiclica));
                }
            }

            field.set(retorno, lista);
            resultado = Boolean.TRUE;
        }
        return resultado;
    }

    /**
     * Converte valor numerico em String
     * 
     * @param retorno {@link Object}
     * @param campoOrigem {@link Field}
     * @param valor {@link Object}
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    private Boolean converterValorNumericoEmString(Object retorno, Field campoOrigem, Object valor, Boolean controle) throws IllegalAccessException, NoSuchFieldException {
        Boolean resultado = controle;
        if (isSubClass(campoOrigem.getType(), String.class) && !resultado) {
            Field field = retorno.getClass().getDeclaredField(campoOrigem.getName());
            field.setAccessible(Boolean.TRUE);
            field.set(retorno, valor.toString());
            resultado = Boolean.TRUE;
        }
        return resultado;
    }

    /**
     * Converte String em valor numerico.
     * 
     * @param retorno {@link Object}
     * @param campoOrigem {@link Field}
     * @param valor {@link Object}
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    private Boolean converterStringEmValorNumerico(Object retorno, Field campoOrigem, Object valor, Boolean controle) throws IllegalAccessException, NoSuchFieldException {
        Field campoAlvo = retorno.getClass().getDeclaredField(campoOrigem.getName());
        Boolean resultado = controle;
        if (isSubClass(campoAlvo.getType(), Number.class) && !resultado) {
            campoAlvo.setAccessible(Boolean.TRUE);
            if (campoAlvo.getType().equals(Byte.class)) {
                campoAlvo.set(retorno, Byte.valueOf(valor.toString()));
            } else if (campoAlvo.getType().equals(Short.class)) {
                campoAlvo.set(retorno, Short.valueOf(valor.toString()));
            } else if (campoAlvo.getType().equals(Integer.class)) {
                campoAlvo.set(retorno, Integer.valueOf(valor.toString()));
            } else if (campoAlvo.getType().equals(Long.class)) {
                campoAlvo.set(retorno, Long.valueOf(valor.toString()));
            } else if (campoAlvo.getType().equals(Float.class)) {
                campoAlvo.set(retorno, Float.valueOf(valor.toString()));
            } else if (campoAlvo.getType().equals(Double.class)) {
                campoAlvo.set(retorno, Double.valueOf(valor.toString()));
            } else if (campoAlvo.getType().equals(BigInteger.class)) {
                campoAlvo.set(retorno, new BigInteger(valor.toString()));
            } else if (campoAlvo.getType().equals(BigDecimal.class)) {
                campoAlvo.set(retorno, new BigDecimal(valor.toString()));
            }
            resultado = Boolean.TRUE;
        }
        return resultado;
    }

    /**
     * Verifica se o campo do VO atende aos requisitos para preenchimento do campo da Entidade.
     * 
     * @param origem {@link Object}
     * @param campo {@link Field}
     * @param valor {@link Object}
     * @return {@link Boolean}
     */
    private Boolean validarRegrasPreenchimentoEntidade(Object origem, Field campo, Object valor) {
        return (isAnotado(origem) && !isEntidade(origem) && (valor != null) && !campo.getName().contains(SERIAL_VERSION_UID) && !isTransient(campo));
    }

    private Boolean validarRegrasPreenchimentoDto(Object origem, Field campo, Object valor) {
        return (isAnotado(origem) && !isDto(origem) && (valor != null) && !campo.getName().contains(SERIAL_VERSION_UID) && !isTransient(campo));
    }

    private Boolean validarRegrasPreenchimentoDTO(Object origem, Field campo, Object valor) {
        return (isAnotado(origem) && !isDTO(origem) && (valor != null) && !campo.getName().contains(SERIAL_VERSION_UID) && !isTransient(campo));
    }

    /**
     * Verifica se o campo da Entidade atende aos requisitos para preenchimento do campo do VO.
     * 
     * @param origem {@link Object}
     * @param campo {@link Field}
     * @param valor {@link Object}
     * @return {@link Boolean}
     */
    private Boolean validarRegrasPreenchimentoVO(Object origem, Field campo, Object valor) {
        return (isAnotado(origem) && isEntidade(origem) && (valor != null) && !campo.getName().contains(SERIAL_VERSION_UID) && !isTransient(campo));
    }

    private boolean isTransient(Field campo) {
        return Modifier.isTransient(campo.getModifiers());
    }

    /**
     * Verifica se o objeto parametro possui a anotação @Covnersor.
     * 
     * @param origem {@link Object}
     * @return {@link Boolean}
     */
    private Boolean isAnotado(Object origem) {
        return origem.getClass().isAnnotationPresent(Conversor.class);
    }

    /**
     * Verifica se o objeto parametro é uma entidade.
     * 
     * @param origem {@link Object}
     * @return {@link Boolean}
     */
    private Boolean isEntidade(Object origem) {
        return (origem.getClass().getAnnotation(Conversor.class).vo() != null && !origem.getClass().getAnnotation(Conversor.class).vo().equals(""));
    }

    private Boolean isDto(Object origem) {
        return (origem.getClass().getAnnotation(Conversor.class).DTO() != null && !origem.getClass().getAnnotation(Conversor.class).DTO().equals(""));
    }

    private Boolean isDTO(Object origem) {
        return (origem.getClass().getAnnotation(Conversor.class).dto() != null && !origem.getClass().getAnnotation(Conversor.class).dto().equals(""));
    }

    /**
     * Converte o tipo {@link java.util.Date} em {@link br.com.sicoob.tipos.DateTime}
     * 
     * @param retorno {@link Object}
     * @param campoOrigem {@link Field}
     * @param valor {@link Object}
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    private Boolean converterDateParaDateTime(Object retorno, Field campoOrigem, Object valor, Boolean controle) throws IllegalAccessException, NoSuchFieldException {
        Boolean resultado = controle;
        if (campoOrigem.getType().equals(java.util.Date.class) && !resultado) {
            DateTime data = new DateTime(((Date) valor).getTime());
            Field field = retorno.getClass().getDeclaredField(campoOrigem.getName());
            field.setAccessible(Boolean.TRUE);
            field.set(retorno, data);
            resultado = Boolean.TRUE;
        }
        return resultado;
    }

    /**
     * Converte o tipo {@link br.com.sicoob.tipos.DateTime} em {@link DateTimeDB}.
     * 
     * @param retorno {@link Object}
     * @param campoOrigem {@link Field}
     * @param valor {@link Object}
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    private Boolean converterDateTimeParaDateTimeDB(Object retorno, Field campoOrigem, Object valor, Boolean controle) throws IllegalAccessException, NoSuchFieldException {
        Boolean resultado = controle;
        if (campoOrigem.getType().equals(DateTime.class) && !resultado) {
            DateTimeDB data = new DateTimeDB(((DateTime) valor).getTime());
            Field field = retorno.getClass().getDeclaredField(campoOrigem.getName());
            field.setAccessible(Boolean.TRUE);
            field.set(retorno, data);
            resultado = Boolean.TRUE;
        }
        return resultado;
    }

    /**
     * Verifica se o objeto passado como parametro herda de determinada classe.
     * 
     * @param objeto {@link Object}
     * @param pai {@link Class}
     * @return {@link Boolean}
     */
    private Boolean isSubClass(Class<?> filho, Class<?> pai) {
        Class<?> filhoAux = filho;
        Class<?> superclassComp = filhoAux.getSuperclass();
        while (superclassComp != null) {
            if (superclassComp.equals(pai)) {
                return Boolean.TRUE;
            }
            filhoAux = superclassComp;
            superclassComp = filhoAux.getSuperclass();
        }
        return Boolean.FALSE;
    }

    /**
     * Recupera a instância do objeto alvo da conversão.
     * 
     * @param origem {@link Object}
     * @return {@link Object}
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private Object recuperarInstanciaAlvo(Object origem) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Object alvo = null;
        if (isAnotado(origem) && isEntidade(origem)) {
            alvo = Class.forName(origem.getClass().getAnnotation(Conversor.class).vo()).newInstance();
        } else if (isAnotado(origem) && isDto(origem)) {
            alvo = Class.forName(origem.getClass().getAnnotation(Conversor.class).DTO()).newInstance();
        } else if (isAnotado(origem) && isDTO(origem)) {
            alvo = Class.forName(origem.getClass().getAnnotation(Conversor.class).dto()).newInstance();
        } else if (isAnotado(origem) && !isEntidade(origem)) {
            alvo = Class.forName(origem.getClass().getAnnotation(Conversor.class).entidade()).newInstance();
        }
        return alvo;
    }
}
