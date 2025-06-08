import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("João");

        Receita receita1 = new Receita(1500.00, LocalDate.of(2023, 1, 15), CategoriaReceita.SALARIO);
        Receita receita2 = new Receita(500.00, LocalDate.of(2023, 2, 10), CategoriaReceita.DECIMO_TERCEIRO);

        Despesa despesa1 = new Despesa(200.00, LocalDate.of(2023, 1, 20), CategoriaDespesa.ALIMENTACAO);
        Despesa despesa2 = new Despesa(300.00, LocalDate.of(2023, 2, 5), CategoriaDespesa.TRANSPORTE);

        usuario.adicionarLancamento(receita1);
        usuario.adicionarLancamento(receita2);

        usuario.adicionarLancamento(despesa1);
        usuario.adicionarLancamento(despesa2);

        System.out.println("Receitas de " + usuario.getNome() + ":");
        for (Receita receita : usuario.getReceitas()) {
            System.out.println(" - " + receita.getValor() + " em " + receita.getData() + " (" + receita.getCategoria() + ")");
        }

        System.out.println("Despesas de " + usuario.getNome() + ":");
        for (Despesa despesa : usuario.getDespesas()) {
            System.out.println(" - " + despesa.getValor() + " em " + despesa.getData() + " (" + despesa.getCategoria() + ")");
        }

        LocalDate dataConsulta = LocalDate.of(2023, 2, 15);
        double saldo = usuario.calcularSaldo(dataConsulta);

        System.out.println("Saldo em " + dataConsulta + ": " + saldo);
    }
}