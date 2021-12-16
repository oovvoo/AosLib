package com.scode.commlib.security;

import android.content.Context;
import android.util.Log;
import com.scode.commlib.PrefManager;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.interfaces.ECPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

public class TokenManager {
    public static final String TAG = "TOKEN_MANAGER";
    public static ECPublicKey EC_PUBLIC_KEY = null; // 공개키로 서명을 검증 및 데이터 암호화
    // 토큰의 유효성+만료일자 확인

    /***
     * 토큰 검증이 실패 -> false
     * 토큰의 유효기간이 지남 -> false
     * @param token
     * @return
     * @throws JwtException
     */
    public static boolean verifyToken(String token) throws JwtException {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(EC_PUBLIC_KEY).build().parseClaimsJws(token).getBody();
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            Log.e(TAG, "토큰 만료" + token);
            return false;
        }
    }

    /***
     * 파라미터로 들어온 토큰을 체크하고, 토큰에 문제가 있으면 앱을 종료 시킨다.
     * 현재 receiver에서 전화가 왔을 경우만 사용. 이 경우에는  token을 삭제 하지 않는다.
     * @param context
     * @param token
     */
    public static boolean checkToken(Context context,String token)
    {
        if(!verifyToken(token)) {
            //토큰 만료를 서버에 알림
            return  false;
        }
        return true;
    }

    /***
     * 토큰을 체크하고, 토큰에 문제가 있으면 앱을 종료 시킨다.
     * @param context
     */
    public static boolean checkToken(Context context) throws GeneralSecurityException, IOException {
        if (verifyToken(getToken(context))) {
            return true;
        } else {
            //토큰 만료를 서버에 알림
            tokenExpired(context);
            return  false;
        }
    }

    public static void tokenExpired(Context context) throws GeneralSecurityException, IOException {

        PrefManager.removeKey(context, "token");
        PrefManager.removeKey(context, "key");
    }

    public static Claims getClaims(String token)
    {
        Claims claims = Jwts.parserBuilder().setSigningKey(EC_PUBLIC_KEY).build().parseClaimsJws(token).getBody();
        return claims;
    }
    public static void setToken(Context context, String token) throws GeneralSecurityException, IOException {
        PrefManager.setString(context,"token",token);
    }
    public static String getToken(Context context) throws GeneralSecurityException, IOException {
        return PrefManager.getString(context,"token");
    }
    //secret str을 받아 공개키를 앱에 할당한다.
    public static void registerKey(Context context,String secret_str) throws Exception {

        KeyFactory keyPairGenerator;
        try {
            keyPairGenerator = KeyFactory.getInstance("EC");
            EC_PUBLIC_KEY = (ECPublicKey) keyPairGenerator.generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(secret_str)));

        } catch (Exception e) {
            Log.i("EC_PUBLIC_KEY:", "키생성 오류");
        }
        PrefManager.setString(context,"key",secret_str);
    }

    public static String getPublicKeyString(Context context) throws GeneralSecurityException, IOException {
        return PrefManager.getString(context,"key");
    }
}
