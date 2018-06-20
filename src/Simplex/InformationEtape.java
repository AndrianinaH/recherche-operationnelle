package Simplex;

/**
 * Created by Andrianina_pc on 20/06/2018.
 */
public class InformationEtape implements Cloneable{
    private  double pivot;
    private int lignePivot;
    private int colonnePivot;
    private String ajoutBase;
    private String horsBase;

    public InformationEtape() {}

    public double getPivot() {
        return pivot;
    }

    public void setPivot(double pivot) {
        this.pivot = pivot;
    }

    public int getLignePivot() {
        return lignePivot;
    }

    public void setLignePivot(int lignePivot) {
        this.lignePivot = lignePivot;
    }

    public int getColonnePivot() {
        return colonnePivot;
    }

    public void setColonnePivot(int colonnePivot) {
        this.colonnePivot = colonnePivot;
    }

    public String getAjoutBase() {
        return ajoutBase;
    }

    public void setAjoutBase(String ajoutBase) {
        this.ajoutBase = ajoutBase;
    }

    public String getHorsBase() {
        return horsBase;
    }

    public void setHorsBase(String horsBase) {
        this.horsBase = horsBase;
    }


    @Override
    public InformationEtape clone() throws CloneNotSupportedException {
        InformationEtape ret = (InformationEtape)super.clone();
        return ret;
    }
}