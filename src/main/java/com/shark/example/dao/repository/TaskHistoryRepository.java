package com.shark.example.dao.repository;

import com.shark.example.dao.pojo.TaskHistoryDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskHistoryRepository extends JpaRepository<TaskHistoryDo, Long> {
}
