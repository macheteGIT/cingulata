
(function() {
    if (!window.cng) {
        window.cng = {};
    }
    window.cng.ajax = {
        get: function(url, callback) {
          request("GET", url, callback);
        },
        put: function(url, callback) {
          request("PUT", url, callback)
        },
        post: function(url, callback) {
          request("POST", url, callback)
        },
        delete: function(url, callback) {
          request("DELETE", url, callback)
        }
    }

    function request(method, url, callback) {
      var xhttp = new XMLHttpRequest();
      xhttp.open("PUT", url, true);

      xhttp.onreadystatechange = function(e) {
        if (this.readyState === 4 && this.status === 200) {
          callback(this);
        }
      }
      xhttp.send();
    }
}())
