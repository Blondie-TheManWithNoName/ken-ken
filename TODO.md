# Tasques a fer a cada sessió

### Per la primera entrega!
- [ ] Casos d'ús:
	1) Proposar KenKen
		* Solucionar
		* Guardar a la base de dades
	2) Generar KenKen
		* Solucionar (ha de tenir solució)
		* Guardar a la base de dades
		* Jugar
	3) _Obrir KenKen_
		* Jugar
	4) Continuar partida guardada
		* Jugar
	5) Consultar rànking

	<br/><br/>

	- _Obrir KenKen_
		1) Obrir KenKen des de la base de dades
		2) Obrir KenKen des d'un fitxer
	- Jugar &harr; Pausar / Continuar &rarr; Guardar Partida
- [ ] És correcte crear els casos d'ús abstractes per a `IA Solve` i/o `Load KenKen`?
- [ ] És correcte crear els casos d'ús de `DB Connector`?
- [ ] Implementar cronòmetre
	* Pausar / Reanudar
- [ ] Algorismes de resolució de KenKens:
	* ~~Brute Force~~ &rarr; [OEIS A002860](https://oeis.org/A002860)
	* ~~Branch And Bound~~
	* Candidate Search
		1) Cridar una funció inversa de cada operació que retorni una llista de vectors candidats que la satisfan (veure [`INVERSE.md`](./INVERSE.md))
		2) Amb els principis dels Latin Squares i el mètode de Line Reduction anar descartant candidats
- [ ] UML de la capa de `model`
- [ ] Drivers tutorials

### En un futur...
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

### 2024-03-06
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
- [ ] Guardar també la sol·lució també en guardar un KenKen?
	* SÍ &rarr; Cost computacional de resolució massa elevat
		1) Comprovar que la solució sigui correcte (en la pròpia base de dades)
- [ ] Afegir més operacions de les que diu l'enunciat
	* `OperationEquality(int target)` &rarr; `OperationLimitedOperands("=", target, 1)`
	* x^y &rarr; GRAN^petit
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
