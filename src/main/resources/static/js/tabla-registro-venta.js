                       // Función para filtrar productos en la tabla según el nombre
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
                            var productName = row.querySelector(".productoNombre").value;
                            var precio = row.querySelector(".productoPrecio").value;
                            var productCategory = row.querySelector(".productoCategoria").value;

                            

                            // Colocar el nombre del producto en el campo de búsqueda
                            document.getElementById("productNombre").value = productName;

                            // Actualizar los demás campos con los valores correspondientes
                            var productCode = row.cells[0].innerText; // Asumiendo que el código está en la primera columna
                            var productPrice = row.cells[1].innerText; // Asumiendo que el precio está en la segunda columna

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

    // Asignar los valores a las celdas
    cell1.textContent = productCode;
    cell2.textContent = productName;
    cell3.textContent = productQuantity;
    cell4.textContent = productPrice;
    cell5.textContent = subtotal;

    // Limpiar los campos de entrada
    document.getElementById("productCode").value = "";
    document.getElementById("productNombre").value = "";
    document.getElementById("productPrice").value = "";
    document.getElementById("productStock").value = "";
    document.getElementById("quantity").value = 1;
    document.getElementById("productType").value = "";


     // Eliminar la clase 'selected' de todas las filas si el campo está vacío
     var table = document.getElementById("productoTabla");
     var rows = table.getElementsByTagName("tr");
     for (var i = 0; i < rows.length; i++) {
         rows[i].classList.remove("selected"); // Eliminar la clase de selección
     }
}


