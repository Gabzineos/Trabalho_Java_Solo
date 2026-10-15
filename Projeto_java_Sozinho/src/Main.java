
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Pagamento> pagamentos = new ArrayList<>();

        pagamentos.add(new CartaoCredito(
                1200.00, "Gabriel",
                "1234567890123456", 3));

        pagamentos.add(new Pix(
                250.00, "Gabriel",
                "gabriel@email.com"));

        pagamentos.add(new Boleto(
                500.00, "Gabriel", "123456789",
                LocalDate.now().plusDays(5)));

        pagamentos.add(new CarteiraDigital(
                150.00, "Gabriel",
                "CARTEIRA001", 500.00));

        for (Pagamento pagamento : pagamentos) {

            System.out.println("\n========================");

            pagamento.exibirDados();
            pagamento.validaPagamento();

            boolean autenticado = true;

            if (pagamento instanceof IAutenticacao) {
                IAutenticacao autenticacao =
                        (IAutenticacao) pagamento;

                autenticado = autenticacao.autenticar();

                System.out.println("Autenticação: "
                        + (autenticado ? "Aprovada" : "Recusada"));
            }

            if (autenticado) {
                pagamento.processaPagamento();

                double taxa = pagamento.calcularTaxa();

                System.out.printf("Taxa: R$ %.2f%n", taxa);
                System.out.printf("Total com taxa: R$ %.2f%n",
                        pagamento.valor + taxa);

                if (pagamento instanceof INotificacao) {
                    INotificacao notificacao =
                            (INotificacao) pagamento;

                    notificacao.enviarNotificacao(
                            "Processamento finalizado. "
                                    + "Consulte o status do pagamento.");
                }
            }
        }
    }
}
