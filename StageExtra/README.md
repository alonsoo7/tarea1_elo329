# Desarrollo Stage Extra

## Descripción
Para la etapa extra, se añadió un nuevo tipo de suscriptor llamado **Contador**. Este suscriptor cuenta:
- La cantidad total de mensajes recibidos
- El tiempo de ejecución desde que se inició el simulador

Los datos se almacenan en un archivo CSV con el formato:
```
<cantidad_mensajes>,<tiempo de ejecución>
```

## Implementación
El **Contador** hereda de `Subscriber` y se añade para su funcionamiento:
- Un contador de mensajes
- Un temporizador que se activa al iniciar la simulación

Este desarrollo fue realizado en otra rama del repositorio:
https://github.com/alonsoo7/tarea1_elo329/tree/StageExtra

## Ejecución

### Usando el Makefile
1. Compilar:
   ```
   make
   ```
2. Ejecutar:
   ```
   make run
   ```

### Manualmente
1. Compilar:
   ```
   javac *.java
   ```
2. Ejecutar:
   ```
   java Simulador <nombre del archivo de configuración, en este caso configEXTRA.txt>
   ```

## Configuración
En el archivo de configuración, ahora puedes añadir un nuevo tipo de suscriptor llamado **Contador**. Por ejemplo:
```
suscriptor Contador Contador_1 Notificaciones_1 contador1.csv
```

### Este tipo suscriptor almacenará:
1. La cantidad de mensajes recibidos en total sumando todos los tópicos a los que está suscrito.
2. El tiempo de ejecución desde que se inició el programa.

### Formato del archivo CSV:
```
<cantidad_mensajes>,<tiempo de ejecución>
```

## Visualización
En la documentación se incluye un gráfico que representa estos datos para facilitar su análisis.




