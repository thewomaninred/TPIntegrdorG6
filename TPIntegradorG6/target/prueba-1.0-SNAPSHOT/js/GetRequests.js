/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener("DOMContentLoaded", function(){
    const bookCards =document.getElementById("peliCard");
    const pelis =[];
    
    function loadPelisList() {
        fetch("/app/peliculas?action=getAll")
                .then(response=>response.json())
                .then(data=>{
                    data.forEach(peli=>{
                        pelis.push(peli);
                        peliCard.innerHTML +=`
                           <div class="col-md-3 mb-4 ident" data-peli-id="${peli.idPelicula}">
                           <div class="card h-100 animate-hover-card"> 
                            <img src="data:image/jpeg;base64,${peli.imagenBase64}" class="card-img-top h-50" alt="Imagen portada">
                                <div class="card-body">
                                   <h5 class="card-title"> ${peli.titulo}</h5>
                                   <p class="card-text">${peli.sinopsis}</p>
                                </div>
                            </div>
                            </div>
                        `
                    })
                    
                });
    }
    loadPelisList();
});
    

