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

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToMany
    private List<ProductQuantity> products;

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

    public int isInCart(Long id) {
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).getProduct().getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public void majProd(int index, int quantite) {
        this.list.get(index).setQuantity(quantite);
    }

    public void removeProduct(int pos) {
        this.list.remove(pos);
    }

    public void addtoCart(ProductQuantity pq) {
        this.list.add(pq);
    }

    public float getPriceOfCart() {
        int tot = 0;
        if (this.list.size() > 0) {
            for (int i = 0; i < this.list.size(); i++) {
                tot += this.list.get(i).getPrice();
            }
        }
        return tot;
    }

    /*public Long getId() {
     return id;
     }

     public void setId(Long id) {
     this.id = id;
     }

     @Override
     public int hashCode() {
     int hash = 0;
     hash += (id != null ? id.hashCode() : 0);
     return hash;
     }

     @Override
     public boolean equals(Object object) {
     // TODO: Warning - this method won't work in the case the id fields are not set
     if (!(object instanceof Cart)) {
     return false;
     }
     Cart other = (Cart) object;
     if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
     return false;
     }
     return true;
     }*/
    @Override
    public String toString() {
        return "model.entity.Cart [ " + list.size() + " products ]";
    }

}
