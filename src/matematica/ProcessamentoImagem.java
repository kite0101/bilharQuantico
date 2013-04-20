package matematica;

public class ProcessamentoImagem {
	int matrix[][];//matriz em que as operacoes de processamento
	//serao aplicadas		
	public	ProcessamentoImagem(int matrix[][]){
		this.matrix=new int[matrix.length][matrix[0].length];
		for(int cont=0;cont<matrix.length;cont++){
			for(int cont2=0;cont2<matrix[0].length;cont2++){
				this.matrix[cont][cont2]=matrix[cont][cont2];
			}
		}
	}

	public void aplicarMorfologiaDilatacao(){        
		int matrixRet[][]=new int[matrix.length][matrix[0].length];
		for(int cont=0;cont<matrix.length-1;cont++){//Aplica dilatacao
			for(int cont2=0;cont2<matrix[0].length-1;cont2++){
				if(matrix[cont][cont2]==1){
					matrixRet[cont+1][cont2]=1;
					matrixRet[cont][cont2+1]=1;
				}
			}            
		}        
		this.matrix=matrixRet;
	}
	public void aplicarMorfologiaErosao(){
		int matrixRet[][]=new int[matrix.length][matrix[0].length];        
		for(int cont=0;cont<matrix.length-1;cont++){//Aplica erosao
			for(int cont2=0;cont2<matrix[0].length-1;cont2++){
				if(matrix[cont][cont2]==1 && matrix[cont+1][cont2]==1 && matrix[cont][cont2+1]==1 && matrix[cont+1][cont2+1]==1){
					matrixRet[cont][cont2]=1;
				}
				else matrixRet[cont][cont2]=0;
			}
		}
        this.matrix=matrixRet; 
	}
	public int[][] getMatrixProcessada(){
		return this.matrix;
	}
	public void desfocar(){
		int matrixRet[][]=new int[matrix.length][matrix[0].length];

		for(int cont=1;cont<matrix.length-1;cont++){//Aplica suavizacao
			for(int cont2=1;cont2<matrix[0].length-1;cont2++){
				int sum=0;
				for(int x=-1;x<2;x++){
					for(int y=-1;y<2;y++){
						sum=sum+matrix[cont+x][cont2+y];
					}
				}
				sum=sum/9;
				if(sum>0)
					matrixRet[cont][cont2]=sum;

			}
		}
		this.matrix=matrixRet;
	}
	public void binarizar(){
		for(int cont=0;cont<matrix.length;cont++){
			for(int cont2=0;cont2<matrix[0].length;cont2++){
				if(matrix[cont][cont2]>0)matrix[cont][cont2]=1;
				else matrix[cont][cont2]=0;
			}
		}
	}
	public void binarizar256(){
		for(int cont=0;cont<matrix.length;cont++){
			for(int cont2=0;cont2<matrix[0].length;cont2++){
				if(matrix[cont][cont2]>0)matrix[cont][cont2]=255;
				else matrix[cont][cont2]=0;
			}
		}
	}
	public void esquetizarHilditch(){
		binarizar();
		int matrizRet[][]=new int[matrix.length][matrix[0].length];
		for(int cont=2;cont<matrix.length-2;cont++){
			for(int cont2=2;cont2<matrix[0].length-2;cont2++){
				int ap1,ap4,ap2,bp1;
				ap1=cruzaZero(cont, cont2);
				ap4=cruzaZero(cont, cont2+1);
				ap2=cruzaZero(cont-1, cont2);
				bp1=viziZero(cont, cont2);
				boolean cond=(matrix[cont][cont2-1]*matrix[cont][cont2+1]*matrix[cont-1][cont2]==0)||(ap2!=1);
				boolean cond2=(matrix[cont-1][cont2]*matrix[cont][cont2+1]*matrix[cont-1][cont2]==0)||(ap4!=1);
				boolean cond3=(bp1>=2 && bp1<=6);
				if(cond && cond3 &&cond2 && ap1==1){
					matrizRet[cont][cont2]=1;					
				}
				else{
					matrizRet[cont][cont2]=0;
				}
			}
		}
		matrix=matrizRet;
	}
	private int cruzaZero(int i, int j){
		int tam=0;
		if(matrix[i][j]==0){
			int p[]=new int[9];
			p[8]=matrix[i-1][j-1];
			p[7]=matrix[i][j-1];
			p[6]=matrix[i+1][j-1];
			p[5]=matrix[i+1][j];
			p[4]=matrix[i+1][j+1];
			p[3]=matrix[i][j+1];
			p[2]=matrix[i-1][j+1];
			p[1]=matrix[i-1][j];
			p[0]=matrix[i][j];		
			for(int cont=2;cont<=8;cont++){
				if(p[cont-1]==0 && p[cont]==1){
					tam=tam+1;
				}
			}
			if(p[8]==0 && p[1]==1){
				tam=tam+1;
			}
		}
		return tam;
	}
	private int viziZero(int i, int j){
		int tam=0;
		if(matrix[i][j]==0){
			int p[]=new int[9];
			p[8]=matrix[i-1][j-1];
			p[7]=matrix[i][j-1];
			p[6]=matrix[i+1][j-1];
			p[5]=matrix[i+1][j];
			p[4]=matrix[i+1][j+1];
			p[3]=matrix[i][j+1];
			p[2]=matrix[i-1][j+1];
			p[1]=matrix[i-1][j];
			p[0]=matrix[i][j];		
			for(int cont=0;cont<=8;cont++){
				if(p[cont]==1) tam++;
			}
			
		}
		return tam;
	}
}
