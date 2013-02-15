package org.tralfamadore.liftcomponents.model

import java.net.URL

case class Id[T](value: Long)
case class Image(url: URL)
case class Brand(id: Id[Brand], label: String, image: Image)
case class Product(id: Id[Product], label: String, brand: Brand, images: List[Image])

// Fake data store.
object Data {

  private val brandsById: Map[Long, Brand] =
    List(
      (1, "Nikon", "http://news.doddleme.com/wp-content/uploads/2012/08/nikon-logo.jpg"),
      (2, "Canon", "http://www.steves-digicams.com/Canon-logo-2012.png"),
      (3, "Sony", "http://cloudtimes.org/wp-content/uploads/2011/05/sony_logo.jpg"))
        .map { case (id, label, url) => Brand(Id(id), label, Image(new URL(url))) }
        .map { b => (b.id.value, b) }
        .toMap

  def brands: List[Brand] = brandsById.values.toList.sortBy(_.id.value)
  def brand(id: Long): Option[Brand] = brandsById.get(id)

  private val productsById: Map[Long, Product] =
    List(
      (1, "D4", brand(1),
        List("http://s3.sortable-static.com/img/cameras/-9223372036854763333/b994bb4b1855a3c04668014814c48026-300-286/Nikon-D4-front.jpg",
             "http://s3.sortable-static.com/img/cameras/-9223372036854763333/bf6bb0571cb2dd907c9878a78610052a-292-300/Nikon-D4-back.jpg")),
      (2, "EOS 1D X", brand(2),
        List("http://s0.sortable-static.com/img/cameras/-9223372036854774616/e28d8cb5a416d8d82a6f6315a479efab-380-400/Canon-EOS-1D-X-front.jpg",
             "http://s2.sortable-static.com/img/cameras/-9223372036854774616/6ca23442284abd389150efd9efccb73e-286-300/Canon-EOS-1D-X-back.jpg")))
        .map { case (id, label, Some(brand), urls) =>
          Product(Id(id), label, brand, urls.map(url => Image(new URL(url))))
        }
        .map { p => (p.id.value, p) }
        .toMap

  def products: List[Product] = productsById.values.toList.sortBy(_.id.value)
  def product(id: Long): Option[Product] = productsById.get(id)

}