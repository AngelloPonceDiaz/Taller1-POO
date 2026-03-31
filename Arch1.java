package Taller1;
// Angello Alexis Ponce Díaz, RUT: 22025486-0, Carrera: ICCI
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Arch1 {
	static String[] usuariosList;
	static String[] passwordList;
	
	static String[] regUser = new String[300];
	static String[] regFecha = new String[300];
	static String[] regActividad = new String[300];
	static String[] regHora = new String[300];
	static int totalRegistros = 0;
	static Scanner escribe = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException 
	{
		cargarUsu();
		cargarReg();

		
		int opcion = 0;
		do {
			System.out.println("1) Menu de usuarios");
			System.out.println("2) Menu de Analisis");
			System.out.println("3) Salir");
			System.out.print("Ingrese una opcion: ");
			
			opcion = escribe.nextInt();
			escribe.nextLine();
			
			if (opcion == 1) {
				logIn();
				
				
			} else if(opcion == 2) {
				// menu de analisis :P
			}
			
		} while(opcion != 3);
		System.out.println("Gracias por usar este programa.");
	}

	
	// ************************** FUNCIONES ******************************* //
	public static void cargarUsu() throws IOException{  
		
		File archUsu = new File("C:/Users/angel/Desktop/Ejercicios asignaturas/POO/Taller1/txts/Usuarios.txt");
		Scanner cuentoUsu = new Scanner(archUsu);
		
		int contadorUsu = 0;
		
		while (cuentoUsu.hasNextLine()) {
			cuentoUsu.nextLine();
			contadorUsu++;
		}
		cuentoUsu.close();
		usuariosList = new String[contadorUsu];
		passwordList = new String[contadorUsu];
		
		Scanner lector = new Scanner(archUsu);
		
		int i = 0;
		while (lector.hasNextLine()) {
			String linea = lector.nextLine();
			String[] partes = linea.split(";");
			
			usuariosList[i] = partes[0]; //poblando las listas
			passwordList[i] = partes[1]; 
			i++;
		
		}lector.close();
	}
	
	// // 
	
	public static void cargarReg() throws IOException{
		File archReg = new File("C:/Users/angel/Desktop/Ejercicios asignaturas/POO/Taller1/txts/Registros.txt");
		Scanner lector = new Scanner(archReg);
		
		while (lector.hasNextLine() && totalRegistros < 300) {
	        String linea = lector.nextLine();
	        String[] partes = linea.split(";");
	        
	        if (partes.length >= 4) {
	            regUser[totalRegistros] = partes[0];
	            regFecha[totalRegistros] = partes[1];
	            regHora[totalRegistros] = partes[2];
	            regActividad[totalRegistros] = partes[3];
	            totalRegistros++;
	        }
	    }
	    lector.close();	
	}
	

	public static void logIn(){ //Completadocreo
		System.out.print("Ingrese su usuario: ");
		String user = escribe.nextLine();
		int i;
		boolean eVelda = false;
		while (eVelda ==false ) {
			for( i = 0 ; i < usuariosList.length ; i++ ) {
				if(usuariosList[i] != null && usuariosList[i].equals(user)) {
					eVelda = true;
					break;
				}
			}
			if (eVelda == false) {
				System.out.println("El usuario no se encuentra en la base de datos");
				System.out.print("Ingrese el usuario nuevamente: ");
				user = escribe.nextLine();
			}
		}
		
		System.out.print("Ingrese su contraseña: ");
		String password = escribe.nextLine(); 
		boolean correctPass = false;
		while(correctPass == false) {
			for(i = 0 ; i < passwordList.length ; i++) {
				
				if(passwordList[i].equals(password) && usuariosList[i].equals(user)) {
					correctPass = true;
					break;
				}
			}
			if(correctPass == false) {
				System.out.println("Contraseña incorrecta!!");
				System.out.print("Ingrese su contraseña nuevamente: ");
				password = escribe.nextLine();
				
			}

		}
				System.out.println("Acceso concedido!!");
		
		System.out.println("Bienvenid@ "+user+"!!!!!!");
		System.out.println("");
		System.out.println("Qué deseas hacer?");
		
		
		menuUsuarios(user);
	}   
	// ** //
	
	public static void menuUsuarios(String nombreUser) {
		int opcion = 0;
		
		while (opcion != 5) {

	        System.out.println("1) Registrar Actividad");
	        System.out.println("2) Modificar Actividad");
	        System.out.println("3) Eliminar Actividad");
	        System.out.println("4) Cambiar Contraseña");
	        System.out.println("5) Salir");
	        System.out.print(">> ");
	        opcion = escribe.nextInt();
	        escribe.nextLine();
	        
	        switch(opcion){
	        
	        case 1:
	        	
	        	System.out.println();
	        	System.out.print("Ingrese la fecha de la actividad (DD/MM/AA): ");
	        	String fecha = escribe.nextLine();
	        	
	        	System.out.print("Ingrese la duración en horas: ");
	        	String horas = escribe.nextLine();        	
	        	
	        	System.out.print("Ingrese el tipo de actividad: ");
	        	String actividad = escribe.nextLine();
	        	
	        	if(totalRegistros < 300) {
	        		regUser[totalRegistros] = nombreUser;
	        		regFecha[totalRegistros] = fecha;
	        		regActividad[totalRegistros] = actividad;
	        		regHora[totalRegistros] = horas;
	        		
	        	System.out.println("Actividad registrada con éxito!!");
	        	System.out.println(" ");
	        	//Deberia agregarlos al archivo pero no se hacer eso aun :P. Seguiré haciendo el resto del codigo
	        	}
	        	
	        case 2:
	        	
	        	System.out.println("");
	        	System.out.println("Qué actividad deseas modificar?");
	        	
	        	int[] index = new int[300]; 
	        	int contador = 1;
	        	int i;
	        	for(i = 0; i < totalRegistros; i++) {
	        		if(regUser[i] != null && regUser[i].equals(nombreUser)) {
	        			System.out.println(contador+") "+ regFecha[i] +" | "+ regHora[i] + " | " + regActividad[i]);
	        			index[contador] = i;
	        			contador++;
	        		}
	        	
	        	}
	        	System.out.print(">>");
	        	int actividadSeleccionada = escribe.nextInt();
		        escribe.nextLine();	
	        	
		        System.out.println("Qué deseas modificar?");
		        System.out.println("0) Regresar");
	        }
	        
		}
	}
	
}

