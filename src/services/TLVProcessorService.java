package services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import exceptions.ReaderNotConfiguredException;
import exceptions.WriterNotConfiguredException;
import models.FormatData;
import models.IOType;
import models.ProcessType;

public class TLVProcessorService extends AbstractTLVProcessor{
	public static final Logger logger = Logger.getLogger(TLVProcessorService.class);

	public TLVProcessorService(IOType readInputType, IOType writeOutputType) throws WriterNotConfiguredException, ReaderNotConfiguredException {
		super(readInputType, writeOutputType);
	}


	@Override
	public void process() {
		try {

			logger.info("- x - Start of Output - x -");
			List<FormatData> formatsToProcess;
			List<String> processedFormatData;
			while((formatsToProcess = getReader().readListOfFormatData()) != null) {
				processedFormatData = processFormats(formatsToProcess);
				getWriter().writeListOfFormatData(processedFormatData);
			}
			logger.info("- x - End of Output - x -");
			
		} catch (Exception e) {
			logger.error("Error occurred while processing formats from InputStream: ", e);
		} finally {
			closeStreams();
		}
	}
	
	
	@Override
	public List<String> processFormats(List<FormatData> listOfFormats) {
		List<String> resultsAfterProcessing = new ArrayList<String>(listOfFormats.size());
		try {
			for(FormatData format : listOfFormats) {
				resultsAfterProcessing.add(processFormat(format));
			}
		} catch (Exception e) {
			logger.error("Error occurred while processing batch of formats: ", e);
		}
		
		return resultsAfterProcessing;
	}
	
	@Override
	public String processFormat(FormatData format) {
		String processedResult = TYPE_NOT_VALID;
		ProcessType processType = format.getFormatType();
		
		if(processType == null)
			return prepareResult(processType, processedResult);
		
		try {
			switch(processType) {
				case UPPRCS:
					processedResult = prepareResult(processType, upperCaseValueTillLength(format.getValue(), format.getLength()));
					break;
				case LOWRCS:
					processedResult = prepareResult(processType, lowerCaseValueTillLength(format.getValue(), format.getLength()));
					break;
				case REPLCE:
					processedResult = prepareResult(processType, THIS_STRING);
					break;
				default:
					break;
			}
			
		} catch (Exception e) {
			logger.error("Error occurred while processing format: " + format, e);
		}
		return processedResult;
	}
	
	
	private String prepareResult(ProcessType type, String value) {
		StringBuffer sb = new StringBuffer();
		
		if(type != null) {
			sb.append(type.name()).append(FormatData.SEPARATOR).append(value);
		} else {
			sb.append(value);
		}
		
		return sb.append(StringUtils.CR).append(StringUtils.LF).toString();
	}

	private String lowerCaseValueTillLength(String value, int length) {
		StringBuffer sb = new StringBuffer(value.substring(0, length));
		return sb.toString().toLowerCase();
	}

	private String upperCaseValueTillLength(String value, int length) {
		StringBuffer sb = new StringBuffer(value.substring(0, length));
		return sb.toString().toUpperCase();
	}

}
