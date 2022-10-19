public class Produto {
    int codigo;
    private String nome;
    private double valor;

    @Override
    public String toString() {
        return "\nProduto;" +
                "\nCodigo: " + codigo +
                "\nNome: " + nome +
                "\nValor: R$" + valor;
    }

    public Produto(int codigo, String nome, double valor) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
    }

    public int getCodigo() {
        return codigo;
    }

    public double getValor() {
        return valor;
    }
}

