package mx.jhcue.example.hello_world;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.ext.web.openapi.RouterBuilder;
import io.vertx.ext.web.openapi.RouterBuilderOptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) {
    log.debug("Starting MainVerticle, configuration:\n{}", config().encodePrettily());

    var routerBuilder = RouterBuilder.create(vertx, "hello-world.yaml");

    routerBuilder
      .compose(builder -> {
        var options = new RouterBuilderOptions()
          .setMountNotImplementedHandler(true)
          .setMountResponseContentTypeHandler(true)
          .setRequireSecurityHandlers(true);
        builder.setOptions(options);
        builder.operation("hello-world").handler(HelloWorldHandler.create(config()));
        return Future.succeededFuture(builder.createRouter());
      })
      .compose(router -> vertx.createHttpServer()
        .requestHandler(router)
        .listen(8080)
      )
      .onComplete(asyncResult -> {
        if (asyncResult.succeeded()) {
          log.debug("Http Server started at port {}", asyncResult.result().actualPort());
        } else {
          log.debug("Error Starting Http Server: {}", asyncResult.cause().getMessage());
        }
      });


  }
}
