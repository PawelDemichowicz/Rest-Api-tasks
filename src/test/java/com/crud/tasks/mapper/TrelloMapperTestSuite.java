package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
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
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void mapToBoardsTest() {
        //Given
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();

        trelloBoardsDto.add(new TrelloBoardDto("123", "ABC", new ArrayList<>()));
        trelloBoardsDto.add(new TrelloBoardDto("124", "EFG", new ArrayList<>()));

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);

        //Then
        Assert.assertEquals(2, trelloBoards.size());
        Assert.assertEquals(trelloBoards.get(0).getId(), trelloBoardsDto.get(0).getId());
        Assert.assertEquals(trelloBoards.get(1).getId(), trelloBoardsDto.get(1).getId());
    }

    @Test
    public void mapToBoardsDtoTest() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();

        trelloBoards.add(new TrelloBoard("123", "ABC", new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("124", "EFG", new ArrayList<>()));

        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        Assert.assertEquals(2, trelloBoards.size());
        Assert.assertEquals(trelloBoardsDto.get(0).getId(), trelloBoards.get(0).getId());
        Assert.assertEquals(trelloBoardsDto.get(1).getId(), trelloBoards.get(1).getId());
    }

    @Test
    public void mapToListTest(){
        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();

        trelloListsDto.add(new TrelloListDto("123", "ABC",false));
        trelloListsDto.add(new TrelloListDto("123", "ABC",false));

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);

        //Then
        Assert.assertEquals(2, trelloLists.size());
        Assert.assertEquals(trelloLists.get(0).getId(), trelloListsDto.get(0).getId());
        Assert.assertEquals(trelloLists.get(1).getId(), trelloListsDto.get(1).getId());
    }

    @Test
    public void mapToListDtoTest(){
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();

        trelloLists.add(new TrelloList("123", "ABC",false));
        trelloLists.add(new TrelloList("123", "ABC",false));

        //When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);

        //Then
        Assert.assertEquals(2, trelloListsDto.size());
        Assert.assertEquals(trelloListsDto.get(0).getId(), trelloLists.get(0).getId());
        Assert.assertEquals(trelloListsDto.get(1).getId(), trelloLists.get(1).getId());
    }

    @Test
    public void mapToTrelloCardTest(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("ABC","Descripion","1","123");

        //When
        TrelloCard trelloCard = trelloMapper.mapToTrelloCard(trelloCardDto);

        //Then
        Assert.assertEquals(trelloCard.getName(),trelloCardDto.getName());
        Assert.assertEquals(trelloCard.getDescription(),trelloCardDto.getDescription());
        Assert.assertEquals(trelloCard.getPos(),trelloCardDto.getPos());
        Assert.assertEquals(trelloCard.getListId(),trelloCardDto.getListId());
    }

    @Test
    public void mapToTrelloCardDtoTest(){
        //Given
        TrelloCard trelloCard = new TrelloCard("ABC","Descripion","1","123");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToTrelloCardDto(trelloCard);

        //Then
        Assert.assertEquals(trelloCardDto.getName(),trelloCard.getName());
        Assert.assertEquals(trelloCardDto.getDescription(),trelloCard.getDescription());
        Assert.assertEquals(trelloCardDto.getPos(),trelloCard.getPos());
        Assert.assertEquals(trelloCardDto.getListId(),trelloCard.getListId());
    }
}

