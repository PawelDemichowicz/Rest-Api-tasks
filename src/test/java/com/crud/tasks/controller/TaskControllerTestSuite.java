package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void getTasksTest() throws Exception {
        //Given
        List<TaskDto> tasks = new ArrayList<>();
        tasks.add(new TaskDto(1L, "A", "AAA"));
        tasks.add(new TaskDto(2L, "B", "BBB"));

        when(taskMapper.mapToTaskDtoList(dbService.getAllTasks())).thenReturn(tasks);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("A")))
                .andExpect(jsonPath("$[0].content", is("AAA")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("B")))
                .andExpect(jsonPath("$[1].content", is("BBB")));
    }

    @Test
    public void getTaskTest() throws Exception {
        //Given
        Task task = new Task(1L, "task", "content");
        TaskDto taskDto = new TaskDto(1L, "task", "content");

        when(dbService.getTask(1L)).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("task")))
                .andExpect(jsonPath("$.content", is("content")));
    }

    @Test
    public void deleteTaskTest() throws Exception {
        //Given
        Task task = new Task(1L, "task", "content");

        dbService.saveTask(task);

        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask?taskId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTaskTest() throws Exception {
        //Given
        Task task = new Task(1L, "A", "AAA");
        TaskDto taskDto = new TaskDto(1L, "B", "BBB");

        dbService.saveTask(task);

        when(taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)))).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("B")))
                .andExpect(jsonPath("$.content", is("BBB")));
    }

    @Test
    public void createTaskTest() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "task", "content");
        Task task = new Task(1L, "task", "content");

        when(dbService.saveTask(taskMapper.mapToTask(taskDto))).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}
