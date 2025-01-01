function buscarClientePorNit() {
    var ciNit = document.getElementById('clientCiNit').value;
    console.log("Buscando por CI/NIT:", ciNit);  // Debugging

    // Si el campo no está vacío
    if (ciNit.length > 0) {
        // Realizamos la petición AJAX al backend
        fetch('http://localhost:8080/clientes/buscarClientePorNit?ciNit=' + ciNit)
            .then(response => response.json())  // Esperamos una respuesta en formato JSON
            .then(cliente => {
                console.log("Cliente encontrado:", cliente);  // Debugging
                // Si encontramos un cliente, asignamos el nombre al input
                if (cliente) {
                    document.getElementById('clientName').value = cliente.nombre;
                    document.getElementById('clientId').value = cliente.id;
                } else {
                    document.getElementById('clientId').value = '';
                    document.getElementById('clientName').value = ''; // Limpiar si no se encuentra
                }
            })
            .catch(error => {
                console.error('Error al buscar el cliente:', error);

                document.getElementById('clientId').value = '';
                document.getElementById('clientName').value = ''; // Limpiar en caso de error
            });
    } else {
        // Limpiar el campo de nombre si el campo de CI/NIT está vacío
        document.getElementById('clientId').value = '';
        document.getElementById('clientName').value = '';

    }
}                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       //  filtrar productos 
                        function filtrarTabla() {
                            var input = document.getElementById("productNombre");
                            var filter = input.value.toLowerCase();
                            var table = document.getElementById("productoTabla");
                            var rows = table.getElementsByTagName("tr");

                            var count = 0;
                            for (var i = 0; i < rows.length; i++) {
                                var cells = rows[i].getElementsByTagName("td");
                                if (cells.length > 0) {
                                    var productName = cells[0].textContent || cells[0].innerText;

                                    // Mostrar solo los productos que coincidan con la búsqueda y mostrar un máximo de 5
                                    if (productName.toLowerCase().indexOf(filter) > -1 && count < 5) {
                                        rows[i].style.display = "";
                                        count++;
                                    } else {
                                        rows[i].style.display = "none";
                                    }
                                }
                            }
                        }

                        // Función para seleccionar una fila y poner su nombre en el campo de búsqueda
                        function seleccionarFila(row) {
                            // Obtener el stock del producto
                            var productStock = row.querySelector(".productoStock").value;

                            // Solo permitir seleccionar si el stock es mayor a 0
                            if (parseInt(productStock) <= 0) {
                                return; // Si el stock es menor o igual a 0, no se hace nada
                            }

                            // Obtener los valores de producto desde los inputs hidden
                            var productoId = row.querySelector(".productoId").value;

                            var productName = row.querySelector(".productoNombre").value;
                            var precio = row.querySelector(".productoPrecio").value;
                            var productCategory = row.querySelector(".productoCategoria").value;

                            

                            // Colocar el nombre del producto en el campo de búsqueda
                            document.getElementById("productNombre").value = productName;

                            // Actualizar los demás campos con los valores correspondientes
                            var productCode = row.cells[0].innerText; // Asumiendo que el código está en la primera columna
                            var productPrice = row.cells[1].innerText; // Asumiendo que el precio está en la segunda columna


                            document.getElementById("productId").value= productoId;
                            document.getElementById("productCode").value = productCode;
                            document.getElementById("productPrice").value = precio;
                            document.getElementById("productType").value = productCategory; // Asignando categoría
                            document.getElementById("productStock").value = productStock; // Asignando stock

                            document.getElementById("quantity").value = 1;

                            // Limpiar selección anterior
                            var table = document.getElementById("productoTabla");
                            var rows = table.getElementsByTagName("tr");
                            for (var i = 0; i < rows.length; i++) {
                                rows[i].classList.remove("selected"); // Eliminar la clase de selección de todas las filas
                            }

                            // Marcar la fila seleccionada
                            row.classList.add("selected");
                        }

                        // Restablecer valores si el campo se vacía
                        document.getElementById("productNombre").addEventListener("input", function() {
                            if (this.value === "") {
                                // Restablecer los valores de los demás campos a los placeholders

                                document.getElementById("productId").value="";
                                document.getElementById("productCode").value = "";
                                document.getElementById("productType").value = "";
                                document.getElementById("productPrice").value = "";
                                document.getElementById("productStock").value = "";



                                document.getElementById("quantity").value = 1;

                                

                                // Eliminar la clase 'selected' de todas las filas si el campo está vacío
                                var table = document.getElementById("productoTabla");
                                var rows = table.getElementsByTagName("tr");
                                for (var i = 0; i < rows.length; i++) {
                                    rows[i].classList.remove("selected"); // Eliminar la clase de selección
                                }
                            }
                        });

                        // Mostrar solo los primeros 5 productos al cargar la página
                        document.addEventListener("DOMContentLoaded", function () {
                            filtrarTabla();  // Inicializa el filtrado de la tabla
                        });




