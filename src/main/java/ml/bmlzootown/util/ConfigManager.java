package ml.bmlzootown.util;

import ml.bmlzootown.NameColors;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigManager {
    private static Plugin pl = NameColors.pl;
    private static FileConfiguration cf;
    public static boolean debug;

    public static void setConfigDefaults() {
        FileConfiguration config = pl.getConfig();
        config.addDefault("debug", false);
        config.options().copyDefaults(true);
        pl.saveConfig();
    }

    public static void initialize() {
        cf = pl.getConfig();
        debug = debug();
    }

    public static void reload() {
        NameColors.reloadCf();
        initialize();
    }

    private static boolean debug() { return cf.getBoolean("debug"); }
}
