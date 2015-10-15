package fr.iutvalence.groupe8.eldwars.model.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * A class used to generate map names.
 * 
 * @author Nicolas
 * @version 20150521
 *
 */
public class NameGenerator {

	/**
	 * The name of the file containing the location types.
	 */
	private final static String LOCATION_TYPES_FILE = "location_types.txt";

	/**
	 * The name of the file containing the locations names.
	 */
	private final static String LOCATION_NAMES_FILE = "location_names.txt";

	/**
	 * The location types file.
	 */
	private final URL locationTypesURL;

	/**
	 * The location names file.
	 */
	private final URL locationNamesURL;
	
	/**
	 * The location types text.
	 */
	private String locationTypesText;

	/**
	 * The location names text.
	 */
	private String locationNamesText;

	/**
	 * The Location constructor.
	 * @throws FileNotFoundException
	 * @throws MalformedURLException
	 */
	public NameGenerator() throws FileNotFoundException, MalformedURLException {

		// Load the text files.
		this.locationTypesURL = new URL(LOCATION_TYPES_FILE);
		this.locationNamesURL = new URL(LOCATION_NAMES_FILE);
		
		Scanner sc = new Scanner(new File(LOCATION_TYPES_FILE));
		this.locationTypesText = sc.useDelimiter("\\Z").next();
		sc.close();
		
		sc = new Scanner(new File(LOCATION_NAMES_FILE));
		this.locationNamesText = sc.useDelimiter("\\Z").next();
		sc.close();

	}

	/**
	 * Get The next map name
	 * 
	 * @return
	 */
	public String nextName() {

		StringBuilder sb = new StringBuilder("The");
		
		// TODO

		return null;
	}
	
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}
