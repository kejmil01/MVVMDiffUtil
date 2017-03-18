package net.fezzed.mvvmdiffutil.microservice;


public class SwitchOddItemsMicroService extends SwitchItemsMicroService {
    private final static String evenText = " Even";

    @Override
    protected String getText() {
        return evenText;
    }

    @Override
    protected int isEven() {
        return 0;
    }
}
