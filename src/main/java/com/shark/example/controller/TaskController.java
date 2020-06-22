package com.shark.example.controller;

import com.shark.example.controller.dio.CreateTaskInput;
import com.shark.example.service.CreateTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/task")
@RestController
public class TaskController {


    private final CreateTaskService createTaskService;

    @PostMapping
    public String createTask(){
        return createTaskService.start();
    }

}
