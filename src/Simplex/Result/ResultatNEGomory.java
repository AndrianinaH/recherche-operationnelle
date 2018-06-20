package Simplex.Result;

import Simplex.MethodeGomory;
import Simplex.OptimaleSimplexe;

import java.util.List;

/**
 * Created by Andrianina_pc on 20/06/2018.
 */
public class ResultatNEGomory {
    MethodeGomory[]gomorys;
    private OptimaleSimplexe optimaleEnEntier;

    public MethodeGomory[] getGomorys() {
        return gomorys;
    }

    public void setGomorys(MethodeGomory[] gomorys) {
        this.gomorys = gomorys;
    }

    public void setGomorys(List<MethodeGomory> goms) {
        this.gomorys = (MethodeGomory[]) (goms.toArray(new MethodeGomory[goms.size()]));
    }

    public OptimaleSimplexe getOptimaleEnEntier() {
        return optimaleEnEntier;
    }

    public void setOptimaleEnEntier(OptimaleSimplexe optimaleEnEntier) {
        this.optimaleEnEntier = optimaleEnEntier;
    }
}

