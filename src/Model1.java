import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Created by Skynet on 15.03.2016.
 */
class Model1 extends Thread {
    protected StringProperty stringProperty;

    public Model1() {
        stringProperty = new SimpleStringProperty(this, "str", "");
        setDaemon(true);
    }


    public String getString() {
        return stringProperty.toString();
    }


    public void setString(String value) {
        stringProperty.set(value);
    }


    public StringProperty stringPropertyProperty() {
        return stringProperty;
    }

    public void run() {

        while (true) {
            String rcvmsg = oop2.mailTextArea.getText();
            stringProperty.set(rcvmsg);

        }
    }
}