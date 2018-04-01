package interfaces;

import java.util.List;

import exceptions.WriterException;

public interface IWriterForProcessor {
	
	public void writeListOfFormatData(List<String> listOfResults) throws WriterException;

	public void writeFormatData(String result) throws WriterException;

	public void close();

}
