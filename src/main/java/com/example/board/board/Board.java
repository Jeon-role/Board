package com.example.board.board;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name="boards")
public class Board extends Timestamped {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String title;
  @Column(nullable = false)
  private String contents;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  public Board(BoardRequestDto boardRequestDto,User user){
    this.title= boardRequestDto.getTitle();
    this.contents=boardRequestDto.getContents();
    this.user=user;
  }

  public void update(BoardRequestDto boardRequestDto){
    this.title= boardRequestDto.getTitle();
    this.contents= boardRequestDto.getContents();
  }



}
