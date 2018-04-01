package interfaces;

import java.util.List;
import models.FormatData;

public interface IReaderForProcessor {

	public List<FormatData> readListOfFormatData();
	
	public FormatData readFormatData();
	
	public void close();

}
