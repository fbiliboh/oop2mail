import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

/**
 * Created by fbiliboh on 06.03.2016.
 */
public class oop2 extends Application {

    private Button sendButton;
    private TextField mailAdress, mailSubject;
    private TextArea mailTextArea;


    @Override
    public void start(Stage primaryStage) {

        BorderPane borderPane = new BorderPane();
                   borderPane.setCenter(createCenterGroup());
                   //borderPane.setRight(createSendButton());
                   borderPane.setPadding(new Insets(15,15,15,15));

        StackPane root = new StackPane();
        root.getChildren().add(borderPane);

        Scene scene = new Scene(root, 300, 250);
              scene.getStylesheets().add(oop2.class.getResource("styling.css").toExternalForm());

        primaryStage.setTitle("");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createCenterGroup() {
        VBox controlBox = new VBox(5, createHeaderBox(),
                                      createTextArea());
        return controlBox;
    }

    private TextArea createTextArea() {

        mailTextArea = new TextArea();
        mailTextArea.setPrefSize(Double.MAX_EXPONENT,Double.MAX_EXPONENT);
        mailTextArea.setFocusTraversable(true);
        //mailTextArea.requestFocus();

        return mailTextArea;
    }

    private HBox createHeaderBox() {

        HBox headerBox = new HBox(2);
             headerBox.getChildren().addAll(createHeaderTextFields(),createSendButton());

        return headerBox;
    }


    private Button createSendButton() {

        sendButton = new Button();
        sendButton.setText("Send");
        sendButton.setMinSize(50,60);

        sendButton.setOnAction(event -> System.out.println("sent E-Mail"));

        return sendButton;
    }

    private VBox createHeaderTextFields() {

        mailAdress = new TextField();
        mailAdress.setPromptText("Type in E-Mail-Adress");

        mailSubject = new TextField();
        mailSubject.setPromptText("Subject:");

        VBox headerVBox = new VBox(2);
             headerVBox.setPrefWidth(Double.MAX_EXPONENT);
             headerVBox.getChildren().addAll(mailAdress,mailSubject);

        return headerVBox;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}