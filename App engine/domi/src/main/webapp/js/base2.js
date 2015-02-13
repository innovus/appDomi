
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
    gapi.client.domi.consultaEmpresas().execute(function(resp) {
            if (!resp.code) {
                    resp.items = resp.items || [];
                    var result = "";
                   for (var i=0;i<resp.items.length;i++) {
                            //result = result+ resp.items[i].message + "..." + "<b>" + resp.items[i].author + "</b>" + "[" + resp.items[i].id + "]" + "<br/>";
                	   result =  result+ resp.items[i].nombre +"  "+resp.items[i].descripcion+" "+resp.items[i].ciudad+" "+resp.items[i].tiempoMinimo +" "+resp.items[i].grupos +" "+resp.items[i].valorMinimoPedido +" <br>";
                    }
                    document.getElementById('listQuotesResult').innerHTML = result;
           }
    	//var result = "<br>hola<br> ";
    	//document.getElementById('listQuotesResult').innerHTML = result;
    	
    });
}
function listaClientes(){
	gapi.client.domi.consultaClientes().execute(function(resp){
		 if (!resp.code) {
             resp.items = resp.items || [];
             var result = "";
            for (var i=0;i<resp.items.length;i++) {
                     //result = result+ resp.items[i].message + "..." + "<b>" + resp.items[i].author + "</b>" + "[" + resp.items[i].id + "]" + "<br/>";
         	   result =  result+ resp.items[i].apellidoPaterno +"  "+resp.items[i].nomCliente+" "+resp.items[i].apellidoMaterno+" "+resp.items[i].correoCliente +" "+resp.items[i].passwordCliente +" "+resp.items[i].preguntaCliente +" <br>";
             }
             document.getElementById('listQuotesResult').innerHTML = result;
    }
	});
}

function crearCliente(){
 var cedulaCliente = document.getElementById('txtcedulaCliente').value;
 var nomCliente = document.getElementById('txtnomCliente').value;
 var apellidoPaterno = document.getElementById('txtapellidoPaterno').value;
 var apellidoMaterno = document.getElementById('txtapellidoMaterno').value;
 var correoCliente = document.getElementById('txtCorreoCliente').value;
 var passwordCliente = document.getElementById('Usuario_password').value;
 var preguntaCliente = document.getElementById('registroCliente_pregunta').value;
 var respuestaCliente = document.getElementById('registroUsuario_respuesta').value;
 
 var requestData = {};
 requestData.cedulaCliente = cedulaCliente;
 requestData.nomCliente = nomCliente;
 requestData.apellidoPaterno = apellidoPaterno;
 requestData.apellidoMaterno = apellidoMaterno;
 requestData.correoCliente = correoCliente;
 requestData.passwordCliente = passwordCliente;
 requestData.preguntaCliente = preguntaCliente;
 requestData.respuestaCliente = respuestaCliente;
 
 gapi.client.domi.crearCliente(requestData).execute(function(resp){
	 
	 if (!resp.code) {
		 console.log( resp.cedulaCliente +":"+resp.nomCliente + ":" + resp.apellidoPaterno + ":" + ":" + resp.apellidoMaterno + ":" + resp.correoCliente + ":" + resp.passwordCliente +":" + resp.preguntaCliente + ":" + resp.respuestaCliente  );
		 
	 }
 });
 
 /*gapi.client.domi.createEmpresa(requestData).execute(function(resp) {
    	
    	 if (!resp.code) {
            
            // console.log( resp.ciudad + ":" + resp.descripcion + ":" + resp.tiempoMinimo + ":" + resp.nombre +":" + resp.grupos + ":" + resp.valorMinimoPedido  );
             console.log( resp.nombre + ":" + resp.passEmpresa + ":" + ":" + resp.descripcion + ":" + resp.ciudad + ":" + resp.tiempoMinimo +":" + resp.grupos + ":" + resp.valorMinimoPedido  );
         }
		
		//document.getElementById('respuesta').innerHTML = nomEmpresa;
		
	});*/
 
 
}

function crearEmpresa(){
	
		
   var nomEmpresa = document.getElementById('txtnomEmpresa').value;
   var passwordEmpresa = document.getElementById('txtpassEmpresa').value;
   var desEmpresa = document.getElementById('txtdesEmpresa').value;
   var ciudEmpresa = document.getElementById('txtciudEmpresa').value;
   var timeEmpresa = document.getElementById('txttimeEmpresa').value;
   // Obtenemos el valor por el id
  // var grupoEmpresa = document.getElementById("idGrupo").value;
  var grupoEmpresa = document.getElementById('idGrupo').value;
  var valorminPedido =  document.getElementById('txtvalorMinimoPedido').value;
   var requestData = {};
   requestData.nombre = nomEmpresa;
   requestData.passEmpresa = passwordEmpresa;   
   requestData.descripcion = desEmpresa;
   requestData.ciudad = ciudEmpresa;
   requestData.tiempoMinimo = timeEmpresa;
   requestData.grupos = grupoEmpresa;
   requestData.valorMinimoPedido = valorminPedido;
   
		
   gapi.client.domi.crearEmpresa(requestData).execute(function(resp) {
    	
    	 if (!resp.code) {
            
            // console.log( resp.ciudad + ":" + resp.descripcion + ":" + resp.tiempoMinimo + ":" + resp.nombre +":" + resp.grupos + ":" + resp.valorMinimoPedido  );
             console.log( resp.nombre + ":" + resp.passEmpresa + ":" + ":" + resp.descripcion + ":" + resp.ciudad + ":" + resp.tiempoMinimo +":" + resp.grupos + ":" + resp.valorMinimoPedido  );
         }
		
		//document.getElementById('respuesta').innerHTML = nomEmpresa;
		
	});
}

/**
 * Enables the button callbacks in the UI.
 */
google.devrel.samples.hello.enableButtons = function() {
	  
	 
	  document.getElementById('btnConsultarCliente').onclick = function() {
		  listaClientes();
	    }
	  document.getElementById('btnCrearCliente').onclick = function() {
		  crearCliente();
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