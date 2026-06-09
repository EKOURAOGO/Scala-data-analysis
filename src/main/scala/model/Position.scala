package model

/**
 * Représente la position d’une tondeuse sur la grille.
 *
 * Une position est définie par deux coordonnées entières :
 *  - x : abscisse
 *  - y : ordonnée
 *
 * Le modèle est immuable : toute modification retourne
 * une nouvelle instance de Position.
 *
 * @param x Coordonnée horizontale
 * @param y Coordonnée verticale
 */
case class Position(x: Int, y: Int) {

  /**
   * Calcule la nouvelle position après un déplacement vers l’avant
   * en fonction de l’orientation actuelle.
   *
   * @param orientation Orientation de la tondeuse
   * @return Nouvelle position après déplacement
   */
  def moveForward(orientation: Orientation): Position =
    orientation match {

      // Déplacement vers le nord (augmentation de y)
      case North => copy(y = y + 1)

      // Déplacement vers l’est (augmentation de x)
      case East  => copy(x = x + 1)

      // Déplacement vers le sud (diminution de y)
      case South => copy(y = y - 1)

      // Déplacement vers l’ouest (diminution de x)
      case West  => copy(x = x - 1)
    }
}
