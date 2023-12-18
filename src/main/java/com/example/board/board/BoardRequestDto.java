package com.example.board.board;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Getter
@Setter
public class BoardRequestDto {
  private String title;
  private String contents;
}
