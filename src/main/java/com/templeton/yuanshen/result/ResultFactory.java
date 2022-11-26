package com.templeton.yuanshen.result;

public class ResultFactory {

    public static Result Success(Object data) {
        return Result(ResultCode.SUCCESS, "成功", data);
    }

    public static Result Fail(String message) {
        return Result(ResultCode.FAIL, message, null);
    }

    public static Result Internal_Server_Error(String message){
        return Result(ResultCode.INTERNAL_SERVER_ERROR, message, null);
    }

    public static Result Result(ResultCode resultCode, String message, Object data) {
        return Result(resultCode.code, message, data);
    }

    public static Result Result(int resultCode, String message, Object data) {
        return new Result(resultCode, message, data);
    }
}
