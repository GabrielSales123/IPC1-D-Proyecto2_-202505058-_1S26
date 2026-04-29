# Informe de Desarrollo - GameZone Pro

## 1. Introducción

GameZone Pro es un sistema desarrollado en Java con interfaz gráfica Swing, orientado a la gestión de usuarios, cartas coleccionables, tienda virtual, álbum personal y sistema de recompensas. El sistema implementa estructuras de datos avanzadas como matrices ortogonales, listas enlazadas y manejo de eventos gráficos.

---

## 2. Objetivo del Sistema

El objetivo principal del sistema es permitir que los usuarios:

- Gestionen un álbum de cartas coleccionables.
- Compren cartas en una tienda virtual.
- Intercambien cartas dentro del álbum.
- Filtren y busquen cartas por atributos.
- Obtengan recompensas y experiencia por actividad.

---

## 3. Arquitectura del Sistema

El sistema está dividido en tres capas principales:

### 3.1 Capa de Vista

- `Album`
- `Tienda`
- `MenuPrincipal`

Encargada de la interfaz gráfica (Swing) y la interacción del usuario.

---

### 3.2 Capa Controlador

- `ControlUsuarios`
- `ControlTienda`
- `ControlRecompensas`

Gestiona la lógica de negocio y la comunicación entre vista y modelo.

---

### 3.3 Capa Modelo

- `Usuario`
- `Carta`
- `MallaOrtogonal`
- `NodoMatriz`
- Estructuras de datos (listas, matrices)

Representa la información del sistema.

---

## 4. Estructuras de Datos Implementadas

### 4.1 Matriz Ortogonal

Se utiliza para representar:

- Álbum de usuario
- Tienda de cartas

Permite navegación en dos dimensiones:

- `derecha`
- `abajo`

---

### 4.2 NodoMatriz

Contiene:

- `Carta dato`
- Punteros:
  - derecha
  - izquierda
  - arriba
  - abajo
- Estado visual:
  - resaltado

---

## 5. Módulo Album

### 5.1 Funcionalidad

El módulo `Album` permite:

- Visualizar cartas en una matriz 4x6.
- Mostrar detalles de cada carta.
- Buscar cartas por nombre, tipo y rareza.
- Intercambiar cartas entre posiciones.

---

### 5.2 Flujo Principal

1. Inicialización del álbum.
2. Renderizado de la matriz.
3. Carga de cartas desde el usuario.
4. Interacción con clic del usuario.
5. Intercambio de cartas (modo swap).

---

### 5.3 Lógica de Intercambio

- Primera selección → nodo1
- Segunda selección → nodo2
- Intercambio en matriz ortogonal
- Reinicio de estado

---

### 5.4 Búsqueda

Se recorre toda la matriz:

- Coincidencia por nombre
- Filtro por tipo
- Filtro por rareza

Se activa resaltado visual.

---

## 6. Módulo Tienda

### 6.1 Funcionalidad

El módulo `Tienda` permite:

- Visualizar cartas disponibles para compra.
- Agregar cartas al carrito.
- Confirmar compras.
- Actualizar usuario (XP, historial, álbum).

---

### 6.2 Flujo Principal

1. Inicialización de tienda.
2. Renderizado de cartas.
3. Selección de carta.
4. Agregar al carrito.
5. Confirmar compra.
6. Actualizar usuario.

---

### 6.3 Modo Compra

El sistema alterna entre:

- Vista normal de cartas
- Vista de carrito

Incluye:

- Lista de compras
- Total acumulado
- Confirmación de transacción

---

### 6.4 Confirmación de Compra

Al confirmar:

- Se agrega al historial del usuario
- Se suma experiencia (XP)
- Se incrementan estadísticas
- Se agrega carta al álbum
- Se limpia carrito

---

## 7. Sistema de Recompensas

El sistema evalúa:

- Número de compras
- Cantidad de cartas en álbum
- XP acumulado
- Gastos del usuario

Se generan logros automáticamente mediante `ControlRecompensas`.

---

## 8. Interfaz Gráfica

El sistema utiliza Swing con:

- `JFrame`
- `JPanel`
- `JLabel`
- `JButton`
- `JProgressBar`
- `JScrollPane`

Características:

- Render dinámico de matriz
- Eventos de mouse
- Actualización en tiempo real

---

## 9. Problemas Encontrados

- Manejo de `NullPointerException` en controladores.
- Actualización de UI tras cambios en la matriz.
- Sincronización entre carrito y modelo.
- Carga de imágenes desde recursos.

---

## 10. Soluciones Implementadas

- Validación de nodos antes de acceder a datos.
- Uso de `revalidate()` y `repaint()` en UI.
- Separación de lógica entre controlador y vista.
- Manejo seguro de recursos de imágenes.

---

## 11. Conclusiones

El proyecto GameZone Pro integra estructuras de datos avanzadas con interfaz gráfica, permitiendo una experiencia interactiva para gestión de cartas. La implementación de matrices ortogonales permitió una representación eficiente del álbum y la tienda.

---

## 12. Trabajo Futuro

- Implementación de base de datos.
- Sistema de combate entre cartas.
- Animaciones en la UI.
- Optimización de carga de imágenes.
