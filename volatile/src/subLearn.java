import com.sun.javafx.binding.StringFormatter;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Formatter;

public class subLearn {
    public static void main(String[] args) throws InterruptedException {

        String s="1.55555";
        System.out.println (s);
        String a= String.valueOf(StringFormatter.format("%.2s",s));
        System.out.println (new Formatter().format("%.2f", Double.valueOf(s)).toString());

        DecimalFormat formater = new DecimalFormat();
        formater.setMaximumFractionDigits(2);
        formater.setGroupingSize(0);
        formater.setRoundingMode(RoundingMode.FLOOR);
        System.out.println(formater.format(11111));


    }
}
