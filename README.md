# README - Tarea POO


# ENTREGA DE PRIMERA ETAPA

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
```

# ENTREGA DE SEGUNDA ETAPA

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
```

# ENTREGA DE TERCERA ETAPA

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
   - Se cargará el archivo de configuración y se configurará el **Publicador** y los **dos seguidores** que se piden en el enunciado. Luego, podrás ingresar los mensajes mediante consola en el siguiente formato:
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
```

Finalmente, tenemos el simulador:

# ENTREGA DE STAGE4 - SIMULADOR.


Este repositorio contiene el simulador desarrollado. El objetivo principal de esta etapa fue mejorar el sistema para permitir la configuración multiples publicadores y suscriptores utilizando archivos de configuración. Se realizó la creación de la clase **Monitor** y mejoras en la gestión de tópicos, suscripciones y mensajes.

## Archivos

La estructura final del proyecto está compuesta por los siguientes archivos en esta etapa:

- `Component.java`
- `Publisher.java`
- `Topic.java`
- `Subscriber.java`
- `Follower.java`
- `Recorder.java`
- `Monitor.java`
- `Broker.java`
- `Simulador.java` (Clase principal para ejecutar el simulador)

## Funcionalidad

El simulador tiene como objetivo permitir la configuración de publicadores y suscriptores (de diferentes tipos), donde los **publicadores** envían mensajes a sus tópicos correspondientes y los **suscriptores** a esos tópicos los reciben y almacen en archivos de configuración según que tipo de suscriptor sean. Solo hay un tipo de publicador.


# Tipos de suscriptores
- **Monitor**: Es un tipo de suscriptor que solo registra las posiciones cuando la distancia al origen (0,0) es mayor o igual a 500, considerando las coordenadas X e Y dentro del rango de [0, 500], en formato CSV.
- **Seguidor**: Es un tipo de suscriptor que registra mensajes (de texto) que envian los publicadores a los tópicos donde está suscrito.
- **Registrador**: Es un tipo de suscriptor que registra posiciones (en coordenada X, Y) en un formato CSV.

# **Gestión de Tópicos**
- Los **publicadores** solo pueden publicar en un único tópico, pero varios publicadores pueden compartir un mismo tópico.
- Los **suscriptores** pueden suscribirse a uno o más tópicos.

# **Archivo de Configuración**:
- El archivo de configuración contiene líneas con el formato:
     ```
     publicador <nombre> <tópico>
     suscriptor <tipo> <nombre> <tópico> <archivo>
     ```
   - El tipo de suscriptor puede ser **Monitor**, **Seguidor** o **Registrador**. El sistema permite que múltiples suscriptores se suscriban a un único tópico y que un publicador publique en un solo tópico.

# **Ejecución del simulador**:

Para la ejecución final del Simulador, se creó un makefile, donde se ejecuta el simulador con un archivo de configuración que por defecto debe llamarse config4.txt. Para esto, se debe ejecutar:

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

   - El simulador recibe mensajes mediante consola con el formato:
     ```
     <nombre_publicador> <mensaje o coordenadas>
     ```
   - Los **suscriptores de tipo Registrador o Monitor** reciben las coordenadas en formato CSV, mientras que los **seguidores** reciben texto. Los mensajes se almacenan en archivos de salida según el tipo de suscriptor.


Otra forma de ejecutarlo para elegir un nombre de archivo de configuración arbitrario es mediante consola. Se puede ejecutar:

javac *.java

java Simulador <nombre del archivo de config, en este caso config4.txt>


La ejecución es igual a la forma con makefile.


