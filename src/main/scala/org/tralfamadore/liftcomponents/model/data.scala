package org.tralfamadore.liftcomponents.model

import java.net.URL

case class Id[T](value: Long) // since we're experimenting... phantom types for IDs

case class Image(url: URL)

case class Brand(id: Id[Brand], label: String, image: Image)

case class Product(
    id: Id[Product],
    label: String,
    brand: Brand,
    blurb: Option[String],
    images: List[Image])

// Fake data store.
object Data {

  private val brandsById: Map[Long, Brand] =
    List(
      (1, "Nikon", "http://news.doddleme.com/wp-content/uploads/2012/08/nikon-logo.jpg"),
      (2, "Canon", "http://www.steves-digicams.com/Canon-logo-2012.png"),
      (3, "Sony", "http://cloudtimes.org/wp-content/uploads/2011/05/sony_logo.jpg"))
        .collect { case (id, label, url) => Brand(Id(id), label, Image(new URL(url))) }
        .map { b => (b.id.value, b) }
        .toMap

  val brands: List[Brand] = brandsById.values.toList.sortBy(_.id.value)
  def brand(id: Long): Option[Brand] = brandsById.get(id)

  private val productsById: Map[Long, Product] =
    List(
      (1, "D4", brand(1), Some("Detail from the new sensor is excellent, especially at the lower ISOs, while tonal gradation, colour accuracy when shooting Raw, white balance accuracy and ISO performance are all highly impressive too."),
        List("http://s3.sortable-static.com/img/cameras/-9223372036854763333/b994bb4b1855a3c04668014814c48026-300-286/Nikon-D4-front.jpg",
             "http://s3.sortable-static.com/img/cameras/-9223372036854763333/bf6bb0571cb2dd907c9878a78610052a-292-300/Nikon-D4-back.jpg")),
      (2, "EOS 1D X", brand(2), Some("Just as impressive is the way the 1D X’s sensor manages to resolve detail at high ISOs, particularly up to ISO 25,600."),
        List("http://s0.sortable-static.com/img/cameras/-9223372036854774616/e28d8cb5a416d8d82a6f6315a479efab-380-400/Canon-EOS-1D-X-front.jpg",
             "http://s2.sortable-static.com/img/cameras/-9223372036854774616/6ca23442284abd389150efd9efccb73e-286-300/Canon-EOS-1D-X-back.jpg")),
      (3, "EOS 6D", brand(2), Some("It's solidly constructed yet lightweight for a full frame model, and feels great in your hand."),
        List("http://s0.sortable-static.com/img/cameras/-9223372036854734938/48b216129c5bbc8ab5f505d2d2fbad26-400-337/Canon-EOS-6D-front.jpg")),
      (4, "SLT A57", brand(3), None,
        List("http://s1.sortable-static.com/img/cameras/-9223372036854770052/67ed7cd8f055d9e2525a8d9f7b5620f9-400-300/Sony-SLT-A57-front.jpg",
             "http://s1.sortable-static.com/img/cameras/-9223372036854770052/2b71b4da2459ce0424ded816cc0027f9-300-226/Sony-SLT-A57-back.jpg")),
      (5, "SLT-A99", brand(3), Some("Sony is certainly confident about it, suggesting that it's close enough to the quality of a high-end optical viewfinder that the advantages (the ability to preview exposure and white balance, or to gain-up for working in low light), outweigh the areas in which it isn't as good."),
        List("http://s1.sortable-static.com/img/cameras/-9223372036854733395/744a17646759be2e810c01f99e0177cb-400-303/Sony-SLT-A99-front.jpg",
             "http://s0.sortable-static.com/img/cameras/-9223372036854733395/903af9025e293a72aae796baf4cbfcfd-300-292/Sony-SLT-A99-angle.jpg",
             "http://s3.sortable-static.com/img/cameras/-9223372036854733395/cdb3b88a4afe1b6b018ff9a1029224a3-300-216/Sony-SLT-A99-back.jpg"))
    )
      .collect { case (id, label, Some(brand), blurb, urls) =>
        Product(Id(id), label, brand, blurb, urls.map(url => Image(new URL(url))))
      }
      .map { p => (p.id.value, p) }
      .toMap

  val products: List[Product] = productsById.values.toList.sortBy(_.id.value)
  def product(id: Long): Option[Product] = productsById.get(id)

}