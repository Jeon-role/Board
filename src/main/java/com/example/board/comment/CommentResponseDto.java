package com.example.board.comment;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto  {
    private String comment;
    private String username;


    public CommentResponseDto(Comment comment){
        this.comment=comment.getComment();
        this.username=comment.getUser().getNickname();
    }
}
