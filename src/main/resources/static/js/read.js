console.log("읽기 시작")
const boardId = parseInt(document.getElementById("boardId").value)

const $writer = document.getElementById("writer")
const $insertDate = document.getElementById("insertDate")
const $view = document.getElementById("view")
const $title = document.getElementById("title")
const $content = document.getElementById("content")

const $btnAria = document.getElementById("btnAria")

const $comment = document.getElementById("comment")

const $commentList = document.getElementById('commentList');
//함수 실행
get_boardRowData();

async function get_boardRowData() {

    const rowMap = {

        boardId
    }

    try {
        const res = await axios.post("/api/v1/board/readPost", rowMap)

        if (res.data.status === "SUCCESS") {
            //게시글 데이터 획득
            console.log(res.data.data)
            create_dom(res.data.data);
            renderComments(res.data.data.commentList)

        } else {

            //게시글없음
            window.location.href = "/board"
        }
    } catch (e) {
        //에러
        window.location.href = "/board"

    }


}


const create_dom = (boardMap) => {


    $writer.innerHTML = `<strong>작성자:</strong> ${boardMap.boardWriter}`
    $insertDate.innerHTML = `<strong>작성일:</strong> ${boardMap.boardInsertDate}`
    $view.innerHTML = `<strong>조회수:</strong>  ${boardMap.boardView}`
    $title.innerHTML = `${boardMap.boardTitle}`
    $content.innerHTML = ` ${boardMap.boardContent}`


}


//비밀번호 확인
const check_pw_update = async () => {

    const boardPw = prompt("비밀번호를 입려해주세요", "")

    const valMap = {
        boardPw, boardId
    }
    try {
        const res = await axios.post("/api/v1/board/checkPw", valMap)

        if (res.data.status === "SUCCESS") {
            //게시글 데이터 획득

            create_updateDom();


        }
    } catch (e) {
        //에러
        errorAlert(e)

    }


}
const create_updateDom = () => {
    $content.disabled = false
    $btnAria.innerHTML = `
     <a href="/board"
               class="inline-block bg-gray-300 hover:bg-gray-400 text-gray-800 px-5 py-2 rounded">
                목록으로
        </a>
        <a onclick="update()"
           class="inline-block bg-green-500 hover:bg-green-600 text-white px-5 py-2 rounded">
            변경
        </a>
        <button  onclick="delete_post()"
                class="inline-block bg-red-500 hover:bg-red-600 text-white px-5 py-2 rounded">
            삭제
        </button>
    `

}

//게시글 내용 수정
const update = async () => {


    const updateMap = {
        boardId,
        content: $content.value
    }
    try {
        const res = await axios.post("/api/v1/board/update", updateMap)

        if (res.data.status === "SUCCESS") {
            //게시글 내용 변경
            window.location.reload();

        }
    } catch (e) {
        //에러
        errorAlert(e)

    }

}

const delete_post = async () => {

    const boardPw = prompt("비밀번호를 입력해주세요", "");

    const deleteMap = {
        boardId,
        boardPw
    }
    try {
        const res = await axios.post("/api/v1/board/delete", deleteMap)

        if (res.data.status === "SUCCESS") {
            //게시글 내용 변경
            window.location.href = "/board"

        }
    } catch (e) {
        //에러
        errorAlert(e)

    }

}

const save_comment = async () => {


    const commentMap = {

        boardId,
        commentContent: $comment.value
    }


    try {
        const res = await axios.post("/api/v1/comment/save", commentMap)

        if (res.data.status === "SUCCESS") {
            //댓글 등록
            // window.location.href = "/board"
            window.location.reload();
        }
    } catch (e) {
        //에러
        errorAlert(e)

    }


}

// 댓글 렌더링
function renderComments(commentList) {
    const $commentList = document.getElementById("commentList");
    $commentList.innerHTML = '';

    // 댓글 정렬 (내림차순)
    commentList.sort((a, b) => b.commentId - a.commentId);

    // 댓글 분리
    const topLevel = commentList.filter(c => c.parentId === 0);
    const childrenMap = {};
    commentList.forEach(c => {
        if (c.parentId !== 0) {
            if (!childrenMap[c.parentId]) {
                childrenMap[c.parentId] = [];
            }
            childrenMap[c.parentId].push(c);
        }
    });

    topLevel.forEach(comment => {
        const commentDiv = document.createElement('div');
        commentDiv.className = 'comment border p-4 rounded bg-gray-50';
        commentDiv.dataset.commentId = comment.commentId;

        commentDiv.innerHTML = `
          <div class="text-sm font-semibold">${comment.commentWriter}</div>
          <div class="text-gray-800">${comment.commentContent}</div>
          <div class="text-xs text-gray-400 mt-1">${new Date().toISOString().split('T')[0]}</div>

          <div class="pl-4 mt-3 space-y-2 reply-list"></div>

          <div class="mt-2">
            <button onclick="toggleReplyForm(this)" class="text-blue-500 text-sm hover:underline">답글 달기</button>
            <div class="mt-2 hidden reply-form">
              <textarea  rows="2" class="w-full border rounded px-2 py-1 text-sm mt-1"
                 data-comment-id="${comment.commentId}"
                        placeholder="답글을 입력하세요"></textarea>
              <button class="mt-1 bg-blue-500 text-white text-sm px-3 py-1 rounded" onclick="getReplyValue(this)">등록</button>
            </div>
          </div>
        `;

        const replyListDiv = commentDiv.querySelector('.reply-list');
        const replies = childrenMap[comment.commentId] || [];
        replies.sort((a, b) => a.commentId - b.commentId); // 대댓글은 오름차순

        replies.forEach(reply => {
            const replyDiv = document.createElement('div');
            replyDiv.className = 'border-l-4 pl-3 text-sm text-gray-700';
            replyDiv.innerHTML = `<strong>${reply.commentWriter}</strong>: ${reply.commentContent}`;
            replyListDiv.appendChild(replyDiv);
        });

        $commentList.appendChild(commentDiv);
    });
}

async function getReplyValue(button) {
    const form = button.closest('.reply-form'); // 버튼에서 가장 가까운 .reply-form 찾기
    const textarea = form.querySelector('textarea');
    const replyContent = textarea.value;
    const commentId = parseInt(textarea.dataset.commentId);

    const replyMap = {
        boardId, commentId, replyContent

    }
    try {
        const res = await axios.post("/api/v1/comment/saveReply", replyMap)

        if (res.data.status === "SUCCESS") {
            //댓글 등록
            // window.location.href = "/board"
            window.location.reload();
        }
    } catch (e) {
        //에러
        errorAlert(e)

    }


}