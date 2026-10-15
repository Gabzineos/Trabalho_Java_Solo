
public class CarteiraDigital extends Pagamento
        implements IAutenticacao, INotificacao {

    private String idCarteira;
    private double saldo;

    public CarteiraDigital(double valor, String cliente,
                           String idCarteira, double saldo) {
        super(valor, cliente);
        this.idCarteira = idCarteira;
        this.saldo = saldo;
    }

    @Override
    public boolean autenticar() {
        return idCarteira != null
                && !idCarteira.isBlank()
                && saldo >= valor;
    }

    @Override
    public void validaPagamento() {
        if (autenticar()) {
            System.out.println("Carteira digital validada.");
        } else {
            System.out.println("Carteira inválida ou saldo insuficiente.");
        }
    }

    @Override
    public void processaPagamento() {
        if (!autenticar()) {
            System.out.println("Pagamento não autorizado.");
            return;
        }

        saldo -= valor;

        System.out.println("Pagamento pela carteira digital realizado.");
        System.out.println("Carteira: " + idCarteira);
        System.out.printf("Saldo restante: R$ %.2f%n", saldo);

        enviarNotificacao(
                "Pagamento pela carteira digital realizado.");
    }

    @Override
    public double calcularTaxa() {
        return valor * 0.015;
    }

    @Override
    public void enviarNotificacao(String mensagem) {
        System.out.println("Notificação para " + cliente
                + ": " + mensagem);
    }
}
