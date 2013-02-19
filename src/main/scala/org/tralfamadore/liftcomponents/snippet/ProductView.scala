package org.tralfamadore.liftcomponents.snippet

import org.tralfamadore.liftcomponents.model.Product
import net.liftweb.util.Helpers._
import net.liftweb.util.CssSel
import xml.NodeSeq

/**
 * Snippet to render various views of a product.
 * @param p The product to view.
 */
class ProductView(p: Product) {

  // Render a full view of the product with images, etc.
  def full: CssSel =
    ".product:label *" #> p.label &
    ".product:brand *" #> p.brand.label &
    // If blurb is none, eat the entire binding element (avoid e.g. <blockquote></blockquote>)
    // This is screaming to be a utility function.
    (p.blurb match {
      case Some(blurb) => ".product:blurb *" #> blurb
      case None => ".product:blurb" #> Nil // note different selector
    }) &
    ".product:blurb *" #> p.blurb &
    ".product:image *" #> p.images.map { new ImageView(_).default }

  // render the product as a textual listing (for tables, etc)
  def textListing: CssSel =
    ".product:label *" #> p.label &
    ".product:brand *" #> p.brand.label

}
