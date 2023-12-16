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
    
    private static final String SQL_INSERT="INSERT INTO peliculas(Titulo, Actores, Director, Genero, Sinopsis, Duracion, Anyo, Imagen) VALUES(?,?,?,?,?,?,?,?)";
    
    private static final String SQL_UPDATE="UPDATE peliculas SET Titulo=?, Actores=?, Director=?, Genero=?, Sinopsis=?, Duracion=?, Anyo=? WHERE idPeliculas=?";
    
    private static final String SQL_DELETE="DELETE FROM peliculas WHERE idPeliculas=?";
    
    public static List<Pelicula> seleccionar(){
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
                int idPeliculas=rs.getInt(1);
                String titulo=rs.getString("Titulo");
                String actores=rs.getString("Actores");
                String director=rs.getString("Director");
                String genero=rs.getString("Genero");
                String sinopsis=rs.getString("Sinopsis");
                int duracion=rs.getInt("Duracion");
                System.out.println("Año que trae de BD "+rs.getInt("Anyo"));
                int año=rs.getInt("Anyo");
                Blob blob =rs.getBlob("Imagen");
                byte[] imagenBytes=blob.getBytes(1,(int)blob.length());
//                System.out.println("Que trae desde BD: "+titulo);
                
                //int idPelicula, String Actores, int Año, String Director, int duracion, String Genero, byte[] Imagen, String Sinopsis, String Titulo
                pelicula=new Pelicula(idPeliculas, actores, año, director, duracion, genero, imagenBytes, sinopsis, titulo);
                System.out.println("Año en objeto "+pelicula.getAnyo());
                
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
            stmt.setInt(6, pelicula.getDuracion());
            stmt.setInt(7, pelicula.getAnyo());
//            stmt.setBytes(8, pelicula.getImagen());
            
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
                int idPeliculas=rs.getInt("idPeliculas");
                String titulo=rs.getString("Titulo");
                String actores=rs.getString("Actores");
                String director=rs.getString("Director");
                String genero=rs.getString("Genero");
                String sinopsis=rs.getString("Sinopsis");
                int duracion=rs.getInt("Duracion");
                int anyo=rs.getInt("Anyo");
                Blob blob =rs.getBlob("Imagen");
                byte[] imagenBytes=blob.getBytes(1,(int)blob.length());
                System.out.println("Pretende modificar: "+titulo);
                
                
                pelicula=new Pelicula(idPeliculas, actores, anyo, director, duracion, genero, imagenBytes, sinopsis, titulo);
//                System.out.println("Año que trae de bd: "+ pelicula.getAño());
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

    public static int actualizar(Pelicula pelicula) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
//        System.out.println("Objeto A actualizar "+pelicula.getAño());
        try {
            conn = getConexion();
            
            //UPDATE peliculas SET Titulo=?, Actores=?, Director=?, Genero=?, Sinopsis=?, Duracion=?, Año=? WHERE idPeliculas=? * FROM peliculas"
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, pelicula.getTitulo());
            stmt.setString(2, pelicula.getActores());
            stmt.setString(3, pelicula.getDirector());
            stmt.setString(4, pelicula.getGenero());
            stmt.setString(5, pelicula.getSinopsis());
            stmt.setInt(6,pelicula.getDuracion());
            stmt.setInt(7, pelicula.getAnyo());
            
            stmt.setInt(8, pelicula.getIdPelicula());
            
            registros = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    }
