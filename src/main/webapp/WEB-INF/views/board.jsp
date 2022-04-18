<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page session="true"%>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId=='' ? 'Login' : 'Logout'}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fastcampus</title>
    <link rel="stylesheet" href="<c:url value='/css/menu2.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/comment2.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: "Noto Sans KR", sans-serif;
        }
        body{
            height: 100%;
        }
        .container {
            width : 50%;
            margin : auto;
        }
        .writing-header {
            position: relative;
            margin: 20px 0 0 0;
            padding-bottom: 10px;
            border-bottom: 1px solid #323232;
        }
        input {
            width: 100%;
            height: 35px;
            margin: 5px 0px 10px 0px;
            border: 1px solid #e9e8e8;
            padding: 8px;
            background: #f8f8f8;
            outline-color: #e6e6e6;
        }
        textarea {
            width: 100%;
            background: #f8f8f8;
            margin: 5px 0px 10px 0px;
            border: 1px solid #e9e8e8;
            resize: none;
            padding: 8px;
            outline-color: #e6e6e6;
        }
        .frm {
            width:100%;
        }
        .btn {
            background-color: rgb(236, 236, 236); /* Blue background */
            border: none; /* Remove borders */
            color: black; /* White text */
            padding: 6px 12px; /* Some padding */
            font-size: 16px; /* Set a font size */
            cursor: pointer; /* Mouse pointer on hover */
            border-radius: 5px;
        }
        .btn:hover {
            text-decoration: underline;
        }


        #commentList > ul{
            border:  1px solid rgb(235,236,239);
            border-bottom : 0;
        }
        #commentList > li {
            background-color: #f9f9fa;
            list-style-type: none;
            border-bottom : 1px solid rgb(235,236,239);
            padding : 18px 18px 0 18px;
        }
    </style>
</head>
<body>
<div id="menu">
    <ul id="ul">
        <li id="logo">About</li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <li><a href="<c:url value='/board/list'/>">Board</a></li>
        <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
        <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
        <li><a href=""><i class="fa fa-search"></i></a></li>
    </ul>
</div>
<script>
    let msg = "${msg}";
    if(msg=="WRT_ERR") alert("게시물 등록에 실패하였습니다. 다시 시도해 주세요.");
    if(msg=="MOD_ERR") alert("게시물 수정에 실패하였습니다. 다시 시도해 주세요.");
</script>
<div class="container">
    <h2 class="writing-header">게시판 ${mode=="new" ? "글쓰기" : "읽기"}</h2>
    <form id="form" class="frm" action="" method="post">
        <input type="hidden" name="bno" value="${boardDto.bno}">

        <input name="title" type="text" value="${boardDto.title}" placeholder="  제목을 입력해 주세요." ${mode=="new" ? "" : "readonly='readonly'"}><br>
        <textarea name="content" rows="20" placeholder=" 내용을 입력해 주세요." ${mode=="new" ? "" : "readonly='readonly'"}>${boardDto.content}</textarea><br>

        <c:if test="${mode eq 'new'}">
            <button type="button" id="writeBtn" class="btn btn-write"><i class="fa fa-pencil"></i> 등록</button>
        </c:if>
        <c:if test="${mode ne 'new'}">
            <button type="button" id="writeNewBtn" class="btn btn-write"><i class="fa fa-pencil"></i> 글쓰기</button>
        </c:if>
        <c:if test="${boardDto.writer eq loginId}">
            <button type="button" id="modifyBtn" class="btn btn-modify"><i class="fa fa-edit"></i> 수정</button>
            <button type="button" id="removeBtn" class="btn btn-remove"><i class="fa fa-trash"></i> 삭제</button>
        </c:if>
        <button type="button" id="listBtn" class="btn btn-list"><i class="fa fa-bars"></i> 목록</button>
    </form>
    <div id="comment-writebox" style="margin-top: 30px">
        <div class="commenter commenter-writebox">${id}</div>
        <div class="comment-writebox-content">
            <textarea name="comment"  cols="30" rows="3" placeholder="댓글을 남겨보세요"></textarea>
        </div>
        <div id="comment-writebox-bottom">
            <div class="register-box">
                <a href="#" class="btn" id="btn-write-comment">등록</a>
            </div>
        </div>
    </div>
    <div id="commentList" style="height: 800px; width: 100%">

    </div>
    <div id="reply-writebox">
        <div class="commenter commenter-writebox">${id}</div>
        <div class="reply-writebox-content">
            <textarea name="" cols="30" rows="3" placeholder="댓글을 남겨보세요"></textarea>
        </div>
        <div id="reply-writebox-bottom">
            <div class="register-box">
                <a href="#" class="btn" id="btn-write-reply">등록</a>
                <a href="#" class="btn" id="btn-cancel-reply">취소</a>
            </div>
        </div>
    </div>
    <div id="modalWin" class="modal">
        <!-- Modal content -->
        <div class="modal-content">
            <span class="close">&times;</span>
            <p>
            <h2> | 댓글 수정</h2>
            <div id="modify-writebox">
                <div class="commenter commenter-writebox"></div>
                <div class="modify-writebox-content">
                    <textarea name="" id="" cols="30" rows="5" placeholder="댓글을 남겨보세요"></textarea>
                </div>
                <div id="modify-writebox-bottom">
                    <div class="register-box">
                        <a href="#" class="btn" id="btn-write-modify">등록</a>
                    </div>
                </div>
            </div>
            </p>
        </div>
    </div>
