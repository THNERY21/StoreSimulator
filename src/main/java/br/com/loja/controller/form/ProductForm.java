package br.com.loja.controller.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.loja.model.Products;
import br.com.loja.model.TypeProduct;
import br.com.loja.repository.ProductsRepository;
import br.com.loja.service.interfaces.TypeProductInterface;

public class ProductForm {

	@NotNull
	@NotEmpty
	private String name;

	@NotNull
	@NotEmpty
	private String nameType;

	@NotNull
	private BigDecimal weight;

	@NotNull
	@NotEmpty
	private String size;

	public ProductForm(@NotNull @NotEmpty String name, @NotNull @NotEmpty String nameType, @NotNull BigDecimal weight,
			@NotNull @NotEmpty String size) {
		this.name = name;
		this.nameType = nameType;
		this.weight = weight;
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public String getNameType() {
		return nameType;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Products convert(TypeProductInterface typeProductSerice) {
		TypeProduct type = typeProductSerice.findByid_NameType(this.nameType);
		return new Products(name, type, weight, size);
	}

	public Products update(Long id, ProductsRepository productsRepository, TypeProductInterface typeProductSerice) {
		Products products = productsRepository.getOne(id);
		TypeProduct type = typeProductSerice.findByid_NameType(this.nameType);

		products.setName(this.name);
		products.setSize(this.size);
		products.setType(type);
		products.setWeight(this.weight);
		return products;
	}

}
