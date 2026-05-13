package techlab.models;

public class GeneradorID {
    private int id;
    public GeneradorID() {
        id = 0;
    }
    public int obtenerId(){
        id+=1;
        return id;
    }
    public boolean idExiste(int id){
        return (id<=this.id) && (id>0);
    }
}
