package com.ghagos.wsviajersey.repository;

import java.util.List;

import com.ghagos.wsviajersey.model.DvdItem;

public interface DvdRepository {

	/**
	 * @return - all DVDs in the library - an implementation of HTTP GET.
	 * @see com.ghagos.ws.jersey.repository.DvdRepositoryImpl#getAllDvds()
	 */
	List<DvdItem> getAllDvds();

	/**
	 * 
	 * @param genre
	 * @return
	 * @see com.ghagos.ws.jersey.repository.DvdRepositoryImpl#getDvdsByGenre()
	 */
	List<DvdItem> getDvdsByGenre(String genre);

	/**
	 * 
	 * @param genre
	 * @param year
	 * @return
	 * @see com.ghagos.ws.jersey.repository.DvdRepositoryImpl#getDvdByGenreForYear()
	 */
	DvdItem getDvdByGenreForYear(String genre, int year);

}