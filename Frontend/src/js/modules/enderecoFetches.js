// import {successModal, errorModal, progressModal} from "./modal.js";

// // CADASTRAR ENDEREÇO
// async function postEndereco () {

//     try {
//         let body = {
//             rua : document.getElementById("paciente-CpfInput").value,
//             numero : document.getElementById("paciente-dataNascimento").value,
//             bairro : document.getElementById("paciente-EmailInput").value,
//             cidade : document.getElementById("paciente-NameInput").value,
//             uf : document.getElementById("paciente-LastNameInput").value,
//             cep : 
//         }

//         let response = await fetch ('http://localhost:8080/pacientes', {
//             method : 'POST',
//             headers : {
//                 'Accept' : 'application/json',
//                 'Content-Type' : 'application/json'
//             },
//             body : JSON.stringify(body)
//         })

//         progressModal();

//         if (response.status == 200 || response.status == 201) {
//             let result = await response.json();
//             successModal();
//             return result;

//         } else {
//             errorModal();
//             return null;

//         }

//     } catch (error) {
//         console.log(error);
//     }
// }

// // CADASTRAR ENDEREÇO + NOVO
// async function postPacienteNovo() {
//     try {
//         let postResult = await postPaciente();
//         console.log(postResult);

//     } catch (err) {
//         console.log(err);
//     }

// }

// // RETORNAR ENDEREÇO POR ID
// async function getPacienteId(id) {
//     let response = fetch ('http://localhost:8080/pacientes/' + id);
//     let resolve = (await response).json();
//     return resolve;
// }

// // RETORNAR TODOS OS ENDEREÇOS CADASTRADOS
// async function getPacientes() {
//     let response = fetch ('http://localhost:8080/pacientes');
//     let resolve = (await response).json();
//     return resolve;
// }

// export {getPacientes, getPacienteId, postPaciente, postPacienteNovo, pacienteHTML};