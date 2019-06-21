package com.aidingyun.ynlive.component.log;

/**
 * <p>
 * A simple logging interface abstracting logging APIs.
 * </p>
 * <p>
 * <p>
 * The five logging levels used by <code>Log</code> are (in order):
 * <ol>
 * <li>verbose</li>
 * <li>debug</li>
 * <li>info</li>
 * <li>warn</li>
 * <li>error</li>
 * </ol>
 *
 * @author ianmao 2014-6-11
 */
public interface LogProxy {

    // ----------------------------------------------------- Logging Properties

    /**
     * <p>
     * Is verbose logging currently enabled?
     * </p>
     * <p>
     * <p>
     * Call this method to prevent having to perform expensive operations (for
     * example, <code>String</code> concatenation) when the log level is more
     * than trace.
     * </p>
     *
     * @return true if verbose is enabled in the underlying logger.
     */
    boolean isVerboseEnabled();

    /**
     * <p>
     * Is debug logging currently enabled?
     * </p>
     * <p>
     * <p>
     * Call this method to prevent having to perform expensive operations (for
     * example, <code>String</code> concatenation) when the log level is more
     * than debug.
     * </p>
     *
     * @return true if debug is enabled in the underlying logger.
     */
    boolean isDebugEnabled();

    /**
     * <p>
     * Is info logging currently enabled?
     * </p>
     * <p>
     * <p>
     * Call this method to prevent having to perform expensive operations (for
     * example, <code>String</code> concatenation) when the log level is more
     * than info.
     * </p>
     *
     * @return true if info is enabled in the underlying logger.
     */
    boolean isInfoEnabled();

    /**
     * <p>
     * Is warn logging currently enabled?
     * </p>
     * <p>
     * <p>
     * Call this method to prevent having to perform expensive operations (for
     * example, <code>String</code> concatenation) when the log level is more
     * than warn.
     * </p>
     *
     * @return true if warn is enabled in the underlying logger.
     */
    boolean isWarnEnabled();

    /**
     * <p>
     * Is error logging currently enabled?
     * </p>
     * <p>
     * <p>
     * Call this method to prevent having to perform expensive operations (for
     * example, <code>String</code> concatenation) when the log level is more
     * than error.
     * </p>
     *
     * @return true if error is enabled in the underlying logger.
     */
    boolean isErrorEnabled();

    // -------------------------------------------------------- Logging Methods

    /**
     * <p>
     * Log a message with verbose log level.
     * </p>
     */
    void v(String tag, String message, Throwable t);

    /**
     * <p>
     * Log a message with debug log level.
     * </p>
     */
    void d(String tag, String message, Throwable t);

    /**
     * <p>
     * Log a message with info log level.
     * </p>
     */
    void i(String tag, String message, Throwable t);

    /**
     * <p>
     * Log a message with warn log level.
     * </p>
     */
    void w(String tag, String message, Throwable t);

    /**
     * <p>
     * Log a message with error log level.
     * </p>
     */
    void e(String tag, String message, Throwable t);

    void flush();
}
