/*
 * hola_mundo.java
 * 
 * Copyright 2018 Alba <Alba@DESKTOP-26H7QSH>
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

import java.io.*;
import java.sql.DriverManager;
import java.sql.*;

public class bd_maria_test {
	public static Connection connection;
	
	public static void amigosDbPrint () {
			// Get current information
			Statement stmt = null;
			String query = "select * from maria_test.amigos";
			System.out.println("\n\nAMIGOS DB\n");
			try {
				stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					String nombre = rs.getString("Nombre");
					String hijo = rs.getString("Hijo");
					int edad = rs.getInt("Edad");
					String colegio = rs.getString("Colegio");
					System.out.println(nombre + " es amigo de " + hijo + " tiene " + edad + " años y va al cole de " + colegio);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	}
	
		public static void amigosDbAdd () {
			// Get current information
			Statement stmt = null;
			System.out.println("\n\nInserta un nuevo amigo\n");
			try {
				// Get new data to insert
				System.out.println("Nombre: ");
				String nombre = System.console().readLine();
				System.out.println("Es amigo de mi hijo: ");
				String hijo = System.console().readLine();
				System.out.println("Edad: ");
				String str_edad = System.console().readLine();
				int edad = Integer.parseInt(str_edad);
				System.out.println("Va al colegio de: ");
				String colegio = System.console().readLine();
				
				// Add new data to the DB
				String query = "INSERT INTO maria_test.amigos (Nombre, Hijo, Edad, Colegio) VALUES (\'" + nombre + "\',\'" + hijo + "\',\'" + edad + "\',\'" + colegio + "\')";
					
			    System.out.println("Command <"+query+">");		
				stmt = connection.createStatement();
				int result = stmt.executeUpdate(query);

			}
			catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public static void main (String[] args) {
		System.out.println("Me conecto a mi base de datos");
		try {
			// Install driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("SQL JDBC driver instalado");
	
			// Connect with DB
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/maria_test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","maria","nopass");
			System.out.println("SQL Data Base connected");
			
			// Print current DB			
			amigosDbPrint();

            // Add new friend
            amigosDbAdd();
            
			// Print current DB
			amigosDbPrint();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
}

