package net.fezzed.mvvmdiffutil.microservice;


public class SwitchEvenItemsMicroService extends SwitchItemsMicroService {
    private final static String evenText = " Odd";

    @Override
    protected String getText() {
        return evenText;
    }

    @Override
    protected int isEven() {
        return 1;
    }
}
