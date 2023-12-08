/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Vale
 */
public class Pelicula {
    private int idPeliculas;
    private String Actores;
    private int Año;
    private String Director;
    private Date Duración;
    private String Genero;
    private Byte Imagen;
    private String Sinopsis;
    private String Titulo;

    public int getIdPeliculas() {
        return idPeliculas;
    }

    public void setIdPeliculas(int idPeliculas) {
        this.idPeliculas = idPeliculas;
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

    public Date getDuración() {
        return Duración;
    }

    public void setDuración(Date Duración) {
        this.Duración = Duración;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public Byte getImagen() {
        return Imagen;
    }

    public void setImagen(Byte Imagen) {
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
