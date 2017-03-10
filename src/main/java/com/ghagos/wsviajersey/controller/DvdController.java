package com.ghagos.wsviajersey.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ghagos.wsviajersey.model.DvdItem;
import com.ghagos.wsviajersey.repository.DvdRepository;
import com.ghagos.wsviajersey.repository.DvdRepositoryImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("dvds") //http://localhost:8080/rest-app-01/webapi/dvds
@Api(value="dvds", description="Lists all DVDs from the library")
public class DvdController {
	
	private DvdRepository dvdRepository = new DvdRepositoryImpl();
	
	/**
	 * Gets all DVDs from the DVD library. REST equivalent of READ in CRUD operations.
	 * @return an XML or JSON collection of a DVD object.
	 */
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<DvdItem> getAllDvds() {
		return dvdRepository.getAllDvds();
	}
	
	
	/**
	 * Gets all DVDs that belong to a specific genre in the DVD library. REST equivalent of READ in CRUD operations.
	 * @return an XML or JSON collection of a DVD object.
	 */
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{genre}") // appends genre from the root path, i.e., http://localhost:8080/rest-dvdapp-1/webapi/listdvds/genre
	@ApiOperation(value="Return a spcific genre in the DVD collection")
	public List<DvdItem> getDvdsByGenre(@PathParam("genre") String genre) {
		return dvdRepository.getDvdsByGenre(genre);
	}

	/**
	 * Gets the first matching DVD for a given year. REST equivalent of READ in CRUD operations.
	 * @return an XML or JSON of a DVD object.
	 */
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("{genre}/{year}") // appends genre then year from the root path, i.e., http://localhost:8080/rest-dvdapp-1/webapi/listdvds/genre/year
	@ApiOperation(value = "Return spcific genre of a given year of the DVD collection")
	public Response getDvdByGenreForYear(@PathParam("genre") String genre, @PathParam("year") int year) {
		if (genre == null || year == 0) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		DvdItem dvdItem = dvdRepository.getDvdByGenreForYear(genre, year);
		if (dvdItem == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok().entity(dvdItem).build();
	}

}
