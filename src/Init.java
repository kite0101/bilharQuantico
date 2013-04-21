import matematica.*;
import fisica.FractalFlocoNeveAleatorio;
import fisica.MesaCirculoDistorcido;
import fisica.MesaFourier;
import fisica.MesaSimples;
import fisica.Particula;
import gui.Janela;
import gui.PlotBilhar;


public class Init {
	public Init() throws InterruptedException{
		//FractalFlocoNeveAleatorio mesa=new FractalFlocoNeveAleatorio(234321L, 600, 50,420);
		//MesaSimples mesa= new MesaSimples();
		//MesaFourier mesa= new MesaFourier(234321L, 500, 560);
		MesaCirculoDistorcido mesa= new MesaCirculoDistorcido(1, 600,79999320L);
		//FractalFlocoNeveAleatorio mesa=new FractalFlocoNeveAleatorio(934321L, 600, 50,420);
		//Stadium std=new Stadium(800,700);
		int mesaBilhar[][]=mesa.getMatrix();;							
		Particula par= new Particula(300, 300, new Vetor(0.2, 0.3),mesaBilhar,mesa.getListaRetas());
		//Particula par2=new Particula(300, 180, new Vetor(0.001000000001, 0.001),proc.getMatrixProcessada());
		PlotBilhar plot= new PlotBilhar(mesaBilhar, par);
		
		new Janela(plot);
		int ite=0;
		while(true){
			Thread.sleep(10);
			par.atuali_posi();
			//par2.atualizaPosi2();				
			ite++;			
		}
		
	}
	public static void main(String args[]) throws InterruptedException{
		new Init();
	}
}
