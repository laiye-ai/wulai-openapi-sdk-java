package http;

import com.alibaba.fastjson.JSONObject;
import exceptions.Client_Exception;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;

import java.net.URI;
import java.util.HashMap;

public class Common_Request {
    private static HttpRequestBase request;


    private Common_Request() {
    }

    private static HttpPost getPostInstance() {
        if (!(request instanceof HttpPost)) {
            synchronized (Common_Request.class) {
                request = new HttpPost();
            }
        }
        return (HttpPost) request;
    }

    public static HttpRequestBase getRequest(HashMap<String, Object> params, String action, String opts) throws Client_Exception {
        if (opts.toUpperCase().equals("POST")) {
            synchronized (HttpRequest.class) {
                Object body = JSONObject.toJSON(params);
                HttpPost request_post = getPostInstance();
                request_post.setEntity(new StringEntity(body.toString(), "UTF-8"));
                request_post.setURI(URI.create(action));
                request = request_post;
            }
        } else if (opts.toUpperCase().equals("GET")) {
            throw new Client_Exception("1", "请使用POST方法");
        } else {
            throw new Client_Exception("1", "请使用POST方法");
        }
        return request;
    }
}
