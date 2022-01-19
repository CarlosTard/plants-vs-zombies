package tp.pr3.util;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import tp.pr3.exceptions.FileContentsException;

public class MyStringUtils {
	public static final String wrongPrefixMsg = "unknown game attribute: ";
	public static final String lineTooLongMsg = "too many words on line commencing: ";
	public static final String lineTooShortMsg = "missing data on line commencing: ";
	public static String repeat(String elmnt, int length) {
		String result = "";
		for (int i = 0; i < length; i++) {
			result += elmnt;
		}
		return result;
	}

	public static String centre(String text, int len){
		String out = String.format(" %" + len + "s %s %" + len + "s", "", text, "");
		float mid = (out.length() / 2);
		float start = mid - (len / 2);
		float end = start + len;
		return out.substring((int) start, (int) end);
	}

	// returns true if string argument is a valid filename
	public static boolean isValidFilename(String filename) {
		try {
			Paths.get(filename);
			return true;
		} catch (InvalidPathException ipe) {
			return false;
		}
	}

	// returns true if file with that name exists (in which case, it may not be
	// accessible )
	public static boolean fileExists(String filename) {
		try {
			Path path = Paths.get(filename);
			return Files.exists(path) && Files.isRegularFile(path);
		} catch (InvalidPathException ipe) {
			return false; // filename invalid => file cannot exist
		}
	}

	// returns true if file with that name exists and is readable
	public static boolean isReadable(String filename) {
		try {
			Path path = Paths.get(filename);
			return Files.exists(path) && Files.isRegularFile(path) && Files.isReadable(path);
		} catch (InvalidPathException ipe) {
			return false; // filename invalid => file cannot exist , never mind be readable
		}
	}

	public static String[] loadLine(BufferedReader inStream, String prefix, boolean isList)
			throws IOException, FileContentsException {
		String line = inStream.readLine().trim();
		// absence of the prefix is invalid
		if (!line.startsWith(prefix + ":"))
			throw new FileContentsException(wrongPrefixMsg + prefix);
		// cut the prefix and the following colon off the line
		// then trim it to get the attribute contents
		String contentString = line.substring(prefix.length() + 1).trim();
		String[] words;
		// the attribute contents are not empty
		if (!contentString.equals("")) {
			if (!isList) {
				// split non−list attribute contents into words
				// using 1−or−more−white−spaces as separator
				words = contentString.split("\\s+");
				// a non−list attribute with contents of more than one word is invalid
				if (words.length != 1)
					throw new FileContentsException(lineTooLongMsg + prefix);
			} else
				// split list attribute contents into words
				// using comma+0−or−more−white−spaces as separator
				words = contentString.split(",\\s*");
			// the attribute contents are empty
		} else {
			// a non−list attribute with empty contents is invalid
			if (!isList)
				throw new FileContentsException(lineTooShortMsg + prefix);
			// a list attibute with empty contents is valid;
			// use a zero−length array to store its words
			words = new String[0];
		}
		return words;
	}
}