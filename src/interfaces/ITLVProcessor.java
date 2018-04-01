package interfaces;

import java.util.List;

import exceptions.ProcesssorException;
import exceptions.ReaderException;
import exceptions.WriterException;
import models.FormatData;

public interface ITLVProcessor {

	public List<String> processFormats(List<FormatData> listOfFormats);
	
	public String processFormat(FormatData format);
	
	public void process() throws ProcesssorException, WriterException, ReaderException;

}
