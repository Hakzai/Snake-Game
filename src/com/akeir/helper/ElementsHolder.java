/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akeir.helper;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;
import javafx.scene.Node;

/**
 *
 * @author Codeiro
 */
public class ElementsHolder {
    
    private Map<String, Object> elementsMap = new HashMap<>();
    
    public ElementsHolder(ObservableList<Node> elements)
    {
        setElements(elements);
    }
    
    private void setElements(ObservableList<Node> elementsList)
    {
        for(Node node : elementsList)
        {
            elementsMap.put(node.getId(), node);
        }
    }
    
    public Map<String, Object> getElementsMap()
    {
        return elementsMap;
    }
}
