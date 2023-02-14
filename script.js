"use strict";

let btn = document.getElementById("btn")

btn.addEventListener("click", () => {
    fetch('localhost:8081/citas/2')
  .then(response => response.json())
  .then(data => {
    // aquí puedes trabajar con los datos recibidos de la API
    console.log(data);
  })
  .catch(error => {
    // aquí puedes manejar errores que ocurran durante la petición
    console.error(error);
  });
})