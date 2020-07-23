package br.com.sicoob.sisbr.sicoobdda.util;

import java.io.FileNotFoundException;
import java.io.IOException;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ControleRateioDto;
import br.com.sicoob.sisbr.sicoobdda.util.Gerador.ProjetoEnum;
import br.com.sicoob.sisbr.sicoobdda.util.Gerador.TipoClasseEnum;

/**
 * GeradorMain é responsável por gerar as classes java a partir de entidade, vo ou dto e também o action script da camada de apresentação. <br>
 * 
 * <b>Utilização</b>: no método <i>main</i> deve ser informado as classes (ejb e/ou fachada) para geração dos VO's e/ou DTO's da fachada e flex.
 * 
 * @author Rodrigo.Neri
 */
public class GeradorMain {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Entidades, Vo's e Dto's (EJB e Fachada)
        Class<?>[] classesProjetoComum = {

        // EventoRateioDto.class
        ControleRateioDto.class,

        };

        new GeradorMain().run(classesProjetoComum, ProjetoEnum.COMUM);

        System.exit(0);
    }

    public void run(Class<?>[] classes, ProjetoEnum projeto) throws FileNotFoundException, IOException {
        for (Class<?> clazz : classes) {
            Class<?> superclass = clazz.getSuperclass();

            if (superclass.isAssignableFrom(Gerador.classeEntidade)) {
                gerarVO(clazz, TipoClasseEnum.ENTIDADE, projeto);
            } else if (superclass.isAssignableFrom(Gerador.classeDto)) {
                gerarDTO(clazz, TipoClasseEnum.DTO, projeto);
            } else if (superclass.isAssignableFrom(Gerador.classeVo)) {
                gerarVO(clazz, TipoClasseEnum.VO, projeto);
            } else if (superclass.isAssignableFrom(Gerador.classeDTO)) {
                gerarDTOActionScript(clazz, TipoClasseEnum.DTO, projeto);
            } else if (superclass.isAssignableFrom(Gerador.classeVO)) {
                gerarVOActionScript(clazz, TipoClasseEnum.VO, projeto);
            }
        }
    }

    private void gerarVO(Class<?> clazz, TipoClasseEnum tipo, ProjetoEnum projeto) throws FileNotFoundException, IOException {
        new GeradorClasse().gerar(clazz, tipo, projeto);
    }

    private void gerarDTO(Class<?> clazz, TipoClasseEnum tipo, ProjetoEnum projeto) throws FileNotFoundException, IOException {
        new GeradorClasse().gerar(clazz, tipo, projeto);
    }

    private void gerarVOActionScript(Class<?> clazz, TipoClasseEnum tipo, ProjetoEnum projeto) throws FileNotFoundException, IOException {
        new GeradorAS().gerar(clazz, tipo, projeto);
    }

    private void gerarDTOActionScript(Class<?> clazz, TipoClasseEnum tipo, ProjetoEnum projeto) throws FileNotFoundException, IOException {
        new GeradorAS().gerar(clazz, tipo, projeto);
    }

}
