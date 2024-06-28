//팝업으로 열때 사용하세요
//속성 onclick = "openPopup(event)" 하면 새창으로 열립니다
function openPopup(event) {
    event.preventDefault(); // 링크의 기본 동작(페이지 이동)을 방지
    var url = event.target.href; // 링크의 URL 가져오기

    // 팝업 창의 옵션을 설정합니다.
    var width = 800;
    var height = 800;
    var left = (window.innerWidth - width) / 2;
    var top = (window.innerHeight - height) / 2;
    var options = `width=${width},height=${height},left=${left},top=${top},toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=yes`;

    // 팝업 창을 엽니다.
    window.open(url, '_blank', options);
}

