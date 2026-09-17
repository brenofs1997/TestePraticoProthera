package com.teste.service;

import com.teste.model.Funcionario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioServiceTest {

    private FuncionarioService service;

    @BeforeEach
    void setUp() {
        service = new FuncionarioService();
    }

    @Test
    @DisplayName("Deve remover funcionário por nome com sucesso")
    void deveRemoverFuncionarioPorNome() {
        int tamanhoInicial = service.getFuncionarios().size();

        List<Funcionario> resultado = service.removerPorNome("João");

        assertEquals(tamanhoInicial - 1, resultado.size());
        assertTrue(resultado.stream().noneMatch(f -> f.getNome().equalsIgnoreCase("João")));
    }

    @Test
    @DisplayName("Deve aplicar o reajuste salarial de 10% corretamente")
    void deveAplicarReajusteSalarial() {
        BigDecimal salarioAntigo = service.getFuncionarios().get(0).getSalario();

        BigDecimal reajusteEsperado = salarioAntigo
                .multiply(new BigDecimal("1.10"))
                .setScale(2, java.math.RoundingMode.HALF_UP);

        service.aplicarReajuste(new BigDecimal("0.10"));

        BigDecimal novoSalario = service.getFuncionarios().get(0).getSalario()
                .setScale(2, java.math.RoundingMode.HALF_UP);

        assertEquals(0, reajusteEsperado.compareTo(novoSalario),
                "O salário após o reajuste de 10% deve ser igual ao valor esperado");
    }

    @Test
    @DisplayName("Deve agrupar funcionários por função corretamente")
    void deveAgruparFuncionariosPorFuncao() {
        Map<String, List<Funcionario>> agrupados = service.agruparPorFuncao();

        assertNotNull(agrupados);
        assertFalse(agrupados.isEmpty());
        assertTrue(agrupados.containsKey("Operador") || agrupados.containsKey("Gerente"));
    }

    @Test
    @DisplayName("Deve filtrar aniversariantes dos meses especificados")
    void deveBuscarAniversariantesDosMeses() {
        List<Funcionario> aniversariantes = service.buscarAniversariantes(10, 12);

        assertNotNull(aniversariantes);
        assertTrue(aniversariantes.stream().allMatch(f -> {
            int mes = f.getNascimento().getMonthValue();
            return mes == 10 || mes == 12;
        }));
    }

    @Test
    @DisplayName("Deve retornar os funcionários ordenados alfabeticamente")
    void deveObterEmOrdemAlfabetica() {
        List<Funcionario> ordenados = service.obterEmOrdemAlfabetica();

        for (int i = 0; i < ordenados.size() - 1; i++) {
            String nomeAtual = ordenados.get(i).getNome();
            String proximoNome = ordenados.get(i + 1).getNome();
            assertTrue(nomeAtual.compareToIgnoreCase(proximoNome) <= 0);
        }
    }

    @Test
    @DisplayName("Deve calcular o valor total dos salários formatado em moeda")
    void deveCalcularTotalSalariosFormatado() {
        String totalFormatado = service.calcularTotalSalariosFormatado();

        assertNotNull(totalFormatado);
        assertTrue(totalFormatado.contains("R$") || totalFormatado.matches(".*\\d+.*"));
    }
}