/**
 * *****************************************************************************
 * BasePlugin
 *
 * UpdateChecker
 *
 * @author SuitedSlime
 * @licence Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * *****************************************************************************
 */

package com.suitedslime.baseplugin.update;

import org.bukkit.plugin.Plugin;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * A class to check for updates of the plugin. A specific updater must
 * extend this and provide the mothod to check for updates.
 */
public abstract class UpdateChecker {

    protected Plugin plugin;
    protected URL filesFeed;

    protected String version;
    protected String link;
    protected String jarLink;

    /**
     * @param plugin The plugin to be checked.
     * @param url    The URL to fetch data from.
     */
    protected UpdateChecker(Plugin plugin, String url) {
        this.plugin = plugin;

        try {
            this.filesFeed = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the most recent version found from the URL.
     *
     * @return The version number.
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Gets the URL for the page where the latest version can be downloaded from.
     *
     * @return The URL.
     */
    public String getLink() {
        return this.link;
    }

    /**
     * Gets the URL for the .jar file for the plugin.
     *
     * @return The URL.
     */
    public String getJarLink() {
        return this.jarLink;
    }

    /**
     * This method should perform the update check and set the three fields.
     *
     * @return True if update is needed, false if not.
     */
    public abstract boolean updateNeeded();
}
