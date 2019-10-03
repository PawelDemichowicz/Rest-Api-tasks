package com.crud.tasks.facade;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTest {

    @InjectMocks
    private TrelloValidator trelloValidator;

    @Test
    public void validateTrelloBoardsTest(){
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "test",new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("1", "board",new ArrayList<>()));

        //When
        List<TrelloBoard> result = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        Assert.assertEquals(1,result.size());
        Assert.assertEquals(result.get(0).getName(),"board");
    }

    @Test
    public void shouldContainsTest(){
        //Given
        TrelloCard trelloCard = new TrelloCard("test","description","pos","id");

        //When
        trelloValidator.validateCard(trelloCard);

        //Then
        Assert.assertEquals("test",trelloCard.getName());
        //Log is written
    }

    @Test
    public void shouldNotContainsTest(){
        //Given
        TrelloCard trelloCard = new TrelloCard("name", "description","pos","id");

        //When
        trelloValidator.validateCard(trelloCard);

        //Then
        Assert.assertNotEquals("test",trelloCard.getName());
        //Log is written
    }
}
