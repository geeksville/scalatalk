package com.theboxjelly.scalalab

import org.scalatra._
import scalate.ScalateSupport
import java.net._
import scala.xml._

class MyScalatraServlet extends LittleLabAppStack {

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }
  
  get("/reader") {
	val baseurl = params("url")
    //val baseurl = params.getOrElse("url", "http://hawaii.reddit.com")
	// Rss feeds seem to be in .rss, so add that to the url
    val url = new URL(baseurl + "/.rss")
    val xml = XML.load(url)
	val items = xml \\ "item"
	<html>
	  <title>A crummy RSS reader</title>
      <body>
	    <i>The feed at { url } says...</i>

	    { items.map { i =>
	        <p>
	          <div>{ (i \ "title").text }</div>
			  <!-- <a href={ (i \ "link").text }>(click me)</a> -->
	        </p>
          }
        }
	  </body>
	</html>
  }
}
