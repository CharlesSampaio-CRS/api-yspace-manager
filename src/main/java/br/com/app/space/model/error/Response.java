package br.com.app.space.model.error;

import br.com.app.space.constants.MessagesConstants;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
public class Response {

    private String message;
    private Integer code;
    private Date timeStamp;
    private Object body;

    public Response(String message, Integer code, Date timeStamp, Object body) {
        this.message = message;
        this.code = code;
        this.timeStamp = timeStamp;
        this.body = body;
    }


    public Response success(){
       return Response.builder()
               .message(MessagesConstants.OK)
               .timeStamp(new Date())
               .code(200)
               .build();
    }

    public Response badRequest(){
        return Response.builder()
                .message(MessagesConstants.SPACE_ALREADY_EXISTS)
                .body(Collections.emptyList())
                .timeStamp(new Date())
                .code(400)
                .build();
    }
}
