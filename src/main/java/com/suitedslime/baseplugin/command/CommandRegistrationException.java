/**
 * *****************************************************************************
 * BasePlugin
 *
 * CommandRegistrationException
 *
 * @author SuitedSlime
 * @licence Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * *****************************************************************************
 */

package com.suitedslime.baseplugin.command;

public class CommandRegistrationException extends RuntimeException {

    private static final long serialVersionUID = -1674769027758878225L;

    public CommandRegistrationException(String message) {
        super(message);
    }
}
