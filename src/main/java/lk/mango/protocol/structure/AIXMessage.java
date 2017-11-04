package lk.mango.protocol.structure;

import lk.mango.protocol.cons.AIXSeparator;

import java.util.StringTokenizer;

public class AIXMessage {
    private AIXHeader aixHeader;
    private AIXBody aixBody;

    private String aixMessage;
    private boolean isProcessed;

    public AIXMessage(){}

    public AIXMessage(String aixMessage){
        setAixString(aixMessage);
    }

    public AIXHeader getAixHeader() {
        if(aixHeader == null && aixMessage != null){
            parseMessage(aixMessage);
        } else if (aixHeader == null){
            aixHeader = new AIXHeader();
        }
        return aixHeader;
    }

    public void setAixHeader(AIXHeader aixHeader){
        if(!isProcessed && aixMessage != null){
            parseMessage(aixMessage);
        }
        this.aixHeader = aixHeader;
    }

    public AIXBody getAixBody() {
        if(aixBody == null && aixMessage != null){
            parseMessage(aixMessage);
        } else if (aixBody == null){
            aixBody = new AIXBody();
        }
        return aixBody;
    }

    public void setAixBody(AIXBody aixBody){
        if(!isProcessed && aixMessage != null){
            parseMessage(aixMessage);
        }
        this.aixBody = aixBody;
    }

    public String getAixString(){
        // 1. progressed using message
        // 2. object initialized
        if(isProcessed || aixMessage == null){
            StringBuilder sb = new StringBuilder();
            if(aixHeader != null) {
                sb.append(aixHeader.getAixString());
            }
            sb.append(AIXSeparator.BODY_SEPARATOR);
            if(aixBody != null) {
                sb.append(aixBody.getAixString());
            }
            sb.append(AIXSeparator.BODY_SEPARATOR);
            return sb.toString();
        } else {
            return this.aixMessage;
        }
    }

    public void setAixString(String aixMessage){
            this.aixMessage  = aixMessage;
            if(isProcessed || (aixHeader != null || aixBody != null) ){
                aixHeader = null;
                aixBody = null;
                isProcessed = false;
            }
    }

    private void parseMessage(String aixMessage){
        StringTokenizer msgSt = new StringTokenizer(aixMessage, AIXSeparator.BODY_SEPARATOR);

        if(msgSt.hasMoreTokens()) aixHeader = new AIXHeader(msgSt.nextToken());
        if(msgSt.hasMoreTokens()) aixBody = new AIXBody(msgSt.nextToken());

        isProcessed = true;
    }
}
