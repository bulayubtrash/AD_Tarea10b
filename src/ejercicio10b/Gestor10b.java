package ejercicio10b;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Gestor10b {
	Scanner sc = new Scanner(System.in);
	Alumno a1 = new Alumno();
	ArrayList<Alumno> alumnos = new ArrayList<>();
	
	public void menu() {
		int opcion;
		do {
			System.out.println("Introducir opcion:\n"
					+ "1. Escribir Fichero\n"
					+ "2. Leer Ficheero\n"
					+ "0. Salir");
			opcion=sc.nextInt();
			switch (opcion) {
			case 1:
				crearAlumno();
				break;
			case 2:
				leerJSON();
				break;

			default:
				break;
			}	
		}while(opcion!=0);
	}

	public void crearAlumno() {
		// son alumnos, hago 2 para la prueba
		for (int i = 0; i < 2; i++) {

			a1 = new Alumno();
			System.out.println("Introduzca los siguientes datos:");

			System.out.println("Intoduzca el NIA");
			a1.setNia(sc.nextInt());

			sc.nextLine();

			System.out.println("Intoduzca el Nombre");
			a1.setNombre(sc.nextLine());

			System.out.println("Intoduzca el Apellidos");
			a1.setApellidos(sc.nextLine());

			System.out.println("Intoduzca el Genero");

			String genero = sc.nextLine();

			a1.setGenero(genero.charAt(0));

			System.out.println("Intoduzca el Fecha de nacimiento dd/mm/yyyy");

			String fecha = sc.nextLine();

			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

			try {
				a1.setFechaNac(formato.parse(fecha));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Intoduzca el Ciclo");
			a1.setCiclo(sc.nextLine());

			System.out.println("Intoduzca el Curso");
			a1.setCurso(sc.nextLine());

			System.out.println("Intoduzca el Grupo");
			a1.setGrupo(sc.nextLine());

			alumnos.add(a1);

			escribirJSON(alumnos);
		}

	}

	public void escribirJSON(ArrayList<Alumno>alumnos) {
		Gson gson = new Gson();

		try(FileWriter writer = new FileWriter("/Users/ayoubrehouni/Desktop/alumnos.json");) {
			
			gson.toJson(alumnos, writer);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void leerJSON() {
		Gson gson= new Gson();
		
		try(FileReader reader = new FileReader("/Users/ayoubrehouni/Desktop/alumnos.json");) {
			
            // Definir el tipo de la lista de alumnos usando TypeToken
            Type alumnosListType = new TypeToken<List<Alumno>>(){}.getType();
            // Leer el JSON y convertirlo en una lista de Alumno
            alumnos = gson.fromJson(reader, alumnosListType);
            // Inicializar alumnos si el archivo está vacío
            if (alumnos == null) {
                alumnos = new ArrayList<>();
            }
            for (Alumno alumno : alumnos) {
				System.out.println(alumno);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
