package com.example.board.controllertest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.example.board.board.BoardPageDTO;
import com.example.board.comment.CommentController;
import com.example.board.comment.CommentPageDTO;
import com.example.board.comment.CommentRequestDto;
import com.example.board.comment.CommentService;
import com.example.board.test.CommentTest;
import com.example.board.test.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

@WebMvcTest(CommentController.class)
public class CommentControllerTest extends ControllerTest implements CommentTest{

  @MockBean
  CommentService commentService;

//테스트를 하기위해서는 CommentRequestDto에 Long id 주석을 푼다

  @DisplayName("생성")
  @Test
  void postCommentTest() throws Exception {


    var action = mockMvc.perform(post("/api/boards/1/comment")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(TEST_COMMENT_REQUEST_DTO)));

    action.andExpect(status().isCreated())
        .andDo(print());
    verify(commentService,times(1)).createComment(anyLong(),any(CommentRequestDto.class),eq(TEST_USER));
  }

  @DisplayName("전체조회")
  @Test
  void getBoardTest() throws Exception{
    var commentPageDTO =  CommentPageDTO.builder().commentPage(1).commentSize(5).commentSortBy("UserId").isAsc(true).build();
    var action = mockMvc.perform(get("/api/boards/comments")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(commentPageDTO)));

    action.andExpect(status().isOk())
        .andDo(print());
    verify(commentService,times(1)).getComments(any(CommentPageDTO.class));
  }

  @DisplayName("수정")
  @Test
  void updateCommentTest() throws  Exception{
    var action = mockMvc.perform(put("/api/boards/1/comment/1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(TEST_COMMENT_REQUEST_DTO_UPDATE)));

    action.andExpect(status().isOk())
        .andDo(print());
    verify(commentService,times(1)).updateComment(anyLong(),anyLong(),any(CommentRequestDto.class),eq(TEST_USER));

  }

  @DisplayName("삭제")
  @Test
  void deleteCommentTest() throws Exception{
    var action = mockMvc.perform(delete("/api/boards/comment/1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON));

    action.andExpect(status().isOk())
        .andDo(print());
    verify(commentService,times(1)).deleteComment(anyLong(),eq(TEST_USER));
  }


}
