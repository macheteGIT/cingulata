
(function() {
    if (!window.cng) {
        window.cng = {};
    }
    window.cng.ajax = {
        declare: function(url) {
            var xhttp = new XMLHttpRequest();
            xhttp.open("GET", url, true);
            return xhttp;
        },

        response: function(xhttp) {
            if (xhttp.status == 200) {
                return true;
            };
        },

        get: function(url, callback) {
          var xhttp = new XMLHttpRequest();
          xhttp.open("GET", url, true);
          xhttp.onreadystatechange = function(e) {
            if (this.readyState === 4 && this.status === 200) {
              callback(this);
            }
          }
          xhttp.send();
        }
    }
}())
