lazy val V = _root_.scalafix.sbt.BuildInfo

lazy val rulesCrossVersions = Seq(V.scala213, V.scala212)
lazy val scala3Version = "3.1.2"

inThisBuild(
  List(
    organization := "com.github.jtjeferreira",
    homepage := Some(url("https://github.com/com/example")),
    licenses := List(
      "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")
    ),
    developers := List(
      Developer(
        "example-username",
        "Example Full Name",
        "example@email.com",
        url("https://example.com")
      )
    ),
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision
  )
)

lazy val `pekko-scalafix` = (project in file("."))
  .aggregate(
    rules.projectRefs ++
      input.projectRefs ++
      output.projectRefs ++
      tests.projectRefs: _*
  )
  .settings(
    publish / skip := true
  )

lazy val rules = projectMatrix
  .settings(
    moduleName := "pekko-scalafix",
    libraryDependencies += "ch.epfl.scala" %% "scalafix-core" % V.scalafixVersion
  )
  .defaultAxes(VirtualAxis.jvm)
  .jvmPlatform(rulesCrossVersions)

lazy val input = projectMatrix
  .settings(
    publish / skip := true,
    libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.6.20",
    libraryDependencies += "com.typesafe.akka" %% "akka-serialization-jackson" % "2.6.20",
    libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" % "2.6.20",
  )
  .defaultAxes(VirtualAxis.jvm)
  .jvmPlatform(scalaVersions = rulesCrossVersions :+ scala3Version)

lazy val output = projectMatrix
  .settings(
    publish / skip := true,
    resolvers += "apache-snapshots" at s"https://repository.apache.org/content/repositories/snapshots/",
    resolvers ++= Resolver.sonatypeOssRepos("snapshots"),
    libraryDependencies += "org.apache.pekko" %% "pekko-actor" % "0.0.0+26623-85c2a469-SNAPSHOT",
    libraryDependencies += "org.apache.pekko" %% "pekko-serialization-jackson" % "0.0.0+26623-85c2a469-SNAPSHOT",
    libraryDependencies += "org.apache.pekko" %% "pekko-slf4j" % "0.0.0+26623-85c2a469-SNAPSHOT",
  )
  .defaultAxes(VirtualAxis.jvm)
  .jvmPlatform(scalaVersions = rulesCrossVersions :+ scala3Version)

lazy val testsAggregate = Project("tests", file("target/testsAggregate"))
  .aggregate(tests.projectRefs: _*)
  .settings(
    publish / skip := true
  )

lazy val tests = projectMatrix
  .settings(
    publish / skip := true,
    scalafixTestkitOutputSourceDirectories :=
      TargetAxis
        .resolve(output, Compile / unmanagedSourceDirectories)
        .value,
    scalafixTestkitInputSourceDirectories :=
      TargetAxis
        .resolve(input, Compile / unmanagedSourceDirectories)
        .value,
    scalafixTestkitInputClasspath :=
      TargetAxis.resolve(input, Compile / fullClasspath).value,
    scalafixTestkitInputScalacOptions :=
      TargetAxis.resolve(input, Compile / scalacOptions).value,
    scalafixTestkitInputScalaVersion :=
      TargetAxis.resolve(input, Compile / scalaVersion).value
  )
  .defaultAxes(
    rulesCrossVersions.map(VirtualAxis.scalaABIVersion) :+ VirtualAxis.jvm: _*
  )
  .jvmPlatform(
    scalaVersions = Seq(V.scala212),
    axisValues = Seq(TargetAxis(scala3Version)),
    settings = Seq()
  )
  .jvmPlatform(
    scalaVersions = Seq(V.scala213),
    axisValues = Seq(TargetAxis(V.scala213)),
    settings = Seq()
  )
  .jvmPlatform(
    scalaVersions = Seq(V.scala212),
    axisValues = Seq(TargetAxis(V.scala212)),
    settings = Seq()
  )
  .dependsOn(rules)
  .enablePlugins(ScalafixTestkitPlugin)
