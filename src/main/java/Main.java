/**
 * Created by Wang on 2017/7/4.
 */

import com.google.common.hash.Hashing;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello World!");
//        String md5String = Hashing.md5().hashBytes("[{\"QuestionGuid\":\"a3228d15-d812-42ce-a15c-6b88bf7c002b\",\"Content\":\"asdas\",\"GroupCode\":\"HotelLocal\",\"CustomType\":\"Channel\"}]".concat("809d6e0be55d48be9a94a1cf39c7d34e").getBytes()).toString();
//        System.out.println(md5String);

        SecurityRsaKeyPair keyPair =  SecurityKeyGenerator.Generate();
        System.out.println(keyPair);
    }
}
