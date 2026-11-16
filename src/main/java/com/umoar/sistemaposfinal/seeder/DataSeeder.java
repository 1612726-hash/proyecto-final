package com.umoar.sistemaposfinal.seeder;

import com.umoar.sistemaposfinal.modelos.Categoria;
import com.umoar.sistemaposfinal.modelos.Cliente;
import com.umoar.sistemaposfinal.modelos.DetalleVenta;
import com.umoar.sistemaposfinal.modelos.Producto;
import com.umoar.sistemaposfinal.modelos.Rol;
import com.umoar.sistemaposfinal.modelos.Usuario;
import com.umoar.sistemaposfinal.modelos.Venta;
import com.umoar.sistemaposfinal.repositorios.CategoriaRepository;
import com.umoar.sistemaposfinal.repositorios.ClienteRepository;
import com.umoar.sistemaposfinal.repositorios.ProductoRepository;
import com.umoar.sistemaposfinal.repositorios.RolRepository;
import com.umoar.sistemaposfinal.repositorios.UsuarioRepository;
import com.umoar.sistemaposfinal.repositorios.VentaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final ClienteRepository clienteRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;
    private final VentaRepository ventaRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        log.info("Iniciando DataSeeder...");

        // 1. Crear roles si no existen
        Rol rolAdmin = rolRepository.findByNombre("ROLE_ADMIN").orElseGet(() -> {
            log.info("Creando rol ROLE_ADMIN");
            return rolRepository.save(Rol.builder().nombre("ROLE_ADMIN").build());
        });

        rolRepository.findByNombre("ROLE_CAJERO").orElseGet(() -> {
            log.info("Creando rol ROLE_CAJERO");
            return rolRepository.save(Rol.builder().nombre("ROLE_CAJERO").build());
        });

        // 2. Crear usuario admin si no existe
        if (!usuarioRepository.existsByUsername("admin")) {
            log.info("Creando usuario inicial de prueba: admin / admin1234");
            Usuario admin = Usuario.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin1234"))
                    .activo(true)
                    .roles(Set.of(rolAdmin))
                    .build();

            usuarioRepository.save(admin);
            log.info("Usuario admin creado exitosamente.");
        } else {
            log.info("El usuario admin ya existe en la base de datos.");
        }

        // 3. Crear clientes de prueba si no existen
        if (clienteRepository.count() == 0) {
            log.info("Creando clientes iniciales de prueba...");
            List<Cliente> clientesIniciales = List.of(
                    Cliente.builder().nombre("Carlos").apellido("Melgar").documento("04829103-5").telefono("7123-4567").email("carlos.melgar@gmail.com").build(),
                    Cliente.builder().nombre("Maria").apellido("Lopez").documento("01234567-8").telefono("7890-1234").email("maria.lopez@gmail.com").build(),
                    Cliente.builder().nombre("Juan").apellido("Perez").documento("03456789-1").telefono("7654-3210").email("juan.perez@hotmail.com").build(),
                    Cliente.builder().nombre("Ana").apellido("Rodriguez").documento("05678901-2").telefono("7412-5896").email("ana.rodriguez@gmail.com").build(),
                    Cliente.builder().nombre("Consumidor").apellido("Final").documento("00000000-0").telefono("0000-0000").email("consumidorfinal@pos.com").build()
            );
            clienteRepository.saveAll(clientesIniciales);
            log.info("Clientes de prueba creados exitosamente.");
        } else {
            log.info("Ya existen clientes registrados en la base de datos.");
        }

        // 4. Crear consolas (Super Nintendo, Sega Genesis, Arcade) y juegos retro si no existen
        if (productoRepository.count() == 0) {
            log.info("Creando consolas y videojuegos iniciales...");

            Categoria snes = categoriaRepository.findAll().stream()
                    .filter(c -> "Super Nintendo".equalsIgnoreCase(c.getNombre()))
                    .findFirst()
                    .orElseGet(() -> categoriaRepository.save(Categoria.builder().nombre("Super Nintendo").descripcion("Consola clásica de 16 bits SNES").build()));

            Categoria genesis = categoriaRepository.findAll().stream()
                    .filter(c -> "Sega Genesis".equalsIgnoreCase(c.getNombre()))
                    .findFirst()
                    .orElseGet(() -> categoriaRepository.save(Categoria.builder().nombre("Sega Genesis").descripcion("Consola clásica de 16 bits Mega Drive").build()));

            Categoria arcade = categoriaRepository.findAll().stream()
                    .filter(c -> "Arcade".equalsIgnoreCase(c.getNombre()))
                    .findFirst()
                    .orElseGet(() -> categoriaRepository.save(Categoria.builder().nombre("Arcade").descripcion("Máquinas recreativas y fichas clásicas").build()));

            List<Producto> juegos = List.of(
                    // Super Nintendo
                    Producto.builder().nombre("Super Mario World").codigo("SNES-SMW").precio(45.0).existencia(12).categoria(snes).build(),
                    Producto.builder().nombre("The Legend of Zelda").codigo("SNES-ZELDA").precio(55.0).existencia(8).categoria(snes).build(),
                    Producto.builder().nombre("Super Metroid").codigo("SNES-METROID").precio(60.0).existencia(5).categoria(snes).build(),
                    Producto.builder().nombre("Donkey Kong Country").codigo("SNES-DKC").precio(40.0).existencia(15).categoria(snes).build(),
                    // Sega Genesis
                    Producto.builder().nombre("Sonic the Hedgehog 2").codigo("GEN-SONIC2").precio(35.0).existencia(14).categoria(genesis).build(),
                    Producto.builder().nombre("Streets of Rage 2").codigo("GEN-SOR2").precio(42.0).existencia(7).categoria(genesis).build(),
                    Producto.builder().nombre("Golden Axe").codigo("GEN-GAXE").precio(30.0).existencia(9).categoria(genesis).build(),
                    Producto.builder().nombre("Mortal Kombat II").codigo("GEN-MK2").precio(39.0).existencia(11).categoria(genesis).build(),
                    // Arcade
                    Producto.builder().nombre("Pac-Man Arcade").codigo("ARC-PACMAN").precio(25.0).existencia(20).categoria(arcade).build(),
                    Producto.builder().nombre("Metal Slug X").codigo("ARC-MSLUGX").precio(50.0).existencia(6).categoria(arcade).build(),
                    Producto.builder().nombre("The King of Fighters 98").codigo("ARC-KOF98").precio(48.0).existencia(10).categoria(arcade).build(),
                    Producto.builder().nombre("Space Invaders Arcade").codigo("ARC-SPACEINV").precio(22.0).existencia(18).categoria(arcade).build()
            );

            productoRepository.saveAll(juegos);
            log.info("Consolas y videojuegos creados exitosamente.");
        } else {
            log.info("Ya existen productos registrados en la base de datos.");
        }

        // 5. Crear facturas iniciales de COMPRA y RENTA si no existen
        if (ventaRepository.count() == 0) {
            log.info("Creando facturas iniciales de compra y renta...");
            List<Cliente> clientes = clienteRepository.findAll();
            List<Producto> productos = productoRepository.findAll();
            Usuario adminUser = usuarioRepository.findByUsername("admin").orElse(null);

            if (!clientes.isEmpty() && productos.size() >= 4) {
                Cliente c1 = clientes.get(0); // Carlos Melgar
                Cliente c2 = clientes.size() > 1 ? clientes.get(1) : c1; // Maria Lopez
                Cliente c3 = clientes.size() > 2 ? clientes.get(2) : c1; // Juan Perez

                Producto p1 = productos.get(0); // Super Mario World
                Producto p2 = productos.get(1); // Zelda
                Producto p3 = productos.get(4); // Sonic 2
                Producto p4 = productos.get(9); // Metal Slug X

                // Factura 1: COMPRA
                Venta fac1 = Venta.builder()
                        .numeroFactura("FAC-00001")
                        .tipo("COMPRA")
                        .cliente(c1)
                        .usuario(adminUser)
                        .fechaVenta(LocalDateTime.now().minusDays(2))
                        .total(BigDecimal.valueOf(45.00))
                        .detalles(new ArrayList<>())
                        .build();
                fac1.getDetalles().add(DetalleVenta.builder()
                        .venta(fac1)
                        .producto(p1)
                        .cantidad(1)
                        .precioUnitario(BigDecimal.valueOf(45.00))
                        .subtotal(BigDecimal.valueOf(45.00))
                        .build());

                // Factura 2: COMPRA
                Venta fac2 = Venta.builder()
                        .numeroFactura("FAC-00002")
                        .tipo("COMPRA")
                        .cliente(c2)
                        .usuario(adminUser)
                        .fechaVenta(LocalDateTime.now().minusDays(1))
                        .total(BigDecimal.valueOf(35.00))
                        .detalles(new ArrayList<>())
                        .build();
                fac2.getDetalles().add(DetalleVenta.builder()
                        .venta(fac2)
                        .producto(p3)
                        .cantidad(1)
                        .precioUnitario(BigDecimal.valueOf(35.00))
                        .subtotal(BigDecimal.valueOf(35.00))
                        .build());

                // Factura 3: RENTA (3 Días)
                Venta ren1 = Venta.builder()
                        .numeroFactura("REN-00001")
                        .tipo("RENTA")
                        .diasRenta(3)
                        .cliente(c3)
                        .usuario(adminUser)
                        .fechaVenta(LocalDateTime.now().minusHours(12))
                        .total(BigDecimal.valueOf(15.00))
                        .detalles(new ArrayList<>())
                        .build();
                ren1.getDetalles().add(DetalleVenta.builder()
                        .venta(ren1)
                        .producto(p4)
                        .cantidad(1)
                        .precioUnitario(BigDecimal.valueOf(5.00))
                        .subtotal(BigDecimal.valueOf(15.00))
                        .build());

                // Factura 4: RENTA (5 Días con recargo)
                Venta ren2 = Venta.builder()
                        .numeroFactura("REN-00002")
                        .tipo("RENTA")
                        .diasRenta(5)
                        .cliente(c1)
                        .usuario(adminUser)
                        .fechaVenta(LocalDateTime.now().minusHours(3))
                        .total(BigDecimal.valueOf(23.50))
                        .detalles(new ArrayList<>())
                        .build();
                ren2.getDetalles().add(DetalleVenta.builder()
                        .venta(ren2)
                        .producto(p2)
                        .cantidad(1)
                        .precioUnitario(BigDecimal.valueOf(5.50))
                        .subtotal(BigDecimal.valueOf(23.50))
                        .build());

                ventaRepository.saveAll(List.of(fac1, fac2, ren1, ren2));
                log.info("Facturas iniciales creadas exitosamente: 2 de compra y 2 de renta.");
            }
        } else {
            log.info("Ya existen facturas registradas en la base de datos.");
        }
    }
}
