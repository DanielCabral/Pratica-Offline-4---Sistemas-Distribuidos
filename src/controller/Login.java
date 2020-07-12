package controller;

import java.rmi.RemoteException;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import server.Cliente;

public class Login extends Application{
	Cliente c = new Cliente();
	boolean logou=false;
	 public Login() {
			// Create the custom dialog.
			Dialog<Pair<String, String>> dialog = new Dialog<>();
			dialog.setTitle("Distribute Store");
			dialog.setHeaderText("Realize Login");

			// Set the icon (must be included in the project).
			//System.out.println(this.getClass().getResource("icone.png").toString());
			dialog.setGraphic(new ImageView(new javafx.scene.image.Image("file:icone.png")));
			Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new javafx.scene.image.Image("file:icone.png"));
			// Set the button types.
			ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

			// Create the username and password labels and fields.
			GridPane grid = new GridPane();
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(20, 150, 10, 10));

			TextField username = new TextField();
			username.setPromptText("Matricula");
			PasswordField password = new PasswordField();
			password.setPromptText("CPF");

			grid.add(new Label("Matricula:"), 0, 0);
			grid.add(username, 1, 0);
			grid.add(new Label("CPF:"), 0, 1);
			grid.add(password, 1, 1);

			// Enable/Disable login button depending on whether a username was entered.
			Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
			loginButton.setDisable(true);

			// Do some validation (using the Java 8 lambda syntax).
			username.textProperty().addListener((observable, oldValue, newValue) -> {
			    loginButton.setDisable(newValue.trim().isEmpty());
			});

			dialog.getDialogPane().setContent(grid);

			// Request focus on the username field by default.
			Platform.runLater(() -> username.requestFocus());

			// Convert the result to a username-password-pair when the login button is clicked.
			dialog.setResultConverter(dialogButton -> {
			    if (dialogButton == loginButtonType) {
			        return new Pair<>(username.getText(), password.getText());
			    }
			    return null;
			});

			Optional<Pair<String, String>> result = dialog.showAndWait();

			result.ifPresent(usernamePassword -> {
				try {
					String matricula = usernamePassword.getKey();
					String cpf = usernamePassword.getValue();
					logou = c.logar(matricula, cpf);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
			});
	 }
	 
	public boolean getLogou() {
		return logou;
	}
	public static void main(String [] args) {
		
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
