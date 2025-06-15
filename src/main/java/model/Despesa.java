package model;

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
    public double getValorComSinal() {
        return -getValor();
    }

    @Override
    public boolean isReceita() {
        return false;
    }

    @Override
    public String toString() {
        return String.format(
            "Valor: R$ %.2f | Data: %s | Categoria: %s",
            getValor(),
            getData(),
            categoria
        );
}
}
