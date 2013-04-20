package matematica;

public interface NumeroAleatorio {//interface para geradores de numeros aleatorios
    public void set_semente(long seme);//seta a semente do gerador de numeros aleatorios
    public double rand();//gera um novo numero aleatorio entre 0 e 1
    public double rand_intv(double x);//gera um numero aleatorio entre -x e  x
}