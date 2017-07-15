// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Play2war plugins release" at "http://repository-play-war.forge.cloudbees.com/release/"

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.2.2")

addSbtPlugin("com.github.play2war" % "play2-war-plugin" % "1.2-beta2")

addSbtPlugin("de.johoop" % "jacoco4sbt" % "2.1.5")

addSbtPlugin("com.sqality.scct" % "sbt-scct" % "0.3")

resolvers += Classpaths.typesafeResolver
 
resolvers += "scct-github-repository" at "http://mtkopone.github.com/scct/maven-repo"
