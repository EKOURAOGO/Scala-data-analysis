package model

/**
 * Représente une tondeuse évoluant sur une grille rectangulaire.
 *
 * Une tondeuse est définie par :
 *  - sa position actuelle (coordonnées x, y)
 *  - son orientation (Nord, Est, Sud, Ouest)
 *  - les limites maximales de la grille (maxX, maxY)
 *
 * Le modèle est immuable : chaque action retourne une nouvelle
 * instance de Mower plutôt que de modifier l’objet courant.
 *
 * @param position Position actuelle de la tondeuse
 * @param orientation Orientation actuelle
 * @param maxX Limite maximale en abscisse de la grille
 * @param maxY Limite maximale en ordonnée de la grille
 */
case class Mower(
                  position: Position,
                  orientation: Orientation,
                  maxX: Int,
                  maxY: Int
                ) {

  /**
   * Effectue une rotation à gauche.
   *
   * @return Une nouvelle tondeuse avec orientation modifiée
   */
  def turnLeft(): Mower =
    copy(orientation = orientation.left)

  /**
   * Effectue une rotation à droite.
   *
   * @return Une nouvelle tondeuse avec orientation modifiée
   */
  def turnRight(): Mower =
    copy(orientation = orientation.right)

  /**
   * Fait avancer la tondeuse d’une case dans la direction actuelle.
   *
   * Si le déplacement dépasse les limites de la grille,
   * la tondeuse reste immobile.
   *
   * @return Une nouvelle tondeuse déplacée ou l’instance actuelle si sortie interdite
   */
  def move(): Mower = {
    val newPosition = position.moveForward(orientation)

    if (newPosition.x >= 0 && newPosition.x <= maxX &&
      newPosition.y >= 0 && newPosition.y <= maxY)
      copy(position = newPosition)
    else
      this
  }
}
