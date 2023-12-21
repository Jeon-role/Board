package com.example.board.controllertest;


import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.board.board.BoardController;
import com.example.board.board.BoardPageDTO;
import com.example.board.board.BoardRequestDto;
import com.example.board.board.BoardService;
import com.example.board.test.ControllerTest;
import com.example.board.test.BoardTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

@WebMvcTest(BoardController.class)
public class BoardControllerTest extends ControllerTest implements BoardTest {

  @MockBean
  private BoardService boardService;



  @DisplayName("생성")
  @Test
  void postBoardTest() throws Exception{

    var action = mockMvc.perform(post("/api/boards")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(TEST_BOARD_REQUEST_DTO)));

    action.andExpect(status().isCreated())
        .andDo(print());
    verify(boardService,times(1)).createBoard(any(BoardRequestDto.class),eq(TEST_USER));
  }

  @DisplayName("전체조회")
  @Test
  void getBoardTest() throws Exception{
    var boardPageDTO =  BoardPageDTO.builder().currentPage(1).size(5).sortBy("UserId").isAsc(true).build();
    var action = mockMvc.perform(get("/api/boards")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(boardPageDTO)));

    action.andExpect(status().isOk())
        .andDo(print());
    verify(boardService,times(1)).getBoards(any(BoardPageDTO.class));
  }

  @DisplayName("검색")
  @Test
  void getBoardsSearchTest() throws Exception{
    var boardPageDTO =  BoardPageDTO.builder().currentPage(1).size(5).keyWord("title").build();
    var action = mockMvc.perform(get("/api/boards/search")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(boardPageDTO)));
    action.andExpect(status().isOk())
        .andDo(print());
    verify(boardService,times(1)).getBoardsSearch(any(BoardPageDTO.class));
  }



  @DisplayName("선택조회")
  @Test
  void getOneBoardTest()throws Exception {
    var action = mockMvc.perform(get("/api/boards/1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(TEST_BOARD_RESPONSE_DTO)));
    action.andExpect(status().isOk())
        .andDo(print());
    verify(boardService,times(1)).findOneBoard(anyLong());
  }


  @DisplayName("수정")
  @Test
  void updateBoardTest() throws Exception{
    var action = mockMvc.perform(put("/api/boards/1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(TEST_BOARD_REQUEST_DTO_UPDATE)));
    action.andExpect(status().isOk())
        .andDo(print());
    verify(boardService,times(1)).updateBoard(anyLong(),any(BoardRequestDto.class),eq(TEST_USER));
  }

  @DisplayName("삭제")
  @Test
  void deleteBoardTest() throws  Exception{
    var action = mockMvc.perform(delete("/api/boards/1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON));
    action.andExpect(status().isOk())
        .andDo(print());
    verify(boardService,times(1)).deleteBoard(anyLong(),eq(TEST_USER));
  }

  @DisplayName("좋아요 생성")
  @Test
  void postLikesTest()throws Exception{
    var action = mockMvc.perform(post("/api/boards/1/likes")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON));
    action.andExpect(status().isCreated())
        .andDo(print());
    verify(boardService,times(1)).createLikes(anyLong(),eq(TEST_USER));
  }

  @DisplayName("좋아요 삭제")
  @Test
  void deleteLikesTest()throws Exception{
    var action = mockMvc.perform(delete("/api/boards/likes/1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON));
    action.andExpect(status().isOk())
        .andDo(print());
    verify(boardService,times(1)).deleteLikes(anyLong(),eq(TEST_USER));
  }



}
