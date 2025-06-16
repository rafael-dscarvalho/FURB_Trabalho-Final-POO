package model;

import java.time.LocalDate;
import java.util.List;
//
//public class Main {
//    public static void main(String[] args) {
////    ControleFinanceiro controle = new ControleFinanceiro();
////
////    controle.adicionarLancamento(new Receita(3000, LocalDate.of(2025, 6, 10), CategoriaReceita.SALARIO));
////    controle.adicionarLancamento(new Despesa(500, LocalDate.of(2025, 6, 12), CategoriaDespesa.ALIMENTACAO));
////    controle.adicionarLancamento(new Despesa(1000, LocalDate.of(2025, 6, 16), CategoriaDespesa.ALIMENTACAO));
////        controle.adicionarLancamento(new Receita(5, LocalDate.of(2025, 6, 17), CategoriaReceita.SALARIO));
////
////    System.out.println("Saldo ate hoje: " + controle.calcularSaldoAteHoje(LocalDate.now()));
////    System.out.println("Saldo total: " + controle.calcularSaldoTotal());
////
////    List<String> extrato = controle.listarLancamentosOrdenados();
////
////    for (String linha : extrato) {
////        System.out.println(linha);
////    }
////
////    
////    for (Lancamento l : controle.listarReceitas()) {
////        System.out.println(l);
////    }
////    
////    for (Lancamento l : controle.listarDespesas()) {
////        System.out.println(l);
////    }
////  
//
//    }
//}
import model.*;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ControleFinanceiro controle = new ControleFinanceiro();

        // Adiciona receita e despesa
        Receita receita = new Receita(1500.00, LocalDate.of(2025, 6, 1), CategoriaReceita.SALARIO);
        Despesa despesa = new Despesa(300.00, LocalDate.of(2025, 6, 5), CategoriaDespesa.ALIMENTACAO);

        controle.adicionarLancamento(receita);
        controle.adicionarLancamento(despesa);

        // Salva no CSV
        controle.salvarCSV("financeiro.csv");

        // Cria um novo controle pra simular carregamento do zero
        ControleFinanceiro controleNovo = new ControleFinanceiro();
        controleNovo.carregarCSV("financeiro.csv");

        // Mostra o extrato carregado
        List<String> extrato = controleNovo.listarLancamentosOrdenados();
        for (String linha : extrato) {
            System.out.println(linha);
        }
    }
}
