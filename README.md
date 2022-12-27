# Clean code en Java

## 1. Código limpio

### Qué es el código limpio

Cada lenguaje y paradigma de programación tiene su conjunto de matices, buenas prácticas, convenciones. EJ:

* Java: camelCase
* Python: snake_case

> El código limpio puede resumirse como un código que cualquier desarrollador puede leer y modificar fácilmente.

Referentes: Martin Fowler y Robert C Martin

Cualquiera puede escribir código que un ordenador pueda entender, la clave es que otras personas puedan entenderlo.

* Lenguaje de bajo nivel
* Lenguaje ensamblador
* Lenguaje de alto nivel
    * Primera generación
    * Segunda generación
    * ....

El código fuente será compilado/interpretado para un procesador concreto.

Java:

* Lenguaje de programación: Java SE
* JVM
* Biblioteca estándar (API)
* Java EE (Jakarta EE): javax a jakarta

Características:

* Enfocado: debe escribirse para resolver un problema específico y nada más.
* Simple. Evitar complejidad
* Testable: el código debe ser intuitivo y fácil de probar

Ciclo de vida del software:
* Análisis de requisitos (ERS)
* Diseño: Arquitectura (alto nivel) y bajo nivel (UML)
* Desarrollo
* Testing: QA, TDD, BDD
* Despliegue: devOps, devSecOps (AWS, GCP, DO, Azure)
* Mantenimiento: monitorización

Paradigmas:

* Estructurada
* POO
* Funcional
* Reactiva

### Por qué queremos el código limpio
Problema **deuda técnica** y complejidad: el código acumula defectos, demasiada complejidad, desarrollos que no se han implementado de buena manera. Se forma un efecto de bola de nieve que se acumula y puede hacer que un proyecto de software, al cabo de 5 años sea insostenible económicamente.

Herramientas para medir deuda técnica y complejidad:

* Empresa: SonarSource
* Plugin IntelliJ IDEA: Sonarlint
* Plataforma: SonarQube
* SaaS: SonarCloud

Ventajas:

* Mejor mantenimiento: corrección de fallos, evolución, nuevas mejoras, todo más fácil.
* Solución de problemas más rápida
* Comprensión más rápida: cualquier desarrollador nuevo en el proyecto se adapta antes.

### Regla BoyScout

> Deja el código más limpio de lo que lo encontraste.

Basta con introducir un pequeño arreglo cada vez que tocamos un código. Desde mejorar los identificadores hasta refactorizar una función.

El concepto de calidad debe ser común para todo el equipo, todo el equipo debe estar alineado en base a unas mismas prácticas.

## 2. Nombrado

* Utilizar nombres que revelen la intención
* Evitar desinformación
* hacer distinciones significativas
* Utilizar nombres pronunciables
* Utilizar nombres que se puedan buscar
* Evitar el mapeo mental
* Elegir nombre de Clase
* Elección nombre de método
* Claridad, cuidado con eufemismos: kill(), abort()
* Una palabra con concepto
* Contexto significativo

### 2.1. Nombres que revelen intención

Mal:
```java
int d; // elapsed time in days
```

Bien:
```java
int elapsedTimeInDays;
int elapsedDays;
int fileAgeInDays;
```



Mal:
```java 
// get active employees
public List<int[]> getElements(){
	List<int[]> list1 = new ArrayList<>();
	for( int[] x: theList )
		if(x[0] == 1)
			list1.add(x);
	return list1;
}
```

Bien:
```java 
public List<Employee> getActiveEmployees(){
	List<Employee> activeEmployees = new ArrayList<>();
	for(Employee employee: employees )
		if(employee.isActive())
			activeEmployees.add(employee);
	return activeEmployees;
}
```

### 2.2. Evitar desinformación
Evitar dejar pistas falsas que creen confusión con respecto a los datos que estamos manejando.

Si tenemos una variable ``account``, que en realidad es una lista entonces nos está creando la confusión de pensar que es una sola cuenta cuando en realidad es una lista. O al revés, una variables ``accounts`` cuando en realidad no es una estructura de datos.

### 2.3. Distinciones significativas

No utilizar el mismo nombre para referirse a cosas diferentes en el mismo ámbito. Evitar elegir nombres de forma arbitraria:

* ``Product``, ``ProductInfo``, ``ProductData``
* ``Customer``, ``CustomerObject``
* ``customer``, ``customerInfo``
* ``money``, ``moneyAmount``
* ``message``, ``theMessage``

Mal:

```java 
public static void copyChars(char[] a1, char[] a2){
	for (int i = 0; i < a1.length; i++)
		a2[i] = a1[i];
}
```

Bien:

```java 
public static void copyChars(char[] source, char[] destination){
	for (int i = 0; i < source.length; i++)
		destination[i] = source[i];
}
```

### 2.4. Utilizar nombres pronunciables

Mal:

```java 
class DtaRcrd102{
	LocalDateTime genymdhms;
	LocalDateTime modymdhms;
}
```

Bien:

```java 
class Customer{
	LocalDateTime generationTimestamp;
	LocalDateTime modificationTimestamp;
}
```

