package com.dogam.backend.Service;

import com.dogam.backend.Dto.UserInfoDto;
import com.dogam.backend.Model.Reply;
import com.dogam.backend.Model.UserInfo;
import com.dogam.backend.Repository.LoginRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final LoginRepository loginRepository;
    private final UserService userService;

    // 받은 code를 이용하여 access token을 발급받기 위한 함수
    public String getKakaoAccessToken(String code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";
        System.out.println("코드값 : " +code);
        // 인가 코드로 토큰 발급을 요청할 url (카카오 서버에게 보냄)

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // POST 요청을 위해 기본값인 false를 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // POST 요청에 필요로 요구하는 파라미터 스프림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            // https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#request-token
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=32563be2662a64d66f1e3547267b03df");
            sb.append("&redirect_uri=http://localhost:9090/oauth/kakao");
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode" + responseCode);

            // 요청을 통해 얻은 JSON 타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body" + result);

            // Gson 라이브러리에 포함된 클래스로 JSON 파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            System.out.println("오류발생");
            e.printStackTrace();
        }
        return access_Token;

    }
    // 회원가입. 사용자 정보 가져오기. token = Access Token

    // 회원가입 검사를 통과한 이후 수행
    public String getUserInfo(String token) {
        String reqUrl = "https://kapi.kakao.com/v2/user/me";
        HashMap<String, Object> InfoMap = new HashMap<>();

        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token);
            // 전송할 header를 작성하고 access token에 붙여 전송

            // 요청을 통해 얻은 JSON 타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            // Gson 라이브러리로 JSON 파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            long userId = element.getAsJsonObject().get("id").getAsInt();
            // 회원번호
            boolean hasEmail = kakao_account.get("has_email").getAsBoolean();
            String userEmail = "";
            if (hasEmail) {
                userEmail = kakao_account.get("email").getAsString();
            }

            String userNickname = "";
            userNickname = properties.get("nickname").getAsString();
            String userImage = "";
            boolean hasImage = kakao_account.get("profile").getAsJsonObject().get("is_default_image").getAsBoolean();
            System.out.println("이미지 여부 : " + hasImage);
            if (hasImage) {
                // 사용자가 등록한 프로필 사진이 없을 경우
                userImage = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png";
            }
            else {
                userImage = properties.get("profile_image").getAsString();
            }

            System.out.println("-----------------");
            System.out.println("id : " + userId);
            System.out.println("email : " + userEmail);
            System.out.println("nickname" + userNickname);
            System.out.println("image : " + userImage);
            System.out.println("-----------------");

            InfoMap.put("userId", userId);
            InfoMap.put("userEmail", userEmail);
            InfoMap.put("userNickname", userNickname);
            InfoMap.put("userImage", userImage);

            br.close();

            UserInfo userInfo = UserInfo.builder()
                    .userId(userId)
                    .userNickname(userNickname)
                    .userEmail(userEmail)
                    .userImage(userImage)
                    .build();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 계정 정보가 DB에 있는지 확인
        if (checkAccount((String)InfoMap.get("userEmail"))) {
            System.out.println("회원정보가 없음");
            // 없을 경우
            register(InfoMap);
            // 회원 가입
        }
        return (String)InfoMap.get("userEmail");
    }
    // 회원가입
    public void register(HashMap<String, Object> InfoMap) {
        ObjectMapper mapper = new ObjectMapper();
        UserInfo entity = mapper.convertValue(InfoMap, UserInfo.class);
        // HashMap -> UserInfo로 변환. 계정 저장시 이용
        userService.saveUserInfo(entity);
    }

    // 해당 계정이 DB에 있는지 확인
    public boolean checkAccount(String email) {
        System.out.println(userService.findByEmail(email).getUserEmail());
        if (userService.findByEmail(email).getUserEmail()==null) {
            System.out.println("이메일 없음");
            return true;
        }
        System.out.println("이메일 있음");
        return false;
    }

    // 계정 정보 조회
    public UserInfoDto login(String userEmail) {
        return userService.findByEmail(userEmail);
    }

    // 로그아웃
}

