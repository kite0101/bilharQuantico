package fisica;
import java.util.ArrayList;
import Image_manipulator.Image_manipulator;
import java.io.IOException;
import java.util.ArrayList;


import matematica.CongruenciaLinear;
import matematica.NumeroAleatorio;
import matematica.Ponto;
import matematica.Reta;
import matematica.SegmentoReta;
import matematica.Vetor;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kite
 */
public class FractalFlocoNeveAleatorio {
    private int matrix[][];//matriz de pontos, com o fractal
    private ArrayList<SegmentoReta> retas;
    public FractalFlocoNeveAleatorio(long seme, int tam,double xini,double yini){//cria o fractal do floco de neve aleatorio
                                                                            //atraves da tecnica L-system, que usa automatos
                                                                            //finitos para desenhar fractais
        //long seme-> semente para o gerador de numeros aleatorios
        //double xini-> posicao inicial em que o automato esta
        //double yini-> posicao inicial em que o automato esta
    	retas=new ArrayList<SegmentoReta>();
        matrix=new int[tam][tam];//matriz de pontos dos lugares onde o agente parou apos ter sido dito para ele andar.
        Vetor localizacaoAutomato =new Vetor(6.0, 0.0);//inidica onde o automato esta no eixo x e y
        ArrayList<Integer> gramar=gera_gramatica(4, seme);//cria gramatica que gera o fractal, esta grama contem as instrucoes que o automato deve seguir durante o seu percurso
        for(int cont=0;cont<gramar.size();cont++){//executa a gramatica
            if(gramar.get(cont).intValue()==0){//ordem para o automato andar                                                                  
                retas.add(new SegmentoReta(yini, yini+localizacaoAutomato.get_b(), xini, xini+localizacaoAutomato.get_a()));
                xini=xini+localizacaoAutomato.get_a();//faz o agente andar
                yini= yini+localizacaoAutomato.get_b();//faz o agente andar
            }
            else if(gramar.get(cont).intValue()==1) localizacaoAutomato.rotacao(60);//vira o automato em 60 graus, o automato nao anda apenas se vira 
            else if(gramar.get(cont).intValue()==2) localizacaoAutomato.rotacao(-60);//vira o automato em -60 graus, o automato nao anda apenas se vira
        }
        for(int cont=0;cont<retas.size();cont++){        	
        	desenhar_reta(retas.get(cont));
        }

    }
    public ArrayList<SegmentoReta> getListaRetas(){
    	return  retas;
    }
    private ArrayList<Integer> gera_gramatica(int tam, long seme ){        
        //tam -> nivel da gramatica
        ArrayList<Integer> temp;
        ArrayList<Integer> gramar=new ArrayList<Integer>();
        //condicao inicial da gramatica
        gramar.add(new Integer(0));
        gramar.add(new Integer(2));
        gramar.add(new Integer(2));
        gramar.add(new Integer(0));
        gramar.add(new Integer(2));
        gramar.add(new Integer(2));
        gramar.add(new Integer(0));
        //fim
        //cria gerador de numeros aleatorios
        NumeroAleatorio aleatorio=new CongruenciaLinear(seme);
        //fim
        //expande a gramatica
        for(int cont0=0;cont0<tam;cont0++){
             temp= new ArrayList<Integer>();
            for(int cont2=0;cont2<gramar.size();cont2++){
                if(gramar.get(cont2).intValue()==0){
                    double rand=aleatorio.rand();
                    if(rand<0.6){
                        temp.add(0);
                        temp.add(1);
                        temp.add(0);
                        temp.add(2);
                        temp.add(2);
                        temp.add(0);
                        temp.add(1);
                        temp.add(0);
                    }
                    else{
                        temp.add(0);
                        temp.add(2);
                        temp.add(0);
                        temp.add(1);
                        temp.add(1);
                        temp.add(0);
                        temp.add(2);
                        temp.add(0);
                    }
                }
                else temp.add(gramar.get(cont2).intValue());
            }
            //fim
            gramar=temp;
        }
        return gramar;
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
            if(xt>=0 && yt>=0 && xt<matrix.length && yt< matrix[0].length){//ve se nao passou dos limites da matriz
                matrix[(int)xt][(int)yt]=255;                
            }
            xt=xt+0.001;
        }
    }
    
}
