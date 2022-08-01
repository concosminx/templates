package com.nimsoc.payara.api.rest.models.db;

import com.nimsoc.payara.api.rest.models.db.common.BaseEntity;
import javax.persistence.*;

@Entity
@Table(name = "product_categories")
@NamedQueries({
  @NamedQuery(name = "ProductCategoryEntity.findAll", query = "SELECT c FROM ProductCategoryEntity c"),
  @NamedQuery(name = "ProductCategoryEntity.findAllCount", query = "SELECT count(c) FROM ProductCategoryEntity c")
})
public class ProductCategoryEntity extends BaseEntity implements java.io.Serializable {

  @Column(name = "code")
  private String code;

  @Column(name = "description")
  private String description;

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

}
