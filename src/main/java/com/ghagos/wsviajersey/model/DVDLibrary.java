/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghagos.wsviajersey.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author ghagos
 */
public class DVDLibrary implements DVDLibraryInterface {
    private final File libraryFile;
    private final List<DvdItem> dvdCollection;
    private final List<String> genreList;
    
    // constructor 
    public DVDLibrary(String dataDirectory) {
        // Initialize data directory and collection file
        this.libraryFile = new File(dataDirectory, "my-library.txt");
        // Initialize and load the DVD collection
        this.dvdCollection = new ArrayList<DvdItem>();
        readFile();
        // Initialize and populate the genre list
        this.genreList = new ArrayList<String>();
        this.genreList.add("Drama");
        this.genreList.add("Sci-Fi");
        this.genreList.add("Romance");
        this.genreList.add("Musical");
        this.genreList.add("Action");
        this.genreList.add("Comedy");
    }
    
    @Override
    public List<DvdItem> getDVDCollection() {
        return Collections.unmodifiableList(dvdCollection);
    }
    
    @Override
    public DvdItem addDVD(String title, String year, String genre) {
        DvdItem item = new DvdItem(title, year, genre);
        dvdCollection.add(item);
        appendToFile(item);
        return item;
    }
    
    @Override
    public DvdItem addDVD(DvdItem dvdItem) {
        dvdCollection.add(dvdItem);
        appendToFile(dvdItem);
        return dvdItem;
    }
    
    @Override
    public List<String> getGenres() {
        return Collections.unmodifiableList(genreList);
    }
    
    @Override
    public void addGenre(String new_genre) {
        if ( ! genreList.contains(new_genre) ) {
            genreList.add(new_genre);
        }
    }
    
    private void readFile() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(libraryFile));
            String line;
            
            // Read every DVD entry in the file
            while ( (line = reader.readLine()) != null ) {
                String[] elements = line.split("\\|");
                
                // Extract the data fields for the record
                String title = elements[0];
                String year = elements[1];
                String genre = elements[2];
                
                // Add the new DVD item to the set
                DvdItem item = new DvdItem(title, year, genre);
                dvdCollection.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ( reader != null ) {
                try { reader.close(); } catch (Exception e) {}
            }
        }
    }
    
    private void appendToFile(DvdItem item) {
        PrintWriter libraryWriter = null;
        try {
            // Open a writer stream and mark it to append the new data
            libraryWriter = new PrintWriter(new FileWriter(libraryFile, true));
            
            libraryWriter.print(item.getTitle());
            libraryWriter.print('|' + item.getYear());
            libraryWriter.print('|' + item.getGenre() );
            libraryWriter.println();
            
        } catch (Exception e) {
            throw new RuntimeException(e);
            
            // Clean up IO resources
        } finally {
            if ( libraryWriter != null ) {
                try { libraryWriter.close(); } catch (Exception e) { System.err.println(e); }
            }
        }
    }
}
