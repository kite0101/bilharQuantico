package gui;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Util.OpMatriz;

import fisica.FractalFlocoNeveAleatorio;
import fisica.Particula;

import matematica.Ponto;
import matematica.Reta;
import matematica.Vetor;
import processing.core.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlotBilhar extends PApplet{

    /**
     * @param args the command line arguments
     */
    int mesaBilhar[][];
    Particula par;
    int sizeX,sizeY;
    Particula part1;    
    Particula part2;    
    public PlotBilhar(int mesaBilhar[][], Particula part1){
    	this.mesaBilhar=new int[mesaBilhar.length][mesaBilhar[0].length];
    	sizeX=mesaBilhar.length;
    	sizeY=mesaBilhar[0].length;
    	part2=null;
    	//copia os dados do argumento local mesaBilhar para o atributo mesaBilhar 
    	for(int cont=0;cont<mesaBilhar.length;cont++){
    		for(int cont2=0;cont2<mesaBilhar[0].length;cont2++){
    			this.mesaBilhar[cont][cont2]=mesaBilhar[cont][cont2];
    		}
    	}
    	//fim    	
    	this.part1=part1;
    	
    	
    }
    public PlotBilhar(int mesaBilhar[][], Particula part1, Particula part2){
    	this.mesaBilhar=new int[mesaBilhar.length][mesaBilhar[0].length];
    	sizeX=mesaBilhar.length;
    	sizeY=mesaBilhar[0].length;
    	//copia os dados do argumento local mesaBilhar para o atributo mesaBilhar 
    	for(int cont=0;cont<mesaBilhar.length;cont++){
    		for(int cont2=0;cont2<mesaBilhar[0].length;cont2++){
    			this.mesaBilhar[cont][cont2]=mesaBilhar[cont][cont2];
    		}
    	}
    	//fim    	
    	this.part1=part1;
    	this.part2=part2;
    	
    	
    }    
    public void setup(){
    	size(sizeX,sizeY);
        frameRate(30);
        background(255);

    }
    public void draw(){
        background(255);
        for(int cont=0;cont<mesaBilhar.length;cont++){
            for(int cont1=0;cont1<mesaBilhar[0].length;cont1++){      
                if(mesaBilhar[cont][cont1]>0){
                    stroke(0,0,255);
                    point(cont,sizeY-cont1);
                }    
            }                       
        } 
        if(part2==null) drawOnePart();
        else drawTwoPart();
       
              
    }
    public void drawTwoPart(){
    	float percursoPart1[][]= part1.percursoParticula();
    	float percursoPart2[][]= part2.percursoParticula();
    	for(int cont=0;cont<mesaBilhar.length;cont++){
            for(int cont1=0;cont1<mesaBilhar[0].length;cont1++){      
                if(percursoPart1[cont][cont1]>0){
                    stroke(percursoPart1[cont][cont1],0f,0f);
                    point(cont,sizeY-cont1);
                }    
                /*if(percursoPart2[cont][cont1]>0){
                    stroke(0,0,255);
                    point(cont,cont1);
                }*/
            }                       
        }
    }
    public void drawOnePart(){    	
       double delta=OpMatriz.getMax(part1.percursoParticula())-OpMatriz.getMin(part1.percursoParticula());
       //if(delta==0.0)delta=1.0;
        if(part1.getRetaColisao()!=null && part1.getRetaColisaoPerpendicular() !=null){
        	double eqReta[]=part1.getRetaColisao().equacao_reta();
        	double eqReta2[]=part1.getRetaColisaoPerpendicular().equacao_reta();
        	stroke(255,0,0);
        	line(0, sizeY-(float)eqReta[1], (float)sizeX, sizeY-(float)(eqReta[1]+(eqReta[0]*sizeX)));
        	stroke(255,0,255);
        	line(0, sizeY-(float)eqReta2[1], (float)sizeX, sizeY-(float)(eqReta2[1]+(eqReta2[0]*sizeX)));
        	stroke(0,0,255);
        }          
        float percursoPart1[][]= part1.percursoParticula();
        for(int cont=0;cont<mesaBilhar.length;cont++){
            for(int cont1=0;cont1<mesaBilhar[0].length;cont1++){      
                if(percursoPart1[cont][cont1]>0){
                	if(delta>0.0){
                		int val=(int) Math.round((percursoPart1[cont][cont1]/delta)*255.0f);
                		stroke(255-val,255-val,255-val);
                		point(cont,sizeY-cont1);
                    }
                }    
            }                       
        }
        stroke(0,0,255);
        
        ellipse((float)part1.getPosiX(),sizeY-(float)part1.getPosiY(),10f,10f);
        
    }
    public JPanel getComponentSwing(){
    	JPanel ret= new JPanel();    	
        this.init();
        ret.add(this);
        return ret;
    }
}


