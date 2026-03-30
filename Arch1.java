package Taller1;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Arch1 {

	public static void main(String[] args) throws IOException 
	{
		File archUsu = new File("C:/Users/angel/Desktop/Ejercicios asignaturas/POO/Taller1/txts/Usuarios.txt");
		Scanner lector = new Scanner(archUsu);
		
		int contador = 0; // usado para contar la cantidad de usuarios y generar una lista segun cuantos haya
		
		while (lector.hasNextLine()) { //Ciclo que lee todo el archivo de usuarios
			String linea = lector.nextLine();
			String[] partes = linea.split(";");		
			contador++;			
		}
		lector.close();
		
		String[] usuariosList = new String[contador]; 
		//Listas de usuarios y sus contraseñas
		String[] passwordList = new String[contador];
		
		Scanner lector2 = new Scanner(archUsu);
		int i = 0; //se va a usar para avanzar en la lista a continuacion
		while (lector2.hasNextLine()) {
			String linea2 = lector2.nextLine();
			String[] partes2 = linea2.split(";");
			
			usuariosList[i] = partes2[0];
			passwordList[i] = partes2[1]; 
			i++;
			
		}
		lector2.close();
		
		int opcion = 0;
		do {
			System.out.println("1) Menu de Usuarios"); // este es el menu 
			System.out.println("2) Menu de Analisis");
			System.out.println("3) Salir");
			System.out.print("Ingrese una opcion: ");
			
			Scanner opMenu = new Scanner(System.in);
			opcion = opMenu.nextInt();
			System.out.println("");
			switch (opcion) {
			
			case 1:
				Scanner esUser = new Scanner(System.in);
				
				System.out.print("Ingrese su usuario: ");
				String user = esUser.nextLine();
				
				boolean eVelda = false; // Booleano que se activa si el usuario está en una lista
				
				for(i=0; i < usuariosList.length;i++) {
					if (usuariosList[i].equals(user)){
						eVelda = true;
						break;
					}

				}
				if (eVelda == false) {
					System.out.println("Usuario no se encuentra en la base de datos");
				}
				
				
				
				break; //Cierra el caso
			case 2:
				System.out.println("Ingrese... algo");
				break;
			case 3:
				System.out.println("Ya bueno");
				break;
				
			default:
				System.out.println("Error");
			}
		} while(opcion != 3);

	}


}

