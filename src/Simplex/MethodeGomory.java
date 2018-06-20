package Simplex;

import Simplex.Math.Inequation;
import Simplex.Objectif.FonctionObjectif;
import Simplex.Result.ResultatSimplexe;

/**
 * Created by Andrianina_pc on 20/06/2018.
 */
public class MethodeGomory {

    private ResultatSimplexe resultSimplexe;
    private String variableAssicierFractElevee;
    private double fractionnaireEleve;
    private String coupe = "";
    private EtapeSimplexe []simplexes;

    public ResultatSimplexe getResultSimplexe() {
        return resultSimplexe;
    }

    public void setResultSimplexe(ResultatSimplexe resultSimplexe) {
        this.resultSimplexe = resultSimplexe;
    }

    public String getVariableAssicierFractElevee() {
        return variableAssicierFractElevee;
    }

    public void setVariableAssicierFractElevee(String variableAssicierFractElevee) {
        this.variableAssicierFractElevee = variableAssicierFractElevee;
    }

    public double getFractionnaireEleve() {
        return fractionnaireEleve;
    }

    public void setFractionnaireEleve(double fractionnaireEleve) {
        this.fractionnaireEleve = fractionnaireEleve;
    }

    public String getCoupe() {
        return coupe;
    }

    public EtapeSimplexe[] getSimplexes() {
        return simplexes;
    }

    public void setSimplexes(EtapeSimplexe[] simplexes) {
        this.simplexes = simplexes;
    }

    public void setCoupe(String coupe) {
        this.coupe = coupe;
    }

    public void setCoupe(Inequation inequationFract, FonctionObjectif fo) {
        for (int i = 0; i < inequationFract.getCoefficients().length - 1; i++) {
            if(!fo.isVarFunct(inequationFract.getVariables()[i])){
                break;
            }

            if((i > 0) && (inequationFract.getCoefficients()[i]>0)){
                coupe += " + ";
            }
            coupe += inequationFract.getCoefficients()[i] + inequationFract.getVariables()[i];
        }
        coupe+= " " + inequationFract.getSigne() + " " + inequationFract.getMbre2();
    }
}