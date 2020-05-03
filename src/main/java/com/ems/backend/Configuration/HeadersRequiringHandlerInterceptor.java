package com.ems.backend.Configuration;

import com.ems.backend.annotation.RequiredHeaders;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.util.*;
import java.util.stream.Collectors;

public class HeadersRequiringHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<String> requiredHeaders = Optional.of(handler)
                .filter(h -> h instanceof HandlerMethod)
                .map(h -> (HandlerMethod) h)
                .map(h -> {
                    List<String> methodHeaders = Optional.ofNullable(AnnotatedElementUtils.getMergedAnnotation(h.getMethod(), RequiredHeaders.class))
                            .map(RequiredHeaders::value)
                            .map(Arrays::asList)
                            .orElse(Collections.emptyList());

                    List<String> classHeaders = Optional.ofNullable(AnnotatedElementUtils.getMergedAnnotation(h.getBeanType(), RequiredHeaders.class))
                            .map(RequiredHeaders::value)
                            .map(Arrays::asList)
                            .orElse(Collections.emptyList());

                    List<String> headers = new ArrayList<>();

                    headers.addAll(methodHeaders);
                    headers.addAll(classHeaders);

                    return headers;
                })
                .orElse(Collections.emptyList());

        if (!requiredHeaders.isEmpty()) {
            validate(request, requiredHeaders);
        }
        return super.preHandle(request, response, handler);
    }

    public void validate(HttpServletRequest request, List<String> requiredHeaders) {
        List<String> missingHeaders = requiredHeaders.stream()
                .filter(name -> StringUtils.isEmpty(request.getHeader(name)))
                .collect(Collectors.toList());
        if (!missingHeaders.isEmpty()) {
            throw new ValidationException("Missing headers " + StringUtils.join(missingHeaders.toArray(), ','));
        }
    }
}
