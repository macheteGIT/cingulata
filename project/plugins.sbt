logLevel := Level.Warn

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.4.6")

addSbtPlugin("com.heroku" % "sbt-heroku" % "1.0.0")

herokuAppName in Compile := "obscure-sierra-7788"