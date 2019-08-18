package com.hastybox.dradrizz.hub.source

import com.hastybox.dradrizz.hub.source.MediaSource.MediaSourceResult

import scala.language.higherKinds

/**
 * Media source that is able to provide Media information
 */
trait MediaSource[F[_], Media, MediaId] {

  /**
   * find Media by a partial name
   *
   * @param name a partial name
   * @return List of possible results
   */
  def find(name: String): F[MediaSourceResult[List[Media]]]

  /**
   * find a Media by its unique id
   *
   * @param id a unique id
   * @return a media
   */
  def find(id: MediaId): F[MediaSourceResult[Option[Media]]]

}

object MediaSource {

  type MediaSourceResult[A] = Either[MediaSourceError, A]


  case class MediaSourceError(msg: String) extends RuntimeException(msg)

}
