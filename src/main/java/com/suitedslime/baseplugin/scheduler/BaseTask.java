/**
 * *****************************************************************************
 * BasePlugin
 *
 * BaseTask
 *
 * @author SuitedSlime
 * @licence Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * *****************************************************************************
 */

package com.suitedslime.baseplugin.scheduler;

import com.suitedslime.baseplugin.BaseObject;

/**
 * The base class that any repeating or scheduled tasks should extend.
 *
 * @param <Type> The plugin that this task belongs to.
 */
public abstract class BaseTask<Type> extends BaseObject<Type> implements Runnable {

    /**
     * @param plugin The plugin that this task belongs to.
     */
    public BaseTask(Type plugin) {
        super(plugin);
    }
}
