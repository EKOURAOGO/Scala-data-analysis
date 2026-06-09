import service._
import scala.util.{Success, Failure}

/**
 * Point d’entrée principal de l’application.
 *
 * Cet objet est responsable de :
 *  - Lire le fichier d’entrée
 *  - Déléguer l’interprétation du fichier au Parser
 *  - Exécuter les instructions via le Simulator
 *  - Afficher la position finale de chaque tondeuse
 *
 * L’architecture respecte une séparation claire des responsabilités :
 *  - Parser : lecture et validation du fichier
 *  - Simulator : exécution des instructions
 *  - Main : orchestration globale
 */
object Main extends App {

  /** Chemin vers le fichier d’entrée contenant la configuration */
  private val filePath = "input.txt"

  /**
   * Analyse le fichier et traite chaque tondeuse.
   *
   * En cas de succès :
   *  - Chaque tondeuse est exécutée
   *  - Son état final est affiché
   *
   * En cas d’erreur :
   *  - Le message d’erreur est affiché
   */
  Parser.parseFile(filePath) match {

    case Success((_, _, mowerData)) =>

      // Parcours de chaque tondeuse avec son index
      mowerData.zipWithIndex.foreach {
        case ((mower, instructions), index) =>

          /**
           * Exécution des instructions pour la tondeuse courante.
           * Le modèle étant immuable, un nouvel objet Mower est retourné.
           */
          val finalMower =
            Simulator.executeInstructions(mower, instructions)

          // Affichage du résultat final selon le format demandé
          println(
            s"Tondeuse ${index + 1} : " +
              s"${finalMower.position.x} " +
              s"${finalMower.position.y} " +
              s"${finalMower.orientation.toShortString}"
          )
      }

    case Failure(exception) =>
      // Gestion des erreurs lors de la lecture/validation du fichier
      println(s"Erreur : ${exception.getMessage}")
  }
}
