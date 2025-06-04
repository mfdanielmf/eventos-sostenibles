//Esperamos a que se cargue el dom
document.addEventListener("DOMContentLoaded", function () {
    //Guardamos el carrusel y los eventos
    const slide = document.querySelector(".carrusel-movimiento");
    const eventos = document.querySelectorAll(".evento");

    //Si no hay eventos salimos
    if(!slide || eventos.length === 0) {
        return;
    }

    //Inicializamos un contador para saber en qué evento estamos
    let contador = 0;

    //Aumentamos 1 al contador y movemos el carrusel. Cuando llega al final, vuelve al principio y repetimos
    function avanzarCarrusel() {
        contador= (contador + 1) % eventos.length;
        slide.style.transform = `translateX(-${contador * 100}%)`;
    }

    //Llamamos a la función de avanzar carrusel cada 3000ms (3 segundos)
    setInterval(avanzarCarrusel, 3000);
});
