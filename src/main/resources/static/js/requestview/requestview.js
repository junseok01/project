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

    var xhr = new XMLHttpRequest();
    xhr.open("POST", form.action, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            // 서버로부터 응답을 성공적으로 받았을 때 팝업 닫기
            window.opener = null;
            window.open('', '_self', '');
            window.close();
        }
    };
    xhr.send(formData);
}
