import {successModal, errorModal, progressModal} from "./modal.js";

//DENTISTA VIEW
let dentistaHTML =  '<div class="px-10"> \
                    <h2 class="px-4 py-2 text-2xl font-medium text-teal-600 border-zinc-200 text-left pb-2 border-b-2 border-b-zinc-300">Cadastros > Cadastro de Dentistas</h2>\
                </div> \
                <div class="px-10 py-5"> \
                <form id="dentista-form"> \
                    <div class="flex"> \
                        <span class="flex flex-col mx-4 gap-1"> \
                            <label class="text-base font-medium text-teal-600 border-b border-zinc-200 text-left" for="nomeDentista">Nome</label> \
                            <input class="border border-zinc-300 shadow-inner rounded h-8 w-72 px-2 focus:border-teal-300 focus:outline-none focus:ring-2 focus:ring-teal-500 focus:ring-opacity-10" type="text" name="nomeDentista" id="dentista-NameInput"> \
                        </span> \
                        <span class="flex flex-col mx-4 gap-1"> \
                            <label class="text-base font-medium text-teal-600 border-b border-zinc-200 text-left" for="sobrenomeDentista">Sobrenome</label> \
                            <input class="border border-zinc-300 shadow-inner rounded h-8 w-96 px-2 focus:border-teal-300 focus:outline-none focus:ring-2 focus:ring-teal-500 focus:ring-opacity-10" type="text" name="sobrenomeDentista" id="dentista-LastNameInput"> \
                        </span> \
                    </div> \
                    <span class="flex flex-col m-4 gap-1"> \
                        <label class="text-base font-medium text-teal-600 border-b border-zinc-200 text-left" for="emailDentista">E-mail</label> \
                        <input class="border border-zinc-300 shadow-inner rounded h-8 w-72 px-2 focus:border-teal-300 focus:outline-none focus:ring-2 focus:ring-teal-500 focus:ring-opacity-10" type="email" name="emailDentista" id="dentista-EmailInput"> \
                    </span> \
                    <span class="flex flex-col m-4 gap-1"> \
                        <label class="text-base font-medium text-teal-600 border-b border-zinc-200 text-left" for="cpfDentista">CPF</label> \
                        <input class="border border-zinc-300 shadow-inner rounded h-8 w-72 px-2 focus:border-teal-300 focus:outline-none focus:ring-2 focus:ring-teal-500 focus:ring-opacity-10" type="text" name="cpfDentista" id="dentista-CpfInput"> \
                    </span> \
                    <span class="flex flex-col m-4 gap-1"> \
                        <label class="text-base font-medium text-teal-600 border-b border-zinc-200 text-left" for="registroDentista">Registro CRO</label> \
                        <input class="border border-zinc-300 shadow-inner rounded h-8 w-36 px-2 focus:border-teal-300 focus:outline-none focus:ring-2 focus:ring-teal-500 focus:ring-opacity-10" type="text"  name="registroDentista" id="dentista-RegistrationInput"> \
                    </span> \
                    <div class="flex gap-x-4 mx-4 my-10"> \
                        <button class="bg-teal-500 px-2 py-2 text-white font-semibold text-sm rounded cursor-pointer hover:bg-teal-600 disabled:bg-slate-400 disabled:cursor-not-allowed" id="dentista-Save">SALVAR</button> \
                        <button class="bg-teal-500 px-2 py-2 text-white font-semibold text-sm rounded cursor-pointer hover:bg-teal-600 disabled:bg-slate-400 disabled:cursor-not-allowed" id="dentista-SaveNew">SALVAR / NOVO</button> \
                        <button class="bg-red-700 px-2 py-2 text-white font-semibold text-sm rounded cursor-pointer hover:bg-red-800 disabled:bg-slate-400 disabled:cursor-not-allowed" id="dentista-Clear">LIMPAR</button> \
                    </div> \
                </form> \
                </div>';

// CADASTRAR DENTISTA
async function postDentista() {

    try {
        let body = {
            cpf : document.getElementById("dentista-CpfInput").value,
            cro : document.getElementById("dentista-RegistrationInput").value,
            email : document.getElementById("dentista-EmailInput").value,
            nome : document.getElementById("dentista-NameInput").value,
            sobrenome : document.getElementById("dentista-LastNameInput").value
        }

        let response = await fetch ('http://localhost:8080/dentistas', {
            method : 'POST',
            headers : {
                'Accept' : 'application/json',
                'Content-Type' : 'application/json'
            },
            body : JSON.stringify(body)
        })

        progressModal();

        if (response.status == 200 || response.status == 201) {
            let result = await response.json();
            successModal();
            return result;

        } else {
            errorModal();
            return null;

        }

    } catch (error) {
        console.log(error);
    }
}

// RETORNAR DENTISTA POR ID
async function getDentistaId(id) {
    let response = fetch ('http://localhost:8080/dentistas/' + id);
    let resolve = (await response).json();
    return resolve;
}

// RETORNAR TODOS OS DENTISTAS CADASTRADOS
async function getDentistas() {
    let response = fetch ('http://localhost:8080/dentistas');
    let resolve = (await response).json();
    return resolve;
}

export {getDentistas, getDentistaId, postDentista, dentistaHTML};