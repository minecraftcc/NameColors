package ml.bmlzootown.commander;

import ml.bmlzootown.NameColors;
import ml.bmlzootown.util.ConfigManager;
import ml.bmlzootown.util.LuckPermsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class NameCommander implements CommandExecutor {
    Logger log = NameColors.log;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            if (args[0].equalsIgnoreCase("reload")) {
                sender.sendMessage(NameColors.prefix + "Config reloaded!");
                if (ConfigManager.debug) NameColors.pl.getLogger().info(NameColors.prefix + "Config reloaded...");
                ConfigManager.reload();
            }
        } else if (sender instanceof Player) {
            Player p = (Player) sender;
            List<String> groups = LuckPermsManager.getPlayerGroups(p);
            Bukkit.getLogger().info(p.getDisplayName() + ": " + groups.toString());
            if (args.length != 1) {
                //Output Help
                sender.sendMessage(NameColors.prefix + "Commands: ");
                sender.sendMessage( ChatColor.AQUA + "/namecolors [rank]" + ChatColor.DARK_AQUA + " Changes name color");
                sender.sendMessage( ChatColor.AQUA + "/namecolors list" + ChatColor.DARK_AQUA + " Lists ranks and their colors");
                sender.sendMessage( ChatColor.AQUA + "/namecolors reset" + ChatColor.DARK_AQUA + " Resets name color");
            } else {
                Object[] grps = groups.toArray();
                if (args[0].equalsIgnoreCase("reset")) {
                    if (groups.isEmpty()) return false;
                    String group = LuckPermsManager.getPlayerMainGroup(p);
                    String prefix = LuckPermsManager.getGroupPrefix(group);
                    LuckPermsManager.setUserPrefix(p, null);
                    LuckPermsManager.setUserPrefix(p, prefix);
                    if (prefix == null) {
                        p.sendMessage(NameColors.prefix + "Setting color to " + group);
                    } else {
                        p.sendMessage(NameColors.prefix + "Setting color to " + ChatColor.translateAlternateColorCodes('&', prefix) + group);
                    }
                } else if (args[0].equalsIgnoreCase("list")) {
                    p.sendMessage(NameColors.prefix + "Ranks:");
                    for (String group : groups) {
                        String prefix = LuckPermsManager.getGroupPrefix(group);
                        if (prefix == null) {
                            p.sendMessage("- " + group);
                        } else {
                            p.sendMessage("- " + ChatColor.translateAlternateColorCodes('&', prefix) + group);
                        }
                    }
                } else if (args[0].equalsIgnoreCase("panurple") && p.hasPermission("namecolors.panurple")) {
                    LuckPermsManager.setUserPrefix(p, null);
                    LuckPermsManager.setUserPrefix(p, "&5");
                    p.sendMessage(NameColors.prefix + "Setting color to " + ChatColor.translateAlternateColorCodes('&', "&5") + "panurple");
                } else {
                    for (int i = grps.length-1; i >= 0; i--) {
                        String group = (String)grps[i];
                        if (args[0].equalsIgnoreCase(group)) {
                            String prefix = LuckPermsManager.getGroupPrefix(group);
                            LuckPermsManager.setUserPrefix(p, null);
                            LuckPermsManager.setUserPrefix(p, prefix);
                            if (prefix == null) {
                                p.sendMessage(NameColors.prefix + "Setting color to " + group);
                            } else {
                                p.sendMessage(NameColors.prefix + "Setting color to " + ChatColor.translateAlternateColorCodes('&', prefix) + group);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
