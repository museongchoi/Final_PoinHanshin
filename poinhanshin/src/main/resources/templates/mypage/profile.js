// "회원정보 수정" 버튼 클릭 시 이벤트 처리
document.getElementById('editProfileBtn').addEventListener('click', function () {
    // 수정 페이지로 이동
    location.href="profedit.html";

    alert('회원정보 수정 페이지로 이동합니다.');
});

// 돌아가기 버튼 클릭 시 브라우저 이전 페이지로 이동
function goBack() {
    location.href="mypage.html";
}