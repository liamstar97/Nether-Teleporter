package com.github.liamstar97.netherteleporter

import org.bukkit.permissions.PermissionDefault

import xyz.janboerman.scalaloader.plugin.ScalaPluginDescription.{Command => SPCommand, Permission => SPPermission}
import xyz.janboerman.scalaloader.plugin.{ScalaPlugin, ScalaPluginDescription}
import xyz.janboerman.scalaloader.plugin.description.{Scala, ScalaVersion}

@Scala(version = ScalaVersion.v2_13_0) //TODO change according to the scala version.
object NetherTeleporterPlugin
    extends ScalaPlugin(new ScalaPluginDescription("NetherTeleporter", "1.0-SNAPSHOT")
        .description("This plugin banishes you to the nether")
        .addCommand(new SPCommand("home") permission "demo.home" usage "/home set|tp")
        .permissions(new SPPermission("demo.home") permissionDefault PermissionDefault.TRUE)) {

    override def onEnable(): Unit = {
        getServer.getPluginManager.registerEvents(PlayerJoinListener, this)
        getCommand("home").setExecutor(HomeExecutor)
    }

}
