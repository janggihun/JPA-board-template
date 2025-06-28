console.log("보드")
//변수
const $page = document.getElementById("page");
const $board = document.getElementById("board")
    const $search = document.getElementById("search");
let NOW_PAGE = 1;
//실행함수
check_nowPage();

//보드 데이터 취득
get_BoardData(NOW_PAGE);


//함수


//현재 페이지 확인
function check_nowPage() {

    const nowPage = window.localStorage.getItem("NOW_PAGE");
    if (nowPage) {
        NOW_PAGE = nowPage;
    }

}

async function get_BoardData(nowPage) {
    NOW_PAGE = nowPage;

    window.localStorage.setItem("NOW_PAGE", NOW_PAGE);

    const readMap = {
        nowPage: NOW_PAGE,
        count: 10,//10줄
        search: $search.value
    }


    try {
        const res = await axios.post("/api/v1/board/read", readMap)

        if (res.data.status === "SUCCESS") {
            //등록완료
            console.log(res.data.data)
            const boardList = res.data.data.boardList
            const pageDto = res.data.data.pageDto
            create_board(boardList)
            create_page(pageDto);

        } else {
            //변경불가
            alert(res.data.data)
        }
    } catch (e) {
        console.log(e)

        errorAlert(e)
    }


}

//검색
const click_search =async () => {

    get_BoardData(NOW_PAGE);
}


//페이지 생성
const create_page = (pageMap) => {
    let pageHtml = ""

    //처음
    pageHtml += `       
        <a href="#" class="px-3 py-1 rounded bg-gray-200 hover:bg-gray-300" onclick="get_BoardData(${pageMap.leftArrow})">&laquo;</a>
        `
    //중간
    for (let i = pageMap.pre; i <= pageMap.next; i++) {
        if (i == NOW_PAGE) {
            pageHtml += `
                    <a href="#" class="px-3 py-1 rounded bg-blue-500 text-white"  onclick="get_BoardData(${i})">${i}</a>
            `
        } else {
            pageHtml += `
                    <a href="#" class="px-3 py-1 rounded bg-gray-200 hover:bg-gray-300" onclick="get_BoardData(${i})">${i}</a>
            `
        }
    }

    //끝
    pageHtml +=
        `
          <a href="#" class="px-3 py-1 rounded bg-gray-200 hover:bg-gray-300"  onclick="get_BoardData(${pageMap.rightArrow})">&raquo;</a>
           `
    $page.innerHTML = pageHtml;
}

// 게시글 생성
const create_board = (boardList) => {
    let html = "";

    boardList.forEach((el) => {
        html += `
        <div class="flex flex-col sm:flex-row px-4 py-4 border-b hover:bg-gray-50 transition">
            <!-- 번호 -->
            <div class="sm:w-1/12 text-sm sm:text-base text-gray-500 sm:text-center mb-1 sm:mb-0">
              No. ${el.boardId}
            </div>

            <!-- 제목 -->
            <div class="sm:w-5/12 mb-1 sm:mb-0">
                <a href="#" onclick="updateView(${el.boardId})" class="text-blue-600 font-medium hover:underline truncate block">
                    ${el.boardTitle}
                </a>
            </div>

            <!-- 작성자 -->
            <div class="sm:w-2/12 text-sm sm:text-base text-gray-700 sm:text-center mb-1 sm:mb-0">
                ${el.boardWriter}
            </div>

            <!-- 날짜 -->
            <div class="sm:w-2/12 text-sm sm:text-base text-gray-600 sm:text-center mb-1 sm:mb-0">
                ${el.boardInsertDate}
            </div>

            <!-- 조회수 -->
            <div class="sm:w-2/12 text-sm sm:text-base text-gray-600 sm:text-center">
               조회수 : ${el.boardView}
            </div>
        </div>
        `;
    });

    $board.innerHTML = html;
}


//조회수 업데이트
const updateView = async (boardId) => {

    const viewMap = {boardId}

    try {
        const res = await axios.post("/api/v1/board/updateView", viewMap)

        if (res.data.status === "SUCCESS") {
            //등록완료
            window.location.href = `/read/` + boardId

        }
    } catch (e) {

        errorAlert(e)
    }


}


