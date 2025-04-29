## TAREA POO. ENTREGA DE PRIMERA ETAPA

Se implementaron las siguientes clases y métodos:

- **subscribe**, **notify** y el **constructor** en `Topic`.
- **Constructor** en `Subscriber` y en `Component`.
- **update** y **constructor** en `Follower`.
- **Constructor** y **publishNewEvent** en `Publisher`.

### Ejecución de la etapa

Existen dos formas de ejecutar esta etapa:

1. **Uso del Makefile**: 
   - Primero, ejecuta el siguiente comando para compilar el proyecto:
     ```bash
     make
     ```
   - Luego, ejecuta el programa con:
     ```bash
     make run
     ```
   - El programa recibirá entrada por consola directamente. Solo debes ingresar el mensaje y notarás que se crea un archivo `seguidor.txt`, que se irá actualizando conforme se vayan enviando mensajes. (se puede visualizar utilizando tail seguidor.txt -f)

2. **Ejecución directa desde consola**:
   - Primero, compila el código con:
     ```bash
     javac *.java
     ```
   - Luego, ejecuta el programa con:
     ```bash
     java T1Stage1
     ```
   - El funcionamiento será el mismo que con el Makefile.

### Limpiar los archivos creados

Para limpiar los archivos generados, puedes ejecutar:
```bash
make clean
