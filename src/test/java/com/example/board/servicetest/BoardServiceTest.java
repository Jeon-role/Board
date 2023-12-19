package com.example.board.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


import com.example.board.board.Board;
import com.example.board.board.BoardRepository;
import com.example.board.board.BoardResponseDto;
import com.example.board.board.BoardServiceImpl;
import com.example.board.board.Likes;
import com.example.board.board.LikesRepository;
import com.example.board.comment.CommentRepository;
import com.example.board.test.BoardTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest implements BoardTest {

  @Mock
  BoardRepository boardRepository;
  @InjectMocks
  BoardServiceImpl boardService;

  @Mock
  LikesRepository likesRepository;

  @Mock
  CommentRepository commentRepository;

  @Test
  @DisplayName("게시판 작성")
  void postBoardTest(){
    //given
    TEST_USER.setId(5L);
    boardService = new BoardServiceImpl(boardRepository,likesRepository,commentRepository);
    Board boards = new Board(TEST_BOARD_REQUEST_DTO,TEST_USER);

    //when
    when(boardRepository.save(any(Board.class))).thenReturn(boards);
    BoardResponseDto responseDto = boardService.createBoard(TEST_BOARD_REQUEST_DTO,TEST_USER);

    //then
    verify(boardRepository,times(1)).save(any(Board.class));
    assertEquals(TEST_USER.getNickname(),responseDto.getNickname());
    assertEquals(TEST_BOARD_REQUEST_DTO.getTitle(),responseDto.getTitle());

  }

  @Test
  @DisplayName("게시판 수정")
  void updateBoardTest(){
    //given
    TEST_USER.setId(5L);
    boardService = new BoardServiceImpl(boardRepository,likesRepository,commentRepository);
    Board boards = new Board(TEST_BOARD_REQUEST_DTO,TEST_USER);
    when(boardRepository.save(any(Board.class))).thenReturn(boards);
    BoardResponseDto responseDto = boardService.createBoard(TEST_BOARD_REQUEST_DTO,TEST_USER);


    Board updateBoard = new Board(TEST_BOARD_REQUEST_DTO_UPDATE,TEST_USER);

    //when
    when(boardRepository.findById(any())).thenReturn(Optional.of(updateBoard));
    BoardResponseDto updateResponseDto = boardService.updateBoard(anyLong(),TEST_BOARD_REQUEST_DTO_UPDATE,TEST_USER);


    //then
    assertNotEquals(responseDto.getTitle(),updateResponseDto.getTitle());
    assertNotEquals(responseDto.getContents(),updateResponseDto.getContents());

  }
  @Test
  @DisplayName("게시판 삭제")
  void deleteBoardTest(){
    //given
    TEST_USER.setId(5L);
    boardService = new BoardServiceImpl(boardRepository,likesRepository,commentRepository);
    Board boards = new Board(TEST_BOARD_REQUEST_DTO,TEST_USER);
    when(boardRepository.save(any(Board.class))).thenReturn(boards);
    boardService.createBoard(TEST_BOARD_REQUEST_DTO,TEST_USER);
    //when
    when(boardRepository.findById(any())).thenReturn(Optional.of(boards));
    boardService.deleteBoard(anyLong(),TEST_USER);

    //then
    verify(boardRepository,times(1)).delete(boards);


  }
  @Test
  @DisplayName("게시판 전체 조회")
  void getBoardsTest(){
    //given
    TEST_USER.setId(5L);
    boardService = new BoardServiceImpl(boardRepository,likesRepository,commentRepository);

    Board board1 = new Board(TEST_BOARD_REQUEST_DTO,TEST_USER);
    when(boardRepository.save(any(Board.class))).thenReturn(board1);
    boardService.createBoard(TEST_BOARD_REQUEST_DTO,TEST_USER);
    List<Board> boardList = new ArrayList<>();


    //when-then
    when(boardRepository.findAllByOrderByCreatedAtDesc()).thenReturn(boardList);
    List<BoardResponseDto> List = boardService.getBoards();

    for(BoardResponseDto boardResponseDto : List){
      assertEquals(TEST_BOARD_REQUEST_DTO.getTitle(),boardResponseDto.getTitle());
      assertEquals(TEST_BOARD_REQUEST_DTO.getContents(),boardResponseDto.getContents());
      assertEquals(TEST_USER.getNickname(),boardResponseDto.getNickname());
    }

  }


  @Test
  @DisplayName("게시판 선택 조회")
  void findOneBoardTest(){
    //given
    TEST_USER.setId(5L);
    boardService = new BoardServiceImpl(boardRepository,likesRepository,commentRepository);
    Board boards = new Board(TEST_BOARD_REQUEST_DTO,TEST_USER);
    when(boardRepository.save(any(Board.class))).thenReturn(boards);
    boardService.createBoard(TEST_BOARD_REQUEST_DTO,TEST_USER);

    //when
    when(boardRepository.findById(any())).thenReturn(Optional.of(boards));
    BoardResponseDto responseDto = boardService.findOneBoard(anyLong());

    //then
    assertEquals(TEST_BOARD_REQUEST_DTO.getTitle(),responseDto.getTitle());
    assertEquals(TEST_BOARD_REQUEST_DTO.getContents(),responseDto.getContents());
  }

  @Test
  @DisplayName("좋아요 생성")
  void postLikes(){
    //given
    TEST_USER.setId(5L);
    boardService = new BoardServiceImpl(boardRepository,likesRepository,commentRepository);
    Board boards = new Board(TEST_BOARD_REQUEST_DTO,TEST_USER);
    when(boardRepository.save(any(Board.class))).thenReturn(boards);
    boardService.createBoard(TEST_BOARD_REQUEST_DTO,TEST_USER);


    Likes likes = new Likes(boards,TEST_USER);

    //when-then
    given(likesRepository.existsByBoardIdAndUserId(anyLong(),anyLong())).willReturn(false);
    when(boardRepository.findById(any())).thenReturn(Optional.of(boards));
    when(likesRepository.save(any(Likes.class))).thenReturn(likes);
    boardService.createLikes(anyLong(),TEST_USER);


  }

  @Test
  @DisplayName("좋아요 삭제")
  void deleteLikes(){
    //given
    TEST_USER.setId(5L);
    boardService = new BoardServiceImpl(boardRepository,likesRepository,commentRepository);
    Board boards = new Board(TEST_BOARD_REQUEST_DTO,TEST_USER);
    when(boardRepository.save(any(Board.class))).thenReturn(boards);
    boardService.createBoard(TEST_BOARD_REQUEST_DTO,TEST_USER);

    Likes likes = new Likes(boards,TEST_USER);
    given(likesRepository.existsByBoardIdAndUserId(anyLong(),anyLong())).willReturn(false);
    when(boardRepository.findById(any())).thenReturn(Optional.of(boards));
    when(likesRepository.save(any(Likes.class))).thenReturn(likes);
    boardService.createLikes(anyLong(),TEST_USER);


    //when
    when(likesRepository.findById(any())).thenReturn(Optional.of(likes));
    boardService.deleteLikes(anyLong(),TEST_USER);
    //then
    verify(likesRepository,times(1)).delete(likes);


  }

}
