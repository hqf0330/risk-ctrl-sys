package com.binghu.risk.flink;

import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;

public class TestStatefulFunction extends RichFlatMapFunction<Tuple2<String, Integer>, Tuple2<String, Integer>> {

    private ValueState<Tuple2<String, Integer>> total;

    @Override
    public void open(Configuration parameters) throws Exception {
        total = getRuntimeContext().getState(new ValueStateDescriptor<Tuple2<String, Integer>>(
                "total",
                TypeInformation.of(new TypeHint<Tuple2<String, Integer>>() {})
        ));
    }

    @Override
    public void flatMap(Tuple2<String, Integer> input, Collector<Tuple2<String, Integer>> collector) throws Exception {
        Tuple2<String, Integer> value = total.value();
        if (value == null) {
            total.update(input);
            collector.collect(input);
        } else {
            int count = value.f1 + input.f1;
            Tuple2<String, Integer> newState = Tuple2.of(input.f0, count);
            total.update(newState);
            collector.collect(newState);
        }
    }
}
