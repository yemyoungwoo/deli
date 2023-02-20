$(document).ready(function(){
	

// 유저 정보 변경하기
function infoModify(data) {
	$.ajax({
		url: "/user/info",
		type: "PATCH",
		data: data
	})
	.then(function(result){
		swal(result);
		$("input").val("");
		
	})
	.fail(function(){
		alert("에러가 발생했습니다");
	})
} 

	


$(".pwd_modify").click(function() {
	const prevPassword = $(".present_password").val().replaceAll(" ", "");
	const newPassword = $(".new_password").val().replaceAll(" ", "");
	const newPasswordCheck = $(".new_password_check").val().replaceAll(" ", "");
	
	if(!prevPassword || !newPassword) {
		return;
	}
	
	if(newPassword != newPasswordCheck) {
		swal('변경하실 비밀번호를 확인해 주세요');
		return;
	}
	
	swal({
		text: '비밀번호를 변경하시겠습니까?',
		buttons: ['취소', '변경하기']
	})
	.then(function(value){
		if(value) {
			const data = {
				value: newPassword,
				valueType: "password",
				prevPassword : prevPassword
			};
		
			infoModify(data);
		}
	})
})



$(".nickname_modify").click(function() {
	const nickname = $(".nickname").val();
	
//	if(newPassword == null) {
//		swal('비밀번호를 입력해주세요');
//		return;
//	}

	if (!nickname) {
		return;
	}

	if (!nicknameCheck(nickname)) {
		swal('닉네임은 한글, 영어, 숫자만 4 ~10자리로 입력 가능합니다');
		return;
	}
	
	
	const data = {
		value: nickname,
		valueType: "nickname"
	};
				

	$.ajax({
		url: "/overlapCheck",
		type: "get",
		data: data,
	})
	.then(function(result){
		if (result != 0) {
			swal('이미 사용중인 닉네임입니다');
		} else {
			swal('닉네임을 ' + nickname + '으로 변경하시겠습니까?', {
				buttons: ['취소', '변경하기'],
			})
			.then(function(value){
				if (value) {
					infoModify(data);
					$(".user_nickname").text(nickname);
				}
			})
		}
	})
	.fail(function(){
		alert("에러가 발생했습니다");
	})
}); 

})