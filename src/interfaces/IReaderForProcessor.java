package interfaces;

import java.util.List;

import exceptions.ReaderException;
import models.FormatData;

public interface IReaderForProcessor {

	public List<FormatData> readListOfFormatData() throws ReaderException;
	
	public void close();

}
