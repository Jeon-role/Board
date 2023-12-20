package com.example.board.comment;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class CommentPageDTO {

  private Integer commentPage;
  private Integer commentSize;
  private String commentSortBy;
  private Boolean isAsc;


  public Pageable toPageable() {
    if (Objects.isNull(commentSortBy)) {
      return PageRequest.of(commentPage - 1, commentSize);
    } else {
      if(isAsc){
        return PageRequest.of(commentPage - 1, commentSize, Sort.by(commentSortBy).ascending());
      }
      else {
        return PageRequest.of(commentPage - 1, commentSize, Sort.by(commentSortBy).descending());
      }

    }
  }

  public Pageable toPageable(String sortBy) {
    if(isAsc){
      return PageRequest.of(commentPage - 1, commentSize, Sort.by(sortBy).ascending());
    }
    else {
      return PageRequest.of(commentPage - 1, commentSize, Sort.by(sortBy).descending());
    }
  }

}
