package br.com.drogaria.bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.drogaria.dao.FactoryDAO;
import br.com.drogaria.dao.ProductDAO;
import br.com.drogaria.dao.SingleConnection;
import br.com.drogaria.domain.Factory;
import br.com.drogaria.domain.Product;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@ManagedBean
@ViewScoped
public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Product> products;
	private List<Factory> factorys;
	private Product product;
	private ProductDAO dao;
	private FactoryDAO daoFactory;
	private StreamedContent streamedContent;//serve p carregar arquivos

	public void init() {
		initAttributes();
		products = dao.getAll();
		factorys = daoFactory.getAll();
	}

	private void initAttributes() {
		product = new Product();
		dao = new ProductDAO();
		daoFactory = new FactoryDAO();
	}

	public void clearBean() {
		product = new Product();
	}

	public void save() {
		try {
			if (product.getWayImage() == null) {
				Messages.addGlobalInfo("Photo is required!");
				return;
			}

			Product product2 = dao.merge(product);

			Path pathSource = Paths.get(product.getWayImage());
			Path pathDestino = Paths.get("C:/Drugstore/img/" + product2.getId() + ".png");
			Files.copy(pathSource, pathDestino, StandardCopyOption.REPLACE_EXISTING);

			product = new Product();
			loadList();
			Messages.addGlobalInfo("Successfully");
		} catch (RuntimeException | IOException e) {
			Messages.addGlobalError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void update(Product product) {
		this.product = product;
		this.product.setWayImage("C:/Drugstore/img/" + product.getId() + ".png");
	}

	public void delete(Product p) {
		try {
			dao.delete(p);
			// Deletando photo
			Files.deleteIfExists(Paths.get("C:/Drugstore/img/" + p.getId() + ".png"));

			loadList();
			Messages.addGlobalInfo("Successfully");
		} catch (RuntimeException | IOException e) {
			Messages.addGlobalError(e.getMessage());
			e.printStackTrace();
		}
	}

	private void loadList() {
		products = dao.getAll();
		daoFactory = new FactoryDAO();
	}
	
	public void mountPhoto(Product p) {
		try {
			FileInputStream fileInputStream = new FileInputStream("C:/Drugstore/img/" + p.getId() + ".png");
			streamedContent= new DefaultStreamedContent(fileInputStream, "image/png", p.getId() + ".png");
		} catch (RuntimeException | FileNotFoundException e) {
			e.printStackTrace();
			Messages.addGlobalError(e.getMessage());
		}
	}

	public void upload(FileUploadEvent fileUploadEvent) {
		UploadedFile uploadedFile = fileUploadEvent.getFile();
		// Criando file temp dentro do system
		try {
			// Fazendo copia do file que veio p S.O
			Path pathTemp = Files.createTempFile(null, null); // nome do file e extensão, bom deixar null. Tamanho 0
			Files.copy(uploadedFile.getInputstream(), pathTemp, StandardCopyOption.REPLACE_EXISTING); // pegando origem
																										// e mandando p
																										// destino, sobrescrevendo o file acima com o que veio
			product.setWayImage(pathTemp.toString()); // pegando o caminho temp

			Messages.addGlobalInfo("Image save successfully");
		} catch (IOException e) {
			e.printStackTrace();
			Messages.addGlobalError(e.getMessage());
		}
	}

	public void print() {
		SingleConnection singleConnection= new SingleConnection();
		Connection connection = singleConnection.getConnection();
		String wayMemory = Faces.getRealPath("/reports/produtos.jasper");// Descobrinco o endereço do relatório em memoria, visto que o jasper nao consegue ir no disco. Ominifaces
		String wayBanner = Faces.getRealPath("/resources/img/logo.PNG"); //Descobrindo o caminho da imagem em runtime, evito assim problemas quando irei instalar a aplicação em otutros S.O
		
		//Extraindo filtros
		DataTable dataTable= (DataTable) Faces.getViewRoot().findComponent("formProduct:dataProduct");
		Map<String, Object> filters = dataTable.getFilters();
		String filterNameProduct = (String) filters.get("name");
		String filterNameFactory = (String) filters.get("factory.name");
		
		Map<String, Object> params = new HashMap<>();
		params.put("PRODUTO_NOME", "%%"); //sem preencher nada acarreta nullpointer
		params.put("FABRICANTE_NOME", "%%");
		params.put("WAY_BANNER", wayBanner);
		
		if(filterNameProduct != null) {
			params.put("PRODUTO_NOME", "%" +filterNameProduct + "%");
		}
		
		if(filterNameFactory != null) {
			params.put("FABRICANTE_NOME", "%" +filterNameFactory + "%");
		}
		
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(wayMemory, params, connection); //return relatório populado
			JasperPrintManager.printReport(jasperPrint, true); //se mando direto p impressora padrão ou pergunto antes
		} catch (Exception e) {
			Messages.addGlobalError("Error ao gerar Relatório");
			e.printStackTrace();
		} finally {
			System.out.println("Fechando connection");
			singleConnection.closedConnection(connection);
		}
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Factory> getFactorys() {
		return factorys;
	}

	public void setFactorys(List<Factory> factorys) {
		this.factorys = factorys;
	}

	public StreamedContent getStreamedContent() {
		return streamedContent;
	}

	public void setStreamedContent(StreamedContent streamedContent) {
		this.streamedContent = streamedContent;
	}
}
