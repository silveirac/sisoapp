import { getDentistas, getDentistaId, postDentista, dentistaHTML } from "./modules/dentistaFetches.js";
import { getPacientes, getPacienteId, postPaciente, postPacienteNovo, pacienteHTML } from "./modules/pacienteFetches.js";
import { skeletonHTML } from "./modules/skeleton.js";

//ABRIR E FECHAR MENU
document.getElementById("side-menu-dropdown-container").addEventListener("click", e => togleDropdownMenu(e.target));
document.getElementById("side-menu-item-cadastros").addEventListener("click", e => togleDropdownMenu(e.target));
document.getElementById("side-menu-item-consultas").addEventListener("click", e => togleDropdownMenu(e.target));

function togleDropdownMenu(et) {
    
    let m = document.getElementById(et.getAttribute("data-target"));
    m.classList.toggle("hidden");
    
    if (et.classList.contains("should-rotate")) {
        et.classList.toggle("rotate-180");
    }
}

//CANVAS DOM CAPTURE
const CANVAS = document.getElementById("canvas");

// FIXED ELEMENTS LISTENERS DISTRIBUTION
let elementsList = [
    {id : "cadastrar-dentista", action : "click", method : () => changeCanvas(dentistaHTML)},
    {id : "dentista-form", action : "submit", method : (event) => event.preventDefault()},
    {id : "dentista-Save", action : "click", method : () => postDentista()},
    {id : "dentista-SaveNew", action : "click", method : () => {postDentista(); clearInputs("f")}},
    {id : "dentista-Clear", action : "click", method : () => clearInputs()},
    {id : "cadastrar-paciente", action : "click", method : () => changeCanvas(pacienteHTML)},
]

// LISTENERS DISTRIBUTION
function listenerDistribution() {
    elementsList.forEach(e => {

        let elemento = document.getElementById(e.id);

        if (elemento != null){
            elemento.addEventListener(e.action, (event) => {
                event.preventDefault()
                e.method()
            }) 
        }
    });
}

// CLEAR INPUTS IN CANVAS
function clearInputs(confirmValue = "") {
    let inputs = Array.from(document.querySelectorAll("input"));
    if (confirmValue == "f" || confirmValue == "F") {
        inputs.forEach(e => {
            e.value = "";        
        })
    } else {
        confirmValue = confirm("Deseja limpar todos os campos?")
        if (confirmValue) {
            inputs.forEach(e => {
                e.value = "";        
            })
        }
    }
}


// CHANGE CANVAS CONTENT
function changeCanvas (htmlContent) {
    clearCanvasContent();
    CANVAS.innerHTML = skeletonHTML;
    setTimeout (() => {
        clearCanvasContent();
        CANVAS.innerHTML = htmlContent;
        setTimeout(listenerDistribution(), 300);
    }, 800)
}

function clearCanvasContent () {
    CANVAS.innerHTML = "";
}

listenerDistribution();