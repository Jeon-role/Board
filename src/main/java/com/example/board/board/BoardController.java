package com.example.board.board;

import static com.example.board.global.constant.ResponseCode.SUCCESS_BOARD;
import static com.example.board.global.constant.ResponseCode.SUCCESS_BOARD_DELETE;
import static com.example.board.global.constant.ResponseCode.SUCCESS_BOARD_FIND_ONE;
import static com.example.board.global.constant.ResponseCode.SUCCESS_BOARD_UPDATE;
import static com.example.board.global.constant.ResponseCode.SUCCESS_LIKES;
import static com.example.board.global.constant.ResponseCode.SUCCESS_LIKES_DELETE;

import com.example.board.global.dto.SuccessResponse;
import com.example.board.jwt.UserDetailsImpl;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class BoardController {

  private final BoardService boardService;

  @PostMapping("/boards")
  public ResponseEntity<SuccessResponse> createBoard(@RequestBody BoardRequestDto boardRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails){
     BoardResponseDto boardResponseDto = boardService.createBoard(boardRequestDto,userDetails.getUser());

    return ResponseEntity.status(SUCCESS_BOARD.getHttpStatus()).body(new SuccessResponse(SUCCESS_BOARD,boardResponseDto));
  }


  //전체조회
  @GetMapping("/boards")
  public ResponseEntity<List<BoardResponseDto>> getBoards(){
    return ResponseEntity.ok().body(boardService.getBoards());
  }

  //선택조회
  @GetMapping("/boards/{id}")
  public ResponseEntity<SuccessResponse> findOneBoard(@PathVariable Long id){
    BoardResponseDto boardResponseDto = boardService.findOneBoard(id);
    return ResponseEntity.status(SUCCESS_BOARD_FIND_ONE.getHttpStatus()).body(new SuccessResponse(SUCCESS_BOARD_FIND_ONE,boardResponseDto));
  }

  @PutMapping("/boards/{id}")
  public ResponseEntity<SuccessResponse> updateBoard(@PathVariable Long id,
      @RequestBody BoardRequestDto boardRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails){
    BoardResponseDto boardResponseDto = boardService.updateBoard(id,boardRequestDto,userDetails.getUser());
    return ResponseEntity.status(SUCCESS_BOARD_UPDATE.getHttpStatus()).body(new SuccessResponse(SUCCESS_BOARD_UPDATE,boardResponseDto));
  }

  @DeleteMapping("/boards/{id}")
  public ResponseEntity<SuccessResponse> deleteBoard(@PathVariable Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails){
    boardService.deleteBoard(id,userDetails.getUser());
    return ResponseEntity.status(SUCCESS_BOARD_DELETE.getHttpStatus()).body(new SuccessResponse(SUCCESS_BOARD_DELETE));
  }

  //좋아요 생성
  @PostMapping("/boards/{board_id}/likes")
  public ResponseEntity<SuccessResponse> createLikes(@PathVariable Long board_id,
      @AuthenticationPrincipal UserDetailsImpl userDetails){
    boardService.createLikes(board_id,userDetails.getUser());
    return ResponseEntity.status(SUCCESS_LIKES.getHttpStatus()).body(new SuccessResponse(SUCCESS_LIKES));
  }

  //좋아요 삭제
  @DeleteMapping("/boards/likes/{id}")
  public ResponseEntity<SuccessResponse> deleteLikes(@PathVariable Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails){
    boardService.deleteLikes(id,userDetails.getUser());
    return ResponseEntity.status(SUCCESS_LIKES_DELETE.getHttpStatus()).body(new SuccessResponse(SUCCESS_LIKES_DELETE));
  }




}
