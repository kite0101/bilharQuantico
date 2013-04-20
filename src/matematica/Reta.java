package matematica;

import java.util.ArrayList;


public class Reta {//segmento de linha que forma uma parte, pequena, da silueta da montanha
    private double p1x;
    private double p1y;
    private double p2x;
    private double p2y;
    private double vetorDirecaoReta[]=new double[2];
    private double eq[];//da a equacao da reta_eq
    private long id;
    private static long semente=0;
    private boolean trocaDePosicaoDosPontos;
    public Reta(double p1y, double p2y, double p1x, double p2x, double vetorDirecaoX, double vetorDirecaoY){//constroi dando seus ponto inicial e final
        //p1x->ponto inicial x
        //p1y->ponto inicial y
        //p2x->ponto final x
        //p2y->ponto final y
    	if(p1x<p2x){
    		trocaDePosicaoDosPontos=false;
    		this.p1x=p1x;
    		this.p1y=p1y;
    		this.p2x=p2x;
    		this.p2y=p2y;
        }
    	else{
    		trocaDePosicaoDosPontos=true;
    		this.p1x=p2x;
    		this.p1y=p2y;
    		this.p2x=p1x;
    		this.p2y=p1y;
    	}
    	//vetorDirecaoReta[0]=p1x-p2x;
    	//vetorDirecaoReta[1]=p1y-p2y;
    	vetorDirecaoReta[0]=p2x-p1x;
        vetorDirecaoReta[1]=p2y-p1y;
        eq=null;
        semente++;
        id=semente;
    }
    public long getID(){
    	return id;
    }
    public static Ponto pontoEncontro(SegmentoReta r0,Reta r1){//acha o ponto em que a reta r se encontra com a reta this
    	//ve se as retas sao paralelas, ou seja nao tem vetores colineares
    	if((r1.getVetorVelocidade()[0]/r0.getVetorVelocidade()[0]) == (r1.getVetorVelocidade()[1]/r0.getVetorVelocidade()[1]) ) return null;
    	//fim
    	//encontra o ponto que as duas retas possuem em comum
    	
    	double v1=r0.get_p1xVerdadeiro();
    	double u1=r1.get_p1xVerdadeiro();
    	double v2=r0.get_p1yVerdadeiro();
    	double u2=r1.get_p1yVerdadeiro();
        double x=u1-v1;
        double y=u2-v2;      
        double w1=r0.getVetorVelocidade()[0];
        double w2=r0.getVetorVelocidade()[1];
        double j1=r1.getVetorVelocidade()[0];
        double j2=r1.getVetorVelocidade()[1];
        double dt=(-w1*j2)+(w2*j1);
        double alfa=((-w1*y)+(w2*x))/dt;
        double delta=((-x*j2)+(j1*y))/dt;
    	Ponto ret= new Ponto(v1+(delta*w1),v2+(delta*w2));
    	//fim
    	//ve se este ponto esta a frente do ponto p1x,p1y
    	double val=(ret.x-r1.get_p1xVerdadeiro())/r1.getVetorVelocidade()[0];
    	if(val<=0.001) return null;
    	//fim
    	//ve se o ponto esta mais adiante que o segmento de reta
    	double lambdaDoPonto=r0.calcularLambdaDoPonto(ret.x,ret.y);
    	if(lambdaDoPonto<0.0 || lambdaDoPonto>r0.getLambda()) return null;
    	//fim
    	return ret;
    }

    public double[] getVetorVelocidade(){
    	double ret[]=new double[2];
    	ret[0]=vetorDirecaoReta[0];
    	ret[1]=vetorDirecaoReta[1];
    	return ret;
    }
    public Reta(ArrayList<Ponto> pontos){//constroi a linha a partir de uma nuvem de pontos
        //cria a equacao da reta_eq
        double sum_xy=0.0,sum_x=0.0,sum_quad_x=0.0,sum_y=0.0;
        int cont;
        double contd;
        for(cont=0;cont<pontos.size();cont++){
            sum_xy=sum_xy+(pontos.get(cont).x*pontos.get(cont).y);
            sum_x=sum_x+pontos.get(cont).x;
            sum_y=sum_y+pontos.get(cont).y;
            sum_quad_x=sum_quad_x+(pontos.get(cont).x*pontos.get(cont).x);
        }
        contd=cont;
        eq=new double[2];
        eq[0]=((contd*sum_xy)-(sum_x*sum_y))/((contd*sum_quad_x)-(sum_x*sum_x));
        eq[1]=((sum_quad_x*sum_y)-(sum_xy*sum_x))/((contd*sum_quad_x)-(sum_x*sum_x));
        //fim
        //cria os p1x.p2x,p1y,p2y a partir da equacao da reta_eq encontrada e da nuvem de pontos
        double min_x=0;//localiza o menor x dentro da nuvem de pontos
        double max_x=0;//localiza o maior x dentro da nuvem de pontos
        for(cont=0;cont<pontos.size();cont++){
            if(cont==0){
                min_x=pontos.get(cont).x;
                max_x=pontos.get(cont).x;
                continue;
            }
            if(min_x> pontos.get(cont).x) min_x=pontos.get(cont).x;
            if(max_x< pontos.get(cont).x) max_x=pontos.get(cont).x;
        }
        this.p1x=min_x;
        this.p2x=max_x;
        this.p1y=(p1x*eq[0])+eq[1];
        this.p2y=(p2x*eq[0])+eq[1];
    }
    public Reta(double eq[],double x1,double x2){
        this.eq=eq;
        this.p1x=x1;
        this.p2x=x2;
        this.p1y=eq[0]*x1+eq[1];
        this.p2y=eq[0]*x2+eq[1];
    }
    public double[] get_ponto_medio(){//acha o ponto medio da linha
        double ret[]=new double[2];
        ret[0]=(this.p1x+p2x)/2.0;
        ret[1]=(this.p1y+p2y)/2.0;
        return ret;
    }
    
    public double get_p1x() {
        return p1x;
    }
    public double get_p2x(){
        return p2x;
    }
    public double get_p1y() {
        return p1y;
    }
    public double get_p2y(){
        return p2y;
    }


    public double get_p1xVerdadeiro() {
    	if(trocaDePosicaoDosPontos) return p2x;
        return p1x;
    }
    public double get_p2xVerdadeiro(){
    	if(trocaDePosicaoDosPontos) return p1x;
        return p2x;
    }
    public double get_p1yVerdadeiro() {
    	if(trocaDePosicaoDosPontos) return p2y;
        return p1y;
    }
    public double get_p2yVerdadeiro(){
    	if(trocaDePosicaoDosPontos) return p1y;
        return p2y;
    }
    public double[] equacao_reta(){//equacao da reta_eq a partir da linha
        if(eq==null){
            eq=new double[2];
            eq[0]=(this.p2y-this.p1y)/(this.p2x-this.p1x);
            eq[1]=this.p1y-(eq[0]*this.p1x);
        }
        return this.eq;
    }
    public Reta perpendicular(Ponto point){//cria uma linha perpendicular a linha this e que passa pelo ponto
        if(this.eq==null) this.equacao_reta();
        double eq[]=new double [2];
        eq[0]=-1.0/this.eq[0];
        eq[1]=point.y-point.x*eq[0];
        return new Reta(eq,p1x,p2x);
    }
    public boolean pontoPertenceRete(Ponto point,double tolerancia){
    	double eq[]=equacao_reta();      
    	double erro=point.y-(point.x*eq[0]+eq[1]);
    	if(erro<0)erro= -erro;
    	if(erro<=tolerancia) return true;
    	else return false;
    }
}

