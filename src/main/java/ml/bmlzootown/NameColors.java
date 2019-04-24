package ml.bmlzootown;

import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.LuckPermsApi;
import ml.bmlzootown.commander.NameCommander;
import ml.bmlzootown.util.ConfigManager;
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
    public static String prefix =  ChatColor.AQUA  + "[" + ChatColor.DARK_AQUA + "NameColor" + ChatColor.AQUA  + "] ";
    public static LuckPermsApi api;

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
        api = LuckPerms.getApi();

        //Register commands/events
        getCommand("namecolor").setExecutor(new NameCommander());
    }

    public void onDisable() {
        log.info("[NameColors] disabled...");
    }

    public static void reloadCf() {
        pl.reloadConfig();
    }

}
