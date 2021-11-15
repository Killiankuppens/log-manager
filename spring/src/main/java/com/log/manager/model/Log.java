package com.log.manager.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="logs")
public class Log {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Date taskDoneOn;
    private String taskRef;
    private String taskDur;
    private String taskDesc;

    public Log() {}

    public Log(Long userId,  Date taskDoneOn, String taskRef, String taskDur, String taskDesc) {
        this.userId=userId;
        this.taskDoneOn=taskDoneOn;
        this.taskRef = taskRef;
        this.taskDur=taskDur;
        this.taskDesc=taskDesc;
    }

    //region GETTERS & SETTERS
    public Long getId() {
        return this.id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getTaskDoneOn() {
        return taskDoneOn;
    }

    public void setTaskDoneOn(Date taskDoneOn) {
        this.taskDoneOn = taskDoneOn;
    }

    public String getTaskRef() {
        return taskRef;
    }

    public void setTaskRef(String taskRef) {
        this.taskRef = taskRef;
    }

    public String getTaskDur() {
        return taskDur;
    }

    public void setTaskDur(String taskDur) {
        this.taskDur = taskDur;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }
    //endregion
}
