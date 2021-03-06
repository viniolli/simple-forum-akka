logLevel := Level.Warn

addSbtPlugin("io.github.davidmweber" % "flyway-sbt" % "5.2.0")
addSbtPlugin("org.lyranthe.sbt" % "partial-unification" % "1.1.2")
addSbtPlugin("com.geirsson" % "sbt-scalafmt" % "1.5.1")

resolvers += "Flyway" at "https://flywaydb.org/repo"