### 2.5. Búsqueda de nombres


Mal:

```java 
for (int j = 0; j < 34; j++){
	s += (t[j]*4) / 5;
}
```

Bien:

```java 
public static final int WORK_DAYS_PER_WEEK = 5;

int idealTasksPerDay = 4; 
int sum = 0;

for (int j = 0; j < tasks.length; j++){
	int realTaskDays = tasks[j].estimate * idealTasksPerDay;
	int realTaskWeek = realTaskDays / WORK_DAYS_PER_WEEK;
	sum += realTaskWeek;
}

```

### 2.6. Notación Húngara

HN proporciona una forma de prefijar el nombre de una variable con su tipo de dato. Esto se utilizaba más en los lenguajes de bajo nivel.

No se usa hoy en día en los lenguajes de alto nivel, especialmente java que es un lenguaje fuertemente tipado.

```java
// Notación húngara: [scope] + type + name + [qualifier]

bFinished = false;
strHexDigits = "0123456789ABCDEF";
```

### 2.7.  Prefijos

Mal:

```java 
public class Part{
	private String m_dsc; // textual description
}
```

Bien:

```java 
public class Part{
	private String description;
}
```

### 2.8. Interfaces e implementaciones

Mal: `IShapeFactory`, `ShapeFactory`

Bien: `ShapeFactory`, `ShapeFactoryImp` o `ShapeFactoryImpl`

### 2.9. Asignación mental

En el contexto tradicional de los bucles ``for`` se suelen utilizar las letras ``i``, ``j``, ``k``, ``l``.

Pero en otros contextos no es buena idea utilizar variables de una sola letra que impliquen un proceso mental extra de tener que saber o mapear mentalmente a qué concepto equivale cada cosa.

### 2.10. Nombrado de clases y métodos

Los métodos deberían ser verbos: ``postPayment()``, ``deletePage()``, ``save()``.

Los métodos para acceder o cambiar debería ser: ``get``, ``set``, ``is``.

En lugar de sobrecargar constructores es mejor usar:

* Factorías que describan los argumentos
* Patrón Builder

```java
Complex point = new Complex(23.0);
Complex point = new Complex(23.0, 50.9, 6.0, 77, true, "text");

// Uso de Factories
Complex point = Complex.fromRealNumber(23.0);
```

### 2.11. Claridad, cuidado con eufemismos: kill(), abort()

```
kill()
abort()
stop()
delete()
```

### 2.12. Una palabra por concepto

Ser consistente a la hora de nombrar, no utilizar distintas palabras para lo mismo de manera arbitraria:

* get(), fetch(), retrieve(), find(), read(): Si todos hacen lo mismo en diferentes clases, lo mejor es nombrarlos igual en todas las clases.
* CustomerController, EmployeeController. Evitar utilizar palabras distintas cuando en realidad es lo mismo: CustomerController, EmployeeManager, AccountDriver
    * Spring REST: CustomerRestController, EmployeeRestController
    * Spring MVC: CustomerController, EmployeeController

## 3. Funciones

### 3.1. Funciones pequeñas

2,3,4 líneas de código por función está bien. En general, cuantas menos líneas tenga mejor.

### 3.2. Bloques e indentación
Indentación es la sangría que aplicamos al código que está contenido en una estructura: if, else, switch, for, while.

Cada bloque debería tener una o dos líneas como mucho.

### 3.3. Una sola funcionalidad por función

Mal

```java
public void emailClients(List<Client> clients){
	for(Client client: clients){
		Client clientDB = repository.findOne(client.getId());
		if(clientDB.isActive())
			email(client);
	}
}

```

Bien:

```java
public void emailClients(List<Client> clients){
	for(Client client: clients){
		if(isActive(client.getId()))
			email(client);
	}
}

private boolean isActive(Long customerId){
	Client clientDB = repository.findById(customerId);
	return clientDB.isActive();
}

```


### 3.4. No mezclar niveles de abstracción

* Nivel abstracción alto: ``getHtml();``
* Nivel de abstracción intermedio: ``String pagePathName = PathParser.render(pagePath);``
* Nivel de abstracción bajo: ``.append("\n")``

### 3.5. Lectura descendente

> De alto nivel a bajo nivel.

El código se lee como una narración descendente.

Cada función vaya seguida de las del siguiente nivel de abstracción, de modo que podamos leer el programa descendiendo un nivel de abstracción cada vez.

Es decir, no mezclar en el orden las funciones de alto nivel con las de bajo ni intercalarlas.

### 3.6. Instrucciones switch

* Técnica de refactoring del polimorfismo.
* Mejoras de expresiones switch incorporadas en las últimas versiones de java 14 y 17.

### 3.7. Nombres descriptivos

```java

// mal
addToDate(date, 1)

// bien
addMonthToDate(1, date)

```

### 3.8. Argumentos

* cero (niládicas)
* uno (monádico)
* dos (diádico)
* tres (triádico)
* más de tres (poliádico)

El número ideal de argumentos es cero. Los argumentos dificultan el testing.

### 3.9. Evitar flags

```java

// mal
render(false);

// bien
// funcion para true
renderForSuite();

// funcion para false
renderForSingleTest();

```

