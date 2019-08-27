package br.com.drogaria.bean;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.omnifaces.util.Messages;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@RequestScoped
public class ProductImageBean {
	@ManagedProperty("#{param.wayImage}")
	private String wayImage;
	private StreamedContent streamedContent;
	
	
	public String getWayImage() {
		System.out.println("Retornando wayImage: " + wayImage.isEmpty());
		return wayImage;
	}
	public void setWayImage(String wayImage) {
		System.out.println("Setando wayImage: " + wayImage);
		this.wayImage = wayImage;
	}
	public StreamedContent getStreamedContent() {
		Path path= Paths.get("C:/Drugstore/img/white.png");
		try {
			if(wayImage != null && !wayImage.isEmpty()) {
				System.out.println("WayImage: " + wayImage);
				path= Paths.get(wayImage);
			} 
			
			InputStream inputStream = Files.newInputStream(path);
			streamedContent= new DefaultStreamedContent(inputStream);
			
		} catch (IOException e) {
			Messages.addGlobalError(e.getMessage());
			e.printStackTrace();
		}
		return streamedContent;
	}
	public void setStreamedContent(StreamedContent streamedContent) {
		this.streamedContent = streamedContent;
	}

}
