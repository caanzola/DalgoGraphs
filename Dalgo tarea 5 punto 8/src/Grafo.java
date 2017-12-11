
public class Grafo 
{

	private Arco[] arcos;
	private Nodo[] nodos;
	int numNodos;
	int indice;
	
	
	public Grafo(int pNumArcos, int pNumNodos) 
	{
		arcos = new Arco[pNumArcos*2];
		nodos = new Nodo[pNumNodos];
		numNodos = pNumNodos;
	}

	public void llenarArcos(String[] conexionesActuales) 
	{
		indice = 0;
			for (int j = 0; j < conexionesActuales.length; j++) 
			{
				String[] linea = conexionesActuales[j].split(" ");
				Nodo uno = new Nodo(Integer.parseInt(linea[0]+""));
				Nodo dos = new Nodo(Integer.parseInt(linea[1]+""));
				arcos[indice] = new Arco(uno, dos, 1);
				indice++;
				arcos[indice] = new Arco(dos, uno, 1);
				indice++;
				
				Nodo padreEnGrafo = buscarNodo(uno);
				Nodo hijoEnGrafo = buscarNodo(dos);
				padreEnGrafo.agregarAdyacente(hijoEnGrafo);
				hijoEnGrafo.agregarAdyacente(padreEnGrafo);
			}
	}

	private Nodo buscarNodo(Nodo padre) 
	{
		Nodo res=null;
		boolean encontro = false;
		for (int i = 0; i < nodos.length && !encontro; i++) 
		{
			if(nodos[i].darElemento() == padre.darElemento())
			{
				res = nodos[i];
				encontro =true;
			}
		}
		return res;
	}

	public void llenarNodos() 
	{
		for (int i = 0; i < nodos.length; i++) 
		{
			nodos[i] = new Nodo(i+1);
		}	
	}
	
	public int darNumNodos()
	{
		return numNodos;
	}
	
	public Arco[] darArcos()
	{
		return arcos;
	}

	public Arco darArco(int origen, int destino) 
	{
		Arco respuesta = null;
		boolean encontro = false;
		for (int i = 0; i < arcos.length && !encontro; i++) 
		{
			if(arcos[i].darHijo().darElemento() == destino && arcos[i].darPadre().darElemento() == origen)
			{
				respuesta = arcos[i]; 
				encontro = true;
			}
		}
		return respuesta;
	}

	public Nodo[] darNodos() 
	{
		return nodos;
	}

}
