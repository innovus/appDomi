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
    gapi.client.domi.consultaEmpresas().execute(function(resp) {
            if (!resp.code) {
                    resp.items = resp.items || [];
                    var result = "";
                   for (var i=0;i<resp.items.length;i++) {
                            //result = result+ resp.items[i].message + "..." + "<b>" + resp.items[i].author + "</b>" + "[" + resp.items[i].id + "]" + "<br/>";
                	   result =  result+ resp.items[i].descripcion +"  "+resp.items[i].descripcion+" "+resp.items[i].ciudad+" "+resp.items[i].tiempoMinimo +" "+resp.items[i].grupos +" "+resp.items[i].valorMinimoPedido +" <br>";
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
// var cedulaCliente = document.getElementById('txtcedulaCliente').value;
 var nomCliente = document.getElementById('txtnomCliente').value;
 var apellidoPaterno = document.getElementById('txtapellidoPaterno').value;
 var apellidoMaterno = document.getElementById('txtapellidoMaterno').value;
 var correoCliente = document.getElementById('txtCorreoCliente').value;
 var passwordCliente = document.getElementById('Usuario_password').value;
 var preguntaCliente = document.getElementById('registroCliente_pregunta').value;
 var respuestaCliente = document.getElementById('registroUsuario_respuesta').value;
 
 var requestData = {};
// requestData.cedulaCliente = cedulaCliente;
 requestData.nomCliente = nomCliente;
 requestData.apellidoPaterno = apellidoPaterno;
 requestData.apellidoMaterno = apellidoMaterno;
 requestData.correoCliente = correoCliente;
 requestData.passwordCliente = passwordCliente;
 requestData.preguntaCliente = preguntaCliente;
 requestData.respuestaCliente = respuestaCliente;
 
 gapi.client.domi.crearCliente(requestData).execute(function(resp){
	 
	 if (!resp.code) {
		 console.log( resp.nomCliente + ":" + resp.apellidoPaterno + ":" + ":" + resp.apellidoMaterno + ":" + resp.correoCliente + ":" + resp.passwordCliente +":" + resp.preguntaCliente + ":" + resp.respuestaCliente  );
		 
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

/**
 * Funcion que captura las variables pasados por GET
 * http://www.lawebdelprogramador.com/pagina.html?id=10&pos=3
 * Devuelve un array de clave=>valor
 */
function getGET()
{
    // capturamos la url
    var loc = document.location.href;
    // si existe el interrogante
    if(loc.indexOf('?')>0)
    {
        // cogemos la parte de la url que hay despues del interrogante
        var getString = loc.split('?')[1];
        // obtenemos un array con cada clave=valor
        var GET = getString.split('&');
        var get = {};

        // recorremos todo el array de valores
        for(var i = 0, l = GET.length; i < l; i++){
            var tmp = GET[i].split('=');
            get[tmp[0]] = unescape(decodeURI(tmp[1]));
        }
        return get;
    }
}

window.onload = function()
{
    // Cogemos los valores pasados por get
    var valores=getGET();
    var valorid = "";
    if(valores)
    {
        // hacemos un bucle para pasar por cada indice del array de valores
        for(var index in valores)
        {
           //document.write("<br>clave: "+index+" - valor: "+valores[index]);
        	valorid = valores[index];
        	document.getElementById("keyCliente").value =valorid;
        }
    }else{
        // no se ha recibido ningun parametro por GET
        document.write("<br>No se ha recibido ningún parámetro");
    }
	//document.getElementById("keyCliente").value ="57";
	
}






function crearEmpresa(){
	
   var keyCliente = document.getElementById('keyCliente').value;		
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
   requestData.keyCliente = keyCliente;
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
             console.log( resp.keyCliente + ":" + resp.nombre + ":" + resp.passEmpresa + ":" + ":" + resp.descripcion + ":" + resp.ciudad + ":" + resp.tiempoMinimo +":" + resp.grupos + ":" + resp.valorMinimoPedido  );
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