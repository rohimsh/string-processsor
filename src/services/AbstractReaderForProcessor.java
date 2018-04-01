package services;

import java.util.Collections;
import java.util.List;

import interfaces.IReaderForProcessor;
import models.FormatData;

public class AbstractReaderForProcessor implements IReaderForProcessor{

	@Override
	public List<FormatData> readListOfFormatData() {
		return Collections.EMPTY_LIST;
	}
	
	@Override
	public FormatData readFormatData() {
		return null;
	}

	@Override
	public void close() {
	}
	

}
