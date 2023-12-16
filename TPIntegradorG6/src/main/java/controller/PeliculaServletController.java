
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
            
            case "getDetails"->{
                String peliId=req.getParameter("id");
//                System.out.println("Id de pelicula que  llega: "+peliId);
                Pelicula peliDetails=PelisDAO.seleccionarPorId(Integer.parseInt(peliId));
                res.setContentType("application/json");
                mapper.writeValue(res.getWriter(),peliDetails);
            }
            
            case "getById"->{
                
                int id = Integer.parseInt(req.getParameter("id"));
                
                res.setContentType("application/json");
                Pelicula peliDetails = PelisDAO.seleccionarPorId(id);
                
                byte[] imagenBytes = peliDetails.getImagen();
                String imagenBase64 = Base64.getEncoder().encodeToString(imagenBytes);
                peliDetails.setImagenBase64(imagenBase64);
                
                mapper.writeValue(res.getWriter(), peliDetails);
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
            
            case "update"->{
                
                try{
                    int id= Integer.parseInt(req.getParameter("id"));
                    String titulo = req.getParameter("titulo");
                    String director = req.getParameter("director");
                    String actores = req.getParameter("actores");
                    String genero = req.getParameter("genero");
                    int duracion = Integer.parseInt(req.getParameter("duracion"));
                    int anyo = Integer.parseInt(req.getParameter("anyo"));
                    String sinopsis = req.getParameter("sinopsis");
                    

                    Part filePart = req.getPart("imagen");
                    byte[] imageBytes = IOUtils.toByteArray(filePart.getInputStream());
                    
                    //int idPelicula, String Actores, int Año, String Director, int duracion, String Genero, byte[] Imagen, String Sinopsis, String Titulo
                    Pelicula pelicula = new Pelicula(id, actores, anyo, director, duracion, genero, imageBytes, sinopsis, titulo);
//                    System.out.println("Controller "+ pelicula.getAño());    
                    PelisDAO.actualizar(pelicula);

                    res.setContentType("application/json");
                    res.setCharacterEncoding("UTF-8");

                    Map <String, String> response = new HashMap<>();
                    response.put("success", "true");
                    mapper.writeValue(res.getWriter(), response);
                }catch(Exception e){
                    res.setContentType("application/json");
                    res.setCharacterEncoding("UTF-8");
                    
                    Map <String, String> responseFail = new HashMap<>();
                    responseFail.put("success", "false");
                    responseFail.put("message", e.getMessage());
                    mapper.writeValue(res.getWriter(), responseFail);
                }
                
            }
                
                
             default -> {
                System.out.println("Parametro no válido.");
            }
        }
               

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String route = req.getParameter("action");
        System.out.println("route = " + route);
        
        switch(route){
            case "delete"->{
                try{
                    int id = Integer.parseInt(req.getParameter("id"));
                    System.out.println("id:" + id);
                    
                    int result = PelisDAO.eliminar(id);
                    res.setContentType("application/json");
                    res.setCharacterEncoding("UTF-8");
                    res.getWriter().write("{\"success\": true}");
                }catch(Exception e){
                    res.setContentType("application/json");
                    res.setCharacterEncoding("UTF-8");
                    res.getWriter().write("{\"success\": false, \"message\": \"Error al borrar el registro.\"}");
                }
            }
            
            default->{
                System.out.println("error en parametro.");
            }
        }

    }

}
