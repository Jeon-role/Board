package com.example.board.board;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


//@RepositoryDefinition(domainClass = Board.class, idClass = Long.class)
public interface BoardRepository extends JpaRepository<Board,Long> ,BoardRepositoryQuery{

  List<Board> findAllBy(Pageable pageable);


}
