package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Representa uma receita financeira, que é um tipo de lançamento com valor
 * positivo e uma categoria específica de receita.
 */
public class Receita extends Lancamento {

    private CategoriaReceita categoria;

    /**
     * Constrói uma nova receita com valor, data e categoria especificados.
     *
     * @param valor valor da receita (positivo)
     * @param data data da receita
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

    /**
     * Converte a receita em uma linha de CSV no formato: valor;data;categoria
     */
    public String toCSV() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("%.2f;%s;%s", getValor(), getData().format(formatter), categoria.name());
    }

    /**
     * Constrói uma receita a partir de uma linha de CSV no formato:
     * valor;data;categoria
     */
    public static Receita fromCSV(String linha) {
        try {
            String[] partes = linha.split(";");
            String valorStr = partes[0].replace(",", ".");
            double valor = Double.parseDouble(valorStr);
            LocalDate data = LocalDate.parse(partes[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            CategoriaReceita categoria = CategoriaReceita.valueOf(partes[2]);
            return new Receita(valor, data, categoria);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler linha do CSV: " + linha, e);
        }
    }
}
