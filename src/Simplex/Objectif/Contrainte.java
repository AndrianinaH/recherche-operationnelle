package Simplex.Objectif;

import Simplex.String.StringUtil;

/**
 * Created by Andrianina_pc on 20/06/2018.
 */
public class Contrainte {

    private StringUtil strutil = new StringUtil();

    private double[] coefficientsContrainte;
    private String signe;
    private double membre2;

    private FonctionObjectif fo;

    public Contrainte() {

    }

    public Contrainte(int tailleCoefficientContrainte, FonctionObjectif fo) {
        this.fo = fo;
        this.coefficientsContrainte = new double[tailleCoefficientContrainte];
        for (int i = 0; i < tailleCoefficientContrainte; i++) {
            this.coefficientsContrainte[i] = 0;
        }
    }

    public double[] getCoefficientsContrainte() {
        return coefficientsContrainte;
    }

    public void setCoefficientsContrainte(double[] coefficientsContrainte) {
        this.coefficientsContrainte = coefficientsContrainte;
    }

    public void setCoefficientsContrainte(String[] coefficientsContrainte, Contrainte[] contraintesO, int i) {
        String nbrVar[];
        for (String coefficientsContrainte1 : coefficientsContrainte) {
            nbrVar = strutil.separerNbrVar(coefficientsContrainte1);
            this.coefficientsContrainte[this.fo.getIndiceVar(nbrVar[1])] = Double.parseDouble(nbrVar[0]);
        }
        this.coefficientsContrainte[contraintesO[0].getFo().getVariables().length + i] = 1;
    }

    public String getSigne() {
        return signe;
    }

    public void setSigne(String signe) {
        this.signe = signe;
    }

    public double getMembre2() {
        return membre2;
    }

    public void setMembre2(double membre2) {
        this.membre2 = membre2;
    }

    public FonctionObjectif getFo() {
        return fo;
    }
}