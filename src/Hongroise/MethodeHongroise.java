package Hongroise;

import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrianina_pc on 11/06/2018.
 */
public class MethodeHongroise {

    private List<List<Case>> tableauInitiale = new ArrayList<List<Case>>();;
    private int init = 5;
    private int coutMinimalAffecation = 0;


    //------------------------------- Initialisation
    public MethodeHongroise(){}

    public MethodeHongroise(int init){
        this.init = init;
    }

    public void setTableauInitiale(int[][] tab) {
        for(int i = 0; i<this.init; i++){
            this.tableauInitiale.add(new ArrayList<Case>());
            for(int j = 0; j<this.init; j++){
                tableauInitiale.get(i).add(new Case(tab[i][j]));
            }
        }
    }

    public List<List<Case>> getTableauInitiale() {
        return tableauInitiale;
    }

    public void setTableauInitiale(List<List<Case>> tableauInitiale) {
        this.tableauInitiale = tableauInitiale;
    }

    public int getInit() {
        return init;
    }

    public void setInit(int init) {
        this.init = init;
    }

    public int getCoutMinimalAffecation() {
        return coutMinimalAffecation;
    }

    public void setCoutMinimalAffecation(int coutMinimalAffecation) {
        this.coutMinimalAffecation = coutMinimalAffecation;
    }

    //---------------------------- Afficher Tableau
    public void afficherTableau(List<List<Case>> tabs){
        System.out.println("---------- Affichage tableau ------------");
        for (List<Case> tab : tabs){
            for (Case data : tab){
                if(data.isEncader() && data.isMarquer() && data.isRayer()){
                    System.out.print("{([");
                    System.out.print(data.getValue()+"])}");
                }
                else if(data.isBarrer() && data.isMarquer() && data.isRayer()){
                    System.out.print("{(%");
                    System.out.print(data.getValue()+"%)}");
                }
                else if(data.isEncader() && data.isMarquer()){
                    System.out.print(" ([");
                    System.out.print(data.getValue()+"])");
                }
                else if(data.isBarrer() && data.isMarquer()){
                    System.out.print(" (%");
                    System.out.print(data.getValue()+"%)");
                }
                else if(data.isRayer() && data.isMarquer()){
                    System.out.print(" {(");
                    System.out.print(data.getValue()+")}");
                }
                else if(data.isRayer() && data.isEncader()){
                    System.out.print(" {[");
                    System.out.print(data.getValue()+"]}");
                }
                else if(data.isRayer() && data.isBarrer()){
                    System.out.print(" {%");
                    System.out.print(data.getValue()+"%}");
                }
                else if(data.isEncader()){
                    System.out.print("   [");
                    System.out.print(data.getValue()+"]");
                }
                else if(data.isBarrer()){
                    System.out.print("   %");
                    System.out.print(data.getValue()+"%");
                }
                else if(data.isMarquer()){
                    System.out.print("   (");
                    System.out.print(data.getValue()+")");
                }
                else if(data.isRayer()){
                    System.out.print("   {");
                    System.out.print(data.getValue()+"}");
                }
                else{
                    System.out.print("    ");
                    System.out.print(data.getValue());
                }
            }
            System.out.println();
        }
    }

    //----------------------------- Final

    //------------- minimisation
    public int minimisationAffectation(List<List<Case>> tabs){
        this.methodeHongroise(tabs);
        return this.getCoutMinimalAffecation();
    }
    //------------- maximisation
    public int maximisationAffectation(List<List<Case>> tabs){
        List<List<Case>> tabOpposer = this.getTabOpposer(tabs);
       this.methodeHongroise(tabOpposer);
        return this.getCoutMinimalAffecation();
    }

    public void methodeHongroise(List<List<Case>> tabs){
        //------------- Etape 0
        List<List<Case>> tableau =  this.soustraireMin(tabs);
        this.afficherTableau(tableau);
        //------------- Etape 1
        this.encadrerZero(tableau);
        this.afficherTableau(tableau);

        if(this.isFinish(tableau)){
            System.out.println("vita");
            this.afficherTableau(tableau);
            System.out.println("tableau initiale");
            this.afficherTableau(this.tableauInitiale);
            //--------------- get cout minimum
            List<Result> result = this.getResult(tableau);
            this.setCoutMinimalAffecation(this.getCoutMinimal(result));

        }else{
            //------------ Etape 2
            System.out.println("tsy vita miditra Etape 2");
            this.marquer(tableau);
            this.afficherTableau(tableau);

            //------------ Etape 3
            this.rayer(tableau);
            this.afficherTableau(tableau);

            int minSousTableauRestant = this.getMinSousTableauRestant(tableau);
            System.out.println("min sous tableau restant= "+minSousTableauRestant);

            this.soustraireMinAuTableauRestant(tableau,minSousTableauRestant);
            this.afficherTableau(tableau);

            this.ajouterMinAuCaseMarquer2(tableau,minSousTableauRestant);
            this.reninitMarquage(tableau);
            this.afficherTableau(tableau);

            //--------------- refaire jusqu'à arriver à "vita"
            this.methodeHongroise(tableau);
        }
    }

