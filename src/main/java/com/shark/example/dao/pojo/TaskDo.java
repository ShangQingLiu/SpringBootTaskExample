package com.shark.example.dao.pojo;

import com.shark.example.type.StatusType;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;


@Data
@Table(name = "task", schema = "task")
@Entity
@TypeDef(name = "enum", typeClass = PostgreSQLEnumType.class)
public class TaskDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigserial")
    private Long id;

    private Date createTime;

    @Enumerated(EnumType.STRING)
    @Type( type = "enum")
    @Column(name = "status", columnDefinition = "status")
    private StatusType statusType;
}
