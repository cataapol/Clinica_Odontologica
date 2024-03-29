function obtenerPacientes() {
    fetch('http://localhost:8080/pacientes/buscarTodos')
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al obtener la lista de pacientes');
            }
            return response.json();
        })
        .then(pacientes => {
            console.log('Lista de pacientes:', pacientes);
            mostrarPacientes(pacientes);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function mostrarPacientes(pacientes) {
    const listaPacientes = document.getElementById('lista-pacientes');
    listaPacientes.innerHTML = '';

    pacientes.forEach(paciente => {
        const pacienteItem = document.createElement('li');
        pacienteItem.textContent = `${paciente.nombre} ${paciente.apellido} - DNI: ${paciente.dni}`;
        listaPacientes.appendChild(pacienteItem);
    });
}

document.getElementById('btnMostrarPacientes').addEventListener('click', obtenerPacientes);