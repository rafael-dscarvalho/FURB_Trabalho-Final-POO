import java.time.LocalDate;
import java.util.ArrayList;

public class Usuario {
    private String nome;
    private ArrayList<Lancamento> lancamentos;

    public Usuario(String nome) {
        this.nome = nome;
        this.lancamentos = new ArrayList<>();
    }

    public Usuario (String nome, ArrayList<Lancamento> lancamentos) {
        this.nome = nome;
        this.lancamentos = lancamentos;
    }

    public String getNome() {
        return nome;
    }

    public void adicionarLancamento(Lancamento lancamento) {
        lancamentos.add(lancamento);
    }

    public ArrayList<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public ArrayList<Receita> getReceitas () {
        ArrayList<Receita> receitas = new ArrayList<>();
        for (Lancamento lancamento : lancamentos) {
            if (lancamento.isReceita()) {
                receitas.add((Receita) lancamento);
            }
        }
        return receitas;
    }

    public ArrayList<Despesa> getDespesas () {
        ArrayList<Despesa> despesas = new ArrayList<>();
        for (Lancamento lancamento : lancamentos) {
            if (!lancamento.isReceita()) {
                despesas.add((Despesa) lancamento);
            }
        }
        return despesas;
    }

    public double calcularSaldo(LocalDate data) {
        double saldo = 0.0;
        for (Lancamento lancamento : lancamentos) {
            if (lancamento.getData().isBefore(data) || lancamento.getData().isEqual(data)) {
                if (lancamento.isReceita()) {
                    saldo += lancamento.getValor();
                } else {
                    saldo -= lancamento.getValor();
                }
            }
        }
        return saldo;
    }

    public double calcularSaldo() {
        return calcularSaldo(LocalDate.now());
    }
}
