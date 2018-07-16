package ml.bmlzootown;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;


import java.util.List;
import java.util.logging.Logger;

/**
 * Created by bmlzootown on 5/16/2017.
 */
public class NameColors extends JavaPlugin {
    private static final Logger log = Logger.getLogger("Minecraft");

    private static String prefix =  ChatColor.AQUA  + "[" + ChatColor.DARK_AQUA + "NameColor" + ChatColor.AQUA  + "] ";

    public void onEnable() {
        log.info("[NameColors] enabled...");
    }

    public void onDisable() {
        log.info("[NameColors] disabled...");
    }

    public boolean onCommand(CommandSender sender, Command command, String cmdLabel, String[] args) {
        PermissionManager pm = PermissionsEx.getPermissionManager();
        if (command.getName().equalsIgnoreCase("namecolor")) {
            if (args.length == 0 || args.length > 1) {
                sender.sendMessage( prefix + "/namecolor [rank]" + ChatColor.DARK_AQUA + " Changes name color");
                sender.sendMessage( prefix + "/namecolor reset" + ChatColor.DARK_AQUA + " Resets name color");
            }
            if (args.length == 1) {
                if (sender instanceof Player) {
                    Player p = ((Player) sender).getPlayer();
                    PermissionUser player = PermissionsEx.getUser(p);
                    if (args[0].equalsIgnoreCase("reset")) {
                        List<String> groups = player.getParentIdentifiers();
                        if (groups.size() == 1) {
                            PermissionGroup group = pm.getGroup(groups.get(0));
                            player.setPrefix("", null);
                            sender.sendMessage(prefix + "Color reset to that of rank " + ChatColor.DARK_AQUA + group.getName() + ChatColor.AQUA + ".");
                        } else {
                            sender.sendMessage(prefix + ChatColor.RED + "Can't reset your name color! Poke bml and tell him that you have too many ranks!");
                        }
                        return true;
                    } else {
                        if (pm.getGroupNames().contains(args[0])) {
                            if (pm.has(p, "namecolors." + args[0])) {
                                player.setPrefix(pm.getGroup(args[0]).getPrefix(), null);
                                sender.sendMessage(prefix + "Color changed to that of rank " + ChatColor.DARK_AQUA + args[0] + ChatColor.AQUA + ".");
                            } else {
                                sender.sendMessage( prefix + ChatColor.RED + "You have either entered the rank name incorrectly, the rank you specified does not exist, or you don't have permission to use this rank!");
                            }
                        } else if (args[0].equalsIgnoreCase("guest")) {
                            if (pm.has(p, "namecolors.guest")) {
                                player.setPrefix(pm.getGroup("Default").getPrefix(), null);
                                sender.sendMessage(prefix + "Color changed to that of rank " + ChatColor.DARK_AQUA + args[0] + ChatColor.AQUA + ".");
                            } else {
                                sender.sendMessage( prefix + ChatColor.RED + "You have either entered the rank name incorrectly, the rank you specified does not exist, or you don't have permission to use this rank!");
                            }
                        } else if (args[0].equalsIgnoreCase("panurple")) {
                            if (pm.has(p, "namecolors.panurple")) {
                                player.setPrefix("&5", null);
                                sender.sendMessage(prefix + "Color changed to that of rank " + ChatColor.DARK_AQUA + args[0] + ChatColor.AQUA + ".");
                            } else {
                                sender.sendMessage( prefix + ChatColor.RED + "You have either entered the rank name incorrectly, the rank you specified does not exist, or you don't have permission to use this rank!");
                            }
                        }  else if (args[0].equalsIgnoreCase("vip+")) {
                            if (pm.has(p, "namecolors.vipplus")) {
                                player.setPrefix(pm.getGroup("vipgold").getPrefix(), null);
                                sender.sendMessage(prefix + "Color changed to that of rank " + ChatColor.DARK_AQUA + args[0] + ChatColor.AQUA + ".");
                            } else {
                                sender.sendMessage( prefix + ChatColor.RED + "You have either entered the rank name incorrectly, the rank you specified does not exist, or you don't have permission to use this rank!");
                            }
                        } else {
                            sender.sendMessage( prefix + ChatColor.RED + "You have either entered the rank name incorrectly, the rank you specified does not exist, or you don't have permission to use this rank!");
                        }
                    }
                } else {
                    sender.sendMessage(prefix + ChatColor.RED + "Go home, console, you've had too much soda...");
                }
            }
            return true;
        } else {
            return false;
        }
    }

}
