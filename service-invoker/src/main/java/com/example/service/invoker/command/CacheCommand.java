package com.example.service.invoker.command;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class CacheCommand extends HystrixCommand {
    private static final HystrixCommandKey GETTER_KEY= HystrixCommandKey.Factory.asKey("CommandKey");
    private RestTemplate restTemplate;
    private String string;

    public CacheCommand(RestTemplate restTemplate, String string) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("userCacheCommand")).andCommandKey(GETTER_KEY));
        this.restTemplate = restTemplate;
        this.string = string;
    }

    @Override
    protected Object run() throws Exception {
        String json = restTemplate.getForObject("http://service-provider/testHystrixCache/{string}",String.class,string);
        log.info("------------>" + json);
        return json;
    }

    @Override
    protected String getCacheKey() {
        return this.string;
    }
    public static void flushCache(String string){
        HystrixRequestCache.getInstance(GETTER_KEY, HystrixConcurrencyStrategyDefault.getInstance()).clear(string);
    }
}
