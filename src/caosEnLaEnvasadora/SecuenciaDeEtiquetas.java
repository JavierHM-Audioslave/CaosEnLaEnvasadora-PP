package caosEnLaEnvasadora;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.io.*;

public class SecuenciaDeEtiquetas {
	
	private char[] secuencia;
	private int cantidad; // Primera linea de salida. //
	private int cantDeLatasBienEtiquetadas; // Segunda linea de salida. //
	private int posIzquierdaDePrimerLataBienEtiquetadaCorrespondienteALaSecuenciaMasLarga;
	private int posDerechaDePrimerLataBienEtiquetadaCorrespondienteALaSecuenciaMasLarga;
	private int posIzquierdaDePrimerLataBienEtiquetadaCorrespondienteALaSegundaSecuenciaMasLarga;
	private int posDerechaDePrimerLataBienEtiquetadaCorrespondienteALaSegundaSecuenciaMasLarga;
	private int cantDeLatasBienEtiquetadasCorrespondientesALaSecuenciaMasLarga;
	private int cantDeLatasBienEtiquetadasCorrespondientesALaSegundaSecuenciaMasLarga;
	private int segundaCantDeLatasBienEtiquetadas; // Tercera lina de salida. //
	private int distanciaEntreBienEtiquetadas; // Cuarta linea de salida. //
	
	public SecuenciaDeEtiquetas(String ruta)
	{
		try
		{
			File arch=new File(ruta);
			FileReader fr=new FileReader(arch);
			BufferedReader br=new BufferedReader(fr);
			String[] entradaProvisoria=new String[5000];
			entradaProvisoria=br.readLine().split(" ");
			int i=0;
			while(entradaProvisoria[i].toUpperCase().charAt(0)!='F')
			{
				i++;
			}
			try
			{
				fr.close();
				br.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.exit(1);
			}
			secuencia=new char[i];
			cantidad=i;
			for(int j=0; j<secuencia.length; j++)
			{
				secuencia[j]=entradaProvisoria[j].charAt(0);
			}
			cantDeLatasBienEtiquetadas=0;
			posDerechaDePrimerLataBienEtiquetadaCorrespondienteALaSecuenciaMasLarga=0;
			posDerechaDePrimerLataBienEtiquetadaCorrespondienteALaSegundaSecuenciaMasLarga=0;
			segundaCantDeLatasBienEtiquetadas=0;
			posIzquierdaDePrimerLataBienEtiquetadaCorrespondienteALaSecuenciaMasLarga=0;
			posIzquierdaDePrimerLataBienEtiquetadaCorrespondienteALaSegundaSecuenciaMasLarga=0;
			cantDeLatasBienEtiquetadasCorrespondientesALaSecuenciaMasLarga=0;
			cantDeLatasBienEtiquetadasCorrespondientesALaSegundaSecuenciaMasLarga=0;
			distanciaEntreBienEtiquetadas=0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	public int secMasLarga()
	{
		int auxLargoMaximo=0; //pos va a contener la ultima posicion de la G de la secuencia mas larga.
		int auxPosIzquierda=0, auxPosDerecha=0, j;
		for(int i=0; i<secuencia.length; i++)
		{
			if(secuencia[i]=='G')
			{
				auxLargoMaximo=0;
				auxPosIzquierda=i;
				for(j=i; j<secuencia.length && secuencia[j]=='G'; j++)
				{
					auxLargoMaximo++;
					i++;
				}
				auxPosDerecha=j-1;
			}
			if(auxLargoMaximo>cantDeLatasBienEtiquetadasCorrespondientesALaSecuenciaMasLarga)
			{
				cantDeLatasBienEtiquetadasCorrespondientesALaSecuenciaMasLarga=auxLargoMaximo;
				posIzquierdaDePrimerLataBienEtiquetadaCorrespondienteALaSecuenciaMasLarga=auxPosIzquierda;
				posDerechaDePrimerLataBienEtiquetadaCorrespondienteALaSecuenciaMasLarga=auxPosDerecha;
			}
		}
	
		return cantDeLatasBienEtiquetadasCorrespondientesALaSecuenciaMasLarga;
	}
	
	public void segSecMasLarga(int masLargo)
	{
		int auxSegundoLargoMaximo=0, auxPosIzquierda=0, auxPosDerecha=0, i, j;
		for(i=0; i<secuencia.length; i++)
		{
			if(secuencia[i]=='G')
			{
				auxPosIzquierda=i;
				auxSegundoLargoMaximo=0;
				for(j=i; j<secuencia.length && secuencia[j]=='G'; j++)
				{
					auxSegundoLargoMaximo++;
					i++;
				}
				auxPosDerecha=j-1;
			}
			if(auxSegundoLargoMaximo>segundaCantDeLatasBienEtiquetadas && auxSegundoLargoMaximo<masLargo)
			{
				segundaCantDeLatasBienEtiquetadas=auxSegundoLargoMaximo;
				posIzquierdaDePrimerLataBienEtiquetadaCorrespondienteALaSegundaSecuenciaMasLarga=auxPosIzquierda;
				posDerechaDePrimerLataBienEtiquetadaCorrespondienteALaSegundaSecuenciaMasLarga=auxPosDerecha;
			}
		}
	}
	
	public void distanciaEntreSecuencias()
	{
		if(posIzquierdaDePrimerLataBienEtiquetadaCorrespondienteALaSecuenciaMasLarga>posIzquierdaDePrimerLataBienEtiquetadaCorrespondienteALaSegundaSecuenciaMasLarga)
		{
			distanciaEntreBienEtiquetadas=Math.abs(posIzquierdaDePrimerLataBienEtiquetadaCorrespondienteALaSecuenciaMasLarga-posDerechaDePrimerLataBienEtiquetadaCorrespondienteALaSegundaSecuenciaMasLarga-1);
		}
		else
		{
			distanciaEntreBienEtiquetadas=Math.abs(posIzquierdaDePrimerLataBienEtiquetadaCorrespondienteALaSegundaSecuenciaMasLarga-posDerechaDePrimerLataBienEtiquetadaCorrespondienteALaSecuenciaMasLarga);
		}		
	}
	
	public void crearArchSalida()
	{
		File archSal;
		FileWriter fw;
		PrintWriter pw;
		try
		{
			archSal=new File(JOptionPane.showInputDialog("Ingrese la ruta de salida"));
			fw= new FileWriter(archSal);
			pw=new PrintWriter(fw);
			pw.println(cantidad);
			pw.println(cantDeLatasBienEtiquetadasCorrespondientesALaSecuenciaMasLarga);
			pw.println(segundaCantDeLatasBienEtiquetadas);
			pw.println(distanciaEntreBienEtiquetadas);
			try
			{
				fw.close();
				pw.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.exit(1);
		}
	}	
}



