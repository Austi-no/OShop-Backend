package com.austin.Oshop.config.security.filter;

import com.austin.Oshop.utils.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.http.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

import javax.servlet.http.*;
import java.io.*;

import static com.austin.Oshop.constant.SecurityContant.FORBIDDEN_MESSAGE;
import static org.springframework.http.HttpStatus.*;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 23/Sep/2022 - 9:43 PM
 * @project Oshop
 */
@Component
public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        HttpResponse httpResponse = new HttpResponse(FORBIDDEN.value(), FORBIDDEN, FORBIDDEN.getReasonPhrase().toUpperCase(), FORBIDDEN_MESSAGE);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(FORBIDDEN.value());
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, httpResponse);
        out.flush();

    }
}

