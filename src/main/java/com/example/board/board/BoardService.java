package com.example.board.board;

import com.example.board.user.User;
import java.util.List;

public interface BoardService {

  BoardResponseDto createBoard(BoardRequestDto boardRequestDto, User user);

  List<BoardResponseDto> getBoards();

  BoardResponseDto findOneBoard(Long id);

  BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto, User user);

  void deleteBoard(Long id, User user);
  Board findId(Long id);

  void createLikes(Long boardId, User user);

  void deleteLikes(Long id, User user);
}
