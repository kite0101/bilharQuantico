package gui;

import info.clearthought.layout.TableLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Janela  extends JFrame{
	int borda=10;
	public Janela(PlotBilhar plot){
		 double size[][] ={{borda, TableLayout.FILL,borda},
		          {borda, TableLayout.FILL, borda}};
		 
		 setLayout(new TableLayout(size));
		 JScrollPane plotBarraRolagem=new JScrollPane(plot.getComponentSwing());
		 add(plotBarraRolagem,"1,1,c,c");
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setSize(800,600);
		 setVisible(true);		 
	}
}
