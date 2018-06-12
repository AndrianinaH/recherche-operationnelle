package Models;

/**
 * Created by Andrianina_pc on 13/02/2018.
 */

public class Matrice
{
    private long[][] coeff = null;
    private int diametre = 0;
    private int distance = 0;

    //----------------------------------------------//
    //					   CONSTRUCTOR							//
    //----------------------------------------------//

    public Matrice(int i, int j)
    {
        this.setLength(i,j);

        for(int k=0; k < i; k++){
            for(int l=0; l < j;l++){
                this.setValue(k, l, 0);
            }
        }
    }

    public Matrice()
    {
        this(0,0);
    }

    public Matrice(long[][] mat)
    {
        this.coeff = mat;
    }

    //----------------------------------------------//
    //					  		 SETTER					   	//
    //----------------------------------------------//
    // définit une matrice de type long[][]
    public void setMatrice(long[][] mat)
    {
        this.coeff = mat;
    }

    // définit une valeur à la position i et j
    // i - ligne
    // j - col
    public void setValue(int i, int j, long value)
    {
        this.coeff[i][j] = value;
    }


    // on définit la taille de la mtrice
    public void setLength(int i, int j)
    {
        this.coeff = new long[i][j];
    }


    //----------------------------------------------//
    //					  		 GETTER					   	//
    //----------------------------------------------//
    // retourne la matrice sous forme du type long[][]
    public long[][] getMatrice()
    {
        return this.coeff;
    }

    // retourne le nombre de ligne
    public int getRows()
    {
        return this.coeff.length;
    }

    // retourne le nombre de colonne
    public int getColumns()
    {
        return this.coeff[0].length;
    }

    // retourne la valeur à la position i et j
    public long getValue(int i, int j)
    {
        return this.coeff[i][j];
    }

    // retourne le déterminant d'une matrice
    public long getDeterminant()
    {
        Matrice a = null;
        long value = 0;

        if (this.getRows() < 3 && this.getColumns() < 3)
            return (this.getValue(0,0)*this.getValue(1,1) - this.getValue(1,0)*this.getValue(0,1));


        for (int j=0; j<this.getColumns(); j++)
        {
            a = this.getNewMatrice(0,j);
            value += (int)Math.pow(-1,j)*(this.getValue(0,j)*a.getDeterminant());
        }

        return value;
    }

    // retourne la matrice inverse de la matrice this
    public Matrice getMatriceInverse()
    {
        Matrice a = new Matrice(this.getRows(), this.getColumns());
        Matrice tmp = null;
        long det = this.getDeterminant();

        for (int i=0; i<this.getRows(); i++)
            for (int j=0; j<this.getColumns(); j++)
            {
                tmp = this.getNewMatrice(i,j);
                a.setValue(i,j,(int)Math.pow(-1,i+j)*(tmp.getDeterminant()/det));
            }

        // on transpose la matrice les coeffcients seront positionné de façon incorrect
        return a.getMatriceTranspose();
    }


    /* Retourne une nouvelle matrice mais en supprimant
     * la ligne row et la colonne columns
     */
    private Matrice getNewMatrice(int row, int columns)
    {
        Matrice a = new Matrice(this.getRows()-1, this.getColumns()-1);
        int k = -1, m = 0;

        for (int i=0; i<this.getRows(); i++)
        {

            k++;

            if (i == row)
            {
                k--;
                continue;
            }

            m = -1;

            for (int j=0; j<this.getColumns(); j++)
            {

                m++;

                if (j==columns)
                {
                    m--;
                    continue;
                }

                a.setValue(k,m,this.getValue(i,j));
            }

        }

        return a;
    }

    /**
     * Retourne le nombre de combinaison a partir d'une matrice
     * définit a partir d'un graphe.
     * @param
     * 	sA - sommet A
     *	sB - sommet B
     *	nb - Nombre de arrete (ou nombre de caractere du combinaison)
     * @return
     * 	long - nombre de combinaison possible entre 2 sommets
     */
    public long getGrapheCombiCount(int sA, int sB, int nb)
    {
        if (sB > this.getRows() || sA > this.getColumns())
            return -1;

        Matrice a = this.matricePow(nb);

        return a.getValue(sB-1,sA-1);
    }


    // retourne la matrice I en fonction de la mtrice this
    public Matrice getMatriceIdentity()
    {
        Matrice a = new Matrice(this.getRows(),this.getColumns());

        for (int i=0; i<this.getRows(); i++)
            a.setValue(i,i,1);

        return a;
    }

