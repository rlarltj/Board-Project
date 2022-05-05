package com.boardproject.ch4.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.boardproject.ch4.domain.BoardDto;
import com.boardproject.ch4.domain.PageHandler;
import com.boardproject.ch4.domain.SearchCondition;
import com.boardproject.ch4.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @GetMapping("/list")
    public String list(SearchCondition sc , Model m, HttpServletRequest request) {
        if(!loginCheck(request))
            return "redirect:/login/login?toURL="+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동


        try {
            int totalCnt = boardService.getSearchResultCnt(sc);
            PageHandler ph = new PageHandler(totalCnt, sc);

            List<BoardDto> list = boardService.getSearchResultPage(sc);
            m.addAttribute("totalCnt", totalCnt);
            m.addAttribute("list", list);
            m.addAttribute("ph", ph);
            m.addAttribute("page", sc.getPage());
            m.addAttribute("pageSize", sc.getPageSize());
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "boardList"; // 로그인을 한 상태이면, 게시판 화면으로 이동
    }

    private boolean loginCheck(HttpServletRequest request) {
        // 1. 세션을 얻어서
        HttpSession session = request.getSession();
        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session.getAttribute("id")!=null;
    }
    @GetMapping("/read")
    public String read(Integer bno, Model m, int page, int pageSize){

        try {
            BoardDto boardDto = boardService.read(bno);
            m.addAttribute("boardDto", boardDto);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/board";
    }

    @PostMapping("/remove")
    public String remove(Integer bno, Integer page, Integer pageSize, Model m,
                         HttpSession session, RedirectAttributes rattr){
        String writer = (String) session.getAttribute("id");

        try {
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);

            int rowCnt = boardService.remove(bno, writer);

            if(rowCnt != 1)
                throw new Exception("board remove error");

            rattr.addFlashAttribute("msg", "DEL_OK");
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "DEL_ERR");
        }

        return "redirect:/board/list";
    }

    @GetMapping("/write")
    public String write(Model m) {
        String mode = "new";
        m.addAttribute("mode", mode);
        return "board";  // 쓰기일 때는 mode == new;
    }

    @PostMapping("/write")
    public String write(BoardDto boardDto, HttpSession session, Model m, RedirectAttributes rattr) {

        String writer = (String) session.getAttribute("id");
        boardDto.setWriter(writer);

        try {
            int rowCnt = boardService.write(boardDto);

            if(rowCnt != 1) throw new Exception("Write failed");

            rattr.addFlashAttribute("msg", "WRT_OK");
            return "redirect:/board/list";

        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("boardDto", boardDto);
            m.addAttribute("msg", "WRT_ERR");

            return "board";
        }
    }

    @PostMapping("/modify")
    public String modify(BoardDto boardDto, HttpSession session, Model m, RedirectAttributes rattr
    , Integer page, Integer pageSize) {

        String writer = (String) session.getAttribute("id");
        boardDto.setWriter(writer);

        try {
            int rowCnt = boardService.modify(boardDto);

            if(rowCnt != 1) throw new Exception("Modify failed");

            rattr.addFlashAttribute("msg", "MOD_OK");
            rattr.addAttribute("page", page);
            rattr.addAttribute("pageSize", pageSize);
            return "redirect:/board/list";

        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("boardDto", boardDto);
            m.addAttribute("msg", "MOD_ERR");

            return "board";
        }
    }
}