window.addEventListener("load", function() {
  var form = document.getElementById("signupform");
  document.getElementById("submit").addEventListener("click", function() {
    cng.ajax.submit(form);
  })
});
