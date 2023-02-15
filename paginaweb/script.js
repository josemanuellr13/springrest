"use strict";

let btn = document.getElementById("btn")


btn.addEventListener("click", () => {
  let valor = document.getElementById("input").value
    fetch('http://localhost:8081/citas/' + valor )
  .then(response => response.json())
  .then(data => {
    console.log(data);
  })
  .catch(error => {
    console.error(error);
  });
})