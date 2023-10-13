package connector;

import java.io.IOException;

import okhttp3.Response;

public class DebugHandler implements okhttp3.Interceptor {

  @Override
  public Response intercept(Chain arg0) throws IOException {
    System.out.println("");
    System.out.printf("Request: %s %s%n", arg0.request().method(), arg0.request().url().toString());
    System.out.println("Request headers:");
    arg0.request().headers().toMultimap()
      .forEach((k, v) -> System.out.printf("%s: %s%n", k, v));
    if (arg0.request().body() != null) {
      System.out.println("Request body:");
      System.out.println(arg0.request().body().toString());
    }

    final Response response = arg0.proceed(arg0.request());

    System.out.println("");
    System.out.printf("Response: %s%n", response.code());
    System.out.println("Response headers:");
    response.headers().toMultimap()
      .forEach((k, v) -> System.out.printf("%s: %s%n", k, v));
    if (response.body() != null) {
      System.out.println("Response body:");
      System.out.println(response.body().string());
    }
    
    return response;
  }
}
