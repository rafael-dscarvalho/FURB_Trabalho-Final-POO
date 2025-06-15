package model;

import java.time.LocalDate;

public abstract class Lancamento {
    private double valor;
    private LocalDate data;

    public Lancamento(double valor, LocalDate data) {
        this.valor = valor;
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public abstract double getValorComSinal();

    public abstract boolean isReceita();

    @Override
    public String toString() {
        return "Lancamento{" +
                "valor=" + valor +
                ", data=" + data +
                '}';
    }
}
