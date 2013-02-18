package org.tralfamadore.liftcomponents.snippet

import org.tralfamadore.liftcomponents.model.Data
import net.liftweb.util.Helpers._
import net.liftweb.util.CssSel
import net.liftweb.http.S

/**
 * Snippet for the main page of our site.
 */
class Index {
  def render: CssSel = {
    val numProducts = S.attr("num-products").flatMap(n => tryo(n.toInt)).openOr(3)
    "tr *" #> Data.products.take(numProducts).map { p =>
      new ProductView(p).full
    }
  }
}
