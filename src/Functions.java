/**
 * Created by Skynet on 15.03.2016.
 */
public class Functions {

    public static boolean checkEmailAddress(String emailadress) {
        int eal = emailadress.length();
        String emailescape = emailadress;

        for(int i = new Integer("A".charAt(1)); i <= 130; i++) {

            String emailescape2 = new String();
            String letter = new String(new char[i]);
            emailescape2 = emailescape.replaceAll(letter, "");
            emailescape = new String(emailescape2);
        }

        if(emailadress.substring(eal - 1, eal).contains(".") == true ||
                emailadress.substring(eal - 2, eal).contains(".") == true ||
                emailadress.startsWith("@") == true ||
                emailadress.contains("@") == false ||
                emailadress.substring(emailadress.lastIndexOf("@")).contains(".") == false ||
                emailadress.substring(1, emailadress.lastIndexOf("@")).contains("@") == true ||
                emailescape.isEmpty() == false) {return false;}
        else {return true;}



    }
}
