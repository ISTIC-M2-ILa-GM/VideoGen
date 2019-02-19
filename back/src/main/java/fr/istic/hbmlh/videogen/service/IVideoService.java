package fr.istic.hbmlh.videogen.service;

import fr.istic.hbmlh.videogen.exception.VideoGenException;

import java.util.List;

public interface IVideoService {

  String VIDEOGEN_ROOT_PATH = "videogen_dir";
  String VIDEO_CONCAT_PATH = "/tmp/videogen/";
  String GIF_CONCAT_PATH = VIDEO_CONCAT_PATH + "gif/";
  String VIDEO_FORMAT=".webm";

  /**
   * Concatène une liste de videos
   *
   * @param videosNames -> une liste de nom de videos
   * @return le nom de la vidéo générer
   */
  String concatVideos(List<String> videosNames) throws VideoGenException;

  /**
   * Génère un gif d'une vidéo et retourne le nom du gif
   *
   * @param videoName le nom de la video
   * @return sont nom
   * @throws VideoGenException si la video n'existe pas
   */
  String generateGif(final String videoName) throws VideoGenException;


}
