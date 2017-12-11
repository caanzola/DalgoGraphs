import java.util.ArrayList;

public class Nodo 
{
	
	private int elemento;
	private String color;
	private ArrayList<Nodo> adj;

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
}
