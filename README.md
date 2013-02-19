# Lift Components

An experiment in trying to make reusable, composable components (controls?) in Lift.  Not
a library -- just one way to use the stuff provided by Lift out of the box.

## Goals

* Reusable, self-contained snippets and templates
* Arbitrary nesting of components
* "Good code"
    * No global variables
    * Classes get their state through constructor params
* If there's a well-defined "Lift way" to do something, prefer it

## Terminology

Since I can't keep it straight.

* ''Snippet'': Scala classes, usually concerned with binding data to HTML templates
* ''Template'': A bit of HTML (some might say an "HTML snippet" -- but don't)
    * Snippets can be "invoked" from templates: `<lift:Foo.bar/>`
    * Templates are the starting point for execution (usually; "view first")

## Approach

* Two types of templates (I'm just making this up)
    * "Page template"
        * e.g. `index.html`, like a top-level template, something a client requests
        * gets its constructor args via sitemap magic (TBD describe this)
        * "invokes" snippets
        * embeds component templates
    * "Component template"
        * usually in `templates-hidden` directory
        * meant to be `lift:embed`ded by other templates
        * should not be invoking snippets (there are exceptions)
        * should strongly prefer CSS class attribute vs. id attribute (id is not reusable/composable)
* Snippets are roughly 1:1 with templates, but this is just my convention
* A snippet should have minimal knowledge of the template HTMKL layout
    * prefer using CSS classes rather than html structure in selectors
      * e.g. ".image-cell" instead of "table tr td")
* Snippets and templates have to "communicate" somehow -- use CSS class names for this
    * e.g Snippet code wants to bind an image url somewhere so the web page displays it
      * Could have unholy knowledge of a template: `"table tr td" #> theUrl`
      * Prefer instead a simple class name: `".image-cell" #> theUrl`
    * Minimizes (?) the shared knowledge between snippet and template
    * Not sure about how to namespace the CSS class names -- TBD

## Running

* SBT 0.12.2+
* `~; container:start; container:reload /`
