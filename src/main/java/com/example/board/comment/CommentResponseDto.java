package com.example.board.comment;


import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto  {
    private String comment;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public CommentResponseDto(Comment comment){
        this.comment=comment.getComment();
        this.nickname=comment.getUser().getNickname();
        this.createdAt=comment.getCreatedAt();
        this.modifiedAt=comment.getModifiedAt();
    }
}
