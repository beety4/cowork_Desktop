package cowork_Desktop.domain.sign;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import cowork_Desktop.config.CryptoModule;
import cowork_Desktop.config.CustomUtility;
import cowork_Desktop.dto.CookieDTO;

public class CustomCookie {
	private final String filePath = "src/cookie.dat";
	private CustomSession session = new CustomSession();
	private CryptoModule cryptoModule = new CryptoModule();
	
	
	// 쿠키 값 설정
	public void setCookie() {
		try {
			// 파일이 존재하지 않다면 생성
			File file = new File(filePath);
			if(file.exists() == false) {
				file.createNewFile();
			}
			
			// 쿠키에 넣을 데이터 넣을 데이터 세팅
			String id = (String)session.getAttributes("sID");
			LocalDateTime expireDate = LocalDateTime.now().plusDays(3);
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm:ss");
			String datetime = expireDate.format(format);
			
			
			CookieDTO cookie = new CookieDTO(id, datetime);
			String digest = cryptoModule.aesCBCEncode(cookie.toString());
			System.out.println(cookie.toString());
			
			// 쿠키 파일에 저장
			BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false));
			bw.write(digest);
			bw.close();
			
			// 무결성을 위한 SHA-1 값 DB저장
			String fileHash = cryptoModule.getFileHash(filePath);
			UserDAO userDAO = new UserDAO();
			userDAO.setFileHash(id, fileHash);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 쿠키 값 가져오기
	public int getCookie() {
		try {
			// 파일이 존재하지 않다면 생성
			File file = new File(filePath);
			if(file.exists() == false) {
				return 2;
			}
			
			// 쿠키 데이터 가져오기
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line = br.readLine();
			br.close();
			if(line == null || line.isBlank()) {
				return 2;
			}
			
			// 쿠키 값 확인
			String original = cryptoModule.aesCBCDecode(line);
			StringTokenizer st = new StringTokenizer(original);
			CookieDTO cookie = new CookieDTO(st.nextToken(), st.nextToken());
			
			String id = cookie.getValue();
			boolean integrity = false;
			
			// 쿠키 데이터 무결성 검사
			UserDAO userDAO = new UserDAO();
			ArrayList<String> hashList = userDAO.getFileHash(id);
			String local = cryptoModule.getFileHash(filePath);
			
			for(String value : hashList) {
				if(local.equals(value)) {
					integrity = true;
				}
			}
			
			// 무결성 검사 실패! - 위변조 감지
			if(integrity == false) {
				invalidate();
				return 1;
			}
			
			// 쿠키 만료 확인 후 재발급
			LocalDateTime now = LocalDateTime.now();
			if(now.isAfter(cookie.getExpires())) {
				// 만료된 쿠키라면 DB에서도 id기준 처음 입력되었던 값 삭제
				userDAO.rmFileHash(id);
				invalidate();
				setCookie();
				return 0;
			}
			
			session.setAttributes("sID", id);
		}catch(Exception e) {
			e.printStackTrace();
			return 1;
		}
		return 0;
	}
	
	// 쿠키 파일 내용 삭제
	public void invalidate() {
		try {
			new FileWriter(filePath, false).close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
