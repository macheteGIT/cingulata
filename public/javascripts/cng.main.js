(function() {
  if (!window.cng) {
    window.cng = {};
  }
  window.cng.ajax = {
    declare: function(url) {
      //var newName = input.value;
      var xhttp = new XMLHttpRequest();
      xhttp.open("GET", url,  true);
    //  xhttp.send();
      return xhttp;
      //console.log("work");
    },

    response: function(xhttp) {
      //xhttp.onreadystatechange = function() {
      if  (xhttp.status == 200) {
          return true;//b.setAttribute("style", "font-weight:normal")
  };
}
}
}())
