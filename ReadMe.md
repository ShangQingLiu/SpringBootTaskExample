# 建立Database 相關Table
```
CREATE TYPE status AS ENUM ('READY', 'PROCESS', 'COMPLETE');

-- 任務表
create table task
(
    id          bigserial not null,
    create_time timestamp,
    status      status
);

-- 任務處理紀錄，查詢任務是哪一個Worker處理
create table task_history
(
    id bigserial not null,
    task_id bigint,
    worker varchar,
    create_time timestamp
);
```

# 啟動兩個Task Application 
一個Task Application被設定成每10毫秒去讀未處理的任務，每個Task Application最多可以處理四個，目前先設定成每個處理10毫秒。

## 修正Database帳密
請修正成你自己database的帳好、密碼、Schema
```
spring:
  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/shark?currentSchema=task
    username: shiyongzhe
    password:
```

## 設定修正Yml檔Port
* 7080
* 7081
```
server:
  port: 7080
```

## 設定修正Yml檔Worker Name
* worker1
* worker2
```
worker:
  name: worker1
```

# 建置任務
可以用swagger-ui建置任務: http://localhost:7080/swagger-ui.html#/task-controller/createTaskUsingPOST
該API會一口氣建置一千筆任務，任務一建置兩個worker就會開始工作

# 查訊是否有任務被重複處理
```
select *
from (select task_id, count(task_id) as count from task_history
group by task_id) as t1 where count = 2;
```