    //----------------------------- Etape 0 :

    //Soustraire le minimum de chaque ligne et le minimum de chaque colonne.

    public List<List<Case>> soustraireMin(List<List<Case>> tabInitial){
        List<List<Case>> tableauSous = this.copieList(tabInitial);
        //------------ soustraire min line
        for (int i = 0; i<this.init; i++) {
            Case minLine = getMin(tabInitial.get(i));
            for(int j = 0; j<this.init; j++){
                tableauSous.get(i).set(j,new Case(tableauSous.get(i).get(j).getValue() - minLine.getValue()));
            }
        }
        //------------ soustraire min colonne
        this.reverseTableau(tableauSous);
        for (int i = 0; i<this.init; i++) {
            Case minLine = getMin(tableauSous.get(i));
            for(int j = 0; j<this.init; j++){
                tableauSous.get(i).set(j,new Case(tableauSous.get(i).get(j).getValue() - minLine.getValue()));
            }
        }
        this.reverseTableau(tableauSous);

        return tableauSous;
    }

    //------------ getMin
    public Case getMin(List<Case> allData){
        Case ret = allData.get(0);
        for (int i = 1; i < allData.size(); i++){
            if(allData.get(i).getValue() < ret.getValue()) ret = allData.get(i);
        }
        return ret;
    }

    //----------- reverse tableau pour manipuler facilement les colonnes
    public void reverseTableau(List<List<Case>> tabs){
        List<List<Case>> tableauRet = new ArrayList<List<Case>>();
        for (int i = 0; i<this.init; i++) {
            List<Case> tempList = new ArrayList<Case>();
            for(int j = 0; j<this.init; j++){
                tempList.add(tabs.get(j).get(i));
            }
            tableauRet.add(tempList);
        }
        tabs.clear();
        tabs.addAll(tableauRet);
    }

    //----------- copier List dans une autre list
    public List<List<Case>> copieList(List<List<Case>> tabs){
        List<List<Case>> tableauRet = new ArrayList<List<Case>>();
        for (int i = 0; i<this.init; i++) {
            List<Case> tempList = new ArrayList<Case>();
            for(int j = 0; j<this.init; j++){
                tempList.add(tabs.get(i).get(j));
            }
            tableauRet.add(tempList);
        }
       return tableauRet;
    }

    //----------------------------- Etape 1 :

    //------ -1
    // Parcourir les lignes et encadrer un des zéros de cette ligne (arbitrairement le plus à gauche).
    // Puis barrer tous les zéros se trouvant sur la même ligne ou sur la même colonne que le zéro encadré.
    //------ -2.
    // On recommence l'opération jusqu'à ce qu'on ne puisse plus encadrer, ni barrer de zéros
    //------ -3.
    // Si l'on a encadré un zéro par ligne et par colonne, c'est terminé, on a la solution optimale.
    // Sinon, on passe à l'étape 2.

    public void encadrerZero(List<List<Case>> tabs){
        for(int i = 0; i < this.init; i++){
            if(ligneEncadrable(tabs.get(i))){
                for(int j = 0; j < this.init; j++){
                    //------ encadrer Zero ligne
                    if(tabs.get(i).get(j).getValue() == 0 && !tabs.get(i).get(j).isBarrer()){
                        tabs.get(i).get(j).setEncader(true);
                        barrerZeroLigne(tabs.get(i));
                        barrerZeroColonne(tabs,j);
                    }
                }
            }
//            this.afficherTableau(tabs);
        }
    }

    public boolean ligneEncadrable(List<Case> list_case){
        for (Case cas : list_case) {
            if (cas.isEncader()) return false;
        }
        return true;
    }

    public void barrerZeroLigne(List<Case> list_case){
        for (Case cas : list_case) {
            if(cas.getValue() == 0 && !cas.isEncader()) cas.setBarrer(true);
        }
    }

