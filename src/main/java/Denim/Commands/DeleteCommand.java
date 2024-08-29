package denim.commands;

import denim.TaskList;
import denim.exceptions.DenimException;
import denim.storage.TaskIO;
import denim.tasks.Task;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_USAGE = "delete <taskNumber>";
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(TaskList taskList, TaskIO taskIO) {

        if (!taskList.isValidIndex(index)) {
            return new CommandResult("The index chosen is invalid.");
        }

        Task deletedTask = taskList.getTask(index);
        taskList.deleteTaskAtIndex(index);

        try {
            taskIO.deleteTask(taskList);
        } catch (DenimException e) {
            taskList.addTaskAtIndex(index, deletedTask);
            return new CommandResult(e.getMessage() + "\n The task was not deleted.");
        }

        int taskListSize = taskList.getTaskListSize();

        String returnMessage = String.format("Got it. I've deleted this task:%n %s %n"
                + "Now you have %d tasks in the list.", deletedTask, taskListSize);
        return new CommandResult(returnMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}