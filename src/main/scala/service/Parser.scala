package service

import scala.io.Source
import scala.util.Try
import model._

/**
 * Objet responsable de la lecture et de l’interprétation
 * du fichier d’entrée.
 *
 * Le format attendu du fichier est :
 *
 * Ligne 1 : dimensions de la grille (maxX maxY)
 * Ligne 2 : position initiale de la tondeuse 1 (x y orientation)
 * Ligne 3 : instructions de la tondeuse 1
 * Ligne 4 : position initiale de la tondeuse 2
 * Ligne 5 : instructions de la tondeuse 2
 * ...
 *
 * Cette classe transforme les données brutes du fichier
 * en objets métier exploitables par le Simulator.
 */
object Parser {

  /**
   * Analyse le fichier d’entrée et construit les tondeuses
   * ainsi que leurs instructions associées.
   *
   * @param path Chemin vers le fichier d’entrée
   * @return Un Try contenant :
   *         - la dimension maximale en X
   *         - la dimension maximale en Y
   *         - une liste de couples (Mower, instructions)
   *
   * En cas d’erreur (fichier vide, format incorrect, orientation invalide),
   * une Failure est retournée.
   */
  def parseFile(path: String): Try[(Int, Int, List[(Mower, String)])] = {

    Try {
      val source = Source.fromFile(path)

      try {

        val lines = source.getLines().toList

        // Vérification que le fichier n’est pas vide
        if (lines.isEmpty)
          throw new IllegalArgumentException("Le fichier est vide")

        // Lecture des dimensions de la grille
        val Array(maxX, maxY) =
          lines.head.split(" ").map(_.toInt)

        // Lecture des tondeuses par groupes de deux lignes
        val mowerData =
          lines.tail.grouped(2).toList.map {

            case List(positionLine, instructionLine) =>

              // Extraction des coordonnées et de l’orientation
              val Array(x, y, orientationStr) =
                positionLine.split(" ")

              val orientation =
                Orientation.fromString(orientationStr)
                  .getOrElse(
                    throw new IllegalArgumentException(
                      s"Orientation invalide : $orientationStr"
                    )
                  )

              // Création de l’objet métier Mower
              val mower =
                Mower(
                  Position(x.toInt, y.toInt),
                  orientation,
                  maxX,
                  maxY
                )

              (mower, instructionLine)

            case _ =>
              throw new IllegalArgumentException(
                "Format de fichier invalide"
              )
          }

        (maxX, maxY, mowerData)

      } finally {
        // Fermeture explicite de la ressource fichier
        source.close()
      }
    }
  }
}
