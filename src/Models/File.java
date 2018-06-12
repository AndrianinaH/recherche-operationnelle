package Models;


public class File<T> {
    T[] tab;
    int size;
    int tete;
    int queue;
    int max;

    public static void main(String[] args) {
        File<Integer> entier=new File<Integer>(10);

        Integer test=new Integer("56");
        try{
            entier.enfiler(test);
//			entier.enfiler(new Integer("57"));
//			entier.enfiler(new Integer("58"));
//			entier.enfiler(new Integer("59"));
            entier.enfiler(new Integer("60"));
            entier.defiler();

            System.out.println("tete == " +entier.getTete());
//                        System.out.println("tete == " +entier.isEmpty());

        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("*****************");
        for(int i=entier.tete();i<entier.queue();i++){
            System.out.println(entier.getValue(i));
        }
    }

    public boolean isEmpty(){
        if(tete == queue){
            return true;
        }
        return false;
    }

    public int queue(){
        return queue;
    }
    public int tete(){
        return tete;
    }

    public T getTete(){
        return tab[tete];
    }

    public T getValue(int i){
        return tab[i];
    }

    public File(int max){
        tab=(T[])new Object[max];
        this.max=max;
        this.size=0;
        tete=1;
        queue=1;
    }

    public void enfiler(T e){
        tab[queue]=e;
        if(queue==max-1){
            queue=1;
            return;
        }
        size++;
        queue++;
    }

    public int defiler(){
        int x=tete;
        if(tete==size){
            tete=1;
        }
        else{
            size--;
            tete++;
        }
        return x;
    }

}