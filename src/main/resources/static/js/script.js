function drag(event) {
    event.dataTransfer.setData("text", this.event.target.src);
}
function drop(event) {
    event.preventDefault();
    let data = event.dataTransfer.files;
    for (file of data) {
        document.getElementById("display").innerText = document.getElementById("display").value + "  " + file.name;
    }
}
function allow(event) {
    event.preventDefault();
}