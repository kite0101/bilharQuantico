package Util;

public class OpMatriz {
	public static float getMin(float mat[][]){
		float ret=mat[0][0];
		for(int cont=0;cont<mat.length;cont++){
			for(int cont2=0;cont2<mat[0].length;cont2++){
				if(ret>mat[cont][cont2]) ret=mat[cont][cont2];
			}				
		}
		return ret;
	}
	public static float getMax(float mat[][]){
		float ret=mat[0][0];
		for(int cont=0;cont<mat.length;cont++){
			for(int cont2=0;cont2<mat[0].length;cont2++){
				if(ret<mat[cont][cont2]) ret=mat[cont][cont2];
			}				
		}
		return ret;
	}
}
