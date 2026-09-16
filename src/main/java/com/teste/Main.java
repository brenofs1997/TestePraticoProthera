package com.teste;

import com.teste.model.Funcionario;
import com.teste.utils.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        System.out.println("Lista de Funcionarios:");
        List<Funcionario> funcionarios = util.popular();
        String nome = "João";

        funcionarios = util.removeFuncionario(funcionarios, nome);
        funcionarios.forEach(System.out::println);

        funcionarios = util.reajusteSalarial(funcionarios, new BigDecimal("0.10"));
        System.out.println("Lista de Funcionarios com Reajuste de 10%:");
        funcionarios.forEach(System.out::println);

        Map<String, List<Funcionario>> funcionariosPorFuncao  = util.funcionariosPorFuncao(funcionarios);
        System.out.println("Lista de Funcionarios por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("\n" + funcao.toUpperCase() );
            lista.forEach(f -> System.out.println("  - " + f.getNome()));
        });

        List<Funcionario> funcionariosPorMes = util.filtraMesAniversarioFuncionario(funcionarios, 10, 12);
        System.out.println("Funcionários que fazem aniversário no mês 10 e 12.:");
        funcionariosPorMes.forEach(
                f -> System.out.println("Nome: " + f.getNome() +" | Nascimento:"+f.aniversarioFormatado()));

         util.obterMaisVelho(funcionarios).ifPresentOrElse(f -> System.out.println("Funcionário mais velho: "
                + f.nomeIdade()),
                 () -> System.out.println("Nenhum funcionário encontrado.") );

        List<Funcionario> funcionariosOrdem = util.funcionariosPorOrdemAlfabetica(funcionarios);
        System.out.println("Funcionários por Ordem Alfabetica:");
        funcionariosOrdem.forEach(System.out::println);

        System.out.println("Total dos Salarios dos Funcionarios: "+util.formataValorMoeda(util.valorTotalSalarios(funcionarios)));

        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        System.out.println("Quant. de Salários Mínimos ganhos por Funcionários:");
        funcionarios.forEach(f ->
                System.out.println("Nome: " + f.getNome() +
                        " | Quant. de Salários Mínimos: " + f.calculaSalariosMin(salarioMinimo))
        );

    }

}