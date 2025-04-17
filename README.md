
# TAREA POO

Este proyecto se desarrolla en 4 etapas progresivas, donde se construye un sistema de comunicación entre objetos que publican mensajes (`Publisher`) y objetos que los reciben (`Subscriber`).

---

## 🟢 Primera Etapa: Streamer envía notificaciones que almacena un Follower

### ✅ Clases requeridas:

- `Component`: Clase base para publicadores y suscriptores. Contiene al menos el atributo `nombre`.
- `Publisher`: Publicador genérico.
- `Subscriber`: Suscriptor genérico.
- `Follower`: Subclase de `Subscriber`, que almacena notificaciones de un `Streamer`.
- `Broker`: Encargado de administrar los tópicos.

### ⚙️ Configuración:
- Todo se define en el `main` de la clase `T1Stage1`.
- Se recibe entrada desde el teclado.
- Como hay un solo publicador, **no se debe escribir su nombre** en la entrada.

### 📄 Salida:
- Los mensajes que recibe el `Follower` se almacenan en `seguidor.txt`.

### 👀 Visualización de salida:
```bash
$ tail -f seguidor.txt
```

---

## 🟡 Segunda Etapa: GPS publica posiciones que almacena un Recorder

### ✅ Nuevas clases:

- `Recorder`: Subclase de `Subscriber`. Similar a `Follower`, pero guarda las salidas en **formato CSV**.

### ⚙️ Configuración:
- Se utiliza un archivo de configuración donde se define:
  - Un publicador tipo `GPS`.
  - Un suscriptor tipo `Recorder`.

- Entrada desde teclado.
- No es necesario incluir el nombre del publicador en cada línea.

### 📄 Salida:
- Las posiciones se guardan en `trayectoria.txt` en formato CSV.

### 👀 Visualización de salida:
```bash
$ tail -f trayectoria.txt
```

---

## 🔵 Tercera Etapa: Streamer envía notificaciones a dos suscriptores

### ⚙️ Cambios:

- Generalización del método `setupSimulation` para soportar:
  - Un `Streamer`.
  - Dos objetos `Follower` como suscriptores.

- La entrada **sí debe incluir el nombre del streamer**.

### ⚠️ Validación:
- Si se ingresa un nombre de streamer incorrecto, debe mostrarse:
```plaintext
Unknown Publisher
```

### 📄 Salida:
- Cada `Follower` guarda su salida en un archivo distinto (por ejemplo `seguidor1.txt`, `seguidor2.txt`).

### 👀 Visualización de salida:
```bash
$ tail -f seguidor1.txt
$ tail -f seguidor2.txt
```

---

## 🔴 Cuarta Etapa: Múltiples publicadores y suscriptores incluyendo Monitores

### ✅ Nuevas clases:

- `Monitor`: Similar a `Recorder`, pero su salida se guarda en otro **formato específico**.

### ⚙️ Configuración:
- Todo se configura mediante un **archivo externo**.
- Puede incluir:
  - Múltiples `Publisher`.
  - Múltiples `Subscriber` (`Follower`, `Recorder`, `Monitor`, etc).

- El ejecutable principal de esta etapa se debe llamar `Simulador`.

---

## 🏅 Extra-crédito: Contador de mensajes

### 🎯 Objetivo:

Crear un nuevo tipo de `Subscriber` que muestre en consola cuántos mensajes ha recibido.

### 📄 Formato de salida:
```plaintext
<tiempo en segundos desde el inicio>, <número de mensajes acumulados>
```

### 📊 Requisito adicional:

- Importar estos datos en Excel.
- Generar un gráfico de:
  - Eje X: Tiempo (segundos).
  - Eje Y: Total de mensajes recibidos.
- Incluir el gráfico en la documentación.

---

> **📌 Nota:** Se recomienda revisar los diagramas de clases entregados para cada etapa. Asegúrate de modular bien las clases y usar el patrón **Publisher-Subscriber** correctamente con ayuda del `Broker`.
