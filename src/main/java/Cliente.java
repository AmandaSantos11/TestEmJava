import java.util.*;
public class Cliente extends Cadastro{
    static Scanner digite;
    public Cliente(String nome, String cpf, String email) {
        super(nome, cpf, email);
    }
    public Cliente(){
        this.digite=new Scanner(System.in);
    }
    static List<Cliente> listaDeClientes = new ArrayList<>();
    public static void adicionarClientesNaLista(){
        listaDeClientes.add(new Cliente("Lola","12309845671","lola.ortega@gmail.com"));
        listaDeClientes.add(new Cliente("Matheus","78907213451","math32@gmail.com"));
    }
    static HashMap<String,String> mapCpfEmailDoCliente = new HashMap<>();
    public static void mapCpfEmailDoCliente(){
        for (Cadastro cadastro : listaDeClientes) {
            String cpfDoMap = cadastro.getCpf();
            String emailDoMap = cadastro.getEmail();
            mapCpfEmailDoCliente.put(cpfDoMap,emailDoMap);
        }
    }
    static HashMap<String, String> mapCpfNomeCliente = new HashMap<>();
    public static void cpfNomeDoCliente(){
        for (Cadastro cadastro : listaDeClientes) {
            String cpfDoMap = cadastro.getCpf();
            String nomeDoMap = cadastro.getNome();
            mapCpfNomeCliente.put(cpfDoMap, nomeDoMap);
        }
    }
    @Override
    public void cadastrarNoSistema(){
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
                if(mapCpfEmailDoCliente.containsKey(cpf)){
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
                if(mapCpfEmailDoCliente.containsValue(email)){
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
        Cliente clienteNovo = new Cliente(nome, cpf, email);
        listaDeClientes.add(clienteNovo);
        System.out.println("Seus dados foram cadastrados com sucesso!");
    }
    public static void mostrarClientesCadastrados(){
        Set<Cliente> cliente = new HashSet<>(listaDeClientes);
        System.out.println("====LISTA DE CLIENTES====");
        for (Cliente clientes:cliente) {
            System.out.println(clientes.toString());
        }
    }
    List<String> compras = Venda.getCompras();
    HashMap<String, List<String>> historico = Venda.getHistoricoCliente();
    @Override
    public void login() throws UnsupportedOperationException{
        boolean continuar=true;
        do{
            try{
                System.out.println("======LOGIN======");
                System.out.println("Digite seu CPF:");
                cpf = digite.nextLine();
                compras = historico.get(cpf);

                if (cpf.isBlank()|| !(cpf.matches("\\d+")) || cpf.length() != 11) {
                    continuar = false;
                    throw new UnsupportedOperationException("Preenchimento inválido, digite novamente");
                }
                if(mapCpfEmailDoCliente.containsKey(cpf)){
                    continuar=true;
                }
                else{
                    System.out.println("CPF não cadastrado!");
                    Menu.menuPrincipal();
                }
            }catch (UnsupportedOperationException error){
                System.err.println(error.getMessage());
            }
        }while(!continuar);
    }
    public static void menuDoCliente() throws UnsupportedOperationException{
        Venda venda = new Venda();
        String escolhaDoUsuario;
        int escolhaConvertida=0;
        boolean continuar=true;
        do{
            System.out.println("Você deseja:\n1-Comprar | 2-Ver suas compras | 3-Ver clientes cadastrados");
            escolhaDoUsuario=digite.nextLine();
            try {
                if(escolhaDoUsuario.isBlank() || !escolhaDoUsuario.matches("\\d+")){
                    continuar=false;
                    throw new UnsupportedOperationException();
                }
                escolhaConvertida = Integer.parseInt(escolhaDoUsuario);
                switch (escolhaConvertida) {
                    case 1:
                        venda.realizarVenda();
                        break;

                    case 2:
                        venda.historicoCliente();
                        break;

                    case 3:
                        mostrarClientesCadastrados();
                        Menu.menuPrincipal(escolhaDoUsuario);
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
        return "Cliente: "+nome+"\nCPF: "+cpf+"\nEmail: "+email+"\n----------------------";
    }
    public static HashMap<String, String> getMapCpfNomeCliente() {
        return mapCpfNomeCliente;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Cliente other = (Cliente) obj;
        return Objects.equals(cpf, other.cpf);
    }
    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}