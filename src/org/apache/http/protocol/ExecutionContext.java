package org.apache.http.protocol;


/**
 * @deprecated Interface ExecutionContext is deprecated
 */

@Deprecated
public interface ExecutionContext
{

    public static final String HTTP_CONNECTION = "http.connection";
    public static final String HTTP_REQUEST = "http.request";
    public static final String HTTP_RESPONSE = "http.response";
    public static final String HTTP_TARGET_HOST = "http.target_host";
    /**
     * @deprecated Field HTTP_PROXY_HOST is deprecated
     */
    @Deprecated
    public static final String HTTP_PROXY_HOST = "http.proxy_host";
    public static final String HTTP_REQ_SENT = "http.request_sent";
}
