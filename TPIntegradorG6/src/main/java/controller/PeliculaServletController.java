/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.PelisDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Pelicula;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Vale
 */

@WebServlet("/peliculas")
@MultipartConfig(
        location="/media/temp",
        fileSizeThreshold=1024*1024,
        maxFileSize=1024*1024*5,
        maxRequestSize=1024*1024*10
)
public class PeliculaServletController extends HttpServlet {

    private List<Pelicula> listaPeliculas = new ArrayList();
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String route = req.getParameter("action");

        switch (route) {
            case "getAll" -> {
                res.setContentType("application/json; charset=UTF-8");
                listaPeliculas = PelisDAO.seleccionar();

                for (Pelicula pelicula : listaPeliculas) {
                    byte[] imagenBytes = pelicula.getImagen();
                    String imagen64 = Base64.getEncoder().encodeToString(imagenBytes);
                    pelicula.setImagenBase64(imagen64);
                }
                mapper.writeValue(res.getWriter(), listaPeliculas);
            }

            default -> {
                System.out.println("Parametro no válido.");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String route=req.getParameter("action");
        
        switch (route) {
            case "add" ->{
                String titulo=req.getParameter("titulo");
                String director=req.getParameter("director");
                String actores=req.getParameter("actores");
                String genero=req.getParameter("genero");
                int duracion=Integer.parseInt(req.getParameter("duracion"));
                int anyo=Integer.parseInt(req.getParameter("anyo"));
                String sinopsis=req.getParameter("sinopsis");
                
                Part filePart=req.getPart("imagen");
                byte [] imagenBytes= IOUtils.toByteArray(filePart.getInputStream());
                
                Pelicula peli= new Pelicula(actores, anyo, director, duracion, genero, imagenBytes, sinopsis, titulo);
                PelisDAO.insertar(peli);
                
                res.setContentType("application/json");
                Map<String,String> response=new HashMap();
                response.put("message","¡Guadado exitosamente!");
                mapper.writeValue(res.getWriter(), response);
                        
                        }
                
                
             default -> {
                System.out.println("Parametro no válido.");
            }
        }
               

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) {

    }

}
