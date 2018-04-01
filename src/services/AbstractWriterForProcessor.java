package services;

import java.util.List;

import exceptions.WriterException;
import interfaces.IWriterForProcessor;

public class AbstractWriterForProcessor implements IWriterForProcessor{


	@Override
	public void writeListOfFormatData(List<String> listOfResults) throws WriterException {
	}

	@Override
	public void writeFormatData(String result) throws WriterException {
	}

	@Override
	public void close() {
	}

	
}
