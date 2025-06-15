package model;

import model.Lancamento;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ControleFinanceiro {
    private List<Lancamento> lancamentos;

    public ControleFinanceiro() {
        lancamentos = new ArrayList<>();
    }

    public void adicionarLancamento(Lancamento lancamento) {
        lancamentos.add(lancamento);
    }

    public double calcularSaldoAteHoje(LocalDate dataLimite) {
        double saldo = 0.0;
        for (Lancamento lan : lancamentos) {
            if (!lan.getData().isAfter(dataLimite)) {
                saldo += lan.getValorComSinal();
            }
        }
        return saldo;
    }

    public double calcularSaldoTotal() {
        double saldo = 0.0;
        for (Lancamento lan : lancamentos) {
            saldo += lan.getValorComSinal();
        }
        return saldo;
    }

    public List<Lancamento> listarReceitas() {
        List<Lancamento> receitas = new ArrayList<>();
        for (Lancamento lan : lancamentos) {
            if (lan.isReceita()) {
                receitas.add(lan);
            }
        }
        return receitas;
    }

    public List<Lancamento> listarDespesas() {
        List<Lancamento> despesas = new ArrayList<>();
        for (Lancamento lan : lancamentos) {
            if (!lan.isReceita()) {
                despesas.add(lan);
            }
        }
        return despesas;
    }

    public List<String> listarLancamentosOrdenados() {
        List<Lancamento> ordenados = new ArrayList<>(lancamentos);
        ordenados.sort(Comparator.comparing(Lancamento::getData));

        double saldo = 0.0;
        List<String> extrato = new ArrayList<>();

        for (Lancamento lan : ordenados) {
            saldo += lan.getValorComSinal();
            extrato.add(lan.toString() + " | Saldo até aqui: R$ " + saldo);
        }

        return extrato;
    }
}