import java.time.LocalDate;

public abstract class Lancamento {
    protected double valor;
    protected LocalDate data;

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

    public abstract boolean isReceita();
}
