package fisica;

import java.util.ArrayList;
import java.util.Random;

import JFFT.NumComplex;

import matematica.Reta;
import matematica.SegmentoReta;

public class MesaElipseDistorcido {
	private static final double pi=3.1415926;
	private ArrayList<SegmentoReta> retas;

	public MesaElipseDistorcido(double raio,int tam, long semente){
		retas=new ArrayList<SegmentoReta>();
		ArrayList<SegmentoReta> retas2=new ArrayList<SegmentoReta>();
		Random rnd=new Random(semente);
		double ruidoAltaFrequencia[]= new double[6300];
		
		for(int cont=0;cont<ruidoAltaFrequencia.length;cont++){
			ruidoAltaFrequencia[cont]=rnd.nextDouble()-rnd.nextDouble();
			
		}
		double dirX=1.0+((rnd.nextDouble()-rnd.nextDouble())/2.0);
		double dirY=1.0+((rnd.nextDouble()-rnd.nextDouble())/2.0);
		double ruidoBaixaFrequencia[]=retornaSinalBaixaFrequencia(ruidoAltaFrequencia, 6);
		
		int inc=0;
		double nivel=26.0;

		double xini=Math.sin(0.0)*dirX;
		double yini=((Math.cos(0.0)*dirY)+(ruidoBaixaFrequencia[inc++]*nivel));
		for(double val=0.001;val<(2*pi);val=val+0.001){
			double xtemp=Math.sin(val)*dirX;
			double ytemp=((Math.cos(val)*dirY)+(ruidoBaixaFrequencia[inc++]*nivel));			
			retas.add(new SegmentoReta(yini,ytemp, xini,xtemp));		
			xini=xtemp;
			yini=ytemp;			
			
		}
		double xtemp=Math.sin(0.0)*dirX;
		double ytemp=((Math.cos(0.0)*dirY)+(ruidoBaixaFrequencia[0]*nivel));
		
		retas.add(new SegmentoReta(yini,ytemp, xini,xtemp));
		
		

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

	
}
