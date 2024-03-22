
document.getElementById("formulario").addEventListener('submit', (e) => {

    e.preventDefault();

    registrarPaciente();

});



function capturarDatos() {
    const nombre = document.getElementById("nombre").value;
    const apellido = document.getElementById("apellido").value;
    const dni = document.getElementById("dni").value;
    const fechaIngresoClinica = document.getElementById("fechaIngreso").value;


    const objPaciente = {
        nombre: nombre,
        apellido: apellido,
        dni: dni,
        fechaIngresoClinica: fechaIngresoClinica
    }

    return objPaciente;
}


function registrarPaciente() {

    const datosPaciente = capturarDatos();

    const configuraciones = {
        method: 'POST',
        body: JSON.stringify(datosPaciente),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    }


    // URL API
    const apiPacienteRegistro = 'http//localhost:8080/pacientes/registrar';

    fetch(apiPacienteRegistro, configuraciones)
        .then((respuesta) => respuesta.json())
        .then((data) => {
            console.log(data);
            renderizarRespuesta(data);
        });

}