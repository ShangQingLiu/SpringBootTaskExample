package com.shark.example.dao.repository;

import com.shark.example.dao.pojo.TaskDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskDo, Long> {
}
