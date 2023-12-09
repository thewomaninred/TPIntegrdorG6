/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import static data.Conexion.close;
import static data.Conexion.getConexion;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Pelicula;

/**
 *
 * @author Vale
 */
public class PelisDAO {
    private static final String SQL_SELECT="SELECT * FROM peliculas";
    
    private static final String SQL_SELECT_BY_ID="SELECT * FROM peliculas WHERE idPeliculas= ?";
    
    private static final String SQL_INSERT="INSERT INTO peliculas(Titulo, Actores, Director, Genero, Sinopsis, Duracion, Año, Imagen) VALUES(?,?,?,?,?,?,?,?)";
    
    private static final String SQL_UPDATE="UPDATE peliculas SET Titulo=?, Actores=?, Director=?, Genero=?, Sinopsis=?, Duracion=?, Año=?, Imagen=? WHERE idPeliculas=? * FROM peliculas";
    
    private static final String SQL_DELETE="DELETE FROM peliculas WHERE idPeliculas=?";
    
    private static List<Pelicula> seleccionar(){
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        Pelicula pelicula=null;
        List<Pelicula> peliculas = new ArrayList();
        try{
            conn=getConexion();
            stmt= conn.prepareStatement(SQL_SELECT);
            rs=stmt.executeQuery();
            while (rs.next()) {                
                int idPelicula=rs.getInt(1);
                String titulo=rs.getString("Titulo");
                String actores=rs.getString("Actores");
                String director=rs.getString("Director");
                String genero=rs.getString("Genero");
                String sinopsis=rs.getString("Sinopsis");
                Date duracion=rs.getDate("Duracion");
                int año=rs.getInt("Año");
                Blob blob =rs.getBlob("Imagen");
                byte[] imagenBytes=blob.getBytes(1,(int)blob.length());
                
                pelicula=new Pelicula(idPelicula, actores, año, director, duracion, genero, imagenBytes, sinopsis, titulo);
                
                peliculas.add(pelicula);
            } 
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        return peliculas;
    }
    
    public static int insertar(Pelicula pelicula){
        Connection conn=null;
        PreparedStatement stmt=null;
        int registros=0;
        try {
            conn=getConexion();
            stmt=conn.prepareStatement(SQL_INSERT);
            //Titulo, Actores, Director, Genero, Sinopsis, Duracion, Año, Imagen
            stmt.setString(1, pelicula.getTitulo());
            stmt.setString(2, pelicula.getActores());
            stmt.setString(3, pelicula.getDirector());
            stmt.setString(4, pelicula.getGenero());
            stmt.setString(5, pelicula.getSinopsis());
            stmt.setDate(6, (java.sql.Date) pelicula.getDuracion());
            stmt.setInt(7, pelicula.getAño());
            stmt.setBytes(8, pelicula.getImagen());
            
            System.out.println("Sinopsis DAO: "+pelicula.getSinopsis());
            Blob imagenBlob=conn.createBlob();
            imagenBlob.setBytes(1, pelicula.getImagen());
            stmt.setBlob(8, imagenBlob);
            
            registros=stmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }finally{
            try {
                close(conn);
                close(stmt);
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public static Pelicula seleccionarPorId(int id){
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        Pelicula pelicula=null;
        try {
            conn= getConexion();
            stmt=conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, id);
            rs=stmt.executeQuery();
            
            while (rs.next()) {                
                int idPelicula=rs.getInt("idPelicula");
                String titulo=rs.getString("Titulo");
                String actores=rs.getString("Actores");
                String director=rs.getString("Director");
                String genero=rs.getString("Genero");
                String sinopsis=rs.getString("Sinopsis");
                Date duracion=rs.getDate("Duracion");
                int año=rs.getInt("Año");
                Blob blob =rs.getBlob("Imagen");
                byte[] imagenBytes=blob.getBytes(1,(int)blob.length());
                
                pelicula=new Pelicula(idPelicula, actores, año, director, duracion, genero, imagenBytes, sinopsis, titulo);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            try {
            close(rs);
            close(stmt);
            close(conn); 
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
            
        }
        return pelicula;
    }
    
    public static int eliminar (int id){
        Connection conn=null;
        PreparedStatement stmt=null;
        int registros=0;
        try {
            conn=getConexion();
            
            stmt=conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);
            
            registros=stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
               
    }
    
    }
