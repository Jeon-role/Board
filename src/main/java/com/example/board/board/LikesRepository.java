package com.example.board.board;


import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes,Long> {
  boolean existsByBoardIdAndUserId(Long boardId,Long userId);

}
