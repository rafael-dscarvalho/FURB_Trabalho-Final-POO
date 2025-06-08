import java.time.LocalDate;

public class Despesa extends Lancamento {
    private CategoriaDespesa categoria;

    public Despesa(double valor, LocalDate data, CategoriaDespesa categoria) {
        super(valor, data);
        this.categoria = categoria;
    }

    public CategoriaDespesa getCategoria() {
        return categoria;
    }

    @Override
    public boolean isReceita() {
        return false;
    }
}
