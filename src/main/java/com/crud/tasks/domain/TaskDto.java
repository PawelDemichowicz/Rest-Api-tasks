package com.crud.tasks.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String title;
    private String content;


}
