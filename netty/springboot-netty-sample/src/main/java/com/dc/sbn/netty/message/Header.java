package com.dc.sbn.netty.message;


import lombok.Data;

/**
 * 自定义协议包头
 */
@Data
public class Header {
    private byte tag;
    /*  编码*/
    private byte encode;
    /*加密*/
    private byte encrypt;
    /*其他字段*/
    private byte extend1;
    /*其他2*/
    private byte extend2;
    /*会话id*/
    private String sessionid;
    /*包的长度*/
    private int length = 1024;
    /*命令*/
    private int cammand;

    public Header() {

    }

    public Header(String sessionid) {
        this.encode = 0;
        this.encrypt = 0;
        this.sessionid = sessionid;
    }

    public Header(byte tag, byte encode, byte encrypt, byte extend1, byte extend2, String sessionid, int length, int cammand) {
        this.tag = tag;
        this.encode = encode;
        this.encrypt = encrypt;
        this.extend1 = extend1;
        this.extend2 = extend2;
        this.sessionid = sessionid;
        this.length = length;
        this.cammand = cammand;
    }

    @Override
    public String toString() {
        return "header [tag=" + tag + "encode=" + encode + ",encrypt=" + encrypt + ",extend1=" + extend1 + ",extend2=" + extend2 + ",sessionid=" + sessionid + ",length=" + length + ",cammand="
                + cammand + "]";
    }
}
