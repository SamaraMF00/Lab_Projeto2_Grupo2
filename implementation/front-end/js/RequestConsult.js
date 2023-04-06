var url = "http://localhost:7070";
var tokenD = localStorage.getItem('token');
var idUserDevolution = 0;
var objRequest = JSON.parse(localStorage.getItem('request'));
var diffInDays;
var multaPaga;

function init() {
    window.location.href = 'devolucao.html';
}

function addZeroes(num, len) {
    var numberWithZeroes = String(num);
    var counter = numberWithZeroes.length;

    while (counter < len) {
        numberWithZeroes = "0" + numberWithZeroes;
        counter++;
    }
    return numberWithZeroes;
}

function onClickSearchRequest() {
    readerId = document.querySelector("#formGroupExampleInput1");
    searchRequest(readerId.value);
}

async function searchRequest(readerId) {
    urlRequest = url + "/request/user/" + readerId;

    const response = await fetch(urlRequest, {
        method: "GET",
        headers: {
            'host': 'localhost:8080',
            'Access-Control-Allow-Origin': '*',
            'authorization': 'Bearer ' + tokenD
        }
    });

    objRequest = await response.json();

    if (objRequest.id > 0) {
        localStorage.setItem('request', JSON.stringify(objRequest));
        window.location.href = 'devolucaoVehicles.html';
    }
    else {
        alert("Não existe requisição para este leitor.");
        init();
    }
}

function showRequest() {
    let tab = document.getElementById("request").innerHTML;
    date1 = new Date(objRequest.inclusionDate);
    date2 = new Date(objRequest.dueDate);

    const diffInMs = date1 - date2;
    diffInDays = diffInMs / (1000 * 60 * 60 * 24);

    if (diffInDays < 0) diffInDays = 0;

    tab += `
        <tr>
        <td scope="row">${objRequest.id}</td>
        <td>${objRequest.reader.name}</td>
        <td>${objRequest.operator.name}</td>
        <td>${returnStringDate(date1, true)}</td>
        <td>${returnStringDate(date2, true)}</td>
        <td>${diffInDays}</td>
        </tr>
    `;

    document.getElementById("request").innerHTML = tab;

    if (objRequest.id > 0)
        listVehicles(objRequest.id);
}

function returnStringDate(date, ddMMyyyy) {
    var data = date,
        dia = (data.getDate()).toString(),
        diaF = (dia.length == 1) ? '0' + dia : dia,
        mes = (data.getMonth() + 1).toString(), //+1 pois no getMonth Janeiro começa com zero.
        mesF = (mes.length == 1) ? '0' + mes : mes,
        anoF = data.getFullYear();

    if (ddMMyyyy)
        return diaF + "/" + mesF + "/" + anoF;
    else
        return anoF + "-" + mesF + "-" + diaF;
}

async function listVehicles(id) {
    urlRequest = url + "/vehicleRequest/requestId/" + id;

    const response = await fetch(urlRequest, {
        method: "GET",
        headers: {
            'host': 'localhost:8080',
            'Access-Control-Allow-Origin': '*',
            'authorization': 'Bearer ' + tokenD
        }
    });

    Vehicles = await response.json();

    showVehiclesRequest(Vehicles);
}

function showVehiclesRequest(Vehicles) {
    let list = `<h3>Vehicles a serem devolvidos</h3><br><ul class="list-group">`;

    for (i = 0; i < Vehicles.length; i++) {
        list += `<li class="list-group-item">` + Vehicles[i].Vehicle.VehicleId + ` - ` + Vehicles[i].Vehicle.modelVehicle + `</li>`
    }

    list += `</ul>`;

    $("#table_pagination")[0].innerHTML = list;
}

function onClickSaveDevolution() {

    if (diffInDays > 0 && localStorage.getItem('multaPaga') != "Sim") {
        var msg = confirm("A devolução deste empréstimo está atrasada a " + diffInDays +
            " dias. Uma multa de 2 reais ao dia foi aplicada. Deseja registrar o pagamento?")
        if (msg == true) {
            registerFine();
        }
        else
            saveDevolution();
    }
    else
        saveDevolution();
}

async function saveDevolution() {
    const timeElapsed = Date.now();
    const today = new Date(timeElapsed);

    inclusionDate = today.toISOString().split('T')[0];

    devolutionBody =
    {
        requestId: objRequest.id,
        returnDate: inclusionDate
    }

    urlRequest = url + "/devolution";

    const response = await fetch(urlRequest, {
        method: "POST",
        headers: {
            'host': 'localhost:8080',
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/JSON',
            'authorization': 'Bearer ' + tokenD
        },
        body: JSON.stringify(devolutionBody)
    });

    init();
}

function registerFine() {
    window.location.href = 'multa.html';
}

showRequest();