package Simplex.Result;

import Simplex.EtapeSimplexe;
import Simplex.Objectif.FonctionObjectif;
import Simplex.OptimaleSimplexe;

import java.util.List;

/**
 * Created by Andrianina_pc on 20/06/2018.
 */
public class ResultatSimplexe {
    private EtapeSimplexe[] etapes;
    private OptimaleSimplexe optimale;
    private FonctionObjectif fo;

    public ResultatSimplexe() {}

    public EtapeSimplexe[] getEtape() {
        return etapes;
    }

    public void setEtape(EtapeSimplexe[] etape) {
        this.etapes = etape;
    }

    public void setEtape(List<EtapeSimplexe> etapes) {
        this.etapes = (EtapeSimplexe[]) (etapes.toArray(new EtapeSimplexe[etapes.size()]));
    }

    public OptimaleSimplexe getOptimale() {
        return optimale;
    }

    public void setOptimale(OptimaleSimplexe optimale) {
        this.optimale = optimale;
    }

    public EtapeSimplexe[] getEtapes() {
        return etapes;
    }

    public void setEtapes(EtapeSimplexe[] etapes) {
        this.etapes = etapes;
    }

    public FonctionObjectif getFo() {
        return fo;
    }

    public void setFo(FonctionObjectif fo) {
        this.fo = fo;
    }
}
