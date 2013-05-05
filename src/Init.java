import matematica.*;
import fisica.MesaCirculoDistorcido;
import fisica.MesaElipseDistorcido;
import fisica.MesaSimples;
import fisica.Particula;
import gui.Janela;
import gui.PlotBilhar;


public class Init {
	public Init() throws InterruptedException{
		
		MesaElipseDistorcido mesa= new MesaElipseDistorcido(1, 600,19L);

								
		Particula par= new Particula(0.2, 0.3, new Vetor(0.2, 0.3),mesa.getListaRetas());
		
		PlotBilhar plot= new PlotBilhar(mesa.getListaRetas(), par);
		
		new Janela(plot);
		int ite=0;
		while(true){
			Thread.sleep(10);
			par.atuali_posi();			
			ite++;			
		}
		
	}
	public static void main(String args[]) throws InterruptedException{
		new Init();
	}
}
