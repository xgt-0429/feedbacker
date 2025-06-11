package com.example.feedbacker.dto.response;

public class ApiResponse<T> {

    private int code;
    private String message;
    private T data;
    private ApiResponse(int c, String m, T d){ code=c; message=m; data=d; }
    public static <T> ApiResponse<T> success(T d){
        return new ApiResponse<>(0, "成功", d);
    }
    public static <T> ApiResponse<T> ok(){
        return new ApiResponse<>(0, "成功", null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
