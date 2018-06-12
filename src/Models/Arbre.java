package Models;

import java.util.*;
/**
 * Created by Andrianina_pc on 05/02/2018.
 */

public class Arbre<T> extends HashMap<T,T>
{
    T racine;
    public Arbre(){}
    public Arbre(T racine)
    {
        this.racine=racine;
        put(racine,null);
    }

    public boolean findValue(T value)
    {
        return containsValue(value);
    }

    public boolean findKey(T value)
    {
        return containsKey(value);
    }

    public void ajouter(T key,T value)
    {
        if(!findKey(key))
        {
            put(key,value);
        }
    }

    public void supprimer(T value)
    {
        T key=(T)this.keySet().toArray();
    }

    public boolean isFeuille(T value)
    {
        return this.containsValue(value);
    }

}