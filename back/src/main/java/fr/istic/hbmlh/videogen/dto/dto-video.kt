package fr.istic.hbmlh.videogen.dto

import fr.istic.hbmlh.videogen.model.MediaType
import fr.istic.hbmlh.videogen.model.ParsedMedia

data class Video(val name: String, val optional: Boolean = false)

fun toConfigurator(parse: MutableList<MutableList<ParsedMedia>>): List<List<Video>> {

  return parse.map { MandaOptioAlter ->

    MandaOptioAlter.map {

      Video(it.fileName, it.type == MediaType.OPTIONAL)

    }.toList()

  }.toList()
}
