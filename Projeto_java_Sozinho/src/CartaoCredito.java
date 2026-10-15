
public class CartaoCredito extends Pagamento
        implements IAutenticacao, INotificacao {

    private String numeroCartao;
    private int parcelas;

    public CartaoCredito(double valor, String cliente,
                         String numeroCartao, int parcelas) {
        super(valor, cliente);
        this.numeroCartao = numeroCartao;
        this.parcelas = parcelas;
    }

    @Override
    public boolean autenticar() {
        System.out.println("Autenticando cartão...");
        return numeroCartao != null
                && !numeroCartao.isBlank()
                && parcelas >= 1;
    }

    @Override
    public void enviarNotificacao(String mensagem) {
        System.out.println("Notificação para " + cliente
                + ": " + mensagem);
    }

    @Override
    public void validaPagamento() {
        if (autenticar()) {
            System.out.println("Dados do cartão válidos.");
        } else {
            System.out.println("Dados do cartão inválidos.");
        }
    }

    @Override
    public void processaPagamento() {
        if (!autenticar()) {
            System.out.println("Pagamento não autorizado.");
            return;
        }

        double valorParcela = valor / parcelas;

        System.out.println("Pagamento no cartão processado.");
        System.out.println("Parcelas: " + parcelas);
        System.out.printf("Valor por parcela: R$ %.2f%n",
                valorParcela);

        enviarNotificacao("Pagamento processado com sucesso.");
    }


    @Override
    public double calcularTaxa() {
        double percentual = 0.03 + (parcelas - 1) * 0.01;
        return valor * percentual;
    }

}
