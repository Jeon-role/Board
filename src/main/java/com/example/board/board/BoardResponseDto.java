package com.example.board.board;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BoardResponseDto {
  private String nickname;
  private String title;
  private String contents;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

  public BoardResponseDto(Board board){
    this.nickname=board.getUser().getNickname();
    this.title= board.getTitle();
    this.contents=board.getContents();
    this.createdAt=board.getCreatedAt();
    this.modifiedAt=board.getModifiedAt();
  }



}
