import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URI;
import java.security.MessageDigest;
import java.util.UUID;

public class WulaiClient {
    private final static String CONTENT_TYPE = "content-type";
    private final static String CONNECTION = "Keep-Alive";

    private static String API_VERSION = null;
    private static URI ENDPOINT = null;
    private static String PUBKEY = null;
    private static String SECRET = null;
    private static String nonce = null;
    private static Long timestamp = null;
    private static MessageDigest md = null;

    private static volatile WulaiClient client;


    static {
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private WulaiClient() {
    }

    private WulaiClient(String pubkey, String secret, String endpoint, String api_version) {
        PUBKEY = pubkey;
        SECRET = secret;
        API_VERSION = "/" + api_version;
        ENDPOINT = URI.create(endpoint);
    }

    private static String getSign(String nonce, Long timeStamp, String secret) {
        String source = nonce + timeStamp + secret;
        StringBuilder buffer = new StringBuilder();
        try {
            MessageDigest sha = (MessageDigest) md.clone();
            sha.update(source.getBytes());
            for (byte b : sha.digest()) {
                buffer.append(String.format("%02X", b));
            }
            return buffer.toString().toLowerCase();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static WulaiClient create(String pubkey, String secret, String endpoint, String api_version) {
        if (client == null) {
            synchronized (WulaiClient.class) {
                if (client == null) {
                    client = new WulaiClient(pubkey, secret, endpoint, api_version);
                }
            }
        }
        return client;
    }


    public HttpResponse process_common_request(HttpPost request) {
        nonce = UUID.randomUUID().toString().replace("-", "");
        timestamp = System.currentTimeMillis() / 1000;
        request.setHeader("Api-Auth-nonce", nonce);
        request.setHeader("Api-Auth-timestamp", String.valueOf(timestamp));
        request.setHeader("Api-Auth-sign", getSign(nonce, timestamp, SECRET));
        request.setHeader("content-type", CONTENT_TYPE);
        //request.setHeader("Connection", CONNECTION);
        request.setHeader("Api-Auth-pubkey", PUBKEY);
        request.setURI(ENDPOINT.resolve(API_VERSION + request.getURI()));
        try {
            return HttpClientBuilder.create().build().execute(request);
        } catch (IOException e) {
            return null;
        }

    }

}
