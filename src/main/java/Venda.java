import java.util.*;
public class Venda {
    static Scanner digite = new Scanner(System.in);
    protected String cpf,codigo,emailVendedor;
    HashMap<String, String> cpfNomeCliente = Cliente.getMapCpfNomeCliente();
    Map<String, List<String>> mapProdutoComVendedor = Vendedor.getMapProdutoComVendedor();
    Map<String, String> mapNomeCliente = Cliente.getMapCpfNomeCliente();
    static List<String> compras = new ArrayList<>();
    static HashMap<String, List<String>> historicoCliente = new HashMap<>();
    static HashMap<String, List<String>> historicoVendedor = new HashMap<>();
    static List<String> compraDoCliente = new ArrayList<>();
    public void realizarVenda(){
        Vendedor vendedor = new Vendedor();
        System.out.println("=====NOSSOS PRODUTOS=====");
        vendedor.mostrarProdutos();
        boolean continuar=true;
        do{
            try {
                if(compras == null || compraDoCliente == null){
                    compras = new ArrayList<>();
                    compraDoCliente = new ArrayList<>();
                    historicoCliente.put(cpf,compras);
                    historicoVendedor.put(emailVendedor,compraDoCliente);
                }
                System.out.println("Infome seu CPF para comprar: ");
                cpf = digite.nextLine();
                if(cpf.isBlank() || !cpf.matches("\\d+")){
                    continuar=false;
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                }
                if(!cpfNomeCliente.containsKey(cpf)){
                    System.out.println("Esse CPF não está cadastrado!!");
                    continuar=false;
                }
                else{
                    continuar=true;
                }
            }catch (UnsupportedOperationException error){
                System.out.println(error.getMessage());
            }
        }while (!continuar);

        do{
            try{
                System.out.println("Digite o código do produto que deseja comprar: ");
                codigo = digite.nextLine();
                if (codigo.isBlank() || !codigo.matches("\\d+")) {
                    continuar = false;
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                }
                if (!verificarSeCodigoExiste(codigo)) {
                    System.out.println("Esse código não existe, digite novamente");
                    continuar=false;
                }
                else{
                    continuar=true;
                }
            }catch (UnsupportedOperationException error){
                System.out.println(error.getMessage());
            }
        }while (!continuar);

        do{
            try{
                System.out.println("Digita a quantidade que deseja comprar: ");
                String quantidade = digite.nextLine();
                if(quantidade.isBlank()){
                    continuar = false;
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                }
                if(!quantidade.matches("[0-9]*")){
                    continuar=false;
                    throw new InputMismatchException("Só números inteiros são permitidos, digite novamente");
                }
                else{
                    System.out.println("Compra realizada com sucesso!");
                    List<String> produtos = mapProdutoComVendedor.get(codigo);
                    String nomeDoVendedor = produtos.get(0);
                    String nomeDoProdutoDoUsuario = produtos.get(1);
                    String valorUnitarioDoProdutoDoUsuario = produtos.get(2);
                    String codigoDoProdutoDoUsuario = produtos.get(3);
                    emailVendedor = produtos.get(4);

                    String nomeCliente = mapNomeCliente.get(cpf);
                    double valorTotalDaCompraDoUsuario = valorTotalDaCompraDoUsuario(quantidade, valorUnitarioDoProdutoDoUsuario);

                    String compra = "Produto: "+nomeDoProdutoDoUsuario+"\nQuantidade: "+quantidade+"\nValor pago:R$"+valorTotalDaCompraDoUsuario+"\n----------";
                    if(historicoCliente.containsKey(cpf)){
                        List<String> compraNovaCliente = historicoCliente.get(cpf);
                        compraNovaCliente.add(compra);
                    }
                    else{
                        List<String> compras = new ArrayList<>();
                        compras.add(compra);
                        historicoCliente.put(cpf,compras);
                    }

                    String valorTotalConvertido = String.valueOf(valorTotalDaCompraDoUsuario);
                    String informacoesDoVendedor = "Vendedor: "+nomeDoVendedor+"\nCliente: "+nomeCliente+
                            "\nProduto vendido: "+nomeDoProdutoDoUsuario+"\nCódigo do produto: "+codigoDoProdutoDoUsuario+"\nValor unitário: "+valorUnitarioDoProdutoDoUsuario+
                            "\nQuantidade: "+quantidade+ "\nValor total recebido:R$"+valorTotalConvertido+"\n----------";

                    if(historicoVendedor.containsKey(emailVendedor)){
                        List<String> compraNova = historicoVendedor.get(emailVendedor);
                        compraNova.add(informacoesDoVendedor);
                    }
                    else{
                        List<String> compraDoCliente = new ArrayList<>();
                        compraDoCliente.add(informacoesDoVendedor);
                        historicoVendedor.put(emailVendedor,compraDoCliente);
                    }
                    Menu.menuPrincipal();
                }
            }catch (UnsupportedOperationException error){
                System.out.println(error.getMessage());
            }
            catch (InputMismatchException error){
                System.out.println(error.getMessage());
            }
        }while (!continuar);
    }
    public void historicoCliente(){
        boolean continuar=true;
        do{
            try{
                System.out.println("Digite seu CPF:");
                String cpf = digite.nextLine();

                if (cpf.isBlank()|| !(cpf.matches("\\d+")) || cpf.length() != 11) {
                    continuar = false;
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                }
                if (historicoCliente.containsKey(cpf)) {
                    List<String> exibe = historicoCliente.get(cpf);
                    System.out.println("======SEU HISTÓRICO DE COMPRAS======");
                    for (String compraCliente : exibe) {
                        System.out.println(compraCliente);
                    }
                    System.out.println("Agradecemos por comprar com a gente!\n------------------");
                    Menu.menuPrincipal();
                }
                else{
                    System.out.println("Você não realizou nenhuma compra!\n-----------------");
                    Cliente.menuDoCliente();
                }
            }catch (UnsupportedOperationException error){
                System.out.println(error.getMessage());
            }
        }while (!continuar);
    }
    public void historicoVendedor(){
        boolean continuar=true;
        do{
            try{
                System.out.println("Digite seu email:");
                String email = digite.nextLine();

                if (email.isBlank() || !(email.indexOf("@") > 0) || email.length() == 1) {
                    continuar = false;
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                }
                if (historicoVendedor.containsKey(email)) {
                    List<String> exibe = historicoVendedor.get(email);
                    System.out.println("======SEU HISTÓRICO DE VENDAS======");
                    for (String vendaVendedor : exibe) {
                        System.out.println(vendaVendedor);
                    }
                    System.out.println("Agradecemos por trabalhar com a gente!\n------------------");
                    Menu.menuPrincipal();
                }
                else{
                    System.out.println("Você não realizou nenhuma venda!\n-----------------");
                    Menu.menuPrincipal();
                }
            }catch (UnsupportedOperationException error){
                System.out.println(error.getMessage());
            }
        }while (!continuar);
    }
    public double valorTotalDaCompraDoUsuario(String quantidade, String valorUnitarioDoProdutoDoUsuario){
        int quantidadeConvertida = Integer.parseInt(quantidade);
        double valorUnitarioConvertido = Double.parseDouble(valorUnitarioDoProdutoDoUsuario);
        return quantidadeConvertida*valorUnitarioConvertido;
    }
    public boolean verificarSeCodigoExiste(String codigo){
        boolean codigoExiste=false;
        if(mapProdutoComVendedor.containsKey(codigo)){
            codigoExiste=true;
        }
        return codigoExiste;
    }
    public static List<String> getCompras() {
        return compras;
    }
    public static HashMap<String, List<String>> getHistoricoCliente() {
        return historicoCliente;
    }
    public static HashMap<String, List<String>> getHistoricoVendedor() {
        return historicoVendedor;
    }
    public static List<String> getCompraDoCliente() {
        return compraDoCliente;
    }
}