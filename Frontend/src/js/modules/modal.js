// ALERT AREA DOM
const alertArea = document.getElementById("alert-area");

// SUCCESS MODAL
const success = '<span class="flex justify-center items-center text-md font-semibold text-white w-72 h-24 bg-teal-500 bg-opacity-70 shadow-sm z-0 duration-500" id="modal-Success">Registro gravado com sucesso!</span>';

function successModal () {
    
    if (document.getElementById("modal-Success") == null) {

        setTimeout(() => {
            alertArea.innerHTML += success;
            let sModal = document.getElementById("modal-Success");

            setTimeout(() => {
                alertArea.removeChild(sModal);
            }, 2400);

        }, 600);
    }
}


// ERROR MODAL
const error = '<span class="flex justify-center items-center text-md font-semibold text-white w-72 h-24 bg-red-500 bg-opacity-70 shadow-sm z-0 duration-500" id="modal-Error">Erro ao gravar registro</span>';

function errorModal () {

    if (document.getElementById("modal-Error") == null) {

        setTimeout(() => {
            alertArea.innerHTML += error;
            let eModal = document.getElementById("modal-Error");

            setTimeout(() => {
                alertArea.removeChild(eModal);
            }, 2400);

        }, 600);
    }
}


// progress MODAL
const progress = '<span class="flex justify-center items-center text-md font-semibold text-white w-72 h-24 bg-zinc-400 bg-opacity-70 shadow-sm z-0 duration-500" id="modal-Progress">Gravando registro ...</span>';

function progressModal () {
    if (document.getElementById("modal-Progress") == null) {
        document.getElementById("alert-area").innerHTML += progress;
        
        setTimeout(() => {
            let pModal = document.getElementById("modal-Progress");
            alertArea.removeChild(pModal);

        }, 2400);
    }
}


export {successModal, errorModal, progressModal};