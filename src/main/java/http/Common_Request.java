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

//    private static HttpGet getGetInstance() {
//        if (!(request instanceof HttpGet)) {
//            synchronized (Common_Request.class) {
//                request = new HttpGet();
//            }
//        }
//        return (HttpGet) request;
//    }

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
//            synchronized (Common_Request.class){
//                HttpGet request_get=getGetInstance();
//                action=action+"?";
//                for (Map.Entry<String, Object> entry : params.entrySet()) {
//                    if (!entry.getValue().equals("")) {
//                        action = action + entry.getKey() + "=" + entry.getValue() + "&";
//                    }
//                }
//                request_get.setURI(URI.create(action));
//                request=request_get;
//            }
        } else {
            throw new Client_Exception("1", "请使用POST方法");
        }
        return request;
    }
}
