package fr.istic.hbmlh.videogen.service

interface IVideoGenParser {

  /**
   * Génère une liste de vidéos (path + nom)
   */
  fun generateRandomConfiguration(): List<String>

}
