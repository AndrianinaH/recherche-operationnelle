package Simplex.Objectif;

/**
 * Created by Andrianina_pc on 20/06/2018.
 */
public class FonctionObjectif {
    private String nomFonction;
    private String[] variables;
    private double[] coefficientVariables;
    private Contrainte[] contraintes;
    private int type; // 1 = maximiser et -1 = minimiser

    public FonctionObjectif() {}

    public FonctionObjectif(String nomFonction) {
        this.nomFonction = nomFonction;
    }

    public int getIndiceVar(String var) {
        for (int i = 0; i < this.variables.length; i++) {
            if (this.variables[i].equals(var)) {
                return i;
            }
        }
        return 1;
    }

    //Getters setters
    public String getNomFonction() {
        return nomFonction;
    }

    public void setNomFonction(String nomFonction) {
        this.nomFonction = nomFonction;
    }

    public String[] getVariables() {
        return variables;
    }

    public void setVariables(String[] variables) {
        this.variables = variables;
    }

    public double[] getCoefficientVariables() {
        return coefficientVariables;
    }

    public void setCoefficientVariables(double[] coefficientVariables) {
        this.coefficientVariables = coefficientVariables;
    }

    public Contrainte[] getContraintes() {
        return contraintes;
    }

    public void setContraintes(Contrainte[] contraintes) {
        this.contraintes = contraintes;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isVarFunct(String str) {
        for (String variable : this.variables) {
            if (str.equals(variable)) {
                return true;
            }
        }
        return false;
    }

}