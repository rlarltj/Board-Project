package com.boardproject.ch4.controller;

import com.boardproject.ch4.domain.CommentDto;
import com.boardproject.ch4.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;

    // 댓글 수정 메서드
    @ResponseBody
    @PatchMapping("/comments/{cno}")
    public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody CommentDto dto,
                                         HttpSession session){
        //String commenter = (String) session.getAttribute("id");
        String commenter = "kiseo";
        dto.setCommenter(commenter);
//        System.out.println("cno= "+ cno);
        dto.setCno(cno);
        System.out.println("dto = " +dto);
        try {
            if(commentService.modify(dto) != 1){
                throw new Exception("Modify Failed.");
            }

            return new ResponseEntity<String>("MOD_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    //댓글 등록 메서드
    @ResponseBody
    @PostMapping("/comments")
    public ResponseEntity<String> write(@RequestBody CommentDto dto, Integer bno, HttpSession session){
        //String commenter = (String) session.getAttribute("id");
        String commenter = "kiseo";
        dto.setCommenter(commenter);
        dto.setBno(bno);
        try {
            if(commentService.write(dto) != 1){
                throw new Exception("Write Failed.");
            }

            return new ResponseEntity<String>("WRT_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("WRT_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    //댓글 삭제 메서드
    @ResponseBody
    @DeleteMapping("/comments/{cno}")  //    /comments/1 <-- 삭제할 댓글 번호
    public ResponseEntity<String> remove(HttpSession session, @PathVariable Integer cno, Integer bno) {
//        String commenter = (String) session.getAttribute("id");
        String commenter = "kiseo";
        try {
            int rowCnt = commentService.remove(cno, bno, commenter);

            if(rowCnt != 1 ){
                throw new Exception("Delete Failed");
            }
            
            return new ResponseEntity<>("DEL_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("DEL_ERR", HttpStatus.BAD_REQUEST);
        }
    }


    @ResponseBody
    @RequestMapping("/comments")    // comments?bno=?1080    GET
    public ResponseEntity<List<CommentDto>> list(Integer bno){
        List<CommentDto> list = null;
        try {
            list = commentService.getList(bno);
            return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK); //400

        } catch (Exception e) {
            e.printStackTrace();
             return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST); //400
        }
    }
}
