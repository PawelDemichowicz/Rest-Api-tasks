package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskMapperTestSuite {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void mapToTaskTest(){
        //Given
        TaskDto taskDto = new TaskDto(1L,"Task","AAA");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        Assert.assertEquals(task.getId(),taskDto.getId());
        Assert.assertEquals(task.getTitle(),taskDto.getTitle());
        Assert.assertEquals(task.getContent(),taskDto.getContent());
    }

    @Test
    public void mapToTaskDtoTest(){
        //Given
        Task task = new Task(1L,"Task","AAA");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        Assert.assertEquals(taskDto.getId(),task.getId());
        Assert.assertEquals(taskDto.getTitle(),task.getTitle());
        Assert.assertEquals(taskDto.getContent(),task.getContent());
    }

    @Test
    public void mapToTaskDtoListTest(){
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Task1", "AAA"));
        tasks.add(new Task(2L, "Task2", "BBB"));

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(tasks);

        //Then
        Assert.assertEquals(taskDtoList.get(0).getId(),tasks.get(0).getId());
        Assert.assertEquals(taskDtoList.get(1).getId(),tasks.get(1).getId());
        Assert.assertEquals(taskDtoList.get(0).getContent(),tasks.get(0).getContent());
        Assert.assertEquals(taskDtoList.get(1).getContent(),tasks.get(1).getContent());
        Assert.assertEquals(taskDtoList.get(0).getTitle(),tasks.get(0).getTitle());
        Assert.assertEquals(taskDtoList.get(1).getTitle(),tasks.get(1).getTitle());
    }

}
