package com.learning.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotification implements JobExecutionListener {

    private Logger logger = LoggerFactory.getLogger(JobCompletionNotification.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("Job execution started.");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        logger.info("Current status of job {} ",jobExecution.getStatus());
    }
}
