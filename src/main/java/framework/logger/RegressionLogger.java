package framework.logger;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.log4j.Logger;

public class RegressionLogger {

    private Logger logger;
    private ExtentTest extentLogger;

    public RegressionLogger(Logger logger, ExtentTest extentLogger){
        this.logger = logger;
        this.extentLogger = extentLogger;
    }

    public void debug(Object message){
        logger.debug(message);
        extentLogger.log(Status.DEBUG, message.toString());
    }

    public void debug(Object message, Throwable e){
        logger.debug(message, e);
        extentLogger.log(Status.DEBUG, message.toString());
        extentLogger.log(Status.DEBUG, e);
    }


    public void info(Object message){
        logger.info(message);
        extentLogger.log(Status.INFO, message.toString());
    }

    public void info(Object message, Throwable e){
        logger.info(message, e);
        extentLogger.log(Status.INFO, message.toString());
        extentLogger.log(Status.INFO, e);
    }


    public void warn(Object message){
        logger.warn(message);
        extentLogger.log(Status.WARNING, message.toString());
    }

    public void warn(Object message, Throwable e){
        logger.warn(message, e);
        extentLogger.log(Status.WARNING, message.toString());
        extentLogger.log(Status.WARNING, e);
    }

    public void fatal(Object message){
        logger.fatal(message);
        extentLogger.log(Status.FATAL, message.toString());
    }

    public void fatal(Object message, Throwable e){
        logger.fatal(message, e);
        extentLogger.log(Status.FATAL, message.toString());
        extentLogger.log(Status.FATAL, e);
    }

    public void trace(Object message){
        logger.trace(message);
        extentLogger.log(Status.DEBUG, message.toString());
    }

    public void trace(Object message, Throwable e){
        logger.trace(message, e);
        extentLogger.log(Status.DEBUG, message.toString());
        extentLogger.log(Status.DEBUG, e);
    }

    public void error(Object message){
        logger.error(message);
        extentLogger.log(Status.ERROR, message.toString());
    }

    public void error(Object message, Throwable e){
        logger.error(message, e);
        extentLogger.log(Status.ERROR, message.toString());
        extentLogger.log(Status.ERROR,e);
    }
}