    public void barrerZeroColonne(List<List<Case>> tabs, int col){
        for(int i = 0; i < this.init; i++){
            if(tabs.get(i).get(col).getValue() == 0 && !tabs.get(i).get(col).isEncader()) {
                tabs.get(i).get(col).setBarrer(true);
            }
        }
    }

    //Si l'on a encadré un zéro par ligne et par colonne, c'est terminé, on a la solution optimale.
    // Sinon, on passe à l'étape 2.
    public boolean isFinish(List<List<Case>> tabs){
        for(int i = 0; i < this.init; i++){
            if(!isFinishLine(tabs,i) && !isFinishColonne(tabs,i)) return false;
        }
        return true;
    }

    public boolean isFinishLine(List<List<Case>> tabs, int line){
        for(int i = 0; i < this.init; i++){
            if(tabs.get(line).get(i).getValue() == 0 && tabs.get(line).get(i).isEncader()){
                return true;
            }
        }
        return false;
    }

    public boolean isFinishColonne(List<List<Case>> tabs, int col){
        for(int i = 0; i < this.init; i++){
            if(tabs.get(i).get(col).getValue() == 0 && tabs.get(i).get(col).isEncader()){
                return true;
            }
        }
        return false;
    }

    //----------------------------- Etape 2 :
    //Marquage des lignes et des colonnes
    // 1. Marquer toute ligne n’ayant pas de zéro encadré,
    // 2. Marquer toute colonne ayant un zéro barré sur une ligne marquée,
    // 3. Marquer toute ligne ayant un zéro encadré dans une colonne marquée
    // et revenir en 2 jusqu’à ce que le marquage ne soit plus possible,

    public void marquer(List<List<Case>> tabs){
        //---------- marquer ligne 2.1
        for(int i = 0; i < this.init; i++){
            for(int j = 0; j < this.init; j++){
                if(isFinishLine(tabs,i)){
                   break;
                }else {
                    tabs.get(i).get(j).setMarquer(true);
                }
            }
        }

        //---------- marquer colonne 2.2
        for(int i = 0; i < this.init; i++){
            for(int j = 0; j < this.init; j++){
                if(tabs.get(i).get(j).getValue() == 0 && tabs.get(i).get(j).isBarrer() && tabs.get(i).get(j).isMarquer()){
                    this.marquerColonne(tabs,j);
                }

            }
        }
//        //--- marquer ligne 2.3
        for(int i = 0; i < this.init; i++){
            for(int j = 0; j < this.init; j++){
                if(tabs.get(i).get(j).getValue()==0 && tabs.get(i).get(j).isEncader() && tabs.get(i).get(j).isMarquer()) {
                    marquerLigne(tabs,i);
                }
            }
        }
    }

    //-------- Marquer colonne
    public void marquerColonne(List<List<Case>> tabs, int col){
        for(int i = 0; i < this.init; i++){
            tabs.get(i).get(col).setMarquer(true);
        }
    }

    //-------- Marquer ligne
    public void marquerLigne(List<List<Case>> tabs, int line){
        for(int i = 0; i < this.init; i++){
            tabs.get(line).get(i).setMarquer(true);
        }
    }

    //----------------------------- Etape 3 :
    //Mise à jour du tableau
    // 1. Rayer les lignes non marquées et les colonnes marquées,
    // 2. Retrancher le plus petit élément du sous-tableau restant à tous
    // les  éléments non rayés et ajouter le aux éléments rayés 2 fois.

    public void rayer(List<List<Case>> tabs){

        for(int i = 0; i < this.init; i++){
            //-------- rayer lignes non marquées
            rayerLigne(tabs,i);
        }
        for (int j = 0; j < this.init; j++){
            //------- rayer les colonnes marquées
            rayerColonne(tabs,j);
        }
    }

    //-------- Rayer les ligne 3.1.1
    public void rayerLigne(List<List<Case>> tabs, int line){
        if(testLigneRayable(tabs,line)){
            for(int i = 0; i < this.init; i++){
                tabs.get(line).get(i).setRayer(true);
            }
        }
    }

    //-------- test si les lignes sont rayable
    public boolean testLigneRayable(List<List<Case>> tabs, int line){
        for(int i = 0; i < this.init; i++){
            if(!tabs.get(line).get(i).isMarquer())return true;
        }
        return false;
    }

