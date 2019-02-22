package com.example.service.invoker.command;

import com.example.service.invoker.config.HystrixThreadLocal;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariable;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableLifecycle;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisher;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategy;
import com.netflix.hystrix.strategy.properties.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 将现有的并发策略作为新并发策略的成员变量
 * 在新并发策略中，返回现有并发策略的线程池、Queue等
 */
@Component
@Slf4j
public class TestHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {
    private HystrixConcurrencyStrategy delegate ;

    public TestHystrixConcurrencyStrategy(){

        this.delegate  = HystrixPlugins.getInstance().getConcurrencyStrategy();
        if (this.delegate instanceof TestHystrixConcurrencyStrategy) {
            return;
        }
        // Keeps references of existing Hystrix plugins.
        HystrixEventNotifier eventNotifier = HystrixPlugins.getInstance()
                .getEventNotifier();
        HystrixMetricsPublisher metricsPublisher = HystrixPlugins.getInstance()
                .getMetricsPublisher();
        HystrixPropertiesStrategy propertiesStrategy = HystrixPlugins.getInstance()
                .getPropertiesStrategy();
        HystrixCommandExecutionHook commandExecutionHook = HystrixPlugins.getInstance()
                .getCommandExecutionHook();

        HystrixPlugins.reset();

        // Registers existing plugins excepts the Concurrent Strategy plugin.
        HystrixPlugins.getInstance().registerConcurrencyStrategy(this);
        HystrixPlugins.getInstance().registerEventNotifier(eventNotifier);
        HystrixPlugins.getInstance().registerMetricsPublisher(metricsPublisher);
        HystrixPlugins.getInstance().registerPropertiesStrategy(propertiesStrategy);
        HystrixPlugins.getInstance().registerCommandExecutionHook(commandExecutionHook);
    }

    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        return new HystrixThreadCallable<>(callable, RequestContextHolder.getRequestAttributes(), HystrixThreadLocal.threadLocal.get());
    }

    static class HystrixThreadCallable<T> implements Callable<T> {
        private final Callable<T> target;
        private final RequestAttributes requestAttributes;
        private String s ;
        public HystrixThreadCallable(Callable<T> target, RequestAttributes requestAttributes, String s) {
            this.target = target;
            this.requestAttributes = requestAttributes;
            this.s = s;
        }
        @Override
        public T call() throws Exception {
            try {
                RequestContextHolder.setRequestAttributes(requestAttributes);
                HystrixThreadLocal.threadLocal.set(s);
                log.warn("----------------> TestHystrixConcurrencyStrategy call()");
                return target.call();
            }
            finally {
                RequestContextHolder.resetRequestAttributes();
                HystrixThreadLocal.threadLocal.remove();
            }
        }
    }

    @Override
    public BlockingQueue<Runnable> getBlockingQueue(int maxQueueSize) {
        return this.delegate.getBlockingQueue(maxQueueSize);
    }

    @Override
    public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey, HystrixThreadPoolProperties threadPoolProperties) {
        return delegate.getThreadPool(threadPoolKey, threadPoolProperties);
    }

    @Override
    public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey, HystrixProperty<Integer> corePoolSize, HystrixProperty<Integer> maximumPoolSize, HystrixProperty<Integer> keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        return delegate.getThreadPool(threadPoolKey, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public <T> HystrixRequestVariable<T> getRequestVariable(HystrixRequestVariableLifecycle<T> rv) {
        return this.delegate.getRequestVariable(rv);
    }
}
