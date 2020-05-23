package ml.bmlzootown.namecolors;

import ml.bmlzootown.namecolors.commander.NameCommander;
import ml.bmlzootown.namecolors.util.ConfigManager;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

/**
 * Created by bmlzootown on 5/16/2017.
 */
public class NameColors extends JavaPlugin {
    public static Plugin pl;
    public static String prefix =  ChatColor.AQUA  + "[" + ChatColor.DARK_AQUA + "NameColors" + ChatColor.AQUA  + "] ";
    public static LuckPerms api;

    public static Logger log;

    public void onEnable() {
        pl = this;
        log = this.getLogger();

        //Config Setup
        File f = new File(getDataFolder(), "config.yml");
        if (!f.exists()) {
            if (ConfigManager.debug) getLogger().info("No config found -- generating!");
            ConfigManager.setConfigDefaults();
        }
        ConfigManager.initialize();

        //Setup LuckPerms
        api = LuckPermsProvider.get();

        //Register commands/events
        getCommand("namecolors").setExecutor(new NameCommander());
    }

    public void onDisable() {
        log.info("[NameColors] disabled...");
    }

    public static void reloadCf() {
        pl.reloadConfig();
    }

}
