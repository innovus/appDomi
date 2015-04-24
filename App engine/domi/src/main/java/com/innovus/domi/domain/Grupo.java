package com.innovus.domi.domain;

import static com.innovus.domi.service.OfyService.ofy;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;


@Entity
public class Grupo {
	
	@Id
	 private long idGrupo;
	
	@Index
	private String nombreGrupo;
	
	@Parent
	@ApiResourceProperty(ignored=AnnotationBoolean.TRUE)
	private Key<Grupo> grupoKey;
	 
	@ApiResourceProperty(ignored=AnnotationBoolean.TRUE)
	private long idGrupoPadre;
	
	private Grupo(){
		
	}

	//constructor para una categoria padre
	public Grupo(final long idGrupo, final String nombreGrupo){
		this.idGrupo = idGrupo;
		this.nombreGrupo = nombreGrupo;
	}
	
	//constructor pa una categoria hija
	public Grupo (final long idGrupo, final String nombreGrupo,final String websafeGrupoPadreKey){
		this.idGrupo = idGrupo;
		this.nombreGrupo = nombreGrupo;
		
		this.grupoKey=Key.create(websafeGrupoPadreKey);
		Grupo grupoPadre= ofy().load().key(grupoKey).now();
		this.idGrupoPadre = grupoPadre.getIdGrupo();
	}

	public long getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(long idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getNombreGrupo() {
		return nombreGrupo;
	}

	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}

	public long getIdGrupoPadre() {
		return idGrupoPadre;
	}

	public void setIdGrupoPadre(long idGrupoPadre) {
		this.idGrupoPadre = idGrupoPadre;
	}
	
	//retorna la key del padre
	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	public Key<Grupo> getGrupoKey() {
		return grupoKey;
	}

	//pone la key del padre
	public void setGrupoKey(Key<Grupo> grupoKey) {
		this.grupoKey = grupoKey;
	}
	
	//retorna llavesegura del grupo
	public String getWefSafeKey(){
		
		if(idGrupoPadre == 0)
			return Key.create(Grupo.class,idGrupo).getString();
		else
		 return Key.create(grupoKey,Grupo.class,idGrupo).getString();
		
	 }
	
	//retorna la llavesegura de el padre
	public String getWefSafeKeyPadre(){
		if(idGrupoPadre == 0) return "";
		else //debe retoornar el padre del padre key
			return Key.create(getKeyPadre(), Grupo.class,idGrupoPadre).getString();
		
	 }
	
	//traer el nombre del padre, si es padre este trae solo la palabra padre
	public String getClienteNombrePadre() {
		//si es padre retorna padre
		if(idGrupoPadre == 0) return "Padre";
		else{
			//si no crea cn la key del abuelo, la clase y la id del padre retorna el nombre del padre
			Grupo grupo = ofy().load().key(Key.create(getKeyPadre(), Grupo.class,idGrupoPadre)).now();
			if ( grupo == null) {
				return String.valueOf( idGrupoPadre);
            } else {
            	return grupo.getNombreGrupo();
            }
		}
    }
	
	//este metodo trae la key del padre del padre (abuelo)
	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	public Key<Grupo> getKeyPadre(){
		Grupo grupoPadre= ofy().load().key(grupoKey).now();
		return grupoPadre.getGrupoKey();
	}
	

}
