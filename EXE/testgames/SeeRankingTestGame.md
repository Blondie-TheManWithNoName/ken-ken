# SeeRankingTestGame

## Objecte de la prova

L'objectiu d'aquest joc de prova és visualitzar el rànquing de puntuacions guardades anteriorment, permetent als jugadors veure les seves posicions i les seves puntuacions.

## Drivers

- `SeeRankingDriver`: Controla el procés de visualització del rànquing de puntuacions.

## Fitxers de dades necessaris

Necessitem l'arxiu `data/scores.kenken_scores`

## Valors estudiats i efectes estudiats

Aquest joc de prova mostra la visualització del rànquing de puntuacions, permetent als jugadors veure les seves posicions i les seves puntuacions guardades anteriorment.

## Operativa

Per a modificar el ranking cal canviar l'arxiu `data/scores.kenken_scores` seguit el format indicat:
```
username1 score1
username2 score2
username3 score3
```

El nom d'usuari no pot contenir espais i ha d'anar separat de la puntuació per un espai. En executar el programa, es mostrarà el rànquing de puntuacions guardades anteriorment. Si no hi ha cap puntuació per mostrar, es mostrarà un missatge indicant que no hi ha puntuacions disponibles. En cas contrari, es mostrarà el rànquing amb el nom de l'usuari i la seva puntuació corresponent.