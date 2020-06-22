package com.shark.example.task;

import com.shark.example.dao.pojo.TaskHistoryDo;
import com.shark.example.dao.repository.TaskHistoryRepository;
import com.shark.example.dao.repository.TaskRepository;
import com.shark.example.dao.pojo.TaskDo;
import com.shark.example.manager.TaskManager;
import com.shark.example.type.StatusType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Component
public class DoTaskTask {

    private final TaskRepository taskRepository;
    private final TaskHistoryRepository taskHistoryRepository;

    @Value("${worker.name}")
    private String workerName;

    @Async
    public void start(Long taskId) {
        log.info("taskId: " + taskId);
        TaskDo task = taskRepository.findById(taskId).get();
        try {
            Random random = new Random();
            long fakeWorkTime = 10;
            Thread.sleep(fakeWorkTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TaskHistoryDo taskHistory = new TaskHistoryDo();
        taskHistory.setTaskId(taskId);
        taskHistory.setWorker(workerName);
        taskHistory.setCreateTime(new Date());
        taskHistoryRepository.save(taskHistory);

        task.setStatusType(StatusType.COMPLETE);
        taskRepository.save(task);

        TaskManager.getInstance().removeTask(taskId);
    }
}
