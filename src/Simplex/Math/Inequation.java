package Simplex.Math;

import Simplex.Objectif.Contrainte;
import Simplex.Objectif.FonctionObjectif;

/**
 * Created by Andrianina_pc on 20/06/2018.
 */
public class Inequation {

    private String[] variables;
    private double[] coefficients;
    private String signe;
    private double mbre2;

    public Inequation(String[] variables, double[] coefficients, String signe) {
        this.variables = variables;
        this.coefficients = coefficients;
        this.signe = signe;
    }

    public Inequation(int taille) {
        this.variables = new String[taille];
        this.coefficients = new double[taille];
    }

    public String[] getVariables() {
        return variables;
    }

    public void setVariables(String[] variables) {
        this.variables = variables;
    }

    public void setVariable(int indice, String variable) {
        this.variables[indice] = variable;
    }

    public double[] getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public void setCoefficients(int indice, double coefficient) {
        this.coefficients[indice] = coefficient;
    }

    public String getSigne() {
        return signe;
    }

    public void setSigne(String signe) {
        this.signe = signe;
    }

    public double getMbre2() {
        return mbre2;
    }

    public void setMbre2(double mbre2) {
        this.mbre2 = mbre2;
    }

    //  setter la variable constante c à 0
    public void setConstZero(){
        int tc = this.coefficients.length;
        this.setMbre2(mbre2 + this.coefficients[tc - 1]);
        this.coefficients[tc - 1] = 0;
    }

    //setter 0 les contraintes de l'inéquation
    public void setVarContrainteZero(FonctionObjectif fo) {

        int indiceContrainte = 0;
        String inequationVar[] = this.getVariables();
        for (int i = 0; i < inequationVar.length - 1; i++) {
            if (!fo.isVarFunct(inequationVar[i])) {
                setVarContrainte(i, fo.getContraintes()[indiceContrainte], fo);
                indiceContrainte++;
            }
        }
    }

    private void setVarContrainte(int indiceInequVar, Contrainte contrainte, FonctionObjectif fo) {
        double coefficientContrainte[] = contrainte.getCoefficientsContrainte();
        double tempValIneq;

        String inequationVar[] = this.getVariables();

        for (int i = 0; i < inequationVar.length; i++) {
            if (!fo.isVarFunct(inequationVar[i])) {
                break;
            }
            tempValIneq = this.coefficients[i] + (-this.coefficients[indiceInequVar] * coefficientContrainte[i]);
            this.setCoefficients(i, tempValIneq);
        }

        tempValIneq = this.coefficients[inequationVar.length - 1] + (-this.coefficients[indiceInequVar] * contrainte.getMembre2());
        this.setCoefficients(inequationVar.length - 1, tempValIneq);
        this.setCoefficients(indiceInequVar, 0);
    }

    //changement de signe
    public void changementDeSigne() {
        for(int i=0; i < this.coefficients.length;i++){
            this.coefficients[i] *= -1 ;
        }

        this.signe = "<=";
        this.mbre2 *= -1;
    }
}