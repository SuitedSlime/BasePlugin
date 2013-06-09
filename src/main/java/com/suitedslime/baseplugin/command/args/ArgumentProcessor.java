/**
 * *****************************************************************************
 * BasePlugin
 *
 * ArgumentProcessor
 *
 * @author SuitedSlime
 * @licence Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * *****************************************************************************
 */

package com.suitedslime.baseplugin.command.args;

/**
 * Used to process command arguments into an easy to work with format.
 */

public abstract class ArgumentProcessor {

    public String[] args;

    /**
     * Process the raw array of arguments, this is called automatically
     */
    public abstract void process();
}
