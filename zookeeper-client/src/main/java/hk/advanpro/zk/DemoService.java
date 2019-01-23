package hk.advanpro.zk;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "zookeeper-service", path = "/api/demo")
public interface DemoService {

    @GetMapping(value = "/instance")
    String instance();

}
