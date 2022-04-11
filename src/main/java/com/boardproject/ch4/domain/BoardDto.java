package com.boardproject.ch4.domain;

import java.util.Date;
import java.util.Objects;


public class BoardDto {
    private Integer bno;
    private String title;
    private String content;
    private String writer;
    private int view_Cnt ;
    private int comment_Cnt;
    private Date reg_date;
    private Date up_date;

    public BoardDto() {
    }

    public BoardDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Integer getBno() {
        return bno;
    }

    public void setBno(Integer bno) {
        this.bno = bno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getView_cnt() {
        return view_Cnt;
    }

    public void setView_cnt(int view_Cnt) {
        this.view_Cnt = view_Cnt;
    }

    public int getComment_cnt() {
        System.out.println("댓글 수 메서드 호출!");
        return comment_Cnt;
    }

//    public void setCommentCnt(int comment_Cnt) {
//        this.comment_Cnt = comment_Cnt;
//    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public Date getUp_date() {
        return up_date;
    }

    public void setUp_date(Date up_date) {
        this.up_date = up_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDto boardDto = (BoardDto) o;
        return Objects.equals(bno, boardDto.bno) && Objects.equals(title, boardDto.title) && Objects.equals(content, boardDto.content) && Objects.equals(writer, boardDto.writer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bno, title, content, writer);
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "bno=" + bno +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", view_Cnt=" + view_Cnt +
                ", commentCnt=" + comment_Cnt +
                ", reg_date=" + reg_date +
                ", up_date=" + up_date +
                '}';
    }

}
