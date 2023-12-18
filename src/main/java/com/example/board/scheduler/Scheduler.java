package com.example.board.scheduler;

import com.example.board.board.Likes;
import com.example.board.board.LikesRepository;
import com.example.board.comment.Comment;
import com.example.board.comment.CommentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j(topic = "Scheduler")
@Component
@RequiredArgsConstructor
public class Scheduler {

    private final LikesRepository likesRepository;
    private final CommentRepository commentRepository;


    public void deleteCommentAndLikes(Long boardId){
        log.info("deleteCommentAndLikes  실행");
        List<Comment> commentList = commentRepository.findAllByBoardId(boardId);
        List<Likes> likesList = likesRepository.findAllByBoardId(boardId);

        if(!commentList.isEmpty()){
            commentRepository.deleteAll(commentList);
        }
        if(!likesList.isEmpty()){
            likesRepository.deleteAll(likesList);
        }
    }

}
