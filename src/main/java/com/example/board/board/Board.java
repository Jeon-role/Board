package com.example.board.board;

import com.example.board.comment.Comment;
import com.example.board.common.Timestamped;
import com.example.board.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name="boards")
public class Board extends Timestamped implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false,length = 500)
  private String title;
  @Column(nullable = false,length = 5000)
  private String contents;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
  private List<Comment> commentList = new ArrayList<>();

  @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
  private List<Likes> boardLikeList = new ArrayList<>();

  @Builder
  public Board(String title,String contents,User user){
    this.title=title;
    this.contents=contents;
    this.user=user;
  }


  @Builder
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
