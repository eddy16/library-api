package com.edcg.util

import org.springframework.security.web.savedrequest.SavedRequest

import javax.servlet.http.HttpServletRequest

/**
 * Created by Edgar on 06/05/2017.
 */
class WebUtil {
    private static final String XML_HTTP_REQUEST = "XMLHttpRequest"
    private static final String X_REQUESTED_WITH = "X-Requested-With"

    private static final String CONTENT_TYPE = "Content-type"
    private static final String CONTENT_TYPE_JSON = "application/json"

    static boolean isAjax(HttpServletRequest request) {
        return XML_HTTP_REQUEST == request.getHeader(X_REQUESTED_WITH)
    }

    static boolean isAjax(SavedRequest request) {
        return request.getHeaderValues(X_REQUESTED_WITH).contains(XML_HTTP_REQUEST)
    }

    static boolean isContentTypeJson(SavedRequest request) {
        return request.getHeaderValues(CONTENT_TYPE).contains(CONTENT_TYPE_JSON)
    }
}
