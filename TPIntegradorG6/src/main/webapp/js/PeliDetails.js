document.addEventListener("DOMContentLoaded", function () {
    const queryParams = new URLSearchParams(window.location.search);

    const peliDetailId = {
        id: queryParams.get("id")
    };

    const peliDetailsContainer = document.getElementById("PeliDetails");
    const btnEliminarElement = document.getElementById("btnEliminar");
    const btnModificarElement = document.getElementById("btnModificar");
    const btnGuardarElement = document.getElementById("btnGuardar");
    const btnContainerElement = document.getElementById("btnContainer");

    let objetoPelicula = {
        id: 0,
        titulo: "",
        actores: "",
        director: "0",
        genero: "",
        duracion: 0,
        anyo: 0,
        sinopsis:"",
        imagen: ""
    };

    function loadPeli() {
        //`/app/peliculas?action=getDetails&id=${peliId}`
        fetch(`/app/peliculas?action=getById&id=${peliDetailId.id}`)
                .then(response => response.json())
                .then(data => {
                    peliDetailsContainer.innerHTML += `
                        <div class="col-md-6 text-center">
                            <div class="clearfix">
                                <img src="data:image/jpeg;base64,${data.imagenBase64}" class="my-4" style="width: 75%" alt="imagen de libro"/>
                            </div>
                        </div>
                        <div class="card-body col-md-6">
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">
                                    <h2 class="card-title">${data.titulo}</h2>
                                </li>
                                <li class="list-group-item">Actores: ${data.actores}</li> 
                                <li class="list-group-item">Director: ${data.director}</li> 
                                <li class="list-group-item">Género: ${data.genero}</li> 
                                <li class="list-group-item">Duración: ${data.duracion}</li>
                                <li class="list-group-item">Año: ${data.anyo}</li> 
                                <li class="list-group-item">Sinopsis: ${data.sinopsis}</li> 
                                
                            </ul>
                        </div>
                    `;
                    objetoPeli.id = data.id;
                    objetoPeli.titulo = data.titulo;
                    objetoPeli.director = data.director;
                    objetoPeli.actores = data.actores;
                    objetoPeli.genero = data.genero;
                    objetoPeli.duracion = data.duracion;
                    objetoPeli.anyo = data.anyo;
                    objetoPeli.sinopsis = data.sinopsis;
                    objetoPeli.imagen = data.imagen;

                });
    }
    
     btnEliminarElement.addEventListener('click',function(){
        fetch(`/app/peliculas?action=delete&id=${peliDetailId.id}`,{
            method:"DELETE"
        })
                .then(response => response.json())
                .then(data=>{
                    if(data.success){
                       // console.log("estoy dentro del if");
                        window.location.href = `/app/index.html`;
                    }
                });
    });
    
        btnModificarElement.addEventListener('click', function(){
        btnModificarElement.classList.add("d-none");
        btnEliminarElement.classList.add("d-none");
        btnGuardarElement.classList.remove("d-none");
        
        peliDetailsContainer.innerHTML = `
            <div class="col-md-6 text-center">
                <div class="clearfix">
                    <img src="data:image/jpeg;base64,${objetoPeli.imagen}" class="my-4" style="width: 75%" alt="Poster Promocional">
                </div>
            </div>
            <div class="card-body col-md-6">
                <form  class="mb-4" id = "updatePeliForm" enctype="multipart/form-data">
                    <div class="card-body">
                        <div class="row">
                            <div class="form-floating my-3">
                                <input type="text" class="form-control" name="titulo" id="titulo" placeholder="titulo" value="${objetoPeli.titulo}" required/>
                                <label for="titulo">Titulo</label>
                            </div>

                            <div class="form-floating my-3">
                                <input type="text" class="form-control" name="actores" id="actores" placeholder="actores" value="${objetoPeli.actores}" required/>
                                <label for="actores">Actores</label>
                            </div>

                            <div class="form-floating my-3">
                                <input type="text" class="form-control" name="director" id="director" placeholder="director" value="${objetoPeli.director}" required/>
                                <label for="director">Director</label>
                            </div>

                            <div class="form-floating my-3">
                                <input type="text" class="form-control" name="genero" id="genero" placeholder="genero" value="${objetoPeli.genero}" required/>
                                <label  for="genero">Género</label>
                            </div>

                            <div class="form-floating">
                                <input type}="number" class="form-control" placeholder="duración" name="duracion" id="duracion">${objetoduracion.sinopsis}</textarea>
                                <label for="duracion">Duración</label>
                            </div>

                            <div class="form-floating my-3">
                                <input type="number" class="form-control" name="anyo" id="anyo" placeholder="año" value="${objetoPeli.anyo}" required/>
                                <label  for="anyo">Año</label>
                            </div>
                            <div class="form-floating">
                                <textarea class="form-control" name="sinopsis" id="sinopsis" placeholder="Escriba la sinopsis aqui" value="${objetoPeli.sinopsis}" required/>
                                <label  for="sinopsis">Precio</label>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        `
    });
    
    btnGuardarElement.addEventListener('click' , function(e){
        e.preventDefault();
        const formulario = new FormData();
       
        formulario.append("action", "update");
        formulario.append("id", peliDetailId.id);
        formulario.append("titulo", document.getElementById("titulo").value);
        formulario.append("director", document.getElementById("director").value);
        formulario.append("actores", document.getElementById("actores").value);
        formulario.append("genero", document.getElementById("genero").value);
        formulario.append("duracion", document.getElementById("duracion").value);
        formulario.append("anyo", document.getElementById("anyo").value);
        formulario.append("sinopsis", document.getElementById("sinopsis").value);
        formulario.append("imagen", objetoPeli.imagen);
        
        fetch(`/app/peliculas`,{
           method:"POST",
           body: formulario
        })
                .then(response => {
                    if(!response.ok){
                        throw new Error(`Error en la solicitud: ${response.status}`);
                    }
                    return response.json();
                })
                .then(data =>{
                    if(data.success == "true"){
                        window.location.href = `/app/index.html`;
                    }
                    else{
                        console.error("La solicitud fue exitosa, pero la respuesta indica un error: "+data.message)
                    }
                });
    });

    loadPeli();
});



