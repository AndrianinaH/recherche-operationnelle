package Simplex;

import Models.Matrice;
import Simplex.Objectif.Contrainte;
import Simplex.Objectif.FonctionObjectif;
import Simplex.Result.ResultatSimplexe;
import Simplex.String.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrianina_pc on 20/06/2018.
 */
public class SimplexeService {
    private StringUtil strutil = new StringUtil();


    //---- typeOptim = "maximiser ou minimiser"
    //---- z = fonction objectif à optimiser
    public ResultatSimplexe simplexe(String z, String contrainte) throws CloneNotSupportedException {
        String[] contraintes = contrainte.trim().split(";");

        FonctionObjectif fo = this.toObject(z, contraintes);

        EtapeSimplexe etape0 = this.fOToEtapeSimplexe0(fo);

        ResultatSimplexe res = new ResultatSimplexe();

        List<EtapeSimplexe> etape0l = new ArrayList<>();
        etape0l.add(etape0);

        List<EtapeSimplexe> etapes = this.optimiser(etape0, etape0l);

        res.setEtape(etapes);

        OptimaleSimplexe os = new OptimaleSimplexe(fo.getVariables());
        os.setValeurs(etapes.get(etapes.size() - 1));
        os.setZ(etapes.get(etapes.size() - 1));

        res.setOptimale(os);
        res.setFo(fo);

        return res;
    }

    //--- inputToObject
    private FonctionObjectif toObject(String z, String[] contraintes) {
        String[] ztemps = z.split("=");
        FonctionObjectif fo = new FonctionObjectif(ztemps[0].trim());
        Contrainte[] contraintesO = new Contrainte[contraintes.length];

        String res = strutil.formaterEquationLineaire(ztemps[1]);

        String[] nbrVar = res.split(" ");
        String[] nbrVarSepare;

        String[] variable = new String[nbrVar.length];
        double[] valeur = new double[nbrVar.length];

        for (int i = 0; i < variable.length; i++) {
            nbrVarSepare = strutil.separerNbrVar(nbrVar[i]);
            variable[i] = nbrVarSepare[1];
            valeur[i] = Double.parseDouble(nbrVarSepare[0]);
        }

        fo.setVariables(variable);
        fo.setCoefficientVariables(valeur);

        for (int i = 0; i < contraintesO.length; i++) {
            nbrVarSepare = strutil.separerContrainte(contraintes[i]);
            contraintesO[i] = new Contrainte(variable.length + contraintes.length, fo);
            contraintesO[i].setSigne(nbrVarSepare[2]);
            contraintesO[i].setCoefficientsContrainte(strutil.formaterEquationLineaire(nbrVarSepare[0]).split(" "), contraintesO, i);
            contraintesO[i].setMembre2(Double.parseDouble(nbrVarSepare[1]));
        }

        fo.setContraintes(contraintesO);
        return fo;
    }

    //    **************************************************************************************
    public EtapeSimplexe fOToEtapeSimplexe0(FonctionObjectif fo) {
        EtapeSimplexe es = new EtapeSimplexe();

        String[] variableH = setVariableH(fo);
        String[] variableV = setVariableV(fo);
        Matrice matrice = setMatrice(fo);

        es.setVariableH(variableH);
        es.setVariableV(variableV);
        es.setMatrice(matrice);

        return es;
    }

    private String[] setVariableH(FonctionObjectif fo) {
        String[] variableH = new String[fo.getVariables().length + fo.getContraintes().length + 1];

        variableH[0] = "Variable";
        int indiceV = 1;

        for (String variable : fo.getVariables()) {
            variableH[indiceV] = variable;
            indiceV++;
        }

        for (int i = 0; i < fo.getContraintes().length; i++) {
            variableH[indiceV] = "e" + (i + 1);
            indiceV++;
        }

        return variableH;
    }

    private String[] setVariableV(FonctionObjectif fo) {
        int t = fo.getContraintes().length;
        String[] variableV = new String[t + 1];

        for (int i = 0; i < t; i++) {
            variableV[i] = "e" + (i + 1);
        }

        variableV[t] = "Max";

        return variableV;
    }

    private Matrice setMatrice(FonctionObjectif fo) {
        Matrice matrice = new Matrice(fo.getContraintes().length + 1, fo.getVariables().length + fo.getContraintes().length + 1);

        for (int i = 0; i < matrice.getRows() - 1; i++) {
            for (int j = 0; j < matrice.getColumns() - 1; j++) {
                matrice.setValue(i, j, fo.getContraintes()[i].getCoefficientsContrainte()[j]);
            }
            matrice.setValue(i, matrice.getColumns() - 1, fo.getContraintes()[i].getMembre2());
        }

        for (int i = 0; i < fo.getCoefficientVariables().length; i++) {
            matrice.setValue(matrice.getRows() - 1, i, fo.getCoefficientVariables()[i]);
        }

        return matrice;
    }

    //    **************************************************************************************
//  optimiser
    public List<EtapeSimplexe> optimiser(EtapeSimplexe es, List<EtapeSimplexe> lists) throws CloneNotSupportedException {
        EtapeSimplexe esc = es.clone();
        this.maximiser(esc, es);
        lists.add(esc);

        if (esc.testerMaxAllNeg()) {
            return lists;
        }
        return optimiser(esc, lists);
    }

    //  maximisation **************************************************************************
    private EtapeSimplexe maximiser(EtapeSimplexe copie, EtapeSimplexe es) throws CloneNotSupportedException {
        copie.setColonnePivot();
        copie.setLignePivot();
        copie.setPivot();
        copie.miseAJourLignePivot();
        copie.miseAjourAutreLigneMatrice(es);
        return copie;
    }
}