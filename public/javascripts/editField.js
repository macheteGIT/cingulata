var a = document.getElementsByClassName('edit');
for (var i = 0; i < a.length; i++) {
    a[i].addEventListener("click", editInput,false);
//a.addEventListener("mouseover",  example, false)
}
function editInput(event) {
   if (event.target.tagName === "INPUT" || event.target.tagName === "BUTTON" ) {
        return false;
     }

    var li =this;
    var input = document.createElement("input")
    input.type = "text"
    input.value = li.innerText;
    var old = li.innerText;
    li.innerText= "";
    li.appendChild(input);
    var newText = input.value;
    console.log("value input" + input.value)
    console.log("old" + old);
    console.log("new" + newText);
    addSaveButton();
    addCancelButton();

          //this.parentNode.innerText = this.value

        console.log("TARGET" + event.target)
      console.log(event.relatedTarget)
    //  input.value = input.defaultValue;
      //this.style.color="#888"

input.onfocus = function () {
  this.onmousedown = function() {
      if (event.parentNode.tagName == "BUTTON") {
          return false;
      }

      this.parentNode.innerText = this.value
      this.remove();

}
}
  input.focus();
  function addSaveButton() {
    //вынести в глобал
      var button = document.createElement("button");
      button.innerText = "Save";
      button.onclick = function() {
          li.innerHTML = input.value;
      }
     li.appendChild(button);
  }

  function addCancelButton() {
      var button = document.createElement("button");
      button.innerText = "Cancel";
      button.onclick = function () {
        li.innerHTML = "";
        li.innerText = old;
        return false;
      }
      li.appendChild(button);
  }
}
