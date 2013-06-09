/**
 * *****************************************************************************
 * BasePlugin
 *
 * BukkitDevUpdateChecker
 *
 * @author SuitedSlime
 * @licence Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * *****************************************************************************
 */

package com.suitedslime.baseplugin.update;

import com.suitedslime.baseplugin.util.StringUtils;
import org.bukkit.plugin.Plugin;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * A BukkitDev specific implementation of {@link UpdateChecker}
 */
public class BukkitDevUpdateChecker extends UpdateChecker {

    /**
     * Creates a new update checker for this plugin.
     *
     * @param plugin The plugin to check for updates for.
     * @param url    The URL of the BukkitDev files RSS feed.
     */
    public BukkitDevUpdateChecker(Plugin plugin, String url) {
        super(plugin, url);
    }

    /**
     * Performs the update check. The methofs getVersion() and similar will return
     * null if this method has not been called yet.
     */
    @Override
    public boolean updateNeeded() {
        try {
            InputStream input = this.filesFeed.openConnection().getInputStream();
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(input);

            Node latestFile = document.getElementsByTagName("item").item(0);
            NodeList children = latestFile.getChildNodes();

            this.version = children.item(1).getTextContent().replaceAll("[a-zA-Z]", "");
            this.link = children.item(3).getTextContent();

            input.close();

            input = (new URL(this.link)).openConnection().getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("<li class=\"user-action user-action-download\">")) {
                    this.jarLink = line.substring(line.indexOf("href=\"") + 6, line.lastIndexOf("\""));

                    break;
                }
            }

            reader.close();
            input.close();

            if (StringUtils.versionCompare(plugin.getDescription().getVersion(), this.version) == -1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
