package com.example.controller;
import com.example.model.ProductContract;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class UrunDetayController {
	@Autowired
    private ProductService productService;

	@GetMapping("/urunDetay")
	public String urunDetay(@RequestParam("id") String id, Model model) {
	    ProductContract product = productService.getProductById(id); //id' ye göre ürün detayı gösterir
	    if (product == null) {
	        return "error"; // Eğer ürün bulunamazsa error.html gösterilebilir.
	    }
	    model.addAttribute("product", product);
	    return "urunDetay"; // urunDetay.html sayfasına yönlendirme
	}

}
