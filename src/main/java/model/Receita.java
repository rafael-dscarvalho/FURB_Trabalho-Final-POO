package model;

import java.time.LocalDate;

/**
 * Representa uma receita financeira, que é um tipo de lançamento com valor positivo
 * e uma categoria específica de receita.
 */
public class Receita extends Lancamento {
    private CategoriaReceita categoria;

     /**
     * Constrói uma nova receita com valor, data e categoria especificados.
     * 
     * @param valor     valor da receita (positivo)
     * @param data      data da receita
     * @param categoria categoria da receita
     */
    public Receita(double valor, LocalDate data, CategoriaReceita categoria) {
        super(valor, data);
        this.categoria = categoria;
    }
    
    /**
     * Retorna a categoria da receita.
     * 
     * @return categoria da receita
     */
    public CategoriaReceita getCategoria() {
        return categoria;
    }

     /**
     * Retorna o valor da receita com o sinal correto (positivo).
     * 
     * @return valor positivo da receita
     */
    @Override
    public double getValorComSinal() {
        return getValor(); 
    }

     /**
     * Indica que este lançamento é uma receita.
     * 
     * @return true sempre, pois esta classe representa receitas
     */
    @Override
    public boolean isReceita() {
        return true;
    }

     /**
     * Retorna uma representação em string formatada da receita.
     * 
     * @return string com valor, data e categoria da receita
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
