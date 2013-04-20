package fisica;

import java.util.ArrayList;
import java.util.Random;

import matematica.Reta;
import matematica.SegmentoReta;

import JFFT.NumComplex;

public class MesaSimples {
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
	public MesaSimples(){
        matrix=new int[600][600];
		retas=new ArrayList<SegmentoReta>();

		SegmentoReta r1=new SegmentoReta( 208.67,319.85, 62.3,150.9);
		SegmentoReta r2=new SegmentoReta(319.85,279.9, 150.9,230.81);
		SegmentoReta r3=new SegmentoReta(279.9,118.33,230.81,251.66);
		SegmentoReta r4=new SegmentoReta(118.33,76.64,251.66,163.06);
		SegmentoReta r5=new SegmentoReta(76.64,165.24,163.06,149.16);
		SegmentoReta r6=new SegmentoReta(165.24,208.67,149.16,62.3);
		
		desenhar_reta(r1 );
		desenhar_reta(r2 );
		desenhar_reta(r3 );
		desenhar_reta(r4 );
		desenhar_reta(r5 );
		desenhar_reta(r6 );
		retas.add(r1);
		retas.add(r2);
		retas.add(r3);
		retas.add(r4);
		retas.add(r5);
		retas.add(r6);
        
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
