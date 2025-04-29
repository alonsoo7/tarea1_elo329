## TAREA POO. ENTREGA DE TERCERA ETAPA

En esta etapa se continuó con la desarrollo, generalizando el simulador para configurar un **Publicador** y **dos seguidores** utilizando un archivo de configuración.

### Cambios Realizados

Se implementaron los siguientes cambios clave en esta etapa:

1. **Generalización del Simulador**:
   - Se configuró el simulador para que pueda manejar un **Publicador** y **dos seguidores** a partir de un archivo de configuración.
   
2. **Lectura del Archivo de Configuración**:
   - Se implementó una doble lectura del archivo para evitar errores por orden en el archivo de configuración:
     1. **Primera lectura**: Identifica al **Publicador** y crea su instancia junto con el tópico en el Broker. Esto garantiza que el tópico esté disponible antes de que los suscriptores intenten suscribirse, asi evitamos problemas.
     2. **Segunda lectura**: Configura a los dos **suscriptores**, validando que el **tópico coincida** y que haya exactamente **dos suscriptores**. Esto previene problemas de ejecución causados por un orden incorrecto en la configuración.

3. **Entrada por consola**:
   - En esta stage los mensajes son ingresados por consola. Se debe seguir el formato: (streamer) (mensaje). donde solo los mensajes del **streamer** (publicador) que se especificó serán enviados, si el nombre del **streamer** no coincide con el nombre registrado del publicador en el archivo de configuración, se arrojará el mensaje de error:
     ```text
     Unknown Publisher
     ```
   
### Configuración y Ejecución

Al igual que en etapas anteriores, el archivo de configuración se utilizará para definir tanto al publicador como a los dos seguidores. Para esta entrega, el archivo de configuración debe tener el nombre de config3.txt si se quiere ejecutar por el makefile.

### Ejecución del Programa

1. **Compilación**:
   - Ejecuta el siguiente comando para compilar el proyecto:
     ```bash
     make
     ```

2. **Ejecución**:
   - Ejecuta el programa con:
     ```bash
     make run
     ```
   - Se cargará el archivo de configuración y se configurará el **Publicador** y los **dos seguidores** según lo especificado. Luego, podrás ingresar los mensajes mediante consola en el siguiente formato:
     ```text
     (streamer) (mensaje)
     ```
     donde (streamer) es el nombre del publicador registrado.

   - Si el nombre del **streamer** coincide con el publicador registrado, se enviará el mensaje. Si no, se mostrará el mensaje de error:
     ```text
     Unknown Publisher
     ```

Al igual que las stages antiguas, se puede tambien ejecutar manualmente haciendo:


javac *.class

java T1Stage3.java (archivo de config, en este caso config3.txt)

Y el funcionamiento es el mismo.

### Limpiar los Archivos Generados

Para limpiar los archivos generados por la ejecución del programa, ejecuta:
```bash
make clean
