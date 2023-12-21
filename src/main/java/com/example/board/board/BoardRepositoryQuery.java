package com.example.board.board;

import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryQuery {

  List<Board> search(String keyWord,Pageable pageable);

}
