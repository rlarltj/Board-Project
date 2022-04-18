package com.boardproject.ch4.domain;

public class PageHandler {

    private SearchCondition sc;

    private int naviSize = 10;
    private int totalCnt;  // 전체 게시글 수
    private int totalPage; // 전체 페이지 수
    private int beginPage;
    private int endPage;

    boolean showPrev; // 이전 페이지의 링크를 보여줄 지 여부
    boolean showNext; // 다음 페이지의 링크를 보여줄 지 여부

    public PageHandler(int totalCnt, SearchCondition sc){
        this.totalCnt = totalCnt;
        this.sc = sc;

        doPaging(totalCnt, sc);
    }

    public void doPaging(int totalCnt, SearchCondition sc) {
        this.totalCnt = totalCnt;

        totalPage = (int) Math.ceil(totalCnt / (double)sc.getPageSize());
        beginPage = (sc.getPage()-1)/naviSize*naviSize + 1;
        endPage = Math.min(beginPage + naviSize -1, totalPage);

        showPrev = beginPage != 1;
        showNext = endPage != totalPage;

    }

    public SearchCondition getSc() {
        return sc;
    }

    public void setSc(SearchCondition sc) {
        this.sc = sc;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }


    public int getNaviSize() {
        return naviSize;
    }

    public void setNaviSize(int naviSize) {
        this.naviSize = naviSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isShowPrev() {
        return showPrev;
    }

    public void setShowPrev(boolean showPrev) {
        this.showPrev = showPrev;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    void print(){
        System.out.println(sc.getPage());
        System.out.print(showPrev? "[PREV] ": "");
        for(int i = beginPage; i<=endPage; i++){
            System.out.print(i+" ");
        }
        System.out.println(showNext? " [Next]": "");
    }



    @Override
    public String toString() {
        return "PageHandler{" +
                "sc=" + sc +
                ", naviSize=" + naviSize +
                ", totalCnt=" + totalCnt +
                ", totalPage=" + totalPage +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
}
