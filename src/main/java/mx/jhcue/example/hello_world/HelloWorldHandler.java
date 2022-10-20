package mx.jhcue.example.hello_world;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloWorldHandler {

  private final JsonObject config;

  public HelloWorldHandler(JsonObject config) {
    this.config = config;
  }

  public static Handler<RoutingContext> create(JsonObject config) {
      return new HelloWorldHandler(config)::handler;
  }

  private void handler(RoutingContext context) {
    log.debug("Handling Request {}", context.request().path());
    context.response()
      .end("Hello World!");
  }

}
