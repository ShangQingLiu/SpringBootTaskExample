package com.shark.example.schedule;

import com.shark.example.dao.mapper.TaskMapper;
import com.shark.example.manager.TaskManager;
import com.shark.example.task.DoTaskTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@EnableScheduling
@Component
@RequiredArgsConstructor
public class TaskSchedule {

    private final DoTaskTask doTaskTask;
    private final TaskMapper taskMapper;

    @Transactional(value = "transactionManager", isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    @Scheduled(initialDelay = 10, fixedDelay = 10)
    public void checkDataFrame() {
        if(TaskManager.getInstance().findTaskSize() >= 4) {
            return;
        }
        int queryTaskSize = TaskManager.TASK_BUFFER - TaskManager.getInstance().findTaskSize();
        List<Long> taskIdList = taskMapper.findIdListByStatusReady(queryTaskSize);
        for(Long taskId: taskIdList) {
            taskMapper.updateTaskStatusToProcessById(taskId);
            TaskManager.getInstance().addTask(taskId);
            doTaskTask.start(taskId);
        }
    }
}
