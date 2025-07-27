function check() {
    var username = document.getElementById("name").value;
    if (username == null || username == "") {
        alert("name must be filled");
        return false;
    }
    var num = document.getElementsByName("number").value;
    if (num.length < 10) {
        alert("check the number \n pls enter valid number "+num)
        return false;
    }
        alert("welcome " + username);
    
}


