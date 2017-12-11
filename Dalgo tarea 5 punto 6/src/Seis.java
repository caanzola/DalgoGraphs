import java.util.Scanner;

public class Seis 
{

	private static Scanner in;
	private static int n;
	private static int a;
	private static int b;
	private static int contadorLinea;
	private static String[] conexionesActuales;
	private static String[] conexionesFuturo;
	private static Grafo confActual;
	private static Grafo confFutura;
	private static int distancematrixActual[][];
	private static int distancematrixNuevo[][];

	public static void main(String[] args) 
	{
		while(true)
		{
			System.out.println();
			System.out.println("-------- WELCOME TO EDGETOWN´S TRAFFIC PROBLEM ---------");
			System.out.println();
			System.out.println("INPUT:");
			System.out.println();
			contadorLinea = 0;
			in = new Scanner(System.in);
			contadorLinea++;
			System.out.println("Line #"+contadorLinea+" (n): ");
			n = in.nextInt();
			conexionesActuales = new String[n];
			conexionesFuturo = new String[n];
			
			for (int i = 0; i < n; i++) 
			{
				contadorLinea++;
				System.out.println("Line #" +contadorLinea+ " (Conection from node " + (i+1) + " in current way): ");
				if(i == 0 && in.nextLine() != " ")
				conexionesActuales[i] = in.nextLine();
				else
				conexionesActuales[i] = in.nextLine();	
			}
			
			for (int i = 0; i < n; i++) 
			{
				contadorLinea++;
				System.out.println("Line #" + contadorLinea + " (Conection from node " + (i+1) + " in future way): ");
				String datoEntrada = in.nextLine(); 
				if(i == 0 && datoEntrada != " ")
				{
					conexionesFuturo[i] = datoEntrada;
				}
				else
				conexionesFuturo[i] = datoEntrada;	
			}
			
			contadorLinea++;
			System.out.println("Line #" + contadorLinea + " A y B respectively");
			String ayb = in.nextLine();
			char[] aybArreglo = ayb.toCharArray();
			String charA = aybArreglo[0]+"";
			String charB = aybArreglo[2]+"";
			a = Integer.parseInt(charA);
			b = Integer.parseInt(charB);
			
			armarGrafos();
			
			System.out.println("");
			System.out.println("OUTPUT: ");
			System.out.println(respuestaSeis());

		}
	}

	private static void armarGrafos() 
	{
		int cantidadArcos = 0;
		for (int i = 0; i < conexionesActuales.length; i++) 
		{
			char[] linea = conexionesActuales[i].toCharArray();
			int contador = 0;
			for (int j = 0; j < linea.length; j++) 
			{
				if(!(linea[j]+"").equals(" "))
					contador++;
			}
			cantidadArcos = cantidadArcos+(contador-1);
		}

		confActual = new Grafo(cantidadArcos, n);
		confActual.llenarNodos();
		confActual.llenarArcos(conexionesActuales);
		
		int cantidadArcosNuevo = 0;
		for (int i = 0; i < conexionesFuturo.length; i++) 
		{
			char[] linea2 = conexionesFuturo[i].toCharArray();
			int contador2 = 0;
			for (int j = 0; j < linea2.length; j++) 
			{
				if(!(linea2[j]+"").equals(" "))
					contador2++;
			}
			cantidadArcosNuevo = cantidadArcosNuevo+(contador2-1);
		}

		confFutura = new Grafo(cantidadArcosNuevo, n);
		confFutura.llenarNodos();
		confFutura.llenarArcos(conexionesFuturo);
	}

	private static String respuestaSeis() 
	{
		floydWarshallGrafoActual();
		floydWarshallGrafoNuevo();
		
		String respuesta = "No";
		if(comparar())
			respuesta = "Yes";
		
		return respuesta;
	}

	private static boolean comparar() 
	{
		boolean cumple = true;
		for (int i = 0; i < distancematrixActual.length && cumple; i++) 
		{
			for (int j = 0; j < distancematrixActual.length && cumple; j++) 
			{
				if(distancematrixNuevo[i][j] > a*distancematrixActual[i][j]+b)
					cumple = false;
			}
		}
		
		return cumple;
	}

	private static void floydWarshallGrafoNuevo() 
	{
		int numeroDeNodos = confFutura.darNumNodos();
		
		distancematrixNuevo = new int[numeroDeNodos][numeroDeNodos];
		int infinity = 999;
		
		 for (int i = 0; i < distancematrixNuevo.length; i++) 
		 {
			for (int j = 0; j < distancematrixNuevo.length; j++) 
			{
				if(j==i)
				{
					distancematrixNuevo[i][j] = 0;
				}
				else
				{
					if(confFutura.darArco(i+1, j+1) != null)
					distancematrixNuevo[i][j] = confFutura.darArco(i+1, j+1).darCosto();
					else
						distancematrixNuevo[i][j] = infinity;
				}
			}
		 }
		 
		 for (int k = 0; k < n; k++) 
		 {
			 for (int i = 0; i < distancematrixNuevo.length; i++) 
			 {
				for (int j = 0; j < distancematrixNuevo.length; j++) 
				{
					distancematrixNuevo[i][j] = Math.min(distancematrixNuevo[i][j], distancematrixNuevo[i][k] + distancematrixNuevo[k][j]);
				}
			 } 
		 } 
	}

	private static void floydWarshallGrafoActual() 
	{
		int numeroDeNodos = confActual.darNumNodos();
		
		distancematrixActual = new int[numeroDeNodos][numeroDeNodos];
		int infinity = 999;
		
		 for (int i = 0; i < distancematrixActual.length; i++) 
		 {
			for (int j = 0; j < distancematrixActual.length; j++) 
			{
				if(j==i)
				{
					distancematrixActual[i][j] = 0;
				}
				else
				{
					if(confActual.darArco(i+1, j+1) != null)
					distancematrixActual[i][j] = confActual.darArco(i+1, j+1).darCosto();
					else
						distancematrixActual[i][j] = infinity;
				}
			}
		 }
		 
		 for (int k = 0; k < n; k++) 
		 {
			 for (int i = 0; i < distancematrixActual.length; i++) 
			 {
				for (int j = 0; j < distancematrixActual.length; j++) 
				{
					distancematrixActual[i][j] = Math.min(distancematrixActual[i][j], distancematrixActual[i][k] + distancematrixActual[k][j]);
				}
			 } 
		 } 

	}

}
