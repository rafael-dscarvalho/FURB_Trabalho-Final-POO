package model;

import java.time.LocalDate;
import model.CategoriaReceita;
import model.Lancamento;

public class Receita extends Lancamento {
    private CategoriaReceita categoria;

    public Receita(double valor, LocalDate data, CategoriaReceita categoria) {
        super(valor, data);
        setCategoria(categoria);
    }

    public CategoriaReceita getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaReceita categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Receita{" +
                "valor=" + getValor() +
                ", data=" + getData() +
                ", categoria=" + getCategoria() +
                '}';
    }
}
