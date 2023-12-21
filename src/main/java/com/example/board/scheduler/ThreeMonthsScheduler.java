package com.example.board.scheduler;


import com.example.board.board.Board;
import com.example.board.board.BoardRepository;
import com.example.board.board.Likes;
import com.example.board.board.LikesRepository;
import com.example.board.comment.Comment;
import com.example.board.comment.CommentRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j(topic = "ThreeMonthsScheduler")
@Component
@RequiredArgsConstructor
public class ThreeMonthsScheduler {

  private final LikesRepository likesRepository;
  private final CommentRepository commentRepository;
  private final BoardRepository boardRepository;


  // 초, 분, 시, 일, 월, 주 순서
  @Scheduled(cron = "0 0 12 1/1 * * ")
  public void deleteAutoThreeMonths(){

    log.info("deleteAutoThreeMonths  실행1");

    List<Board> boardList = boardRepository.findAll();


    LocalDateTime realNow = LocalDateTime.now();

    if(!boardList.isEmpty()){
      log.info("deleteAutoThreeMonths  실행2");
      for(Board board : boardList){
        LocalDateTime threeMonths = board.getModifiedAt().plusDays(90);
        if(realNow.isAfter(threeMonths)){
          Scheduler scheduler= new Scheduler(likesRepository,commentRepository);
          scheduler.deleteCommentAndLikes(board.getId());
          boardRepository.delete(board);
        }
      }


      List<Comment> commentList = commentRepository.findAll();
      List<Likes> likesList = likesRepository.findAll();

      if(!commentList.isEmpty()){
        log.info("deleteAutoThreeMonths  실행3");
        for(Comment comment : commentList){
          LocalDateTime threeMonths = comment.getModifiedAt().plusDays(90);
          if(realNow.isAfter(threeMonths)){
            commentRepository.delete(comment);
          }
        }
      }

      if(!likesList.isEmpty()){
        log.info("deleteAutoThreeMonths  실행4");
        for(Likes likes : likesList){
          LocalDateTime threeMonths = likes.getModifiedAt().plusDays(90);
          if(realNow.isAfter(threeMonths)){
            likesRepository.delete(likes);
          }

        }
      }



    }




  }


}
