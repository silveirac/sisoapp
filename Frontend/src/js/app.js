import { getDentistas, getDentistaId, postDentista } from "./modules/fetches.js";

//ABRIR E FECHAR MENU
document.getElementById("side-menu-dropdown-container").addEventListener("click", e => togleDropdownMenu(e.target));
document.getElementById("side-menu-item-cadastros").addEventListener("click", e => togleDropdownMenu(e.target));

function togleDropdownMenu(et) {
    
    let m = document.getElementById(et.getAttribute("data-target"));
    m.classList.toggle("hidden");
    
    if (et.classList.contains("should-rotate")) {
        et.classList.toggle("rotate-180");
    }
}

//Alteração Dentista

const CANVAS = document.getElementById("canvas");

document.getElementById("cadastrar-dentista").addEventListener("click", e => changeCanvasToDentist());


function changeCanvasToDentist () {
    clearCanvasContent();
    CANVAS.innerHTML = '<a href="http://www.oralb.com">DENTISTA</a>';
}

function skeletonLoad() {
    clearCanvasContent();
    CANVAS.innerHTML = '<div class="w-full h-full flex overflow-hidden">\
                            <span class="w-1/2">\
                                <span class="flex bg-zinc-300 opacity-10 max-w-xl h-8 rounded-lg mx-10 my-6 animate-pulse"></span>\
                                <span class="flex bg-zinc-300 opacity-10 max-w-sm h-8 rounded-lg mx-10 my-6 animate-pulse"></span>\
                                <span class="flex bg-zinc-300 opacity-10 max-w-lg h-8 rounded-lg mx-10 my-6 animate-pulse"></span>\
                                <span class="flex bg-zinc-300 opacity-10 max-w-xs h-8 rounded-lg mx-10 my-6 animate-pulse"></span>\
                                <span class="flex bg-zinc-300 opacity-10 max-w-xl h-8 rounded-lg mx-10 my-6 animate-pulse"></span>\
                                <span class="flex bg-zinc-300 opacity-10 max-w-sm h-8 rounded-lg mx-10 my-6 animate-pulse"></span>\
                                <span class="flex bg-zinc-300 opacity-10 max-w-xs h-8 rounded-lg mx-10 my-6 animate-pulse"></span>\
                                <span class="flex bg-zinc-300 opacity-0 max-w-xl h-8 rounded-lg mx-10 my-6"></span>\
                                <span class="flex bg-zinc-300 opacity-10 max-w-lg h-8 rounded-lg mx-10 my-6 animate-pulse"></span>\
                                <span class="flex bg-zinc-300 opacity-10 max-w-xs h-8 rounded-lg mx-10 my-6 animate-pulse"></span>\
                                <span class="flex bg-zinc-300 opacity-10 max-w-xs h-8 rounded-lg mx-10 my-6 animate-pulse"></span>\
                                <span class="flex bg-zinc-300 opacity-10 max-w-lg h-8 rounded-lg mx-10 my-6 animate-pulse"></span>\
                                <span class="flex bg-zinc-300 opacity-10 max-w-xl h-8 rounded-lg mx-10 my-6 animate-pulse"></span>\
                            </span>\
                            <span class="w-1/2">\
                                <span class="flex bg-zinc-300 opacity-10 max-w-xl h-8 rounded-lg mx-10 my-6 animate-pulse"></span>\
                                <span class="flex bg-zinc-300 opacity-10 max-w-sm h-8 rounded-lg mx-10 my-6 animate-pulse"></span>\
                                <span class="flex bg-zinc-300 opacity-10 max-w-lg h-8 rounded-lg mx-10 my-6 animate-pulse"></span>\
                                <span class="flex bg-zinc-300 opacity-10 max-w-xs h-8 rounded-lg mx-10 my-6 animate-pulse"></span>\
                                <span class="flex bg-zinc-300 opacity-10 max-w-xl h-8 rounded-lg mx-10 my-6 animate-pulse"></span>\
                                <span class="flex bg-zinc-300 opacity-0 max-w-xl h-8 rounded-lg mx-10 my-6"></span>\
                                <span class="flex bg-zinc-300 opacity-10 max-w-sm h-8 rounded-lg mx-10 my-6 animate-pulse"></span>\
                                <span class="flex bg-zinc-300 opacity-10 max-w-xs h-8 rounded-lg mx-10 my-6 animate-pulse"></span>\
                                <span class="flex bg-zinc-300 opacity-10 max-w-lg h-8 rounded-lg mx-10 my-6 animate-pulse"></span>\
                                <span class="flex bg-zinc-300 opacity-10 max-w-xs h-8 rounded-lg mx-10 my-6 animate-pulse"></span>\
                                <span class="flex bg-zinc-300 opacity-10 max-w-xs h-8 rounded-lg mx-10 my-6 animate-pulse"></span>\
                                <span class="flex bg-zinc-300 opacity-10 max-w-lg h-8 rounded-lg mx-10 my-6 animate-pulse"></span>\
                                <span class="flex bg-zinc-300 opacity-10 max-w-xl h-8 rounded-lg mx-10 my-6 animate-pulse"></span>\
                            </span>\
                        </div>';

}

function clearCanvasContent () {
    CANVAS.innerHTML = "";
}

skeletonLoad();
console.log(await getDentistas());
console.log(await getDentistaId(2));
console.log(await postDentista());