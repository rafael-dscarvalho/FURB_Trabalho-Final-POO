package model;

import java.time.LocalDate;
import model.CategoriaDespesa;

public class Despesa extends Lancamento {
    private CategoriaDespesa categoria;

    public Despesa(double valor, LocalDate data, CategoriaDespesa categoria) {
        super(valor, data);
        setCategoria(categoria);
    }

    public CategoriaDespesa getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDespesa categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Despesa{" +
                "valor=" + getValor() +
                ", data=" + getData() +
                ", categoria=" + getCategoria() +
                '}';
    }
}
