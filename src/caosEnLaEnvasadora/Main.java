package caosEnLaEnvasadora;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		
		SecuenciaDeEtiquetas secDeMaquina=new SecuenciaDeEtiquetas(JOptionPane.showInputDialog("Ingrese la ruta completa del archivo"));
		//secDeMaquina.getCantidad();
		//secDeMaquina.secMasLarga();
		secDeMaquina.segSecMasLarga(secDeMaquina.secMasLarga());
		secDeMaquina.distanciaEntreSecuencias();
		secDeMaquina.crearArchSalida();
	}
}
