/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import lombok.*;

/**
 *
 * @author Vale
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pelicula {
    private int idPelicula;
    private String Actores;
    private int Año;
    private String Director;
    private Date duracion;
    private String Genero;
    private byte[] Imagen;
    private String ImagenBase64;
    private String Sinopsis;
    private String Titulo;

    public Pelicula(String Actores, int Año, String Director, Date duracion, String Genero, byte[] Imagen, String Sinopsis, String Titulo) {
        this.Actores = Actores;
        this.Año = Año;
        this.Director = Director;
        this.duracion = duracion;
        this.Genero = Genero;
        this.Imagen = Imagen;
        this.Sinopsis = Sinopsis;
        this.Titulo = Titulo;
    }

    public Pelicula(int idPelicula, String Actores, int Año, String Director, Date duracion, String Genero, byte[] Imagen, String Sinopsis, String Titulo) {
        this.idPelicula = idPelicula;
        this.Actores = Actores;
        this.Año = Año;
        this.Director = Director;
        this.duracion = duracion;
        this.Genero = Genero;
        this.Imagen = Imagen;
        this.Sinopsis = Sinopsis;
        this.Titulo = Titulo;
    }

    
    
    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getActores() {
        return Actores;
    }

    public void setActores(String Actores) {
        this.Actores = Actores;
    }

    public int getAño() {
        return Año;
    }

    public void setAño(int Año) {
        this.Año = Año;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String Director) {
        this.Director = Director;
    }

    public Date getDuracion() {
        return duracion;
    }

    public void setDuracion(Date duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public byte[] getImagen() {
        return Imagen;
    }

    public void setImagen(byte[] Imagen) {
        this.Imagen = Imagen;
    }

    public String getSinopsis() {
        return Sinopsis;
    }

    public void setSinopsis(String Sinopsis) {
        this.Sinopsis = Sinopsis;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }
    
    
    
}
