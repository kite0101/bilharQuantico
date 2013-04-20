package fisica;

import java.util.ArrayList;
import java.util.Random;

import matematica.Reta;
import matematica.SegmentoReta;

import JFFT.NumComplex;

public class MesaFourier {
	private double parteCima[];
	private double parteBaixo[];
	private double parteEsquerda[];
	private double parteDireita[]; 
	private double parteCimaSuavi[];
	private double parteBaixoSuavi[];
	private double parteEsquerdaSuavi[];
	private double parteDireitaSuavi[];
	private int matrix[][];//matriz de pontos, com o fractal
    private ArrayList<SegmentoReta> retas;
	public MesaFourier(long semente, int tamCadaParteDaMesa, int tam){
		Random rnd=new Random(semente);
		parteCima=new double[tamCadaParteDaMesa];
		parteBaixo=new double[tamCadaParteDaMesa];
		parteEsquerda=new double[tamCadaParteDaMesa];
		parteDireita=new double[tamCadaParteDaMesa];
		double fator=150.0;
		for(int cont=0;cont<tamCadaParteDaMesa;cont++){
			parteCima[cont]=rnd.nextDouble()*fator;
			parteBaixo[cont]=rnd.nextDouble()*fator;
			parteEsquerda[cont]=rnd.nextDouble()*fator;
			parteDireita[cont]=rnd.nextDouble()*fator;			
		}
		parteCimaSuavi=retornaSinalBaixaFrequencia(parteCima, 10);
		parteBaixoSuavi=retornaSinalBaixaFrequencia(parteBaixo, 10);
		parteEsquerdaSuavi=retornaSinalBaixaFrequencia(parteEsquerda, 10);
		parteDireitaSuavi=retornaSinalBaixaFrequencia(parteDireita, 10);
		retas=new ArrayList<SegmentoReta>();
        matrix=new int[tam][tam];
        double descx=50;
        double descy=50;
        for(int cont=0;cont<(tamCadaParteDaMesa-1);cont++){
        	retas.add(new SegmentoReta(descy+parteCimaSuavi[cont], descy+parteCimaSuavi[cont+1], descx+cont, descx+1+cont));
        	retas.add(new SegmentoReta(400+parteBaixoSuavi[cont], 400+parteBaixoSuavi[cont+1], descx+cont, descx+1+cont));
        	retas.add(new SegmentoReta( descy+cont, descy+1+cont,descx+parteEsquerdaSuavi[cont], descx+parteEsquerdaSuavi[cont+1]));
        	retas.add(new SegmentoReta(descy+cont, descy+1+cont,400+parteDireitaSuavi[cont], 400+parteDireitaSuavi[cont+1]));        	
        }
        for(int cont=0;cont<retas.size();cont++) 
        	desenhar_reta(retas.get(cont));
	}

	private double[] retornaSinalBaixaFrequencia( double sinal[], int corteFiltro){
		NumComplex vetFFT[] =new JFFT.FFT(sinal).getFFT();		
		for(int cont=corteFiltro;cont<vetFFT.length;cont++){
				vetFFT[cont].i=0;
				vetFFT[cont].r=0;
		}
		NumComplex vetIFFT[] =new JFFT.IFFT(vetFFT).getIFFT();
		double ret[]=new double[sinal.length];
		for(int cont=0;cont<sinal.length;cont++){				
			ret[cont]=vetIFFT[cont].modulo();
		}
		return ret;
	}
	 public ArrayList<SegmentoReta> getListaRetas(){
	    	return  retas;
	    }
	 public int[][] getMatrix(){//retorna a matrix de pontos do fractal
	    	int ret[][]=new int[matrix.length][matrix[0].length];//matriz a ser retornada pelo metodo
	    	//copia o atributo matrix para a variavel ret
	    	for(int cont=0;cont<ret.length;cont++){
	    		for(int cont2=0;cont2<ret[cont].length;cont2++){
	    			ret[cont][cont2]=matrix[cont][cont2];
	    		}
	    		
	    	}
	    	return ret;
	    	
	    }
	 public void desenhar_reta(Reta r){
	        double xt,yt;
	        double eq[]=r.equacao_reta();
	        double x1=r.get_p1x();
	        double y1=r.get_p1y();
	        double x2=r.get_p2x();
	        double y2=r.get_p2y();
	        if(x1>x2){
	            xt=x1;
	            x1=x2;
	            x2=xt;
	            yt=y1;
	            y1=y2;
	            y2=yt;
	        }
	        xt=x1;
	        while(xt<x2){
	            yt=xt*eq[0]+eq[1];
	            if(xt>=0 && yt>=0 &&  xt<matrix.length && yt< matrix[0].length){
	                matrix[(int)xt][(int)yt]=255;                
	            }
	            xt=xt+0.001;
	        }
	    }
}
