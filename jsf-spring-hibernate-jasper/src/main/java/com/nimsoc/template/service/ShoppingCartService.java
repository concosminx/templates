package com.nimsoc.template.service;

import com.nimsoc.template.objects.ShoppingCart;
import java.util.List;

public interface ShoppingCartService {

  public List<ShoppingCart> getShoppingList(String sessionId);

  public void deleteShoppingCartItem(ShoppingCart shoppingCartItem);

  public void incrementQuantity(ShoppingCart shoppingCartItem);

  public void decrementQuantity(ShoppingCart shoppingCartItem);

  public void emptyShoppingCart(String sessionId);
}
