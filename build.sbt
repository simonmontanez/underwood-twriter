import com.trueaccord.scalapb.compiler.Version.{grpcJavaVersion, scalapbVersion}
import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "co.bagman",
      scalaVersion := "2.12.3",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Bagman",
    libraryDependencies += scalaTest % Test
  )

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)

libraryDependencies  ++= Seq(
  "com.trueaccord.scalapb" %% "scalapb-runtime"       % scalapbVersion % "protobuf",
  "com.trueaccord.scalapb" %% "scalapb-runtime-grpc"  % scalapbVersion,
  "io.grpc"                 % "grpc-netty"            % grpcJavaVersion
)

