package Simplex;

import Models.Matrice;
import Simplex.Math.Inequation;
import Simplex.Objectif.Contrainte;
import Simplex.Objectif.FonctionObjectif;
import Simplex.Result.ResultatNEGomory;
import Simplex.Result.ResultatSimplexe;
import Simplex.String.StringUtil;

import java.util.List;

/**
 * Created by Andrianina_pc on 20/06/2018.
 */
public class GomoryService {
    private StringUtil strutil = new StringUtil();
    private SimplexeService simplexUtil = new SimplexeService();

    public ResultatNEGomory calculerNEGomory(String z, String contraintes, List<MethodeGomory> mglists) throws CloneNotSupportedException {

        ResultatSimplexe rs = simplexUtil.simplexe(z, contraintes);

        EtapeSimplexe[] ess = rs.getEtape();

        MethodeGomory mg;

        if (rs.getOptimale().testerEntier(0.000009)) {
            ResultatNEGomory res = new ResultatNEGomory();
            res.setOptimaleEnEntier(rs.getOptimale());

            mg = new MethodeGomory();
            mg.setSimplexes(ess);
            mglists.add(mg);

            res.setGomorys(mglists);

            return res;
        }

        mg = this.methodeGomory(ess[ess.length - 1], rs.getFo(), rs);
        mg.setSimplexes(ess);
        mglists.add(mg);

        return this.calculerNEGomory(z, contraintes + ";" + mg.getCoupe(), mglists);
    }

    public MethodeGomory methodeGomory(EtapeSimplexe es, FonctionObjectif fo, ResultatSimplexe rs) {
        MethodeGomory ret = new MethodeGomory();
        Matrice m = es.getMatrice();
        int tcol = m.getColumns();
        int indiceLMaxFract = es.getIndiceLigneMaxFractionnaire();
        String coupe = "";
        Inequation inequationFract = new Inequation(tcol);

        inequationFract.setSigne(">=");
        for (int i = 0; i < es.getMatrice().getColumns() - 1; i++) {
            inequationFract.setVariable(i, es.getVariableH()[i + 1]);
            inequationFract.setCoefficients(i, m.getFractionnaire(indiceLMaxFract, i));
        }

        inequationFract.setVariable(tcol - 1, "c");
        inequationFract.setMbre2(m.getFractionnaire(indiceLMaxFract, tcol - 1));

        Contrainte c[] = fo.getContraintes();

        inequationFract.setVarContrainteZero(fo);

        inequationFract.setConstZero();

        inequationFract.changementDeSigne();

        ret.setVariableAssicierFractElevee(inequationFract.getVariables()[indiceLMaxFract - 1]);
        ret.setFractionnaireEleve(m.getFractionnaire(indiceLMaxFract, tcol - 1));
        ret.setCoupe(inequationFract, fo);
        ret.setResultSimplexe(rs);

        return ret;
    }

}
