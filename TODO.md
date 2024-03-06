# Tasques a fer a cada sessió

### Per la primera entrega! (22 d'abril)
- [X] Diagrama de casos d'ús
- [ ] Diagrama UML
- [ ] Classes implementades per cadascú
- [ ] Descripció de les ED i Algoritmes
- [ ] Codi
- [ ] Tests JUnit
- [ ] Drivers
- [ ] Jocs de proves

### En un futur...
- [ ] Implementar cronòmetre
	* Pausar / Reanudar
- [ ] Sistema de puntuació:
	* Per mida (9&times;9 més difícil que 3&times;3)
	* Per tipus d'operacions (* més difícil que +)
	* Per número de grups (com més grups, més fàcil) ???
	* Per temps de resolució
- [ ] Afegir un codi d'error a les excepcions? &rarr; només té lògic si en fem un manual
- [ ] Implementar guardar partida
- [ ] Assistència CPU durant la solució:
	* Pistes &rarr; següent pas (potser té un cost elevat)
	* Sí el KenKen és incorrecte mostrar el perquè
- [ ] UML de la capa de `presentation`
- [ ] UML de la capa de `persistence`
- [X] Pensar com mostrar les restriccions dels grups en `swing`:
	* ~~Fons de color + llegenda~~
	* `JLabel` a la `BorderLayout.NORTH` de la `CellView`
- [X] ~~És correcte crear els casos d'ús abstractes per a `IA Solve` i/o `Load KenKen`?~~
- [X] ~~És correcte crear els casos d'ús de `DB Connector`?~~
- [ ] Algorismes de resolució de KenKens:
	* ~~Brute Force~~ &rarr; [OEIS A002860](https://oeis.org/A002860)
	* ~~Branch And Bound~~
	* Candidate Search
		1) Cridar una funció inversa de cada operació que retorni una llista de vectors candidats que la satisfan
		2) Amb els principis dels Latin Squares i el mètode de Line Reduction anar descartant candidats
- [ ] UML de la capa de `model`
- [ ] Drivers tutorials
- [ ] Guardar també la sol·lució també en guardar un KenKen?
	* SÍ &rarr; Cost computacional de resolució massa elevat
		1) Comprovar que la solució sigui correcte (en la pròpia base de dades)

### 2024-03-13

### 2024-03-06
- [ ] Afegir més operacions de les que diu l'enunciat &rarr; Dani
	* `OperationEquality(int target)` &rarr; `OperationLimitedOperands("=", target, 1)`
	* `OperationPower(int target)` &rarr; GRAN^petit
- [ ] Començar `Candidate Search` &rarr; Brian
- [ ] Format estàndard per fitxers

### 2024-02-28
- [X] Repositori GitLab &rarr; subgrup-prop41.3
- [X] Preguntar pel format de lliurament:
	* Arxius `index.txt` a TOTS els directoris o només `.`, `/DOCS`, `/EXE` i `/FONTS`?
- [X] ~~Farem servir una base de dades?~~
	* ~~SÍ &rarr; on la hostegem?~~
		1) ~~SQL~~
			- ~~localhost:3306 (MySQL)~~
				* ~~proporcionar script de creació `db_init.sql`~~
				* ~~proporcionar script de població `sample.sql`~~
			- ~~EC2 (MySQL)~~
		2) ~~NoSQL~~
			- ~~Firebase~~
	* NO &rarr; tot en fitxers
- [X] Lògica de la divisó:
	* ~~Divisió entera (5 / 2 = 2)~~
	* Forçar divisió exacte (5 / 2 => ERROR)
- [X] Generar KenKen &rarr; com demanar la topologia?
	* ~~Grup amb una forma &tau; (i una operació &psi;)~~
	* Escollir topologia i fer servir **NOMÉS** aquella forma &rarr; sinó, error

### 2024-02-21
- [X] Log in / Guest &rarr; després de resoldre un KenKen es demanarà a l'usuari el seu nom, per al rànking
- [X] Es pot utilitzar `swing` per a la interfície
- [X] Escollir on guardar les partides:
	* ~~Base de dades~~
	* Fitxers locals
