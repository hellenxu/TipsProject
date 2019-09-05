function checkreg() {
	if (document.form1.User.value=="") {
    alert("请输入您的手机号!");
    form1.User.focus();
    return false;
   }
}