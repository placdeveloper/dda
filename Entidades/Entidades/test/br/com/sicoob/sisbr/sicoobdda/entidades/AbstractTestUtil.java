package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;

/**
 * AbstractTestUtil é responsável por
 * 
 * @author tayrone.oliveira
 */
public abstract class AbstractTestUtil {

    private static final Integer UM_NEGATIVO = -1;

    /**
	 * 
	 */
    @Test
    public void testAll() throws BancoobException {
        try {
            Class<?>[] classes = getClasses("br.com.sicoob.sisbr.sicoobdda.entidades");

            if (classes != null) {
                for (Class<?> classe : classes) {
                    StandardGettersSettersUtil standardGettersSettersUtil = new StandardGettersSettersUtil();

                    if ((!Modifier.isAbstract(classe.getModifiers()) && !Modifier.isFinal(classe.getModifiers())) || Enum.class.isAssignableFrom(classe)) {
                        standardGettersSettersUtil.testAll(classe, Boolean.TRUE);
                        standardGettersSettersUtil.testAll(classe, Boolean.FALSE);
                    }
                }
            }
        } catch (Exception e) {
            throw new BancoobException(e);
        }
    }

    /**
     * @param packageName
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private static Class<?>[] getClasses(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new LinkedList<File>();

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();

            if (resource.getFile().indexOf("test-classes") == UM_NEGATIVO) {
                dirs.add(new File(resource.getFile()));
            }
        }

        List<Class<?>> classes = new LinkedList<Class<?>>();

        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }

        return classes.toArray(new Class[classes.size()]);
    }

    /**
     * @param directory
     * @param packageName
     * @return
     * @throws ClassNotFoundException
     */
    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new LinkedList<Class<?>>();

        if (!directory.exists()) {
            return classes;
        }

        File[] files = directory.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }

        return classes;
    }
}
