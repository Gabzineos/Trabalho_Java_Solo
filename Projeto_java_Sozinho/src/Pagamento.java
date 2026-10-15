
public abstract class Pagamento {

    protected double valor;
    protected String cliente;

    public Pagamento(double valor, String cliente) {
        this.valor = valor;
        this.cliente = cliente;
    }

    public void exibirDados() {
        System.out.println("Cliente: " + cliente);
        System.out.printf("Valor: R$ %.2f%n", valor);
    }

    public abstract void processaPagamento();

    public abstract void validaPagamento();

    public abstract double calcularTaxa();
}
