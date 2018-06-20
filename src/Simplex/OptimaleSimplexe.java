package Simplex;

import Models.Matrice;

/**
 * Created by Andrianina_pc on 20/06/2018.
 */
public class OptimaleSimplexe {

    private String[] variables;
    private double[] valeurs;
    private double z;

    public OptimaleSimplexe(String[] variables) {
        this.variables = variables;
        this.valeurs = new double[variables.length];
        for (int i = 0; i < this.valeurs.length; i++) {
            valeurs[i] = 0;
        }
    }

    public OptimaleSimplexe(String[] variables, double[] valeurs, double z) {
        this.variables = variables;
        this.valeurs = valeurs;
        this.z = z;
    }

    public String[] getVariables() {
        return variables;
    }

    public void setVariables(String[] variables) {
        this.variables = variables;
    }

    public double[] getValeurs() {
        return valeurs;
    }

    public double getValeursArrondi(int i) {
        return Math.round(valeurs[i]);
    }

    public void setValeurs(double[] valeurs) {
        this.valeurs = valeurs;
    }

    public void setValeurs(EtapeSimplexe es) {
        Matrice m = es.getMatrice();
        String[] vars = es.getVariableV();

        for (int i = 0; i < this.valeurs.length; i++) {
            for (int j = 0; j < vars.length; j++) {
                if (this.variables[i].equals(vars[j])) {
                    this.valeurs[i] = m.getValue(j, m.getColumns() - 1);
                }
            }
        }
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setZ(EtapeSimplexe es) {
        Matrice m = es.getMatrice();
        this.z = -m.getValue(m.getRows() - 1, m.getColumns() - 1);
    }


    //  tester si c'est un entier avec une variable erreur
    public boolean testerEntier(double erreur){
        double round = Math.round(z);

        if((Math.abs(z - round)) > erreur) return false;

        for(int i=0; i < this.valeurs.length;i++){
            round = this.valeurs[i];
            if(Math.abs(this.valeurs[i] - round) > erreur){
                return false;
            }
        }
        return true;
    }
}