package com.log.manager.controller;

import com.log.manager.exception.LogNotFoundException;
import com.log.manager.model.Log;
import com.log.manager.repository.LogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LogController {
    Logger logger = LoggerFactory.getLogger(LogController.class);

    @Autowired
    private LogRepository repo;

    @GetMapping("/log")
    public List<Log> getAllLogs() {
        logger.info("Retrieving all logs");
        return repo.findAll();
    }

    @PostMapping("/log")
    public Log newLog(@RequestBody Log Log) {
        return repo.save(Log);
    }

    @GetMapping("/log/{id}")
    public Log getLog(@PathVariable Long id) {
        logger.info("Retrieve one log");
        return repo.findById(id)
                .orElseThrow(() -> new LogNotFoundException(id));
    }

    @PutMapping("/log/{id}")
    public Log updateLog(@RequestBody Log newLog, @PathVariable Long id) {
        return repo.findById(id)
                .map(log -> {
                        log.setUserId(newLog.getUserId());
                        log.setTaskDoneOn(newLog.getTaskDoneOn());
                        log.setTaskRef(newLog.getTaskRef());
                        log.setTaskDur(newLog.getTaskDur());
                        log.setTaskDesc(newLog.getTaskDesc());
                        return repo.save(log);
                })
                .orElseThrow(() -> new LogNotFoundException(id));
    }

    @DeleteMapping("/Log/{id}")
    public void deleteLog(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
