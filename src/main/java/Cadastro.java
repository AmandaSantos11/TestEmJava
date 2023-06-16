import java.util.Scanner;
public class Cadastro {
    protected String nome,cpf,email;
    public Scanner digite;
    public Cadastro(String nome, String cpf, String email){
        this.nome=nome;
        this.cpf=cpf;
        this.email=email;
    }
    public Cadastro(){
        this.digite=new Scanner(System.in);
    }
    public String getNome() {
        return nome;
    }
    public String getCpf() {
        return cpf;
    }
    public String getEmail() {
        return email;
    }
    public void cadastrarNoSistema(){}
    public void login(){}
}
