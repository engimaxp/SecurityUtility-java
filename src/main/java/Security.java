
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.util.PrivateKeyFactory;

import javax.crypto.Cipher;
import java.util.Base64;

/**
 * Created by Wang on 2017/7/4.
 */
public class Security {

    private final static String ENCODING_STRING = "UTF-8";

    private final static String RSA_ALGORITHM = "RSA/ECB/PKCS1Padding";

    public static String RSAEncrypt(String privateKeyJava, String data){
        return RSAEncrypt(privateKeyJava,data,ENCODING_STRING);
    }
    public static String RSAEncrypt(String privateKeyJava, String data, String encoding)
    {
        try{
            RSAKeyParameters privateKeyParam = (RSAKeyParameters) PrivateKeyFactory.createKey(Base64.getDecoder().decode(privateKeyJava));
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM,"BC");
            byte[] cipherbytes = data.getBytes(encoding);
            cipher.init(Cipher.ENCRYPT_MODE, privateKeyParam);
            cipherbytes = cipher.DoFinal(cipherbytes, 0, cipherbytes.Length);
            return Convert.ToBase64String(cipherbytes);
        }
        catch (Exception exception){
            System.out.println("RSAEncrypt:"+exception);
            return null;
        }
    }
}
