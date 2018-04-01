package services;

import java.util.Collections;
import java.util.List;

import exceptions.ReaderException;
import interfaces.IReaderForProcessor;
import models.FormatData;

public class AbstractReaderForProcessor implements IReaderForProcessor{

	@Override
	public List<FormatData> readListOfFormatData() throws ReaderException {
		return Collections.EMPTY_LIST;
	}
	

	@Override
	public void close() {
	}
	

}
