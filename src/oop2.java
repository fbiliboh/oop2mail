import com.sun.org.apache.xml.internal.security.Init;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.mail.MessagingException;
import java.util.concurrent.ExecutionException;


/**
 * Created by fbiliboh on 06.03.2016.
 */
public class oop2 extends Application {

    public static TextArea mailTextArea;
    private Button sendButton;
    public static TextField mailAdress, mailSubject;
    private SideBar sidebar;
    public int hxv = 0;
    private HTMLEditor htmlEditor;
    private StackPane webStack;
    private HTMLEditor editor;
    private WebView view;
    private VBox controlBox;
    private HBox headerBox;
    private VBox headerVBox;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws MessagingException, ExecutionException, InterruptedException {

        primaryStage.initStyle(StageStyle.UNIFIED); // not working yet..

        //Sidebar...
        final Pane lyricPane = createSidebarContent();
        sidebar = new SideBar(150,10,lyricPane); //
        //sidebar.setMaxSize(600, 400);
        VBox.setVgrow(lyricPane, Priority.ALWAYS);
        //...Sidebar

        BorderPane borderPane = new BorderPane();
                   borderPane.setCenter(allContent());
                   borderPane.setRight(sidebar);
                   borderPane.setPadding(new Insets(15, 15, 15, 15));

        StackPane root = new StackPane();
                  root.setStyle("-fx-opacity: 0.9; -fx-background-color: rgba(255, 255, 255, 0);"); // test
                  root.getChildren().add(borderPane);

        Scene scene = new Scene(root, 600, 400);
              scene.setFill(Color.TRANSPARENT); // test
              scene.getStylesheets().add(oop2.class.getResource("styling.css").toExternalForm());

        primaryStage.setTitle("");
        primaryStage.setScene(scene);
        primaryStage.show();

        Label labelObservable = new Label();
        Label labelOldvalue = new Label();
        Label labelNewvalue = new Label();

        Model1 model1 = new Model1();

        model1.stringProperty.addListener(new ChangeListener() {
                                                      @Override
                                                      public void changed (ObservableValue observable, Object oldValue, Object newValue){
                                                          labelObservable.setText((String) observable.getValue());
                                                          labelOldvalue.setText((String) oldValue);
                                                          labelNewvalue.setText((String) newValue);

                                                          if (oldValue.equals(newValue) == false) {
                                                              Platform.runLater(new Runnable() {
                                                                  @Override
                                                                  public void run() {
                                                                      String incoming = newValue.toString();
                                                                      if (incoming.contains("-fx-border-color: #C0C0C0;") == false && incoming.contains("-fx-border-color: #c0c0c0;") == false) {
                                                                          String redhex;
                                                                          String bluehex;
                                                                          if (Integer.toHexString(hxv * 3).length() == 1) {
                                                                              redhex = new String("0" + Integer.toHexString(hxv * 3));
                                                                          } else {
                                                                              redhex = Integer.toHexString(hxv * 3);

                                                                          }
                                                                          if (hxv == 0) {
                                                                              bluehex = Integer.toHexString(255);
                                                                          } else {
                                                                              bluehex = Integer.toHexString(256 - hxv);
                                                                          }


                                                                          String hexborder = new String("-fx-font-size: 23px; -fx-btext-alignment: center; -fx-border-color: #" + redhex + bluehex + redhex + "; -fx-border-width: 3px; -fx-effect: dropshadow(gaussian, black, 10, 3.0, 100, 100); -fx-alignment: center; -fx-text-fill: #" + redhex + bluehex + redhex + "; -fx-fill-width: true;");
                                                                          mailTextArea.setStyle(hexborder);
                                                                          hxv++;

                                                                      } else {
                                                                          mailTextArea.setStyle("-fx-font-size: 10px; -fx-text-alignment: left; -fx-border-color: #C0C0C0; -fx-border-width: 0px;");
                                                                          mailTextArea.clear();
                                                                          hxv = 0;
                                                                      }

                                                                  }

                                                              });
                                                          }
                                                      }
                                                  }
        );
        model1.start();
    }

    private BorderPane createSidebarContent() {
        // create some content to put in the sidebar.
            final Text lyric = new Text();
                       lyric.getStyleClass().add("lyric-text");
            final Button changeLyric = new Button("New Song");
                         changeLyric.getStyleClass().add("change-lyric");
                         //changeLyric.setMaxWidth(Double.MAX_VALUE);
                         changeLyric.fire();
            final BorderPane lyricPane = new BorderPane();
                             lyricPane.setCenter(lyric);
                             lyricPane.setBottom(changeLyric);

            return lyricPane;
    }

    private HBox allContent() throws InterruptedException, ExecutionException, MessagingException {

        HBox allContent = new HBox(8);
             allContent.getChildren().addAll(createCenterGroup(),
                                             createSideMenu());
        HBox.setHgrow(controlBox, Priority.ALWAYS);

        return allContent;
    }

    private VBox createCenterGroup() throws MessagingException, ExecutionException, InterruptedException {

        controlBox = new VBox(8, createHeaderBox(),
                                 createStackPaneHtmlTextArea());

        controlBox.setStyle("-fx-opacity: 0.9; -fx-background-color: rgba(255, 255, 255, 0);"); // test

        HBox.setHgrow(headerBox,Priority.ALWAYS);

        return controlBox;
    }

