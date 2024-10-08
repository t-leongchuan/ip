package denim.commands;

import denim.TaskList;
import denim.storage.WriteTaskFile;

/**
 * Represents a list command that can be executed.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_USAGE = "list";
    public static final String COMMAND_EXAMPLE = "list";
    @Override
    public CommandResult execute(TaskList taskList, WriteTaskFile writeTaskFile) {
        String returnMessage = taskList.printList();
        return new CommandResult(returnMessage, CommandStatus.COMMAND_SUCCESSFUL);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