    //-------- Rayer les colonnes 3.1.2
    public void rayerColonne(List<List<Case>> tabs, int col){
        if(testColonneRayable(tabs,col)){
            for(int i = 0; i < this.init; i++){
                if(tabs.get(i).get(col).isRayer()){
                    tabs.get(i).get(col).setRayer2(true);
                }else{
                    tabs.get(i).get(col).setRayer(true);
                }
            }
        }
    }

    //-------- test si les colonnes sont rayable
    public boolean testColonneRayable(List<List<Case>> tabs, int col){
        for(int i = 0; i < this.init; i++){
            if(!tabs.get(i).get(col).isMarquer())return false;
        }
        return true;
    }

    //Retrancher le plus petit élément du sous-tableau restant à tous
    // les  éléments non rayés

    //get min sous tableau restant 3.2
    public int getMinSousTableauRestant(List<List<Case>> tabs){
        int ret = 1000000;
        for(int i = 0; i < this.init; i++){
            for(int j = 0; j < this.init; j++){
                if(!tabs.get(i).get(j).isRayer()) {
                    if (ret > tabs.get(i).get(j).getValue()) ret = tabs.get(i).get(j).getValue();
                }
            }
        }
        return ret;
    }

    //soustrait le min au sous tableau restant 3.2
    public void soustraireMinAuTableauRestant(List<List<Case>> tabs, int min){
        List<List<Case>> tabTemp = tabs;
        for(int i = 0; i < this.init; i++){
            for(int j = 0; j < this.init; j++){
                if(!tabs.get(i).get(j).isRayer()) {
                    tabs.get(i).get(j).setValue(tabTemp.get(i).get(j).getValue()- min);
                }
            }
        }
    }

    //ajouter le min au case doublement marqué 3.2
    public void ajouterMinAuCaseMarquer2(List<List<Case>> tabs, int min){
        List<List<Case>> tabTemp = tabs;
        for(int i = 0; i < this.init; i++){
            for(int j = 0; j < this.init; j++){
                if(tabs.get(i).get(j).isRayer2()) {
                    tabs.get(i).get(j).setValue(tabTemp.get(i).get(j).getValue()+ min);
                }
            }
        }
    }

    //-------------- reninialize les marquages
    public void reninitMarquage(List<List<Case>> tabs){
        for(int i = 0; i < this.init; i++){
            for(int j = 0; j < this.init; j++){
                tabs.get(i).get(j).setEncader(false);
                tabs.get(i).get(j).setBarrer(false);
                tabs.get(i).get(j).setMarquer(false);
                tabs.get(i).get(j).setRayer(false);
                tabs.get(i).get(j).setRayer2(false);
            }
        }
    }


    //--------------------------- Coût minimum de l'affectation

    //get result by coordonnée 0 encadrer
    public List<Result> getResult(List<List<Case>> tabs){
        List<Result> list_ret = new ArrayList<Result>();
        for(int i = 0; i < this.init; i++){
            for(int j = 0; j < this.init; j++){
                if(tabs.get(i).get(j).getValue()==0 && tabs.get(i).get(j).isEncader()){
                    list_ret.add(new Result(i,j, this.tableauInitiale.get(i).get(j).getValue()));
                }
            }
        }
        return list_ret;
    }

    //-------------- get coût minimum de l’affectation
    //-------------- coût minimal d'un tableau = côut maximal du tableau opposé
    public int getCoutMinimal(List<Result> list_ret){
        int ret = 0;
        for (Result result: list_ret) {
            ret = ret + result.getValue();
        }
        return ret;
    }

    //--------------------------- Coût maximal de l'affectation

    //-------------- get coût minimum de l’affectation
    //-------------- coût minimal d'un tableau = côut maximal du tableau opposé
    public int getMaximalTableau(List<List<Case>> tabs){
        int ret = 0;
        for (int i = 0; i < this.init; i++) {
            for (int j = 0; j < this.init; j++) {
                if(ret < tabs.get(i).get(j).getValue()){
                    ret = tabs.get(i).get(j).getValue();
                }
            }
        }
        return ret;
    }

    //-------------- get tab opposé du tableau initial
    public List<List<Case>> getTabOpposer(List<List<Case>> tabs){
        List<List<Case>> tableauPlus = new ArrayList<List<Case>>();
        int max = this.getMaximalTableau(tabs);
        for (int i = 0; i < this.init; i++) {
            List<Case> tempList = new ArrayList<Case>();
            for (int j = 0; j < this.init; j++) {
                tempList.add(new Case(max - tabs.get(i).get(j).getValue()));
            }
            tableauPlus.add(tempList);
        }
        return tableauPlus;
    }


}
