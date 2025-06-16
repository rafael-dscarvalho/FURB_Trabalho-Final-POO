package model;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ControleFinanceiroTest {
    
    public ControleFinanceiroTest() {
    }

    @Test
    public void adicionarLancamento_Receita() {
        ControleFinanceiro controle = new ControleFinanceiro();
        Receita r = new Receita(1000.0, LocalDate.of(2025,6,15), CategoriaReceita.SALARIO);
        controle.adicionarLancamento(r);
        List<Lancamento> receitas = controle.listarReceitas();
        assertEquals(1, receitas.size());
        assertEquals(1000.0, receitas.get(0).getValor());
    }

    @Test
    public void adicionarLancamento_Despesa() {
        ControleFinanceiro controle = new ControleFinanceiro();
        Despesa d = new Despesa(200.0, LocalDate.of(2025,6,15), CategoriaDespesa.ALIMENTACAO);
        controle.adicionarLancamento(d);
        List<Lancamento> despesas = controle.listarDespesas();
        assertEquals(1, despesas.size());
        assertEquals(200.0, despesas.get(0).getValor());
    }

    @Test
    public void calcularSaldoAteHoje_SemLancamentos() {
        ControleFinanceiro controle = new ControleFinanceiro();
        double saldo = controle.calcularSaldoAteHoje(LocalDate.of(2025,6,15));
        assertEquals(0.0, saldo);
    }

    @Test
    public void calcularSaldoAteHoje_ComLancamentos() {
        ControleFinanceiro controle = new ControleFinanceiro();
        controle.adicionarLancamento(new Receita(1000.0, LocalDate.of(2025,6,10), CategoriaReceita.SALARIO));
        controle.adicionarLancamento(new Despesa(200.0, LocalDate.of(2025,6,12), CategoriaDespesa.ALIMENTACAO));
        double saldo = controle.calcularSaldoAteHoje(LocalDate.of(2025,6,15));
        assertEquals(800.0, saldo);
    }

    @Test
    public void calcularSaldoAteHoje_DesconsideraFuturos() {
        ControleFinanceiro controle = new ControleFinanceiro();
        controle.adicionarLancamento(new Receita(1000.0, LocalDate.of(2025,6,20), CategoriaReceita.SALARIO));
        double saldo = controle.calcularSaldoAteHoje(LocalDate.of(2025,6,15));
        assertEquals(0.0, saldo);
    }

    @Test
    public void calcularSaldoTotal_ComLancamentos() {
        ControleFinanceiro controle = new ControleFinanceiro();
        controle.adicionarLancamento(new Receita(1500.0, LocalDate.of(2025,6,1), CategoriaReceita.SALARIO));
        controle.adicionarLancamento(new Despesa(500.0, LocalDate.of(2025,6,10), CategoriaDespesa.ALIMENTACAO));
        double saldo = controle.calcularSaldoTotal();
        assertEquals(1000.0, saldo);
    }

    @Test
    public void listarReceitas_RetornaApenasReceitas() {
        ControleFinanceiro controle = new ControleFinanceiro();
        controle.adicionarLancamento(new Receita(1000.0, LocalDate.of(2025,6,15), CategoriaReceita.SALARIO));
        controle.adicionarLancamento(new Despesa(200.0, LocalDate.of(2025,6,15), CategoriaDespesa.ALIMENTACAO));
        List<Lancamento> receitas = controle.listarReceitas();
        assertEquals(1, receitas.size());
        assertTrue(receitas.get(0).isReceita());
    }

    @Test
    public void listarDespesas_RetornaApenasDespesas() {
        ControleFinanceiro controle = new ControleFinanceiro();
        controle.adicionarLancamento(new Receita(1000.0, LocalDate.of(2025,6,15), CategoriaReceita.SALARIO));
        controle.adicionarLancamento(new Despesa(200.0, LocalDate.of(2025,6,15), CategoriaDespesa.ALIMENTACAO));
        List<Lancamento> despesas = controle.listarDespesas();
        assertEquals(1, despesas.size());
        assertFalse(despesas.get(0).isReceita());
    }

    @Test
    public void listarLancamentosOrdenados_VerificaOrdemESaldo() {
        ControleFinanceiro controle = new ControleFinanceiro();
        controle.adicionarLancamento(new Receita(1000.0, LocalDate.of(2025,6,10), CategoriaReceita.SALARIO));
        controle.adicionarLancamento(new Despesa(200.0, LocalDate.of(2025,6,12), CategoriaDespesa.ALIMENTACAO));
        controle.adicionarLancamento(new Receita(300.0, LocalDate.of(2025,6,15), CategoriaReceita.FERIAS));
        List<String> extrato = controle.listarLancamentosOrdenados();
        assertEquals(3, extrato.size());
        assertTrue(extrato.get(0).contains("Saldo: R$ 1000.0"));
        assertTrue(extrato.get(1).contains("Saldo: R$ 800.0"));
        assertTrue(extrato.get(2).contains("Saldo: R$ 1100.0"));
    }

    @Test
    public void adicionarLancamento_ValorNegativo() {
        ControleFinanceiro controle = new ControleFinanceiro();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            controle.adicionarLancamento(new Despesa(-100.0, LocalDate.of(2025,6,15), CategoriaDespesa.ALIMENTACAO));
        });
    }
}
    
