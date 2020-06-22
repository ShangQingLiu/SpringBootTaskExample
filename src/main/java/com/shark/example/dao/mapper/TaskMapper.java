package com.shark.example.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskMapper {

    @Select("select id " +
            "from task " +
            "where status = 'READY' " +
            "order by create_time " +
            "limit ${taskSize}")
    List<Long> findIdListByStatusReady(@Param("taskSize") int taskSize);

    @Update("update task set status = 'PROCESS' where id = #{taskId}")
    void updateTaskStatusToProcessById(@Param("taskId") Long taskId);

}
