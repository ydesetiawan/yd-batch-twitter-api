package com.yd.controller;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;

import com.yd.common.util.AppsLifecycleEvent;
import com.yd.common.util.AppsLifecycleStartupEvent;

/**
 * @author edys
 * @version 1.0, Jan 20, 2017
 * @since
 */
@Controller
public class AppsLifecycleController implements
        ApplicationListener<AppsLifecycleEvent> {

    private static Logger log = Logger.getLogger(AppsLifecycleController.class);

    @Autowired
    private Scheduler scheduler;

    @Override
    public void onApplicationEvent(AppsLifecycleEvent event) {
        if (event instanceof AppsLifecycleStartupEvent) {

            log.info("Starting Quartz Scheduler...");
            try {
                scheduler.start();
            } catch (SchedulerException e) {
                log.warn("Failed to start scheduler", e);
            }
        }
    }
}
