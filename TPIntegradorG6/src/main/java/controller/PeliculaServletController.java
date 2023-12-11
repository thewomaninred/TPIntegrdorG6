/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.PelisDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import model.Pelicula;

/**
 *
 * @author Vale
 */

@WebServlet("/peliculas")
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
    protected void doPost(HttpServletRequest req, HttpServletResponse res) {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) {

    }

}
