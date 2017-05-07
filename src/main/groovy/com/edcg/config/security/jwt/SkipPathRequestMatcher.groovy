package com.edcg.config.security.jwt

import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.OrRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcher

import javax.servlet.http.HttpServletRequest
import java.util.stream.Collectors

/**
 * Created by Edgar on 07/05/2017.
 */
class SkipPathRequestMatcher implements RequestMatcher{
    private OrRequestMatcher matchers
    private RequestMatcher processingMatcher

    SkipPathRequestMatcher(List<String> pathsToSkip, String processingPath) {
        def m = pathsToSkip.stream()
                .map({it -> new AntPathRequestMatcher(it)}).collect(Collectors.toList())
        matchers = new OrRequestMatcher(m)
        processingMatcher = new AntPathRequestMatcher(processingPath)
    }

    @Override
    boolean matches(HttpServletRequest request) {
        if (matchers.matches(request))
            return false

        return processingMatcher.matches(request) ? true : false
    }
}
