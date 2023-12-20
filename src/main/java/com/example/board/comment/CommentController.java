package com.example.board.comment;

import static com.example.board.global.constant.ResponseCode.SUCCESS_COMMENT;
import static com.example.board.global.constant.ResponseCode.SUCCESS_COMMENT_DELETE;
import static com.example.board.global.constant.ResponseCode.SUCCESS_COMMENT_UPDATE;


import com.example.board.board.Likes;
import com.example.board.global.dto.SuccessResponse;
import com.example.board.jwt.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/boards")
public class CommentController {
  private final CommentService commentService;


  @PostMapping("/{id}/comment")
  public ResponseEntity<SuccessResponse> createComment(@PathVariable Long id,
      @RequestBody CommentRequestDto commentRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails){
    CommentResponseDto responseDto = commentService.createComment(id,commentRequestDto,userDetails.getUser());
    return ResponseEntity.status(SUCCESS_COMMENT.getHttpStatus()).body(new SuccessResponse(SUCCESS_COMMENT,responseDto));
  }

  @GetMapping("/comments")
  public ResponseEntity<List<CommentResponseDto>> getComments(@RequestBody CommentPageDTO commentPageDTO){
    return ResponseEntity.ok().body(commentService.getComments(commentPageDTO));
  }

  @PutMapping("/{board_id}/comment/{id}")
  public ResponseEntity<SuccessResponse> updateComment(@PathVariable Long board_id,@PathVariable Long id,
      @RequestBody CommentRequestDto commentRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails){
    CommentResponseDto responseDto = commentService.updateComment(board_id,id,commentRequestDto,userDetails.getUser());
    return ResponseEntity.status(SUCCESS_COMMENT_UPDATE.getHttpStatus()).body(new SuccessResponse(SUCCESS_COMMENT_UPDATE,responseDto));
  }

  @DeleteMapping("/comment/{id}")
  public ResponseEntity<SuccessResponse> deleteComment(@PathVariable Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails){
    commentService.deleteComment(id,userDetails.getUser());
    return ResponseEntity.status(SUCCESS_COMMENT_DELETE.getHttpStatus()).body(new SuccessResponse(SUCCESS_COMMENT_DELETE));
  }



}
