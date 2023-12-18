package com.example.board.board;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes,Long> {
  boolean existsByBoardIdAndUserId(Long boardId,Long userId);

  List<Likes> findAllByBoardId(Long boardId);

}
