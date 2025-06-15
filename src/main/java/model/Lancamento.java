package model;

import java.time.LocalDate;

/**
 * Classe abstrata que representa um lançamento financeiro, que pode ser
 * uma receita ou despesa, contendo valor e data.
 */
public abstract class Lancamento {
    private double valor;
    private LocalDate data;

    /**
     * Constrói um lançamento financeiro com valor e data especificados.
     * 
     * @param valor valor do lançamento (deve ser positivo)
     * @param data  data do lançamento
     */
    public Lancamento(double valor, LocalDate data) {
        if (valor >= 0) {
            this.valor = valor;
        } else {
            throw new IllegalArgumentException("Valor não pode ser negativo.");
        }
        this.data = data;
    }
    
    /**
     * Retorna o valor do lançamento (sempre positivo).
     * 
     * @return valor do lançamento
     */
    public double getValor() {
        return valor;
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
     * Retorna o valor do lançamento com o sinal correto,
     * considerando se é receita (positivo) ou despesa (negativo).
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
