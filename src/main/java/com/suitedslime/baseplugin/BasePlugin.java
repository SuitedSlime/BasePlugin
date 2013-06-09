/**
 * *****************************************************************************
 * BasePlugin
 *
 * BasePlugin
 *
 * @author SuitedSlime
 * @licence Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * *****************************************************************************
 */

package com.suitedslime.baseplugin;

import com.suitedslime.baseplugin.command.CommandManager;
import com.suitedslime.baseplugin.config.PluginConfig;
import com.suitedslime.baseplugin.logging.PluginLogger;
import com.suitedslime.baseplugin.permissions.PermissionManager;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
import java.io.FileInputStream;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

/**
 * The base class that the main plugin class should extend.
 *
 * @version 1.0.0
 */

public abstract class BasePlugin extends JavaPlugin {

    /**
     * The version of the BasePlugin library
     */
    public static final String VERSION = "1.0.0";
    private static final String PACKAGE_NAME = "1_0_0";

    /**
     * The {@link PluginDescriptionFile} for this plugin.
     */
    public PluginDescriptionFile description;

    /**
     * The {@link PluginLogger} for this plugin.
     */
    public PluginLogger log;

    /**
     * The plugins data folder.
     */
    protected File baseDir;

    /**
     * The absolute path to the plugins data folder.
     */
    protected String baseDirPath;

    public Server server;
    public PluginManager pluginManager;
    public PermissionManager permissionManager;
    public CommandManager commandManager;
    public BukkitScheduler scheduler;

    /**
     * The plugins configuration this may be null if no config file is defined.
     */
    public PluginConfig config;

    /**
     * The display name of the plugin.
     */
    public String displayName;

    /**
     * The Jenkins build number
     */
    private Integer buildNumber;

    /**
     * Sets up the default fields for the plugin.
     *
     * @param createFolder If this is true then the plugins data folder will be created if it does not exist.
     */
    public void onEnable(boolean createFolder) {
        // This prevents Maven Shade plugin from replacing the package name
        String packageName = new String(new char[]{'c', 'o', 'm', '.', 's', 'u', 'i', 't', 'e', 'd', 's', 'l', 'i',
                'm', 'e', '.', 'b', 'a', 's', 'e', 'p', 'l', 'u', 'g', 'i', 'n'});

        if (!BasePlugin.class.getName().equals(packageName + ".v" + PACKAGE_NAME + ".BasePlugin")) {
            throw new PackageNameException(BasePlugin.class.getName());
        }

        this.description = this.getDescription();
        this.log = new PluginLogger(this);

        this.baseDir = this.getDataFolder();
        this.baseDirPath = this.baseDir.getAbsolutePath();

        this.server = this.getServer();
        this.pluginManager = this.server.getPluginManager();
        this.permissionManager = new PermissionManager(this);
        this.commandManager = new CommandManager(this);
        this.scheduler = this.server.getScheduler();

        if (createFolder && !this.baseDir.exists()) {
            this.baseDir.mkdirs();
        }

        this.displayName = this.description.getName();

        try {
            JarInputStream jar = new JarInputStream(new FileInputStream(this.getFile()));
            Manifest manifest = jar.getManifest();

            if (manifest != null) {
                String number = manifest.getMainAttributes().getValue("Build-Number");

                if (number != null) {
                    this.buildNumber = Integer.parseInt(number);
                }
            }

            jar.close();
        } catch (Exception e) {
            this.log.warn("Failed to read build number: " + e.getMessage());
        }
    }

    /**
     * Formats a message for the plugin.
     *
     * @param message The message to be formatted.
     * @param color  If set to true the prefix is colored blue.
     * @param version If set to true the version is included in the prefix.
     */
    public String formatMessage(String message, boolean color, boolean version) {
        StringBuilder line = new StringBuilder();

        if (color) {
            line.append(ChatColor.BLUE);
        }

        line.append("[");
        line.append(this.getDisplayName());

        if (version) {
            line.append(" v");
            line.append(this.description.getVersion());
        }

        line.append("] ");
        line.append(ChatColor.RESET);
        line.append(message);

        return line.toString();
    }

    /**
     * Formats a message for the plugin.
     * <p/>
     * NOTE: The version number is included in the prefix if  color is disabled.
     *
     * @param message The message to be formatted.
     * @param color  If set to true the prefix is colored blue.
     */
    public String formatMessage(String message, boolean color) {
        return this.formatMessage(message, color, !color);
    }

    /**
     * Formats a message for the plugin.
     * <p/>
     * NOTE: Colour is enabled and the version number is not in the prefix.
     *
     * @param message The message to be formatted.
     */
    public String formatMessage(String message) {
        return this.formatMessage(message, true, false);
    }

    /**
     * Gets the File for the base directory of the plugin.
     */
    public File getBaseDir() {
        return this.baseDir;
    }

    /**
     * Gets the path to the base directory of the plugin
     */
    public String getBaseDirPath() {
        return this.baseDirPath;
    }

    /**
     * Gets the plugins display name, this defaults to the name and is
     * used for the formatMessage() method.
     *
     * @return The display name.
     */
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * Sets the plugins display name, this defaults to the name and is
     * used for the formatMessage() method.
     *
     * @param displayName The display name.
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the Jenkins build number.
     *
     * @return The number
     */
    public int getBuildNumber() {
        return this.buildNumber;
    }
}
