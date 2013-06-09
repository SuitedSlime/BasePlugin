/**
 * *****************************************************************************
 * BasePlugin
 *
 * SubCommandHandler
 *
 * @author SuitedSlime
 * @licence Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * *****************************************************************************
 */

package com.suitedslime.baseplugin.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that this method should be used to handle a sub-command.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SubCommandHandler {

    /**
     * @return The name of the parent command.
     */
    String parent();

    /**
     * @return The same of this sub-command.
     */
    String name();
}
