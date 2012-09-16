name := "MongoDate"

version := "1.0"

scalaVersion := "2.9.1"

resolvers += Classpaths.typesafeResolver

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "repo.novus rels" at "http://repo.novus.com/releases/"

resolvers += "repo.novus snaps" at "http://repo.novus.com/snapshots/"

resolvers += "Sonatype Snapshot repo" at "http://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies +=  "com.novus" %% "salat" % "1.9.0"

libraryDependencies +=   "org.specs2" %% "specs2" % "1.12.1" % "test"

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.1.0")

