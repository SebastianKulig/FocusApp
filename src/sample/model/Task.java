package sample.model;

public class Task {
    private int userId;
    private int taskId;
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return this.userId;
    }

    private String task_name;
    private String description;

    public Task() {
    }

    public Task(String task_name, String description) {
        this.task_name = task_name;
        this.description = description;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTask_name() {
        return task_name;
    }

    public String getDescription() {
        return description;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
