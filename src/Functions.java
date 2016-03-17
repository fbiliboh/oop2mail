import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.*;


/**
 * Created by [fbiliboh] on 06.03.2016.
 * Created by [::Tyler the human Compiler::  on 06.03.2016.
 */
public class Functions extends Thread {




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


    public static void makeTextGreatAgain(int p1, int p2, String htmltag) {
        String otag = new String("<" + htmltag + ">");
        String ctag = new String("</" + htmltag + ">");


        String t1;
        String t2;

        String prestring = oop2.editor.getHtmlText().substring(0, p1);
        String boldstr = oop2.editor.getHtmlText().substring(p1, p2);
        String poststr = oop2.editor.getHtmlText().substring(p2);
        String replacestr = new String();

        if(prestring.lastIndexOf(otag) < prestring.lastIndexOf(ctag) == true) {t1 = otag;} else {t1 = ctag;}
        if(boldstr.contains(otag) == false || (boldstr.contains(otag) == true && boldstr.lastIndexOf(otag) < boldstr.lastIndexOf(ctag) == true) || boldstr.contains(ctag) == false) {t2 = ctag;} else {t2 = otag;}
        if(boldstr.contains(otag) == true || boldstr.contains(ctag) == true) {String rep1 = boldstr.replaceAll(otag, "<htmltag2>"); String rep2 = rep1.replaceAll(ctag, otag); replacestr = rep2.replaceAll("<htmltag2>", ctag);}
        if(replacestr.isEmpty() == false) {boldstr = replacestr;}

        String boldagain = new String(prestring + t1 + boldstr + t2 + poststr);

        oop2.editor.setHtmlText(boldagain);
    }


    public static void makeTextColorAgain(int p1, int p2, String hexcol) {

        String otag = new String("<color=\"#" + hexcol + "\">");
        String ctag = new String("</color>");

        String t1;
        String t2;

        String prestring = oop2.editor.getHtmlText().substring(0, p1);
        String markstr = oop2.editor.getHtmlText().substring(p1, p2);
        String poststr = oop2.editor.getHtmlText().substring(p2);
        String replacestr = new String();


        if ((prestring.lastIndexOf("<color=\"#") > prestring.lastIndexOf(ctag) == true) ||
                (prestring.contains("<color=\"#") == true && prestring.contains(ctag) == false)) {
            t1 = ctag + otag;} else {t1 = otag;}

        if ((poststr.indexOf(ctag) < prestring.indexOf("<color=\"#") == true) ||
                (prestring.contains(ctag) == true && prestring.contains("<color=\"#") == false)) {
            String searchcol = prestring + markstr;
            int lastcoltag = searchcol.lastIndexOf("<color=\"#") + 9;
            String excol = new String("<color=\"#" + searchcol.substring(lastcoltag, (lastcoltag + 6)) + "\">");
            t2 = ctag + excol;} else {t2 = ctag;}

            if (markstr.contains("<color=\"#") == true || markstr.contains(ctag) == true) {
                replacestr = markstr.replaceAll("<color=\"#(.)(.)(.)(.)(.)(.)\">", "").replaceAll(ctag, "");
                markstr = replacestr;}
        if (hexcol.matches("xxxxxx") == false && hexcol.matches("XXXXXX") == false) {
            String coloragain = new String(prestring + t1 + markstr + t2 + poststr);
            oop2.editor.setHtmlText(coloragain);
            //return coloragain;

        } else {
            String fontcheck = prestring + markstr;
            String fontface;
            double fontsize;
            System.out.println(markstr);
         /*   if (fontcheck.contains("<font ") == true) {
                fontface = fontcheck.substring((fontcheck.lastIndexOf("<font face=\"") + 12), fontcheck.lastIndexOf(" size=\""));
                fontsize = Double.parseDouble(fontcheck.substring((fontcheck.lastIndexOf(" size=\"") + 7), (fontcheck.lastIndexOf(" size=\"") + 9)));
            } else {*/fontface = "Lucida Grande"; fontsize = 10;//}
                List<String> txtlines = textSlicer(markstr, "\n");
                int listln = txtlines.size();
                int maxlnindex = 0;
                if (listln > 1) {
                    int entrylength = 0;
                    for (int i = 0; i < listln; i++) {
                        if (txtlines.get(i).length() > entrylength) {
                            entrylength = txtlines.get(i).length();
                            maxlnindex = i;
                        }
                    }
                }

                double maxwidth = getTextWidth(txtlines.get(maxlnindex), fontface, fontsize);
            System.out.println("MAXINDEX:       " + Double.toString(maxwidth));
            System.out.println("MAXWIDTH:       " + Double.toString(maxwidth));





                String rainbowtext = new String();
                for(int i = 0; i < txtlines.size(); i++) {
                    String rainbowline = new String(lineToRainbow(txtlines.get(i), fontface, fontsize, maxwidth));
                    System.out.println("RAINBOWLINE: \n\n" + rainbowline);
                    rainbowtext += rainbowline;
                }
            String rainbow = prestring + rainbowtext + poststr;
            oop2.editor.setHtmlText(rainbow);
            System.out.println("RAINBOW OUT: \n\n" + rainbow);
            //return rainbow;
        }

    }
    public static List<String> textSlicer(String textstr, String slinp) {
        List<String> linelist = new ArrayList<>();
        //if(textstr.contains(slinp) == false) {linelist.add(textstr);return linelist;}
        int lbi = textstr.indexOf(slinp) + 1;
        linelist.add(textstr.substring(0, lbi));
        if(textstr.substring(lbi).contains(slinp) == true) {
            linelist.addAll(textSlicer(textstr.substring(lbi), slinp));
            return linelist;} else {linelist.add(textstr.substring(lbi)); return linelist;}
    }



