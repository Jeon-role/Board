package com.example.board.comment;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto  {
    private String comment;
    private String nickname;


    public CommentResponseDto(Comment comment){
        this.comment=comment.getComment();
        this.nickname=comment.getUser().getNickname();
    }
}
