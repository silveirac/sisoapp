const dentistaBody = {
    nome : "Cauê",
    sobrenome : "Silveira",
    matricula : "0001"
};

// FUNÇÃO PARA CADASTRAR DENTISTA
async function postDentista() {
    let response = await fetch ("http://localhost:8080/dentistas", {
        method : "POST",
        headers : {
            "Content-Type" : "application/json"
        },
        body : JSON.stringify(dentistaBody)
    })
    return (await response).json
}

// FUNÇÃO PARA RETORNAR DENTISTA POR ID
async function getDentistaId(id) {
    let response = fetch ('http://localhost:8080/dentistas/' + id);
    let resolve = (await response).json();
    return resolve;
}

// FUNÇÃO PARA RETORNAR TODOS OS DENTISTAS CADASTRADOS
async function getDentistas() {
    let response = fetch ('http://localhost:8080/dentistas');
    let resolve = (await response).json();
    return resolve;
}

export {getDentistas, getDentistaId, postDentista};
