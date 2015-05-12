/**
 * 
 */

/**
 * 
 */
var com = com || {};

/** devrel namespace for Google Developer Relations projects. */
com.innovus = com.innovus || {};

/** samples namespace for DevRel sample code. */
com.innovus.domi = com.innovus.domi || {};

/** hello namespace for this sample. */
com.innovus.domi.login = com.innovus.domi.login || {};

/**
 * Initializes the application.
 * 
 * @param {string}
 *            apiRoot Root of the API's path.
 */


function logearse() {
	// var cedulaCliente = document.getElementById('txtcedulaCliente').value;

	var email = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	var requestData = {};
	// requestData.cedulaCliente = cedulaCliente;
	requestData.email = email;
	requestData.password = password;

	gapi.client.doomiTodos.loginEstado(requestData).execute(
			function(resp) {

				if (!resp.code) {
					alert("estado" + resp.estado);
					console.log(resp.nomCliente + ":" + resp.apellidoPaterno
							+ ":" + ":" + resp.apellidoMaterno + ":"
							+ resp.correoCliente + ":" + resp.passwordCliente
							+ ":" + resp.preguntaCliente + ":"
							+ resp.respuestaCliente);

				}
			});

}
com.innovus.domi.login.enableButtons = function() {

	document.getElementById('post').onclick = function() {
		logearse();
	}

};

com.innovus.domi.login.init = function(apiRoot) {
	// Loads the OAuth and helloworld APIs asynchronously, and triggers login
	// when they have completed.
	var apisToLoad;
	var callback = function() {
		if (--apisToLoad == 0) {
			com.innovus.domi.login.enableButtons();
		}
	}

	apisToLoad = 1; // must match number of calls to gapi.client.load()
	gapi.client.load('doomiTodos', 'v1', callback, apiRoot);
	// gapi.client.load('oauth2', 'v2', callback);
};