# Compiladores 2019-1: Proyecto 4 (Sistema de tipos).

## Integrantes del equipo

* Almanza Ibarra Raziel
* Casas Monreal Juan
* Naranjo Robledo Carlos

## Compilar
`mvn clean initialize compile`

## Ejecutar
Se ejecutara el archivo en test.p,, debe llevar un espacio al final
`mvn exec:java -Dexec.mainClass="ast.Compilador"`

## Especificación de commits.
Todos los commits deben seguir el siguiente formato dependiendo del caso.

* **Caso de nuevos archivos**: El commit debe llevar el prefijo `Añadido:`.
* **Caso de cambiar archivos**: El commit debe llevar el prefijo `Cambios:`.
* **Caso de arreglar bug**: El commit debe llevar el prefijo `Arreglado:`.
* **Caso de archivos eliminados**: El commit debe llevar el prefijo `Eliminado:`.


## Generación de documentación
El pom incluye un plugin que es un wrapper de doxygen
https://github.com/os-cillation/doxygen-maven-plugin .

Para usarlo tiene que tener instalado _doxygen_ y, de manera recomendada,
la biblioteca _graphviz_ .

`mvn doxygen:generate`
