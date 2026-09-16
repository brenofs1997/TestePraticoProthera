package com.teste.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.time.temporal.ChronoUnit;

public class Funcionario extends Pessoa{
    private BigDecimal salario;
    private String funcao ;
    private final DateTimeFormatter fmtData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final NumberFormat fmtMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public Funcionario() {
        super();
    }

    public Funcionario(String nome, LocalDate nascimento, BigDecimal salario, String funcao) {
        super(nome, nascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void reajusteSalarial(BigDecimal porcentagemAumento ){
         this.salario = (this.salario.multiply(porcentagemAumento))
                             .add(this.salario)
                             .setScale(2, java.math.RoundingMode.HALF_UP);
    }

    public String aniversarioFormatado() {
        return getNascimento().format(fmtData);
    }
    public Long calculaIdade() {
        return ChronoUnit.YEARS.between(getNascimento(), LocalDate.now());
    }
    @Override
    public String toString() {


        return String.format("Nome: %-10s | Nascimento: %s | Salário: %-12s | Função: %s",
                getNome(),
                getNascimento().format(fmtData),
                fmtMoeda.format(getSalario()),
                getFuncao());
    }

}
