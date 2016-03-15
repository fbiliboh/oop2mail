import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.concurrent.TimeUnit;


/**
 * Created by Skynet on 15.03.2016.
 */
class Model1 extends Thread {
    protected static StringProperty stringProperty;

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
            String rcvmsg = oop2.mailTextArea.getStyle();
            System.out.println(rcvmsg);
            stringProperty.set(rcvmsg);
            try {
                TimeUnit.MILLISECONDS.sleep(64);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}