package Hongroise;

/**
 * Created by Andrianina_pc on 13/06/2018.
 */
public class Result {
    private int X;
    private int Y;
    private int value;

    public Result(int x, int y, int value) {
        X = x;
        Y = y;
        this.value = value;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
