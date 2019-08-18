package com.hastybox.dradrizz.hub.storage

import scala.language.higherKinds

/**
 * Stores Media information
 */
trait MediaStorage[F[_], Media, MediaId] {

  def save(media: Media): F[Unit]

  def get(id: MediaId): F[Option[Media]]

  def remove(id: MediaId): F[Unit]

}
