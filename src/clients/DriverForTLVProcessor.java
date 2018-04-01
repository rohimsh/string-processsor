package clients;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import exceptions.ReaderNotConfiguredException;
import exceptions.WriterNotConfiguredException;
import models.IOType;
import services.TLVProcessorService;

public class DriverForTLVProcessor {
	public static final Logger logger = Logger.getLogger(DriverForTLVProcessor.class);

	public static void main(String[] args) {
		IOType inputType = IOType.STDIN;
		IOType outputType = IOType.STDOUT;
		
		try {
			BasicConfigurator.configure();
			logger.info("Starting TLVProcessorService...");
			TLVProcessorService service = new TLVProcessorService(inputType, outputType);
			logger.info("Processing TLVProcessorService for Input Type: " + inputType + " and Output Type: " + outputType);
			service.process();
			logger.info("Processed TLVProcessorService...");
		} catch (WriterNotConfiguredException e) {
			logger.error("Writer Not Configured for the given output type: " + outputType);
		} catch (ReaderNotConfiguredException e) {
			logger.error("Reader Not Configured for the given input type: " + inputType);
		}
		
	}

}
