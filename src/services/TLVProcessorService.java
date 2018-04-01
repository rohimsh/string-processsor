package services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import exceptions.ReaderNotConfiguredException;
import exceptions.WriterNotConfiguredException;
import models.Format;
import models.IOType;
import models.ProcessType;

public class TLVProcessorService extends AbstractTLVProcessor{
	public static final Logger logger = Logger.getLogger(TLVProcessorService.class);

	public TLVProcessorService(IOType readInputType, IOType writeOutputType) throws WriterNotConfiguredException, ReaderNotConfiguredException {
		super(readInputType, writeOutputType);
	}


	@Override
	public void process() {
		logger.info("- x - Start of Output - x -");
		Format formatToProcess = Format.emptyObject();
		String processedFormatData = StringUtils.EMPTY;
		while((formatToProcess = getReader().readFormatData()) != null) {
			processedFormatData = processFormat(formatToProcess);
			getWriter().writeFormatData(processedFormatData);
		}
		logger.info("- x - End of Output - x -");
	}
	
	
	@Override
	public boolean processFormats(List<Format> listOfFormats) {
		try {
			for(Format format : listOfFormats) {
				processFormat(format);
			}
			return true;
		} catch (Exception e) {
			logger.error("Error occurred while processing batch of formats: ", e);
		}
		
		return false;
	}
	
	@Override
	public String processFormat(Format format) {
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
			sb.append(type.name()).append(Format.SEPARATOR).append(value);
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