    public static char[] getRainbowHex (double pos, double maxwidth) {
        double rad = (java.lang.StrictMath.PI * 5/3) * (pos / maxwidth);
        double redamp = (cos(rad - (0.5 * sin(rad))) * 248);
        double grnamp = (sin(rad - (PI * 1/6) + (0.5 * cos(rad - (PI * 1/6)))) * 248);
        double bluamp = ((sin(rad + (PI * 7/6) + (0.5 + cos(rad + (PI * 7/6))))) * 248);

        char[] rainbowhex = new char[3];
        if(redamp >= 0) {rainbowhex[0] = ((char) round(redamp));} else {rainbowhex[0] = ((char) 0);}
        if(grnamp >= 0) {rainbowhex[1] = ((char) round(grnamp));} else {rainbowhex[1] = ((char) 0);}
        if(bluamp >= 0) {rainbowhex[2] = ((char) round(bluamp));} else {rainbowhex[2] = ((char) 0);}
        System.out.println("REDVALUE        :       " + Integer.toString(rainbowhex[0]) + "         GRNVALUE:       " + Integer.toString(rainbowhex[1]) + "         BLUVALUE:       " + Integer.toString(rainbowhex[2]));
        return rainbowhex;
    }

    public static double getTextWidth(String strwid, String fontface, double fontsize) {
        if(strwid.isEmpty() == true ) {return 0;}
        Text txtmsr = new Text();
        txtmsr.setText(strwid);
        txtmsr.setFont(Font.font(fontface, fontsize));
        new Scene(new Group(txtmsr));
        txtmsr.applyCss();
        return txtmsr.getLayoutBounds().getWidth();
    }


    public static String lineToRainbow(String linestr, String fontface, double fontsize, double maxwidth) {
        List<String> rbx = new ArrayList<>();
        int strln = linestr.length();

                for(int i = 0; i < strln; i++) {
            char c = linestr.charAt(i);
            double pos = getTextWidth(linestr.substring(0, i), fontface, fontsize);
            String htmlrbxchar = chaRBX(c, pos, maxwidth);
            rbx.add(htmlrbxchar);
        }
        String out = new String();
        for(String s : rbx) {
            out += s;
        }

        return  out;
    }


    public static String chaRBX(char c, double pos, double maxwidth) {
        char[] rainbowhexval = getRainbowHex(pos, maxwidth);
        String redval = Integer.toHexString(((int) rainbowhexval[0]));
        String grnval = Integer.toHexString(((int) rainbowhexval[1]));
        String bluval = Integer.toHexString(((int) rainbowhexval[2]));
        String r, g ,b;
        if(redval.length() == 1) {r = "0" + redval;} else {r = redval;}
        if(grnval.length() == 1) {g = "0" + grnval;} else {g = grnval;}
        if(bluval.length() == 1) {b = "0" + bluval;} else {b = bluval;}
        String rgb = new String(r + g + b);
        String charbx = new String("<span style=\"text-shadow: 1px 1px 1px #000; color:#" + rgb + "; font-size: 10px; line-height: 0.7; font-family: \'verdana\';\">" + c + "</span>");
        return charbx;

    }







}
