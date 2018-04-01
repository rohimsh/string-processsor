package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import models.Format;

public class ReaderForStdIn extends AbstractReaderForProcessor{
	public static final Logger logger = Logger.getLogger(ReaderForStdIn.class);
	
	private BufferedReader br;

	public ReaderForStdIn() {
		this.br = new BufferedReader(new InputStreamReader(System.in));
	}

	
	public void finalize(){
		try {
			br.close();

		} catch (Exception exception) {
			logger.warn("Exception occurred while closing BufferedReader", exception);
		}
	}

	@Override
	public Format readFormatData() {
		
		try {
			String input = br.readLine();
			if(input != null) {
				return Format.createObjectFromString(input);
			} else
				return null;
			
		} catch (IOException ioe) {
			logger.error("Exception occurred while reading data from BufferedReader", ioe);
		}
		
		return null;
	}
	
}
