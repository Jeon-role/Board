package com.example.board.board;

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
public class BoardPageDTO {

  private Integer currentPage;
  private Integer size;
  private String sortBy;
  private Boolean isAsc;







  public Pageable toPageable() {
    if (Objects.isNull(sortBy)) {
      return PageRequest.of(currentPage - 1, size);
    } else {
      if(isAsc){
        return PageRequest.of(currentPage - 1, size, Sort.by(sortBy).ascending());
      }
      else {
        return PageRequest.of(currentPage - 1, size, Sort.by(sortBy).descending());
      }

    }
  }

  public Pageable toPageable(String sortBy) {
    if(isAsc){
      return PageRequest.of(currentPage - 1, size, Sort.by(sortBy).ascending());
    }
    else {
      return PageRequest.of(currentPage - 1, size, Sort.by(sortBy).descending());
    }
  }








}