package model;

import java.time.LocalDate;
import java.time.Year; 

/**
 * Classe abstrata que representa um lançamento financeiro, que pode ser uma
 * receita ou despesa, contendo valor e data.
 */
public abstract class Lancamento {

    private double valor;
    private LocalDate data;

    /**
     * Constrói um lançamento financeiro com valor e data especificados.
     *
     * @param valor valor do lançamento (deve ser positivo)
     * @param data data do lançamento
     */
    public Lancamento(double valor, LocalDate data) {
        this.valor = valor;
        this.data = data;
    }

    public void setValor(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor inválido, valor deve ser maior que zero.");
        }
        this.valor = valor;
    }

    /**
     * Retorna o valor do lançamento (sempre positivo).
     *
     * @return valor do lançamento
     */
    public double getValor() {
        return valor;
    }

    public void setData(LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException("Data não pode ser nula.");
        }

        int ano = data.getYear();
        int anoAtual = Year.now().getValue();

        if (ano < 1970 || ano > anoAtual) {
            throw new IllegalArgumentException("Ano da receita deve ser entre 1970 e " + anoAtual + ".");
        }

        // Aqui você pode adicionar se quiser que a data não seja futura:
        if (data.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data da receita não pode ser no futuro.");
        }

        this.data = data;
    }

    /**
     * Retorna a data do lançamento.
     *
     * @return data do lançamento
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Retorna o valor do lançamento com o sinal correto, considerando se é
     * receita (positivo) ou despesa (negativo).
     *
     * @return valor com sinal adequado
     */
    public abstract double getValorComSinal();

    /**
     * Indica se o lançamento é uma receita.
     *
     * @return true se for receita, false se for despesa
     */
    public abstract boolean isReceita();
}
