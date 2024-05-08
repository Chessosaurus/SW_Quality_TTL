package backend;

import logging.CustomLogger;

/**
 * Exception which is thrown as soon as someone enters a number in an input.
 */
public class WrongInputException extends Exception{
    WrongInputException(){
        CustomLogger.error("Wrong Input Exception");
    }
}
