package net.olu.serviceImpl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.olu.models.Cartline;
import net.olu.models.Category;
import net.olu.models.Product;
import net.olu.repositories.AccountRepository;
import net.olu.repositories.ProductRepository;
import net.olu.repositories.TaskRepository;
import net.olu.services.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService{
	

	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	TaskRepository taskRepository;

	@Override
	public List<Product> findAllByCategory(Category category) {
		return productRepository.findByCategory(category);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public List<Integer> QuanityLeft(int left) {
		List<Integer> quantityleft= new ArrayList<Integer>();
		for(int i=1;i<=left;i++) {
			quantityleft.add(i);
		}
		
		return quantityleft;
	}


	@Override
	public List<Product> listChanges(List<Product> product, List<Cartline> cartline) {
	int a=0;
	int i=0;
		while(i<cartline.size()) {
		a=	product.get(i).getQuantity() + cartline.get(i).getQuantity();
		product.get(i).setQuantity(a);
		i++;
		}
		
		return  product;
	}

	//handle images
	@Value("${app.upload.dir:${user.home}}")
    public String rootDirectory;
	
	@Override
	public void saveImage(Product product) throws Exception {
		String folder= rootDirectory + "\\Videos\\pjts\\spring\\plain\\plain\\src\\main\\resources\\static\\photos\\";
//		String folder= "C:\\Users\\ADMIN\\Videos\\pjts\\spring\\plain\\plain\\photos\\";
		String imageFormat=".jpg";
		MultipartFile image = product.getImage();
		int uniqueName = product.getId();
		//for creating the path down to the image name where the image will be stored
		Path path = Paths.get(folder + uniqueName + imageFormat);

		//if directory without image name doesn't exist make the directory
		if(!new File(folder).exists()) {
			new File(folder).mkdirs();
		}
			image.transferTo(new File(path.toString()));
			
		
		//EXCEPTION HANDLER IS JUST A BIG BAG OF BEAN
		
//		byte[] bytes= image.getBytes();
//		
//		//Paths, Files
//		Files.write(path,bytes);
	}
		
	
}