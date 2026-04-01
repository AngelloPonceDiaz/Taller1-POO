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
		
		menuPrincipal();
		
		escribe.close();

	}

	
	// ************************** FUNCIONES ******************************* //
	public static void menuPrincipal() {
		int opcion = 0;
		while (opcion != 3){
			
		
		System.out.println("1) Menu de usuarios");
		System.out.println("2) Menu de Analisis");
		System.out.println("3) Salir");
		System.out.print("Ingrese una opcion: ");
		opcion = escribe.nextInt();
		escribe.nextLine();
		
		switch(opcion) {
		case 1:
			logIn();
			break;
		case 2: 
			menuAnalisis();
			break;
		case 3:
			System.out.println("Gracias por usar este programa.");
			break;
			}
		}
		
	}
	



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
	        	System.out.print("Ingrese la fecha de la actividad (DD/MM/AAAA): ");
	        	String fecha = escribe.nextLine();
	        	
	        	//tests
	        	int p;
	        	String [] separaciones = fecha.split("/");
	        	int dia = Integer.valueOf(separaciones[0]);
	        	int mes = Integer.valueOf(separaciones[1]);
	        	int año = Integer.valueOf(separaciones[2]);
	        	
	        	String[] fechasRegSeparadas = regFecha[totalRegistros-1].split("/");
	        	
	        	int diaReg = Integer.valueOf(fechasRegSeparadas[0]);
	        	int mesReg = Integer.valueOf(fechasRegSeparadas[1]);
	        	int añoReg = Integer.valueOf(fechasRegSeparadas[2]);
	        	
	        	while(dia < diaReg||diaReg > 30 ||mes > 12 ||mes < mesReg|| año < añoReg || año > 2026) {
	        		System.out.println("Error");
	        		System.out.print("Ingrese una fecha válida: ");
	        		fecha = escribe.nextLine();
	        	}
	        	
	        	//tests
	        	
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
	        	break;
	        	//Deberia agregarlos al archivo pero no se hacer eso aun :P. Seguiré haciendo el resto del codigo
	        	}
	        	
	        case 2:
	        	
	        	System.out.println("");
	        	System.out.println("Qué actividad deseas modificar?");
	        	
	        	int[] index = new int[300]; 
	        	int contador = 1;
	        	int i;
	        	System.out.println("0) Regresar");
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
	        	if(actividadSeleccionada == 0) return;
	        	
	        	int posicion = index[actividadSeleccionada];
		        
		        
		        System.out.println("Qué deseas modificar?");
		        System.out.println("0) Regresar");
		        System.out.println("1) Fecha");
		        System.out.println("2) Cantidad de horas");
		        System.out.println("3) Tipo de actividad");
		        System.out.print(">>");
		        int opcionModificar = escribe.nextInt();
		        escribe.nextLine();
		        
		        switch(opcionModificar) {
		        case 0:
		        	return;
		        case 1:
		        	System.out.println("Ingrese la nueva fecha (DD/MM/AAAA): ");
		        	regFecha[posicion] = escribe.nextLine();
		        	System.out.println("Nueva fecha cambiada!");
		        	
		        	break;
		        case 2:
		        	System.out.println("Ingrese la nueva cantidad de horas: ");
		        	regHora[posicion] = escribe.nextLine();
		        	System.out.println("Nueva cantidad de horas cambiada!!");
		        	
		        	break;
		        case 3:
		        	System.out.println("Ingrese la nueva actividad: ");
		        	regActividad[posicion] = escribe.nextLine();
		        	System.out.println("Nueva actividad cambiada!!");
		        	
		        	break;
		        default:
		        	System.out.println("Opcion no valida");
		        	return;
		        }
		        break;
		        
		    // de nuevo, tengo que modificar el archivo PERO NO SE COMOOOOO AAAAAAAAAAA
	        case 3:
	        	System.out.println("");
	        	int[] index2 = new int[301];
	        	int contador2 = 1;
	        	
	        	for(i = 0; i < totalRegistros;i++) {
	        		if(regUser[i] != null && regUser[i].equals(nombreUser)) {
	        			System.out.println(contador2+") "+ regFecha[i] +" | "+ regHora[i] + " | " + regActividad[i]);
	        			index2[contador2] = i;
	        			contador2++;
	        		}
	        		
	        	}
	        	
	        	System.out.println("");
	        	System.out.println("Qué actividad deseas eliminar?: ");
	        	int seleccion = escribe.nextInt();
	        	escribe.nextLine();
	        	
	        	int posicioN = index2[seleccion];
	        	
	        	
	        	for(int a = posicioN ;a < totalRegistros-1; a++) {
	        		regUser[i] = regUser[i+1];
	        		regFecha[i] = regFecha[i+1];
	        		regActividad[i] = regActividad[i+1];
	        		regHora[i] = regHora[i+1];
	        	}
	        	
	        	regUser[totalRegistros-1] = null;
	        	regFecha[totalRegistros-1] = null;
	        	regHora[totalRegistros-1] = null;
	       		regActividad[totalRegistros-1] = null;
	       		System.out.println("Actividad eliminada!!");
	        	
	        	break;
	        case 4:
	        	System.out.println("");
	        	System.out.print("Ingrese nueva contraseña: ");
	        	String newPass = escribe.nextLine();
	        	
	        	int indice = 0;
	        	for(int j = 0;j < passwordList.length ; j++) {
	        		if(usuariosList[j].equals(nombreUser) && usuariosList[j] != null) {
	        			indice = j;
	        			break;
	        		}
	        	}
	        	passwordList[indice] = newPass;
	        	System.out.println("Contraseña actualizada!!!");
	        	
	        	break;
	        case 5:
	        	return;
	        	
	        }
	        
		}
	}
	public static void menuAnalisis() {
		int opcion = 0;
		while (opcion !=5) {
			System.out.println("1) Actividad mas realizada");
			System.out.println("2) Actividad mas realizada por cada usuario");
			System.out.println("3) Usuario con mayor procrastinacion");
			System.out.println("4) Ver todas las actividades");
			System.out.println("5) Salir");
			System.out.print(">>");
			
			opcion = escribe.nextInt();
			escribe.nextLine();
			
			switch(opcion) {
			case 1:
				int i;
				int contadorAct = 0;
				
				String actividadMas = "";
				String actBase;
				
				for(i = 0;i < totalRegistros;i++) {
					int contador = 0;
					actBase = regActividad[i];
					for(int j = 0; j < totalRegistros; j++) {
						if(actBase.equals(regActividad[j]) && regActividad[j] != null) {
							
							contador++;
						}
					}
					if(contadorAct < contador) {
						contadorAct = contador;
						actividadMas = actBase;
					}
				}
				
				System.out.println("La actividad mas realizada fue "+actividadMas+" que fue realizada "+contadorAct+" veces");
				System.out.println("");
				break;
			case 2: //es lo mas complicado creo asi q lo dejo al final :P
				i = 0;
				int contadorUser = 0;
				String userActual = "";
				for(i = 0; i < usuariosList.length;i++) {
					
					userActual = usuariosList[i];
					
					for(int j = 0; j < totalRegistros;j++) {
						if(regUser[i].equals(userActual)) {
							
						}
						
					}
				}
				
				break;
			case 3:

				String userProcrastinante = "";
				String maxUserProcrastinante = "";
				int maxProcrastinador = 0;
				
				for(i = 0; i < usuariosList.length ; i++) {
					userProcrastinante = usuariosList[i];
					int contandoConFor = 0;
					for(int j = 0;j < totalRegistros; j++) {
						if(regUser[j].equals(userProcrastinante)) {
							contandoConFor = contandoConFor + Integer.valueOf(regHora[j]);
							
						}
					}
				if(contandoConFor > maxProcrastinador) {
					maxProcrastinador = contandoConFor;
					maxUserProcrastinante = userProcrastinante;
				}
				
				}
				
				System.out.println("El usuario mas procrastinador es " + maxUserProcrastinante + " con " + maxProcrastinador + " horas perdidas");
				break;
			case 4:
				System.out.println("Aquí están todas las actividades realizadas: ");
				for(i = 0;i < totalRegistros;i++) {
					System.out.println(i+1 + ") Usuario: " + regUser[i] + " || Fecha: " + regFecha[i] + " || Horas: " + regHora[i] + " || Actividad: " + regActividad[i]);
				}
				break;
			case 5:
				return;
			default:
				System.out.println("Opcion no valida");
				
			}
				
		}
	}
	

	
}

