Descripción del Proyecto: Librería Multifuncional ORM/ODM en Java
El proyecto tiene como objetivo principal la creación de una herramienta en Java que actúe como una librería multifuncional capaz de operar como Object-Relational Mapping (ORM) o Object-Document Mapping (ODM), dependiendo del tipo de base de datos proporcionado como parámetro de configuración. Esta herramienta proporcionará una interfaz intuitiva y flexible para mapear objetos de clases Java a tablas en bases de datos relacionales o a documentos en bases de datos NoSQL, facilitando así el desarrollo de aplicaciones Java escalables y eficientes.

Características Principales
Flexibilidad de Configuración:

La librería puede configurarse para trabajar tanto con bases de datos relacionales (como MySQL y Oracle) como con bases de datos NoSQL (como MongoDB), adaptándose dinámicamente según las necesidades del proyecto.
Mapeo de Objetos:

Permite mapear objetos Java a estructuras de almacenamiento en la base de datos seleccionada, simplificando la persistencia y recuperación de datos.
Operaciones CRUD:

Implementa funciones para realizar las operaciones básicas de Create, Read, Update y Delete (CRUD) sobre las bases de datos, proporcionando métodos estandarizados y fáciles de usar.
Funcionalidades Detalladas
Para MongoDB:

insertDocument

Inserta un documento en una colección específica de MongoDB.
Parámetros: tableName, connectionString, databaseName, object, idValue.
ReadDocuments

Lee documentos de una colección de MongoDB y los convierte en objetos Java.
Parámetros: clazz, connectionMongo, databaseNameMongo.
UpdateDocument

Actualiza un campo específico de un documento en una colección de MongoDB.
Parámetros: collectionName, connectionMongo, databaseNameMongo, keyField, keyValue, fieldName, nuevoValor.
UpdateStructureCollection

Actualiza la estructura de una colección en MongoDB, basada en una clase Java.
Parámetros: clazz, connectionString, databaseName.
DeleteDocument

Elimina un documento de una colección en MongoDB basado en su identificador.
Parámetros: collectionName, connectionString, databaseName, id.
Para MySQL y Oracle:

InsertRecord

Inserta un registro en una tabla específica de una base de datos relacional.
Parámetros: tableName, url, user, password, object, idValue.
ReadRecords

Lee registros de una tabla y los convierte en objetos Java.
Parámetros: clazz, url, user, password.
UpdateRegistry

Actualiza un campo específico de un registro en una tabla.
Parámetros: tableName, url, user, password, keyField, keyValue, fieldName, nuevoValor.
UpdateTableStructure

Actualiza la estructura de una tabla en la base de datos, basada en una clase Java.
Parámetros: clazz, url, user, password.
DeleteField

Elimina un campo de una tabla en la base de datos.
Parámetros: url, user, password, tableName, fieldName.
DeleteRecord

Elimina un registro de una tabla en la base de datos basado en su identificador.
Parámetros: tableName, url, user, password, id.
Beneficios

Simplicidad: La librería abstrae la complejidad de la interacción directa con las bases de datos, permitiendo a los desarrolladores centrarse en la lógica de negocio.
Escalabilidad: Facilita la creación de aplicaciones Java escalables, al soportar tanto bases de datos relacionales como NoSQL.
Flexibilidad: Proporciona una interfaz común para trabajar con diferentes tipos de bases de datos, haciendo que el cambio entre ellas sea transparente para el desarrollador.

Este proyecto está diseñado para ser una solución completa y eficiente para la persistencia de datos en aplicaciones Java, proporcionando todas las herramientas necesarias para trabajar de manera efectiva con bases de datos relacionales y NoSQL.

HERRAMIENTAS NECESARIAS PARA SU USO:
tener instalado apache maven link:https://maven.apache.org/download.cgi
instalar en visual code: .NET tool, java, debugger for java
luego ir a la terminal y escribir: mvn clean install
y listo
