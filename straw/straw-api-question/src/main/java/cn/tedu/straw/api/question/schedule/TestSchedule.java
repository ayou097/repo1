package cn.tedu.straw.api.question.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
@Deprecated
public class TestSchedule {
    private int i=0;
    @Scheduled(fixedRate = 3000)
    public void task(){
        log.debug("测试执行计划任务:"+ ++i);
    }
}
