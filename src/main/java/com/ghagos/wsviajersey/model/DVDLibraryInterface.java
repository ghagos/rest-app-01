/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghagos.wsviajersey.model;

import java.util.List;

/**
 *
 * @author ghagos
 */
public interface DVDLibraryInterface {
        
    // Return a read-only reference the Collection of DVDs in the library
    public List<DvdItem> getDVDCollection();
    
    // Given a title, a year and a genre, 
    // 1.) create a new DVD item, 
    // 2.) add the new item to the library
    // 3.) return a reference to the new DVD item
    public DvdItem addDVD(String title, String year, String genre);
    
    // Return a read-only reference 
    // a List of genres currently recognized by the library
    public List<String> getGenres();
    
    // Given the name of a genre, 
    // check if that genre is present in the current genre list, 
    // if not present add the new genre to the list
    public void addGenre(String new_genre);

	DvdItem addDVD(DvdItem dvdItem);
}
