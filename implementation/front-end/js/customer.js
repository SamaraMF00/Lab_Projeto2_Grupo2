let url = "http://localhost:8080"
var idCustomer = 0;
let user = [];

function onClickSave() {
    save(customerBody());
}

function onClickDelete() {
    if (idCustomer == 0) {
        document.getElementById("buttonConsult").click();
    }

    if (idCustomer == 0)
        alert("Sem dados para deletar.");
    else
        deleteAccount();

    init();
}

function onClickConsult() {
    cpf = document.getElementById("cpfConsult").value
    consult(cpf);
}

async function deleteAccount() {
    let urlCustomer = url + "/customer/" + idCustomer;

    await fetch(urlCustomer, {
        method: "DELETE",
        headers: {
            'host': 'localhost:8080',
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/JSON',
            "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept"
        }
    });

    alert("Dados deletados com sucesso.");
}

async function consult(cpf) {
    let urlCustomer = url + "/customer/" + cpf;

    const response = await fetch(urlCustomer, {
        method: "GET",
        headers: {
            'host': 'localhost:8080',
            'Access-Control-Allow-Origin': '*',
            "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept"
        }
    });

    let customer = await response.json();

    showCustomer(customer);
    document.getElementById("buttonClose").click();
}

async function save(obj) {
    urlCustomer = url + "/customer";
    var met = "POST";

    if (idCustomer > 0)
        met = "PUT";

    postAPI(urlCustomer, met, obj)
    alert("Cadastro registrado com sucesso!");
    init();
}

async function postAPI(url, met, body) {
    await fetch(url, {
        method: met,
        headers: {
            'host': 'localhost:8080',
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/JSON',
            "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept"
        },
        body: JSON.stringify(body)
    });
}

function customerBody() {
    return {
        id: idCustomer,
        name: String(document.getElementById("name").value),
        cpf: String(document.getElementById("cpf").value),
        rg: String(document.getElementById("rg").value),
        address: String(document.getElementById("address").value),
        employers: concatEmployers()
    }
}

function showCustomer(customer) {
    if (customer.error == "Not Found")
        alert("Sem dados para exibir.");
    else {
        idCustomer = customer.id;
        document.getElementById("name").value = customer.name;
        document.getElementById("cpf").value = customer.cpf;
        document.getElementById("rg").value = customer.rg;
        document.getElementById("address").value = customer.address;

        showEmployers(customer.employers);
    }
}

function concatEmployers() {
    let employers = "";

    for (let i = 1; i <= 3; i++) {
        let name = "employer0" + String(i);
        if (String(document.getElementById(name).value) != "") {
            if (employers != "")
                employers = employers + ";";
            employers = employers + String(document.getElementById(name).value);
        }
    }
    return employers;
}

function showEmployers(employer) {
    let employers = employer.split(";");
    let input = null;

    for (var i = 0; i < employers.length; i++) {
        let name = "#employer0" + String(i + 1);
        input = document.querySelector(name);
        input.value = employers[i];
    }
}

function init() {
    document.getElementById("name").value = '';
    document.getElementById("cpf").value = '';
    document.getElementById("rg").value = '';
    document.getElementById("address").value = '';
    document.getElementById("employer01").value = '';
    document.getElementById("employer02").value = '';
    document.getElementById("employer03").value = '';
    idCustomer = 0;
}
