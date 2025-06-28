console.log("create 스크립트 ")

//변수

const $password = document.getElementById("password");
const $title = document.getElementById("title");
const $content = document.getElementById("content");


const insert = async () => {
    const insertMap = {
        boardWriter: "비공개",
        boardTitle: $title.value,
        boardContent: $content.value,
        boardPw: $password.value,
    }


    try {
        const res = await axios.post("/api/v1/board/insert", insertMap)

        if (res.data.status === "SUCCESS") {
            //등록완료
            alert("등록 완료")
            window.location.href = "/board"
        } else {
            //변경불가
            alert(res.data.data)
        }
    } catch (e) {
        console.log(e)

        errorAlert(e)
    }


}

// const test_insert = async () => {
//
//     for (let i = 1; i < 1000; i++) {
//         const insertMap = {
//             boardWriter: "비공개",
//             boardTitle: i + "번째 글입니다.",
//             boardContent: "비밀번호는 " + i + " 입니다.",
//             boardPw: i,
//         }
//         const res = await axios.post("/api/v1/board/insert", insertMap)
//
//
//     }
//
//
// }
// test_insert();