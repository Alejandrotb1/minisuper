// // Función para abrir el formulario de registro
//                function abrirFormularioRegistro() {
//                    document.getElementById('formularioRegistro').style.display = 'block';
//                }
//
//                // Función para cerrar el formulario de registro si no hay errores
//                function cerrarFormularioRegistro() {
//                    document.getElementById('formularioRegistro').style.display = 'none';
//                }
//
//                // Validación del formulario de registro
//                function validateForm() {
//                    var password = document.getElementById('inputPassword').value;
//                    var confirmPassword = document.getElementById('inputPasswordConfirm').value;
//
//                    // Verificar si las contraseñas coinciden
//                    if (password !== confirmPassword) {
//                        alert("Las contraseñas no coinciden. Por favor, intenta nuevamente.");
//                        return false;  // Evitar que se envíe el formulario
//                    }
//
//                    // Verificar si al menos un rol está seleccionado
//                    var rolesSelected = document.querySelectorAll('input[type="checkbox"]:checked').length;
//                    if (rolesSelected === 0) {
//                        alert("Debes seleccionar al menos un rol.");
//                        return false;  // Evitar que se envíe el formulario
//                    }
//
//                    // Si todas las validaciones son exitosas, se puede enviar el formulario
//                    return true;
//                }
//
//                // Función para controlar el comportamiento de los roles
//                function toggleRoles(adminCheckbox) {
//                    const cajeroCheckbox = document.getElementById("roleCajero");
//                    if (adminCheckbox.checked) {
//                        cajeroCheckbox.checked = true;
//                        cajeroCheckbox.disabled = true; // Deshabilitar "Cajero" si "Admin" está seleccionado
//                    } else {
//                        cajeroCheckbox.disabled = false;
//                    }
//                }


// Función para abrir el formulario de registro
function abrirFormularioRegistro() {
    document.getElementById('formularioRegistro').style.display = 'block';

    // Marcar automáticamente el checkbox de "Cajero"
    const cajeroCheckbox = document.getElementById('roleCajero');
    if (cajeroCheckbox) {
        cajeroCheckbox.checked = true;
    }
}

// Función para cerrar el formulario de registro si no hay errores
function cerrarFormularioRegistro() {
    document.getElementById('formularioRegistro').style.display = 'none';
}

// Validación del formulario de registro
function validateForm() {
    var password = document.getElementById('inputPassword').value;
    var confirmPassword = document.getElementById('inputPasswordConfirm').value;

    // Verificar si las contraseñas coinciden
    if (password !== confirmPassword) {
        alert("Las contraseñas no coinciden. Por favor, intenta nuevamente.");
        return false; // Evitar que se envíe el formulario
    }

    // Verificar si al menos un rol está seleccionado
    var rolesSelected = document.querySelectorAll('input[type="checkbox"]:checked').length;
    if (rolesSelected === 0) {
        alert("Debes seleccionar al menos un rol.");
        return false; // Evitar que se envíe el formulario
    }

    // Si todas las validaciones son exitosas, se puede enviar el formulario
    return true;
}

// Función para controlar el comportamiento de los roles
function toggleRoles(adminCheckbox) {
    const cajeroCheckbox = document.getElementById("roleCajero");
    if (adminCheckbox.checked) {
        cajeroCheckbox.checked = true;
        cajeroCheckbox.disabled = true; // Deshabilitar "Cajero" si "Admin" está seleccionado
    } else {
        cajeroCheckbox.disabled = false;
    }
}
