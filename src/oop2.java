import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
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
                   borderPane.setCenter(allContent());
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

    private HBox allContent(){

        HBox allContent = new HBox(2);
             allContent.getChildren().addAll(createCenterGroup(),createSideMenu());

        return allContent;
    }

    private VBox createCenterGroup() {
        VBox controlBox = new VBox(2, createFinalHeaderBox(),
                                      createTextArea());
        return controlBox;
    }

    private VBox createSideMenu() {

        Button btn = new Button("Fischen");

        VBox sideMenuButtons = new VBox(5);
             sideMenuButtons.getChildren().addAll(btn);
             //sideMenuButtons.getChildren().addAll(sidebar.getControlButton(), iconOption);
             //sideMenuButtons.setPadding(new Insets(35, 5, 10, 5));

        return sideMenuButtons;
    }

    private HBox createFinalHeaderBox() {

        HBox headerBox = new HBox(2);
             headerBox.setPadding(new Insets(10,10,10,10));
             headerBox.getStyleClass().add("vbox");
             headerBox.getChildren().addAll(createHeaderTextFields(),createSendButton());

        return headerBox;
    }

    private TextArea createTextArea() {

        mailTextArea = new TextArea();
        mailTextArea.setPrefSize(Double.MAX_EXPONENT,Double.MAX_EXPONENT);
        mailTextArea.setFocusTraversable(true);
        mailTextArea.setWrapText(true);
        mailTextArea.setStyle("-fx-control-inner-background: black; -fx-text-fill: white"); //set as ID with styling.css!
        //mailTextArea.requestFocus();
        mailTextArea.getStyleClass().add("vbox");

        return mailTextArea;
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

    private Button createSendButton() {

        sendButton = new Button();
        sendButton.setText("Send");
        sendButton.setMinSize(50,60);

        sendButton.setOnAction(event -> System.out.println("sent E-Mail"));

        return sendButton;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}