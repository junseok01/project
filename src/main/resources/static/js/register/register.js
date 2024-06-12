function validateForm() {
	let loginId = document.getElementById("loginId").value;
	var loginPw = document.getElementById("loginPw").value;
	var loginPwConfirm = document.getElementById("loginPwConfirm").value;
	var name = document.getElementById("name").value;
	var email = document.getElementById("email").value;
	var nickname = document.getElementById("nickname").value;
	var cellphoneNo = document.getElementById("cellphoneNo").value;
	var job = document.getElementById("job").value;
	
	console.log(loginId + " " + loginPw + " " + loginPwConfirm);
	console.log(job);
	if (loginId === "" || loginPw === "" || loginPwConfirm === "" || name === "" || email === "" || nickname === "" || cellphoneNo === "" || job === "") {
		alert("입력되지 않은 칸이있어요")
        return false;
    }
    if(loginPw !== loginPwConfirm){
		alert("비밀번호를 확인해주세요")
		return false;
	}
}
