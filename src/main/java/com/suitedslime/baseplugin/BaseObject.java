/**
 * *****************************************************************************
 * BasePlugin
 *
 * BaseObject
 *
 * @author SuitedSlime
 * @licence Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * *****************************************************************************
 */

package com.suitedslime.baseplugin;

/**
 * The class that wall other object belonging to this plugin should extend.
 *
 * @param <Type> The plugin that this object belongs to.
 */
public abstract class BaseObject<Type> {

    /**
     * The plugin that created this object.
     */
    protected Type plugin;


    /**
     * @param plugin The plugin that this object belongs to
     */
    public BaseObject(Type plugin) {
        this.plugin = plugin;
    }
}
