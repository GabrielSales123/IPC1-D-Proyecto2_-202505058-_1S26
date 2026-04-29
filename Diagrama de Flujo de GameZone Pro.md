# Diagrama de Flujo de GameZone Pro

---

## Menú principal

```mermaid
flowchart TD
    A[Inicio MenuPrincipal] --> B[Recibir ControlUsuarios y UsuarioActual]
    B --> C[Crear ControlRecompensas]
    C --> D[Ejecutar gestionLogros]

    D --> E[initComponents]
    E --> F[inicializar datos de usuario]

    F --> G[Mostrar ID de usuario en pantalla]

    G --> H[Calcular nivel XP]

    H --> I{XP < 500}
    I -->|Si| J[Nivel Aprendiz]
    I -->|No| K{XP < 1500}

    K -->|Si| L[Nivel Jugador]
    K -->|No| M{XP < 3500}

    M -->|Si| N[Nivel Veterano]
    M -->|No| O{XP < 7000}

    O -->|Si| P[Nivel Maestro]
    O -->|No| Q[Nivel Leyenda]

    J --> R[Mostrar barra XP]
    L --> R
    N --> R
    P --> R
    Q --> R

    R --> S[Menu principal listo]

    S --> T{Usuario selecciona modulo}

    T -->|Tienda| U[Abrir Tienda y cerrar MenuPrincipal]
    T -->|Album| V[Abrir Album y cerrar MenuPrincipal]
    T -->|Eventos| W[Abrir Torneos y cerrar MenuPrincipal]
    T -->|Gamificacion| X[Abrir Recompensas y cerrar MenuPrincipal]
    T -->|Reportes| Y[Abrir Reportes y cerrar MenuPrincipal]
    T -->|Info| Z[Abrir Informacion y cerrar MenuPrincipal]
    T -->|Sesion| AA[Cerrar sesion y volver a Login]

    U --> END[Fin flujo MenuPrincipal]
    V --> END
    W --> END
    X --> END
    Y --> END
    Z --> END
    AA --> END
```

## Torneo

```mermaid
flowchart TD

A[Inicio ventana Torneos] --> B[Crear ControlTorneos]
B --> C[Cargar torneos desde archivo]
C --> D[Inicializar interfaz grafica]

D --> E[Crear panel de torneos]
E --> F[Mostrar torneos disponibles]

F --> G{Usuario selecciona torneo}

G -->|Selecciona| H[Guardar torneoActual]
H --> I[Limpiar cola visual]
I --> J[Mostrar mensaje de seleccion]

G -->|No selecciona| F

J --> K{Usuario encola jugador}

K -->|Ingresar nombre| L[Agregar a cola del torneo]
L --> M[Actualizar vista de cola]
M --> K

K --> N{Presiona RUN}

N -->|Si| O[Verificar torneo seleccionado]
O --> P[Crear Taquilla 1 y Taquilla 2]
P --> Q[Iniciar hilos concurrentes]

Q --> R[Taquilla 1 atiende cola]
Q --> S[Taquilla 2 atiende cola]

R --> T[Actualizar campo Taquilla 1]
S --> U[Actualizar campo Taquilla 2]

T --> V[Registrar log de atencion]
U --> V

V --> W{Cola vacia?}

W -->|No| R
W -->|Si| X[Finaliza procesamiento]

X --> Y[Hilos detenidos o en espera]

N -->|No| K

Y --> Z[Fin proceso torneos]
```

## Album

```mermaid
flowchart TD

A[Inicio Album] --> B[Constructor Album]
B --> C[initComponents]
C --> D[inicializarObjetos]
D --> E[inicializarAlbum]
E --> F[renderizarAlbum]

%% Inicializar album
E --> E1[Crear panel 4x6]
E1 --> E2[Crear 24 celdas vacias]
E2 --> E3[Mostrar panel]

%% Renderizar album
F --> F1[Limpiar panel]
F1 --> F2[Recorrer matriz usuario]
F2 --> F3{Nodo existe}
F3 -->|No| F4[Fin fila]
F3 -->|Si| F5[Crear celda UI]

F5 --> F6{Tiene carta}
F6 -->|No| F7[Mostrar Vacia]
F6 -->|Si| F8[Cargar imagen y datos]

F8 --> F9[Agregar evento click]
F7 --> F10[Agregar celda]
F9 --> F10
F10 --> F2

%% Click en carta
G[Manejo click] --> G1{Dato null}
G1 -->|Si| G2[Salir]
G1 -->|No| G3{Intercambio activo}

G3 -->|No| G4[Seleccion 1]
G4 --> G5[Resaltar nodo]
G5 --> F

G3 -->|Si| G6[Seleccion 2]
G6 --> G7{Es misma carta}
G7 -->|Si| G2
G7 -->|No| G8[Intercambiar nodos]
G8 --> G9[Limpiar seleccion]
G9 --> F

%% Busqueda
H[Buscar cartas] --> H1[Recorrer matriz]
H1 --> H2{Nodo tiene carta}
H2 -->|No| H3[Desactivar resaltado]
H2 -->|Si| H4[Comparar filtros]

H4 --> H5{Coincide}
H5 -->|Si| H6[Resaltar]
H5 -->|No| H3

H6 --> H1
H3 --> H1
H1 --> H7[Renderizar album]

%% Mostrar carta
I[Mostrar carta] --> I1[Asignar nombre tipo rareza]
I1 --> I2[Actualizar barras]
I2 --> I3[Cargar imagen]
```

## Tienda

```mermaid

```
