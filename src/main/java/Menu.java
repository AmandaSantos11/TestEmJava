import java.io.PrintStream;
import java.util.Scanner;
public class Menu {
    public static Scanner digite = new Scanner(System.in);
    public static Vendedor vendedor = new Vendedor();
    public static Cliente cliente = new Cliente();


    public static void menuPrincipal(){
        vendedor.adicionarVendedoresNaLista();
        vendedor.mapEmailCpfDoVendedor();
        vendedor.nomeEmailDoVendedor();
        vendedor.mapDoVendedorComProduto();

        cliente.adicionarClientesNaLista();
        cliente.mapCpfEmailDoCliente();
        cliente.cpfNomeDoCliente();

        String escolhaDoUsuario;
        int escolhaConvertida=0;
        boolean continuar=true;
        do{
            System.out.println("=====Bem-Vindo a nossa loja=====\n             MENU                ");
            System.out.println("Você deseja:\n1-Fazer Login | 2-Cadastrar-se | 3-Sair");
            escolhaDoUsuario = digite.nextLine();
            try{
                if(escolhaDoUsuario.isBlank() || !escolhaDoUsuario.matches("\\d+")){
                    continuar=false;
                    throw new UnsupportedOperationException();
                }
                escolhaConvertida = Integer.parseInt(escolhaDoUsuario);
                switch (escolhaConvertida){
                    case 1:
                        opcaoLogin();
                        break;

                    case 2:
                        opcaoCadastro();
                        break;
                    case 3:

                        System.out.println("Obrigado por utilizar nosso serviço!!");
                        System.exit(0);

                    default:
                        continuar=false;
                        throw new UnsupportedOperationException();
                }
            }
            catch (UnsupportedOperationException error) {
                System.err.println("Opção inválida, tente novamente");
            }
        }
        while (!(escolhaConvertida==1 || escolhaConvertida==2 || escolhaConvertida==3 || continuar));
    }
    public static void opcaoCadastro(){
        String escolhaDoUsuario;
        int escolhaConvertida=0;
        boolean continuar=true;
        do{
            System.out.println("---------------------\nDeseja se cadastrar como:\n1-Cliente | 2-Vendedor");
            escolhaDoUsuario = digite.nextLine();
            try {
                if(escolhaDoUsuario.isBlank() || !escolhaDoUsuario.matches("\\d+")){
                    continuar=false;
                    throw new UnsupportedOperationException();
                }
                escolhaConvertida = Integer.parseInt(escolhaDoUsuario);
                switch (escolhaConvertida) {
                    case 1:
                        cliente.cadastrarNoSistema();
                        menuPrincipal();
                        break;

                    case 2:
                        vendedor.cadastrarNoSistema();
                        menuPrincipal();
                        break;

                    default:
                        continuar=false;
                        throw new UnsupportedOperationException();
                }
            }
            catch (UnsupportedOperationException error){
                System.err.println("Opção inválida, digite novamente");
            }
        } while(!(escolhaConvertida==1 || escolhaConvertida==2 || continuar));
    }
    public static void opcaoLogin(){
        String escolhaDoUsuario;
        int escolhaConvertida=0;
        boolean continuar=true;
        do{
            System.out.println("Deseja logar como:\n1-Cliente | 2-Vendedor");
            escolhaDoUsuario=digite.nextLine();
            try {
                if(escolhaDoUsuario.isBlank() || !escolhaDoUsuario.matches("\\d+")){
                    continuar=false;
                    throw new UnsupportedOperationException();
                }
                escolhaConvertida = Integer.parseInt(escolhaDoUsuario);
                switch (escolhaConvertida) {
                    case 1:
                        cliente.login();
                        cliente.menuDoCliente();
                        break;

                    case 2:
                        vendedor.login();
                        vendedor.menuVendedor();
                        break;

                    default:
                        continuar=false;
                        throw new UnsupportedOperationException();
                }
            }
            catch (UnsupportedOperationException error){
                System.err.println("Opção inválida, digite novamente");
            }
        } while(!(escolhaConvertida==1 || escolhaConvertida==2 || continuar));
    }


}