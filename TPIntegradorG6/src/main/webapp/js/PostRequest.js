
 document.addEventListener("DOMContentLoaded", function () {
    const addFilmForm = document.getElementById("addPeliculas-form");
    const parrafoAlerta = document.createElement("P");
    const tituloElement = document.getElementById("titulo");
    const directorElement = document.getElementById("director");
    const actoresElement = document.getElementById("actores");
    const generoElement = document.getElementById("genero");
    const duracionElement = document.getElementById("duracion");
    const anyoElement = document.getElementById("anyo");
    const sinopsisElement = document.getElementById("sinopsis");

    const imageElement = document.getElementById("imagen");
    const imagenPreview = document.getElementById("imagenPreview");
    const imagenContainer = document.getElementById("imagenContainer");


    addFilmForm.addEventListener("submit",function(e){
        e.preventDefault();

        const datos= new FormData();
        datos.append("action","add");
        datos.append("titulo",tituloElement.value);
        datos.append("director",directorElement.value);
        datos.append("actores",actoresElement.value);
        datos.append("genero",generoElement.value);
        datos.append("duracion",duracionElement.value);
        datos.append("anyo",anyoElement.value);
        datos.append("sinopsis",sinopsisElement.value); 
        datos.append("imagen",imageElement.files[0]);

        fetch("/app/peliculas", {
            method: "POST",
            body: datos
        })
                .then(response => response.json())
                .then(data => {
                    parrafoAlerta.textContent = data.message;
                    addFilmForm.appendChild(parrafoAlerta);

                    setTimeout(() => {
                        parrafoAlerta.remove();
                        tituloElement.value = "";
                        directorElement.value = "";
                        actoresElement.value = "";
                        generoElement.value = "";
                        duracionElement.value = "";
                        anyoElement.value = "";
                        sinopsisElement.value = "";
                        imageElement.value = "";
                        imagenContainer.classList.add("d-none");
                    }, 3000);
                });
    });




imageElement.addEventListener("change", function () {
        const selectedImage = imageElement.files[0];

        if (selectedImage) {
            const reader = new FileReader();
            reader.onload = function (e) {
                imagenPreview.src = e.target.result;
                imagenContainer.classList.remove("d-none");
            };
            reader.readAsDataURL(selectedImage);
        } else {
            imagenPreview.src = "";
        }
    });
});