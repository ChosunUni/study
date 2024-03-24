package com.suntime.study.service;

import com.suntime.study.dto.TimerDTO;
import com.suntime.study.entity.TimerEntity;
import com.suntime.study.repository.TimerRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimerService {
    @Autowired
    TimerRepository timerRepository;

    public void save(TimerDTO timerDTO){
        TimerEntity timerEntity = TimerEntity.toTimerEntity(timerDTO);
        timerRepository.save(timerEntity);
    }

    public List<TimerEntity> subAll() {
        return timerRepository.findAll();
    }
}