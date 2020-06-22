package com.shark.example.manager;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    public static int TASK_BUFFER = 4;

    private static TaskManager instance = null;
    private List<Long> taskIdList;

    private TaskManager() {
        taskIdList = new ArrayList<>();
    }

    public int findTaskSize() {
        return taskIdList.size();
    }

    public void addTask(Long taskId) {
        taskIdList.add(taskId);
    }

    public void removeTask(Long taskId) {
        taskIdList.remove(taskId);
    }

    public static TaskManager getInstance() {
        if(instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }


}
