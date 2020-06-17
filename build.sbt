val Name = "NetherTeleporter"
val Version = "1.0-SNAPSHOT"
val GroupId = "com.github.liamstar97"

name := Name
version := Version
organization := GroupId

scalaVersion := "2.13.0"
scalacOptions += "-language:implicitConversions"

packageOptions in (Compile, packageBin) +=
    Package.ManifestAttributes("Automatic-Module-Name" -> (GroupId + "." + Name.toLowerCase))

/* uncomment if you need more dependencies
resolvers += Resolver.mavenCentral
*/
resolvers += "jitpack" at "https://jitpack.io"
resolvers += "spigot-repo" at "https://hub.spigotmc.org/nexus/content/repositories/snapshots/"

libraryDependencies += "org.bukkit" % "bukkit" % "1.14.4-R0.1-SNAPSHOT" % "provided"
libraryDependencies += "com.github.Jannyboy11.ScalaPluginLoader" % "ScalaLoader" % "v0.13.6" % "provided"

/* uncomment if you need to shade dependencies - see https://github.com/sbt/sbt-assembly#shading
assemblyShadeRules in assembly := Seq(
    ShadeRule.rename("xyz.janboerman.guilib.**" -> "com.github.liamstar97.netherteleporter.guilib.@1").inAll,
)
*/
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
assemblyMergeStrategy in assembly := {
    case "plugin.yml"   => MergeStrategy.first /* always choose our own plugin.yml if we shade other plugins */
    case x              =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
}
assemblyJarName in assembly := Name + "-" + Version + ".jar"
