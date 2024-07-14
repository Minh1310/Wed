/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Nhat Anh
 */
public class CategoryAggregation {
    
    public Set<String> nameSubCategories = new HashSet<>();

    public List<String> getNameSubCategories() {
        return new LinkedList<>(nameSubCategories);
    }

    public void setNameSubCategories(Set<String> nameSubCategories) {
        this.nameSubCategories = nameSubCategories;
    }
    
}
