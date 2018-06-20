package Simplex.Math;

import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * Created by Andrianina_pc on 20/06/2018.
 */
public class MathUtil {
    public static double arrondir(double d){
        return (double)Math.round(d * 10000d) / 10000d;
    }

    public static String roundDecimal(double nb)
    {
        NumberFormat numberFormat = NumberFormat.getInstance(java.util.Locale.FRENCH);
        numberFormat.setRoundingMode(RoundingMode.HALF_DOWN);
        numberFormat.setMinimumFractionDigits(0);
        numberFormat.setMaximumFractionDigits(0);
        return numberFormat.format(nb);
    }

    public  static String formatNumber(double nb)
    {
        NumberFormat numberFormat_ = NumberFormat.getInstance(java.util.Locale.FRENCH);

        numberFormat_.setRoundingMode(RoundingMode.HALF_DOWN);
        numberFormat_.setMinimumIntegerDigits(2);
        numberFormat_.setMaximumIntegerDigits(20);
        return numberFormat_.format(nb);
    }
}
