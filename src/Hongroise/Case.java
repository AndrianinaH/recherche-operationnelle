package Hongroise;

/**
 * Created by Andrianina_pc on 11/06/2018.
 */
public class Case {
    private int value;
    private boolean encader = false;
    private boolean barrer = false;
    private boolean marquer = false;
    private boolean rayer = false;
    private boolean rayer2 = false;

    public Case(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isEncader() {
        return encader;
    }

    public void setEncader(boolean encader) {
        this.encader = encader;
    }

    public boolean isBarrer() {
        return barrer;
    }

    public void setBarrer(boolean barrer) {
        this.barrer = barrer;
    }

    public boolean isMarquer() {
        return marquer;
    }

    public void setMarquer(boolean marquer) {
        this.marquer = marquer;
    }

    public boolean isRayer() {
        return rayer;
    }

    public void setRayer(boolean rayer) {
        this.rayer = rayer;
    }

    public boolean isRayer2() {
        return rayer2;
    }

    public void setRayer2(boolean rayer2) {
        this.rayer2 = rayer2;
    }
}
