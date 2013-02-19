package org.tralfamadore.liftcomponents.snippet

import org.tralfamadore.liftcomponents.model.{Data, Product}
import net.liftweb.util.CssSel
import net.liftweb.util.Helpers._
import net.liftweb.sitemap.Menu
import net.liftweb.sitemap.Menu.PreParamMenu

/**
 * Render a particular product.
 */
class ProductPage(p: Product) {
  def render: CssSel = (new ProductView(p)).full
}

object ProductPage {

//  private def parser(s: String): Box[Product] =
//    tryo(Data.product(s.toInt).get) ?~ "Could not find product with id %d".format(s)
//
//  private def encoder(p: Product): String = p.id.value.toString

  val menu: PreParamMenu[Product] =
    Menu.param[Product]("useless?", "useless?",
      ((s: String) => tryo(Data.product(s.toInt).get) ?~ "Could not find product with id %d".format(s)),
      ((p: Product) => p.id.value.toString))

}
