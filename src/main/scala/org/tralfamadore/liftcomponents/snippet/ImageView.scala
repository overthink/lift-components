package org.tralfamadore.liftcomponents.snippet

import net.liftweb.util.Helpers._
import org.tralfamadore.liftcomponents.model.Image

class ImageView(img: Image) {
  def default =
    "img [src]" #> img.url.toExternalForm
}
