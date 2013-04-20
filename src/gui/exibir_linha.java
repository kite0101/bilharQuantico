package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import matematica.Reta;
import matematica.Vetor;
import processing.core.PApplet;

/**
 *
 * @author kite
 */
public class exibir_linha extends PApplet {
    Reta linhas[];
    Vetor vet[];
    public exibir_linha(Reta linhas[], Vetor vet[]){
        this.linhas=linhas;
        this.vet=vet;
    }
    public void setup(){
        size(600,600,P3D);
        colorMode(RGB, 1);
        frameRate(30);
    }
    public void draw(){
        background(255);
        pushMatrix();
        translate(width/2, (height/2)+19, 0);
        scale(90.0f);
        if(linhas[0]!=null){
            stroke(0.0f);          
            line(1.0f,(float)(1.0*linhas[0].equacao_reta()[0]),0,0);
            stroke(1.0f,1.0f,1.0f);
            line((float)vet[0].get_a()*100, (float)vet[0].get_b()*100, 0.0f, 0.0f);
             stroke(1.0f,0.0f,0.0f);
            line((float)vet[1].get_a()*100, (float)vet[1].get_b()*100, 0.0f, 0.0f);

        }
        popMatrix();
    }
    public void init2(){
        JFrame janela=new JFrame();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        this.init();
        JPanel painel=new JPanel();
        painel.add(this);
        janela.add(painel);
        janela.setSize(600,600);


        janela.setVisible(true);
    }
}

