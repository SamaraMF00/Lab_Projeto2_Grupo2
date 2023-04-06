var url = "http://localhost:8080";
var idUserDevolution = 0;
var objRequest = JSON.parse(localStorage.getItem('request'));
var diffInDays;
var multaPaga;

function init() {
    window.location.href = 'RequestConsult.html';
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
    customer = document.querySelector("#formGroupExampleInput1");
    searchRequest(customer.value);
}

async function searchRequest(customerId) {
    urlRequest = url + "/request/customer/" + customerId;

    const response = await fetch(urlRequest, {
        method: "GET",
        headers: {
            'host': 'localhost:8080',
            'Access-Control-Allow-Origin': '*'
        }
    });

    objRequest = await response.json();

    if (objRequest.id > 0) {
        localStorage.setItem('request', JSON.stringify(objRequest));
        window.location.href = 'VehicleRequest.html';
    }
    else {
        alert("Não existe requisição para este cliente.");
        init();
    }
}

function showRequest() {
    date = new Date(objRequest.inclusionDate);
    let tab = document.getElementById("request").innerHTML;

    tab += `
        <tr>
        <td scope="row">${objRequest.id}</td>
        <td>${objRequest.customer.name}</td>
        <td>${returnStringDate(date, true)}</td>
        <td>${objRequest.state}</td>
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
            'Access-Control-Allow-Origin': '*'
        }
    });

    vehicles = await response.json();

    showVehiclesRequest(vehicles);
}

function showVehiclesRequest(vehicles) {
    let list = `<h3>Veículos do pedido</h3><br><ul class="list">`;

    for (i = 0; i < vehicles.length; i++) {
        list += `<li class="list-item">` + vehicles[i].vehicle.id + ` - ` + vehicles[i].vehicle.model + `</li>`
    }

    list += `</ul>`;

    $("#table_pagination")[0].innerHTML = list;
}

showRequest();