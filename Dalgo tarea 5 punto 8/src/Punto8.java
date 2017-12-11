import java.util.ArrayList;
import java.util.Scanner;

public class Punto8 
{

	private static int numGrafos;
	private static int numNodos;
	private static int numLinea;
	private static int[] numsArcos;
	private static Scanner in;
	private static ArrayList<String[]> conexiones;
	private static Grafo[] grafos;
	
	public static void main(String[] args) 
	{
		while(true)
		{
			numLinea = 1;
			System.out.println();
			System.out.println("-------- WELCOME TO CIRCUIT NETS PROBLEM ---------");
			System.out.println();
			System.out.println("INPUT:");
			System.out.println();
			System.out.println("Line # " + numLinea + " (Number of cases):");
			numLinea++;
			in = new Scanner(System.in);
			numGrafos = in.nextInt();
			grafos = new Grafo[numGrafos];
			numsArcos = new int[numGrafos];
			System.out.println("Line # " + numLinea + " (Space)");
			System.out.println();
			numLinea++;
			System.out.println("Line # " + numLinea + " (N):");
			numNodos = in.nextInt();
			numLinea++;
			System.out.println("Line # " + numLinea + " (Conections):");
			in.nextLine();
			String cadenaDeConexiones = in.nextLine();
			String[] arregloConexiones = cadenaDeConexiones.split(" ");
			int dondeVa = 0;
			int empieza = 0;
			
			for (int i = 0; i < numsArcos.length; i++) 
			{
				int numArcos = 0;
				boolean acabo = false;

				for (int i1 = empieza; i1 < arregloConexiones.length && !acabo; i1=i1+2) 
				{

					if(estaAntes(i1, arregloConexiones, empieza)==false)
					{
						numArcos++;
					}
					else
					{
						acabo = true;
						empieza = i1;
					}
					dondeVa=i1;
				}	
				numsArcos[i] = numArcos; 
			}
			
			conexiones = new ArrayList<String[]>();
			
			int count = 0;
			for (int i = 0; i < numsArcos.length; i++) 
			{
				String[] arr = new String[numsArcos[i]];
				for (int j = 0; j < arr.length; j++) 
				{
					arr[j] = arregloConexiones[count] + " " + arregloConexiones[count+1];
					count+=2;
				}
				conexiones.add(arr);
			}
			crearGrafos();
			int[] outputs = respuestaOcho();
			System.out.println();
			System.out.println("OUTPUT:");
			for (int i = 0; i < outputs.length; i++) 
			{
				System.out.println(outputs[i]);
			}
		}
	}

	private static boolean estaAntes(int posicion, String[] arreglo, int empieza) 
	{
		int count = 0;
		boolean ans = false;
		for (int i = posicion; i >=empieza; i=i-2) 
		{
			if(arreglo[posicion].equals(arreglo[i]))
				count++;
		}
		
		if(count > 1)
			ans = true;
		
		return ans;
	}

	private static int[] respuestaOcho() 
	{
		int[] respuesta = dfsCompConexas();
		return respuesta;
	}

	private static int[] dfsCompConexas() 
	{
		int[] respuesta = new int[numGrafos];
		for (int i = 0; i < grafos.length; i++) 
		{
			Grafo grafoActual = grafos[i];
			for (int j = 0; j < grafoActual.darNodos().length; j++) 
			{
				grafoActual.darNodos()[j].setColor("White");
			}
			int count = 0;
			for (int j = 0; j < grafoActual.darNodos().length; j++) 
			{
				if(grafoActual.darNodos()[j].darColor().equals("White"))
				{
					dfsVisit(grafoActual, grafoActual.darNodos()[j]);
					count++;
				}
			}
			
			respuesta[i] = count;
		}
		return respuesta;
	}

	private static void dfsVisit(Grafo g, Nodo u) 
	{
		u.setColor("Gray");
		for (int i = 0; i < u.darAdyacentes().size(); i++) 
		{
			if(u.darAdyacentes().get(i).darColor().equals("White"))
			{
				dfsVisit(g, u.darAdyacentes().get(i));
			}
		}
		u.setColor("Black");
	}

	private static void crearGrafos() 
	{
		for (int i = 0; i < grafos.length; i++) 
		{
			grafos[i] = new Grafo(numsArcos[i], numNodos);
			grafos[i].llenarNodos();
			grafos[i].llenarArcos(conexiones.get(i));
		}
	}
}
