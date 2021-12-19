package com.safereach.codechallenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RunController {

    @Autowired
    private DataCacheLoader dataCacheLoader;

    @GetMapping("/run")
    public String run() {
        return dataCacheLoader.getCachedData().toString();
    }

}
