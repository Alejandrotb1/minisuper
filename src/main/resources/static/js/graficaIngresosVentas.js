 let myLineChart = null;  // Variable global para almacenar el gráfico

                                // Función para cargar el gráfico con los datos seleccionados
                                function cargarGrafico(anio, mes) {
                                    fetch(`/grafico-ingresos-ventas?anio=${anio}&mes=${mes}`)
                                        .then(response => response.json())
                                        .then(datos => {
                                            const fechas = Object.keys(datos).sort((a, b) => new Date(a) - new Date(b));
                                            const ingresos = fechas.map(fecha => datos[fecha].ingresos);
                                            const ventas = fechas.map(fecha => datos[fecha].ventas);

                                            // Si ya existe un gráfico, destruir antes de crear uno nuevo
                                            if (myLineChart) {
                                                myLineChart.destroy();
                                            }

                                            var ctx = document.getElementById("myAreaChart");
                                            myLineChart = new Chart(ctx, {
                                                type: 'line',
                                                data: {
                                                    labels: fechas,
                                                    datasets: [
                                                        {
                                                            label: "Ingresos",
                                                            lineTension: 0.3,
                                                            backgroundColor: "rgba(2,117,216,0.2)",
                                                            borderColor: "rgba(2,117,216,1)",
                                                            pointRadius: 5,
                                                            pointBackgroundColor: "rgba(2,117,216,1)",
                                                            pointBorderColor: "rgba(255,255,255,0.8)",
                                                            pointHoverRadius: 5,
                                                            pointHoverBackgroundColor: "rgba(2,117,216,1)",
                                                            pointHitRadius: 50,
                                                            pointBorderWidth: 2,
                                                            data: ingresos,
                                                        },
                                                        {
                                                            label: "Ventas",
                                                            lineTension: 0.3,
                                                            backgroundColor: "rgba(255,99,132,0.2)",
                                                            borderColor: "rgba(255,99,132,1)",
                                                            pointRadius: 5,
                                                            pointBackgroundColor: "rgba(255,99,132,1)",
                                                            pointBorderColor: "rgba(255,255,255,0.8)",
                                                            pointHoverRadius: 5,
                                                            pointHoverBackgroundColor: "rgba(255,99,132,1)",
                                                            pointHitRadius: 50,
                                                            pointBorderWidth: 2,
                                                            data: ventas,
                                                        }
                                                    ],
                                                },
                                                options: {
                                                    scales: {
                                                        x: {
                                                            type: 'category',
                                                            labels: fechas,
                                                            ticks: {
                                                                autoSkip: true,
                                                                maxRotation: 45,
                                                                minRotation: 45
                                                            },
                                                            grid: {
                                                                display: false
                                                            },
                                                        },
                                                        y: {
                                                            ticks: {
                                                                min: 0,
                                                                max: Math.max(...ingresos.concat(ventas)) + 5000,
                                                                maxTicksLimit: 5,
                                                            },
                                                            grid: {
                                                                color: "rgba(0, 0, 0, .125)",
                                                            }
                                                        }
                                                    },
                                                    legend: {
                                                        display: true
                                                    }
                                                }
                                            });
                                        })
                                        .catch(error => console.error('Error al obtener los datos:', error));
                                }

                                // Llenar los selectores de año y mes con rangos de 2020 a 2100
                                const anioSelector = document.getElementById('anioSelector');
                                const mesSelector = document.getElementById('mesSelector');

                                // Llenar el selector de años de 2020 a 2100
                                for (let i = 2020; i <= 2100; i++) {
                                    const option = document.createElement('option');
                                    option.value = i;
                                    option.textContent = i;
                                    anioSelector.appendChild(option);
                                }

                                // Llenar el selector de meses (1-12)
                                for (let i = 1; i <= 12; i++) {
                                    const option = document.createElement('option');
                                    option.value = String(i).padStart(2, '0');
                                    option.textContent = `Mes ${i}`;
                                    mesSelector.appendChild(option);
                                }

                                // Seleccionar el mes y año actuales por defecto
                                const anioActual = new Date().getFullYear();
                                const mesActual = new Date().getMonth() + 1;

                                anioSelector.value = anioActual;
                                mesSelector.value = String(mesActual).padStart(2, '0');

                                // Cargar el gráfico por defecto
                                cargarGrafico(anioActual, mesActual);

                                // Actualizar el gráfico cuando cambian los selectores
                                anioSelector.addEventListener('change', function() {
                                    cargarGrafico(this.value, mesSelector.value);
                                });

                                mesSelector.addEventListener('change', function() {
                                    cargarGrafico(anioSelector.value, this.value);
                                });