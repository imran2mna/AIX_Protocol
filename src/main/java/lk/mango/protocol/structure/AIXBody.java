package lk.mango.protocol.structure;


public class AIXBody {
    //todo - introduce div objects for processing

    AIXBody(){}

    AIXBody(String aixMessage){
        parseMessage(aixMessage);
    }

    String getAixString() {
        StringBuilder sb = new StringBuilder();
        return  sb.toString();
    }

    private void parseMessage(String aixMessage){
        //todo - implement parse logic here
    }

}
