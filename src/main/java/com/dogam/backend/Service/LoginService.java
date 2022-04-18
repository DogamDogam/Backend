package com.dogam.backend.Service;

import com.dogam.backend.Dto.UserInfoDto;
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
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final LoginRepository loginRepository;
    private static UserService userService;

    // 받은 code를 이용하여 access token을 발급받기 위한 함수
    public static String getKakaoAccessToken(String code){
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";
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
            e.printStackTrace();
        }
        return access_Token;

    }

    // 회원가입. 사용자 정보 가져오기. token = Access Token
    // 회원가입 검사를 통과한 이후 수행
    public static Optional<UserInfoDto> getUserInfo(String token) {
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

            while ((line = br.readLine())!= null) {
                result += line;
            }
            System.out.println("response body : " + result);

            // Gson 라이브러리로 JSON 파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            int id = element.getAsJsonObject().get("id").getAsInt();
            // 회원번호
            boolean hasEmail = kakao_account.get("has_email").getAsBoolean();
            String email = "";
            if (hasEmail) {
                email = kakao_account.get("email").getAsString();
            }

            String nickname = "";
            nickname = properties.get("nickname").getAsString();
            String image = properties.get("profile_image").getAsString();

            System.out.println("-----------------");
            System.out.println("id : " + id);
            System.out.println("email : " + email);
            System.out.println("nickname" + nickname);
            System.out.println("image : " + image);
            System.out.println("-----------------");

            InfoMap.put("id", id);
            InfoMap.put("email", email);
            InfoMap.put("nickname", nickname);
            InfoMap.put("image", image);

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(InfoMap.get("email"));
        if ((userService.findByEmail((String)(InfoMap.get("email")))).isPresent()) {
            ObjectMapper mapper = new ObjectMapper();
            UserInfoDto dto = mapper.convertValue(InfoMap, UserInfoDto.class);
            userService.saveUserInfo(dto);
            return userService.findByEmail((String)(InfoMap.get("email")));
        }
        else
            return userService.findByEmail((String)(InfoMap.get("email")));
    }

    // 로그인

    // 회원가입시 기존 회원정보가 있는지 검사

    // 로그인시 기존 회원정보와 같은 정보가 있는지 검사
}

