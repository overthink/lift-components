package org.tralfamadore.liftcomponents.snippet

import org.tralfamadore.liftcomponents.model.Product
import net.liftweb.util.Helpers._
import net.liftweb.util.CssSel

class ProductDefault(p: Product) {

  // Render a default view of Product p.
  def render: CssSel =
    ".product:label *" #> p.label &
    ".product:brand *" #> p.brand.label &
    "ul *" #> p.images.map { img => new Image(img).render }
}
