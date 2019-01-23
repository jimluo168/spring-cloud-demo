package hk.advanpro.zk;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/demo")
public class DemoController {

    private static final UUID INSTANCE_UUID = UUID.randomUUID();

    private final DemoService remoteService;

    public DemoController(DemoService remoteService) {
        this.remoteService = remoteService;
    }

    @GetMapping(value = "/remote-instance")
    public Object remoteInstance() {
        return this.remoteService.instance();
    }

    @GetMapping(value = "/instance")
    public Object instance() {
        return INSTANCE_UUID.toString();
    }

}