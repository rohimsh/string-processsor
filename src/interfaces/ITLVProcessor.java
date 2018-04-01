package interfaces;

import java.util.List;

import models.Format;

public interface ITLVProcessor {

	public boolean processFormats(List<Format> listOfFormats);
	
	public String processFormat(Format format);
	
	public void process();

	
}
