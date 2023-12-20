package com.example.board.comment;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

  List<Comment> findAllByBoardId(Long boardId);

  List<Comment> findAllBy(Pageable pageable);

}
