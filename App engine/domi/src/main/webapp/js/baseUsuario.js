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


function crearUsuario(){
	 var nombreUsuario = document.getElementById('txtNombreUsuario').value;
	 var celularUsuario = document.getElementById('txtTelefonoUsuario').value;
	 var correoUsuario = document.getElementById('txtCorreoUsuario').value;
	 var passwordUsuario = document.getElementById('txtPasswordUsuario').value;
	 
	 var requestData = {};
	 requestData.nombreUsuario = nombreUsuario;
	 requestData.celularUsuario = celularUsuario;
	 requestData.correoUsuario = correoUsuario;
	 requestData.passwordUsuario = passwordUsuario;
	 
	 gapi.client.domi.crearUsuario(requestData).execute(function(resp){		 
		 if (!resp.code) {
			 console.log( resp.nombreUsuario +":"+resp.celularUsuario + ":" + resp.correoUsuario + ":" +  resp.passwordUsuario  );			 
		 }
	 });
	 
	
}

google.devrel.samples.hello.enableButtons = function() {	  
	 
	  document.getElementById('btnCrearUsuario').onclick = function() {
		  crearUsuario();
	    }	 
	  
	};
/**
* Initializes the application.
* @param {string} apiRoot Root of the API's path.
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