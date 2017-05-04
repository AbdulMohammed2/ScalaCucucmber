package uk.anatwine.automation.base

/**
  * Created by SUMOHAMM on 04/05/2017.
  */
object DemoMail {
  def main(args: Array[String]): Unit = {

    import mail._

    send.a(Mail (
      from = ("mas222r@gmail.com"),
      to = "suboor.mohammed@capgemini.com",
      cc = Seq("rangareddy.pannala@capgemini.com","husnabegum.iiitb@gmail.com"),
      subject = "Import stuff",
      message = "Dear Boss..."
    ))

    send a Mail (
      from = "mas222r@gmail.com",
      to = Seq("suboor.mohammed@capgemini.com", "husnabegum.iiitb@gmail.com"),
      subject = "Our New Strategy (tm)",
      message = "Please find attach the latest strategy document.",
      richMessage = "Here's the <blink>latest</blink> <strong>Strategy</strong>..."
    )
  }
}
