package mx.jhcue.example.hello_world;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) {
    log.debug("Starting MainVerticle\nconfiguration:\n{}", config().encodePrettily());
  }
}
