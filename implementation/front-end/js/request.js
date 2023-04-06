var url = "http://localhost:8080";
var tokenL = localStorage.getItem('token');
var vehicles = [];
var vehicleRequest = [];
var existRequest;

//#region User
function showUser(users) {
    let tab = "<option selected value = 0>Escolher...</option>";

    for (i = 0; i < users.length; i++) {
        tab += `<option value="${users[i].id}">${users[i].name}</option>`;
    }

    if (document.getElementById("opUser") != null)
        document.getElementById("opUser").innerHTML = tab;
}

async function getUserAPI() {
    urlUser = url + "/customer/all";

    const response = await fetch(urlUser, {
        method: "GET",
        headers: {
            'host': 'localhost:8080',
            'Access-Control-Allow-Origin': '*',
            "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept"
        }
    });

    users = await response.json();

    showUser(users);
}

function showIdUser() {
    let nameUser = document.getElementById("inputGroupSelect01");
    var idUser = document.querySelector("#formGroupExampleInput1");
    if (idUser != null)
        idUser.value = addZeroes(nameUser.value, 4);
}

async function getUserByName(name) {

    let urlUserName = url + "/customer/" + name;

    const response = await fetch(urlUserName, {
        method: "GET",
        headers: {
            'host': 'localhost:8080',
            'Access-Control-Allow-Origin': '*',
            "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept"
        }
    });

    var data = await response.json();
    console.log(data);

    if (data.id > 0) {
        var idUser = document.querySelector("#idUser");
        idUser.value = data.id;
    }
}

function showNameUser() {
    let idUser = document.getElementById('idUser');
    data = getUserById(idUser.value);
}

async function getUserById(id) {
    let urlIdUser = url + "/customer/" + id;

    const response = await fetch(urlIdUser, {
        method: "GET",
        headers: {
            'host': 'localhost:8080',
            'Access-Control-Allow-Origin': '*',
            "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept"
        }
    });

    let data = await response.json();
    console.log(data);

    if (data.name != "") {
        var nameUser = document.querySelector("#nameUser");
        nameUser.value = data.name;
    }
}
//#endregion

//#region vehicle
function showIdvehicle() {
    let descvehicle = document.getElementById("inputGroupSelect02");
    var idvehicle = document.querySelector("#formGroupExampleInput2");
    idvehicle.value = addZeroes(descvehicle.value, 4);
}

async function getvehicleByName(model) {
    let urlvehicle = url + "/vehicle/name/" + model;

    const response = await fetch(urlvehicle, {
        method: "GET",
        headers: {
            'host': 'localhost:8080',
            'Access-Control-Allow-Origin': '*',
            "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept"
        }
    });

    let data = await response.json();

    console.log(data);

    if (data.vehicleId > 0) {
        var idvehicle = document.querySelector("#formGroupExampleInput2");
        idvehicle.value = data.model;
    }
}

function showvehicleRequest(vehicle) {
    let tab = document.getElementById("vehicleRequest").innerHTML;
    tab += `
        <tr>
        <td scope="row">${vehicle.id}</td>
        <td>${vehicle.brand}</td>
        <td>${vehicle.model}</td>
        <td>${vehicle.year}</td>
        </tr>
    `;
    vehicleRequest.push(vehicle.id);
    document.getElementById("vehicleRequest").innerHTML = tab;
}

function showvehicle(vehicles) {
    let tab = "<option selected value = 0>Escolher...</option>";

    for (i = 0; i < vehicles.length; i++) {
        tab += `<option value="${vehicles[i].id}">${vehicles[i].model}</option>`;
    }

    if (document.getElementById("opvehicle") != null)
        document.getElementById("opvehicle").innerHTML = tab;
}

async function getvehicleAPI() {
    let urlvehicle = url + "/vehicle/all";

    const response = await fetch(urlvehicle, {
        method: "GET",
        headers: {
            'host': 'localhost:8080',
            'Access-Control-Allow-Origin': '*',
            "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept"
        }
    });

    vehicles = await response.json();

    showvehicle(vehicles);
}
//#endregion

//#region events User
function onchangeUser() {
    showIdUser();
}
//#endregion

//#region events vehicle
function onchangeTitVehicle() {
    showIdvehicle();
}
//#endregion

//#region onClick
async function onClickAddVehicle() {

    let idvehicle = document.getElementById('formGroupExampleInput2');
    let urlvehicle = url + "/vehicle/id/" + idvehicle.value;

    const response = await fetch(urlvehicle, {
        method: "GET",
        headers: {
            'host': 'localhost:8080',
            'Access-Control-Allow-Origin': '*',
            "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept"
        }
    });

    let data = await response.json();

    console.log(data);

    showvehicleRequest(data)
}

async function onClickSaveRequest() {

    const timeElapsed = Date.now();
    const today = new Date(timeElapsed);

    customerId = document.querySelector("#formGroupExampleInput1");
    inclusionDate = today.toISOString().split('T')[0];
    today.setDate(today.getDate() + 5);
    dueDate = today.toISOString().split('T')[0];


    requestBody =
    {
        customer: {
            id: customerId.value
        },
        inclusionDate: inclusionDate,
        dueDate: dueDate,
        state: 'Aguardando liberação'
    }
    
    saveRequest(requestBody);
}

function newVehicleRequest(request) {
    for (let vehicleId of vehicleRequest) {

        vehicleRequestBody = {
            vehicle: {
                "id": vehicleId
            },
            requestId: request.id
        };
        savevehicleRequest(vehicleRequestBody);
    };
}
//#endregion

//#region helper methods
function addZeroes(num, len) {
    var numberWithZeroes = String(num);
    var counter = numberWithZeroes.length;

    while (counter < len) {
        numberWithZeroes = "0" + numberWithZeroes;
        counter++;
    }
    return numberWithZeroes;
}

function init() {
    if (document.getElementById("vehicleRequest") != null)
        document.getElementById("vehicleRequest").innerHTML = "";

    var idUser = document.querySelector("#formGroupExampleInput1");
    if (idUser != null)
        idUser.value = addZeroes(0, 4);

    var idvehicle = document.querySelector("#formGroupExampleInput2");
    if (idvehicle != null)
        idvehicle.value = addZeroes(0, 4);

    getvehicleAPI();
    getUserAPI();
}
//#endregion

//#region Request
async function saveRequest(request) {
    let urlvehicle = url + "/request";

    const response = await fetch(urlvehicle, {
        method: "POST",
        headers: {
            'host': 'localhost:8080',
            'Access-Control-Allow-Origin': '*',
            "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept",
            'Content-Type': 'application/JSON'
        },
        body: JSON.stringify(request)
    });

    let data = await response.json();
    newVehicleRequest(data);
    alert("Pedido realizado com sucesso! Código do pedido: " + data.id);
    init();
}

async function savevehicleRequest(vehicleRequest) {
    let urlvehicle = url + "/vehicleRequest";

    const response = await fetch(urlvehicle, {
        method: "POST",
        headers: {
            'host': 'localhost:8080',
            'Access-Control-Allow-Origin': '*',
            "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept",
            'Content-Type': 'application/JSON'
        },
        body: JSON.stringify(vehicleRequest)
    });
}
//#endregion

init();
