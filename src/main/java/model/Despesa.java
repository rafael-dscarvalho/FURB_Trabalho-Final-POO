package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Representa uma despesa financeira, que é um tipo de lançamento com valor
 * negativo e uma categoria específica de despesa.
 */
public class Despesa extends Lancamento {

    private CategoriaDespesa categoria;

    /**
     * Constrói uma nova despesa com valor, data e categoria especificados.
     *
     * @param valor valor da despesa (positivo)
     * @param data data da despesa
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

    /**
     * Converte a despesa para uma linha de CSV no formato: valor;data;categoria
     */
    public String toCSV() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("%.2f;%s;%s", getValor(), getData().format(formatter), categoria.name());
    }

    /**
     * Cria uma despesa a partir de uma linha de CSV no formato:
     * valor;data;categoria
     */
    public static Despesa fromCSV(String linha) {
        try {
            String[] partes = linha.split(";");
            String valorStr = partes[0].replace(",", ".");
            double valor = Double.parseDouble(valorStr);
            LocalDate data = LocalDate.parse(partes[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            CategoriaDespesa categoria = CategoriaDespesa.valueOf(partes[2]);
            return new Despesa(valor, data, categoria);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler linha do CSV: " + linha, e);
        }
    }
}
