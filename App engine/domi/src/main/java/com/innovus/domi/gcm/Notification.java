package com.innovus.domi.gcm;

import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;
//import org.apache.commons.io.IOUtils;

public class Notification {
	//public static final String API_KEY = "AIzaSyBmxApjnHtAUcq4uJIFxORWdZKETat7t-c";
	
	public static final String API_KEY = "AIzaSyDJ1iMMQH5nVF42-0NEnVnsp-G9AMDWbdE";
	
	private String mensaje;
	private String respuesta;
	 
	 public Notification(String m){
		 this.mensaje = m;
	 }
	
	
	public void sendNotification() throws JSONException{
		
		
		 try {
	            // Prepare JSON containing the GCM message content. What to send and where to send.
	            JSONObject jGcmData = new JSONObject();
	            JSONObject jData = new JSONObject();
	            jData.put("message", this.mensaje);
	            jGcmData.put("to", "/topics/global");
	            jGcmData.put("data", jData);
	            
	            // Create connection to send GCM Message request.
	            URL url = new URL("https://android.googleapis.com/gcm/send");
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestProperty("Authorization", "key=" + API_KEY);
	            conn.setRequestProperty("Content-Type", "application/json");
	            conn.setRequestMethod("POST");
	            conn.setDoOutput(true);

	            // Send GCM message content.
	            OutputStream outputStream = conn.getOutputStream();
	            outputStream.write(jGcmData.toString().getBytes());
	         // Read GCM response.
	            InputStream inputStream = conn.getInputStream();
	          //  String resp = IOUtils.toString(inputStream);
	            String resp = IOUtils.toString(inputStream);
	           // String resp = inputStream.toString();
	            respuesta = resp + "Check your device/emulator for notification or logcat for " +
	                    "confirmation of the receipt of the GCM message...";
	            //return respuesta; 
		          /*
	            System.out.println(resp);
	            System.out.println("Check your device/emulator for notification or logcat for " +
	                    "confirmation of the receipt of the GCM message...");
	                    */
	        } catch (IOException e) {
	        	respuesta =" Unable to send GCM message. "+ "Please ensure that API_KEY has been replaced by the server " +
	                    "API key, and that the device's registration token is correct (if specified).";
	           
	        	/*System.out.println("Unable to send GCM message.");
	            System.out.println("Please ensure that API_KEY has been replaced by the server " +
	                    "API key, and that the device's registration token is correct (if specified).");
	           */
	        }
		 }


	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public String getRespuesta() {
		return respuesta;
	}


	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

}
