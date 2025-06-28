console.log("공통js 입니다.")

//변수


//페이지변경
const changePage = (url) => {

    window.location.href = "/" + url;

}

//에러시 문구 출력
const errorAlert = (e) => {

    if (e.response.data.message) {
        alert(e.response.data.message)
    } else if (e.response.data.data) {
        alert(e.response.data.data)
    }

}