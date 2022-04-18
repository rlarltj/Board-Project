<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
<%--  <style rel="stylesheet" href="css/comment.css" />--%>


</head>
<body>
<h2>commentTest</h2>
comment: <input type="text" name="comment"><br>
<button id="sendBtn" type="button">SEND</button>
<button id="modBtn" type="button">수정</button>
<h2>Data From Server :</h2>
<div id="commentList"></div>
<div id="replyForm" style="display: none">
  <input type="text" name="replyComment">
  <button id="wrtRepBtn" type="button">등록</button>
</div>
<script>
  let bno = 782;
  let showList = function(bno){  // 댓글 목록을 가져와서 보여주는 함수 --> 게시물 번호가 필요하다.
    $.ajax({
      type:'GET',       // 요청 메서드
      url: '/ch4/comments?bno='+bno,  // 요청 URI
      // dataType : 'text', // 전송받을 데이터의 타입. 생략할 경우 json을 받는다. 따라서 아래의 stringify, parse가 필요없어짐.
      // data : JSON.stringify(person),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
      success : function(result){

        $("#commentList").html(toHtml(result));    // 서버로부터 응답이 도착하면 호출될 함수
               // result는 서버가 전송한 데이터
      },
      error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
    }); // $.ajax()
  }


  $(document).ready(function(){
    showList(bno);

    /**
     * 댓글 수정 기능
     */
    // 수정 버튼
    $("#modBtn").click(function(){

      let cno = $(this).attr("data-cno");
      let comment = $("input[name=comment]").val();
      if(comment.trim() == ''){
        alert("댓글을 입력해주세요.");
        $("input[name=comment]").focus();
        return;
      }

      $.ajax({
        type:'PATCH',       // 요청 메서드
        url: '/ch4/comments/' +cno, // 요청 URI
        headers : {"content-type": "application/json"},
        data : JSON.stringify({cno: cno, comment: comment}),
        success : function(result){
          showList(bno);
        },
        error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
      }); // $.ajax()

      alert("the request is sent")
    });

    /**
     * 댓글 수정 기능
     */
    $("#commentList").on("click", ".modBtn", function() {

      let cno = $(this).parent().attr("data-cno");
      let comment = $("span.comment", $(this).parent()).text();
      //1. comment의 내용을 input으로 뿌려준다.
      $("input[name=comment]").val(comment);

      //2. cno를 전달한다.(어떤 댓글을 수정할 지 알아야 함)
      $("#modBtn").attr("data-cno", cno);
    });


    /**
     * 댓글 등록 기능
     */
    // SEND 버튼
    $("#sendBtn").click(function(){
      let comment = $("input[name=comment]").val();

      if(comment.trim() == ''){
        alert("댓글을 입력해주세요.");
        $("input[name=comment]").focus();
        return;
      }

      $.ajax({
        type:'POST',       // 요청 메서드
        url: '/ch4/comments?bno='+bno,  // 요청 URI
        headers : {"content-type": "application/json"},
        data : JSON.stringify({bno: bno, comment: comment}),
        success : function(result){
          showList(bno);
        },
        error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
      }); // $.ajax()

      alert("the request is sent")
    });

    /**
     * 답글 기능
      */
    $("#wrtRepBtn").click(function(){
      let comment = $("input[name=replyComment]").val();
      let pcno = $("#replyForm").parent().attr("data-pcno");

      if(comment.trim() == ''){
        alert("댓글을 입력해주세요.");
        $("input[name=replyComment]").focus();
        return;
      }

      $.ajax({
        type:'POST',       // 요청 메서드
        url: '/ch4/comments?bno='+bno,  // 요청 URI
        headers : {"content-type": "application/json"},
        data : JSON.stringify({pcno: pcno, bno: bno, comment: comment}),
        success : function(result){
          showList(bno);
        },
        error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
      }); // $.ajax()

      $("#replyForm").css("display", "none");
      $("input[name=replyComment]").val('');
      $("#replyForm").appendTo("body");
    });
    /**
     * 답글 기능
     */
$("#commentList").on("click", ".replyBtn", function() {
  // 1. replyForm을 해당 댓글 아래로 옮기고 보여준다.
  $("#replyForm").appendTo($(this).parent());

  // 2. 답글을 입력할 폼을 보여준다.
  $("#replyForm").css("display", "block");

});

    /**
     * 댓글 삭제 기능
     */
$("#commentList").on("click", ".delBtn", function(){

  let cno = $(this).parent().attr("data-cno");
  let bno = $(this).parent().attr("data-bno");
  console.log(cno);
  console.log($(this).parent());
  $.ajax({
    type:'DELETE',       // 요청 메서드
    url: '/ch4/comments/'+cno+'?bno='+bno,  // 요청 URI
    success : function(result){
        showList(bno);
    },
    error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
  }); // $.ajax()


})


    /**
     * 댓글 등록 기능
     */
  $("#sendBtn").click(function(){
    let comment = $("input[name=comment]").val();

    if(comment.trim() == ''){
      alert("댓글을 입력해주세요.");
      $("input[name=comment]").focus();
      return;
    }


    $.ajax({
      type:'POST',       // 요청 메서드
      url: '/comments?bno='+bno,  // 요청 URI
      headers : {"content-type": "application/json"},
      data : JSON.stringify({bno: bno, comment: comment}),
      success : function(result){
        showList(bno);
      },
      error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
    }); // $.ajax()

    alert("the request is sent")
  });

});


  /**
   * 댓글 창 HTML
   */
  let toHtml = function(comments){
    let tmp = "<ul>";

    comments.forEach((com) => {
        tmp +=
                `<li data-cno= \${com.cno}
                    data-pcno=\${com.pcno}
                    data-bno= \${com.bno}>
                    \${com.cno != com.pcno ? 'ㄴ' : ''}
                    commenter=<span class="commenter"> \${com.commenter} </span>
                    comment =<span class="comment">  \${com.comment} </span>
                    update= \${com.up_date}
                    <button class="delBtn">삭제</button>
                    <button class="modBtn">수정</button>
                    <button class="replyBtn">답글</button>
                </li>
        `
    })
    return tmp +"</ul>";
  }
</script>
</body>
</html>