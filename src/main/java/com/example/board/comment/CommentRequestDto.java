package com.example.board.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Getter
@Setter
public class CommentRequestDto {
    private Long id;
    private String comment;
}