    // transpose la matrice
    public Matrice getMatriceTranspose()
    {
        Matrice a = new Matrice(this.getColumns(), this.getRows());
        long tmp = 0;

        for (int i=0; i<a.getRows(); i++)
            for (int j=0; j<a.getColumns(); j++)
            {
                tmp = this.getValue(j,i);
                a.setValue(i,j,tmp);
            }


        return a;
    }

    // retourne la valeur de la trice de la matrice
    public long getTraceMatrice()
    {
        long value = 0;

        for (int i=0; i<this.getRows(); i++)
            value += this.getValue(i,i);

        return value;
    }


    /** Retourne la distance (nombre d'arrete) entre
     * entre deux sommets sA et sB, tel que sA <= sB
     * si sA > sB, méthode renvoi -1 pour erreur
     */
    public long getDistanceGraphe(int sA, int sB)
    {
        long value = 0;

        if (sA > sB)
            return -1;

        if (this.getValue(sB-1,sA-1) != 0)
            return (this.getValue(sB-1,sA-1));

        for (int i=sA; i<sB; i++)
            value += this.getValue(i-1, (i+1)-1);

        return value;
    }

    // GRAPHE
    // retourne la matrice de distance
    public Matrice getMatriceDistanceGraphe()
    {
        Matrice a = new Matrice(this.getRows(),this.getColumns());
        int n=1;

        for (int i=0; i<this.getRows(); i++)
            for (int j=0; j<this.getColumns(); j++)
            {
                if (a.getValue(i,j) == 0)
                    a = this.matricePow(n++);

            }

        // n-1 correspond a la distance
        this.distance = n-1;

        return a;
    }

    // GRAPHE
    // retourne la distance de la matrice
    public int getDistance()
    {
        this.getMatriceDistanceGraphe();
        return this.distance;
    }


    // retourne la matrice compagnon en fonction de la matrice this
    public Matrice getMatriceCompagnon()
    {
        Matrice a = new Matrice(this.getRows(),this.getColumns());

        for (int i=0; i<a.getRows()-1; i++)
            a.setValue(i+1,i,1);

        a.setValue(this.getRows()-1,this.getColumns()-1,-1);

        return a;
    }


    // GRAPHE
    // retourne la matrice diam
    public Matrice getMatriceDiametre()
    {
        int n=1;
        Matrice ai = this.sumMatrice(this.getMatriceIdentity());

        for (int i=0; i<this.getRows(); i++)
            for (int j=0; j<this.getColumns(); j++)
            {
                if (ai.getValue(i,j) == 0)
                    ai = this.matricePow(n++);

            }

        this.diametre = n-1;

        return ai;
    }

    // GRAPHE
    // retourne la valeur du diametre de la matrice (ou graphe :))
    public int getDiametre()
    {
        this.getMatriceDiametre();
        return this.diametre;
    }

    // GRAPHE
    // retourne les deux sommets les plus éloignés
    public int[] getSommetPLusDistant()
    {
        int[] sommets = new int[2];
        Matrice m = this.matricePow(this.getDiametre()-1);
        byte n = 0;

        for (int i=0; i<m.getRows(); i++)
            for (int j=0; j<m.getColumns(); j++)
                if (m.getValue(i,j) == 0)
                {
                    sommets[n++] = i+1;
                }

        return sommets;
    }

    //----------------------------------------------//
    //  		  			 OTHERS METHODS				   //
    //----------------------------------------------//
    // multiplication
    public Matrice multiply(final Matrice matrice)
    {
        Matrice a = new Matrice(this.getRows(), this.getColumns());
        int k,i,j,m;
        long value = 0;

        for (k=0; k<this.getColumns(); k++)
        {

            for (i=0; i<this.getRows(); i++)
            {

                for (j=0; j<this.getColumns(); j++)
                    value += this.getValue(i,j)*matrice.getValue(j,k);

                a.setValue(i,k,value);
                value = 0;
            }
        }

        return a;
    }

    // addition
    public Matrice sumMatrice(final Matrice matrice)
    {
        Matrice a = new Matrice(this.getRows(), this.getColumns());

        for (int i=0; i<this.getRows(); i++)
            for (int j=0; j<this.getColumns(); j++)
                a.setValue(i,j,this.getValue(i,j)+matrice.getValue(i,j));

        return a;
    }

