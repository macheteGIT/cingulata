
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
        var q = [];
        for (var i = 0; i < form.elements.length; i++) {
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
        get(url, callback) {
          request("GET", url, callback);
        },
        put(url, callback) {
          request("PUT", url, callback);
        },
        post(url, callback) {
          request("POST", url, callback);
        },
        delete(url, callback) {
          request("DELETE", url, callback);
        },
        submit(form, callback) {
          submit(form, callback);
        }
    }
}())
