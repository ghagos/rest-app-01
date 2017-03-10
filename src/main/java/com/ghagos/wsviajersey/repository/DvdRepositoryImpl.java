package com.ghagos.wsviajersey.repository;

import java.util.ArrayList;
import java.util.List;

import com.ghagos.wsviajersey.model.DVDLibrary;
import com.ghagos.wsviajersey.model.DvdItem;

public class DvdRepositoryImpl implements DvdRepository {
	
	//private static final String dataDirectory = "C:\\zdev\\workspace\\zdvdws\\src\\main\\webapp\\WEB-INF\\data";
	private static final String dataDirectory = "/home/getachew/zdev/workspace/ws-projects/rest-dvdapp-1/src/main/webapp/WEB-INF/data";
	
	private DVDLibrary dvdLibrary = new DVDLibrary(dataDirectory);
	
	@Override
	public List<DvdItem> getAllDvds() {
		return dvdLibrary.getDVDCollection();
	}

	@Override
	public List<DvdItem> getDvdsByGenre(String genre) {
		List<DvdItem> allDvds = getAllDvds();
		List<DvdItem> dvdsByGenre = new ArrayList<>();
		
		for (DvdItem d : allDvds) {
			if (d.getGenre().equalsIgnoreCase(genre)) {
				dvdsByGenre.add(d);
			}
		}
		return dvdsByGenre;
	}

	@Override
	public DvdItem getDvdByGenreForYear(String genre, int year) {
		List<DvdItem> dvdsByGenre = getDvdsByGenre(genre);
		DvdItem dvdItem = new DvdItem();
		for (DvdItem d : dvdsByGenre) {
			int y = Integer.parseInt(d.getYear());
			if (y == year) {
				dvdItem = d;
			}
		}
		return dvdItem;
	}

}
