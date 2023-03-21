
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