# Mower Simulation - Conception orientée objet en Scala

> Simulation du déplacement de tondeuses automatiques sur une grille rectangulaire  
> Programmation orientée objet · Immutabilité · Pattern matching · Scala  


---

## Contexte

Implémentation en **Scala** d'un algorithme simulant le déplacement de tondeuses automatiques sur une grille rectangulaire.

**Objectifs :**
- Calculer correctement les positions finales après application d'une séquence d'instructions
- Architecture robuste et modulaire conforme aux principes OOP et fonctionnels Scala

> Encadrant : Marwan GUERNOUG

---

## Fonctionnement

### Format d'entrée (`input.txt`)

```
5 5          ← dimensions de la grille
1 2 N        ← position + orientation tondeuse 1
GAGAGAGAA    ← instructions tondeuse 1
3 3 E        ← position + orientation tondeuse 2
AADAADADDA   ← instructions tondeuse 2
```

### Instructions

| Instruction | Action |
|---|---|
| `G` | Rotation à gauche (90°) |
| `D` | Rotation à droite (90°) |
| `A` | Avancer d'une case |

### Exemple de sortie

```
Tondeuse 1 : 1 3 N
Tondeuse 2 : 5 1 E
```

---

## Architecture

```
src/main/scala/
├── model/
│   ├── Position.scala      # Coordonnées (x, y) + déplacement
│   ├── Orientation.scala   # sealed trait N/E/S/W + rotations
│   └── Mower.scala         # État complet (position + orientation)
├── service/
│   ├── Parser.scala        # Lecture & validation du fichier
│   └── Simulator.scala     # Application des instructions
└── Main.scala              # Point d'entrée
```

---

## Choix techniques

**Immutabilité** - Aucun `var`. Toutes les entités sont des case classes immuables :

```scala
def move: Mower = {
  val next = position.forward(orientation)
  if (next.x >= 0 && next.x <= maxX && next.y >= 0 && next.y <= maxY)
    copy(position = next)
  else this
}
```

**Pattern matching** - sealed trait exhaustif sur les orientations :

```scala
orientation match {
  case North => copy(orientation = West)
  case West  => copy(orientation = South)
  case South => copy(orientation = East)
  case East  => copy(orientation = North)
}
```

**Collections fonctionnelles** - `foldLeft` pour les instructions :

```scala
instructions.foldLeft(mower) { (currentMower, instruction) =>
  instruction match {
    case 'G' => currentMower.turnLeft
    case 'D' => currentMower.turnRight
    case 'A' => currentMower.move
  }
}
```

---

## Tests

| Scénario | Résultat |
|---|---|
| Cas nominal (grille 5×5, 2 tondeuses) | ✅ |
| Dépassement des limites | ✅ |
| Instruction invalide | ✅ |
| Fichier vide ou mal formaté | ✅ |

---

## Installation & lancement

```bash
git clone https://github.com/EKOURAOGO/Scala-data-analysis.git
cd Scala-data-analysis

sbt compile
sbt "run input.txt"
sbt test
```

---

## Stack technique

![Scala](https://img.shields.io/badge/Scala-DC322F?style=flat-square&logo=scala&logoColor=white)
![sbt](https://img.shields.io/badge/sbt-build-orange?style=flat-square)
![OOP](https://img.shields.io/badge/OOP-Immutabilité%20·%20Pattern%20matching-blue?style=flat-square)
![Functional](https://img.shields.io/badge/FP-foldLeft%20·%20map%20·%20Try-green?style=flat-square)

---

## Auteur

**KOURAOGO Emmanuel**   
Data Scientist & Data Analyst 

[![GitHub](https://img.shields.io/badge/GitHub-EKOURAOGO-181717?style=flat-square&logo=github)](https://github.com/EKOURAOGO)

*Encadrant : Marwan GUERNOUG · 2025-2026*