document.addEventListener("DOMContentLoaded", function () {
    const quantityInput = document.getElementById("quantity");
    const productStockInput = document.getElementById("productStock");
    const productCodeInput = document.getElementById("productCode");

    // Limitar la cantidad para que no exceda el stock y no sea menor que 1
    quantityInput.addEventListener("input", function () {
        const stock = parseInt(productStockInput.value) || 0; // Obtener el stock disponible
        let quantity = parseInt(quantityInput.value) || 1; // Obtener la cantidad ingresada

        // Validar cantidad
        if (quantity > stock) {
            quantityInput.value = stock;
        } else if (quantity < 1) {
            quantityInput.value = 1;
        }
    });


});











function addProduct() {
    // Obtener los valores de los campos
    const productId = document.getElementById("productId").value;
    const productCode = document.getElementById("productCode").value;
    const productName = document.getElementById("productNombre").value;
    const productQuantity = document.getElementById("quantity").value;
    const productPrice = document.getElementById("productPrice").value;

    // Calcular el subtotal
    const subtotal = productQuantity * productPrice;

    // Crear una nueva fila en la tabla de productos agregados
    const tableVenta = document.getElementById("addedProductsTable").getElementsByTagName('tbody')[0];
    const newRow = tableVenta.insertRow();

    // Insertar celdas en la fila
    const cell1 = newRow.insertCell(0);
    const cell2 = newRow.insertCell(1);
    const cell3 = newRow.insertCell(2);
    const cell4 = newRow.insertCell(3);
    const cell5 = newRow.insertCell(4);
    const cell6 = newRow.insertCell(5);

    // Asignar los valores a las celdas
    cell1.textContent = productCode;         //cambiara code luego
    cell2.textContent = productName;
    cell3.textContent = productQuantity;
    cell4.textContent = productPrice;
    cell5.textContent = subtotal;
    cell6.textContent = productId;
    cell6.style.display = "none";            // Oculta el ID

    // Opcional: agrega el ID como un atributo de la fila
    newRow.setAttribute("data-id", productId);




    // document.getElementById("clientCiNit").value = productId;


    // limipiar
    document.getElementById("productId").value = "";

    document.getElementById("productCode").value = "";
    document.getElementById("productNombre").value = "";
    document.getElementById("productPrice").value = "";
    document.getElementById("productStock").value = "";
    document.getElementById("quantity").value = 1;
    document.getElementById("productType").value = "";

    // document.getElementById("clientName").value = document.getElementById("paymentMethod").value;


     // Eliminar la clase 'selected' de todas las filas si el campo está vacío
     var table = document.getElementById("productoTabla");
     var rows = table.getElementsByTagName("tr");
     for (var i = 0; i < rows.length; i++) {
         rows[i].classList.remove("selected"); // Eliminar la clase de selección
     }
     

     updateTotal();

     
}


// Función para calcular y mostrar el total
function updateTotal() {
    let total = 0;
    const tableVenta = document.getElementById("addedProductsTable").getElementsByTagName('tbody')[0];
    const rows = tableVenta.getElementsByTagName("tr");

    // Recorrer todas las filas y sumar los subtotales
    for (let i = 0; i < rows.length; i++) {
        const subtotalCell = rows[i].cells[4]; // Subtotal está en la columna 4
        const subtotal = parseFloat(subtotalCell.textContent);
        total += subtotal;
    }

    // Mostrar el total en el campo de entrada
    document.getElementById("totalAmount").value = total.toFixed(2);
}



