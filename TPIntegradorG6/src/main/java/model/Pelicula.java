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
    private int Anyo;
    private String Director;
    private int duracion;
    private String Genero;
    private byte[] Imagen;
    private String ImagenBase64;
    private String Sinopsis;
    private String Titulo;

    /**
     * Constructor Sin Imagen ni ImagenBase64
     */
    public Pelicula(int idPelicula, String Actores, int Anyo, String Director, int duracion, String Genero, String Sinopsis, String Titulo) {
        this.idPelicula = idPelicula;
        this.Actores = Actores;
        this.Anyo = Anyo;
        this.Director = Director;
        this.duracion = duracion;
        this.Genero = Genero;
        this.Sinopsis = Sinopsis;
        this.Titulo = Titulo;
    }

    
    /**
     * Constructor sin idPeliculas
     */
    public Pelicula(String Actores, int Anyo, String Director, int duracion, String Genero, byte[] Imagen, String Sinopsis, String Titulo) {
        this.Actores = Actores;
        this.Anyo = Anyo;
        this.Director = Director;
        this.duracion = duracion;
        this.Genero = Genero;
        this.Imagen = Imagen;
        this.Sinopsis = Sinopsis;
        this.Titulo = Titulo;
    }

    /**
     * Constructor sin ImagenBase64
     */
    public Pelicula(int idPelicula, String Actores, int Anyo, String Director, int duracion, String Genero, byte[] Imagen, String Sinopsis, String Titulo) {
        this.idPelicula = idPelicula;
        this.Actores = Actores;
        this.Anyo = Anyo;
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

    public int getAnyo() {
        return Anyo;
    }

    public void setAnyo(int Año) {
        this.Anyo = Año;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String Director) {
        this.Director = Director;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
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
    
    public String getImagenBase64() {
        return ImagenBase64;
    }

    public void setImagen(String imagenBase64) {
        this.ImagenBase64 = imagenBase64;
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
