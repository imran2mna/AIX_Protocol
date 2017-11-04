package lk.mango.protocol.structure;

import lk.mango.protocol.cons.AIXMeta;
import lk.mango.protocol.cons.AIXSeparator;

import java.util.StringTokenizer;

public class AIXHeader {
    private int groupId;
    private int requestId;
    private int channelId;
    //todo - include message time, ip address, client version


    AIXHeader(){

    }

    AIXHeader(String aixMessage) {
        if(aixMessage != null) parseMessage(aixMessage);
    }

    public String getAixString() {
        StringBuilder sb = new StringBuilder();

        if(groupId > 0){
            sb.append(AIXMeta.GROUP_ID);
            sb.append(AIXSeparator.EQUAL_SEPARATOR);
            sb.append(groupId);
            sb.append(AIXSeparator.FIELD_SEPARATOR);
        }

        if(requestId > 0){
            sb.append(AIXMeta.REQ_ID);
            sb.append(AIXSeparator.EQUAL_SEPARATOR);
            sb.append(requestId);
            sb.append(AIXSeparator.FIELD_SEPARATOR);
        }

        if(channelId > 0){
            sb.append(AIXMeta.CHANNEL_ID);
            sb.append(AIXSeparator.EQUAL_SEPARATOR);
            sb.append(channelId);
            sb.append(AIXSeparator.FIELD_SEPARATOR);
        }

        return sb.toString();
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }


    private void parseMessage(String aixMessage){
        StringTokenizer fieldSt = new StringTokenizer(aixMessage, AIXSeparator.FIELD_SEPARATOR);
        //todo - replace tokenizer with string substring
        StringTokenizer contentSt;
        while (fieldSt.hasMoreTokens()){
            contentSt = new StringTokenizer(fieldSt.nextToken(), AIXSeparator.EQUAL_SEPARATOR);
            String tag = contentSt.nextToken();
            String value = contentSt.nextToken();
            setAttribute(tag,value);
        }
    }

    private void setAttribute(String tag, String value){
        switch (Integer.parseInt(tag)){
            case AIXMeta.GROUP_ID:
                setGroupId(Integer.parseInt(value));
                break;

            case AIXMeta.REQ_ID:
                setRequestId(Integer.parseInt(value));
                break;

            case AIXMeta.CHANNEL_ID:
                setChannelId(Integer.parseInt(value));
                break;
        }
    }
}
