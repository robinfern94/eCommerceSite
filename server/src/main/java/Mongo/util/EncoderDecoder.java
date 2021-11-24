package Mongo.util;

import java.util.Base64;

public class EncoderDecoder {

    public static final byte[] decode (String inputData){
        return Base64.getDecoder().decode(inputData);
    }

}
