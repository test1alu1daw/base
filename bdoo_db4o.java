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
 
import misc.Alumno;
import java.io.*;
import com.db4o.*;
import com.db4o.config.EmbeddedConfiguration;

public class bdoo_db4o {
	
	public static void main (String[] args) {
	  
	  // Limpiar la base de datos
	  File bd = new File("miBaseDatosOO");
	  bd.delete();
	  
	  // LLenar base de datos
	  System.out.println("Rellenar datos");
	  EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
	  ObjectContainer baseDatos = Db4oEmbedded.openFile(config, "miBaseDatosOO");
	  
	  try {
		  String[] nombres = { "Alvaro", "Maria", "Julian", "Rodrigo"};
		  int [] edades = { 26, 15, 15, 12};
		  for (int i = 0; i<4; i++) {
			Alumno alumno = new Alumno();
			alumno.cambiarNombre(nombres[i]);
			alumno.cambiarEdad(edades[i]);			  
			baseDatos.store(alumno);
		    System.out.println("Alumno <" + alumno.obtenerNombre() + "> edad " + alumno.obtenerEdad());
		  }		  
	  }
	  catch (Exception e) { 
		  e.printStackTrace(); 
	  }
	  finally {
	      baseDatos.close();
	  }
	  
	  // Volcar base de datos
	  System.out.println("\nObtener datos");
	  EmbeddedConfiguration config2 = Db4oEmbedded.newConfiguration();
	  ObjectContainer baseDatos2 = Db4oEmbedded.openFile(config2, "miBaseDatosOO");
	  
	  try {
		  Alumno alumno = new Alumno(); // Sacarlos todos
		  ObjectSet objetos = baseDatos2.queryByExample(alumno);
		  System.out.println("Recuperados " + objetos.size() + " objetos");
		  while (objetos.hasNext()) {
			  Alumno resultado = (Alumno)objetos.next();
			  System.out.println("Alumno <" + resultado.obtenerNombre() + "> edad " + resultado.obtenerEdad());
		  }
	  }
	  finally {
	      baseDatos2.close();
	  }		  	  
		  	
	  // Recuperar los que tengan 15 años
	  System.out.println("\nRecuperar los que tengan 15 años");
	  EmbeddedConfiguration config3 = Db4oEmbedded.newConfiguration();
	  ObjectContainer baseDatos3 = Db4oEmbedded.openFile(config3, "miBaseDatosOO");
	  
	  try {
		  Alumno alumno = new Alumno(); // Sacarlos todos
		  alumno.cambiarEdad(15);
		  ObjectSet objetos = baseDatos3.queryByExample(alumno);
		  System.out.println("Recuperados " + objetos.size() + " objetos");
		  while (objetos.hasNext()) {
			  Alumno resultado = (Alumno)objetos.next();
			  System.out.println("Alumno <" + resultado.obtenerNombre() + "> edad " + resultado.obtenerEdad());
		  }
	  }
	  finally {
	      baseDatos3.close();
	  }		  	    	  
	}
}

