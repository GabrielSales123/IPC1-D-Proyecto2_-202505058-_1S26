# GameZone Pro - Manual de Usuario

## 1. Introducción

GameZone Pro es una aplicación de escritorio desarrollada en Java que simula una plataforma de entretenimiento digital.

El sistema permite a los usuarios gestionar videojuegos, cartas coleccionables, participar en torneos y acceder a un sistema de recompensas basado en experiencia (XP), niveles y logros.

La aplicación funciona completamente mediante interfaz gráfica (Java Swing) y no requiere consola.

---

## 2. Requisitos del sistema

- Java JDK 8 o superior
- Sistema operativo compatible con Java (Windows, Linux o MacOS)
- No requiere base de datos
- Uso de archivos de texto para persistencia de datos
- Ejecución mediante archivo .JAR o clase principal del proyecto

---

## 3. Inicio del sistema

Al ejecutar la aplicación, se muestra la ventana principal del sistema.

Desde este punto el usuario puede acceder a todos los módulos disponibles mediante el menú principal.

Opciones del sistema:

- Tienda de Videojuegos
- Álbum de Cartas
- Torneos
- Recompensas
- Reportes
- Datos del Usuario
- Salir

---

## 4. Tienda de Videojuegos

Este módulo permite explorar y comprar videojuegos.

### 4.1 Funcionalidades

- Visualización de catálogo de juegos
- Búsqueda por nombre o código
- Filtros por género y plataforma
- Agregar juegos al carrito
- Validación de stock disponible
- Confirmación de compra
- Generación de historial de compras

### 4.2 Uso del carrito

- El usuario puede agregar juegos al carrito.
- Puede eliminar productos antes de comprar.
- Al confirmar la compra:
  - Se descuenta el stock.
  - Se registra la compra.
  - Se limpia el carrito.

---

## 5. Álbum de Cartas Coleccionables

Este módulo permite gestionar un álbum de cartas digitales.

### 5.1 Funcionalidades

- Visualización de una cuadrícula de cartas
- Agregar cartas al álbum desde la tienda
- Intercambiar cartas entre posiciones
- Búsqueda de cartas por nombre, tipo o rareza
- Visualización de estadísticas de cada carta

### 5.2 Estructura del álbum

El álbum está organizado como una matriz de nodos enlazados (malla ortogonal), donde cada celda puede contener una carta o estar vacía.

---

## 6. Torneos

Este módulo permite la inscripción y compra de tickets para torneos.

### 6.1 Funcionalidades

- Visualización de torneos disponibles
- Inscripción a torneos
- Cola de espera de usuarios
- Venta de tickets mediante taquillas
- Procesamiento concurrente

### 6.2 Funcionamiento

- Los usuarios ingresan a una cola de espera.
- Las taquillas procesan la cola de forma simultánea.
- Cada ticket vendido se descuenta del total disponible.
- El sistema simula tiempo de atención.

---

## 7. Sistema de Recompensas

El sistema de recompensas permite medir el progreso del usuario.

### 7.1 XP (Experiencia)

El usuario gana experiencia por acciones como:

- Comprar videojuegos
- Participar en torneos
- Completar actividades del sistema

### 7.2 Niveles

- Nivel 1: Aprendiz
- Nivel 2: Jugador
- Nivel 3: Veterano
- Nivel 4: Maestro
- Nivel 5: Leyenda

Cada nivel se desbloquea automáticamente al alcanzar cierta cantidad de XP.

---

## 8. Logros

El sistema incluye logros desbloqueables.

### Ejemplos:

- Primera compra
- Coleccionista de cartas
- Participación en torneos
- Jugador activo
- Nivel máximo alcanzado

Los logros se muestran en pantalla y se desbloquean automáticamente al cumplir condiciones.

---

## 9. Reportes

El sistema permite generar reportes en formato HTML.

### Tipos de reportes:

- Inventario de videojuegos
- Historial de compras
- Estado del álbum
- Información de torneos

Los reportes se abren automáticamente en el navegador del sistema.

---

## 10. Datos del usuario

Esta sección muestra la información del usuario actual:

- Nombre
- XP acumulado
- Nivel actual
- Logros desbloqueados

---

## 11. Navegación del sistema

- Todas las pantallas cuentan con botones de regreso al menú principal.
- La navegación es completamente visual mediante botones.
- No se requiere uso de comandos.

---

## 12. Guardado de datos

El sistema guarda información automáticamente en archivos de texto.

Archivos utilizados:

### album.txt

Almacena las cartas del usuario.

Ejemplo:

1,Wopa,Agua,Legendaria,200,200,200,/imagenes/Webo.png  
2,Poliwaj,Agua,Comun,150,100,50,/imagenes/poli.png

---

### torneos.txt

Almacena información de torneos.

Ejemplo:

T1|Torneo Fuego Extremo|Pokémon TCG|2026-05-10|18:30|25.5|10  
T2|Liga Maestro Pokémon|Pokémon TCG|2026-05-12|20:00|30.0|8

---

## 13. Recomendaciones de uso

- No cerrar la aplicación durante procesos de torneos.
- Verificar stock antes de realizar compras.
- Revisar el progreso de XP frecuentemente.
- Mantener conexión de archivos en la carpeta del proyecto.

---

## 14. Problemas comunes

### La aplicación no carga imágenes

Verificar que la ruta de la imagen sea correcta dentro del proyecto.

### No se guardan datos

Verificar que los archivos .txt existan en la carpeta raíz del proyecto.

### Error en torneos

Puede deberse a falta de tickets disponibles o cola vacía.

---

## 15. Cierre del sistema

Para cerrar el sistema correctamente:

- Usar la opción "Salir" del menú principal
- No cerrar forzadamente la ventana

Esto asegura que los datos se guarden correctamente en los archivos.
