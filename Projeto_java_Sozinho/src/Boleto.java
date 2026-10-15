
import java.time.LocalDate;

public class Boleto extends Pagamento implements INotificacao {

    private String codigoBoleto;
    private LocalDate vencimento;

    public Boleto(double valor, String cliente,
                  String codigoBoleto, LocalDate vencimento) {
        super(valor, cliente);
        this.codigoBoleto = codigoBoleto;
        this.vencimento = vencimento;
    }

    @Override
    public void enviarNotificacao(String mensagem) {
        System.out.println("Notificação para " + cliente
                + ": " + mensagem);
    }

    @Override
    public void validaPagamento() {
        if (codigoBoleto != null && !codigoBoleto.isBlank()
                && vencimento != null
                && !vencimento.isBefore(LocalDate.now())) {
            System.out.println("Boleto válido.");
        } else {
            System.out.println("Boleto inválido ou vencido.");
        }
    }

    @Override
    public void processaPagamento() {
        if (codigoBoleto == null || codigoBoleto.isBlank()
                || vencimento == null
                || vencimento.isBefore(LocalDate.now())) {
            System.out.println("Boleto inválido ou vencido.");
            return;
        }

        System.out.println("Boleto registrado para pagamento.");
        System.out.println("Código: " + codigoBoleto);
        System.out.println("Vencimento: " + vencimento);

        enviarNotificacao("Boleto registrado. "
                + "Aguardando confirmação do pagamento.");
    }

    @Override
    public double calcularTaxa() {
        return 2.00;
    }

}
