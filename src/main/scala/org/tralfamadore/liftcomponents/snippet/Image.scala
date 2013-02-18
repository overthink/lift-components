package org.tralfamadore.liftcomponents.snippet

import net.liftweb.util.Helpers._
import org.tralfamadore.liftcomponents.model.{Image => ImageModel}

class Image(img: ImageModel) {
  def render =
    "img [src]" #> img.url.toExternalForm
}
