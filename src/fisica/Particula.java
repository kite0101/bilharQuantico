package fisica;

import java.util.ArrayList;

import org.w3c.dom.ls.LSInput;

import matematica.Ponto;
import matematica.Reta;
import matematica.SegmentoReta;
import matematica.Vetor;


public class Particula {
	long vezesChamdoAtualizaPosiDebug=0L;//debug
    private Vetor velocidade;
    private double posix,posiy;    
    private ArrayList<Ponto> pontosColisao;//pega os ultimos pontos com que a particula colidiu
    private Reta retaColisao;//pega a reta formada pelos ultimos pontos em que particula colidiu
    private Reta retaColisaoPerpendicular;//pega a reta perpendicular a reta formada pelos ultimos pontos em que particula colidiu
    private float matrizPercurso[][];    
    private long id;    
    private ArrayList<SegmentoReta> listaRetas;
    public  Particula(double posix,double posiy,Vetor velocidade, int mesaBilhar[][], ArrayList<SegmentoReta> listaRetas){
        this.posix=posix;
        this.posiy=posiy;
        this.velocidade=velocidade;        
        this.matrizPercurso=new float [mesaBilhar.length][mesaBilhar[0].length];                                      
        this.listaRetas=listaRetas;
        id=-1;
    }
    public void atuali_posi(){
    	Reta retaParticula= new Reta(posiy,posiy+velocidade.get_b(),posix,posix+velocidade.get_a(),velocidade.get_a(),velocidade.get_b());
    	Ponto pf=null;
    	Reta rf=null;
    	double deltaMin=1000000000000000.0;
    	vezesChamdoAtualizaPosiDebug++;//debug
    	if(vezesChamdoAtualizaPosiDebug==707){
    		int akll=7;
    	}
    	for(SegmentoReta r:listaRetas){
    		Ponto p=Reta.pontoEncontro(r,retaParticula);
    		if(p!=null){
    			double delta=(p.x-posix)/(velocidade.get_a());
    			
    			if(delta>0 && delta<deltaMin){
    				deltaMin=delta;
    				rf=r;
    				pf=p;    			
    			}
    		}
    		
    	}
    	//desenhaReta(rf.get_p1x(),rf.get_p2x(), rf.get_p1y(),rf.get_p2y());
    	if(rf!=null && pf!=null){
    		//debug
    		double compVelocidadeYAntesColisao=velocidade.get_b();
    		double compVelocidadeXAntesColisao=velocidade.get_a();
    		//fim
    		colisaReflexao(rf, pf);
    		//debug
    		System.out.printf("ite:%d, Id da linha que colidiu: %d, posiXColisao: %f, posiyColisao: %f \n",vezesChamdoAtualizaPosiDebug, rf.getID(), pf.x,pf.y);
    		System.out.printf("componente x vetor velocidade antes colisao: %f, componente y vetor velocidade antes colisao: %f, componente x vetor velocidade pos-colisao: %f, componente y vetor velocidade pos-colisao: %f,  \n",compVelocidadeXAntesColisao,compVelocidadeYAntesColisao,velocidade.get_a(),velocidade.get_b());
    		System.out.println("==========================================================================");
    		//fim
    		retaColisao=rf;
    		retaColisaoPerpendicular=rf.perpendicular(pf);
    		desenhaReta(pf.x,posix, pf.y,posiy);
    		posix=pf.x;
    		posiy=pf.y;
    		id=rf.getID();
    	}
    	else{    
    		posix=300.0+((Math.random()*2.0)-1.0)*10.0;
    		posiy=180.0+((Math.random()*2.0)-1.0)*10.0;
    		id=-1;
    		System.exit(0);
    	}
    	    	    
    }
 
    public float[][] percursoParticula(){
    	return matrizPercurso;
    }          
    public Reta getRetaColisao(){
    	return retaColisao;
    }
    public Reta getRetaColisaoPerpendicular(){
    	return retaColisaoPerpendicular;
    }
    public double getPosiX(){
    	return posix;
    }
    public double getPosiY(){
    	return posiy;
    }

    private void colisaReflexao(Reta reta,Ponto point){//faz a particula mudar sua velocidade
                                                                           //quando esta colide com a parede do bilhar
        //point -> ponto do bilhar em que a particula colidi
        //pontos_colisao -> pontos vizinhos a point que sao paredes do bilhar
        Vetor refle;
        double eq_linha[] =reta.perpendicular(point).equacao_reta();
        if(Double.isInfinite(eq_linha[0])){
            velocidade.set_a(velocidade.get_a());
             velocidade.set_b(-velocidade.get_b());
        
        }
        else if(Math.sqrt(eq_linha[0]*eq_linha[0])<0.0001){
            velocidade.set_a(-velocidade.get_a());
            velocidade.set_b(velocidade.get_b());
        }
        else{
            Vetor perpen=new Vetor(-1.0,(eq_linha[0]*-1.0)).vetor_unitario();
            double prod_i=perpen.produto_interno(velocidade);
            Vetor vet_1=perpen.vetor_multiplicacao(prod_i);
            Vetor vet_2=vet_1.vetor_multiplicacao(2.0);
            Vetor vet3=velocidade.vetor_subtracao(vet_2);
            velocidade=vet3;
        }     

    }
  	public Vetor getVelocidade() {
		return velocidade;
	}
	 public void desenhaReta(double p1x,double p2x, double p1y,double p2y){
	    	
	    	double eq[]=new Reta(p1y,p2y, p1x,p2x,p2x-p1x, p2y-p1y).equacao_reta();
	    	for(double xite=p1x;xite<p2x;xite=xite+0.01){
	    		matrizPercurso[(int)xite][(int)(eq[0]*xite+eq[1])]=matrizPercurso[(int)xite][(int)(eq[0]*xite+eq[1])]+1.0f;
	    	}
	    	for(double xite=p2x;xite<p1x;xite=xite+0.01){
	    		matrizPercurso[(int)xite][(int)(eq[0]*xite+eq[1])]=matrizPercurso[(int)xite][(int)(eq[0]*xite+eq[1])]+1.0f;
	    	}
	    }

}

