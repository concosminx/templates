package com.nimsoc.dropwizard.api.models.db;

import com.nimsoc.dropwizard.api.models.db.common.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
@NamedQueries({
  @NamedQuery(name = "ProductEntity.findAll", query = "SELECT o FROM ProductEntity o"),
  @NamedQuery(name = "ProductEntity.findAllCount", query = "SELECT count(o) FROM ProductEntity o")
})
public class ProductEntity extends BaseEntity {

  @NotNull
  @Column(name = "code")
  private String code;

  @NotNull
  @Column(name = "description")
  private String description;

  @NotNull
  @Column(name = "value")
  private int value;

  @NotNull
  @Column(name = "category_id")
  private String productCategoryId;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public String getProductCategoryId() {
    return productCategoryId;
  }

  public void setProductCategoryId(String productCategoryId) {
    this.productCategoryId = productCategoryId;
  }

}
