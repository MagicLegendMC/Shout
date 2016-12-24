package net.magiclegend.net;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * Created by Simon on 22/12/2016.
 *
 * @author Simo_Xz
 * @author Camouflage10
 * @since 1.0
 */
public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Shout Has Been Enabled!");
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            getLogger().info("config.yml not found, creating!");
            saveDefaultConfig();
        } else {
            getLogger().info("config.yml found,loading!");
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("Shout Has Been Disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if ((sender.hasPermission("shout.use")) && (command.getName().equalsIgnoreCase("shout"))) {
            if (args.length == 0) {
                sender.sendMessage(colorize("&e&l(&b&lShout&e&l) &4Usage: /shout <message>"));
                return true;
            }

            StringBuilder str = new StringBuilder();
            for (String arg : args)
                str.append(arg).append(" ");


            String prefix = this.getConfig().getString("prefix");
            Bukkit.broadcastMessage(colorize(prefix + " " + str.toString().trim()));

            return true;
        }

        if ((sender.hasPermission("shout.reload")) && (command.getName().equalsIgnoreCase("shoutreload"))) {
            sender.sendMessage((ChatColor.GREEN + "[Shout] Configs Reloaded!"));
            this.reloadConfig();

            return true;
        }

        // Must be last line of code in this method...
        return true;
    }

    private String colorize(String todo) {
        return ChatColor.translateAlternateColorCodes('&', todo);
    }
}
