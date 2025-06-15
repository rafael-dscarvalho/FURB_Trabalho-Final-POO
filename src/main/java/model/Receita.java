package model;

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
    public double getValorComSinal() {
        return getValor(); 
    }

    @Override
    public boolean isReceita() {
        return true;
    }

    @Override
    public String toString() {
        return "Receita{" +
                "valor=" + getValor() +
                ", data=" + getData() +
                ", categoria=" + categoria +
                '}';
    }
}
