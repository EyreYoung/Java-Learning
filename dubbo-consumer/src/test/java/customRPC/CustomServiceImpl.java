package customRPC;

public class CustomServiceImpl implements CustomService{

    @Override
    public String sayHello(String param) {
        return "Hello, " + param + "!";
    }

}
