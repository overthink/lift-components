# Lift Components

An experiment in trying to make composable components (controls?) in Lift.

## Goals

* Reusable, self-contained snippets and templates
* Arbitrary nesting of components
* No global variables
* No magic casts
* If there's a "Lift way" to do something, prefer it

## Approach

* Each page has a "big" snippet (e.g. IndexPage, ProductPage, etc.)
* "Big" snippets are composed of "small" snippets like ProductView, ImageView, etc.
  * Composed, i.e. they instantiate other snippets within
* All snippets take constructor params like well behaved classes -- no magic
* Sitemap does the parse URL -> object -> pass to snippet constructor wiring (magic?)
* A snippet should have minimal knowledge of how the template is laid out (use class names rather than
  html structure in selector transforms: ".image-cell" instaed of "table tr td")
