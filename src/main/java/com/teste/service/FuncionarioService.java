package com.teste.service;

import com.teste.model.Funcionario;
import com.teste.utils.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class FuncionarioService {
    List<Funcionario> funcionarios;

    public FuncionarioService(){
        this.funcionarios = util.popular();
    }
    public List<Funcionario> removerPorNome(String nome){
        return this.funcionarios = util.removeFuncionario(this.funcionarios, nome);
    }

    public List<Funcionario> aplicarReajuste(BigDecimal porcentagem){
        return this.funcionarios = util.reajusteSalarial(this.funcionarios, porcentagem);
    }

    public Map<String, List<Funcionario>> agruparPorFuncao(){
        return util.funcionariosPorFuncao(this.funcionarios);
    }

    public List<Funcionario> buscarAniversariantes(int... meses) {
        return util.filtraMesAniversarioFuncionario(this.funcionarios, meses);
    }

    public void exibirFuncionarioMaisVelho() {
        util.obterMaisVelho(this.funcionarios).ifPresentOrElse(
                f -> System.out.println("Funcionário mais velho: " + f.nomeIdade()),
                () -> System.out.println("Nenhum funcionário encontrado.")
        );
    }

    public List<Funcionario> obterEmOrdemAlfabetica() {
        return util.funcionariosPorOrdemAlfabetica(this.funcionarios);
    }

    public String calcularTotalSalariosFormatado() {
        BigDecimal total = util.valorTotalSalarios(this.funcionarios);
        return util.formataValorMoeda(total);
    }

    public void exibirSalariosMinimos(BigDecimal salarioMinimo) {
        System.out.println("Quant. de Salários Mínimos ganhos por Funcionários:");
        this.funcionarios.forEach(f ->
                System.out.println("Nome: " + f.getNome() +
                        " | Quant. de Salários Mínimos: " + f.calculaSalariosMin(salarioMinimo))
        );
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void execute(){


        System.out.println("Lista de Funcionarios:");
        this.funcionarios.forEach(System.out::println);

        this.removerPorNome("João");
        System.out.println("João foi removido da Lista:");
        this.getFuncionarios().forEach(System.out::println);

        System.out.println("\nLista de Funcionarios com Reajuste de 10%:");
        this.aplicarReajuste(new BigDecimal("0.10")).forEach(System.out::println);

        System.out.println("\nLista de Funcionarios por função:");
        Map<String, List<Funcionario>> funcionariosPorFuncao = this.agruparPorFuncao();
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("\n" + funcao.toUpperCase());
            lista.forEach(f -> System.out.println("  - " + f.getNome()));
        });

        System.out.println("\nFuncionários que fazem aniversário no mês 10 e 12.:");
        this.buscarAniversariantes(10, 12).forEach(
                f -> System.out.println("Nome: " + f.getNome() + " | Nascimento: " + f.aniversarioFormatado())
        );

        System.out.println();
        this.exibirFuncionarioMaisVelho();

        System.out.println("\nFuncionários por Ordem Alfabetica:");
        this.obterEmOrdemAlfabetica().forEach(System.out::println);

        System.out.println("\nTotal dos Salarios dos Funcionarios: " + this.calcularTotalSalariosFormatado());

        System.out.println();
        this.exibirSalariosMinimos(new BigDecimal("1212.00"));

    }
}
