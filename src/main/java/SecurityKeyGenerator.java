/**
 * Created by Wang on 2017/7/4.
 */
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.RSAKeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.RSAKeyGenerationParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.util.PrivateKeyInfoFactory;
import org.bouncycastle.crypto.util.SubjectPublicKeyInfoFactory;
import org.bouncycastle.util.encoders.Base64;

public class SecurityKeyGenerator {
    public static SecurityRsaKeyPair Generate() {
        RSAKeyPairGenerator generator = new RSAKeyPairGenerator();
        //RSA密钥构造器的参数
        RSAKeyGenerationParameters param = new RSAKeyGenerationParameters(
                java.math.BigInteger.valueOf(3),
                new java.security.SecureRandom(),
                1024,   //密钥长度
                25);
        //用参数初始化密钥构造器
        generator.init(param);
        //产生密钥对
        AsymmetricCipherKeyPair keyPair = generator.generateKeyPair();
        //获取公钥和密钥
        AsymmetricKeyParameter publicKey = keyPair.getPublic();
        AsymmetricKeyParameter privateKey = keyPair.getPrivate();
        if (((RSAKeyParameters) publicKey).getModulus().bitLength() < 1024) {
            System.out.println("failed key generation (1024) length test");
        }
        try {
            SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfoFactory.createSubjectPublicKeyInfo(publicKey);
            PrivateKeyInfo privateKeyInfo = PrivateKeyInfoFactory.createPrivateKeyInfo(privateKey);
            ASN1Primitive asn1ObjectPublic = subjectPublicKeyInfo.toASN1Primitive();
            byte[] publicInfoByte = asn1ObjectPublic.getEncoded();
            ASN1Primitive asn1ObjectPrivate = privateKeyInfo.toASN1Primitive();
            byte[] privateInfoByte = asn1ObjectPrivate.getEncoded();

            SecurityRsaKeyPair result = new SecurityRsaKeyPair();
            result.setPublicKey(Base64.toBase64String(publicInfoByte));
            result.setPrivateKey(Base64.toBase64String(privateInfoByte));
            return result;
        } catch (Exception exception) {
            System.out.println("SecurityKeyGenerator fail:" + exception);
            return null;
        }

    }
}
