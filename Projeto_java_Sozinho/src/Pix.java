
public class Pix extends Pagamento
        implements IAutenticacao, INotificacao {

    private String chavePix;

    public Pix(double valor, String cliente, String chavePix) {
        super(valor, cliente);
        this.chavePix = chavePix;
    }

    @Override
    public boolean autenticar() {
        System.out.println("Validando dados do PIX...");
        return chavePix != null && !chavePix.isBlank();
    }

    @Override
    public void enviarNotificacao(String mensagem) {
        System.out.println("Notificação para " + cliente
                + ": " + mensagem);
    }

    @Override
    public void validaPagamento() {
        if (autenticar()) {
            System.out.println("Dados do PIX válidos.");
        } else {
            System.out.println("Chave PIX inválida.");
        }
    }

    @Override
    public void processaPagamento() {
        if (!autenticar()) {
            System.out.println("PIX não processado.");
            return;
        }

        System.out.println("Pagamento via PIX processado.");
        System.out.println("Chave PIX: " + chavePix);

        enviarNotificacao("PIX processado com sucesso.");
    }


    @Override
    public double calcularTaxa() {
        return 0.0;
    }

}
