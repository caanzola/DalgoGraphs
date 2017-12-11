import java.util.ArrayList;

public class Nodo 
{
	
	private int elemento;
	private String color;
	private ArrayList<Nodo> adj;
	private int nivelEnArbol;

	public Nodo(int pElemento) 
	{
		elemento = pElemento;
		adj = new ArrayList<Nodo>();
	}

	public int darElemento() {
		// TODO Auto-generated method stub
		return elemento;
	}
	
	public void setColor(String pColor)
	{
		color = pColor;
	}

	public String darColor()
	{
		return color;
	}

	public void agregarAdyacente(Nodo adyacente) 
	{
		adj.add(adyacente);
	}
	
	public ArrayList<Nodo> darAdyacentes()
	{
		return adj;
	}
	
	public void setNivelEnArbol(int nivel) 
	{
		nivelEnArbol = nivel;
	}
	public int getNivelEnArbol()
	{
		return nivelEnArbol;
	}

	public boolean tieneAdyacenteBlanco() 
	{
		boolean respuesta = false;
		for (int i = 0; i < adj.size() && !respuesta; i++) 
		{
			if(adj.get(i).darColor().equals("White"))
				respuesta = true;
		}
		return respuesta;
	}
}
