package playground.websocket.stomp;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    private final SimpMessagingTemplate messagingTemplate;

    public GreetingController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
//        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/black")
    @SendTo("/topic/admin")
    public Greeting admin(HelloMessage message) throws Exception {
//        Thread.sleep(1000); // simulated delay
        return new Greeting("BlackList User " + HtmlUtils.htmlEscape(message.getName()) + " Joined!");
    }

    @GetMapping("/api/test")
    public void test() {
        messagingTemplate.convertAndSend("/topic/dev", "test api called!");
    }

}
