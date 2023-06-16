import java.util.*;
public class Vendedor extends Cadastro{
    static Scanner digite;
    protected String nomeDoProduto, codigoDoProduto;
    protected double valorUnitarioDoProduto;
    public Vendedor(String nome, String cpf, String email, String nomeDoProduto, double valorUnitarioDoProduto, String codigoDoProduto){
        super(nome,cpf,email);
        this.nomeDoProduto=nomeDoProduto;
        this.valorUnitarioDoProduto=valorUnitarioDoProduto;
        this.codigoDoProduto=codigoDoProduto;
    }
    public Vendedor(){
        super();
        this.digite=new Scanner(System.in);
    }
    static List<Vendedor> listaDeVendedores = new ArrayList<>();
    public void adicionarVendedoresNaLista(){
        listaDeVendedores.add(new Vendedor("João","12345678901","joao@gmail.com","Maça",2.50,"000"));
        listaDeVendedores.add(new Vendedor("Maria","09876543211","maria.p@gmail.com","Chinelo",10.2,"001"));
    }
    static HashMap<String, String> mapEmailNomeVendedor = new HashMap<>();
    public void nomeEmailDoVendedor(){
        for (Cadastro cadastro : listaDeVendedores) {
            String emailDoMap = cadastro.getEmail();
            String nomeDoMap = cadastro.getNome();
            mapEmailNomeVendedor.put(emailDoMap, nomeDoMap);
        }
    }
    public static void mostrarVendedoresCadastrados(){
        Set<Vendedor> vendedor = new HashSet<>(listaDeVendedores);
        System.out.println("====LISTA DE VENDEDORES====");
        for (Vendedor vendedores:vendedor) {
            System.out.println(vendedores.toString());
        }
    }
    static HashMap<String,String> mapEmailCpfDoVendedor = new HashMap<>();
    public void mapEmailCpfDoVendedor(){
        for (Cadastro cadastro : listaDeVendedores) {
            String emailDoMap = cadastro.getEmail();
            String cpfDoMap = cadastro.getCpf();
            mapEmailCpfDoVendedor.put(emailDoMap, cpfDoMap);
        }
    }
    @Override
    public void cadastrarNoSistema() throws UnsupportedOperationException{
        boolean continuar=true;
        do{
            try{
                System.out.println("Digite seu nome:");
                nome= digite.nextLine();
                if(nome.isBlank() || nome.matches(".*\\d.*") || nome.length() <= 3 ){
                    continuar=false;
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                }
                continuar=true;
            }catch (UnsupportedOperationException error){
                System.err.println(error.getMessage());
            }
        }while (!continuar);

        do{
            try{
                System.out.println("Digite seu CPF:");
                cpf=digite.nextLine();
                if(cpf.isBlank() || !(cpf.matches("\\d+")) || cpf.length() != 11){
                    continuar=false;
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                }
                if(mapEmailCpfDoVendedor.containsValue(cpf)){
                    continuar=false;
                    System.out.println("Esse CPF já está cadastrado!!");
                }
                else{
                    continuar=true;
                }

            }catch (UnsupportedOperationException error){
                System.err.println(error.getMessage());
            }
        }while (!continuar);

        do{
            try{
                System.out.println("Digite seu email:");
                email=digite.nextLine();
                if(email.isBlank() || !(email.indexOf("@") > 0) || email.length() == 1){
                    continuar=false;
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                }
                if(mapEmailCpfDoVendedor.containsKey(email)){
                    continuar=false;
                    System.out.println("Esse email já está cadastrado!!");
                }
                else{
                    continuar=true;
                }
            }catch (UnsupportedOperationException error){
                System.err.println(error.getMessage());
            }
        }while (!continuar);
        System.out.println("Seus dados foram cadastrados com sucesso!");
        System.out.println("----------------------\nPreencha os campos para cadastrar o seu produto:");

        do{
            try{
                System.out.println("Digite o nome do produto:");
                nomeDoProduto=digite.nextLine();
                if(nomeDoProduto.isBlank() || nomeDoProduto.matches(".*\\d.*") || nomeDoProduto.length()==1){
                    continuar=false;
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                }
                else{
                    continuar=true;
                }
            }catch (UnsupportedOperationException error){
                System.err.println(error.getMessage());
            }
        }while (!continuar);

        do{
            try{
                System.out.println("Digite o valor unitário do produto:");
                String valorUnitario = digite.nextLine();
                if(valorUnitario.isBlank() || !validarValorUnitario(valorUnitario)){
                    continuar=false;
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                }
                else{
                    valorUnitarioDoProduto = Double.parseDouble(valorUnitario);
                    continuar=true;
                }
            }catch (UnsupportedOperationException error){
                System.err.println(error.getMessage());
            }
        }while (!continuar);

        do{
            try{
                System.out.println("Digite o código do produto:");
                codigoDoProduto=digite.nextLine();
                if(codigoDoProduto.isBlank() || !codigoDoProduto.matches("\\d+")){
                    continuar=false;
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                }

                if(verificarSeCodigoExiste(codigoDoProduto)){
                    System.out.println("Esse código de produto já está cadastrado, digite novamente");
                    continuar=false;
                }
                else {
                    continuar=true;
                }
            }catch (UnsupportedOperationException error){
                System.err.println(error.getMessage());
            }
        }while (!continuar);
        Vendedor vendedorNovo = new Vendedor(nome, cpf, email, nomeDoProduto, valorUnitarioDoProduto, codigoDoProduto);
        listaDeVendedores.add(vendedorNovo);
        System.out.println("Seu produto foi cadastrado com sucesso!!");
    }
    public static HashMap<String, List<String>> mapProdutoComVendedor = new HashMap<>();
    public void mapDoVendedorComProduto() {
        for (Vendedor vendedor : listaDeVendedores) {
            String nomeVendedor = vendedor.getNome();
            String nomeProduto = vendedor.getNomeDoProduto();
            String valorUnitario = String.valueOf(vendedor.getValorUnitarioDoProduto());
            String codigo = vendedor.getCodigoDoProduto();
            String email = vendedor.getEmail();

            List<String> produtos = new ArrayList<>();
            produtos.add(nomeVendedor);
            produtos.add(nomeProduto);
            produtos.add(valorUnitario);
            produtos.add(codigo);
            produtos.add(email);

            mapProdutoComVendedor.put(codigo, produtos);
        }
    }
    public void mostrarProdutos(){
        for (Map.Entry<String, List<String>> entry : mapProdutoComVendedor.entrySet()) {
            List<String> produtos = entry.getValue();

            for (int i = 0; i < produtos.size(); i +=5) {
                String nomeVendedor = produtos.get(0);
                String nomeProduto = produtos.get(1);
                String valorUnitario = produtos.get(2);
                String codigo = produtos.get(3);

                System.out.println("Vendedor: " + nomeVendedor);
                System.out.println("Produto: " + nomeProduto);
                System.out.println("Valor unitário:R$" + valorUnitario);
                System.out.println("Código:" + codigo);
                System.out.println("---------------------");
            }
        }
    }
    List<String> compraDoCliente = Venda.getCompraDoCliente();
    HashMap<String, List<String>> historico = Venda.getHistoricoVendedor();
    @Override
    public void login() throws UnsupportedOperationException {
        boolean continuar = true;
        do {
            try {
                System.out.println("======LOGIN======");
                System.out.println("Digite seu email:");
                email = digite.nextLine();
                compraDoCliente = historico.get(email);

                if (email.isBlank() || !(email.indexOf("@") > 0) || email.length() == 1) {
                    continuar = false;
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                }
                if (mapEmailCpfDoVendedor.containsKey(email)) {
                    continuar = true;
                } else {
                    System.out.println("Email não cadastrado!");
                    Menu.menuPrincipal();
                }
            } catch (UnsupportedOperationException error) {
                System.err.println(error.getMessage());
            }
        } while (!continuar);
    }
    public static void menuVendedor() throws UnsupportedOperationException{
        String escolhaDoUsuario;
        int escolhaConvertida=0;
        boolean continuar=true;
        do{
            System.out.println("Você deseja:\n1-Ver as vendas | 2-Ver vendedores cadastrados");
            escolhaDoUsuario=digite.nextLine();
            try {
                if(escolhaDoUsuario.isBlank() || !escolhaDoUsuario.matches("\\d+")){
                    continuar=false;
                    throw new UnsupportedOperationException();
                }
                escolhaConvertida = Integer.parseInt(escolhaDoUsuario);
                switch (escolhaConvertida) {
                    case 1:
                        Venda venda = new Venda();
                        venda.historicoVendedor();
                        break;

                    case 2:
                        mostrarVendedoresCadastrados();
                        Menu.menuPrincipal();
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
    @Override
    public String toString() {
        return "Nome: "+nome+"\nCPF: "+cpf+"\nEmail: "+email+"\n----------------------";
    }
    public String getNomeDoProduto() {
        return nomeDoProduto;
    }
    public double getValorUnitarioDoProduto() {
        return valorUnitarioDoProduto;
    }
    public String getCodigoDoProduto() {
        return codigoDoProduto;
    }
    public boolean verificarSeCodigoExiste(String codigoDoProduto){
        boolean codigoExiste=false;
        for (Vendedor codigo:listaDeVendedores){
            if(codigo.getCodigoDoProduto().equals(codigoDoProduto)){
                codigoExiste=true;
            }
        }
        return codigoExiste;
    }
    public boolean validarValorUnitario(String valorUnitario) {
        try {
            Double.parseDouble(valorUnitario);
            return true;
        } catch (NumberFormatException error) {
            return false;
        }
    }
    public static HashMap<String, List<String>> getMapProdutoComVendedor() {
        return mapProdutoComVendedor;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vendedor other = (Vendedor) obj;
        return Objects.equals(cpf, other.cpf);
    }
    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}