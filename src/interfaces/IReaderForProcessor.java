package interfaces;

import java.util.List;
import models.Format;

public interface IReaderForProcessor {

	public List<Format> readListOfFormatData();
	
	public Format readFormatData();

}
