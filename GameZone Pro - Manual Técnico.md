# GameZone Pro - Manual Técnico

## Información General

- **Curso:** Introducción a la Programación y Computación I  
- **Lenguaje:** Java  
- **Interfaz:** Java Swing  
- **Autor:** Gabriel Sales  

---

# Descripción del Sistema

GameZone Pro es una aplicación de escritorio desarrollada en Java que simula una plataforma de entretenimiento digital.

El sistema permite la gestión de videojuegos, cartas coleccionables, torneos en línea y un sistema de recompensas basado en experiencia (XP), niveles y logros.

Incluye el uso de estructuras de datos dinámicas implementadas desde cero, programación orientada a objetos, concurrencia con hilos y generación de reportes HTML.

---

# Arquitectura del Sistema

El sistema está basado en el patrón **MVC (Modelo - Vista - Controlador)**.

## Modelo

- Usuario  
- Juego  
- Carta  
- Torneo  
- NodoSimple / ListaSimple  
- NodoMatriz / Malla Ortogonal  
- NodoCola / Cola  
- Logro  

---

## Controlador

- ControlUsuarios  
- ControlTienda  
- ControlTorneos  
- ControlAlbum  
- ControlRecompensas  
- ControlReportes  
  
  

---

## Vista (Swing)

- MenuPrincipal  
- Tienda  
- AlbumCartas  
- Torneos  
- Recompensas  
- Reportes  
- Login  

---

# Manejo de Archivos

## catalogo.txt

CODIGO|NOMBRE|GENERO|PRECIO|PLATAFORMA|STOCK|DESCRIPCION

## historial.txt

Registro de compras del usuario.  

## album.txt

Estado del álbum de cartas.  

## torneos.txt

ID|NOMBRE|JUEGO|FECHA|HORA|PRECIO|TICKETS

## tickets_vendidos.txt

Registro de tickets vendidos.

## leaderboard.txt

Ranking de usuarios.

---

# Módulos del Sistema

---

## Tienda de Videojuegos

- Catálogo desde archivo  
- Búsqueda por nombre o código  
- Filtros por género y plataforma  
- Carrito con lista enlazada simple  
- Validación de stock  
- Historial de compras  

---

## Álbum de Cartas Coleccionables

- Malla ortogonal de nodos enlazados  

- Cada nodo tiene:
  
  - norte
  - sur
  - este
  - oeste  

- Agregar cartas desde tienda  

- Intercambiar cartas  

- Búsqueda visual con resaltado  

- Renderizado en Swing  

---

## Eventos y Torneos

- Cola implementada desde cero  
- Múltiples Threads (taquillas)  
- Venta de tickets concurrente  
- Uso de synchronized en dequeue()  

---

## Sistema de Recompensas

- Sistema de XP  

- Niveles:
  
  - Aprendiz  
  - Jugador  
  - Veterano  
  - Maestro  
  - Leyenda  

- Logros desbloqueables  

- Barra de progreso  

---

## Leaderboard

- Ordenamiento manual  
- Top 10 usuarios  
- Podio visual  
- Resaltado del usuario actual  

---

## Reportes HTML

- Inventario  
- Ventas  
- Álbum  
- Torneos  

Generados en HTML con CSS embebido y apertura automática en navegador.

---

# Estructuras de Datos

- Lista Enlazada Simple  
- Cola personalizada  
- Malla ortogonal  
- Arreglos básicos (internos)  

 Sin ArrayList ni Collections Framework

---

# Concurrencia

- Uso de Threads en torneos  
- Múltiples taquillas simultáneas  
- synchronized para evitar race conditions  
- SwingUtilities.invokeLater para GUI  

---

# Reglas de Negocio

- No compra sin stock  
- Cola no puede procesarse vacía  
- Logros solo una vez  
- XP define nivel automáticamente  
- Todo implementado con estructuras propias  

---

# Validaciones

- Campos obligatorios  
- Validación numérica  
- Control de stock  
- Duplicados  
- Entradas vacías  

---

# Bitácora

Formato:[OPERACION][USUARIO][MODULO][FECHA][HORA]

Registra:

- Compras  
- Tickets  
- Logros  
- Errores  

---

# Ejecución

1. Ejecutar MenuPrincipal.java  
2. Iniciar sesión  
3. Navegar módulos  

---

# Consideraciones Técnicas

- Java Swing obligatorio  
- Sin Collections Framework  
- Estructuras propias  
- Persistencia en archivos  
- Uso de Threads  
- Actualización GUI segura  

---

# Problemas y Soluciones

## Condición de carrera

Solución: synchronized en cola

## UI no actualiza

Solución: invokeLater()

## Malla ortogonal fallando

Solución: creación en 2 fases

---

# Conclusión

GameZone Pro integra estructuras de datos, concurrencia y programación orientada a objetos en un sistema completo de escritorio, simulando una plataforma real con múltiples módulos interconectados.
