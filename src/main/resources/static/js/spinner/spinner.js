function startLongRunningTask() {
            showLoadingMessage();
            updateLoadingMessage();
        }

function showLoadingMessage() {
    var loadingMessage = document.getElementById('loadingMessage');
    loadingMessage.classList.remove('hidden');
}

function updateLoadingMessage() {
    var loadingText = document.getElementById('loadingText');
    var loadingImage = document.getElementById('loadingImage');

    setTimeout(function() {
        loadingText.innerText = 'PDF 파일 변환 중입니다...';
        /*loadingImage.innerHTML = '<img src="/OCRImages/029f70c2-5b82-475a-b5ad-d5765ca2592c.pdf.png" alt="문서에서 글자 추출 중" />';*/

        setTimeout(function() {
            loadingText.innerText = '문서에서 텍스트를 추출 중입니다...';
            //loadingImage.innerHTML = '<img src="/OCRImages/f32be2e5-d066-497f-b75a-2fd7e024de4b.pdf.png" alt="문서에서 글자 추출 중" />';
            loadingImage.classList.add('fixed-size');  // 고정 크기 스타일 추가

            setTimeout(function() {
                loadingImage.parentNode.removeChild(loadingImage);
                loadingText.innerText = 'AI 판단 중입니다...';
                loadingImage.classList.remove('fixed-size');  // 고정 크기 스타일 제거

            }, 2000);

        }, 1200);

    }, 1000);
}

//function updateLoadingImages() {
//    var loadingImage = document.getElementById('loadingImage');
//    setTimeout(function() {
//        loadingImage.innerHTML = '<img src="/OCRImages/029f70c2-5b82-475a-b5ad-d5765ca2592c.pdf.png" alt="문서에서 글자 추출 중" />';
//        setTimeout(function() {
//            loadingImage.innerHTML = '<img src="/OCRImages/f32be2e5-d066-497f-b75a-2fd7e024de4b.pdf.png" alt="문서에서 글자 추출 중" />';
//            setTimeout(function() {
//                loadingImage.innerHTML = '<img src="/OCRImages/e452ab1e-bb1b-4450-9b71-95388f0c5f6d.pdf.png" alt="AI 판단 중" />';
//            }, 1000);
//        }, 1000);
//    }, 1000);
//}