### 3.10. Funciones diádicas

Se debería evitar tener que revisar el javadoc/signatura para enterarnos de qué argumentos y en qué orden debemos pasarlos.

```java

assertEquals(expected, actual)

assertExpectedActualEquals(expected, actual)
```

### 3.11. Objetos y listas de argumentos

```
Circle makeCircle(double x, double y, double radius)

Circle makeCircle(Point point, double radius)
```


### 3.12. Verbos y palabras clave

```java
// tratar de combinar verbo/sustantivo
write(String name)
write("Alan")
```

### 3.13. Evitar efectos secundarios

Función que comprueba unas credenciales.

Un efecto secundario sería si esa misma función inicia una sesión en la que cargue las credenciales cuando son correctas.

Ejemplos de efectos secundarios:

* llamadas en red
* IO con archivos
* Bases de datos
* Interactuar con otros sistemas

Tratar de desglosar las funcionalidades que no tienen efectos secundarios de las que sí con el fin de evitar confusiones. Por ejemplo llamar dos veces a una función checkPassword podría sobreescribir los datos de la sesión.

## 4. Comentarios

### Malos comentarios

* Comentarios confusos
* Comentarios redundantes
* Comentarios obligatorios (javadoc con muchos o excesivos parámetros)
* Javadoc en código que no es público
* Comentarios de seguimiento de cambios con fechas etc: Se usa control de versiones como por ejemplo Git.
* Comentarios ruido: un comentario indicando cuál es el constructor por defecto
* Comentarios autogenerados por el IDE y que no aportan realmente información
* Código comentado
* Comentarios HTML
* Comentarios muy largos
* Información sin contexto o no evidente: por ejemplo un comentario que dice que agregues 200 bytes a algo
* Información no local: añadir información de aspectos que no pueden ser controlados por dicha función.

### Buenos

* Comentarios legales / copyright
* Comentarios informativos pero breves que muestre determinada importancia o agregue información relevante
* Comentarios que expliquen la intencionalidad del código
* Clarificación: aclarar una expresión regular
* Comentarios TODO: siempre y cuando se quiten una vez se haya terminado el desarrollo
* Amplificar o destacar la importancia de un proceso
* Javadocs en APIs públicas
* Advertencia de consecuencias: por ejemplo avisar de que la ejecución de un determinado método puede consumir mucho tiempo.

## 5. Formateo

### Formato vertical:

* Cuantas menos líneas en cada archivo de código fuente mejor.
* Ideal: menos de 200 líneas. Combinar esta técnica junto con el Principio de una sola responsabilidad.
* Metáfora del periódico: arriba del todo lo de más alto nivel y a medida que descendemos está lo de más bajo nivel.
* distancia vertical:
    * Si tenemos una función que llama a otra función entonces interesa que estén próximas
    * Los conceptos que sean afine o relacionados deberían estar más próximos verticalmente. Ejemplo: Métodos de la interfaz List, los que están relacionados están más próximos
* densidad vertical
    * facilitar la respiración entre sentencias
    * se pueden poner seguidas aquellas sentencias que estén relacionadas o conformen un todo mientras que aquellas que no estén tan relacionadas se pueden separar

### Formato horizontal:

* La regla principal es no tener que hacer scroll horizontal
* Tamaño ideal de 120 caracteres (IDEs ya lo controlan)
* Apertura horizontal y densidad: poner espacios entre operadores y variables
* En los argumentos de los métodos no es necesario dejar espacios entre los paréntesis
* Respetar la indentación
* IntelliJ IDEA:
    * Formatear el código con: Ctrl + Shift + L
    * Se pueden exportar/importar las configuraciones en File > Manage IDE Settings > Export


## 6. Objetos y estructuras de datos

* No exponer los detalles de implementación
    * Abstracción: interfaces, clases abstractas
    * Encapsulación: modificadores ``public``, ``private``, ``protected``
    * Métodos ``get()`` y ``set()``
    * Bajo acoplamiento

Asimetría de objetos y estructuras de datos:

* Las **estructuras de datos** exponen sus datos y no tienen funciones significativas que restrinjan los detalles de esos datos.
* **Objetos** esconden sus datos detrás de abstracciones y exponen funciones/métodos que operan sobre esos datos.
    * Ejemplo: Clase ``Customer`` tiene un atributo ``age`` solo dejamos modificarla a través de un método en el que controlamos que sea mayor de edad y que esté en un rango que tenga sentido (18 a 100 años).

Aplicar la Ley de Demeter (LOD):
* Directiva del principio de **menor conocimiento** promueve modularidad, separación de responsabilidades, evita el código Espagueti.
* Funciones: un método no debe invocar métodos en objetos que son devueltos por cualquier de las funciones permitidas. Ejemplo:

```java

// mal 
context.getOptions().getScrathDir().getAbsolutePath().getURI()

// bien
context.createScrathDirPathURI()

```

En el caso de estar trabajando con APIs cerradas que no podemos modificar se podría plantear usar un patrón de diseño Adapter.

