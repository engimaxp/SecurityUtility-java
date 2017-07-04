/**
 * Created by Wang on 2017/7/4.
 */
public class SecurityRsaKeyPair {
    private String publicKey;
    private String privateKey;

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {

        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public String toString() {
        return
                "publicKey='" + publicKey + '\'' + "\r\n" +
                "privateKey='" + privateKey + '\'';
    }
}
