package com.duocuc.products_srv.dto;

import java.util.Objects;

import com.duocuc.products_srv.model.Category;

public class ProductDto {

  private Long id;
  private String name;
  private String description;
  private Double price;
  private Integer stock;
  private Category category; // Usa la misma enumeración que en el modelo
  private String imageUrl;

  public ProductDto() {
  }

  public ProductDto(Long id, String name, String description, Double price, Integer stock, String imageUrl,
      Category category) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.stock = stock;
    this.imageUrl = imageUrl;
    this.category = category;
  }

  // Getters y Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  @Override
  public String toString() {
    return "ProductDto{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", price=" + price +
        ", stock=" + stock +
        ", category=" + category +
        ", imageUrl='" + imageUrl + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    ProductDto that = (ProductDto) o;
    return id.equals(that.id) &&
        name.equals(that.name) &&
        description.equals(that.description) &&
        price.equals(that.price) &&
        stock.equals(that.stock) &&
        category == that.category &&
        imageUrl.equals(that.imageUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, price, stock, category, imageUrl);
  }

}
