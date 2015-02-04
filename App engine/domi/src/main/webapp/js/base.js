/**
 * @fileoverview
 * Provides methods for the Hello Endpoints sample UI and interaction with the
 * Hello Endpoints API.
 */

var google = google || {};

/** devrel namespace for Google Developer Relations projects. */
google.devrel = google.devrel || {};

/** samples namespace for DevRel sample code. */
google.devrel.samples = google.devrel.samples || {};

/** hello namespace for this sample. */
google.devrel.samples.hello = google.devrel.samples.hello || {};

/**
 * Prints a greeting to the greeting log.
 * param {Object} greeting Greeting to print.
 */
google.devrel.samples.hello.print = function(greeting) {
	  var element = document.createElement('div');
	  element.classList.add('row');
	  element.innerHTML = greeting.message;
	  document.getElementById('outputLog').appendChild(element);
	};




/**
 * Gets a numbered greeting via the API.
 * @param {string} id ID of the greeting.
 */
	google.devrel.samples.hello.getGreeting = function(id) {
		  gapi.client.helloworld.greetings.getGreeting({'id': id}).execute(
		      function(resp) {
		        if (!resp.code) {
		          google.devrel.samples.hello.print(resp);
		        } else {
		          window.alert(resp.message);
		        }
		      });
		};

/**
 * Lists greetings via the API.
 */
		google.devrel.samples.hello.listGreeting = function() {
			  gapi.client.helloworld.greetings.listGreeting().execute(
			      function(resp) {
			        if (!resp.code) {
			          resp.items = resp.items || [];
			          for (var i = 0; i < resp.items.length; i++) {
			            google.devrel.samples.hello.print(resp.items[i]);
			          }
			        }
			      });
			};

function listQuotes() {
    gapi.client.domi.consultaEmpresa().execute(function(resp) {
            if (!resp.code) {
                    resp.items = resp.items || [];
                    var result = "";
                   for (var i=0;i<resp.items.length;i++) {
                            //result = result+ resp.items[i].message + "..." + "<b>" + resp.items[i].author + "</b>" + "[" + resp.items[i].id + "]" + "<br/>";
                	   result =  result+ resp.items[i].nombre +"  "+resp.items[i].descripcion+" "+resp.items[i].ciudad+" "+resp.items[i].tiempoMinimo +"<br>";
                    }
                    document.getElementById('listQuotesResult').innerHTML = result;
           }
    	//var result = "<br>hola<br> ";
    	//document.getElementById('listQuotesResult').innerHTML = result;
    	
    });
}

function crearEmpresa(){
	
		
   var nomEmpresa = document.getElementById('txtnomEmpresa').value;
   var desEmpresa = document.getElementById('txtdesEmpresa').value;
   var ciudEmpresa = document.getElementById('txtciudEmpresa').value;
   var timeEmpresa = document.getElementById('txttimeEmpresa').value;
   var requestData = {};
   requestData.nombre = nomEmpresa;
   requestData.descripcion = desEmpresa;
   requestData.ciudad = ciudEmpresa;
   requestData.tiempoMinimo = timeEmpresa;
		
   gapi.client.domi.createEmpresa(requestData).execute(function(resp) {
    	
    	 if (!resp.code) {
            
             console.log(resp.ciudad + ":" + resp.descripcion + ":" + resp.tiempoMinimo + ":" + resp.nombre );
         }
		
		//document.getElementById('respuesta').innerHTML = nomEmpresa;
		
	});
}

/**
 * Enables the button callbacks in the UI.
 */
google.devrel.samples.hello.enableButtons = function() {
	
	
	  
	  document.getElementById('listQuote').onclick = function() {
	      listQuotes();
	    }
	  document.getElementById('btnCrearEmpresa').onclick = function() {
	      crearEmpresa();
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