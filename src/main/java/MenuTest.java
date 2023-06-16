import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.Assert.assertEquals;

public class MenuTest {
    @InjectMocks
    private Menu menu;
    @Mock
    private Scanner scanner = new Scanner(System.in);

    @Test
    public void usuarioEscolheuOpcaoUmNoMenuPrincipal() {
        String escolhaDoUsuario = "1";

        Mockito.doNothing().when(menu).opcaoLogin();

        Menu.menuPrincipal();

        Mockito.verify(menu, Mockito.atLeastOnce()).opcaoLogin();


//        String opcaoEscolhida = "1\n";
//        InputStream in = new ByteArrayInputStream(opcaoEscolhida.getBytes());
//        System.setIn(in);
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream));
//
//        Menu.menuPrincipal();
//
//        String output = outputStream.toString().trim();
//        String expectedOutput = "Deseja logar como:\n1-Cliente | 2-Vendedor";
//
//        assertEquals(expectedOutput, output);

    }


}

