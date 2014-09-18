/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.searching;

import java.io.Serializable;

/**
 *
 * @author inilog
 */
public class ProductSearchOption implements Serializable {

    private String keyword;
    private boolean keywordRequiered = true;
    private float prixMax;

    public boolean isKeywordRequiered() {
        return keywordRequiered;
    }

    public void setKeywordRequiered(boolean keywordRequiered) {
        this.keywordRequiered = keywordRequiered;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public float getPrixMax() {
        return prixMax;
    }

    public void setPrixMax(float prixMax) {
        this.prixMax = prixMax;
    }

}