    // puissance -> M^n
    public Matrice matricePow(int n)
    {
        Matrice a = this;
        Matrice b = a;

        for (int i=0; i<n-1; i++)
            b = a.multiply(b);

        return b;
    }

    // soustraction
    public Matrice soustraction(final Matrice matrice)
    {
        Matrice a = new Matrice(this.getRows(), this.getColumns());

        for (int i=0; i<this.getRows(); i++)
            for (int j=0; j<this.getColumns(); j++)
                a.setValue(i,j,this.getValue(i,j)-matrice.getValue(i,j));

        return a;
    }

    // multiplication d'une matrice par une constante k
    public Matrice multiplyByK(long k)
    {
        Matrice a = this;

        for (int i=0; i<this.getRows(); i++)
            for (int j=0; j<this.getColumns(); j++)
                a.setValue(i,j,this.getValue(i,j)*k);

        return a;
    }


    // division d'une matrice par une constante k
    public Matrice divByK(long k)
    {
        Matrice a = this;

        for (int i=0; i<this.getRows(); i++)
            for (int j=0; j<this.getColumns(); j++)
                a.setValue(i,j,this.getValue(i,j)/k);

        return a;
    }

    // le fameux toString() :)
    public String toString()
    {
        String out = "";

        for (int i=0; i<this.getRows(); i++)
        {
            for (int j=0; j<this.getColumns(); j++)
                out +=this.coeff[i][j]+"\t ";

            out+="\n";
        }

        return out;
    }


    // definit si deux matrices sont équivalentes
    public boolean equals(Matrice matrice)
    {
        for(int i=0; i<this.getRows(); i++)
            for (int j=0; j<this.getColumns(); j++)
                if (this.getValue(i,j) != matrice.getValue(i,j))
                    return false;

        return true;
    }

    //----------------------------------------------//
    //	   		  		 METHODS IS...					   //
    //----------------------------------------------//
    // détermine si la matrice est symetrique
    public boolean isSymetric()
    {
        if (this.getRows() == this.getColumns())
            return false;

        for(int i=0; i<this.getRows(); i++)
            for (int j=0; j<this.getColumns(); j++)
                if (this.getValue(i,j) != this.getValue(j,i))
                    return false;

        return true;
    }

    // détermine si la matrice est triangulaire
    public boolean isTriangularMatrix()
    {
        for(int i=0; i<this.getRows(); i++)
            for (int j=1; j<this.getColumns(); j++)
                if (this.getValue(i,j) != 0)
                    return false;

        return true;
    }

    // détermine si la matrice est une matrice unité
    public boolean isUnitMatrix()
    {
        return (this.equals(this.getMatriceIdentity()));
    }

    // détermine si la matrice est inversible
    public boolean isInversible()
    {
        return (this.getDeterminant() != 0);
    }

    // determine si la mtrice contient au moins une valeur 0
    public boolean isZero()
    {
        for(int i=0; i<this.getRows(); i++)
            for (int j=0; j<this.getColumns(); j++)
                if (this.getValue(i,j) == 0)
                    return true;

        return false;
    }

    //----------------------------------------------//
    //	   		  	 LAUNCH PROGRAM					   //
    //----------------------------------------------//
    public static void main(String[] args)
    {
        // matrice d'adjacence d'un graphe
        Matrice a = new Matrice();
        a.setMatrice(new long[][] { {0,1,1,0,0,0,0,0,0,0,0}, // 1
                {1,0,1,1,1,0,0,0,0,0,0}, // 2
                {1,1,0,0,1,0,0,0,0,0,0}, // 3
                {0,1,0,0,0,1,0,0,0,0,0}, // 4
                {0,1,1,0,0,0,1,0,0,0,0}, // 5
                {0,0,0,1,0,0,1,1,1,0,0}, // 6
                {0,0,0,0,1,1,0,0,0,1,0}, // 7
                {0,0,0,0,0,1,0,0,1,0,1}, // 8
                {0,0,0,0,0,1,0,1,0,1,1}, // 9
                {0,0,0,0,0,0,1,0,1,0,1}, // 10
                {0,0,0,0,0,0,0,1,1,1,0}  // 11
        });

        //System.out.println("Matrice A : \n"+a);
        //System.out.println("Distance : \n"+a.matricePow(4));

        Matrice x = new Matrice(new long[][] { {0,1,0,0}, {1,0,0,0}, {0,0,1,1},{0,0,0,1}});
        System.out.println("Det : \n" + x.getDeterminant());
        System.out.println("Matrice inverse : \n" + x.getMatriceInverse());

    }
}

