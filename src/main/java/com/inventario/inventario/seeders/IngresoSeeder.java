package com.inventario.inventario.seeders;

import com.github.javafaker.Faker;
import com.inventario.inventario.enums.TipoTransaccion;
import com.inventario.inventario.model.Ingreso;
import com.inventario.inventario.model.Usuario;
import com.inventario.inventario.repository.IngresoRepository;
import com.inventario.inventario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;




@Component
public class IngresoSeeder implements CommandLineRunner {

    private final IngresoRepository ingresoRepository;
    private final UsuarioRepository usuarioRepository;
    private final Faker faker;

    @Autowired
    public IngresoSeeder(IngresoRepository ingresoRepository, UsuarioRepository usuarioRepository) {
        this.ingresoRepository = ingresoRepository;
        this.usuarioRepository = usuarioRepository;
        this.faker = new Faker();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Iniciando IngresoSeeder...");

        // Obtener el usuario con id = 1
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(1L);

        if (!usuarioOptional.isPresent()) {
            System.out.println("No se encontró el usuario con id = 1.");
            return;
        }

        Usuario usuario = usuarioOptional.get();

        // Establecer el rango de fechas para diciembre de 2024
        LocalDate startDate = LocalDate.of(2024, 11, 1);
        LocalDate endDate = LocalDate.of(2024, 11, 30);
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        // insertar ingresos
        //i = cantidad de ingresos a insertar
        for (int i = 0; i < 0; i++) {
            Ingreso ingreso = new Ingreso();
            ingreso.setUsuario(usuario);  // Asignar el usuario con id = 1
            ingreso.setMonto(BigDecimal.valueOf(faker.number().randomDouble(2, 100, 1000)));  // Monto aleatorio

            // Generar fecha aleatoria dentro del rango de diciembre de 2024
            LocalDate randomDate = startDate.plusDays(faker.number().numberBetween(0, daysBetween + 1));
            ingreso.setFecha(randomDate);  // Asignar la fecha aleatoria de diciembre de 2024

            ingreso.setConcepto(faker.commerce().productName());  // Concepto aleatorio
            ingreso.setDetalle(faker.lorem().sentence());  // Detalle aleatorio
            ingreso.setTipo(TipoTransaccion.INGRESO);  // Tipo de transacción INGRESO

            // No es necesario asignar la fecha aquí si ya se estableció en el Seeder
            ingresoRepository.save(ingreso);  // Guardar ingreso
            System.out.println("Ingreso guardado: " + ingreso.getConcepto());
        }

        System.out.println("Ingresos insertados con Faker.");
    }
}
