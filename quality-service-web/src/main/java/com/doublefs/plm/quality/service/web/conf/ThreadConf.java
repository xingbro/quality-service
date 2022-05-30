package com.doublefs.plm.quality.service.web.conf;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * description:
 *
 * @author 吴瑾 (wujin@doublefs.com)
 * @date 2021-12-21
 */
@Configuration
@EnableAsync
public class ThreadConf {

    /**
     * 通用功能线程池，针对小的需要异步执行的业务。
     *
     * @return
     */
    @Bean(name = "commonThread")
    public Executor commonThread() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数
        executor.setCorePoolSize(5);
        //队列
        executor.setQueueCapacity(10);
        //最大线程数
        executor.setMaxPoolSize(10);
        executor.setThreadNamePrefix("plm-pattern-commonThread-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //(扔给主线程处理)CallerRunsPolicy 该策略只要线程池未关闭，该策略直接在调用者线程（主线程）中，运行当前被丢弃的任务（白话就是不会抛弃线程，也不抛出异常，而是将任务回退到调用者，从而降低新任务的流量），这样会影响QPS（Queries per second）
        //(静默抛弃)DiscardPolicy 该策略默默地丢弃无法处理的任务，不予任何处理。
        //(抛弃最后一个任务)DiscardOldestPolicy 抛弃策略：当任务添加到线程池中被拒绝时，线程池会放弃等待队列中最旧的未处理任务(抛弃下一个将被执行的任务)，然后将被拒绝的任务添加到等待队列中，如果队列是一个优先队列，那么抛弃最旧的策略就会抛弃优先级最高的任务，因此不要将两者在一起使用。
        //(抛异常丢弃)AbortPolicy 终止策略。当任务添加到线程池中被拒绝时，它将抛出 RejectedExecutionException 异常（继承自RuntimeException），调用者可以捕获该异常自行处理。是默认饱和策略。
        executor.setKeepAliveSeconds(30);
        executor.initialize();
        return executor;
    }

    /**
     * 多线程消费mq
     */
    @Bean(name = "thread4Mq")
    public Executor thread4Cache() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数
        executor.setCorePoolSize(5);
        //队列
        executor.setQueueCapacity(10);
        //最大线程数
        executor.setMaxPoolSize(10);
        executor.setThreadNamePrefix("plm-pattern-thread4Mq-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //(扔给主线程处理)CallerRunsPolicy 该策略只要线程池未关闭，该策略直接在调用者线程（主线程）中，运行当前被丢弃的任务（白话就是不会抛弃线程，也不抛出异常，而是将任务回退到调用者，从而降低新任务的流量），这样会影响QPS（Queries per second）
        //(静默抛弃)DiscardPolicy 该策略默默地丢弃无法处理的任务，不予任何处理。
        //(抛弃最后一个任务)DiscardOldestPolicy 抛弃策略：当任务添加到线程池中被拒绝时，线程池会放弃等待队列中最旧的未处理任务(抛弃下一个将被执行的任务)，然后将被拒绝的任务添加到等待队列中，如果队列是一个优先队列，那么抛弃最旧的策略就会抛弃优先级最高的任务，因此不要将两者在一起使用。
        //(抛异常丢弃)AbortPolicy 终止策略。当任务添加到线程池中被拒绝时，它将抛出 RejectedExecutionException 异常（继承自RuntimeException），调用者可以捕获该异常自行处理。是默认饱和策略。
        executor.setKeepAliveSeconds(30);
        executor.initialize();
        return executor;
    }


}
