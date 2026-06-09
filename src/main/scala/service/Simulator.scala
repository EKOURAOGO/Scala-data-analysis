package service

import model._

/**
 * Objet responsable de l’exécution des instructions de déplacement
 * sur une tondeuse.
 *
 * Les instructions possibles sont :
 *  - 'G' : rotation à gauche
 *  - 'D' : rotation à droite
 *  - 'A' : avancer d’une case dans la direction actuelle
 *
 * Cette classe respecte le principe d’immuabilité :
 * chaque instruction retourne une nouvelle instance de Mower.
 *
 * @throws IllegalArgumentException si une instruction invalide est rencontrée
 */
object Simulator {

  /**
   * Exécute une séquence d’instructions sur une tondeuse.
   *
   * Le traitement est réalisé à l’aide de la fonction `foldLeft`,
   * permettant d’appliquer successivement chaque instruction
   * en accumulant l’état courant de la tondeuse.
   *
   * @param mower État initial de la tondeuse
   * @param instructions Chaîne de caractères contenant les instructions
   * @return La tondeuse après exécution complète des instructions
   */
  def executeInstructions(mower: Mower, instructions: String): Mower = {
    instructions.foldLeft(mower) {

      // Rotation à gauche
      case (currentMower, 'G') =>
        currentMower.turnLeft()

      // Rotation à droite
      case (currentMower, 'D') =>
        currentMower.turnRight()

      // Avancer d’une case
      case (currentMower, 'A') =>
        currentMower.move()

      // Gestion d’une instruction invalide
      case (_, invalid) =>
        throw new IllegalArgumentException(
          s"Instruction invalide : $invalid"
        )
    }
  }
}
