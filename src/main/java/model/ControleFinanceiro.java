package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Classe responsável por gerenciar os lançamentos financeiros de um usuário.
 * Permite adicionar lançamentos, calcular saldos, listar receitas, despesas
 * e gerar extratos ordenados por data.
 */
public class ControleFinanceiro {
    private List<Lancamento> lancamentos;
    
    /**
     * Construtor que inicializa a lista de lançamentos financeiros.
     */
    public ControleFinanceiro() {
        lancamentos = new ArrayList<>();
    }
    
    /**
     * Adiciona um novo lançamento financeiro à lista.
     * 
     * @param lancamento objeto do tipo Lancamento a ser adicionado
     */
    public void adicionarLancamento(Lancamento lancamento) {
        lancamentos.add(lancamento);
    }
    
    /**
     * Calcula o saldo acumulado até a data atual.
     * Considera receitas e despesas lançadas até essa data.
     * 
     * @param dataAtual a data limite para cálculo do saldo
     * @return saldo acumulado até a atual
     */
    public double calcularSaldoAteHoje(LocalDate dataAtual) {
        double saldo = 0.0;
        for (Lancamento lan : lancamentos) {
            if (!lan.getData().isAfter(dataAtual)) {
                saldo += lan.getValorComSinal();
            }
        }
        return saldo;
    }
    
    /**
     * Calcula o saldo total considerando todos os lançamentos financeiros.
     * 
     * @return saldo total de receitas e despesas
     */
    public double calcularSaldoTotal() {
        double saldo = 0.0;
        for (Lancamento lan : lancamentos) {
            saldo += lan.getValorComSinal();
        }
        return saldo;
    }
    
    /**
     * Retorna uma lista contendo apenas os lançamentos classificados como receitas.
     * 
     * @return lista de lançamentos que são receitas
     */
    public List<Lancamento> listarReceitas() {
        List<Lancamento> receitas = new ArrayList<>();
        for (Lancamento lan : lancamentos) {
            if (lan.isReceita()) {
                receitas.add(lan);
            }
        }
        return receitas;
    }
    
    /**
     * Retorna uma lista contendo apenas os lançamentos classificados como despesas.
     * 
     * @return lista de lançamentos que são despesas
     */
    public List<Lancamento> listarDespesas() {
        List<Lancamento> despesas = new ArrayList<>();
        for (Lancamento lan : lancamentos) {
            if (!lan.isReceita()) {
                despesas.add(lan);
            }
        }
        return despesas;
    }
    
    /**
     * Retorna uma lista de strings representando os lançamentos ordenados por data,
     * acompanhados do saldo acumulado até cada lançamento.
     * 
     * @return lista de strings com detalhes dos lançamentos e saldo acumulado
     */
    public List<String> listarLancamentosOrdenados() {
        List<Lancamento> ordenados = new ArrayList<>(lancamentos);
        ordenados.sort(Comparator.comparing(Lancamento::getData));

        double saldo = 0.0;
        List<String> extrato = new ArrayList<>();

        for (Lancamento lan : ordenados) {
            saldo += lan.getValorComSinal();
            extrato.add(lan.toString() + " | Saldo: R$ " + saldo);
        }

        return extrato;
    }
}