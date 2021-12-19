package com.safereach.codechallenge;

import com.safereach.codechallenge.donottouch.Data;
import com.safereach.codechallenge.donottouch.DoNotTouchProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class DataCacheLoader {

    List<List<Data>> cachedData= new ArrayList<>();

    @Autowired
    private DoNotTouchProcessor processor;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @EventListener(ApplicationReadyEvent.class)
    private void runAfterStartup() {
        for(int i = 0; i < 10000; i++) {
            try {
                cachedData.add(processor.run());
            } catch (IndexOutOfBoundsException e) {
                //do nothing
            }
        }
    }

    /***
     *
     * @return the first cached result and fill cache with a new result
     */
    public List<Data> getCachedData() {
        executor.submit(() -> cachedData.add(processor.run()));
        return cachedData.remove(0);
    }



}
