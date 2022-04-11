package com.boardproject.ch4.dao;

import com.boardproject.ch4.domain.BoardDto;
import com.boardproject.ch4.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface BoardDao {
    BoardDto select(int bno) throws Exception;

    int count() throws Exception;

    int insert(BoardDto boardDto) throws Exception;

    int deleteAll() throws Exception;

    int delete(Integer bno, String writer) throws Exception;

    List<BoardDto> selectAll() throws Exception;

    List<BoardDto> selectPage(Map map) throws Exception;

    int update(BoardDto boardDto) throws Exception;

//    int updateCommentCnt(Map map) throws Exception;

    int increaseViewCnt(int bno) throws Exception;

    List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception;

    int searchResultCnt(SearchCondition sc) throws Exception;

    int updateCommentCnt(Integer bno, int cnt);
}
