document.addEventListener("DOMContentLoaded", () => {
    const registroForm = document.getElementById("registroForm");
    const mensaje = document.getElementById("mensaje");

    registroForm.addEventListener("submit", (e) => {
        e.preventDefault();
        const nombre = document.getElementById("nombre").value;
        const apellido = document.getElementById("apellido").value;
        const dni = document.getElementById("dni").value;
        const fechaIngreso = document.getElementById("fechaIngreso").value;
        const calle = document.getElementById("calle").value;
        const numero = document.getElementById("numero").value;
        const localidad = document.getElementById("localidad").value;
        const provincia = document.getElementById("provincia").value;

        // Enviar los datos al backend (puedes usar Fetch API o Axios para esto)
        fetch('http://localhost:8080/pacientes/registrar', 
        {
            method: 'POST',
            headers: {'Content-Type': 'application/json; charset=UTF-8',},
            body: JSON.stringify({nombre, apellido, dni, fechaIngreso, calle, numero, localidad, provincia}),
        })
        .then((response) => response.json())
        .then((data) => {
            if (!data.ok) {
                mensaje.innerHTML = "El paciente " + data.nombre + " " + data.apellido + " se ha registrado exitosamente."
            } else {
                mensaje.innerHTML = "Error al registrar el paciente.";
            }
        })
        .catch((error) => {
            console.error("Error:", error);
            mensaje.innerHTML = "Error al conectar con el servidor.";
        });
    });
});