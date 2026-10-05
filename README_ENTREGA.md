# Muebles de los Alpes - Laboratorio Servicios REST

## Qué contiene esta entrega

Se completó la capa REST para los EJB Mock solicitados:

- ServicioCarritoMock -> `CarroComprasService`
- ServicioCatalogoMock -> `CatalogoService`
- ServicioRegistroMock -> `RegistroService`
- ServicioSeguridadMock -> `SeguridadService`
- ServicioVendedoresMock -> `VendedoresService`

Se implementaron operaciones GET, POST, PUT y DELETE cuando la lógica del EJB lo permite. Para Seguridad, la operación natural es POST `/login/`.

También se corrigieron anotaciones EJB faltantes (`@Stateless`, `@Local`, `@Remote`) y se agregaron operaciones de actualización a catálogo, registro y vendedores para soportar PUT mediante la persistencia Mock existente.

## Entorno recomendado por la guía

- NetBeans 8.2
- GlassFish 4.1
- JDK 8 (recomendado para este proyecto antiguo)
- Maven

> El proyecto original compila con `source/target 1.6`. JDK 8 aún lo soporta. No se recomienda intentar este laboratorio con JDK 17/21 sin modernizar primero los POM.

## Forma más rápida de ejecutar

1. Abrir `mueblesdelosalpes.backend` en NetBeans.
2. Ejecutar **Clean and Build**.
3. Abrir `mueblesdelosalpes.servicios`.
4. Ejecutar **Clean and Build**.
5. Ejecutar/Deploy **solamente** `mueblesdelosalpes.servicios` en GlassFish 4.1.
6. Probar en navegador:

   `http://localhost:8080/mueblesdelosalpes.servicios/webresources/Catalogo/muebles/`

7. Importar `POSTMAN/MueblesLosAlpes_REST.postman_collection.json` en Postman.

## Si GlassFish muestra EJB Container initialization error

Desde `glassfish/bin` ejecutar:

`asadmin set server.ejb-container.property.disable-nonportable-jndi-names=true`

Luego reiniciar GlassFish y desplegar de nuevo.

## Compilación desde consola

Si `mvn` está en PATH, ejecutar `01_COMPILAR.bat`. El script instala primero el backend en el repositorio Maven local y después construye el WAR de servicios.

El WAR esperado queda en:

`mueblesdelosalpes.servicios\target\mueblesdelosalpes.servicios-1.0-SNAPSHOT.war`

## Credenciales Mock incluidas en el proyecto original

- Administrador: `admin` / `adminadmin`
- Cliente: `client` / `clientclient`

## Validación realizada sobre esta entrega

Se hizo una compilación sintáctica local de todos los fuentes Java contra stubs de las APIs `javax.ejb` y `javax.ws.rs` usando Java 8 como nivel de lenguaje. Pasó sin errores de sintaxis o tipos internos. La validación final del contenedor debe hacerse en GlassFish 4.1, que es el entorno exigido por la guía.