    private StackPane createStackPaneHtmlTextArea() {

        editor = new HTMLEditor();
        editor.setHtmlText("<h1>The Gettysburg Address</h1><i>Four score and twenty years ago . . .</i><br/><img src='http://bluebuddies.com/gallery/Historical_Smurfs/jpg/Smurfs_Historical_Figure_20506_Abraham_Lincoln.jpg'/>");
        editor.setStyle("-fx-opacity: 0.6"); // test
        editor.setPrefSize(Double.MAX_EXPONENT, Double.MAX_EXPONENT);

        view = new WebView();
        view.setBlendMode(BlendMode.DARKEN);
        view.setPrefSize(Double.MAX_EXPONENT, Double.MAX_EXPONENT);

        webStack = new StackPane();
        webStack.setStyle("-fx-border-color: black; -fx-background-color: limegreen; -fx-opacity: 0.6"); // test
        webStack.getChildren().addAll(editor, view);

        return webStack;
    }

    private VBox createSideMenu() {

        Label labelToggleButton  = new Label("Edit");
              labelToggleButton.setRotate(-90);
              labelToggleButton.setStyle("-fx-text-fill: black");

        Label labelButton  = new Label("??"); //If you use the Button, rename the Label "labelButton" properly!
              labelButton.setRotate(-90);
              labelButton.setStyle("-fx-text-fill: black");

        final ToggleButton editToggleButton = new ToggleButton();
                           editToggleButton.setGraphic(new Group(labelToggleButton));

        final Button btn = new Button();
                     btn.setGraphic(new Group(labelButton));

        editToggleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent arg0) {
                webStack.getChildren().clear();
                if (editToggleButton.isSelected()) {
                    webStack.getChildren().addAll(editor);
                } else {
                    view.getEngine().loadContent(editor.getHtmlText());
                    webStack.getChildren().addAll(view);
                }
            }
        });

        editToggleButton.fire();

        VBox sideMenuButtons = new VBox(5);
             sideMenuButtons.getChildren().addAll(sidebar.getControlButton(), editToggleButton, btn);
             //sideMenuButtons.getChildren().addAll(sidebar.getControlButton(), iconOption);
             //sideMenuButtons.setPadding(new Insets(35, 5, 10, 5));

        return sideMenuButtons;
    }

    private TextArea createTextArea() { //not used, as html editor was added

        mailTextArea = new TextArea();
        mailTextArea.setPrefSize(Double.MAX_EXPONENT, Double.MAX_EXPONENT);
        mailTextArea.setFocusTraversable(true);
        mailTextArea.setWrapText(true);
        mailTextArea.setStyle("-fx-control-inner-background: black; -fx-text-fill: white; -fx-border-color: #c0c0c0; -fx-border-width: 0px;"); //set as ID with styling.css!
        //mailTextArea.requestFocus();
        mailTextArea.getStyleClass().add("vbox");

        return mailTextArea;
    }

    private VBox createHeaderTextFields() {

        mailAdress = new TextField();
        mailAdress.setPromptText("Type in E-Mail-Adress");
        mailAdress.setStyle("-fx-opacity: 0.7"); // test
        mailAdress.setPrefWidth(Double.MAX_EXPONENT);

        mailSubject = new TextField();
        mailSubject.setPromptText("Subject:");
        mailSubject.setStyle("-fx-opacity: 0.7"); // test
        mailSubject.setPrefWidth(Double.MAX_EXPONENT);

        headerVBox = new VBox(2);
        headerVBox.getChildren().addAll(mailAdress, mailSubject);

        return headerVBox;
    }

    private HBox createHeaderBox() throws MessagingException, ExecutionException, InterruptedException {

        headerBox = new HBox(2);
        headerBox.getChildren().addAll(createHeaderTextFields(),
                                       createSendButton());
        headerBox.setStyle("-fx-background-color: darkblue"); // test
        headerBox.setPadding(new Insets(10,10,10,10));        // test

        HBox.setHgrow(headerVBox,Priority.ALWAYS);

        return headerBox;
    }

    private Button createSendButton() throws MessagingException, ExecutionException, InterruptedException {

        sendButton = new Button();
        sendButton.setText("Send");
        sendButton.setMinSize(50, 60);
        char[] passwd = UserVariables.passwd.toCharArray();


        sendButton.setOnAction(event -> {

            String emailaddress = oop2.mailAdress.getText();

            //if (Functions.checkEmailAddress(emailaddress)) {

            if (mailSubject.getText().length() == 0) {
                mailSubject.setText("No Subject");
            }
            if (mailSubject.getText().length() > 77) {
                System.out.println("ERROR: Subject contains too many characters");
                mailSubject.setText(mailSubject.getText().substring(1, 77));
                mailSubject.setStyle("-fx-border-color: red; -fx-border-width: 2px;");


            } else {
                try {
                    BFH_EmailSender.send(UserVariables.user, passwd, mailAdress.getText(), mailSubject.getText(), mailTextArea.getText());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }

                mailTextArea.setStyle("-fx-font-size: 23px; -fx-text-alignment: center; -fx-border-color: #00FF00; -fx-border-width: 3px; -fx-effect: dropshadow(gaussian, black, 10, 1.0, 100, 100); -fx-alignment: center; -fx-text-fill: green; -fx-fill-width: true;");

                //System.out.println("Style: " + mailTextArea.getStyle());
                Text sctext = new Text("e-Mail sent.");
                //sctext.setStyle("-fx-alignment: center; -fx-effect: dropshadow(gaussian, black, 10, 1.0, 100, 100); -fx-fill-width: true;" +
                //" -fx-opacity: 2; -fx-text-alignment: center; -fx-text-fill: green");
                mailTextArea.setText(sctext.getText());
                mailAdress.clear();
                mailSubject.clear();
                if (mailSubject.getStyle().contains("-fx-border-color: red;")) {
                    mailSubject.setStyle("-fx-border-color: gray; -fx-border-width: 0px;");
                }
            }
        });

        return sendButton;
    }
}