package com.shark.example.service;

import com.shark.example.dao.repository.TaskRepository;
import com.shark.example.dao.pojo.TaskDo;
import com.shark.example.type.StatusType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class CreateTaskService {

    private final TaskRepository taskRepository;

    public String start() {
        for(int i = 0; i < 1000; i++) {
            TaskDo taskDo = new TaskDo();
            taskDo.setCreateTime(new Date());
            taskDo.setStatusType(StatusType.READY);
            taskRepository.save(taskDo);
        }
        return "success";
    }
}
