package model;

import java.time.LocalDate;

public abstract class Lancamento {
    
    private double valor;
    private LocalDate data;
    
    public Lancamento(double valor, LocalDate data) {
    setValor(valor);
    setData(data);
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Lancamento{" + "valor=" + valor + ", data=" + data + '}';
    }
}
