package com.example.minipixeljava.aggregation;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("splitAggregationStrategy")
public class SplitAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        String itemResult = newExchange.getMessage().getBody(String.class);

        if (oldExchange == null) {
            List<String> results = new ArrayList<>();
            results.add(itemResult);

            newExchange.getMessage().setBody(results);
            return newExchange;
        }

        List<String> results = oldExchange.getMessage().getBody(List.class);
        results.add(itemResult);

        return oldExchange;
    }
}