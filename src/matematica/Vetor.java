package matematica;

public class Vetor {
    private double a,b;
    public Vetor(double a,double b){
        this.a=a;
        this.b=b;
    }
    public double get_a(){
        return this.a;
    }
    public double get_b(){
        return this.b;
    }
    public void set_a(double a){
        this.a=a;
    }
    public void set_b(double b){
        this.b=b;
    }
    public double modulo(){
        return Math.sqrt((a*a)+(b*b));
    }
    public double angulo_entr_vet(Vetor vetb){//angulo entre os vetores this e vetb
        double ret;        
        ret=Math.acos(((vetb.a*this.a)+(vetb.b*this.b))/(this.modulo()*vetb.modulo()));
        //if(ret>1.570796327)ret=3.1415926535897932384626433832795-ret;
        return ret;
    }
    public Vetor vetor_resultante(Vetor vetb){//calcula o vetor resultante da reflexao do vetor this com o vetor vetb
        Vetor ret=null;
        double sen_alfa;
        double cos_alfa;
        double angulo_alfa;
        angulo_alfa=2.0*angulo_entr_vet(vetb);

        if((vetb.b/vetb.a)<(this.b/this.a))angulo_alfa=-angulo_alfa;
        cos_alfa=Math.cos(angulo_alfa);
        sen_alfa=Math.sin(angulo_alfa);
        double x=(this.a*cos_alfa)-(this.b*sen_alfa);
        double y=(this.b*cos_alfa)+(this.a*sen_alfa);
        ret=new Vetor(x, y);
        return ret;
    }
    public void rotacao(double graus){
        double angulo_alfa=graus*0.017453293;
        double cos_alfa=Math.cos(angulo_alfa);
        double sen_alfa=Math.sin(angulo_alfa);
        double x=(this.a*cos_alfa)-(this.b*sen_alfa);
        double y=(this.b*cos_alfa)+(this.a*sen_alfa);
        a=x;
        b=y;
    }
    public Vetor vetor_unitario(){
        double modulo=modulo();
        double ret_a=this.a/modulo;
        double ret_b=this.b/modulo;
        return new Vetor(ret_a, ret_b);
    }
    public double produto_interno(Vetor vetb){
        double ret=0;
        ret=(this.a*vetb.a)+(this.b*vetb.b);
        return ret;
    }
    public Vetor vetor_subtracao(Vetor vetb){    
        double ret_a=this.a-vetb.a;
        double ret_b=this.b-vetb.b;
        return new Vetor(ret_a, ret_b);
    }
    public Vetor vetor_multiplicacao(double mul){    
        double ret_a=this.a*mul;
        double ret_b=this.b*mul;
        return new Vetor(ret_a, ret_b);
    }
}

