package org.example.handler;

import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransaction;
import io.seata.tm.api.GlobalTransactionContext;
import org.example.utils.ResultUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class CustomHandler {
    @ExceptionHandler(value = Exception.class)
    public ResultUtils roleExectionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) {
        //做法二（推荐）
//        try{
//            GlobalTransactionContext.reload(RootContext.getXID()).rollback();
//        }catch(TransactionException ee){
//            //seata的错误，不进行处理，不try catch的话
//            System.out.println(ee.getMessage());
//        }
//        return ResultUtils.error("库存不足");
//        //做法一（不推荐）
//        GlobalTransactionContext.reload(RootContext.getXID()).rollback();
//        return ResultUtils.error("库存不足");
        //做法三(调用者拿不到业务状态码进行逻辑判断，因为直接报500了，即使给调用者返回了自定义的异常，但是feign见是500，就直接报错了)
        response.setStatus(500);
        return ResultUtils.error("库存不足");
    }
}
