import java.time.LocalDate;

public class Receita extends Lancamento {
    private CategoriaReceita categoria;

    public Receita(double valor, LocalDate data, CategoriaReceita categoria) {
        super(valor, data);
        this.categoria = categoria;
    }

    public CategoriaReceita getCategoria() {
        return categoria;
    }

    @Override
    public boolean isReceita() {
        return true;
    }
}
