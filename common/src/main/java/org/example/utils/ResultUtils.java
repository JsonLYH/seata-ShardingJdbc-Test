package org.example.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ResultUtils<T> {
    private Integer code;
    private String message;
    private T data;
    public static  ResultUtils error(String errorMessage){
        return new ResultUtils<>(500,errorMessage,null);
    }
    public static <T> ResultUtils<T> success(String message,T data){
        return new ResultUtils<T>(200,message,data);
    }
}
