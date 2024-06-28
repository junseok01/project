function startLongRunningTask() {
    showLoadingMessage();
}

function showLoadingMessage() {
    var loadingMessage = document.getElementById('loadingMessage');
    loadingMessage.classList.remove('hidden');
}

