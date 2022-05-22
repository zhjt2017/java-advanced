package com.course.week02;

/**
 * Using HttpClient
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-05-15 11:29:10
 */
public class MyHttpClient {
    public static void main(String[] args) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http GET请求
        HttpGet httpGet = new HttpGet("http://127.0.0.1:8808/test");
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println("内容长度："+content.length());
                System.out.println("内容："+content);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
