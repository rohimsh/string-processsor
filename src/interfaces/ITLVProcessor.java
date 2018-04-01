package interfaces;

import java.util.List;

import models.FormatData;

public interface ITLVProcessor {

	public List<String> processFormats(List<FormatData> listOfFormats);
	
	public String processFormat(FormatData format);
	
	public void process();

	
}
