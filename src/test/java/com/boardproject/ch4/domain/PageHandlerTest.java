//package com.fastcampus.ch4.domain;
//
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class PageHandlerTest {
//
//    @Test
//    public void test() {
//        PageHandler ph = new PageHandler(250, 11);
//        assertTrue(ph.getBeginPage() == 11);
//        assertTrue(ph.getEndPage() == 20);
//        ph.print();
//    }
//
//    @Test
//    public void test2() {
//        PageHandler ph = new PageHandler(255, 25);
//        ph.print();
//        assertTrue(ph.getBeginPage() == 21);
//        assertTrue(ph.getEndPage() == 26);
//    }
//
//    @Test
//    public void goBefore(){
//        PageHandler ph = new PageHandler(255, 10, 10);
//
//        ph.print();
//
////        assertTrue(ph.getBeginPage() == )
//
//    }
//}