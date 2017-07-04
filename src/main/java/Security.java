
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import org.apache.commons.lang3.ArrayUtils;

import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.security.KeyFactory;
import javax.crypto.Cipher;
import java.util.Base64;

/**
 * Created by Wang on 2017/7/4.
 */
public class Security {

    private final static String ENCODING_STRING = "UTF-8";

    private final static String RSA_ALGORITHM = "RSA/ECB/PKCS1Padding";

    public static String RSAEncrypt(String publicKeyJava, String data) throws Exception {
        return rsaEncrypt(data,getPublicKey(publicKeyJava));
    }

    public static String RSADecrypt(String privateKeyJava, String data) throws Exception {
        return rsaDecrypt(data,getPrivateKey(privateKeyJava));
    }

    // 获取私钥
    private static PrivateKey getPrivateKey(String privateKey)
    {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(privateKey);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) keyFactory.generatePrivate(spec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    // 获取公钥
    private static PublicKey getPublicKey(String publicKey)
    {
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PublicKey key = factory.generatePublic(x509EncodedKeySpec);
            return key;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    // 非对称加密 公钥加密
    private static String rsaEncrypt(String text, PublicKey publicKey) throws Exception {
        String encryptedText = "";
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] dataReturn = null;
        byte[] data = text.getBytes(ENCODING_STRING);
        dataReturn = cipher.doFinal(data);
        encryptedText = org.bouncycastle.util.encoders.Base64.toBase64String(dataReturn);
        return encryptedText;
    }
    // 非对称解密 私钥解密
    private static String rsaDecrypt(String text, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        StringBuilder sb = new StringBuilder();
        byte[] data = Base64.getDecoder().decode(text);
        if (data.length <= 128) {
            sb.append(new String(cipher.doFinal(data)));
        } else {
            for (int i = 0; i < data.length; i += 128) {
                byte[] piece = ArrayUtils.subarray(data, i, i + 128);
                byte[] doFinal = cipher.doFinal(piece);
                sb.append(new String(doFinal, ENCODING_STRING));
            }
        }
        return sb.toString();
    }

    // 解密数据
    public static String desDecrypt(String message, String key) throws Exception {

        byte[] bytesrc = convertHexString(message);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(ENCODING_STRING));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes(ENCODING_STRING));
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] retByte = cipher.doFinal(bytesrc);
        return new String(retByte);
    }


    // 对称加密
    public static String desEncrypt(String value ,String key) {
        String result = "";
        try {
            value = java.net.URLEncoder.encode(value, ENCODING_STRING.toLowerCase());
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(ENCODING_STRING));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            IvParameterSpec iv = new IvParameterSpec(key.getBytes(ENCODING_STRING));
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            result = toHexString( cipher.doFinal(value.getBytes(ENCODING_STRING))).toUpperCase();

        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
        return result;
    }

    private static byte[] convertHexString(String ss) {
        byte digest[] = new byte[ss.length() / 2];
        for (int i = 0; i < digest.length; i++) {
            String byteString = ss.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte) byteValue;
        }
        return digest;
    }
    private static String toHexString(byte b[]) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String plainText = Integer.toHexString(0xff & b[i]);
            if (plainText.length() < 2)
                plainText = "0" + plainText;
            hexString.append(plainText);
        }
        return hexString.toString();
    }
}
