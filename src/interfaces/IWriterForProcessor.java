package interfaces;

import java.util.List;

public interface IWriterForProcessor {
	
	public void writeListOfFormatData(List<String> listOfResults);

	public void writeFormatData(String result);

}
