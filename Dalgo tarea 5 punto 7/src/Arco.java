
public class Arco 
{

	private int costo;
	private Nodo nodoOrigen;
	private Nodo nodoDestino;
	private boolean esDirigido;
	
	public Arco(Nodo pPadre, Nodo pHijo, int pCosto) 
	{
		nodoOrigen = pPadre;
		nodoDestino = pHijo;
		costo = pCosto;
	}

	public Nodo darPadre() 
	{
		// TODO Auto-generated method stub
		return nodoOrigen;
	}

	public Nodo darHijo() {
		// TODO Auto-generated method stub
		return nodoDestino;
	}

	public int darCosto() 
	{
		// TODO Auto-generated method stub
		return costo;
	}

}
