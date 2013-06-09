/**
 * *****************************************************************************
 * BasePlugin
 *
 * PluginLogger
 *
 * @author SuitedSlime
 * @licence Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * *****************************************************************************
 */

package com.suitedslime.baseplugin.logging;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import java.util.logging.Logger;

public class PluginLogger {

    private Plugin plugin;
    private Logger logger;

    /**
     * @param plugin The plugin that this logger will format it's messages for.
     */
    public PluginLogger(Plugin plugin) {
        this.plugin = plugin;
        this.logger = Logger.getLogger("Minecraft");
    }

    /**
     * Formats a message to be logged to the file.
     *
     * @param msg The message to be formatted.
     */
    private String buildString(String msg) {
        PluginDescriptionFile pdf = plugin.getDescription();
        return "[" + pdf.getName() + " v" + pdf.getVersion() + "]: " + msg;
    }

    /**
     * Logs a message with the INFO level.
     *
     * @param msg The message to be logged.
     */
    public void info(String msg) {
        this.logger.info(this.buildString(msg));
    }

    /**
     * Logs a message with the WARNING level.
     *
     * @param msg The message to be logged.
     */
    public void warn(String msg) {
        this.logger.warning(this.buildString(msg));
    }

    /**
     * Logs a message with the SEVERE level.
     *
     * @param msg The message to be logged.
     */
    public void fatal(String msg) {
        this.logger.severe(this.buildString(msg));
    }
}
