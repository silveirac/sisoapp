import {successModal, errorModal, progressModal} from "./modal.js";

//PACIENTE VIEW
let pacienteHTML =  '<div class="px-10"> \
                    <h2 class="px-4 py-2 text-2xl font-medium text-teal-600 border-zinc-200 text-left pb-2 border-b-2 border-b-zinc-300">Cadastros > Cadastro de Paciente</h2>\
                </div> \
                <div class="px-10 py-5"> \
                <form action=""> \
                    <h3 class="px-4 py-6 text-2xl font-medium text-teal-600 border-zinc-200 text-left">Dados Pessoais</h3>\
                    <div class="flex"> \
                        <span class="flex flex-col mx-4 gap-1"> \
                            <label class="text-base font-medium text-teal-600 border-b border-zinc-200 text-left" for="nomePaciente">Nome</label> \
                            <input class="border border-zinc-300 shadow-inner rounded h-8 w-72 px-2 focus:border-teal-300 focus:outline-none focus:ring-2 focus:ring-teal-500 focus:ring-opacity-10" type="text" name="nomePaciente" id="paciente-NameInput"> \
                        </span> \
                        <span class="flex flex-col mx-4 gap-1"> \
                            <label class="text-base font-medium text-teal-600 border-b border-zinc-200 text-left" for="sobrenomePaciente">Sobrenome</label> \
                            <input class="border border-zinc-300 shadow-inner rounded h-8 w-96 px-2 focus:border-teal-300 focus:outline-none focus:ring-2 focus:ring-teal-500 focus:ring-opacity-10" type="text" name="sobrenomePaciente" id="paciente-LastNameInput"> \
                        </span> \
                    </div> \
                    <span class="flex flex-col m-4 gap-1"> \
                        <label class="text-base font-medium text-teal-600 border-b border-zinc-200 text-left" for="emailPaciente">E-mail</label> \
                        <input class="border border-zinc-300 shadow-inner rounded h-8 w-72 px-2 focus:border-teal-300 focus:outline-none focus:ring-2 focus:ring-teal-500 focus:ring-opacity-10" type="email" name="emailPaciente" id="paciente-EmailInput"> \
                    </span> \
                    <span class="flex flex-col m-4 gap-1"> \
                        <label class="text-base font-medium text-teal-600 border-b border-zinc-200 text-left" for="cpfPaciente">CPF</label> \
                        <input class="border border-zinc-300 shadow-inner rounded h-8 w-72 px-2 focus:border-teal-300 focus:outline-none focus:ring-2 focus:ring-teal-500 focus:ring-opacity-10" type="text" name="cpfPaciente" id="paciente-CpfInput"> \
                    </span> \
                    <span class="flex flex-col m-4 gap-1"> \
                        <label class="text-base font-medium text-teal-600 border-b border-zinc-200 text-left" for="dataNascimentoPaciente">Data de Nascimento</label> \
                        <input class="border border-zinc-300 shadow-inner rounded h-8 w-36 px-2 focus:border-teal-300 focus:outline-none focus:ring-2 focus:ring-teal-500 focus:ring-opacity-10" name="dataNascimentoPaciente" id="paciente-BirthdayInput" type="date" pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"> \
                    </span> \
                    <h3 class="px-4 py-6 text-2xl font-medium text-teal-600 border-zinc-200 text-left border-t-2 border-t-zinc-300">Endereço</h3>\
                    <div class="flex"> \
                        <span class="flex flex-col m-4 gap-1"> \
                            <label class="text-base font-medium text-teal-600 border-b border-zinc-200 text-left" for="cepInput">CEP</label> \
                            <input class="border border-zinc-300 shadow-inner rounded h-8 w-36 px-2 focus:border-teal-300 focus:outline-none focus:ring-2 focus:ring-teal-500 focus:ring-opacity-10" name="cepInput" id="cepInput" pattern="\d{5}-?\d{3}"> \
                        </span> \
                        <span class="flex flex-col m-4 gap-1"> \
                            <label class="text-base font-medium text-teal-600 border-b border-zinc-200 text-left" for="logradouroInput">Logradouro</label> \
                            <input class="border border-zinc-300 shadow-inner rounded h-8 px-2 focus:border-teal-300 focus:outline-none focus:ring-2 focus:ring-teal-500 focus:ring-opacity-10  w-96" name="logradouroInput" id="logradouroInput"> \
                        </span> \
                        <span class="flex flex-col m-4 gap-1"> \
                            <label class="text-base font-medium text-teal-600 border-b border-zinc-200 text-left" for="cepInput">Número</label> \
                            <input class="border border-zinc-300 shadow-inner rounded h-8 w-36 px-2 focus:border-teal-300 focus:outline-none focus:ring-2 focus:ring-teal-500 focus:ring-opacity-10" name="cepInput" id="cepInput"> \
                        </span> \
                    </div> \
                    <div class="flex"> \
                        <span class="flex flex-col m-4 gap-1"> \
                            <label class="text-base font-medium text-teal-600 border-b border-zinc-200 text-left" for="bairroInput">Bairro</label> \
                            <input class="border border-zinc-300 shadow-inner rounded h-8 px-2 focus:border-teal-300 focus:outline-none focus:ring-2 focus:ring-teal-500 focus:ring-opacity-10  w-72" name="bairroInput" id="bairroInput"> \
                        </span> \
                        <span class="flex flex-col m-4 gap-1"> \
                            <label class="text-base font-medium text-teal-600 border-b border-zinc-200 text-left" for="cidadeInput">Cidade</label> \
                            <input class="border border-zinc-300 shadow-inner rounded h-8 px-2 focus:border-teal-300 focus:outline-none focus:ring-2 focus:ring-teal-500 focus:ring-opacity-10  w-72" name="cidadeInput" id="cidadeInput"> \
                        </span> \
                        <span class="flex flex-col m-4 gap-1"> \
                            <label class="text-base font-medium text-teal-600 border-b border-zinc-200 text-left" for="cidadeInput">UF</label> \
                            <input class="border border-zinc-300 shadow-inner rounded h-8 px-2 focus:border-teal-300 focus:outline-none focus:ring-2 focus:ring-teal-500 focus:ring-opacity-10 w-36" name="cidadeInput" id="cidadeInput"> \
                        </span> \
                    </div> \
                    <h2 class="px-4 py-2 text-2xl font-medium text-teal-600 border-zinc-200 text-left pb-2 border-b-2 border-b-zinc-300"></h2>\
                    <div class="flex gap-x-4 mx-4 my-10"> \
                        <button class="bg-teal-500 px-2 py-2 text-white font-semibold text-sm rounded cursor-pointer hover:bg-teal-600 disabled:bg-slate-400 disabled:cursor-not-allowed" id="paciente-Save">SALVAR</button> \
                        <button class="bg-teal-500 px-2 py-2 text-white font-semibold text-sm rounded cursor-pointer hover:bg-teal-600 disabled:bg-slate-400 disabled:cursor-not-allowed" id="paciente-SaveNew">SALVAR / NOVO</button> \
                        <button class="bg-red-700 px-2 py-2 text-white font-semibold text-sm rounded cursor-pointer hover:bg-red-800 disabled:bg-slate-400 disabled:cursor-not-allowed" id="paciente-Clear">LIMPAR</button> \
                    </div> \
                </form> \
                </div>';

// CADASTRAR PACIENTE
async function postPaciente() {

    try {
        let body = {
            cpf : document.getElementById("paciente-CpfInput").value,
            dataNascimento : document.getElementById("paciente-dataNascimento").value,
            email : document.getElementById("paciente-EmailInput").value,
            nome : document.getElementById("paciente-NameInput").value,
            sobrenome : document.getElementById("paciente-LastNameInput").value
        }

        let response = await fetch ('http://localhost:8080/pacientes', {
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

// CADASTRAR DENTISTA + NOVO
async function postPacienteNovo() {
    try {
        let postResult = await postPaciente();
        console.log(postResult);

    } catch (err) {
        console.log(err);
    }

}

// RETORNAR PACIENTE POR ID
async function getPacienteId(id) {
    let response = fetch ('http://localhost:8080/pacientes/' + id);
    let resolve = (await response).json();
    return resolve;
}

// RETORNAR TODOS OS PACIENTES CADASTRADOS
async function getPacientes() {
    let response = fetch ('http://localhost:8080/pacientes');
    let resolve = (await response).json();
    return resolve;
}

export {getPacientes, getPacienteId, postPaciente, postPacienteNovo, pacienteHTML};