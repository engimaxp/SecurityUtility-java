/**
 * Created by Wang on 2017/7/4.
 */

import com.google.common.hash.Hashing;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello World!");
//        String md5String = Hashing.md5().hashBytes("[{\"QuestionGuid\":\"a3228d15-d812-42ce-a15c-6b88bf7c002b\",\"Content\":\"asdas\",\"GroupCode\":\"HotelLocal\",\"CustomType\":\"Channel\"}]".concat("809d6e0be55d48be9a94a1cf39c7d34e").getBytes()).toString();
//        System.out.println(md5String);

//        SecurityRsaKeyPair keyPair =  SecurityKeyGenerator.Generate();
//        System.out.println(keyPair);
//
//        String data = "HelloWorld";
//        try{
//            String encrypted = Security.RSAEncrypt(keyPair.getPublicKey(),data);
//            String decrypted = Security.RSADecrypt(keyPair.getPrivateKey(),encrypted);
//            System.out.println(decrypted);
//        }catch(Exception exception){
//            exception.printStackTrace();
//        }


//        try {
//            String netPublicKey = "MIGdMA0GCSqGSIb3DQEBAQUAA4GLADCBhwKBgQCHf0TtJvM+2tsLDuvTuHkr3OZXI6k7pA7CFkKdJSczuzJN5sG84XIeBnzsZbsoaL962RVcyUhxGxkibxlIdvZ4z8ie2Ren2CwHMQhQJSKkyt+RpijZIMg8ZPF65x4ya2Q08LhzG9jZbGkuFeukd4pCzY6T65F96VSOafXafhlWzwIBAw==";
//            String encrypted = Security.RSAEncrypt(netPublicKey, data);
//            System.out.println("Java Use Net PublicKey");
//            System.out.println(encrypted);
//
//
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }

        try{
            String encrypted = "S/5vlgjiKrsb8zuTaR7375GuyAfdUTONPOh6SxBL9nELdrpyKpIjWSf6ZxmWESMjLxxcv4ri336et/E36bhGbrOvzwqQPMluO8NVWspt6qZaDw5bd5DA5Gd7XsduEvtQtqIAXaGINepA01kbO/ZGI1+yuMYXHcrAWWQYVkcffWc=";
            String javaPrivateKey ="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAN4ii2bSjgHk8yIVO+9d3tpf4llAR3hgIhyvWRfDx4OglLMEIkxJNycy+CqrfDaBCdq+/LH2r5z54wpW/l6TgvlzhVnvv/aDDZaJmfHbatr4wDR56D9cwvFmjhKBN6I/lnOKtweUTBge7QcIIN9COZ6V5b+HWZnyZRpEYiarVE/ZAgEDAoGAJQXB5nhtAFDTMFjfUo+lJGVQZDVhPrqwWh05g/X2lfAYyICwYgwz293UBxyUs8AsTx/UyFPH7377LGPVD8NAfpkR0pXlxBiyFK5Blsb+GGrzSR9SLyRUaevz5FtaLfe8oLrvE+lGPoXn2/myIm0gsSzu+E3YKuv2dK1L5jY6/4kCQQD05rf+bY9l8Hasf3CuQpemZgvWR9zsel5ndn2zoTput0uu1c2jXeWJPmV7wgOs43D6A1IzxZZLSY9WdfsWXJD3AkEA6DOybe/OiPCjx5D4gzOw0KZx57NHlk4Xd2A6qXlUAnNjekbCeUi7cj95sjINBpIGjkibgIMCH1TO3iTGT5XBrwJBAKNEeqmeX5lK+chU9cmBum7uso7ak0hRlET5qSJrfEnPh8nj3mzpQ7DUQ6fWrR3s9fwCNs0uZDIxCjmj/LmTC08CQQCazSGen98F9cKFC1BXd8s1xEvvzNpkNA+k6tHGUOKsTOz8LyxQ2yehf6Z2zAivDARe2xJVrKwU4zSUGIQ1DoEfAkEAnHY4KbkKEHbUbi4d8ywvoBNQhxMQWpUNCC7sU4PcRnzNbINf+gH2dMsuiqqDCfY+vtlBDmKNj2OnTLHsO9EXuQ==";
            String decrypted = Security.RSADecrypt(javaPrivateKey,encrypted);
            System.out.println("Net Use Java PublicKey");
            System.out.println(decrypted);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
