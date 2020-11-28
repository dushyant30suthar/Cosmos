package in.obvious.assignments.cosmos.framework.network;

import okhttp3.HttpUrl;

/*
 * This class has to look for http url formations. Which api to use which token to use.
 *
 * They must be handled here. */
public class ApiUrls {

    /*
     * https://api.myjson.com/bins/1f3vm2*/

    private static HttpUrl.Builder httpUrl;
    private static String AUTHORITY = "api.jsonbin.io";
    private static String PROTOCOL_SCHEME = "https";

    private static HttpUrl.Builder getHttpUrlBuilder() {
        if (httpUrl == null) {
            httpUrl = new HttpUrl.Builder();
        } else {
            httpUrl.build().newBuilder();
        }
        return httpUrl;
    }

    private static String getAuthority() {
        return AUTHORITY;
    }

    public static HttpUrl getBaseUrl() {
        return getHttpUrlBuilder().scheme(getProtocolScheme()).host(getAuthority()).build();
    }

    private static String getProtocolScheme() {
        return PROTOCOL_SCHEME;
    }

}
