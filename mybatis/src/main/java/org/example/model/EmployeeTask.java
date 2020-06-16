package org.example.model;



/**
 * @author chenj
 */
public class EmployeeTask {

    private Long id;
    private Long empId;
    private Long taskId;
    private String taskName;
    private String note;
    private Task task;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


//    @Override
//    public String toString() {
//        return "EmployeeTask{" +
//                "id=" + id +
//                ", empId=" + empId +
//                ", taskId=" + taskId +
//                ", taskName='" + taskName + '\'' +
//                ", note='" + note + '\'' +
//                ", task=" + task +
//                '}';
//    }
}
