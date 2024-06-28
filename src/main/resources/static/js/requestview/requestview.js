// 파일 추가 버튼 클릭 시 실행될 함수
function addFileInput() {
    var fileInputs = document.getElementById('fileInputs');
    var fileInputCount = fileInputs.children.length;

    if (fileInputCount < 5) { // 최대 다섯 개까지 추가
        var newFileInput = document.createElement('div');
        newFileInput.classList.add('file-upload-input');
        newFileInput.innerHTML = `
            <label for="files">파일 업로드:</label>
            <input type="file" id="files" name="files" multiple>
        `;
        fileInputs.appendChild(newFileInput);
    } else {
        alert('최대 5개의 파일을 업로드할 수 있습니다.');
    }
}

// 폼 제출 시 팝업 닫기
function closePopup(event) {
    event.preventDefault();  // 기본 폼 제출 동작을 막습니다.

    var form = event.target;
    var formData = new FormData(form);

    //서버와 데이터를 교환하게 해주는 객체
    var xhr = new XMLHttpRequest();
    xhr.open("POST", form.action, true);

    //데이터가 서버어세 처리되고, 클라이언트로 응답보냈을때 자동으로 감지하고 실행되는 부분
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            //응답받은 데이터 지금은 간단한 텍스트
            var response = xhr.responseText;
             if (response === "success") {
                alert("모든 요건이 충족되었습니다. 유저 등급을 트레이너로 업데이트합니다");
             } else if (response === "fail") {
                alert("요건이 불충족 되었습니다. 관리자 승인을 기다리세요");
             } else {
                alert("서버에서 처리 중 문제가 발생했습니다");
             }

            // 서버로부터 응답을 성공적으로 받았을 때 팝업 닫기
            window.opener = null;
            //보안을위해 오픈하고 꺼야된다고함
            window.open('', '_self', '');
            window.close();
        }
    };
    xhr.send(formData);
}
