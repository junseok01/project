function validateForm() {
	let loginId = document.getElementById("loginId").value;
	var loginPw = document.getElementById("loginPw").value;
	var loginPwConfirm = document.getElementById("loginPwConfirm").value;
	var name = document.getElementById("name").value;
	var nickname = document.getElementById("nickname").value;
	var cellphoneNo = document.getElementById("cellphoneNo").value;

	
	console.log(loginId + " " + loginPw + " " + loginPwConfirm);
	if (loginId === "" || loginPw === "" || loginPwConfirm === "" || name === "" || nickname === "" || cellphoneNo === "" ){
		alert("입력되지 않은 칸이있어요")
        return false;
    }
    if(loginPw !== loginPwConfirm){
		alert("비밀번호를 확인해주세요")
		return false;
	}
}
