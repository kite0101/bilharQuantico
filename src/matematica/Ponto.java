package matematica;

public class Ponto {
    public double x;
    public double y;   
    public Ponto(double x, double y){
        this.x=x;
        this.y=y;
    }
    boolean igual(Ponto p){
        if(p.x==this.x && p.y==this.y) return true;
        else return false;
    }
}
