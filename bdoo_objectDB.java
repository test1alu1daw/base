/*
 * bdoo_prueba.java
 * 
 * Copyright 2019 Alba <Alba@DESKTOP-26H7QSH>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */
 
import misc.AlumnoS;
import java.io.*;
import java.util.*;
import javax.persistence.*;


public class bdoo_objectDB {
	static final String BD_NAME = "miBaseDatosOOconObjectDB.odb";

	public static void main (String[] args) {
	  
	  // Limpiar la base de datos y tambien el fichero de recuperación
	  File bdFile = new File(BD_NAME);
	  bdFile.delete();
	  File bdFileRecovery = new File(BD_NAME + "$");
	  bdFileRecovery.delete();	  
	  
 	  // Abrir o crear BD si aun no existe
      // (create a new database if it doesn't exist yet):
      EntityManagerFactory emf =
            Persistence.createEntityManagerFactory(BD_NAME);
      EntityManager baseDatos = emf.createEntityManager();

	  // Rellenar
	  System.out.println("Rellenar datos");	  
	  try {
		  baseDatos.getTransaction().begin();
		  String[] nombres = { "Alvaro", "Maria", "Julian", "Rodrigo"};
		  int [] edades = { 26, 15, 15, 22};
		  for (int i = 0; i<4; i++) {
			AlumnoS alumno = new AlumnoS();
			alumno.cambiarNombre(nombres[i]);
			alumno.cambiarEdad(edades[i]);			  
			baseDatos.persist(alumno);
		    System.out.println("Alumno <" + alumno.obtenerNombre() + "> edad " + alumno.obtenerEdad());
		  }		
		  baseDatos.getTransaction().commit();
	  }
	  catch (Exception e) { 
		  e.printStackTrace(); 
	  }
	  finally {
	  }
	  
	  // Recuperar
  	  System.out.println("\nObtener datos");
	  try {
		  TypedQuery<AlumnoS> query = baseDatos.createQuery("SELECT a FROM AlumnoS a",AlumnoS.class);
		  List<AlumnoS> objetos = query.getResultList();
		  System.out.println("Recuperados " + objetos.size() + " objetos");
		  for (AlumnoS a : objetos) {
			  System.out.println("Alumno <" + a.obtenerNombre() + "> edad " + a.obtenerEdad());
		  }
	  }
	  catch (Exception e) { 
		  e.printStackTrace(); 
	  }
	  finally {
	  }		 
	  
	  // Modificar objetos
  	  System.out.println("\nIncrementar en 2 la edad de los que tengan 15");
	  try {
		  TypedQuery<AlumnoS> query = baseDatos.createQuery("SELECT a FROM AlumnoS a where edad = 15",AlumnoS.class);
		  List<AlumnoS> objetos = query.getResultList();
		  System.out.println("Recuperados " + objetos.size() + " objetos");
		  baseDatos.getTransaction().begin();
		  for (AlumnoS a : objetos) {
			  System.out.println("Alumno <" + a.obtenerNombre() + "> edad " + a.obtenerEdad());
			  a.cambiarEdad(17);
			  System.out.println("Alumno <" + a.obtenerNombre() + "> edad " + a.obtenerEdad());
		  }
		  baseDatos.getTransaction().commit();
	  }
	  catch (Exception e) { 
		  e.printStackTrace(); 
	  }
	  finally {
	  }	
	  	 	  
	  // Find the number of Point objects in the database:
      Query q1 = baseDatos.createQuery("SELECT COUNT(a) FROM AlumnoS a");
      System.out.println("\nTotal elementos: " + q1.getSingleResult());
      // Find the average X value:
      Query q2 = baseDatos.createQuery("SELECT AVG(a.edad) FROM AlumnoS a");
      System.out.println("Edad media: " + q2.getSingleResult());
        
	  // Recuperar
  	  System.out.println("\nObtener datos");
	  try {
		  TypedQuery<AlumnoS> query = baseDatos.createQuery("SELECT a FROM AlumnoS a",AlumnoS.class);
		  List<AlumnoS> objetos = query.getResultList();
		  System.out.println("Recuperados " + objetos.size() + " objetos");
		  for (AlumnoS a : objetos) {
			  System.out.println("Alumno <" + a.obtenerNombre() + "> edad " + a.obtenerEdad());
		  }
	  }
	  catch (Exception e) { 
		  e.printStackTrace(); 
	  }
	  finally {
	  }
	  
	  // Borrar un elemento
  	  System.out.println("\nBorrar el que tiene 22 años");
	  try {
		  TypedQuery<AlumnoS> query = baseDatos.createQuery("SELECT a FROM AlumnoS a where edad = 22",AlumnoS.class);
		  List<AlumnoS> objetos = query.getResultList();
		  System.out.println("Recuperados " + objetos.size() + " objetos");
		  baseDatos.getTransaction().begin();
		  for (AlumnoS a : objetos) {
			  System.out.println("Alumno <" + a.obtenerNombre() + "> edad " + a.obtenerEdad());
			  baseDatos.remove(a);
		  }
		  baseDatos.getTransaction().commit();
	  }
	  catch (Exception e) { 
		  e.printStackTrace(); 
	  }
	  finally {
	  }	
	  	  
	  // Find the number of Point objects in the database:
      System.out.println("\nTotal elementos: " + q1.getSingleResult());
      // Find the average X value:
      System.out.println("Edad media: " + q2.getSingleResult());
      
	  baseDatos.close(); 
	  emf.close();	  
    }
}

