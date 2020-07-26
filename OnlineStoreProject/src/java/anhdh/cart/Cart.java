/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhdh.cart;

import anhdh.tblproduct.TblProductDTO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author babyz
 */
public class Cart implements Serializable {

    private Map<TblProductDTO, Integer> cart = null;

    public Cart() {
    }

    public Map<TblProductDTO, Integer> getCart() {
        return cart;
    }

    public void addItemToCart(TblProductDTO item) {
        if (cart == null) {
            cart = new HashMap<TblProductDTO, Integer>();
        }
        int quantity = 1;
        if (cart.containsKey(item)) {
            quantity = cart.get(item) + 1;
        }
        cart.put(item, quantity);
    }

    public void removeItemFromCart(TblProductDTO item) {
        if (cart == null) {
            return;
        }
        if (cart.containsKey(item)) {
            cart.remove(item);
            if (cart.isEmpty()) {
                cart = null;
            }
        }
    }
}
