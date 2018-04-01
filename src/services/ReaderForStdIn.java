package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import exceptions.ReaderException;
import models.FormatData;
import models.ProcessType;

public class ReaderForStdIn extends AbstractReaderForProcessor{
	public static final Logger logger = Logger.getLogger(ReaderForStdIn.class);
	
	private BufferedReader br;

	public ReaderForStdIn() {
		this.br = new BufferedReader(new InputStreamReader(System.in));
	}

	@Override
	public void close(){
		try {
			logger.info("Closing Reader class... " + getClass().getName());
			br.close();
		} catch (Exception exception) {
			logger.warn("Exception occurred while closing BufferedReader", exception);
		}
	}

	@Override
	public List<FormatData> readListOfFormatData() throws ReaderException {
		
		try {
			String input = br.readLine();
			if(input != null) {
				List<FormatData> listOfFormats = new ArrayList<FormatData>();
				String[] arrayOfInputs = input.split("(?="+ProcessType.LOWRCS.name()+")|(?="+ProcessType.REPLCE.name()+")|(?="+ProcessType.UPPRCS.name()+")");
				for(String formatString : arrayOfInputs) {
					listOfFormats.add(FormatData.createObjectFromString(formatString));
				}
				return listOfFormats;
			} 
		} catch (IOException ioe) {
			logger.error("Exception occurred while reading data from BufferedReader", ioe);
			throw new ReaderException(ioe);
		}
		
		return null;
	}
	
	
}
