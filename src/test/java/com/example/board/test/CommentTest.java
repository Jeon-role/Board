package com.example.board.test;


import com.example.board.comment.CommentRequestDto;


public interface CommentTest extends CommonTest{

  String TEST_COMMENT="화이팅이요 테스트";
  String TEST_COMMENT_UPDATE="화이팅이요 테스트 수정";
  CommentRequestDto TEST_COMMENT_REQUEST_DTO = CommentRequestDto.builder().comment(TEST_COMMENT).build();
  CommentRequestDto TEST_COMMENT_REQUEST_DTO_UPDATE = CommentRequestDto.builder().comment(TEST_COMMENT_UPDATE).build();



}
