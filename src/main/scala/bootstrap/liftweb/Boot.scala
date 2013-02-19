package bootstrap.liftweb

import net.liftweb.http.{Html5Properties, LiftRules, Req}
import net.liftweb.sitemap.{Menu, SiteMap}
import org.tralfamadore.liftcomponents.snippet.ProductPage

class Boot {
  def boot() {
    // where to search snippet
    LiftRules.addToPackages("org.tralfamadore.liftcomponents")

    // Ah, sitemap
    LiftRules.setSiteMap(SiteMap(
      Menu.i("Home") / "index",
      ProductPage.menu / "product"
    ))

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) => new Html5Properties(r.userAgent))
  }
}
