package com.inventario.inventario.seeders;

import com.github.javafaker.Faker;
import com.inventario.inventario.enums.TipoTransaccion;
import com.inventario.inventario.model.Ingreso;
import com.inventario.inventario.model.Usuario;
import com.inventario.inventario.repository.IngresoRepository;
import com.inventario.inventario.repository.UsuarioRepository;
import com.inventario.inventario.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
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

        // Insertar ingresos solo si ya existen en la base de datos
        if (ingresoRepository.count() == 0) {
            System.out.println("No existen ingresos en la base de datos.");
        } else {
            for (int i = 0; i < 10; i++) {
                Ingreso ingreso = new Ingreso();
                ingreso.setUsuario(usuario);  // Asignar el usuario con id = 1
                ingreso.setMonto(BigDecimal.valueOf(faker.number().randomDouble(2, 100, 1000)));  // Monto aleatorio
                ingreso.setFecha(faker.date().between(
                                java.sql.Date.valueOf("2024-08-01"),
                                java.sql.Date.valueOf("2024-12-30"))
                        .toInstant()
                        .atZone(ZoneId.systemDefault()) // Zona horaria del sistema
                        .toLocalDate());  // Convertir a LocalDate

                ingreso.setConcepto(faker.commerce().productName());  // Concepto aleatorio
                ingreso.setDetalle(faker.lorem().sentence());  // Detalle aleatorio
                ingreso.setTipo(TipoTransaccion.INGRESO);  // Tipo de transacción INGRESO

                ingresoRepository.save(ingreso);  // Guardar ingreso
                System.out.println("Ingreso guardado: " + ingreso.getConcepto());
            }
            System.out.println("Ingresos insertados con Faker.");
        }
    }
}

