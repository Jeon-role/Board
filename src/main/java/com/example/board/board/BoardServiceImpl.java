package com.example.board.board;

import com.example.board.comment.Comment;
import com.example.board.comment.CommentResponseDto;
import com.example.board.global.constant.ErrorCode;
import com.example.board.global.exception.ApiException;
import com.example.board.user.User;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

  private final BoardRepository boardRepository;
  private final LikesRepository likesRepository;



  @Override
  public BoardResponseDto createBoard(BoardRequestDto boardRequestDto, User user) {
    Board board = new Board(boardRequestDto,user);
    Board saveBoard = boardRepository.save(board);
    return new BoardResponseDto(saveBoard);
  }

  @Override
  public List<BoardResponseDto> getBoards() {
    List<Board> boardList = boardRepository.findAllByOrderByCreatedAtDesc();
    List<BoardResponseDto> responseDtoList = new ArrayList<>();
    for(Board board : boardList){
      List<CommentResponseDto> commentList = new ArrayList<>();
      for(Comment comment : board.getCommentList()){
        commentList.add(new CommentResponseDto(comment));
      }
      responseDtoList.add(new BoardResponseDto(board,commentList));
    }

    return responseDtoList;
  }

  @Override
  public BoardResponseDto findOneBoard(Long id) {
    Board board = findId(id);
    List<CommentResponseDto> commentList = new ArrayList<>();
    for(Comment comment : board.getCommentList()){
      commentList.add(new CommentResponseDto(comment));
    }
    return new BoardResponseDto(board,commentList);
  }

  @Transactional
  @Override
  public BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto, User user) {
    Board board = findId(id);
    if(user.getNickname().equals(board.getUser().getNickname())){
      board.update(boardRequestDto);
      return new BoardResponseDto(board);
    }
    else {
      throw new ApiException(ErrorCode.INVALID_MADE);
    }
  }

  @Override
  public void deleteBoard(Long id, User user) {
    Board board = findId(id);
    if(user.getNickname().equals(board.getUser().getNickname())){
      boardRepository.delete(board);
    }
    else {
      throw new ApiException(ErrorCode.INVALID_MADE);
    }

  }

  @Override
  public Board findId(Long id){
    Board board = boardRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.INVALID_BOARD));
    return board;
  }

  @Override
  public void createLikes(Long boardId, User user) {
    Board board = findId(boardId);

    if(!likesRepository.existsByBoardIdAndUserId(boardId,user.getId())){
      Likes likes = new Likes(board,user);
      likesRepository.save(likes);
    }
    else {
      throw new ApiException(ErrorCode.INVALID_LIKES_EXIST);
    }
  }

  @Override
  public void deleteLikes(Long id, User user) {
    Likes likes = likesRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.INVALID_LIKES));
    if(user.getNickname().equals(likes.getUser().getNickname())){
      likesRepository.delete(likes);
    }
    else {
      throw new ApiException(ErrorCode.INVALID_MADE);
    }

  }


}
