package com.example.board.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.board.board.Board;
import com.example.board.board.BoardRepository;
import com.example.board.board.BoardResponseDto;
import com.example.board.board.BoardServiceImpl;
import com.example.board.board.Likes;
import com.example.board.board.LikesRepository;
import com.example.board.comment.Comment;
import com.example.board.comment.CommentPageDTO;
import com.example.board.comment.CommentRepository;
import com.example.board.comment.CommentResponseDto;
import com.example.board.comment.CommentServiceImpl;
import com.example.board.test.BoardTest;
import com.example.board.test.CommentTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest implements CommentTest, BoardTest {
  @Mock
  CommentRepository commentRepository;

  @Mock
  BoardRepository boardRepository;


  @InjectMocks
  CommentServiceImpl commentService;

  @InjectMocks
  BoardServiceImpl boardService;


  @Test
  @DisplayName("댓글 작성")
  void postComment(){
    //given
    TEST_USER.setId(5L);
    Board board = new Board(TEST_BOARD_REQUEST_DTO,TEST_USER);

    commentService = new CommentServiceImpl(commentRepository,boardService);
    Comment comment = new Comment(TEST_COMMENT_REQUEST_DTO,board,TEST_USER);


    //when
    when(boardRepository.findById(any())).thenReturn(Optional.of(board));
    when(commentRepository.save(any(Comment.class))).thenReturn(comment);
    CommentResponseDto commentResponseDto = commentService.createComment(anyLong(),TEST_COMMENT_REQUEST_DTO,TEST_USER);

    //then
    verify(commentRepository,times(1)).save(any(Comment.class));
    assertEquals(TEST_COMMENT_REQUEST_DTO.getComment(),commentResponseDto.getComment());
    assertEquals(TEST_USER.getNickname(),commentResponseDto.getNickname());


  }
  @Test
  @DisplayName("댓글 전체조회")
  void getComments(){
    //given
    TEST_USER.setId(5L);
    Board board = new Board(TEST_BOARD_REQUEST_DTO,TEST_USER);
    commentService = new CommentServiceImpl(commentRepository,boardService);
    Comment comment = new Comment(TEST_COMMENT_REQUEST_DTO,board,TEST_USER);
    List<Comment> commentList = new ArrayList<>();
    CommentPageDTO commentPageDTO =  CommentPageDTO.builder().commentPage(1).commentSize(5).commentSortBy("UserId").isAsc(true).build();


    //when
    when(commentRepository.findAllBy(commentPageDTO.toPageable())).thenReturn(commentList);
    List<CommentResponseDto> list = commentService.getComments(commentPageDTO);

    //then
    for (CommentResponseDto commentResponseDto : list){
      assertEquals(TEST_COMMENT_REQUEST_DTO.getComment(),commentResponseDto.getComment());
      assertEquals(TEST_USER.getNickname(),commentResponseDto.getNickname());
    }
  }


  @Test
  @DisplayName("댓글 수정")
  void updateComment(){
    //given
    TEST_USER.setId(5L);
    Board board = new Board(TEST_BOARD_REQUEST_DTO,TEST_USER);

    commentService = new CommentServiceImpl(commentRepository,boardService);
    Comment comment = new Comment(TEST_COMMENT_REQUEST_DTO,board,TEST_USER);


    Comment updatecomment = new Comment(TEST_COMMENT_REQUEST_DTO_UPDATE,board,TEST_USER);


    //when
    given(boardRepository.findById(any())).willReturn(Optional.of(board));
    when(commentRepository.findById(any())).thenReturn(Optional.of(updatecomment));
    CommentResponseDto updateResponseDto = commentService.updateComment(board.getId(),anyLong(),TEST_COMMENT_REQUEST_DTO_UPDATE,TEST_USER);

    //then
    assertNotEquals(TEST_COMMENT_REQUEST_DTO.getComment(),updateResponseDto.getComment());

  }

  @Test
  @DisplayName("댓글 삭제")
  void deleteComment(){
    //given
    TEST_USER.setId(5L);
    Board board = new Board(TEST_BOARD_REQUEST_DTO,TEST_USER);

    commentService = new CommentServiceImpl(commentRepository,boardService);
    Comment comment = new Comment(TEST_COMMENT_REQUEST_DTO,board,TEST_USER);

    //when
    when(commentRepository.findById(any())).thenReturn(Optional.of(comment));
    commentService.deleteComment(anyLong(),TEST_USER);

    //then
    verify(commentRepository,times(1)).delete(comment);


  }




}
