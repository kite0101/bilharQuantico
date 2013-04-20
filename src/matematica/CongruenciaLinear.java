package matematica;


public class CongruenciaLinear implements NumeroAleatorio{//gerador de numeros aleatorios usando congruencia linear
    long semente;
    public CongruenciaLinear(long semente){
        this.semente=semente;
    }
    public CongruenciaLinear(){//inicia o gerador com um numero aleatorio qualquer
        this.semente=3451198212L;
    }
    public void set_semente(long seme){
        this.semente=seme;
    }
    public double rand(){
        double ret=0.0;
        semente=((semente*17413869L)+1000033L)%9997954933L;//metodo da congruencia linear
        ret=((double)semente)/9997954933.0;
        return ret;
    }
    public double rand_intv(double x){        
        double ret=(rand()*2.0*x)-x;
        return ret;
    }

}

