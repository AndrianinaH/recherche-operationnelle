package Models;

/**
 * Created by Andrianina_pc on 02/07/2018.
 */
public class Ordonnancement {

    private String tache;
    private String description;
    private int duree;
    private String tacheAnterieur;

    public Ordonnancement(String tache, String description, String duree, String tacheAnterieur) throws Exception {
        this.setTache(tache);
        this.description = description;
        this.setDuree(duree);
        this.setTacheAnterieur(tacheAnterieur);
    }

    public String getTache() {
        return tache;
    }

    public void setTache(String tache) throws Exception {
        if (tache.isEmpty()) {
            throw new Exception("Une case tâche est vide !");
        }
        this.tache = tache;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(String duree) throws Exception {
        if (tache.isEmpty()) {
            throw new Exception("Une case durée est vide !");
        }
        this.duree = Integer.parseInt(duree);
    }

    public String getTacheAnterieur() {
        return tacheAnterieur;
    }

    public String[] getTacheAnterieurs() {
        return tacheAnterieur.split("[\\p{Punct}\\s]+");
    }

    public void setTacheAnterieur(String tacheAnterieur) throws Exception {
        String[] taches = tacheAnterieur.trim().split("[\\p{Punct}\\s]+");
        for (String t : taches) {
            if (t.equals(this.tache)) {
                throw new Exception("Une case tâche anterieur contienne son propre tâche");
            }
        }
        this.tacheAnterieur = tacheAnterieur;
    }
}
