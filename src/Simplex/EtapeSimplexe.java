package Simplex;

import Models.Matrice;

/**
 * Created by Andrianina_pc on 20/06/2018.
 */
public class EtapeSimplexe implements Cloneable {

    private Matrice matrice;
    private String[] variableH;
    private String[] variableV;
    private InformationEtape infos = new InformationEtape();

    public EtapeSimplexe() {
    }

    public Matrice getMatrice() {
        return matrice;
    }

    public void setMatrice(Matrice matrice) {
        this.matrice = matrice;
    }

    public String[] getVariableH() {
        return variableH;
    }

    public void setVariableH(String[] variableH) {
        this.variableH = variableH;
    }

    public String[] getVariableV() {
        return variableV;
    }

    public void setVariableV(String[] variableV) {
        this.variableV = variableV;
    }

    public InformationEtape getInfos() {
        return infos;
    }

    public void setInfos(InformationEtape infos) {
        this.infos = infos;
    }

    //  tester si toutes les valeurs des max sont negatifs
    public boolean testerMaxAllNeg() {
        boolean ret = true;
        for (int i = 0; i < this.matrice.getColumns() ; i++) {
            if (this.matrice.getValue(this.matrice.getRows() - 1, i) > 0) {
                ret = false;
            }
        }
        return ret;
    }

    //  obtenir la colonne du pivot
    public int getColPivot(int indiceLigne) {
        int indRet = 0;
        double valRet = this.matrice.getValue(indiceLigne, 0);
        for (int i = 1; i < this.matrice.getColumns(); i++) {
            if (valRet < this.matrice.getValue(indiceLigne, i)) {
                valRet = this.matrice.getValue(indiceLigne, i);
                indRet = i;
            }
        }
        return indRet;
    }

    public int getLignePivot() {
        int indValLigneMin = 0;
        int indColPivot = this.infos.getColonnePivot();
        Matrice ma = this.matrice;
        int indColMb2 = ma.getColumns() - 1;

        double temp = Math.abs(ma.getValue(0, indColMb2) / ma.getValue(0, indColPivot));
        double tempi;
        for (int i = 1; i < ma.getRows() - 1; i++) {
            tempi = Math.abs(ma.getValue(i, indColMb2) / ma.getValue(i, indColPivot));
            if (temp > tempi) {
                temp = tempi;
                indValLigneMin = i;
            }
        }
        return indValLigneMin;
    }

    //*****************************************************************************************
    public void setLignePivot() {
        int lignePivot = this.getLignePivot();
        this.infos.setLignePivot(lignePivot);
        this.infos.setHorsBase(this.getVariableV()[lignePivot]);
        this.getVariableV()[lignePivot] = this.infos.getAjoutBase();

    }

    public void setPivot() {
        this.infos.setPivot(this.getMatrice().getValue(this.infos.getLignePivot(), this.infos.getColonnePivot()));
    }

    //    setColonnePivot
    public void setColonnePivot() {
        Matrice m = this.getMatrice();
        int indiceLigne = m.getRows() - 1;
        int indColPivot = this.getColPivot(indiceLigne);
        this.infos.setColonnePivot(indColPivot);
        this.infos.setAjoutBase(this.getVariableH()[indColPivot + 1]);
    }

    //  mise à jour ligne pivot du matrice
    public void miseAJourLignePivot() {
        double pivot = this.getInfos().getPivot();
        Matrice m = this.getMatrice();
        int lignePivot = this.getInfos().getLignePivot();
        double valTemp = 0;

        for (int i = 0; i < m.getColumns(); i++) {
            valTemp = m.getValue(lignePivot, i) / pivot;
            m.setValue(lignePivot, i, valTemp);
        }
    }

    //  Mise à jour ligne par ligne de la matrice
    public void miseAjourLigneMatrice(int indiceLigne, EtapeSimplexe es) {
        Matrice m = this.getMatrice();
        Matrice mpivot = es.getMatrice();

        double val = 0;
        InformationEtape ie = this.getInfos();
        for (int kCol = 0; kCol < m.getColumns(); kCol++) {
            val = ((m.getValue(indiceLigne, kCol) * (ie.getPivot() * 10000)) - ((mpivot.getValue(indiceLigne, ie.getColonnePivot()) * mpivot.getValue(ie.getLignePivot(), kCol) * 10000) ) ) / (ie.getPivot() * 10000);
//            val = ((m.getValue(indiceLigne, kCol) * ie.getPivot()) - ((mpivot.getValue(indiceLigne, ie.getColonnePivot()) - mpivot.getValue(ie.getLignePivot(), kCol)))) / ie.getPivot();
            m.setValue(indiceLigne, kCol, val);
        }
    }

    public void miseAjourAutreLigneMatrice(EtapeSimplexe es) {
        Matrice m = this.getMatrice();
        InformationEtape ie = this.getInfos();

        for (int i = 0; i < m.getRows(); i++) {
            if (i == ie.getLignePivot()) {
                continue;
            }
            miseAjourLigneMatrice(i, es);
        }
    }

    public int getIndiceLigneMaxFractionnaire() {
        Matrice m = this.matrice;
        int ret = 0;
        int colMax = m.getColumns() - 1;
        double fract = m.getFractionnaire(0, colMax);
        double fractTemp = 0;

        for (int i = 1; i < m.getRows(); i++) {
            fractTemp = m.getFractionnaire(i, colMax);
            if (fract < fractTemp) {
                fract = fractTemp;
                ret = i;
            }
        }
        return ret;
    }

    ///////////////////////////////////////////////
    @Override
    public EtapeSimplexe clone() throws CloneNotSupportedException {
        EtapeSimplexe ret = (EtapeSimplexe) super.clone();
        ret.infos = this.infos.clone();
        ret.matrice = this.matrice.clone();
        ret.variableH = new String[this.variableH.length];
        System.arraycopy(this.variableH, 0, ret.variableH, 0, ret.variableH.length);
        ret.variableV = new String[this.variableV.length];
        System.arraycopy(this.variableV, 0, ret.variableV, 0, ret.variableV.length);
        return ret;
    }
}