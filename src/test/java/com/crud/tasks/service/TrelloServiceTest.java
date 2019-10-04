package com.crud.tasks.service;

import com.crud.tasks.client.TrelloClient;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    TrelloService trelloService;

    @Mock
    TrelloClient trelloClient;

    @Mock
    SimpleEmailService emailService;

    @Test
    public void createTrelloCardTest(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name","description","pos","id");

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(new CreatedTrelloCardDto("id","name","url"));

        //When
        CreatedTrelloCardDto result = trelloService.createTrelloCard(trelloCardDto);

        //Then
        Assert.assertEquals(result.getName(),"name");
    }

    @Test
    public void shouldFetchTrelloBoards(){
        //Given
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1","A", new ArrayList<>()));
        trelloBoards.add(new TrelloBoardDto("2","B", new ArrayList<>()));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoards);

        //When
        List<TrelloBoardDto> result = trelloService.fetchTrelloBoards();

        //Then
        Assert.assertEquals(2,result.size());
    }
}
