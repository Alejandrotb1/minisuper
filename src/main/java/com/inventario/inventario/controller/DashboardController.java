package com.inventario.inventario.controller;

import com.inventario.inventario.model.Ingreso;
import com.inventario.inventario.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class DashboardController {

    @Autowired
    private IngresoService ingresoService;
    @Autowired
    private VentaService ventaService;

    @Autowired
    private GastoService gastoService;
    @Autowired
    private CompraService compraService;
    @Autowired
    private ProductoService productoService;


    @GetMapping("/index")
    public String mostrarDashboard(Model model) {

//       //fecha actual
//        LocalDate fechaActual = LocalDate.now();
//        model.addAttribute("fechaActual", fechaActual);

        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String fechaFormateada = fechaActual.format(formatter);

        model.addAttribute("fechaActual", fechaFormateada);


        LocalDate inicioDelMes = LocalDate.now().withDayOfMonth(1);
        LocalDate finDelMes = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());

        BigDecimal ingresosDelMes = ingresoService.obtenerIngresosDelMes(inicioDelMes, finDelMes);
        model.addAttribute("ingresosDelMes", ingresosDelMes);
       BigDecimal ventasDelMes = ventaService.obtenerMontoTotalVentasDelMes(inicioDelMes, finDelMes);
        model.addAttribute("ventasDelMes", ventasDelMes);


        BigDecimal gastosDelMes = gastoService.obtenerGastosDelMes(inicioDelMes, finDelMes);
        model.addAttribute("gastosDelMes", gastosDelMes);
        BigDecimal comprasDelMes = compraService.obtenerTotalComprasDelMes(inicioDelMes, finDelMes);
        model.addAttribute("comprasDelMes", comprasDelMes);













        Long ventasDelDia = ventaService.contarVentasDelDia(fechaActual);
        model.addAttribute("ventasDelDia", ventasDelDia);
        BigDecimal ingresosVentasDelDia = ventaService.obtenerMontoTotalVentasDelDia(fechaActual);
        model.addAttribute("ingresosVentasDelDia", ingresosVentasDelDia);



        //stock
        long stockCritico = productoService.countProductosConStockCritico();
        long sinStock = productoService.countProductosSinStock();

        model.addAttribute("stockCritico", stockCritico);
        model.addAttribute("sinStock", sinStock);

        return "index";
    }










//    @GetMapping("/grafico-ingresos-ventas")
//    @ResponseBody
//    public Map<String, Map<String, BigDecimal>> obtenerDatosGraficoIngresosVentas() {
//        // Obtener todos los ingresos
//        List<Ingreso> ingresos = ingresoService.obtenerTodosLosIngresos();
//
//        // Agrupar los ingresos por fecha y sumar los montos
//        Map<LocalDate, BigDecimal> ingresosPorFecha = ingresos.stream()
//                .collect(Collectors.groupingBy(
//                        Ingreso::getFecha,
//                        Collectors.reducing(BigDecimal.ZERO, Ingreso::getMonto, BigDecimal::add)
//                ));
//
//        // Filtrar los ingresos que tienen una venta asociada y agrupar por fecha
//        Map<LocalDate, BigDecimal> ventasPorFecha = ingresos.stream()
//                .filter(ingreso -> ingreso.getVenta() != null)  // Solo ingresos con venta asociada
//                .collect(Collectors.groupingBy(
//                        ingreso -> ingreso.getFecha(),
//                        Collectors.reducing(BigDecimal.ZERO, ingreso -> ingreso.getMonto(), BigDecimal::add)
//                ));
//
//
//        // Combinar ingresos y ventas por fecha en un solo mapa
//        Map<String, Map<String, BigDecimal>> datosGrafico = new TreeMap<>();
//        for (LocalDate fecha : ingresosPorFecha.keySet()) {
//            BigDecimal montoIngreso = ingresosPorFecha.get(fecha);
//            BigDecimal montoVenta = ventasPorFecha.getOrDefault(fecha, BigDecimal.ZERO);
//
//            // Crear un mapa con ingresos y ventas para cada fecha
//            Map<String, BigDecimal> datosFecha = new HashMap<>();
//            datosFecha.put("ingresos", montoIngreso);
//            datosFecha.put("ventas", montoVenta);
//
//            // Guardar los datos por fecha
//            datosGrafico.put(fecha.toString(), datosFecha);
//        }
//
//        return datosGrafico;  // Retorna los datos combinados para el gráfico
//    }


    @GetMapping("/grafico-ingresos-ventas")
    @ResponseBody
    public Map<String, Map<String, BigDecimal>> obtenerDatosGraficoIngresosVentas(@RequestParam("anio") int anio, @RequestParam("mes") int mes) {
        // Obtener todos los ingresos
        List<Ingreso> ingresos = ingresoService.obtenerTodosLosIngresos();

        // Filtrar los ingresos por el mes y el año seleccionados
        Map<LocalDate, BigDecimal> ingresosPorFecha = ingresos.stream()
                .filter(ingreso -> ingreso.getFecha().getYear() == anio && ingreso.getFecha().getMonthValue() == mes)
                .collect(Collectors.groupingBy(
                        Ingreso::getFecha,
                        Collectors.reducing(BigDecimal.ZERO, Ingreso::getMonto, BigDecimal::add)
                ));

        // Filtrar los ingresos que tienen una venta asociada y agrupar por fecha
        Map<LocalDate, BigDecimal> ventasPorFecha = ingresos.stream()
                .filter(ingreso -> ingreso.getVenta() != null && ingreso.getFecha().getYear() == anio && ingreso.getFecha().getMonthValue() == mes)
                .collect(Collectors.groupingBy(
                        ingreso -> ingreso.getFecha(),
                        Collectors.reducing(BigDecimal.ZERO, ingreso -> ingreso.getMonto(), BigDecimal::add)
                ));

        // Combinar ingresos y ventas por fecha en un solo mapa
        Map<String, Map<String, BigDecimal>> datosGrafico = new TreeMap<>();
        for (LocalDate fecha : ingresosPorFecha.keySet()) {
            BigDecimal montoIngreso = ingresosPorFecha.get(fecha);
            BigDecimal montoVenta = ventasPorFecha.getOrDefault(fecha, BigDecimal.ZERO);

            // Crear un mapa con ingresos y ventas para cada fecha
            Map<String, BigDecimal> datosFecha = new HashMap<>();
            datosFecha.put("ingresos", montoIngreso);
            datosFecha.put("ventas", montoVenta);

            // Guardar los datos por fecha
            datosGrafico.put(fecha.toString(), datosFecha);
        }

        return datosGrafico;  // Retorna los datos combinados para el
    }




    }