//token input

var csrfToken = document.getElementById('csrfToken').value;


console.log(csrfToken);




// function submitForm(event) {


//     event.preventDefault();

//     const ingreso = {


//         metodoPago: document.getElementById("paymentMethod").value,
//         monto: parseFloat(document.getElementById("totalAmount").value)
//     };

//     const venta = {


//         clienteId: parseInt(document.getElementById("clientId").value),
//         detalleVentas: []
//     };

//     const productsTable = document.getElementById("addedProductsTable").getElementsByTagName('tbody')[0];
//     const rows = productsTable.getElementsByTagName('tr');
    
//     for (let i = 0; i < rows.length; i++) {
//         const cells = rows[i].getElementsByTagName('td');
//         venta.detalleVentas.push({


//             productoId: parseInt(cells[5].textContent),
//             cantidad: parseInt(cells[2].textContent),
//             precio_unitario: parseFloat(cells[3].textContent),
//             subtotal: parseFloat(cells[4].textContent)
//         });
//     }

//     const jsonData = { ingreso: ingreso, venta: venta };
//     const csrfToken = document.querySelector('input[name="_csrf"]').value;

//     const xhr = new XMLHttpRequest();
//     xhr.open("POST", 'http://localhost:8080/venta/guardar', true);
//     xhr.setRequestHeader('Content-Type', 'application/json');
//     xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);

//     // xhr.onreadystatechange = function() {
//     //     if (xhr.readyState === XMLHttpRequest.DONE) {
//     //         if (xhr.status === 200) {
//     //             console.log("Datos enviados correctamente");
//     //             location.reload(true);
//     //         } else {
//     //             console.error("Error al enviar los datos");
//     //         }
//     //     }
//     // };


//     // xhr.onreadystatechange = function() {
//     //     if (xhr.readyState === XMLHttpRequest.DONE) {
//     //         if (xhr.status === 200) {
//     //             console.log("Datos enviados correctamente");
//     //             window.location.href = "http://localhost:8080/venta"; // Redirigir a /venta sin pasar datos
//     //         } else {
//     //             console.error("Error al enviar los datos");
//     //         }
//     //     }
//     // };

//     xhr.send(JSON.stringify(jsonData));

//     window.location.href = "http://localhost:8080/venta"; // Redirigir a /venta sin pasar datos

// }



function submitForm(event) {
    const ingreso = {
        metodoPago: document.getElementById("paymentMethod").value,
        monto: parseFloat(document.getElementById("totalAmount").value),
    };

    const venta = {
        clienteId: parseInt(document.getElementById("clientId").value),
        detalleVentas: [],
    };

    const productsTable = document.getElementById("addedProductsTable").getElementsByTagName('tbody')[0];
    const rows = productsTable.getElementsByTagName('tr');

    for (let i = 0; i < rows.length; i++) {
        const cells = rows[i].getElementsByTagName('td');
        venta.detalleVentas.push({
            productoId: parseInt(cells[5].textContent),
            cantidad: parseInt(cells[2].textContent),
            precio_unitario: parseFloat(cells[3].textContent),
            subtotal: parseFloat(cells[4].textContent),
        });
    }

    const jsonData = { ingreso: ingreso, venta: venta };
    const csrfToken = document.querySelector('input[name="_csrf"]').value;

    const xhr = new XMLHttpRequest();
    xhr.open("POST", 'http://localhost:8080/venta/guardar', true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);

    // Manejo de la respuesta del servidor
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                console.log("Datos enviados correctamente");
                window.location.href = "http://localhost:8080/venta"; // Redirige solo si la respuesta fue exitosa
            } else {
                console.error("Error al enviar los datos");
                alert("Hubo un error al guardar los datos. Inténtalo de nuevo.");
            }
        }
    };

    xhr.send(JSON.stringify(jsonData));

    // Previene comportamiento por defecto si es necesario (ej. evitar recargar página)
    event.preventDefault();
}