* Data Access Object (DAO): patrón de diseño empleado para abstraer las operaciones de base de datos (CRUD: Create Retrieve Update Delete).
* Data Transfer Object (DTO): es un POJO (Plain Old Java Object), es decir, una clase normal y corriente con getters y setter que sirve únicamente para llevar información. Ejemplo: se usan para interactuar con otros sistemas software externos, serializar-deserializar de un formato a java y viceversa.
* https://stackoverflow.com/questions/1612334/difference-between-dto-vo-pojo-javabeans
* Record: son nuevos tipos en Java que permiten evitar tener que escribir boilerplate el uso de getter, setter, toString, etc. La idea es realizar lo que hace Lombok pero de forma nativa: https://docs.oracle.com/en/java/javase/16/language/records.html

## 7. Manejo de errores

* Usar excepciones en lugar de códigos de error
* Escribir try-catch de entrada a la hora de desarrollar un código que puede ocasionar excepciones.
* checked vs. unchecked: las excepciones comprobadas (checked) dan lugar a tener que cambiar la signatura en todos los métodos asociados, es decir, quien ha invocado a la función que lanza la excepción. Recomendación: unchecked.
* Casuística en la que desde una función de alto nivel/intermedio nos puede interesar gestionar todas las excepciones lanzadas por funciones de bajo nivel siempre y cuando estén relacionadas.
    * Si son totalmente independientes y no están relacionadas puede interesar más tratarlas con try catch en la propia función.
    * El trato de las excepciones lanzadas se podría gestionar con AOP.
* Retorno de valores:
    * No devolver ``null``, o lanzar una excepción o usar ``Optional``.
    * El principal problema de devolver null o trabajar con null es:
        * Ensucia el código teniendo que añadir las típicas comprobaciones de null-checks
        * Es propenso a bugs y errores: ``NullPointerException``
* Pasar valores:
    * No pasar ``null`` como parámetro. Se suele aplicar programación defensiva y lanzar una excepción de tipo ``IllegalArgumentException``.
