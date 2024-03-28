function capturarDatos() {

    const nombre = document.getElementById("nombre").value;
    const apellido = document.getElementById("apellido").value;
    const dni = document.getElementById("dni").value;
    const fechaIngresoClinica = document.getElementById("fechaIngreso").value;
    const nombreCalle = document.getElementById("calle").value;
    const numeroDomicilio = document.getElementById("numero").value;
    const nombreLocalidad = document.getElementById("localidad").value;
    const nombreProvincia = document.getElementById("provincia").value;

    console.log("Nombre:", nombre);
    console.log("Apellido:", apellido);
    console.log("DNI:", dni);
    console.log("Fecha de ingreso:", fechaIngresoClinica);
    console.log("Calle:", nombreCalle);
    console.log("Número:", numeroDomicilio);
    console.log("Localidad:", nombreLocalidad);
    console.log("Provincia:", nombreProvincia);


    const objPaciente = {
        nombre: nombre,
        apellido: apellido,
        dni: dni,
        fechaIngresoClinica: fechaIngresoClinica,
        domicilio: {
         calle: nombreCalle,
         numero: numeroDomicilio,
         localidad: nombreLocalidad,
         provincia: nombreProvincia
        }
    }

    return objPaciente;
}

function registrarPaciente() {
    const datosPaciente = capturarDatos();

    console.log("Datos del paciente:", datosPaciente);

    const configuraciones = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(datosPaciente)
    }

    const apiPacienteRegistro = 'http://localhost:8080/pacientes/registrar';

    fetch(apiPacienteRegistro, configuraciones)
        .then((respuesta) => respuesta.json())
        .then((data) => {
            console.log(data);
        })
}


document.getElementById("formulario").addEventListener('submit', (e) => {
    e.preventDefault();
    registrarPaciente();
});
