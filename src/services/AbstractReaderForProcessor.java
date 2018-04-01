package services;

import java.util.Collections;
import java.util.List;

import interfaces.IReaderForProcessor;
import models.Format;

public class AbstractReaderForProcessor implements IReaderForProcessor{

	@Override
	public List<Format> readListOfFormatData() {
		return Collections.EMPTY_LIST;
	}
	
	@Override
	public Format readFormatData() {
		return null;
	}
	

}
