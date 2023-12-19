package com.example.board.comment;

import com.example.board.board.Board;
import com.example.board.common.Timestamped;
import com.example.board.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name="comments")
public class Comment extends Timestamped {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String comment;


  @ManyToOne
  @JoinColumn(name = "board_id", nullable = false)
  private Board board;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
  @Builder
  public Comment(String comment,Board board,User user){
    this.comment=comment;
    this.board=board;
    this.user=user;
  }

  public Comment(CommentRequestDto commentRequestDto,Board board,User user){
    this.comment=commentRequestDto.getComment();
    this.board=board;
    this.user=user;
  }
  public void update(CommentRequestDto commentRequestDto){
    this.comment=commentRequestDto.getComment();
  }


}
