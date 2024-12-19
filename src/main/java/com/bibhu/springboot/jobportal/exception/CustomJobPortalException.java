package com.bibhu.springboot.jobportal.exception;

public class CustomJobPortalException extends RuntimeException {

    /*
     * Required when we want to add a custom message when throwing the exception as
     * throw new CustomJobPortalException(" Custom Unchecked Exception ");
     */
    public CustomJobPortalException(String message) {
        // calling super invokes the constructors of all super classes
        // which helps to create the complete stacktrace.
        super(message);
    }

    /*
     * Required when we want to wrap the exception generated inside the catch block and rethrow it as
     * catch(ArrayIndexOutOfBoundsException e) {
     *         throw new CustomJobPortalException(e);
     * }
     */
    public CustomJobPortalException(Throwable cause) {
        // call appropriate parent constructor
        super(cause);
    }

    /*
     * Required when we want both the above as
     * catch(ArrayIndexOutOfBoundsException e) {
     *      throw new CustomJobPortalException(e, "File not found");
     * }
     */
    public CustomJobPortalException(String message, Throwable throwable) {
        // call appropriate parent constructor
        super(message, throwable);
    }
}
