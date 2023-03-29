$(document).ready(function () {
  bindSignInButton();
});

function bindSignInButton() {
  let signIn = $("#signInButton");
  signIn.click(function () {
    let userNameText = $("#userName").val();
    let passwordText = $("#password").val();
    let userJson = { userName: userNameText, hashedPw: passwordText };
    $.ajax({
      data: JSON.stringify(userJson),
      contentType: "application/json",
      type: "POST",
      url: "http://localhost:8080/api/Login",
      success: function (user) {
        localStorage.setItem("userId", user.id);
        localStorage.setItem("userName", user.userName);
        localStorage.setItem("isAdmin", user.admin);
        localStorage.setItem("userEmail", user.email);
        window.location.href = "./index.html?";
      },
      error: function () {},
    });
  });
}
