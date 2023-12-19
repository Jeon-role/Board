package com.example.board.test;

import com.example.board.board.Board;
import com.example.board.board.BoardRequestDto;
import com.example.board.board.BoardResponseDto;


public interface BoardTest extends CommonTest {

	Long TEST_TODO_ID = 1L;
	String TEST_BOARD_TITLE = "title";
	String TEST_BOARD_CONTENT = "content";

	String TEST_BOARD_TITLE_UPDATE = "title update";
	String TEST_BOARD_CONTENT_UPDATE = "content update";

	BoardRequestDto TEST_BOARD_REQUEST_DTO = BoardRequestDto.builder()
		.title(TEST_BOARD_TITLE)
		.contents(TEST_BOARD_CONTENT)
		.build();

	BoardRequestDto TEST_BOARD_REQUEST_DTO_UPDATE = BoardRequestDto.builder()
			.title(TEST_BOARD_TITLE_UPDATE)
			.contents(TEST_BOARD_CONTENT_UPDATE)
			.build();


	Board board= Board.builder().user(TEST_USER).title(TEST_BOARD_TITLE).contents(TEST_BOARD_CONTENT).build();
	BoardResponseDto TEST_BOARD_RESPONSE_DTO =new  BoardResponseDto(board);


}
