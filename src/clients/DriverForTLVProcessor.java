package clients;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import exceptions.ProcesssorException;
import exceptions.ReaderException;
import exceptions.ReaderNotConfiguredException;
import exceptions.WriterException;
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
		} catch (WriterNotConfiguredException wnce) {
			logger.error("Writer Not Configured for the given output type: " + outputType, wnce);
		} catch (ReaderNotConfiguredException rnce) {
			logger.error("Reader Not Configured for the given input type: " + inputType, rnce);
		} catch (ReaderException re) {
			logger.error("Exception occurred while reading formats data from Input: " + inputType, re);
		} catch (WriterException we) {
			logger.error("Exception occurred while writing formats data to Output: " + outputType, we);
		} catch (ProcesssorException pse) {
			logger.error("Exception occurred while processing formats: ", pse);
		} catch(Exception e) {
			logger.error("Some unknown error occurred while processing formats: ", e);
		}
		
	}

}
