document.addEventListener("DOMContentLoaded", () => {
    const registroForm = document.getElementById("registroForm");
    const mensaje = document.getElementById("mensaje");

    registroForm.addEventListener("submit", (e) => {
        e.preventDefault();
        const matricula = document.getElementById("matricula").value
        const nombre = document.getElementById("nombre").value;
        const apellido = document.getElementById("apellido").value;
        

        // Enviar los datos al backend (puedes usar Fetch API o Axios para esto)
        fetch('http://localhost:8080/odontologos/registrar', 
        {
            method: 'POST',
            headers: {'Content-Type': 'application/json; charset=UTF-8',},
            body: JSON.stringify({matricula, nombre, apellido}),
        })
        .then((response) => response.json())
        .then((data) => {
            if (!data.ok) {
                mensaje.innerHTML = "Odontólogo registrado exitosamente.";
            } else {
                mensaje.innerHTML = "Error al registrar el odontólogo.";
            }
        })
        .catch((error) => {
            console.error("Error:", error);
            mensaje.innerHTML = "Error al conectar con el servidor.";
        });
    });
});