function displaypop() {
    document.getElementById("main").classList.add("blur-back");
    document.getElementById("sidenav").classList.add("blur-back");
    document.getElementById("notificationbox").classList.add("blur-back");
    document.getElementById("fotter").classList.add("blur-back");
    document.getElementById("form").style.display = "flex";
    document.getElementById("form_frame").style.display = "flex";
}
function hidepop() {
    document.getElementById("main").classList.remove("blur-back");
    document.getElementById("sidenav").classList.remove("blur-back");
    document.getElementById("notificationbox").classList.remove("blur-back");
    document.getElementById("fotter").classList.remove("blur-back");
    document.getElementById("form").style.display = "none";
    document.getElementById("form_frame").style.display = "none";
}