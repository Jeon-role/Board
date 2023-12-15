package com.example.board.comment;

import com.example.board.board.Board;
import com.example.board.board.BoardService;
import com.example.board.global.constant.ErrorCode;
import com.example.board.global.exception.ApiException;
import com.example.board.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
  private final CommentRepository commentRepository;
  private final BoardService boardService;

  @Override
  public CommentResponseDto createComment(Long id, CommentRequestDto commentRequestDto, User user) {
    Board board = boardService.findId(id);
    Comment comment = commentRepository.save(new Comment(commentRequestDto,board,user));
    return new CommentResponseDto(comment);
  }

  @Transactional
  @Override
  public CommentResponseDto updateComment(Long boardId, Long id,
      CommentRequestDto commentRequestDto, User user) {
    boardService.findId(boardId);
    Comment comment = commentRepository.findById(id).orElseThrow(()-> new ApiException(ErrorCode.INVALID_COMMENT));
    if(comment.getUser().getNickname().equals(user.getNickname())){
      comment.update(commentRequestDto);
      return new CommentResponseDto(comment);
    }
    else {
      throw new ApiException(ErrorCode.INVALID_MADE);
    }
  }

  @Override
  public void deleteComment(Long id, User user) {
    Comment comment = commentRepository.findById(id).orElseThrow(()-> new ApiException(ErrorCode.INVALID_COMMENT));
    if(user.getNickname().equals(comment.getUser().getNickname())){
      commentRepository.delete(comment);
    }
    else {
      throw new ApiException(ErrorCode.INVALID_MADE);
    }
  }
}
