package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository repository;

    @Test
    public void getAllTasksTest(){
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L,"A","AAA"));
        tasks.add(new Task(2L,"B","BBB"));
        tasks.add(new Task(3L,"C","CCC"));

        when(repository.findAll()).thenReturn(tasks);

        //When
        List<Task> result = dbService.getAllTasks();

        //Then
        Assert.assertEquals(3,result.size());
    }

    @Test
    public void getTaskTest(){
        //Given
        Task task = new Task(1L,"A","AAA");

        when(repository.findById(task.getId())).thenReturn(Optional.of(task));

        //When
        Task result = dbService.getTask(task.getId());

        //Then
        Assert.assertEquals("A", result.getTitle());
        Assert.assertEquals("AAA", result.getContent());
        Assert.assertEquals(result.getId(),1L,0.0001);
    }

    @Test(expected = TaskNotFoundException.class)
    public void getTask_throwTaskNotFoundException() {

        //Given
        when(repository.findById(1L)).thenReturn(Optional.empty());

        //When
        dbService.getTask(1L);

        //Then
        //Exception is thrown
    }

    @Test
    public void saveTaskTest(){
        //Given
        Task task = new Task(1L, "title", "content");

        when(repository.save(task)).thenReturn(task);

        //When
        Task result = dbService.saveTask(task);

        //Then
        Assert.assertEquals(1L, result.getId(),0.001);
        Assert.assertEquals("title", result.getTitle());
        Assert.assertEquals("content", result.getContent());
    }
}
