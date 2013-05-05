package gui;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Util.OpMatriz;

import fisica.Particula;

import matematica.Ponto;
import matematica.Reta;
import matematica.SegmentoReta;
import matematica.Vetor;
import processing.core.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlotBilhar extends PApplet{

    /**
     * @param args the command line arguments
     */
    ArrayList<SegmentoReta> retas;
    Particula par;
    int sizeX,sizeY;
    Particula part1;    
    Particula part2;    
    public PlotBilhar(ArrayList<SegmentoReta> retas, Particula part1){    	
    	this.retas=retas;
    	part2=null;    	    
    	this.part1=part1;
    	sizeX=600;
    	sizeY=600;
    	
    	
    }
        
    public void setup(){
    	size(sizeX,sizeY,P3D);
        frameRate(30);
        background(255);
        ortho(0, width,height,0 , -10,10);

    }
    public void draw(){
    	translate(width, height);
    	scale(90);
        background(255);
        stroke(0,0,255);       	
    	line(0, 0,20,10);
        for(SegmentoReta r: retas){
        	stroke(0,0,255);        	
        	line((float)r.get_p1x(), (float)r.get_p1y(),(float) r.get_p2x(),(float) r.get_p2y());
        }
        
        if(part2==null) drawOnePart();
  
       
              
    }
   
    public void drawOnePart(){    	
    	stroke(255,0,0);
        ellipse((float)part1.getPosiX(),(float)part1.getPosiY(),0.1f,0.1f);
        
    }
    public JPanel getComponentSwing(){
    	JPanel ret= new JPanel();    	
        this.init();
        ret.add(this);
        return ret;
    }
}


