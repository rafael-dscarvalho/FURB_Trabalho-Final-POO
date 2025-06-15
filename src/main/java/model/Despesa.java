package model;

import java.time.LocalDate;

/**
 * Representa uma despesa financeira, que é um tipo de lançamento com valor negativo
 * e uma categoria específica de despesa.
 */
public class Despesa extends Lancamento {
    private CategoriaDespesa categoria;

     /**
     * Constrói uma nova despesa com valor, data e categoria especificados.
     * 
     * @param valor     valor da despesa (positivo)
     * @param data      data da despesa
     * @param categoria categoria da despesa
     */
    public Despesa(double valor, LocalDate data, CategoriaDespesa categoria) {
        super(valor, data);
        this.categoria = categoria;
    }

     /**
     * Retorna a categoria da despesa.
     * 
     * @return categoria da despesa
     */
    public CategoriaDespesa getCategoria() {
        return categoria;
    }
    
    /**
     * Retorna o valor da despesa com sinal negativo.
     * 
     * @return valor negativo da despesa
     */
    @Override
    public double getValorComSinal() {
        return -getValor();
    }
    
    /**
     * Indica que este lançamento não é uma receita, mas sim uma despesa.
     * 
     * @return false sempre, pois esta classe representa despesas
     */
    @Override
    public boolean isReceita() {
        return false;
    }
    
    /**
     * Retorna uma representação em string formatada da despesa.
     * 
     * @return string com valor, data e categoria da despesa
     */
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
