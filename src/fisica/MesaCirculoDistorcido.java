package fisica;

import java.util.ArrayList;
import java.util.Random;

import JFFT.NumComplex;

import matematica.Reta;
import matematica.SegmentoReta;

public class MesaCirculoDistorcido {
	private static final double pi=3.1415926;
	private ArrayList<SegmentoReta> retas;
	private int matrix[][];//matriz de pontos, com o fractal

	public MesaCirculoDistorcido(double raio,int tam, long semente){
        matrix=new int[tam][tam];
		retas=new ArrayList<SegmentoReta>();
		ArrayList<SegmentoReta> retas2=new ArrayList<SegmentoReta>();
		Random rnd=new Random(semente);
		double ruidoAltaFrequencia[]= new double[6300];		
		for(int cont=0;cont<ruidoAltaFrequencia.length;cont++){
			ruidoAltaFrequencia[cont]=rnd.nextDouble();
			
		}
		double ruidoBaixaFrequencia[]=retornaSinalBaixaFrequencia(ruidoAltaFrequencia, 6);
		double descX=300.0;
		double descY=-1280.0;
		double scala=120.0;
		double scalaX=220.0;
		int inc=0;
		double nivel=26.5;

		double xini=(Math.sin(0.0)*scalaX)+descX;
		double yini=((Math.cos(0.0)+(ruidoBaixaFrequencia[inc++]*nivel))*scala)+descY;
		double yini2=((Math.cos(0.0)+(ruidoBaixaFrequencia[0]*nivel))*scala)+descY;
		for(double val=0.001;val<(2*pi);val=val+0.001){
			double xtemp=((Math.sin(val))*scalaX)+descX;
			double ytemp=((Math.cos(val)+(ruidoBaixaFrequencia[inc++]*nivel))*scala)+descY;
			double ytemp2=((Math.cos(val)+(ruidoBaixaFrequencia[0]*nivel))*scala)+descY;	
			retas.add(new SegmentoReta(yini,ytemp, xini,xtemp));
			retas2.add(new SegmentoReta(yini2,ytemp2, xini,xtemp));
			xini=xtemp;
			yini=ytemp;
			yini2=ytemp2;
			
		}
		double xtemp=(Math.sin(0.0)*scalaX)+descX;
		double ytemp=((Math.cos(0.0)+(ruidoBaixaFrequencia[0]*nivel))*scala)+descY;
		double ytemp2=((Math.cos(0.0)+(ruidoBaixaFrequencia[0]*nivel))*scala)+descY;
		retas.add(new SegmentoReta(yini,ytemp, xini,xtemp));
		retas2.add(new SegmentoReta(yini,ytemp, xini,xtemp));
		for(int cont=0;cont<retas.size();cont++){ 
			desenhar_reta(retas.get(cont));
			//desenhar_reta(retas2.get(cont));
		}

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
			ret[cont]=vetIFFT[cont].r;
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
