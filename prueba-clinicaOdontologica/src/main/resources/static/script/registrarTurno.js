document.addEventListener("DOMContentLoaded", () => {
    const registroForm = document.getElementById("registroForm");
    const mensaje = document.getElementById("mensaje");

    registroForm.addEventListener("submit", (e) => {
        e.preventDefault();

        const datosTurno =
        {
            pacienteId: document.getElementById("pacienteId").value,
            odontologoId: document.getElementById("odontologoId").value,
            fechaYHora: document.getElementById("fechaYHora").value,
        }
        
    
        // Enviar los datos al backend (puedes usar Fetch API o Axios para esto)
        fetch('http://localhost:8080/turnos/registrar', 
        {
            method: 'POST',
            headers: {'Content-Type': 'application/json; charset=UTF-8',},
            body: JSON.stringify(datosTurno),
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
                mensaje.innerHTML = "El turno se ha registrado exitosamente.";
            } 
        })
        .catch((error) => {
            console.error("Error:", error);
            mensaje.innerHTML = "Error al conectar con el servidor.";
        });
    });
});