package br.com.drogaria.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest")
public class ResourceConfigDrugstore extends ResourceConfig{
	
	public ResourceConfigDrugstore() {
		packages(" br.com.drogaria.services"); //passando o caminho onde estão meus services
	}

}
