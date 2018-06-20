package Simplex.Math;

/**
 * Created by Andrianina_pc on 20/06/2018.
 */
public class MathUtil {
    public static double arrondir(double d){
        return (double)Math.round(d * 10000d) / 10000d;
    }
}
