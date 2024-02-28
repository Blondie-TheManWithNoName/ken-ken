<a name="readme-top"></a>

# KenKen System

<details>
	<summary>Taula de Continguts</summary>
	<ol>
		<li><a href="#sobre-el-projecte">Sobre el Projecte</a></li>
		<li><a href="#llenguatge-i-llibreries">Llenguatge i Llibreries</a></li>
		<li><a href="#consideracions">Consideracions</a></li>
		<li><a href="#execució-del-pograma">Execució del Programa</a></li>
		<li><a href="#referències">Referències</a></li>
	</ol>
</details>

## Sobre el Projecte

Projecte de l'assignatura de Projectes de Programació (PROP) de la UPC-FIB.
* Semestre: 2023-2024 Q2
* Grup: 41
* Professor: Carles Arnal (carles.arnal@upc.edu)
* Membres del grup:
	* Brian Fernando i Arias (brian.fernando.arias@estudiantat.upc.edu)
	* Daniel Espinalt i Jitkov (daniel.espinalt@estudiantat.upc.edu)
	* Noah Barberà i Guardiola (noah.barbera.i@estudiantat.upc.edu)
	* Oriol Segura i Niño (oriol.segura.nino@estudiantat.upc.edu)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Llenguatge i Llibreries

Aquest projecte ha estat desenvolupat amb el JDK 11.0.21 distribuït per Oracle.

[![Java][Java]][Java-url]

I utilitza les següents llibreries per a realitzar testejos (incloses en el VC):

[![JUnit][JUnit]][JUnit-url]
[![Hamcrest][Hamcrest]][Hamcrest-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Consideracions

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Execució del Programa

A continuació s'explica com es pot executar el programa del projecte.

1. Abans de res cal assegurar-se que tenim instalada la versió de Java correcte, com s'ha dit abans, aquesta és la [JDK 11.0.21][Java-url].

	- És important assegurar-nos que farem servir la versió de Java correcte ja que pot ser que en tinguem més d'una instalada. Podem saber quina versió executarem des de la terminal amb la comanda `java -version`.

	- En cas que l'output indiqui una altra versió caldrà editar la variables d'entorn `$PATH` per a executar la versió que volem de Java:

	```bash
	# Canviar '/path/to/java/' per la ruta del JDK 11.0.21
	export PATH=/path/to/java/bin:$PATH
	```

2. Des d'una terminal ens dirigim a la carpeta on volem el projecte i el clonem allà:
```bash
git clone https://repo.fib.upc.es/grau-prop/subgrup-prop41.3.git
cd subgrup-prop41.3
```

3. El VC ja trackeja els executables JAR que són a la carpeta EXE, així que directament podem probar el programa sencer amb
```bash
java -jar EXE/KenKen.jar
```

4. Si volem compilar-lo ho farem des del `makefile` del directori `FONTS` amb:
```bash
make compile
```

5. El propi `makefile` inclou un entry-point explicant les diverses funcionalitats que implementa:
```bash
make help
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Referències

https://www.researchgate.net/publication/302915862_An_Integer_Programming_Model_for_the_KenKen_Problem

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
[Java]: https://img.shields.io/badge/Java_11.0.21-F8981D?style=for-the-badge&logo=openjdk&logoColor=white
[Java-url]: https://www.oracle.com/es/java/technologies/javase/jdk11-archive-downloads.html
[JUnit]: https://img.shields.io/badge/JUnit-4.13.1-blue
[JUnit-url]: https://mvnrepository.com/artifact/junit/junit/4.13.1
[Hamcrest]: https://img.shields.io/badge/Hamcrest_Core-1.3-red
[Hamcrest-url]: https://mvnrepository.com/artifact/org.hamcrest/hamcrest-core/1.3
