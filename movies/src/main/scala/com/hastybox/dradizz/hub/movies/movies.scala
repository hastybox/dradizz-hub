package com.hastybox.dradizz.hub.movies

import com.hastybox.dradizz.hub.movies.MovieIds.{ImdbId, MovieDbId, TraktId}

/**
  * TODO: Comment
  *
  * @author Patrick Sy (patrick.sy@get-it.us)
  */
case class Movie()

case class MovieIds(
                   imdb: ImdbId,
                   trakt: TraktId,
                   movieDb: MovieDbId
                   )

object MovieIds {

  sealed trait MovieId

  case class ImdbId() extends MovieId

  case class TraktId() extends MovieId

  case class MovieDbId() extends MovieId

}
