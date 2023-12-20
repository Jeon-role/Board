package com.example.board.comment;

import com.example.board.user.User;
import java.util.List;

public interface CommentService {

  CommentResponseDto createComment(Long id, CommentRequestDto commentRequestDto, User user);

  CommentResponseDto updateComment(Long boardId, Long id, CommentRequestDto commentRequestDto, User user);

  void deleteComment(Long id, User user);

  List<CommentResponseDto> getComments(CommentPageDTO commentPageDTO);

}
