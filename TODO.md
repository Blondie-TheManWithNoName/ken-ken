# Tasques a fer a cada sessió

### Per la segona entrega (27 de maig)
- [ ] Diagrama UML
- [ ] Nou cas d'ús &rarr; Play Random ??? Choose dificulty (Easy / Medium / Hard)
- [ ] Optimitzacions ???
	- [ ] Caselles amb menys opcions

### Per la tercera entrega (3 de juny)
- [ ] Manual d'usuari &rarr; LaTeX
	- [ ] Com instal·lar l'aplicació
	- [ ] [...]

### Consideracions
- Els mètodes del controlador de domini han de ser accessibles des del driver o des del controlador de presentació, però mai des de les classes del model.
- Excepcionalment, el controlador de presentació pot passar el controlador de domini com a paràmentre a una vista en cas d'interacció continuada de la vista amb la capa de domini.

### En un futur...
- [ ] Implementar cronòmetre
	* Pausar / Reanudar
- [ ] Afegir un codi d'error a les excepcions ???
- [X] Implementar guardar partida
- [ ] Assistència CPU durant la solució:
	* Pistes &rarr; següent pas (potser té un cost elevat)
	* Sí el KenKen és incorrecte mostrar el perquè
- [X] Pensar com mostrar les restriccions dels grups en `swing`:
	* ~~Fons de color + llegenda~~
	* `JLabel` a la `BorderLayout.NORTH` de la `CellView`
- [X] ~~És correcte crear els casos d'ús abstractes per a `IA Solve` i/o `Load KenKen`?~~
- [X] ~~És correcte crear els casos d'ús de `DB Connector`?~~
- [X] Algorismes de resolució de KenKens:
	* ~~Brute Force~~ &rarr; [OEIS A002860](https://oeis.org/A002860)
	* ~~Branch And Bound~~
	* Candidate Search
		1) Cridar una funció inversa de cada operació que retorni una llista de vectors candidats que la satisfan
		2) Amb els principis dels Latin Squares i el mètode de Line Reduction anar descartant candidats
	* Backtracking

### 2024-05-01
- [ ] Figma

### Per la primera entrega (22 d'abril)
- [X] Diagrama de casos d'ús
- [ ] Diagrama UML
- [X] Codi
- [X] JavaDoc+
- [X] Classes implementades per cadascú
- [X] Descripció de les ED i Algoritmes
- [X] Tests JUnit
	- [X] `OperationAdditionTest`
	- [X] `KenKenProposerTest`
	- [X] `KenKenGeneratorTest`
- [X] Jocs de proves &rarr; mirar-ho bé!
	- [X] Propose KenKen &rarr; Oriol
	- [X] Generate KenKen
	- [X] Load KenKen
	- [X] Continue saved game
	- [X] See ranking
- [X] Drivers
	- [X] Llegir KenKen de l'usuari des d'un arxiu `.kenken`

### ~~2024-03-20~~ &rarr; 2024-04-10
- [X] `DomainController` &rarr; Oriol
- [X] Sistema de puntuació &rarr; Dani
	* Per mida (9&times;9 més difícil que 3&times;3)
	* Per tipus d'operacions (* més difícil que +)
	* Per número de grups (com més grups, més fàcil) ???
	* Per temps de resolució

### 2024-03-13
- [X] Generar KenKen:
	- [X] N&times;N
	- [X] &tau; topologia
	- [X] Fixed values
- [X] Proposar KenKen

### 2024-03-06
- [X] Afegir més operacions de les que diu l'enunciat
	* `OperationEquality(int target)` &rarr; `OperationLimitedOperands("=", target, 1)`
	* `OperationPower(int target)` &rarr; GRAN^petit
- [X] Analitzar el diagrama UML (ensenyar-li al professor)

### 2024-02-28
- [X] Repositori GitLab &rarr; subgrup-prop41.3
- [X] Preguntar pel format de lliurament:
	* Arxius `index.txt` ~~a TOTS els directoris o~~ només a `.`, `/DOCS`, `/EXE` i `/FONTS`
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
