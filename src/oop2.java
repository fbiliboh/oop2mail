import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.mail.MessagingException;
import java.util.concurrent.ExecutionException;


/**
 * Created by fbiliboh on 06.03.2016.
 * Created by ::Tyler the human Compiler::  on 06.03.2016.
 */
public class oop2 extends Application {

    public static TextArea mailTextArea;
    private Button sendButton;
    private Button colorButton;
    public static TextField mailAdress, mailSubject;
    private SideBar sidebar;
    public int hxv = 0;
    public static StackPane webStack;
    public static HTMLEditor editor;
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
        sidebar = new SideBar(250,10,lyricPane); //
        //sidebar.setMaxSize(600, 400);
        VBox.setVgrow(lyricPane, Priority.ALWAYS);
        //...Sidebar

        BorderPane borderPane = new BorderPane();
                   borderPane.setCenter(allContent());
                   borderPane.setRight(sidebar);
                   borderPane.setPadding(new Insets(15, 15, 15, 15));

        StackPane root = new StackPane();
                  //root.setStyle("-fx-background-color: rgba(255, 255, 255, 0);"); // test

                  root.getChildren().add(borderPane);

        Scene scene = new Scene(root, 600, 400);
              //scene.setFill(Color.LIGHTGRAY); // test

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
                                                              if ((hxv < 127) == true && (incoming.contains("-fx-border-color: #D") == false && incoming.contains("-fx-border-color: #d") == false
                                                                      && incoming.contains("-fx-border-color: #E") == false && incoming.contains("-fx-border-color: #e") == false
                                                                      && incoming.contains("-fx-border-color: #F") == false && incoming.contains("-fx-border-color: #f") == false)) {
                                                                          String redhex;
                                                                          String bluehex;
                                                                  if (Integer.toHexString(hxv * 2).length() == 1) {
                                                                      redhex = new String("0" + Integer.toHexString(hxv * 2));
                                                                          } else {
                                                                      redhex = Integer.toHexString(hxv * 2);

                                                                          }

                                                                  bluehex = Integer.toHexString(255 - ((hxv - (hxv % 4)) / 4));



                                                                  String hexborder = new String("-fx-border-color: #" + redhex + bluehex + redhex + "; -fx-border-width: 3px;");
                                                                  String edistyle = new String("-fx-alignment: center; -fx-text-fill: #" + redhex + bluehex + redhex + "; -fx-fill-width: true;");
                                                                          editor.setStyle("-fx-border-color: #000; -fx-border-width: 1px;  -fx-font-size: 23px; -fx-text-alignment: center; " + edistyle);

                                                                          webStack.setStyle(hexborder);
                                                                          hxv++;

                                                                      } else {
                                                                          editor.setHtmlText("");
                                                                          editor.setStyle("-fx-border-color: #4f4f4f; -fx-border-width: 1px; -fx-font-size: 10px; -fx-text-alignment: left; -fx-alignment: left; -fx-text-fill: #000000; -fx-fill-width: false;");
                                                                          webStack.setStyle("-fx-border-color: #d8d8d8; -fx-border-width: 3px;");
                                                                          hxv = 0;}

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

        controlBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0);"); // test

        HBox.setHgrow(headerBox,Priority.ALWAYS);

        return controlBox;
    }

    private StackPane createStackPaneHtmlTextArea() {

        editor = new HTMLEditor();
        editor.setHtmlText("");
        editor.setStyle("-fx-opacity: 1; -fx-control-inner-background: white; -fx-text-fill: black; -fx-border-color: #4f4f4f; -fx-border-width: 1px"); // test
        editor.setPrefSize(Double.MAX_EXPONENT, Double.MAX_EXPONENT);

        view = new WebView();
        view.setBlendMode(BlendMode.DARKEN);
        //view.setStyle("-fx-border-color: black; -fx-border-insets: 5px 5px 5px 5px;");
        view.setPrefSize(Double.MAX_EXPONENT, Double.MAX_EXPONENT);

        webStack = new StackPane();
        webStack.setStyle("-fx-border-color: #d8d8d8; -fx-border-width: 3px; -fx-background-color: white; -fx-opacity: 1"); // test
        webStack.getChildren().addAll(editor, view);

        return webStack;
    }

    private VBox createSideMenu() throws InterruptedException, ExecutionException, MessagingException {

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
        sideMenuButtons.getChildren().addAll(sidebar.getControlButton(), editToggleButton, btn, createColorButton());
             //sideMenuButtons.getChildren().addAll(sidebar.getControlButton(), iconOption);
             //sideMenuButtons.setPadding(new Insets(35, 5, 10, 5));

        return sideMenuButtons;
    }

    private TextArea createTextArea() { //not used, as html editor was added

        mailTextArea = new TextArea();
        mailTextArea.setPrefSize(Double.MAX_EXPONENT, Double.MAX_EXPONENT);
        mailTextArea.setFocusTraversable(true);
        mailTextArea.setWrapText(true);
        mailTextArea.setStyle("-fx-control-inner-background: white; -fx-text-fill: black; -fx-border-color: #e0e0e0; -fx-border-width: 0px;"); //set as ID with styling.css!
        //mailTextArea.requestFocus();
        mailTextArea.getStyleClass().add("vbox");

        return mailTextArea;
    }

    private VBox createHeaderTextFields() {

        mailAdress = new TextField();
        mailAdress.setPromptText("Type in E-Mail-Adress");
        mailAdress.setStyle("-fx-opacity: 1"); // test
        mailAdress.setPrefWidth(Double.MAX_EXPONENT);

        mailSubject = new TextField();
        mailSubject.setPromptText("Subject:");
        mailSubject.setStyle("-fx-opacity: 1"); // test
        mailSubject.setPrefWidth(Double.MAX_EXPONENT);

        headerVBox = new VBox(2);
        headerVBox.getChildren().addAll(mailAdress, mailSubject);

        return headerVBox;
    }

    private HBox createHeaderBox() throws MessagingException, ExecutionException, InterruptedException {

        headerBox = new HBox(2);
        headerBox.getChildren().addAll(createHeaderTextFields(),
                                       createSendButton());
        headerBox.setStyle("-fx-background-color: #f5f5f5"); // test
        headerBox.setPadding(new Insets(0,0,0,0));        // test

        HBox.setHgrow(headerVBox,Priority.ALWAYS);

        return headerBox;
    }


    private Button createColorButton() throws MessagingException, ExecutionException, InterruptedException {

        colorButton = new Button();
        colorButton.setText("Rainbow Hex!");



        colorButton.setStyle("-fx-text-fill: black");


        colorButton.setOnAction(event -> {
            int len = editor.getHtmlText().length();
            Functions m1 = new Functions();

            m1.makeTextColorAgain(0, len, "xxxxxx");});
        return colorButton;
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
                    BFH_EmailSender.send(UserVariables.user, passwd, mailAdress.getText(), mailSubject.getText(), editor.getHtmlText());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }

                editor.setStyle("-fx-font-size: 23px; -fx-text-alignment: center; -fx-alignment: center; -fx-text-fill: green; -fx-fill-width: true;");
                webStack.setStyle("-fx-border-color: #00FF00; -fx-border-width: 3px; ");
                //System.out.println("Style: " + mailTextArea.getStyle());
                Text sctext = new Text("e-Mail sent.");
                //sctext.setStyle("-fx-alignment: center; -fx-effect: dropshadow(gaussian, black, 10, 1.0, 100, 100); -fx-fill-width: true;" +
                //" -fx-opacity: 2; -fx-text-alignment: center; -fx-text-fill: green");
                editor.setHtmlText(sctext.getText());
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