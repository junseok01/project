var naverLogin = new naver.LoginWithNaverId({
    clientId: 'http://127.0.0.1:8082/',
    callbackUrl: 'http://127.0.0.1:8082/naver_login',
    isPopup: false,
    callbackHandle: true,
    loginButton: {color: 'green', type: 3, height: 45}
});

document.getElementById('naverLoginBtn').addEventListener('click', function() {
    naverLogin.init();
});