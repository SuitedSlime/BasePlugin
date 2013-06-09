/**
 * *****************************************************************************
 * BasePlugin
 *
 * BaseListener
 *
 * @author SuitedSlime
 * @licence Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * *****************************************************************************
 */

package com.suitedslime.baseplugin.event;

import com.suitedslime.baseplugin.BaseObject;
import org.bukkit.event.Listener;

/**
 * The base class that all listeners should extends.
 *
 * @param <Type> The plugin that this listener belongs to.
 */
public abstract class BaseListener<Type> extends BaseObject<Type> implements Listener {

    /**
     * @param plugin The plugin that this listener belongs to.
     */
    public BaseListener(Type plugin) {
        super(plugin);
    }
}
