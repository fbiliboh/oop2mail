import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.mail.MessagingException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by fbiliboh on 06.03.2016.
 */
public class oop2 extends Application {

    protected static TextArea mailTextArea;
    private Button sendButton;
    private TextField mailAdress, mailSubject;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws MessagingException, ExecutionException, InterruptedException {

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(createCenterGroup());
        //borderPane.setRight(createSendButton());
        borderPane.setPadding(new Insets(15, 15, 15, 15));

        StackPane root = new StackPane();
        root.getChildren().add(borderPane);

        Scene scene = new Scene(root, 300, 250);
        scene.getStylesheets().add(oop2.class.getResource("styling.css").toExternalForm());

        primaryStage.setTitle("");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createCenterGroup() throws MessagingException, ExecutionException, InterruptedException {
        VBox controlBox = new VBox(5, createHeaderBox(),
                createTextArea());
        return controlBox;
    }

    private TextArea createTextArea() {

        mailTextArea = new TextArea();
        mailTextArea.setPrefSize(Double.MAX_EXPONENT, Double.MAX_EXPONENT);
        mailTextArea.setFocusTraversable(true);
        //mailTextArea.requestFocus();

        return mailTextArea;
    }

    private HBox createHeaderBox() throws MessagingException, ExecutionException, InterruptedException {

        HBox headerBox = new HBox(2);
        headerBox.getChildren().addAll(createHeaderTextFields(), createSendButton());

        return headerBox;
    }

    private Button createSendButton() throws MessagingException, ExecutionException, InterruptedException {

        sendButton = new Button();
        sendButton.setText("Send");
        sendButton.setMinSize(50, 60);
        char[] passwd = "123456".toCharArray();


        sendButton.setOnAction(event -> {
            if (mailSubject.getText().length() == 0) {
                mailSubject.setText("No Subject");
            }
            if (mailSubject.getText().length() > 77) {
                System.out.println("ERROR: Subject contains too many characters");
                mailSubject.setText(mailSubject.getText().substring(1, 77));
                mailSubject.setStyle("-fx-border-color: red; -fx-border-width: 2px;");


            } else {
                try {
                    BFH_EmailSender.send("meied3", passwd, mailAdress.getText(), mailSubject.getText(), mailTextArea.getText());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }

                mailTextArea.setStyle("-fx-font-size: 23px; -fx-text-alignment: center; -fx-border-color: greenyellow; -fx-border-width: 3px;");
                mailTextArea.setText("E-Mail Sent!");
                mailAdress.clear();
                mailSubject.clear();
                if (mailSubject.getStyle().contains("-fx-border-color: red;")) {
                    mailSubject.setStyle("-fx-border-color: gray; -fx-border-width: 0px;");
                }


                Label labelObservable = new Label();
                Label labelOldvalue = new Label();
                Label labelNewvalue = new Label();

                Model1 model1 = new Model1();

                model1.stringProperty.addListener(new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        labelObservable.setText((String) observable.getValue());
                        labelOldvalue.setText((String) oldValue);
                        labelNewvalue.setText((String) newValue);

                        if (oldValue.equals(newValue) == false) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    String incoming = newValue.toString();
                                    if (incoming.contains("yellowgreen") == true) {
                                        try {
                                            TimeUnit.MILLISECONDS.sleep(900);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        mailTextArea.setStyle("-fx-font-size: 10px; -fx-btext-alignment: left; -fx-border-color: grey; -fx-border-width: 0px;");
                                        mailTextArea.clear();
                                    }
                                }

                            });
                        }
                    }
                });
                model1.start();


            }
        });


        return sendButton;

    }

    private VBox createHeaderTextFields() {

        mailAdress = new TextField();
        mailAdress.setPromptText("Type in E-Mail-Adress");

        mailSubject = new TextField();
        mailSubject.setPromptText("Subject:");

        VBox headerVBox = new VBox(2);
        headerVBox.setPrefWidth(Double.MAX_EXPONENT);
        headerVBox.getChildren().addAll(mailAdress, mailSubject);

        return headerVBox;
    }
}