</div>
<div id="mycommentList"></div>
<script>
    let bno = 782;

    $(document).ready(function(){
        showList(bno);
        let formCheck = function() {
            let form = document.getElementById("form");
            if(form.title.value=="") {
                alert("제목을 입력해 주세요.");
                form.title.focus();
                return false;
            }
            if(form.content.value=="") {
                alert("내용을 입력해 주세요.");
                form.content.focus();
                return false;
            }
            return true;
        }
        $("#writeNewBtn").on("click", function(){
            location.href="<c:url value='/board/write'/>";
        });
        $("#writeBtn").on("click", function(){
            let form = $("#form");
            form.attr("action", "<c:url value='/board/write'/>");
            form.attr("method", "post");
            if(formCheck())
                form.submit();
        });
        $("#modifyBtn").on("click", function(){
            let form = $("#form");
            let isReadonly = $("input[name=title]").attr('readonly');
            // 1. 읽기 상태이면, 수정 상태로 변경
            if(isReadonly=='readonly') {
                $(".writing-header").html("게시판 수정");
                $("input[name=title]").attr('readonly', false);
                $("textarea").attr('readonly', false);
                $("#modifyBtn").html("<i class='fa fa-pencil'></i> 등록");
                return;
            }
            // 2. 수정 상태이면, 수정된 내용을 서버로 전송
            form.attr("action", "<c:url value='/board/modify${searchCondition.queryString}'/>");
            form.attr("method", "post");
            if(formCheck())
                form.submit();
        });
        $("#removeBtn").on("click", function(){
            if(!confirm("정말로 삭제하시겠습니까?")) return;
            let form = $("#form");
            form.attr("action", "<c:url value='/board/remove${searchCondition.queryString}'/>");
            form.attr("method", "post");
            form.submit();
        });
        $("#listBtn").on("click", function(){
            location.href="<c:url value='/board/list${searchCondition.queryString}'/>";
        });


        // 댓글 구현
        $("#commListBtn").on("click", function (){
            $.ajax({
                type: 'GET',
                url: 'comments?bno='+782,
                // headers : {"content-type": "application/json"},
                // data : JSON.stringify({bno: 782}),
                success : function(result){
                    $("#mycommentList").html(toHtml(result));
                },
                error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
            }); // $.ajax()
            alert("the request is sent")
        });

        //댓글 등록하기
        $("#btn-write-comment").on("click", ()=>{
            let 댓글창 = $("textarea[name=comment]");
            let comment = 댓글창.val();
            console.log(comment);
            $.ajax({
                type: 'POST',
                url: 'comments?bno='+bno,
                headers : {"content-type": "application/json"},
                data : JSON.stringify({bno: 782, comment: comment}),
                success : function(result){
                    alert("댓글이 등록되었슈");
                    showList(bno);
                    댓글창.innerText("");
                },
                error : function(){ alert("error!")}
            });
        });


        /**
         * 댓글 삭제하기
         */
        $("#commentList").on("click", ".delBtn", function(){

            let cno = $(this).parent().attr("data-cno");
            let bno = $(this).parent().attr("data-bno");
            console.log(cno);
            console.log($(this).parent());
            $.ajax({
                type:'DELETE',       // 요청 메서드
                url: '/ch4/board/comments/'+cno+'?bno='+bno,  // 요청 URI
                success : function(result){
                    showList(bno);
                },
                error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
            }); // $.ajax()

    });
    })


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

    //댓글을 뿌려주는 메서드
    let showList = function(bno){  // 댓글 목록을 가져와서 보여주는 함수 --> 게시물 번호가 필요하다.
        $.ajax({
            type:'GET',       // 요청 메서드
            url: 'comments?bno='+bno,  // 요청 URI
            success : function(result){

                $("#commentList").html(toHtml(result));    // 서버로부터 응답이 도착하면 호출될 함수
                // result는 서버가 전송한 데이터
            },
            error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
        }); // $.ajax()
    }
</script>
</body>
</html>