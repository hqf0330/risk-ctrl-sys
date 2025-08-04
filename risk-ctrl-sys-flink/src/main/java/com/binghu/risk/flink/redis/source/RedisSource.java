package com.binghu.risk.flink.redis.source;

import com.binghu.risk.model.RedisPO;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;

/**
 * RichParallelSourceFunction 支持多并行度 对比一下RichSourceFunction
 */
public class RedisSource extends RichParallelSourceFunction<RedisPO> {

    /**
     * 当该变量被修改的时候，会通知到其他的线程
     */
    private volatile boolean isRunning = true;

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
    }

    @Override
    public void run(SourceContext<RedisPO> output) throws Exception {
        while (isRunning) {
            output.collect(new RedisPO());
        }
    }

    @Override
    public void cancel() {
        this.isRunning = false;
    }
}
