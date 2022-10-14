package com.austin.Oshop.config.security.filter;


import com.austin.Oshop.utils.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.http.*;
import org.springframework.security.access.*;
import org.springframework.security.web.access.*;
import org.springframework.stereotype.*;

import javax.servlet.http.*;
import java.io.*;

import static com.austin.Oshop.constant.SecurityContant.ACCESS_DENIED_MESSAGE;
import static org.springframework.http.HttpStatus.*;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 23/Sep/2022 - 9:51 PM
 * @project Oshop
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        HttpResponse httpResponse =
                new HttpResponse(UNAUTHORIZED.value(), UNAUTHORIZED,
                        UNAUTHORIZED.getReasonPhrase().toUpperCase(),
                        ACCESS_DENIED_MESSAGE);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(UNAUTHORIZED.value());
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, httpResponse);
        out.flush();
    }
}
