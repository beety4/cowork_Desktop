package cowork_Desktop.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class CryptoModule {
	// 암호화, 복호화를 위한 임의의 대칭 키
	private final String ALGORITHM = "AES";
    private final String KEY = "Thi5i$Se(retKeY="; // 암호화에 사용할 키 (16, 24, 32 bytes)
	    
	// AES256로 암호화 진행
	public String aesCBCEncode(String plainText) throws Exception {
		SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
	}

	// AES256으로 복호화 진행
	public String aesCBCDecode(String encodeText) throws Exception {
		SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encodeText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
	}
	
	// 쿠키 저장 파일의 파일 해쉬 확인 SHA-1
	public String getFileHash(String filePath) {
        try {
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);

            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] buffer = new byte[8192];
            int bytesRead;
            while (	(bytesRead = fileInputStream.read(buffer)) != -1) {
                md.update(buffer, 0, bytesRead);
            }
            
            byte[] digest = md.digest();
            
            // 해시값을 16진수로 변환
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            
            return sb.toString();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return null;
	}
	

	// 인증 코드용 5자리 문자/숫자
	public String getNewKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();
 
        for (int i = 0; i < 5; i++) {
            int index = rnd.nextInt(3);
            switch (index) {
                case 0:	// 소문자
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:	// 대문자
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:	// 숫자
                    key.append((rnd.nextInt(10)));
                    break;
            }
        }
        return key.toString();
    }

}
