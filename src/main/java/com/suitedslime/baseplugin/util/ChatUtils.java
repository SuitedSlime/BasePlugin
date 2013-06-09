/**
 * *****************************************************************************
 * BasePlugin
 *
 * ChatUtils
 *
 * @author SuitedSlime
 * @licence Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * *****************************************************************************
 */

package com.suitedslime.baseplugin.util;

import org.bukkit.ChatColor;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * A collection of useful methods for working with Minecraft chat.
 */
public class ChatUtils {

    private static final LinkedHashMap<String, ChatColor> formattingCodeMap;

    static {
        formattingCodeMap = new LinkedHashMap<String, ChatColor>();

        for (ChatColor color : ChatColor.values()) {
            if (color.isColor()) {
                formattingCodeMap.put("&" + color.getChar(), color);
            } else {
                formattingCodeMap.put("#" + color.name().substring(0, 1).toLowerCase(), color);
            }
        }
    }

    /**
     * Gets the list of formatting codes and the values they represent.
     * <p/>
     * <p>Color codes are the colors hex value prefixed with a &</p>
     * <p>Formatting codes are the name of the format prefixed with a #, e.g. the code for underlined is #u</p>
     *
     * @return The map.
     */
    public static LinkedHashMap<String, ChatColor> getFormattingCodeMap() {
        return formattingCodeMap;
    }


    /**
     * Replaces color/formatting codes in a string with actual colors.
     *
     * @param message The message to parse.
     * @return The message with color codes replaced.
     */
    public static String parseFormattingCodes(String message) {
        for (Entry<String, ChatColor> entry : formattingCodeMap.entrySet()) {
            message = message.replaceAll(entry.getKey(), entry.getValue().toString());
        }
        return message;
    }
}
