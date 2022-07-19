package com.nimsoc.template.web;

import com.nimsoc.template.objects.ShoppingCart;
import com.nimsoc.template.objects.Products;
import com.nimsoc.template.utils.Helper;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import com.nimsoc.template.service.ShoppingCartService;
import com.nimsoc.template.service.ProductsService;

public class ProductsBB {

  private ProductsService productsService;
  private ShoppingCartService shoppingCartService;
  
  private transient UIComponent addbutton;

  public UIComponent getAddbutton() {
    return addbutton;
  }

  public void setAddbutton(UIComponent addbutton) {
    this.addbutton = addbutton;
  }

  public List<Products> getProducts() {
    return productsService.getProducts();
  }

  public List<ShoppingCart> getShoppingList() {
    String httpSessionId = Helper.getHttpSessionID();
    return shoppingCartService.getShoppingList(httpSessionId);
  }

  public void setShoppingCartService(ShoppingCartService shoppingCartService) {
    this.shoppingCartService = shoppingCartService;
  }

  public void setProductsService(ProductsService productsService) {
    this.productsService = productsService;
  }

  public void addProduct(Products product) {
    String httpSessionId = Helper.getHttpSessionID();
    if (!productsService.isAdded(product.getId(), httpSessionId)) {
      productsService.addProduct(product, httpSessionId);
    } else {
      FacesContext context = FacesContext.getCurrentInstance();
      FacesMessage message = new FacesMessage(" * The product already exists in the cart");
      context.addMessage(addbutton.getClientId(context), message);
    }
  }

  public void deleteShoppingCartItem(ShoppingCart shoppingCartItem) {
    shoppingCartService.deleteShoppingCartItem(shoppingCartItem);
  }

  public void emptyShoppingCart() {
    String httpSessionId = Helper.getHttpSessionID();
    shoppingCartService.emptyShoppingCart(httpSessionId);
  }

  public void incrementQuantity(ShoppingCart shoppingCartItem) {
    shoppingCartService.incrementQuantity(shoppingCartItem);
  }

  public void decrementQuantity(ShoppingCart shoppingCartItem) {
    shoppingCartService.decrementQuantity(shoppingCartItem);
  }

  public void invalidateSession() {
    Helper.invalidateSession();
  }
}
