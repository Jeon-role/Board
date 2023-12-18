package com.example.board.board;

import com.example.board.comment.CommentResponseDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardResponseDto {
  private String nickname;
  private String title;
  private String contents;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
  private List<CommentResponseDto> commentList = new ArrayList<>();
  private int likeCount;

  public BoardResponseDto(Board board){
    this.nickname=board.getUser().getNickname();
    this.title= board.getTitle();
    this.contents=board.getContents();
    this.createdAt=board.getCreatedAt();
    this.modifiedAt=board.getModifiedAt();
  }
  public BoardResponseDto(Board board,
      List<CommentResponseDto> commentList){
    this.nickname=board.getUser().getNickname();
    this.title= board.getTitle();
    this.contents=board.getContents();
    this.createdAt=board.getCreatedAt();
    this.modifiedAt=board.getModifiedAt();
    this.commentList=commentList;
    this.likeCount=board.getBoardLikeList().size();

  }



}
