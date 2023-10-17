//package com.app.bank_app.security.config;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;
//import org.springframework.stereotype.Service;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Service
//@RequiredArgsConstructor
//public class JwtService {
//    private final String key="84dfc8d3da28734c8c73289d73134ba6372e45ab8280214e38c3d32fcfbffa67";
//
//    public <T> T extractClaim(String token,Function<Claims,T> claimsResolver){
//       final Claims claims=extractAll(token);
//       return claimsResolver.apply(claims);
//    }
//
//    public Claims extractAll(String token){
//       return Jwts
//               .parserBuilder()
//               .setSigningKey(getSigningKey())
//               .build()
//               .parseClaimsJws(token)
//               .getBody();
//    }
//    private String generateToken(UserDetails userDetails){
//            return generateToken(new HashMap<>() ,userDetails);
//    }
//    private String generateToken(
//            Map<String,Object> extraClaims,
//            UserDetails userDetails
//    ){
//        return Jwts
//                .builder()
//                .setClaims(extraClaims)
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
//                .signWith(getSigningKey())
//                .compact();
//
//    }
//    public Boolean isTokenValide(String token,UserDetails userDetails){
//        String email=extractemail(token);
//        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//    public boolean isTokenExpired(String token){
//        return  extractClaim(token,Claims::getExpiration).before(new Date());
//    }
//    private Key getSigningKey() {
//        byte[] keybyte= Decoders.BASE64.decode(this.key);
//        return Keys.hmacShaKeyFor(keybyte);
//    }
//
//    public String extractemail(String jwt){
//          return extractClaim(jwt,Claims::getSubject);
//    }
//}
