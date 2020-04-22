package com.ctsi.runner;

import com.ctsi.quartz.MonitorJob;
import com.ctsi.quartz.QuartzManager;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * ClassName QuartzRunner
 * Description //TODO
 * Author tongliwei
 * Date 2020/3/27 11:19
 * Version v1.0
 **/
@Component
public class QuartzRunner implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(QuartzRunner.class);
    @Autowired
    private Scheduler scheduler;

    @Override
    public void run(ApplicationArguments args) {
        QuartzManager manager = new QuartzManager(scheduler);
        String cronMonitor = "0 0/5 * * * ?";//"0 0 23 * * ?";
        String cronReport="0 0/3 * * * ?";
        //manager.addJob("monitor", MonitorJob.class,cronMonitor);
    }
}
