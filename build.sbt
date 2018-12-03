name := "red-book"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= (Seq(
  "org.scalacheck" %% "scalacheck" % "1.14.0")
  ++ Seq(
  "org.typelevel" %% "cats-core",
  "org.typelevel" %% "cats-kernel",
  "org.typelevel" %% "cats-macros"
).map(_ % "1.5.0-RC1")).map(_ % "test")

scalacOptions += "-Ypartial-unification"