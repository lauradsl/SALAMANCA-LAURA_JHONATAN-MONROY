document.addEventListener("DOMContentLoaded", () => {
    const registroForm = document.getElementById("registroForm");
    const mensaje = document.getElementById("mensaje");

    registroForm.addEventListener("submit", (e) => 
    {
        e.preventDefault();

            const datosPacientes = 
            {
                nombre: document.getElementById("nombre").value,
                apellido: document.getElementById("apellido").value,
                dni: document.getElementById("dni").value,
                fechaIngreso: document.getElementById("fechaIngreso").value,
                domicilio:
                    {
                        calle: document.getElementById("calle").value,
                        numero: document.getElementById("numero").value,
                        localidad: document.getElementById("localidad").value,
                        provincia: document.getElementById("provincia").value,
                    }
            }
        
        // Enviar los datos al backend (puedes usar Fetch API o Axios para esto)
        fetch('http://localhost:8080/pacientes/registrar', 
        {
            method: 'POST',
            headers: {'Content-Type': 'application/json; charset=UTF-8',},
            body: JSON.stringify(datosPacientes),
        })
        .then((response) => 
        {
            if (response.ok != true) {
                alert("Alguno de los datos es incorrecto.")
            }
            return response.json();
        })
        .then((data) => 
        {
            if (data.ok = true) {
                mensaje.innerHTML = "El paciente " + data.nombre + " " + data.apellido + " se ha registrado exitosamente.";
            } 
        })
        .catch((error) => {
            console.error("Error:", error);
            mensaje.innerHTML = "Error al conectar con el servidor.";
        });
    });
});