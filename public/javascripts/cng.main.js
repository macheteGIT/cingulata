
//Ajax processing
(function() {
    if (!window.cng) {
        window.cng = {};
    }

    /**
     * Processes request.
     * @var method {String}
     * @var url {String}
     * @var callback {Function}
     */
    function request(method, url, callback) {
      var xhttp = new XMLHttpRequest();
      xhttp.open(method, url, true);

      xhttp.onreadystatechange = function(e) {
        if (this.readyState === 4 && this.status === 200) {
          callback(this);
        }
      };
      xhttp.send();
    }

    /**
     * Serializes form.
     * @var {Node}
     */
    function serialize(form) {
        if (!form || form.nodeName !== "FORM") {
            return;
        }
        var i, q = [];
        for (i = form.elements.length - 1; i >= 0; i = i - 1) {
            if (form.elements[i].name === "") {
                continue;
            }
            q.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
        }
        return q.join("&");
    }
    
    /**
     * Takes a form node and sends it over AJAX.
     * @param {HTMLFormElement} form - Form node to send
     * @param {function} callback - Function to handle onload.
     *                              this variable will be bound correctly.
     */
    function submit (form) {
      function validate(xhttp) {
        var response = JSON.parse(xhttp.response);
        if (response.email) {
          document.getElementById("email").style.borderColor = "red";
        }
        if (response.password) {
          document.getElementById("password").style.borderColor = "red";
        }
      }

      var xhttp = new XMLHttpRequest();
      xhttp.open("POST", form.action, true);
      xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

      xhttp.onreadystatechange = function(e) {
        if (this.readyState === 4) {
          validate(xhttp);
        }
      }
      xhttp.send(serialize(form));
    }

    /**
    * Ajax functions wrapper. POST, GET, PUT, DELETE supported.
    * @var url {String}
    * @var callback {Function}
    */
    window.cng.ajax = {
        get: function(url, callback) {
          request("GET", url, callback);
        },
        put: function(url, callback) {
          request("PUT", url, callback);
        },
        post: function(url, callback) {
          request("POST", url, callback);
        },
        delete: function(url, callback) {
          request("DELETE", url, callback);
        },
        submit: function(form, callback) {
          submit(form, callback);
        }
    }
}())
