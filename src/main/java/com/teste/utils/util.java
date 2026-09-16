package com.teste.utils;

import com.teste.model.Funcionario;
import com.teste.model.Pessoa;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class util {
    private static final NumberFormat fmtMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public static List<Funcionario>  popular() {

      return new ArrayList<>(Arrays.asList(
              new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
              new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
              new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
              new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
              new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
              new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
              new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
              new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
              new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
              new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
      ));
    }
    public static List<Funcionario> removeFuncionario(List<Funcionario> funcionarios, String nome ){

        return  funcionarios.stream()
                .filter( funcionario -> !funcionario.getNome().startsWith(nome)).toList();

    }

    public static List<Funcionario> reajusteSalarial(List<Funcionario> funcionarios, BigDecimal aumento ){

        return  funcionarios.stream()
                .peek( funcionario -> funcionario.reajusteSalarial(aumento)).toList();

    }

    public static String  formataValorMoeda(BigDecimal valor){

        return fmtMoeda.format(valor);
    }

    public static BigDecimal valorTotalSalarios(List<Funcionario> funcionarios){
        return funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, java.math.RoundingMode.HALF_UP);
    }

    public static Map<String, List<Funcionario>> funcionariosPorFuncao(List<Funcionario> funcionarios ){

        return funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

    }

    public static List<Funcionario> filtraMesAniversarioFuncionario(List<Funcionario> funcionarios, int... meses ){
        List<Integer> mesesDesejados = List.of(
                Arrays.stream(meses).boxed().toArray(Integer[]::new)
        );
        return  funcionarios.stream()
                .filter(
                        f -> (f.getNascimento() != null && mesesDesejados.contains(f.getNascimento().getMonthValue()) )
                ).toList();

    }
    public static Optional<Funcionario> obterMaisVelho(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getNascimento));
    }
    public static  List<Funcionario> funcionariosPorOrdemAlfabetica(List<Funcionario> funcionarios ){

        return funcionarios.stream().sorted(Comparator.comparing(Funcionario::getNome)).toList();

    }
}
