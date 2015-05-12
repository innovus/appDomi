/**
 * 
 */
var google = google || {};

/** devrel namespace for Google Developer Relations projects. */
google.devrel = google.devrel || {};

/** samples namespace for DevRel sample code. */
google.devrel.samples = google.devrel.samples || {};

/** hello namespace for this sample. */
google.devrel.samples.hello = google.devrel.samples.hello || {};

function enviar_formulario() {
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	var idCliente = "";
	var int = 0;

	gapi.client.domi.consultaClientes().execute(
			function(resp) {
				if (!resp.code) {
					resp.items = resp.items || [];
					var result = "";
					for (var i = 0; i < resp.items.length; i++) {
						// result = result+ resp.items[i].message + "..." +
						// "<b>" + resp.items[i].author + "</b>" + "[" +
						// resp.items[i].id + "]" + "<br/>";
						result = result + resp.items[i].idCliente + "  "
								+ resp.items[i].correoCliente + " "
								+ resp.items[i].passwordCliente + " <br>";
						if (resp.items[i].correoCliente == username
								&& resp.items[i].passwordCliente == password) {
							int = 1;
							idCliente = resp.items[i].idCliente;
							document.f1.campo.value = idCliente;
						}
					}

					if (int == 1) {
						// window.location = "NewFile.html";
						document.f1.submit()
					} else {
						alert("contraseña incorrecta");
					}

				}
			});
}

google.devrel.samples.hello.enableButtons = function() {

	document.getElementById('post').onclick = function() {
		enviar_formulario();
	}

};
/**
 * Initializes the application.
 * 
 * @param {string}
 *            apiRoot Root of the API's path.
 */
google.devrel.samples.hello.init = function(apiRoot) {
	// Loads the OAuth and helloworld APIs asynchronously, and triggers login
	// when they have completed.
	var apisToLoad;
	var callback = function() {
		if (--apisToLoad == 0) {
			google.devrel.samples.hello.enableButtons();
		}
	}

	apisToLoad = 1; // must match number of calls to gapi.client.load()
	gapi.client.load('domi', 'v1', callback, apiRoot);
	// gapi.client.load('oauth2', 'v2', callback);
};