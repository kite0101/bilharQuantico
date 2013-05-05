import matematica.*;
import fisica.MesaCirculoDistorcido;
import fisica.MesaElipseDistorcido;
import fisica.MesaSimples;
import fisica.Particula;
import gui.Janela;
import gui.PlotBilhar;


public class Init {
	public Init() throws InterruptedException{
		//ollada
		//FractalFlocoNeveAleatorio mesa=new FractalFlocoNeveAleatorio(234321L, 600, 50,420);
		//MesaSimples mesa= new MesaSimples();
		//MesaFourier mesa= new MesaFourier(234321L, 500, 560);
		MesaElipseDistorcido mesa= new MesaElipseDistorcido(1, 600,19L);
		//FractalFlocoNeveAleatorio mesa=new FractalFlocoNeveAleatorio(934321L, 600, 50,420);
		//Stadium std=new Stadium(800,700);
								
		Particula par= new Particula(0.2, 0.3, new Vetor(0.2, 0.3),mesa.getListaRetas());
		//Particula par2=new Particula(300, 180, new Vetor(0.001000000001, 0.001),proc.getMatrixProcessada());
		PlotBilhar plot= new PlotBilhar(mesa.getListaRetas(), par);
		
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
