package com.example.board.board;


import com.querydsl.core.types.dsl.BooleanExpression;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import static com.example.board.board.QBoard.board;



@RequiredArgsConstructor
public class BoardRepositoryQueryImpl implements BoardRepositoryQuery {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<Board> search(String keyWord, Pageable pageable) {
    //title or  contents 에서 key

    List<Board> list = jpaQueryFactory
        .select(board)
        .from(board)
        .where(containsSearchTitle(keyWord).or(containsSearchContents(keyWord)))
        .orderBy(board.id.desc())
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    return list;
  }


  private BooleanExpression containsSearchTitle(String keWord){
    return keWord != null ? board.title.contains(keWord) : null;
  }
  private BooleanExpression containsSearchContents(String keWord){
    return keWord != null ? board.contents.contains(keWord) : null;
  }

}
