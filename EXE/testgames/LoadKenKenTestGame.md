# LoadKenKenTestGame

## Objecte de la prova

En aquest joc de prova es busca provar el funcionament del `LoadKenKenDriver` per a carregar un KenKen des d'un fitxer que segueixi el format especificat.

## Drivers

El joc de prova utilitza els drivers:
- `LoadKenKenDriver` per a carregar el KenKen
- `PlayKenKenDriver` per a jugar amb el KenKen carregat

## Fitxers de dades necessaris

Caldrà que el fitxer `data/load_tg.kenken` contingui el KenKen a carreger amb el format estàndar especificat.

Afegidament, es pot crear un fitxer `.values` per a carregar diversos moviments.

## Valors i efectes estudiats

El joc de prova funciona per a qualsevol KenKen correctament formatejat guardat en `data/load_tg.kenken`. Dins la carpeta `data/` se'n proporcionen uns quants exemples.

## Operativa

En ejecutar el JAR, es carregarà el KenKen des de l'arxiu `data/load_tg.kenken` i en cas d'error acabarà.

Un cop carregat l'usuari pot jugar al KenKen fent moviments individuals o carregant una llista de valors des d'un arxiu `.values`. Un cop acaba pot comprovar el resultat. En qualsevol moment l'usuari pot guardar la partida en `data/example_path.kenken_game` o sortir sense guardar.
