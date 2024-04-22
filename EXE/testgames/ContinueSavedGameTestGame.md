# ContinueSavedGameTestGame

## Objecte de la prova

L'objectiu d'aquest joc de prova és continuar una partida guardada anteriorment del KenKen carregant-la des d'un fitxer i permetent al jugador seguir jugant-hi.

## Drivers

- `LoadSavedGameDriver´: Controla el procés de càrrega de la partida guardada del KenKen des d'un fitxer.

## Fitxers de dades necessaris

S'espera que el fitxer de la partida guardada del KenKen estigui ubicat dins la carpeta "data/" i tingui l'extensió ".kenken_game".

## Valors estudiats i efectes estudiats

En aquest joc de prova es tracta del procés de càrrega d'una partida guardada del KenKen des d'un fitxer.

## Operativa

En executar el programa, l'usuari serà preguntat pel camí del fitxer de la partida guardada del KenKen. S'assumeix que el 
fitxer es troba dins la carpeta data/ i té l'extensió ".kenken_game", per tant, només cal especificar el nom del fitxer. 
En cas que es produeixi un error durant la càrrega de la partida guardada, es mostrarà un missatge d'error i es demanarà 
a l'usuari que introdueixi un camí de fitxer vàlid. Un cop la partida s'hagi carregat amb èxit, el joc de prova permetrà al jugador continuar jugant amb el KenKen carregat.