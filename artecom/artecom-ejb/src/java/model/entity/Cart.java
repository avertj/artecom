/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author inilog
 */
//@Entity
public class Cart implements Serializable {

    /*private static final long serialVersionUID = 1L;
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;

     @OneToMany*/
    private List<ProductQuantity> list;

    public Cart() {
        this.list = new ArrayList<>();
    }

    public List<ProductQuantity> getList() {
        return list;
    }

    /*public void setList(List<ProductQuantity> list) {
     this.list = list;
     */
    public boolean contains(Product p) {
        return list.contains(p);
    }

    public void add(ProductQuantity p) {
        if (list.contains(p)) {
            updateQuantity(p);
        } else {
            list.add(p);
        }
    }

    public void remove(ProductQuantity p) {
        if (list.contains(p)) {
            list.remove(p);
        }
    }

    public void updateQuantity(ProductQuantity p) {
        ProductQuantity pq = list.get(list.indexOf(p));
        pq.setQuantity(pq.getQuantity() + p.getQuantity());
    }

    public float getPrice() {
        float sum = 0;
        for (ProductQuantity pq : list) {
            sum += pq.getPrice();
        }
        return sum;
    }

    public Integer getSize() {
        return list.size();
    }

    public void empty() {
        list.clear();
    }

    @Override
    public String toString() {
        return "model.entity.Cart [ " + list.size() + " products ]";
    }

}
