package org.tralfamadore.liftcomponents.snippet

import org.tralfamadore.liftcomponents.model.Data
import net.liftweb.util.Helpers._
import net.liftweb.util.CssSel

class Index {
  def render: CssSel =
    "*" #> Data.products.take(3).map { p =>
      new ProductDefault(p).render
    }
}
