package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    
     // -------- CSV --------

    public void salvarCSV(String caminho) {
        try (PrintWriter out = new PrintWriter(new FileWriter(caminho))) {
            for (Lancamento l : lancamentos) {
                if (l.isReceita()) {
                    Receita r = (Receita) l;
                    out.println("RECEITA;" + r.toCSV());
                } else {
                    Despesa d = (Despesa) l;
                    out.println("DESPESA;" + d.toCSV());
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar CSV: " + e.getMessage());
        }
    }

    public void carregarCSV(String caminho) {
        lancamentos.clear(); // limpa antes de carregar

        try (BufferedReader in = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = in.readLine()) != null) {
                String[] partes = linha.split(";", 2);
                String tipo = partes[0];
                String dados = partes[1];

                if (tipo.equals("RECEITA")) {
                    Receita r = Receita.fromCSV(dados);
                    lancamentos.add(r);
                } else if (tipo.equals("DESPESA")) {
                    Despesa d = Despesa.fromCSV(dados);
                    lancamentos.add(d);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar CSV: " + e.getMessage());
        }
    }
}