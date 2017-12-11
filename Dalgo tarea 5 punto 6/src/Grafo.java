
public class Grafo 
{

	private Arco[] arcos;
	private Nodo[] nodos;
	int numNodos;
	
	
	public Grafo(int pNumArcos, int pNumNodos) 
	{
		arcos = new Arco[pNumArcos];
		nodos = new Nodo[pNumNodos];
		numNodos = pNumNodos;
	}

	public void llenarArcos(String[] conexionesActuales) 
	{
		int dondeVa = 0;
			for (int j = 0; j < conexionesActuales.length; j++) 
			{
				char[] linea = conexionesActuales[j].toCharArray();
				Nodo padre = new Nodo(Integer.parseInt(linea[0]+""));
				for (int k = 0; k < linea.length; k++) 
				{
					if(k != 0 && !(linea[k]+"").equals(" "))
					{
						Nodo hijo = new Nodo(Integer.parseInt(linea[k]+""));
						arcos[dondeVa] = new Arco(padre, hijo, 1);
						dondeVa++;
					}
				}
			}

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

}