* A nivel de catch: tratar de capturar siempre excepciones concretas en lugar de ``Exception``
* CUIDADO: los niveles de error cuando usamos loggers porque podemos saturar las alertas al equipo de desarrollo si ponemos como error algo que en realidad sería de tipo INFO o WARNING.
* Dentro de los catch solemos utilizar una herramienta de logging (ejemplo log4j) para registrar los errores ya sea en archivos o en sistemas externos como por ejemplo [Elastic Stack](https://www.elastic.co/es/elastic-stack/).

## 8. Límites

Límites entre nuestro código y las APIs que utilizamos.

* Código de **terceras partes**: ``java.util.Map``.
    * Crear una clase que represente una estructura de datos, por ejemplo la clase ``Sensors`` que tenga dentro un Mapa y permita realizar operaciones con el mismo sin necesidad de tener que conocer sus métodos desde fuera de Sensors.
    * Patrón Iterator
    * Patrón Adapter
* **Learning test**: crear un test donde se pruebe la librería o dependencia antes de utilizarla en nuestros desarrollos. El test luego servirá para probar que todo funciona si el día de mañana se decide actualizar la versión de la librería.
* Uso de **mocks** cuando necesitamos utilizar un código que todavía no ha sido desarrollado.

### log4j

4j: para java.

Lo normal es que se utilice **log4j** a través de **slf4j** que actuaría como un Factory que permite obtener objetos de log.



## 9. Organización de clases


Orden de una clase:

1. Variables
    1. Constantes públicas/privadas
    2. Variables estáticas (static) privadas
    3. Variables privadas de instancia
2. Funciones públicas
3. Funciones privadas


* Respetar el orden de las clases
* Encapsulación: modificadores private/protected
* Principio SRP: clases pequeñas y de una sola responsabilidad
* Alta cohesión: una clase en la que cada variable es utilizada por cada método tiene una cohesión máxima. Cuando la cohesión es alta, significa que los métodos y las variables de la clase son codependientes y forman un todo lógico.
* Buscamos bajo acoplamiento y alta cohesión
* Romper clases grandes en clases más pequeñas
* Romper funciones en funciones más pequeñas
* Organización para el cambio: principio de Open/Closed, separar todo en clases más pequeñas en vez de una clase grande de manera que los nuevos se añaden por medio de nuevas clases y la estructura lo permite.


## 10. Sistemas

* Separación de conceptos:
    * No vincularse con clases concretas: no usar concreciones o implementaciones directamente.
    * Utilizar abstracción: inyección de dependencias (DI) (IoC). No crear nosotros directamente los objetos, los crea e inyecta un mecanismo de dependencias.
* Usar patrón Factory para la construcción de objetos
* Java Proxies: utilizar las APIs de Java Proxy y Reflection da lugar a código complejo y la recomendación es utilizar un framework de AOP.
* Desarrollo escalado: implementar solo las historias de esta iteración o de este momento en lugar de programar cosas que no hacen falta todavía "por si acaso".
* Uso de Domain Specific Language (DSL): es interesante crear lenguajes específicos de dominio con el objetivo de representar el conocimiento del equipo experto en una materia. Ejemplo de DSL: lenguaje SQL, queries de Spring Data JPA, sintaxis JHipster para la generación de entities.

### AOP

Programación Orientada Objetos (POO) tiene ciertas desventajas:

* Dispersión y duplicado del código: una misma responsabilidad se repite a lo largo de diferentes componentes del código: **seguridad, logs, auditoría, transaccionalidad**.
* Código Espagueti: acoplamiento entre diferentes responsabilidades

Solución:

Programación Orientada a Aspectos (AOP): es un paradigma que intenta formalizar y representar aquellos elementos que son transversales a todo el sistema.

Elementos de AOP:

* Aspectos (aspect)
* Consejos (advice)
* punto de corte (pointcut)
* Interceptadores (interceptors)

Implementación:

* Spring AOP: implementación simple de AOP a través de Spring IoC para resolver los problemas más comunes.
* AspectJ: la tecnología AOP original que ofrece una solución AOP completa.



## 11. Diseños emergentes

4 reglas:

1. Todos los tests deben pasar, tener éxito, cumplirse todos los asserts programados
2. No debe haber código duplicado
3. Se debe expresar la intención por medio del nombrado
4. Minimizar el uso de clases y métodos

## 12. Concurrencia

### Conceptos
Concurrencia: es simplemente ejecutar varias tareas en paralelo entre sí para aprovechar mejor los recursos computacionales. En los lenguajes de programación como Java, los encargados de proporcionar la concurrencia son los hilos.

Hilo: un hilo es un proceso ligero con su propia pila de llamadas. Sin embargo, un hilo tiene el privilegio de acceder a los datos compartidos de otros hilos que se ejecutan bajo el mismo proceso. Dentro de una aplicación Java, podemos utilizar muchos hilos para lograr un procesamiento paralelo o concurrencia.

Es mucho más fácil escribir código que se ejecuta en un solo hilo que escribir programas concurrentes.

### Por qué queremos concurrencia

La concurrencia es una estrategia de desacoplamiento porque nos ayuda a segmentar qué es lo que se hace del cuándo se hace. En aplicaciones de un solo hilo (single-threated) el qué y el cuando están acoplados.

Desacoplar el qué del cuando permite mejorar el rendimiento y la estructura de los programas, pero es difícil de diseñar e implementar.


### Mitos y conceptos erróneos

- La concurrencia siempre mejora el rendimiento
- El diseño no cambia cuando se escriben programas concurrentes
- Entender los problemas de concurrencia no es importante cuando se trabaja con un contenedor como el contenedor Web o EJB

Cuidado:

* La concurrencia incurre en cierta sobrecarga (escribir código adicional)
* La concurrencia correcta es compleja
* Los errores de concurrencia son difíciles de repetir
* La concurrencia a menudo requiere un cambio fundamental en la estrategia de diseño

¿Qué hace que la concurrencia sea tan difícil? A diferencia de las aplicaciones de un solo hilo, la concurrencia permite múltiples vías de procesamiento que pueden conducir a resultados diferentes.


### Principios para la concurrencia:

* SRP
* Limitar el alcance de los datos usando encapsulación y evitando compartición de los datos
* Usar copias de los datos
* Hilos independientes que eviten compartir datos con otros hilos
* Conocer la librería que se use: por ejemplo framework Collections de Java tiene colecciones concurrentes
* Modelos de ejecución:
    * Bound resources
    * mutual exclusion
    * starvation
    * deadlock
    * livelock
    * Esquema producer-consumer
    * Esquema readers-writers
    * Problema de la cena entre filósofos
* Mantener secciones synchronized lo más pequeñas posibles
* Escribir código de terminación de hilos es complicado
* Escribir test que permitan comprobar el código que use hilos
* Aquellos fallos intermitentes y extraños podrían venir derivados de problemas en el uso de hilos
* Parametrizar el código que usa hilos, dejarlo tuneable


### Concurrencia en Java

java.lang.Thread

java.lang.Runnable

Explorar la API _java.util.concurrent_

La librería rxjava proporciona programación reactiva que facilita el uso de concurrencia.


### Posibles fuentes de problemas

* Errores de interferencia de hilos
* Errores de consistencia de memoria

### Implementación

* Procesos
* Hilos
* Clase Thread
* Crear Runnable
* Thread.sleep
* Interrumpir: t.interrupt() y Thread.interrupted()
* t.join() permite a un hilo esperar a que otro acabe
* Palabras reservadas en Java: ``synchronized`` y ``volatile``.
* Fallos de sincronización:
    * Thread interference: describe cómo se introducen los errores cuando varios hilos acceden a datos compartidos.
    * memory consistency: describe los errores que resultan de las vistas inconsistentes de la memoria compartida.
    * synchronized methods: describe un sencillo lenguaje que puede prevenir eficazmente la interferencia de hilos y los errores de consistencia de memoria.
    * implicit locks: describe un lenguaje de sincronización más general, y describe cómo la sincronización se basa en bloqueos implícitos.
    * atomic access: habla de la idea general de operaciones que no pueden ser interferidas por otros hilos.
* **deadlock**: El bloqueo describe una situación en la que dos o más hilos están bloqueados para siempre, esperando el uno al otro.
* **Starvation**: La inanición describe una situación en la que un hilo no puede acceder regularmente a los recursos compartidos y no puede progresar. Esto ocurre cuando los recursos compartidos dejan de estar disponibles durante largos periodos por hilos "codiciosos".
* **livelock**: A menudo, un hilo actúa en respuesta a la acción de otro hilo. Si la acción del otro hilo es también una respuesta a la acción de otro hilo, entonces puede producirse un bloqueo. Al igual que en el caso del bloqueo, los hilos bloqueados no pueden seguir avanzando. Sin embargo, los hilos no están bloqueados - simplemente están demasiado ocupados respondiendo el uno al otro para reanudar el trabajo.


## 13. SOLID

Principios orientados a objetos con el fin de que el código sea más mantenibles. Tienen su origen gracias a Robert Martins (Uncle Bob) y son promovidos

SOLID es un acrónimo mnemotécnico acuñado por Michael Feathers y que se basa en los cinco principios que establece para escribir software comprensible y mantenible:

* **Principio de responsabilidad única (SRP)**: Cada interfaz, clase o método que definamos debe tener un objetivo claramente definido. En esencia, lo ideal es que haga una cosa y la haga bien. Esto lleva a que los métodos y las clases sean más pequeños y que se puedan probar.
* **Principio de apertura-cierre (OCP)**: El código que escribimos debería estar idealmente abierto a la extensión pero cerrado a la modificación. Lo que esto significa es que una clase debe ser escrita de manera que no haya necesidad de modificarla. Sin embargo, debería permitir cambios a través de la herencia o la composición.
* **Principio de sustitución de Liskov (LSP)**: Este principio establece que cada subclase o clase derivada debe ser sustituible por su clase madre o base. Esto ayuda a reducir el acoplamiento en el código base y, por tanto, a mejorar la reutilización.
* **Principio de segregación de interfaces (ISP)**: Implementar una interfaz es una forma de proporcionar un comportamiento específico a nuestra clase. Sin embargo, una clase no debe implementar métodos que no necesita. Esto nos obliga a definir interfaces más pequeñas y centradas.
* **Principio de inversión de la dependencia (DIP)**: Según este principio, las clases sólo deben depender de las abstracciones y no de sus implementaciones concretas. Esto significa efectivamente que una clase no debería ser responsable de crear instancias para sus dependencias. En su lugar, dichas dependencias deben ser inyectadas en la clase.

## 14. Arquitectura limpia


### 1. Diseño y arquitectura
Diseño y arquitectura son lo mismo, con matices.

El **diseño** se asocia comúnmente con bajo nivel de detalle mientras que la arquitectura hace referencia a estructuras a alto nivel de detalle pero ambas son parte del mismo todo.

El objetivo de la **arquitectura** es minimizar los recursos humanos requeridos para construir y mantener el sistema requerido.

**¿Motivo?** El principal enemigo del buen diseño es el exceso de confianza de los desarrolladores que quieren terminar los desarrollos demasiado rápido. El problema viene al pensar que se puede limpiar el código más adelante pero finalmente nunca se arregla y la deuda técnica se acumula y la complejidad crece exponencialmente. Es por esta razón que deben plantearse las buenas prácticas desde el principio.

### Por qué arquitectura limpia


El centro de tu aplicación no es la base de datos. Tampoco es uno o varios de los frameworks que puedas estar utilizando. El centro de tu aplicación son los casos de uso de tu aplicación - Unclebob (fuente)

La arquitectura limpia nos ayuda a resolver, o al menos a mitigar, estos problemas comunes de la arquitectura:

* Las decisiones se toman demasiado pronto, a menudo al principio del proyecto, cuando menos sabemos del problema que tenemos que resolver
* Es difícil cambiar, así que cuando descubrimos nuevos requisitos tenemos que decidir si queremos introducirlos o pasar por un costoso y doloroso rediseño. Todos sabemos cuál suele ganar. Las mejores arquitecturas son las que nos permiten aplazar el compromiso con una solución concreta y nos permiten cambiar de opinión
* Se centra en los frameworks. Los marcos son herramientas que se utilizan, no arquitecturas a las que hay que ajustarse. Los marcos a menudo requieren compromisos de tu parte, pero no se comprometen contigo. Pueden evolucionar en diferentes direcciones, y entonces estarás atascado siguiendo sus reglas y peculiaridades
* Se centra en la base de datos. A menudo pensamos primero en la base de datos y luego creamos un sistema CRUD a su alrededor. Acabamos utilizando los objetos de la base de datos en todas partes y tratamos todo en términos de tablas, filas y columnas
* Nos centramos en los aspectos técnicos y cuando nos preguntan por nuestra arquitectura decimos cosas como "son servlets corriendo en tomcat con una db de oracle usando spring"
* Es difícil encontrar las cosas lo que hace que cada cambio sea más largo y doloroso
* La lógica de negocio está repartida por todas partes, dispersa en muchas capas, por lo que al comprobar cómo funciona algo nuestra única opción es depurar todo el código base. Peor aún, a menudo está duplicada en múltiples lugares
* Fuerza/Anima a realizar pruebas lentas y pesadas. A menudo nuestra única opción para las pruebas es pasar por la GUI, ya sea porque la GUI tiene mucha lógica, o porque la arquitectura no nos permite hacer otra cosa. Esto hace que las pruebas sean lentas de ejecutar, pesadas y frágiles. El resultado es que la gente no los ejecuta y la compilación se rompe a menudo
* Despliegues poco frecuentes porque es difícil hacer cambios sin romper las funcionalidades existentes. La gente recurre a ramas de características de larga duración que sólo se integran al final y dan lugar a grandes lanzamientos, en lugar de pequeños incrementos

La arquitectura limpia nos proporciona todos estos beneficios:

* Estrategia de pruebas efectiva que sigue la pirámide de pruebas y nos da una construcción rápida y fiable
* Los frameworks están aislados en módulos individuales para que cuando (no si) cambiamos de opinión sólo tengamos que cambiar un lugar, sin que el resto de la aplicación se entere
* Independiente de la base de datos, que se trata como cualquier otro proveedor de datos. Nuestra aplicación tiene casos de uso reales en lugar de ser un sistema CRUD
* Arquitectura chillona, es decir, grita su uso previsto. Cuando miras la estructura del paquete te haces una idea de lo que hace la aplicación en lugar de ver los detalles técnicos
* Toda la lógica de negocio está en un caso de uso, por lo que es fácil de encontrar y no está duplicada en ningún otro lugar
* Es difícil hacer algo incorrecto porque los módulos imponen dependencias de compilación. Si intentas usar algo que no debes, la aplicación no compila
* Siempre estamos listos para desplegar dejando el cableado del objeto para el final o utilizando banderas de características, por lo que obtenemos todos los beneficios de la integración continua (sin necesidad de ramas de características)
* Enjambre de historias para que diferentes parejas puedan trabajar fácilmente en la misma historia al mismo tiempo para completarla más rápidamente
* Un buen monolito con casos de uso claros que puedes dividir en microservicios más adelante, una vez que hayas aprendido más sobre ellos

Por supuesto, tiene un coste:

* La percepción de la duplicación de código. Las entidades pueden ser representadas de manera diferente cuando se utilizan en la lógica de negocio, cuando se trata de la base de datos y cuando se presentan en un formato JSON. Puedes sentir que estás duplicando código, pero en realidad estás favoreciendo el desacoplamiento sobre el DRY
* Necesitas una lógica de negocio interesante para "justificar" la estructura. Si todo lo que haces en tu caso de uso es un método de una línea para leer o guardar desde una base de datos, entonces tal vez puedas salirte con la tuya con algo más simple


### Arquitectura limpia

4 aspectos del desarrollo de software:

* Desarrollo
* despliegue
* operaciones
* mantenimiento

La arquitectura debe mantener esos 4 aspectos lo más sencillos posibles para poder crear software realmente útil. Un arquitecto dejará para el final siempre los detalles y se centrará en esos 4 aspectos a alto nivel primero.

Dejar cuantas más opciones abiertas mejor.


### Características de arquitectura limpia

- Independiente de los marcos de trabajo. La arquitectura no depende de la existencia de alguna biblioteca de software cargada de características. Esto le permite utilizar dichos marcos como herramientas, en lugar de obligarle a encajar su sistema en sus limitadas restricciones.
- Comprobable. Las reglas de negocio pueden probarse sin la interfaz de usuario, la base de datos, el servidor web o cualquier otro elemento externo.
- Independiente de la interfaz de usuario. La interfaz de usuario puede cambiar fácilmente, sin cambiar el resto del sistema. Una interfaz de usuario web puede ser sustituida por una interfaz de usuario de consola, por ejemplo, sin cambiar las reglas de negocio.
- Independiente de la base de datos. Puedes cambiar Oracle o SQL Server por Mongo, BigTable, CouchDB o cualquier otra cosa. Sus reglas de negocio no están ligadas a la base de datos.
- Independientes de cualquier organismo externo. De hecho, tus reglas de negocio no saben nada de las interfaces con el mundo exterior.


### partes de arquitectura limpia

* Entidades
* Casos de uso
* Adaptadores de interfaces
* Frameworks y drivers


### Cohesión y acoplamiento

Cohesión:

- REP: Principio de Equivalencia de Reutilización/Liberación
- CCP: El principio de cierre común
- CRP: El principio de reutilización común

Acoplamiento:

* SDP: Principio de dependencias estables
* SAP: principio de abstracciones estables.


Acoplamiento:
* Dependencias acíclicas
* Diseño top-down
* Dependencias estables
* Métricas para la gestión de dependencias

### Política y nivel
Un software es un conjunto de políticas.

El nivel de una política es la distancia de las entradas y salidas.

Cuanto más lejos esté, mayor será su nivel. Las políticas de alto nivel tienden a cambiar con menos frecuencia que las de bajo nivel.

Separar esas políticas y hacer que las dependencias apunten hacia las de alto nivel reduce el impacto del cambio.

###  Reglas del negocio

Las reglas de negocio son la razón de ser de un sistema de software. Son el núcleo de la funcionalidad. Llevan el código que hace, o ahorra, dinero. Son las joyas de la familia.

Las reglas de negocio deben permanecer claras, sin ser manchadas por preocupaciones más básicas como la interfaz de usuario o la base de datos utilizada. Idealmente, el código que representa las reglas de negocio debería ser el corazón del sistema, con preocupaciones menores conectadas a ellas. Las reglas de negocio deberían ser el código más independiente y reutilizable del sistema.

Son las:

* Entidades
* Casos de uso

### Límites y su anatomía

La arquitectura de un sistema está compuesta por un conjunto de componentes software y los límites que los separan.
* **Cruce de límites**: una función de un límite llama a otra función que está en otro límite pasando datos. La clave es controlar esos límites para que si uno cambia no haya que recompilar el otro.
* **Arquitectura monolítica**: no tiene límites físicos estrictos, todo está en un mismo desplegable. No obstante define sus límites haciendo uso de polimorfismo.
* Componentes desplegables: en el caso de java están los jar y war.
* Hilos: no son límites arquitecturales, tan solo una forma de organizar y programar el orden de ejecución.
* Procesos locales
* Servicios: representan los límites más fuertes

Conclusión:

La mayoría de los sistemas, salvo los monolíticos, utilizan más de una estrategia de límites. Un sistema que hace uso de los límites de los servicios también puede tener algunos límites de los procesos locales.

De hecho, un servicio es a menudo sólo una fachada para un conjunto de procesos locales que interactúan. Un servicio, o un proceso local, será casi con toda seguridad un monolito compuesto por componentes de código fuente o un conjunto de componentes de despliegue vinculados dinámicamente.

Los límites de un sistema suelen ser una mezcla de comunicaciones locales y otras que se preocupan por la latencia.


###  Límites parciales y capas

YAGNI: “You Aren’t Going to Need It.”

3 formas de implementar un límite parcial:

Un límite completo puede ser caro: interfaces de límite, estructuras de datos de entrada y salida, y difícil de mantener. Un diseño puramente anticipatorio puede violar YAGNI "no lo vas a necesitar", en cuyo caso los arquitectos a veces proponen un límite parcial, en el que todos los componentes pueden estar en su lugar pero sentados en un sistema monolítico, o una inversión de dependencia (interfaz) está en su lugar, o como un patrón de fachada donde incluso una inversión de dependencia no se aplica.



### Servicios pequeños y grandes

¿Son los servicios siempre importantes desde el punto de vista arquitectónico?

No necesariamente: la arquitectura del sistema está definida por los límites que separan la política de alto nivel de los detalles de bajo nivel. Los servicios en sí mismos son una forma, y son arquitectónicamente significativos cuando representan límites arquitectónicos.

¿Ofrecen un mejor desacoplamiento?

No necesariamente. Todavía pueden estar acoplados por los recursos compartidos en la red, o los datos que comparten. Por ejemplo, al igual que ocurre con los cambios en la firma de las funciones, la incorporación de un nuevo campo en una serie de microservicios requiere cambios en el esquema del servicio.

¿Ofrecen desarrollo y despliegue independientes?

Es cierto hasta cierto punto, pero a menudo vemos que las operaciones siguen necesitando coordinación. "La arquitectura no se define por los mecanismos físicos por los que los elementos se comunican y ejecutan".

Preocupaciones transversales: un sistema construido con descomposición funcional es muy vulnerable a las nuevas características que atraviesan todos los comportamientos funcionales, sean o no SOA.

En resumen, por muy útiles que sean, las SOA no son la panacea, y para tener en cuenta los cambios transversales, puede ser necesario diseñar las partes internas de un servicio (o un componente funcional) teniendo en cuenta la Regla de Dependencia, y permitir implementaciones concretas enchufables.

* **Monolítica**
* **SOA**  (Service Oriented Architecture):
    * es un tipo de arquitectura de software, la cual se basa en la integración de aplicaciones mediante servicios. Sobre estos servicios se construyen: composiciones, BPM, proxys e incluso APIs.
    * Descomponer la lógica de negocio de una organización (o partes de ella) en pequeñas unidades de funcionalidad. Estas pequeñas unidades son los **servicios**. Con esto conseguimos romper con el concepto de aplicaciones «silo», donde se creaba una aplicación para resolver una necesidad de negocio concreta, otra para resolver otra, etc… Lo que tendremos será una **plataforma transversal** formada por un **inventario de servicios** (o varios) de forma que no solventaremos las necesidades cambiantes del negocio creando nuevas aplicaciones sino **combinando diferentes servicios** (y creando nuevos servicios cuando corresponda)
    * Normalmente usan SOAP (web services): **SOAP (S**imple **O**bject **A**ccess **P**rotocol), es un protocolo que nos permitirá realizar servicios web sin estado, a través de TCP y con un formato XML.
* **Microservicios**: se consideran una evolución de SOA y son más a grano fino e independientes entre sí.
    * Normalmente usan REST (servicios RESTful): **RE**presentational **S**tate **T**ransfer es una definición de arquitectura basadas en el protocolo HTTP usando habitualmente JSON.
    * GraphQL


