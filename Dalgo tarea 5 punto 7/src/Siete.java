import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import javax.swing.SortingFocusTraversalPolicy;

public class Siete 
{

	private static Scanner in;
	private static int numLinea;
	private static int numNodos;
	private static int numArcos;
	private static String[] conexionesA;
	private static String[] conexionesB;
	private static Grafo grafoA;
	private static Grafo grafoB;
	
	public static void main(String[] args) 
	{
		while(true)
		{
			numLinea = 1;
			System.out.println();
			System.out.println("-------- WELCOME TO COMBATING CANCER PROBLEM ---------");
			System.out.println();
			System.out.println("INPUT:");
			System.out.println();
			System.out.println("Line # " + numLinea + " (Number of nodes):");
			numLinea++;
			in = new Scanner(System.in);
			numNodos = in.nextInt();
			
			System.out.println();
			System.out.println("Conections:");
			System.out.println();
			
			numArcos = numNodos-1;
			conexionesA = new String[numArcos];
			conexionesB = new String[numArcos];
			int c2 = 0;
			
			for (int i = 0; i < (numNodos*2)-2; i++) 
			{
				System.out.println("Line # " + numLinea + ":");
				if(i == 0)
				in.nextLine();
				if(i < numNodos-1)
				conexionesA[i] =in.nextLine();
				else
				{
					conexionesB[c2] =in.nextLine();
					c2++;
				}
				numLinea++;
			}
			
			crearGrafoA();
			
			/*for (int i = 0; i < conexionesB.length; i++) 
			{
				System.out.println("Line # " + numLinea + ":");
				if(i == 1)
				in.nextLine();
				conexionesB[i] =in.nextLine();
				numLinea++;
			}*/
			
			crearGrafoB();
			System.out.println("OUTPUT: ");
			System.out.println();
			System.out.println(respuestaSiete());
		}
	}

	private static String respuestaSiete() 
	{
		String respuesta = "N";
		
		bfs(grafoA, 0);
		
		boolean encontroIgual = false;
		for (int i = 0; i < grafoB.darNumNodos() && !encontroIgual; i++) 
		{
			bfs(grafoB, i);
			encontroIgual = compararArboles();
		}
		
		if(encontroIgual)
			respuesta = "S";
		
		return respuesta;
	}

	private static boolean compararArboles() 
	{
		int[] arbolGrafoA = new int[numNodos];
		int[] arbolGrafoB = new int[numNodos];
		boolean respuesta = true;
		
		for (int i = 0; i < arbolGrafoA.length; i++) 
		{
			arbolGrafoA[i] = darNodosEnNivel(grafoA, i);
		}
		
		for (int i = 0; i < arbolGrafoB.length; i++) 
		{
			arbolGrafoB[i] = darNodosEnNivel(grafoB, i);
		}
		
		for (int i = 0; i < arbolGrafoB.length && i < arbolGrafoA.length && respuesta; i++) 
		{
			if(arbolGrafoA[i] != arbolGrafoB[i])
				respuesta = false;
		}
		
		return respuesta;
	}

	private static int darNodosEnNivel(Grafo g, int nivel) 
	{
		int respuesta = 0;
		for (int i = 0; i < g.darNodos().length; i++) 
		{
			if(g.darNodos()[i].getNivelEnArbol() == nivel)
				respuesta++;
		}
		return respuesta;
	}

	private static void bfs(Grafo g, int nodoFuente) 
	{
		int nivelEnElArbol = 0;
		// Cada que agrega a la cola esos son sus hijos
		for (int i = 0; i < g.darNodos().length; i++) 
		{
			g.darNodos()[i].setColor("White");
		}
		g.darNodos()[nodoFuente].setColor("Gray");
		LinkedList<Nodo> cola = new LinkedList<Nodo>();
		cola.addLast(g.darNodos()[nodoFuente]);
		g.darNodos()[nodoFuente].setNivelEnArbol(nivelEnElArbol);
		
		while(cola.isEmpty() == false)
		{
			Nodo u = cola.removeFirst();
			if(u.tieneAdyacenteBlanco())
			{
				nivelEnElArbol++;
			}
			for (int i = 0; i < u.darAdyacentes().size(); i++) 
			{
				if(u.darAdyacentes().get(i).darColor().equals("White"))
				{
					u.darAdyacentes().get(i).setColor("Gray");
					cola.addLast(u.darAdyacentes().get(i));
					u.darAdyacentes().get(i).setNivelEnArbol(nivelEnElArbol);
				}
			}
			u.setColor("Black");
		}
	}

	private static void crearGrafoB() 
	{
		grafoB = new Grafo(numArcos, numNodos);
		grafoB.llenarNodos();
		grafoB.llenarArcos(conexionesB);
	}

	private static void crearGrafoA() 
	{
		grafoA = new Grafo(numArcos, numNodos);
		grafoA.llenarNodos();
		grafoA.llenarArcos(conexionesA);
	}
}
