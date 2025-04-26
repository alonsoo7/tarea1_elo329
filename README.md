## TAREA POO. ENTREGA DE SEGUNDA ETAPA

En esta etapa se continuó con la implementación del sistema, centrándose en la clase **Recorder** para implementar el tipo de Suscriptor: Registrador. Para esto, se utilizó la estructura de la clase `Follower`, pero con la modificación de que la salida ahora se graba en formato **CSV**. 

### Clases y Métodos Implementados

- **Constructor** en `Recorder`.
- **Método** para grabar las posiciones en formato CSV.
- **Método `subscribe`** para que el `Recorder` pueda recibir notificaciones.

Además, se adaptaron las clases **Publisher** y **Subscriber** para trabajar con la nueva funcionalidad de almacenamiento en **CSV**. 

### Configuración y Ejecución

Para esta etapa, ahora se contempla un archivo de configuración. Por defecto, el archivo de configuración para esta etapa es `config.txt`. y tiene la estructura:

publicador      GPS   Posiciones
suscriptor      Registrador MiRegistrador   Posiciones    trayectoria.txt

Es decir, se menciona un componente de tipo publicador con nombre GPS que publica en el topico Posiciones.
Ademas, se menciona un componente de tipo suscriptor de tipo Registrador con nombre MiRegistrador que se suscribe al topico Posiciones y cuyo archivo de salida es  trayectoria.txt

### Ejecución del Programa

Se ha creado un nuevo **Makefile** para este stage, lo que permite ejecutar el programa con una configuracion predeterminada donde el archivo de configuración se llama config.txt. Para ejecutar el programa, sigue los siguientes pasos:

1. **Compilación**:
   - Ejecuta el siguiente comando para compilar el proyecto:
     ```bash
     make
     ```

  **Ejecución**:
   - Ejecuta el programa con:
     ```bash
     make run
     ```
   - El programa tomará el archivo `config.txt` por defecto y procesará las entradas correctamente. 
  
2. **Ejecución directa desde consola**:
   - Primero, compila el código con:
     ```bash
     javac *.java
     ```
   - Luego, ejecuta el programa con:
     ```bash
     java T1Stage2 config.txt
     ```
   - El funcionamiento será el mismo que con el Makefile.


- A medida que un publicador, en este caso GPS publique las posiciones mediante entrada de consola, el Registrador almacenará esas posiciones en un formato CSV (Nombre,Topico,CoordenadaX,CoordenadaY), en este caso, en el archivo trayectoria.txt

### Limpiar los Archivos Generados

Para limpiar los archivos generados por la ejecución del programa, ejecuta:
```bash
make clean
