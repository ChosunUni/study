package com.suntime.study.service;

import java.util.List;

import com.suntime.study.entity.ToDoEntity;
import com.suntime.study.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;

    public List<ToDoEntity> getList(){
        return this.toDoRepository.findAll();
    }

    public void create(String content){
        ToDoEntity toDoEntity = new ToDoEntity();
        toDoEntity.setContent(content);
        toDoEntity.setCompleted(false);
        this.toDoRepository.save(toDoEntity);
    }
}