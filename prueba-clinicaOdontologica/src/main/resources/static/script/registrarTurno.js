document.addEventListener("DOMContentLoaded", () => {
    const registroForm = document.getElementById("registroForm");
    const mensaje = document.getElementById("mensaje");

    registroForm.addEventListener("submit", (e) => {
        e.preventDefault();
        const pacienteId = document.getElementById("pacienteId").value;
        const odontologoId = document.getElementById("odontologoId").value;
        const fechaYHora = document.getElementById("fechaYHora").value;
        

        // Enviar los datos al backend (puedes usar Fetch API o Axios para esto)
        fetch('http://localhost:8080/turnos/registrar', 
        {
            method: 'POST',
            headers: {'Content-Type': 'application/json; charset=UTF-8',},
            body: JSON.stringify({pacienteId, odontologoId, fechaYHora}),
        })
        .then((response) => response.json())
        .then((data) => {
            if (!data.ok) {
                mensaje.innerHTML = "Turno registrado exitosamente.";
            } else {
                mensaje.innerHTML = "Error al registrar el turno.";
            }
        })
        .catch((error) => {
            console.error("Error:", error);
            mensaje.innerHTML = "Error al conectar con el servidor.";
        });
    });
});