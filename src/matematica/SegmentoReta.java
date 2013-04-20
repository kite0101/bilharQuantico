package matematica;

import java.util.ArrayList;


public class SegmentoReta extends Reta{//segmento de uma linha 
	                                   // a linha esta na forma $r: \vec{P}+\lambda \vec{V}$
	
	private double ini;
    private double fim;
    double p1,p2;//vetor $\vec{P}$ do seguimento de linha, este vetor diz o ponto inicial
    double q1,q2;// vetor $\vec{Q}$, este vetor diz ate que ponto o seguimento de linha deve ir
    double v1,v2; //vetor direcao da linha    
    private double lambda;//diz qual deve ser o valor de $\lambda$ para que $\vec{P} \lambda \vec{V}$ seja igual a $\vec{Q}$
    boolean usarQ1eQ2NoMetodocalcularLambdaDoPonto;   
    public SegmentoReta(double p1y, double p2y, double p1x, double p2x){//constroi dando seus ponto inicial e final
        super(p1y, p2y, p1x,p2x,p1x-p2x,p1y-p2y);
        
        double v[]=getVetorVelocidade();
        v1=v[0];
        v2=v[1];
        p1=get_p1x();
        p2=get_p1y();
        q1=get_p2x();
        q2=get_p2y();        
        if(v2!=0.0)
        	lambda=(q2-p2)/(v2);
        else
        	lambda=(q1-p1)/(v1);
      if(lambda<0){    	  
    	  usarQ1eQ2NoMetodocalcularLambdaDoPonto=true;
    	  if(v2!=0.0)
          	lambda=(-q2+p2)/(v2);
          else
          	lambda=(-q1+p1)/(v1);
      }
    }           
    public double calcularLambdaDoPonto(double x, double y){
    	double v[]=getVetorVelocidade();
    	double lambda;
        double v1=v[0];
        double v2=v[1];
        double p1,p2;
        double q1=x;
        double q2=y; 
        if(usarQ1eQ2NoMetodocalcularLambdaDoPonto==false){
        	p1=this.p1;
        	p2=this.p2;
        }
        else{
        	p1=this.q1;
        	p2=this.q2;
        }
        if(v2!=0.0)
        	lambda=(q2-p2)/(v2);
        else
        	lambda=(q1-p1)/(v1);
        
    	return lambda;
    }
    public double getLambda(){
    	return lambda;
    	
    }
}

