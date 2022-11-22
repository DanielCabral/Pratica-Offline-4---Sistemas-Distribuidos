package test.pages;

import com.athaydes.automaton.FXApp;
import controller.Login;
import controller.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

public class MainTest{
    private static Stage palco;

    public static Stage getPalco() {
        return palco;
    }
    public static void setPalco(Stage palco) {
        controller.Main.palco = palco;
    }
    public void start (Stage palco) {
        setPalco(palco);
        telaInicial();
    }
    public static void telaInicial() {
        try {
            Login l=new Login();

            if(l.getLogou()) {
                Parent root = FXMLLoader.load(controller.Main.class.getResource("/view/FXMLVBoxMain.fxml"));

                Scene scene = new Scene(root);
                Image img=new javafx.scene.image.Image("file:icone.png");
                palco.getIcons().add(img);
                palco.setTitle("Distribute Store");
                palco.setScene(scene);
                palco.show();
            }else {
                controller.Main.caixaDeInformacao("Login n�o realizado", "Falha!", "Login falhou, dados incorretos", 2);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    @BeforeClass
    public void setUp () throws Exception {
        System.out.println( "Launching Java App" );

        FXApp.startApp( new Main());

        System.out.println( "App has been launched" );

        // let the window open and show before running tests
        Thread.sleep( 2000 );
    }

    @AfterClass
    public void tearDown () throws Exception {
        System.out.println( "Cleaning up" );
        FXApp.doInFXThreadBlocking( () -> FXApp.getStage().close() );
    }

    @Test
    public void testEnglishInput () {
        System.out.println( "Launching Java App" );

        FXApp.startApp( new Main());

        System.out.println( "App has been launched" );
        //clickOn("loginButton");
        //write("This is a test!");
    }

    @Test
    public void automatonTest() {
        System.out.println( "Running test" );
        //FXer user = FXer.getUserWith();

        //user.clickOn( TextField.class )
                //.enterText( "" )
                //.type( "Hello Automaton" );

        //noinspection unchecked
        //assertThat( user.getAt( TextField.class ), hasText( "Hello Automaton" ) );
    }

    public static boolean caixaDeInformacao(String titulo,String cabecalho,String conteudo,int tipo) {
        Alert dialogoInfo;
        if(tipo==0) {
            dialogoInfo= new Alert(Alert.AlertType.CONFIRMATION);
        }else if(tipo==1){
            dialogoInfo= new Alert(Alert.AlertType.INFORMATION);
        }else if(tipo==2){
            dialogoInfo= new Alert(Alert.AlertType.WARNING);
        }else {
            dialogoInfo= new Alert(Alert.AlertType.ERROR);
        }
        dialogoInfo.setTitle(titulo);
        dialogoInfo.setContentText(conteudo);
        return !dialogoInfo.showAndWait().get().getButtonData().toString().equals("CANCEL_CLOSE");

    }
}
